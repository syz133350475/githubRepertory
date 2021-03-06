package cn.cuco.controller.manager.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.authorization.entity.User;

import cn.cuco.annotation.API;
import cn.cuco.common.httpservice.HttpServiceContext;
import cn.cuco.common.utils.PrePersistUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.entity.ButlerTask;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarType;
import cn.cuco.entity.Carport;
import cn.cuco.entity.Member;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OrderMemberCarUsed;
import cn.cuco.entity.TransferList;
import cn.cuco.enums.ButlerTaskType;
import cn.cuco.enums.TaskStatus;
import cn.cuco.enums.security.RoleEnum;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.carport.CarTypeService;
import cn.cuco.service.basic.carport.CarportService;
import cn.cuco.service.car.carInfo.CarService;
import cn.cuco.service.order.OrderMemberCarUsedService;
import cn.cuco.service.security.SecurityService;
import cn.cuco.service.task.ButlerTaskService;

/**
 * 
 * @ClassName: ButlerTaskController 
 * @Description: 任务
 * @author: wangchuntao 
 * @date: 2017年2月22日 下午2:05:27
 */
@RestController
public class ButlerTaskController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private ButlerTaskService butlerTaskService;
    @Autowired
    private OrderMemberCarUsedService memberCarUsedService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarService carService;
    @Autowired
    private CarportService carportService;
    @Autowired
    private SecurityService securityService;
    
    
    @API("客服分配管家")
    @RequestMapping(value = "/butlerTask/updateTaskCustomerToOperater",method = RequestMethod.POST)
    public Object updateTaskCustomerToOperater(@RequestBody ButlerTask butlerTask){
		
		ParamVerifyUtils.paramNotNull(butlerTask);
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "客服操作，任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId(), "客服操作,管家id不能为空");
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		return butlerTaskService.updateTaskCustomerToOperater(butlerTask);
		
    }
    
    /**
     * 
     * @Title: findButlerTaskListByPage 
     * @Description: 查询任务集合(根据待处理、处理中、已结束进行查询)
     * @author: wangchuntao 
     * @param butlerTask
     * @return Object
     * @date: 2017年2月22日 下午2:28:24
     */
    @API(value="任务列表")
    @RequestMapping(value = "/butlerTask/getButlerTaskListByPage",method = RequestMethod.POST)
    public Object getButlerTaskListByPage(@RequestBody ButlerTask butlerTask){
    	ParamVerifyUtils.paramNotNull(butlerTask);
    	ParamVerifyUtils.paramNotNull(butlerTask.getUseCarType());
//    	0:待处理 1处理中2已结束
    	if(butlerTask.getUseCarType()==0){
    		return butlerTaskService.getButlerTaskPageForWaitDealWith(butlerTask);
    	}else if(butlerTask.getUseCarType()==1){
    		return butlerTaskService.getButlerTaskPageForProcessing(butlerTask);
    	}else{
    		return butlerTaskService.getButlerTaskPageForComplete(butlerTask);
    	}
    }
    
    
    
 	/** 
 	 * @Title: getCustomerAndOperaterList 
 	 * @Description: 负责人列表
 	 * @author: wangchuntao 
 	 * @return 
 	 * @return Object
 	 * @date: 2017年2月22日 下午4:47:29
 	 */
 	 @API("负责人列表")
     @RequestMapping(value = "/butlerTask/getCustomerAndOperaterList",method = RequestMethod.GET)
     public Object getCustomerAndOperaterList(){
 		List<User> usersByRole = securityService.getUsersByRole(RoleEnum.CUCO_GJ);
 		return usersByRole;
 	 }
    /**
     * 
     * @Title: findButlerTaskDetail 
     * @Description: 任务详细
     * @author: wangchuntao 
     * @param carUsedId 用车记录id
     * @return Object
     * @date: 2017年2月22日 下午2:39:48
     */
    @API(value="任务详情")
    @RequestMapping(value = "/butlerTask/getButlerTaskDetail",method = RequestMethod.GET)
    public Object getButlerTaskDetail(Long carUsedId){
    	ParamVerifyUtils.paramNotNull(carUsedId);
    	
    	Map<String, Object> returnMap = new HashMap<>();
    	//查询用车记录
    	OrderMemberCarUsed memberCarUsed = new OrderMemberCarUsed();
    	memberCarUsed.setId(carUsedId);
		memberCarUsed = memberCarUsedService.getOrderMemberCarUsed(memberCarUsed );
		if(null==memberCarUsed){
			ExceptionUtil.throwWarn("无此用车记录");
		}
//		查预约车型
		CarType carType = carTypeService.getCarTypeById(memberCarUsed.getOrderCartypeId());
		
		Carport carport = carportService.getCarportById(memberCarUsed.getCarportId());
		Member member = new Member();
		member.setName(memberCarUsed.getMemberName());
		member.setMobile(memberCarUsed.getMemberMobile());
		member.setCarportName(carport.getCarportName());
		returnMap.put("member", member);
		
//    	根据用车记录查询任务
        ButlerTask butlerTask = new ButlerTask();
        butlerTask.setCarUsedId(carUsedId);
		List<ButlerTask> butlerTaskList = butlerTaskService.getButlerTaskList(butlerTask );
//		2:取车任务；3:送车任务；
		ButlerTask butlerTaskGet=null;
		ButlerTask butlerTaskSend=null;
		for (ButlerTask butlerTaskTemp : butlerTaskList) {
			if(butlerTaskTemp.getType()==2){
				butlerTaskGet=butlerTaskService.getButlerTaskById(butlerTaskTemp.getId());
				butlerTaskGet.setBrand(carType.getBrand());
				butlerTaskGet.setCarType(carType.getCarType());
				
				OperateLog operateLog = new OperateLog();
				operateLog.setOperationId(butlerTaskGet.getId());
				PageResult<OperateLog> taskLogByPage = butlerTaskService.getButlerTaskLogByPage(operateLog );
				returnMap.put("getTaskLogs", taskLogByPage.getItems());
				
			}else if(butlerTaskTemp.getType()==3){
				butlerTaskSend=butlerTaskService.getButlerTaskById(butlerTaskTemp.getId());
				Car car = carService.getCarById(butlerTaskSend.getCarOperateId());
				butlerTaskSend.setBrand(car.getCarBrand());
				butlerTaskSend.setCarType(car.getCarType());
				butlerTaskSend.setCarPlateNum(car.getCarPlateNum());
				
				OperateLog operateLog = new OperateLog();
				operateLog.setOperationId(butlerTaskSend.getId());
				PageResult<OperateLog> taskLogByPage = butlerTaskService.getButlerTaskLogByPage(operateLog );
				returnMap.put("sendTaskLogs", taskLogByPage.getItems());
			}
		}
		returnMap.put("getTask", butlerTaskGet);
		returnMap.put("orderCarUsedDay", memberCarUsed.getOrderCarUsedDay());
		returnMap.put("sendTask", butlerTaskSend);
		return returnMap;
    }
	/**
	 * 
	 * @Title: updateButler 
	 * @Description: 转派管家
	 * @author: wangchuntao 
	 * @param butlerTask
	 * @return Object
	 * @date: 2017年2月22日 下午2:59:00
	 */
	@API("转派管家")
    @RequestMapping(value = "/butlerTask/updateButler",method = RequestMethod.POST)
    public Object updateButler(@RequestBody ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask);
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "转派管家，任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId(), "转派管家，管家ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterName(), "转派管家-管家姓名不能为空");
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		return butlerTaskService.updateTaskHousekeeper(butlerTask);
    }
	

	/**
	 * @Title: changeButlerTaskstatus 
	 * @Description: 客服操作送车任务
	 * @author: wangchuntao 
	 * @param butlerTask
	 * @return Object
	 * @date: 2017年2月22日 下午3:05:32
	 */
	@API("客服操作送车任务")
    @RequestMapping(value = "/butlerTask/updateSendcarStatus",method = RequestMethod.POST)
    public Object updateSendcarStatus(@RequestBody ButlerTask butlerTask){
		
		ParamVerifyUtils.paramNotNull(butlerTask);
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "客服操作，任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getStatus(), "客服操作，任务状态不能为空");
		ButlerTask old = butlerTaskService.getButlerTaskById(butlerTask.getId());
		if(old.getStatus() == TaskStatus.TASKSTATUS_CANCEL.getIndex().intValue()){
			throw new RuntimeExceptionWarn("该任务已被取消");
		}
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
//		TASKSTATUS_WAITACCEPT(2,"待接单"),
//		TASKSTATUS_WAITSENDCAR(3,"待送车"),
//		TASKSTATUS_SENDCAR(4,"送车中"),
//		TASKSTATUS_ARRIVE(7,"已到达"),//新需求，去除该状态
//		TASKSTATUS_COMPLETE(8,"已完成"),
//		TASKSTATUS_CANCEL(9,"已取消");
		checkPlanTime(butlerTask, old);
		if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_WAITACCEPT.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByWaiting(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_WAITSENDCAR.getIndex().intValue()){
			return butlerTaskService.updateSendButlerTaskBySendCar(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_SENDCAR.getIndex().intValue()){
			return butlerTaskService.updateSendButlerTaskBySendIn(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_ARRIVE.getIndex().intValue()){
			return butlerTaskService.updateSendButlerTaskByArrive(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_COMPLETE.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByCompleted(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_CANCEL.getIndex().intValue()){
			return butlerTaskService.updateButlerTaskByCancelled(butlerTask);
		}else{
			throw new RuntimeExceptionWarn("状态不正确，请重新操作");
		}
    }
	
	/**
	 * 
	 * @Title: updateButlerTaskgetstatus 
	 * @Description: 客服操作取车任务
	 * @author: wangchuntao 
	 * @param butlerTask
	 * @return 
	 * @return Object
	 * @date: 2017年2月22日 下午3:06:39
	 */
	@API("客服操作取车任务")
    @RequestMapping(value = "/butlerTask/updateGetcarStatus",method = RequestMethod.POST)
    public Object updateGetcarStatus(@RequestBody ButlerTask butlerTask){
		
		ParamVerifyUtils.paramNotNull(butlerTask);
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "客服操作，任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getStatus(), "客服操作，任务状态不能为空");
		ButlerTask old = butlerTaskService.getButlerTaskById(butlerTask.getId());
		if(old.getStatus() == TaskStatus.TASKSTATUS_CANCEL.getIndex().intValue()){
			throw new RuntimeExceptionWarn("该任务已被取消");
		}
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
//		TASKSTATUS_WAITACCEPT(2,"待接单"),
//		TASKSTATUS_WAITSENDCAR(3,"待送车"),
//		TASKSTATUS_SENDCAR(4,"送车中"),
//		TASKSTATUS_WAITTASKCAR(5,"待取车"),
//		TASKSTATUS_TASKCAR(6,"取车中"),
//		TASKSTATUS_ARRIVE(7,"已到达"),
//		TASKSTATUS_STORAGE(8,"待入库"),
//		TASKSTATUS_COMPLETE(9,"已完成"),
//		TASKSTATUS_CANCEL(10,"已取消");
		checkPlanGetTime(butlerTask, old);
		if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_WAITACCEPT.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByWaiting(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_WAITSENDCAR.getIndex().intValue()){
			return butlerTaskService.updateSendButlerTaskBySendCar(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_SENDCAR.getIndex().intValue()){
			return butlerTaskService.updateSendButlerTaskBySendIn(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_WAITTASKCAR.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByTakeCar(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_TASKCAR.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByGetIn(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_ARRIVE.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByArrive(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_STORAGE.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByStorage(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_COMPLETE.getIndex().intValue()){
			return butlerTaskService.updateGetButlerTaskByCompleted(butlerTask);
		}else if(butlerTask.getStatus() == TaskStatus.TASKSTATUS_CANCEL.getIndex().intValue()){
			return butlerTaskService.updateButlerTaskByCancelled(butlerTask);
		}else{
			throw new RuntimeExceptionWarn("状态不正确，请重新操作");
		}
    }
	
	/** 
	 * @Title: createButerTaskAttachment 
	 * @Description: 添加附件
	 * @author: wangchuntao 
	 * @return Object
	 * @date: 2017年2月22日 下午3:42:08
	 */
	@API(value="添加附件")
	@RequestMapping(value = "/butlerTask/createButerTaskAttachment",method = RequestMethod.POST)
    public Object createButerTaskAttachment(TransferList transferList){
    	return butlerTaskService.createButerTaskTransfer(transferList);
    }
	
	/** 
	 * @Title: deleteButerTaskAttachment 
	 * @Description: 删除附件
	 * @author: wangchuntao 
	 * @return Object
	 * @date: 2017年2月22日 下午3:42:29
	 */
	@API(value="删除附件")
	@RequestMapping(value = "/butlerTask/deleteButerTaskAttachment",method = RequestMethod.GET)
    public Object deleteButerTaskAttachment(Long id){
		return butlerTaskService.deleteButerTaskTransfer(id);
    }
	
	/**
	* @Title: checkPlanTime 
	* @Description: TODO(判断时间) 
	* @param @param butlerTask
	* @param @param old    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void checkPlanTime(ButlerTask butlerTask,ButlerTask old){
		ButlerTask butlerTaskSearch = new ButlerTask();
    	butlerTaskSearch.setCarUsedId(old.getCarUsedId());
    	List<ButlerTask> list = butlerTaskService.getButlerTaskList(butlerTaskSearch);
    	if(!CollectionUtils.isEmpty(list)){
    		for (ButlerTask butlerTaskView : list) {
    			if(butlerTaskView.getType() == ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex().intValue()){
    				if(null != butlerTask.getPlanTime() && butlerTask.getPlanTime().after(butlerTaskView.getPlanTime())){
    					throw new RuntimeExceptionWarn("送车时间不能在取车时间之后");
    				}
    				
    			}
    		}
    	}
    	
	}
	
	private void checkPlanGetTime(ButlerTask butlerTask,ButlerTask old){
		ButlerTask butlerTaskSearch = new ButlerTask();
    	butlerTaskSearch.setCarUsedId(old.getCarUsedId());
    	List<ButlerTask> list = butlerTaskService.getButlerTaskList(butlerTaskSearch);
    	if(!CollectionUtils.isEmpty(list)){
    		for (ButlerTask butlerTaskView : list) {
    			if(butlerTaskView.getType() == ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex().intValue()){
    				if(null != butlerTask.getPlanTime() && butlerTaskView.getPlanTime().after(butlerTask.getPlanTime())){
    					throw new RuntimeExceptionWarn("取车时间不能在送车时间之前");
    				}
    				
    			}
    		}
    	}
    	
	}
}
