package cn.cuco.service.basic.carport;


import java.util.List;

import cn.cuco.entity.CarBrand;
import cn.cuco.entity.CarType;
import cn.cuco.entity.CarTypePrice;
import cn.cuco.page.PageResult;

/**
* @ClassName: CarTypeService 
* @Description: 运营车型接口
* @author huanghua
* @date 2017年2月21日 下午1:49:38
 */
public interface CarTypeService {
	
    /**
    * @Title: createCarType 
    * @Description: 创建运营车型
    * @param carType
    * @return CarType
     */
    public CarType createCarType(CarType carType);

    /**
    * @Title: updateByPrimaryKeyCarType 
    * @Description: 修改运营车型信息
    * @param carType
    * @return CarType
     */
    public CarType updateCarType(CarType carType);

    /**
    * @Title: getCarTypeById 
    * @Description: 取运营车型信息
    * @author huanghua
    * @param id
    * @return CarType
     */
    public CarType getCarTypeById(Long id);

    /**
    * @Title: getCarTypeByCondition 
    * @Description: 分页运营车型
    * @param carType
    * @return List<CarType>
     */
    public PageResult<CarType> getCarTypeByPage(CarType carType);
    
    /**
    * @Title: getCarTypeListByUp 
    * @Description: 上架车型
    * @author huanghua
    * @return List<CarType>
     */
    public List<CarType> getCarTypeListByUp();
    
    /**
    * @Title: getCarTypeByUpAsc 
    * @Description: 车型价格-时间排序
    * @author huanghua
    * @param carType
    * @return
    * @return PageResult<CarType>
     */
     public PageResult<CarType> getCarTypeByUpAsc(CarType carType);
     
    /**
    * @Title: updateCarTypeShelves 
    * @Description: 运营车型上架
    * @param carType
    * @return CarType
     */
    public void updateCarTypeShelves(CarType carType);

    /**
    * @Title: updateCarTypeTheShelves 
    * @Description: 运营车型下架
    * @param carType
    * @return CarType
     */
    public void updateCarTypeTheShelves(CarType carType);
    
    /**
    * @Title: getCarTypePriceList 
    * @Description: 根据车型和城市Id查询运营公里计费规则
    * @param carTypePrice
    * @return List<CarTypePrice>
     */
    public List<CarTypePrice> getCarTypePriceList(CarTypePrice carTypePrice);
    
    /**
    * @Title: getAllCarTypeCityAll 
    * @Description: 取所有车型设置服务城市
    * @return List<CarTypePrice>
     */
    public List<CarTypePrice> getAllCarTypeCityAll();
    
    /**
     * @Title: selectCarBrandForAll 
     * @Description: 新建车型去重查询所有品牌
     * @return List<String>
      */
 	public List<String> getCarBrandForAll();

 	/**
 	* @Title: selectCartypeByBrand 
 	* @Description: 新建车型根据车品牌查询所有车型
 	* @param @param carType
 	* @return List<CarType>
 	 */
 	public List<CarBrand> getCartypeByBrand(CarBrand carBrand);
 	
    /**
     * @Title: getCarBrandForCreateCar 
     * @Description: 新建车辆去重查询所有品牌
     * @return List<String>
      */
 	public List<String> getCarBrandForCreateCar();

 	/**
 	* @Title: getCartypeByBrandForCreateCar 
 	* @Description: 新建车辆根据车品牌查询所有车型
 	* @author huanghua
 	* @param carType
 	* @return
 	* @return List<CarType>
 	 */
 	public List<CarType> getCartypeByBrandForCreateCar(CarType carType);
}
