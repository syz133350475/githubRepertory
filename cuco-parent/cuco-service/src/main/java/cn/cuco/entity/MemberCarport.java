package cn.cuco.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-27 11:51:26
 */
public class MemberCarport extends PageQuery<MemberCarport> implements Serializable {

    private static final long serialVersionUID = 8066645932522348L;

    private Long id;//主键

    private Long memberId;//用户ID

    private Long carportId;//车库ID
    
    private String carportName;//车库名称

    private Long cartypeId;//用户喜欢的车型ID

    private Integer status;//车库状态: 0:未解锁;1:解锁中;2:已解锁;3:已停用 默认0

    private BigDecimal mileage;//行驶里程

    private Integer count;//使用次数

    private Integer valid;//数据状态: 0:无效;1:有效;默认1

    private Date created;//创建时间

    private Date buyTime;//完成解锁时间

    private Date lasttimeUsed;//最后用车时间

    private String modifer;//修改人

    private Long modifierId;//修改人ID
    
    private CarType favoriteCarType;//用户喜爱的车型
    
    public MemberCarport(){}
    
    public MemberCarport(Long memberId, Long carportId, Long cartypeId, Integer status, BigDecimal mileage, Integer count, Integer valid, Date created, Date buyTime, Date lasttimeUsed, String modifer, Long modifierId) {
		super();
		this.memberId = memberId;
		this.carportId = carportId;
		this.cartypeId = cartypeId;
		this.status = status;
		this.mileage = mileage;
		this.count = count;
		this.valid = valid;
		this.created = created;
		this.buyTime = buyTime;
		this.lasttimeUsed = lasttimeUsed;
		this.modifer = modifer;
		this.modifierId = modifierId;
	}



	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Long carportId) {
        this.carportId = carportId;
    }

    public Long getCartypeId() {
        return this.cartypeId;
    }

    public void setCartypeId(Long cartypeId) {
        this.cartypeId = cartypeId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMileage() {
        return this.mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getBuyTime() {
        return this.buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getLasttimeUsed() {
        return this.lasttimeUsed;
    }

    public void setLasttimeUsed(Date lasttimeUsed) {
        this.lasttimeUsed = lasttimeUsed;
    }

    public String getModifer() {
        return this.modifer;
    }

    public void setModifer(String modifer) {
        this.modifer = modifer;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

	public CarType getFavoriteCarType() {
		return favoriteCarType;
	}

	public void setFavoriteCarType(CarType favoriteCarType) {
		this.favoriteCarType = favoriteCarType;
	}

	public String getCarportName() {
		return carportName;
	}

	public void setCarportName(String carportName) {
		this.carportName = carportName;
	}

}