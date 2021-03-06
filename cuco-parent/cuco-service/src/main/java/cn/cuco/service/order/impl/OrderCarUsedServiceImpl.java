package cn.cuco.service.order.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.OrderCarUsedMapper;
import cn.cuco.entity.OrderCarUsed;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.page.PageResult;
import cn.cuco.service.order.OrderCarUsedService;

/**
 * @ClassName: OrderCarUsedServiceImpl
 * @Description: 用车明细相关接口的实现
 * @author zc.du
 * @date 2017年2月23日 上午10:52:08
 */
@Service(value = "orderCarUsedService")
public class OrderCarUsedServiceImpl implements OrderCarUsedService {

	@Autowired
    private OrderCarUsedMapper<OrderCarUsed> orderCarUsedMapper;
	
	@Override
	public PageResult<OrderCarUsed> getOrderCarUsedListByPage(OrderCarUsed orderCarUsed) {
		ParamVerifyUtils.paramNotNull(orderCarUsed);
		Integer page = orderCarUsed.getPage();
		Integer pageSize = orderCarUsed.getPageSize();
		Integer totalSize = this.orderCarUsedMapper.getOrderCarUsedCountByFuzzy(orderCarUsed);
		PageHelper.startPage(page, pageSize);
		List<OrderCarUsed> list = this.orderCarUsedMapper.getOrderCarUsedListByFuzzy(orderCarUsed);
		PageResult<OrderCarUsed> pageResult = new PageResult<OrderCarUsed>(page, pageSize, totalSize, list);
		return pageResult;
	}

	@Override
	public OrderCarUsed createOrderCarUsedForButtler(OrderCarUsed orderCarUsed) {
		// TODO Auto-generated method stub
		//计算行程
		//计算时间使用费用
		//计算退回金额
		
		return null;
	}

	@Override
	public OrderCarUsed createOrderCarUsedForSystem(OrderCarUsed orderCarUsed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderCarUsed createOrderCarUsedForAdditionalDeduction(OrderCarUsed orderCarUsed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getMemberDriveMileageStatistics(Long memberId) {
		ParamVerifyUtils.paramNotNull(memberId, "根据用户ID获取用户用车里程统计，用户ID不能为空");
		Map<String,Integer> map = new HashMap<String,Integer>();
		Integer a = this.orderCarUsedMapper.getMemberDriveCountByMileage(memberId,0,50);
		map.put("kmOne", a);
		Integer b = this.orderCarUsedMapper.getMemberDriveCountByMileage(memberId,51,100);
		map.put("kmTwo", b);
		Integer c = this.orderCarUsedMapper.getMemberDriveCountByMileage(memberId,101,300);
		map.put("kmThree", c);
		Integer d = this.orderCarUsedMapper.getMemberDriveCountByMileage(memberId,301,500);
		map.put("kmFour", d);
		Integer e = this.orderCarUsedMapper.getMemberDriveCountByMileage(memberId,501,null);
		map.put("kmFive", e);
		return map;
	}

	@Override
	public OrderCarUsed getOrderCarUsedById(Long id) {
		ParamVerifyUtils.paramNotNull(id,"根据解散订单ID查看结算订单时");
		return this.orderCarUsedMapper.selectByPrimaryKey(id);
	}

	@Override
	public int getOrderCarUsedCount(OrderCarUsed orderCarUsed) {
		ParamVerifyUtils.paramNotNull(orderCarUsed,"获取两个时间之间的结费次数，用车明细对象不能为空");
		ParamVerifyUtils.paramNotNull(orderCarUsed.getTimeStart(),"获取两个时间之间的结费次数，开始时间不能为空");
		ParamVerifyUtils.paramNotNull(orderCarUsed.getTimeEnd(),"获取两个时间之间的结费次数，结束时间不能为空");
		if(orderCarUsed.getTimeStart().after(orderCarUsed.getTimeEnd())){
			throw new RuntimeExceptionWarn("获取两个时间之间的结费次数，开始时间不能再结束时间之后");
		}
		return this.orderCarUsedMapper.getOrderCarUsedCount(orderCarUsed);
	}

	@Override
	public BigDecimal getOrderCarUsedMoney(OrderCarUsed orderCarUsed) {
		ParamVerifyUtils.paramNotNull(orderCarUsed,"获取两个时间之间的结费金额，用车明细对象不能为空");
		ParamVerifyUtils.paramNotNull(orderCarUsed.getTimeStart(),"获取两个时间之间的结费金额，开始时间不能为空");
		ParamVerifyUtils.paramNotNull(orderCarUsed.getTimeEnd(),"获取两个时间之间的结费金额，结束时间不能为空");
		if(orderCarUsed.getTimeStart().after(orderCarUsed.getTimeEnd())){
			throw new RuntimeExceptionWarn("获取两个时间之间的结费金额，开始时间不能再结束时间之后");
		}
		BigDecimal total = this.orderCarUsedMapper.getOrderCarUsedMoney(orderCarUsed);
		if(null == total)
			return new BigDecimal(0);
		return this.orderCarUsedMapper.getOrderCarUsedMoney(orderCarUsed).abs();
	}
}
