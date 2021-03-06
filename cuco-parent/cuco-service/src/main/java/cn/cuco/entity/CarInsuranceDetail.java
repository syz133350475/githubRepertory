package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-24 11:42:24
 */
public class CarInsuranceDetail implements Serializable {

    private static final long serialVersionUID = 1440244009588723L;

    private Long id;//车辆保险明细表ID

    private Long carId;//车辆ID

    private String compulsoryInsuranceNum;//车辆所投保的交强险保单号

    private Date compulsoryInsuranceStart;//交强险开始时间

    private Date compulsoryInsuranceEnd;//交强险结束时间

    private String businessInsuranceNum;//车辆所投保的商业险保单号

    private Date businessInsuranceStart;//车辆所投保的商业险保单开始时间

    private Date businessInsuranceEnd;//车辆所投保的商业险保单结束时间

    private Date created;//创建时间

    private Long modifierId;//操作人

    private String modifier;//操作人
    
    private String[] compulsoryInsuranceUrl;//强制险上传图片url
    
    private String[] businessInsuranceUrl;//商业险上传图片url
    
    private List<OperateLog> operateLog;

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

    public String getCompulsoryInsuranceNum() {
        return this.compulsoryInsuranceNum;
    }

    public void setCompulsoryInsuranceNum(String compulsoryInsuranceNum) {
        this.compulsoryInsuranceNum = compulsoryInsuranceNum;
    }

    public Date getCompulsoryInsuranceStart() {
        return this.compulsoryInsuranceStart;
    }

    public void setCompulsoryInsuranceStart(Date compulsoryInsuranceStart) {
        this.compulsoryInsuranceStart = compulsoryInsuranceStart;
    }

    public Date getCompulsoryInsuranceEnd() {
        return this.compulsoryInsuranceEnd;
    }

    public void setCompulsoryInsuranceEnd(Date compulsoryInsuranceEnd) {
        this.compulsoryInsuranceEnd = compulsoryInsuranceEnd;
    }

    public String getBusinessInsuranceNum() {
        return this.businessInsuranceNum;
    }

    public void setBusinessInsuranceNum(String businessInsuranceNum) {
        this.businessInsuranceNum = businessInsuranceNum;
    }

    public Date getBusinessInsuranceStart() {
        return this.businessInsuranceStart;
    }

    public void setBusinessInsuranceStart(Date businessInsuranceStart) {
        this.businessInsuranceStart = businessInsuranceStart;
    }

    public Date getBusinessInsuranceEnd() {
        return this.businessInsuranceEnd;
    }

    public void setBusinessInsuranceEnd(Date businessInsuranceEnd) {
        this.businessInsuranceEnd = businessInsuranceEnd;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

	public String[] getCompulsoryInsuranceUrl() {
		return compulsoryInsuranceUrl;
	}

	public void setCompulsoryInsuranceUrl(String[] compulsoryInsuranceUrl) {
		this.compulsoryInsuranceUrl = compulsoryInsuranceUrl;
	}

	public String[] getBusinessInsuranceUrl() {
		return businessInsuranceUrl;
	}

	public void setBusinessInsuranceUrl(String[] businessInsuranceUrl) {
		this.businessInsuranceUrl = businessInsuranceUrl;
	}

	public List<OperateLog> getOperateLog() {
		return operateLog;
	}

	public void setOperateLog(List<OperateLog> operateLog) {
		this.operateLog = operateLog;
	}
	
}
