package cn.cuco.service.log;


import java.util.List;

import cn.cuco.entity.ButlerTask;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarAccident;
import cn.cuco.entity.CarInsuranceDetail;
import cn.cuco.entity.CarRepair;
import cn.cuco.entity.CarType;
import cn.cuco.entity.CarViolation;
import cn.cuco.entity.Carport;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OperateSetting;
import cn.cuco.entity.Parking;
import cn.cuco.entity.PreAuthorizedDebit;
import cn.cuco.page.PageResult;

/**
* @ClassName: OperateLogService 
* @Description: 日志接口
* @author huanghua
* @date 2017年2月21日 下午3:59:33
 */
public interface OperateLogService {
	
    /**
    * @Title: createOperateLog 
    * @Description: 创建日志
    * @param operateLog
    * @return OperateLog
     */
    public OperateLog createOperateLog(OperateLog operateLog);

    /**
    * @Title: getOperateLogList 
    * @Description: 取日志列表
    * @param @param operateLog
    * @return List<OperateLog>
     */
    public List<OperateLog> getOperateLogList(OperateLog operateLog);
    
    /**
    * @Title: getOperateLogCount 
    * @Description: 取日志总条数
    * @author huanghua
    * @param operateLog
    * @return List<OperateLog>
     */
    public Integer getOperateLogCount(OperateLog operateLog);
    
    /**
    * @Title: getOperateLogByPage 
    * @Description: 查询日志分页
    * @author huanghua
    * @param OperateLog
    * @return
    * @return PageResult<OperateLog>
     */
    public PageResult<OperateLog> getOperateLogByPage(OperateLog OperateLog);
    
    /**
    * @Title: createOperateLogForParking 
    * @Description: 创建运营参数日志
    * @author huanghua
    * @param operateSetting
    * @return OperateLog
     */
    public void createOperateLogForOperateSetting(OperateSetting operateSetting);
    
    /**
    * @Title: createOperateLogForCartype 
    * @Description: 创建车型上下架日志
    * @author huanghua
    * @param carType
    * @return
    * @return OperateLog
     */
    public void createOperateLogForCartype(CarType carType);
    
    /**
    * @Title: createOperateLogForCarport 
    * @Description: 创建车库上下架日志
    * @author huanghua
    * @param carport
    * @return void
     */
    public void createOperateLogForCarport(Carport carport);
    
    /**
    * @Title: createLogForParking 
    * @Description: 创建停车场日志
    * @author huanghua
    * @param parking
    * @return void
     */
    public void createLogForParking(Parking parking);
    
    /**
    * @Title: createOperateLogForCarRepair 
    * @Description: 维修状态日志
    * @author huanghua
    * @param carRepair
    * @return void
     */
    public void createOperateLogForCarRepair(CarRepair carRepair);
    
    /**
    * @Title: createOperateLogForCarAccident 
    * @Description: 出险状态日志
    * @author huanghua
    * @param carAccident
    * @return void
     */
    public void createOperateLogForCarAccident(CarAccident carAccident);
    
    /**   
     * @Title: createLogForCarInsurance   
     * @Description: 创建车辆保险日志
     * @param: CarInsuranceDetail      
     * @return: void      
     */
    public void createLogForCarInsurance(CarInsuranceDetail carInsuranceDetail);
    
    /**   
     * @Title: createLogForCar   
     * @Description: 创建车辆操作日志   
     * @param: Car      
     * @return: void      
     */
    public void createLogForCar(Car car);
    
    /**
    * @Title: createeOperateLogForButlerTask 
    * @Description: 创建任务日志
    * @author huanghua
    * @param butlerTask
    * @return void
     */
    public void createeOperateLogForButlerTask(ButlerTask butlerTask);
    
    /**
    * @Title: createeOperateLogForPreAuthorizedDebit 
    * @Description: 创建预授权日志
    * @author huanghua
    * @param preAuthorizedDebit
    * @return void
     */
    public void createeOperateLogForPreAuthorizedDebit(PreAuthorizedDebit preAuthorizedDebit);
    
    /**   
     * @Title: createLogForCarViolation   
     * @Description:创建车辆违章操作日志
     * @param: @param carViolation      
     * @return: void       
     */
    public void createLogForCarViolation(CarViolation carViolation);
    
}
