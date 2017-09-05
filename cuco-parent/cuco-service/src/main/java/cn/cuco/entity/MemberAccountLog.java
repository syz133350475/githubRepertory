package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-28 15:32:10
 */
public class MemberAccountLog extends PageQuery<MemberAccountLog> implements Serializable {

    private static final long serialVersionUID = 4759389503574771L;

    private Long id;//主键

    private Long accountId;//账户ID

    private Long memberId;//用户ID

    private Integer accountType;//变动项：1:余额;2:押金;3:积分

    private Integer operateType;//操作类型：1:解锁车库;2:账户续费,3:系统补扣;4:系统扣费;5:系统补回

    private Integer transformType;//变动类型:0:储值;1:消费

    private BigDecimal total;//金额/积分(扣费时存负数)

    private String remark;//备注

    private Date created;//创建时间

    private String modifer;//操作人

    private Long modifierId;//操作人ID
    
    private Date startDate;//开始时间
    
    private Date endDate;//结束时间
    
    public MemberAccountLog(){}
    
    public MemberAccountLog(Long accountId, Long memberId, Integer accountType, Integer operateType, Integer transformType, BigDecimal total, String remark, Date created, String modifer, Long modifierId) {
		super();
		this.accountId = accountId;
		this.memberId = memberId;
		this.accountType = accountType;
		this.operateType = operateType;
		this.transformType = transformType;
		this.total = total;
		this.remark = remark;
		this.created = created;
		this.modifer = modifer;
		this.modifierId = modifierId;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getAccountType() {
        return this.accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getOperateType() {
        return this.operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getTransformType() {
        return this.transformType;
    }

    public void setTransformType(Integer transformType) {
        this.transformType = transformType;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}