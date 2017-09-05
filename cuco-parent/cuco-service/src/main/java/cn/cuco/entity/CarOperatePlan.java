package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-24 17:09:38
 */
public class CarOperatePlan implements Serializable {

    private static final long serialVersionUID = 4895904040717028L;

    private Long id;//车辆运营计划ID

    private Long carId;//运营车辆ID

    private String carPlateNum;//运营车辆车牌号

    private Long carportId;//车库ID

    private Long carTypeId;//运营车型ID

    private Long memberId;//用车会员ID

    private String memberName;//用车会员名称

    private String memberMobile;//会员电话

    private Integer type;//'用车类型: 0:非会员用车;1:会员用车;

    private Date startTime;//计划开始时间

    private Date endTime;//计划结束时间

    private Integer status;//运营状态（0:待执行；1:执行中；2:已完成；3:已取消；）

    private Date created;//创建时间

    private Long memberUsecarId;//用车记录主表ID

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return this.carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarPlateNum() {
        return this.carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public Long getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Long carportId) {
        this.carportId = carportId;
    }

    public Long getCarTypeId() {
        return this.carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return this.memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public Long getMemberUsecarId() {
		return memberUsecarId;
	}

	public void setMemberUsecarId(Long memberUsecarId) {
		this.memberUsecarId = memberUsecarId;
	}

}