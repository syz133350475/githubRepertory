package cn.cuco.service.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.cuco.entity.MemberCarUsedAddress;
import cn.cuco.entity.OrderMemberCarUsed;
import cn.cuco.page.PageResult;

/**
 * @ClassName: OrderMemberCarUsedService
 * @Description: 用户用车订单相关接口
 * @author zc.du
 * @date 2017年2月23日 上午10:40:12
 */
public interface OrderMemberCarUsedService {

	/**
	 * @Title: getOrderMemberCarUsedNotComplete
	 * @Description: 查询用户待支付或者进行中的用车订单
	 * @param: orderMemberCarUsed
	 * @return: OrderMemberCarUsed
	 */
	public List<OrderMemberCarUsed> getOrderMemberCarUsedNotComplete(OrderMemberCarUsed orderMemberCarUsed);

	/**
	 * @Title: createOrderMemberCarUsedForApp
	 * @Description: app端发起用户用车
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return OrderMemberCarUsed
	 */
	public OrderMemberCarUsed createOrderMemberCarUsedForApp(OrderMemberCarUsed orderMemberCarUsed);

	/**
	 * @Title: updateOrderMemberCarUsedForChangeCar
	 * @Description: 用户换车
	 * @author zc.du
	 * @param memberCarUsed
	 * @return OrderMemberCarUsed
	 */
	public OrderMemberCarUsed updateOrderMemberCarUsedForChangeCar(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedStatusByConfirmUse 
	* @Description: 确认用车
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByConfirmUse(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedStatusByConfirmPayment 
	* @Description: 确认支付
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByConfirmPayment(OrderMemberCarUsed orderMemberCarUsed);

	/**
	 * @Title: updateOrderInfoBeforePayment
	 * @Description: 支付前，用户修改用户用车信息
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return OrderMemberCarUsed
	 */
	public OrderMemberCarUsed updateOrderInfoBeforePayment(OrderMemberCarUsed orderMemberCarUsed);
	
	/**
	 * @Title: updateOrderForReturnCarAdvance
	 * @Description: 用户提前还车
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return OrderMemberCarUsed
	 */
	public OrderMemberCarUsed updateOrderForReturnCarAdvance(OrderMemberCarUsed orderMemberCarUsed);
	
	/**
	 * @Title: updateOrderForReturnCarDelay
	 * @Description: 用户延期还车
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return OrderMemberCarUsed
	 */
	public OrderMemberCarUsed updateOrderForReturnCarDelay(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedStatusByUsing 
	* @Description: 修改用车记录状态为使用中
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByUsing(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedStatusByComplete 
	* @Description: 修改用车记录状态为已完成
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByComplete(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedStatusByCompleteForLose 
	* @Description: 修改用车记录状态已失联为已完成
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByCompleteForLose(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedStatusBycancel 
	* @Description: 修改用车记录状态为已取消
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusBycancel(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedStatusByLose
	* @Description: 修改用车记录状态为已失联
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByLose(OrderMemberCarUsed orderMemberCarUsed);
	
	/**
	 * @Title: getOrderMemberCarUsed
	 * @Description: 查询用户用车记录
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return OrderMemberCarUsed
	 */
	public OrderMemberCarUsed getOrderMemberCarUsed(OrderMemberCarUsed orderMemberCarUsed);

	/**
	 * @Title: getOrderMemberCarUsedListByPage
	 * @Description: 分页查询指定用户用车列表
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return PageResult<OrderMemberCarUsed>
	 */
	public PageResult<OrderMemberCarUsed> getOrderMemberCarUsedListByPage(OrderMemberCarUsed orderMemberCarUsed);

	/**
	 * @Title: getOrderMemberCarUsedCountByMemberId
	 * @Description: 根据用户ID查询用户用车次数
	 * @author zc.du
	 * @param memberId
	 * @return Integer
	 */
	public Integer getOrderMemberCarUsedCountByMemberId(Long memberId);

	/**
	 * @Title: getOrderMemberCarUsedMileagesByMemberId
	 * @Description: 根据用户ID查询用户行驶里程
	 * @author zc.du
	 * @param memberId
	 * @return BigDecimal
	 */
	public BigDecimal getOrderMemberCarUsedMileagesByMemberId(Long memberId);

	/**
	 * @Title: getMemberCarUsedHabit
	 * @Description: 根据用户ID获取用户用车里程统计
	 * @author zc.du
	 * @param memberId
	 * @return Map<String,Integer>
	 */
	public Map<String, Integer> getMemberDriveMileageStatistics(Long memberId);

	/**
	 * @Title: getMemberCarTypeUsedStatistics
	 * @Description: 根据用户ID获取用户使用车型统计
	 * @author zc.du
	 * @param memberId
	 * @return Map<String,Integer>
	 */
	public Map<String, Integer> getMemberCarTypeUsedStatistics(Long memberId);

	/**
	 * @Title: deleteMemberCarUsedAddress
	 * @Description: 删除用户用车地址
	 * @author zc.du
	 * @param memberCarUsedAddress
	 * @return Integer
	 */
	public Integer deleteMemberCarUsedAddress(MemberCarUsedAddress memberCarUsedAddress);

	/**
	 * @Title: getMemberCarUsedAddressListByPage
	 * @Description: 分页查询用户用车地址列表
	 * @author zc.du
	 * @param memberCarUsedAddress
	 * @return PageResult<MemberCarUsedAddress>
	 */
	public PageResult<MemberCarUsedAddress> getMemberCarUsedAddressListByPage(MemberCarUsedAddress memberCarUsedAddress);

	/**
	 * @Title: getLastOrderMemberCarUsedByMemberId
	 * @Description: 查询用户最后一次用车记录
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return OrderMemberCarUsed
	 */
	public OrderMemberCarUsed getLastOrderMemberCarUsedByMemberId(OrderMemberCarUsed orderMemberCarUsed);

	/**
	 * @Title: getMemberCarusedListByCarIdAndDate
	 * @Description: 根据车辆ID、时间查询用车记录(违章专用)
	 * @author zc.du
	 * @param orderMemberCarUsed(caroperateId:车辆ID;startDate:日期)
	 * @return List<OrderMemberCarUsed>
	 */
	public List<OrderMemberCarUsed> getOrderMemberCarUsedListByCarIdAndDate(OrderMemberCarUsed orderMemberCarUsed);

	/**
	 * @Title: getCarUsedCountByDate
	 * @Description: 查询指定时间段内指定车型确认支付用车次数(统计用)
	 * @author zc.du
	 * @param orderMemberCarUsed
	 * @return Integer
	 */
	public Integer getCarUsedCountByDate(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: getCarUsedCostMoneyByDate 
	* @Description: 查询指定时间段内指定车型确认支付的用车金额
	* @author zc.du
	* @param orderMemberCarUsed
	* @return BigDecimal
	*/
	public BigDecimal getCarUsedCostMoneyByDate(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: updateOrderMemberCarUsedForSettingCar 
	* @Description: 分配车辆
	* @author zc.du
	* @param orderMemberCarUsed
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed updateOrderMemberCarUsedForSettingCar(OrderMemberCarUsed orderMemberCarUsed);
	
	/** 
	* @Title: getOrderMemberCarUsedByMobileAndCarPlateNum 
	* @Description: 根据手机号、车牌号查询使用中的用车记录（手机号、车牌号至少一个不为空）
	* @author zc.du
	* @param mobile
	* @param carPlateNum
	* @return OrderMemberCarUsed
	*/
	public OrderMemberCarUsed getOrderMemberCarUsedByMobileAndCarPlateNum(String mobile,String carPlateNum);
	
}
