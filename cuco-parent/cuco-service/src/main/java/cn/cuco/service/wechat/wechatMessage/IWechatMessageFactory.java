package cn.cuco.service.wechat.wechatMessage;

import java.util.Map;

import cn.cuco.service.wechat.wechatMessage.template.parent.WechatMsgParent;

/**
 * 
* @ClassName: IWechatMessageFactory 
* @Description: wechat消息工厂接口   wechatMessage制造者
* @author jiaxiaoxian
* @date 2017年2月21日 上午10:48:02
 */
public interface IWechatMessageFactory {
	
	/**
	 * 启动工厂引擎
	* @Title: startEngine 
	* @Description: TODO
	* @param @param openId
	* @param @param wechatMsgParent
	* @return void
	* @throws
	 */
	public void startEngine(String openId,WechatMsgParent wechatMsgParent);
	
	//下面就是各个业务方法的消息模板内容的构造
	public Map<String, Object> createTestMessage();
	
	/**
	* @Title: createSendInCarMessage 
	* @Description: 送车任务变更为送车中
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createSendInCarMessage();
	
	/**
	* @Title: createSendArrivedMessage 
	* @Description: 送车任务变更为已到达
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createSendArrivedMessage();
	
	/**
	* @Title: createSendCompletedMessage 
	* @Description: 送车任务变更为已完成
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createSendCompletedMessage();
	
	/**
	* @Title: createGetTakeCarMessage 
	* @Description: 取车任务变更为取车中
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createGetTakeCarMessage();
	
	/**
	* @Title: createGetArrivedMessage 
	* @Description: 取车任务变更为已到达
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createGetArrivedMessage();
	
	/**
	* @Title: createGetPendingStorageMessage 
	* @Description: 取车任务变更为待入库
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createGetPendingStorageMessage();
	
	/**
	* @Title: createSendTaskToCustomer 
	* @Description: 送车任务客服发送
	* @author huanghua
	* @return
	* @return Map<String,Object>
	 */
	public Map<String, Object> createSendTaskToCustomer();
	
	/**
	* @Title: createGetTaskToCustomer 
	* @Description:  取车任务客服发送
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createGetTaskToCustomer();
	
	/**
	* @Title: createSendTaskCustomerToOperater 
	* @Description: 客服通知管家送车
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createSendTaskCustomerToOperater();
	
	/**
	* @Title: createGetTaskCustomerToOperater 
	* @Description: 客服通知管家取车
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createGetTaskCustomerToOperater();
	
	/**
	* @Title: createSendOrderTakingtoCarService 
	* @Description: 管家接单给车务推送消息
	* @author huanghua
	* @return
	* @return Map<String,Object>
	 */
	public Map<String, Object> createSendOrderTakingtoCarService();
	
	/**
	* @Title: createOperaterGettoCarService 
	* @Description: 管家取车完成给车务发送入库消息
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createOperaterGettoCarService();
	/**
	* @Title: createCarRestrictionMessage 
	* @Description: 限行通知
	* @author huanghua
	* @return Map<String,Object>
	 */
	public Map<String, Object> createCarRestrictionMessage();
}