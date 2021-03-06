package cn.cuco.controller.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-22 14:30:41
 */
public class MemberAccountLogVO extends PageQuery<MemberAccountLogVO> implements Serializable {

    private static final long serialVersionUID = 2273801502768214L;

    private Long id;//主键

    private Long accountId;//账户ID

    private Long memberId;//用户ID

    private Integer accountType;//变动项：1:余额;2:押金;3:积分

    private Integer transformType;//变动类型：1:客户续费;2:后台人工续费,3:后台人工扣费;4:用车扣费;5:解锁订单;6:用车加急;7:燃油费; 8:取消用车违约费; 9:紧急救援费用;10:替换启用车型费;11:代充电费用;12一次性故障换车费用;13.提现;14.提现押金;15:赠送;16:退款

    private BigDecimal total;//金额

    private String reason;//变动事由

    private Integer status;//资金状态: 0:冻结;1:正常;默认1

    private Integer valid;//数据状态: 0:无效;1:有效;默认1

    private String remark;//备注

    private Date created;//创建时间

    private String modifer;//操作人

    private Long modifierId;//操作人ID
    
    private Integer type;//1储值2消费 
    
    private Date createdBegin;//查询开始时间
    
    private Date createdEnd;//查询结束时间
    
    
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

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreatedBegin() {
		return createdBegin;
	}

	public void setCreatedBegin(Date createdBegin) {
		this.createdBegin = createdBegin;
	}

	public Date getCreatedEnd() {
		return createdEnd;
	}

	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}

}
