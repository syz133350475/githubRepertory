package cn.cuco.service.statistics.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarType;
import cn.cuco.entity.Supplier;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.service.basic.business.SupplierService;
import cn.cuco.service.basic.carport.CarTypeService;
import cn.cuco.service.statistics.CarStatisticsService;

@Service
public class CarStatisticsServiceImpl implements CarStatisticsService{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean;
	
	@Override
	public List<Map<String, String>> getOperationStatisticsByDay(Date startTime, Date endTime, Long carTypeId, Long carSupplierId) {
		//校验数据正确性
		this.validateBefore(startTime, endTime, carTypeId, carSupplierId);
		Car car = new Car();
		SqlSession session=null;
		List<Map<String, String>> returnList = new ArrayList<>();
		try {
			session=sqlSessionFactoryBean.getObject().openSession();
			long count =(endTime.getTime()-startTime.getTime()) / (1000 * 60 * 60 * 24) +1;
			this.logger.info("需要循环的次数为--"+count);
			for(int i=0;i<count;i++){
				Map<String, String> returnMap= new HashMap<>();
				startTime = DateUtils.getDayMaxDate(startTime);
				//按天查询的车辆总数
				car.setCarTypeId(carTypeId);
				car.setCarSupplierId(carSupplierId);
				car.setStartTime(startTime);
				//车辆总数
				Integer total = session.selectOne("cn.cuco.dao.CarMapper.getTotalByCarPortOrCarType", car);
				this.logger.info("查询日期为--"+DateUtils.formatDate(startTime,"yyyy-MM-dd")+"的车辆总数为--"+total);
				returnMap.put(DateUtils.formatDate(startTime,"yyyy-MM-dd"), total.toString());
				returnList.add(returnMap);
				startTime=DateUtils.addDays(startTime, 1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return returnList;
	}

	@Override
	public List<Map<String, String>> getOperationStatisticsByWeek(Date startTime, Date endTime, Long carTypeId, Long carSupplierId) {
		//校验数据正确性
		this.validateBefore(startTime, endTime, carTypeId, carSupplierId);
		Car car = new Car();
		SqlSession session=null;
		List<Map<String, String>> returnList = new ArrayList<>();
		try {
			session=sqlSessionFactoryBean.getObject().openSession();
			long count =DateUtils.getCountTwoDayWeek(startTime, endTime);
			this.logger.info("需要循环的次数为--"+count);
			for(int i=0;i<count;i++){
				Map<String, String> returnMap= new HashMap<>();
				startTime = DateUtils.getDayMaxDate(startTime);
				Date sunday = DateUtils.addWeeks(DateUtils.getSundayByDate(startTime), i);
				sunday = DateUtils.getDayMaxDate(sunday);
				if(endTime.before(sunday)){
					sunday = endTime;
				}
				//按天查询的车辆总数
				car.setCarTypeId(carTypeId);
				car.setCarSupplierId(carSupplierId);
				car.setStartTime(sunday);
				//车辆总数
				Integer total = session.selectOne("cn.cuco.dao.CarMapper.getTotalByCarPortOrCarType", car);
				this.logger.info("查询日期为--"+DateUtils.formatDate(startTime,"yyyy-MM-dd")+"的车辆总数为--"+total);
				returnMap.put(DateUtils.formatDate(startTime,"yyyy-MM-dd"), total.toString());
				returnList.add(returnMap);
				startTime=DateUtils.addWeeks(sunday, 1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return returnList;
	}

	@Override
	public List<Map<String, String>> getOperationStatisticsByMonth(Date startTime, Date endTime, Long carTypeId, Long carSupplierId) {
		//校验数据正确性
		this.validateBefore(startTime, endTime, carTypeId, carSupplierId);
		Car car = new Car();
		SqlSession session=null;
		List<Map<String, String>> returnList = new ArrayList<>();
		try {
			session=sqlSessionFactoryBean.getObject().openSession();
			int count =DateUtils.getCountTwoDayMonth(startTime, endTime);
			this.logger.info("需要循环的次数为--"+count);
			for(int i=0;i<count;i++){
				Map<String, String> returnMap= new HashMap<>();
				startTime = DateUtils.getDayMaxDate(startTime);
				Date monthLastDay = DateUtils.addMonths(DateUtils.getLastOfMonth(startTime), i);
				monthLastDay = DateUtils.getDayMaxDate(monthLastDay);
				if(endTime.before(monthLastDay)){
					monthLastDay = endTime;
				}
				//按天查询的车辆总数
				car.setCarTypeId(carTypeId);
				car.setCarSupplierId(carSupplierId);
				car.setStartTime(monthLastDay);
				//车辆总数
				Integer total = session.selectOne("cn.cuco.dao.CarMapper.getTotalByCarPortOrCarType", car);
				this.logger.info("查询日期为--"+DateUtils.formatDate(startTime,"yyyy-MM-dd")+"的车辆总数为--"+total);
				returnMap.put(DateUtils.formatDate(startTime,"yyyy-MM-dd"), total.toString());
				returnList.add(returnMap);
				startTime=DateUtils.addMonths(monthLastDay, 1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return returnList;
	}
	
	/**   
	 * @Title: validateBefore   
	 * @Description: 统计之前的校验调用   
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @param carportId
	 * @param: @param carTypeId      
	 * @return: void      
	 */
	private void validateBefore(Date startTime, Date endTime, Long carTypeId, Long carSupplierId){
		
		ParamVerifyUtils.paramNotNull(startTime,"开始日期不能为空");
		ParamVerifyUtils.paramNotNull(endTime,"结束日期不能为空");
		if(startTime.after(endTime)){
			throw new RuntimeExceptionWarn("开始时间不能再结束时间之后");
		}
		if(null!=carTypeId){
			CarType carType = this.carTypeService.getCarTypeById(carTypeId);
			if(null == carType){
				throw new RuntimeExceptionWarn("没有找到该车型信息--"+carTypeId);
			}
		}
		if(null!=carSupplierId){
			Supplier supplier = this.supplierService.getSupplierById(carSupplierId);
			if(null == supplier){
				throw new RuntimeExceptionWarn("没有找到供应商信息--"+carSupplierId);
			}
		}
	}

}