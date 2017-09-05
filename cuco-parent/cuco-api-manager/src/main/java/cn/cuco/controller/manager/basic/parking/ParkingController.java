package cn.cuco.controller.manager.basic.parking;


import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.cuco.annotation.API;
import cn.cuco.common.httpservice.HttpServiceContext;
import cn.cuco.common.utils.PrePersistUtils;
import cn.cuco.entity.Parking;
import cn.cuco.httpservice.ResponseUtil;
import cn.cuco.service.basic.business.ParkingService;


/**
 * @ClassName: ParkingController 
 * @Description: 停车场
 * @author: wangchuntao 
 * @date: 2017年2月23日 上午10:49:52
 */
@RestController
public class ParkingController {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ParkingService parkingService;
    /**
     * @Title: getParkingListByPage 
     * @Description: 停车场列表
     * @author: wangchuntao 
     * @param parking
     * @return Object
     * @date: 2017年2月23日 上午10:51:09
     */
	@API(value="停车场列表")
	@RequestMapping(value = "/parking/getParkingList",method = RequestMethod.GET)
    public Object getParkingListByPage(String parkingName){
		Parking parking = new Parking();
		parking.setParkingName(parkingName);
        return parkingService.getParkingByPage(parking);
    }
	/**
	 * @Title: updateParking 
	 * @Description: 修改停车场
	 * @author: wangchuntao 
	 * @param parking
	 * @return 
	 * @return Object
	 * @date: 2017年2月23日 上午10:52:18
	 */
	@API(value="删除停车场")
	@RequestMapping(value = "/parking/deleteParking",method = RequestMethod.GET)
	public Object deleteParking(Long id){
		Parking parking = new Parking();
		parking.setId(id);
		PrePersistUtils.onModify(parking, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		parkingService.deleteParkingById(parking);
		return ResponseUtil.toSuccessBody(null);
	}
	/**
	 * @Title: createParking 
	 * @Description: 新建停车场
	 * @author: wangchuntao 
	 * @param parking
	 * @return Object
	 * @date: 2017年2月23日 上午10:52:43
	 */
	@API(value="新建停车场")
	@RequestMapping(value = "/parking/createParking",method = RequestMethod.POST)
	public Object createParking(@RequestBody Parking parking){
		PrePersistUtils.onCreate(parking, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		return parkingService.createParking(parking);
	}

}