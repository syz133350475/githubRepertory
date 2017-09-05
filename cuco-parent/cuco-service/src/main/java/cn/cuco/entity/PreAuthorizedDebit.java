package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-03-07 15:55:01
 */
public class PreAuthorizedDebit extends PageQuery implements Serializable {

    private static final long serialVersionUID = 1104736860384840L;

    private Long id;//主键ID

    private Long memberId;//会员ID

    private String memberName;//用户名称

    private String memberMobile;//用户电话

    private Long carId;//车辆ID

    private String carPlateNum;//车牌号码

    private Integer type;//冻结类型 0:用车押金；1:违章押金；

    private Integer source;//金额来源: 0:预授权;1:余额

    private Integer status;//状态: 0:冻结;1:解冻;默认0

    private BigDecimal frozenAmount;//预授权金额(冻结金额)

    private Date frozenTime;//冻结时间

    private Date expectUnfreezeTime;//预计解冻时间

    private BigDecimal unfreezeAmount;//解冻金额

    private BigDecimal deductionAmount;//

    private Date actualUnfreezeTime;//实际解冻时间

    private Date created;//创建时间

    private String remark;//备注

    private Date lasttimeModify;//最后修改时间

    private Long modifierId;//操作人id

    private String modifier;//操作人姓名

    private Long carUsedId;//用车记录ID

    private List<TransferList> useVoucher;//预授权用车押金凭证集合
    
    private List<TransferList> violationVoucher;//预授权违章押金凭证集合
    
    private Date createdStart;//开始创建时间
    
    private Date createdEnd;//结束创建时间
    
    public Date getCreatedStart() {
		return createdStart;
	}

	public void setCreatedStart(Date createdStart) {
		this.createdStart = createdStart;
	}

	public Date getCreatedEnd() {
		return createdEnd;
	}

	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
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

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSource() {
        return this.source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getFrozenAmount() {
        return this.frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public Date getFrozenTime() {
        return this.frozenTime;
    }

    public void setFrozenTime(Date frozenTime) {
        this.frozenTime = frozenTime;
    }

    public Date getExpectUnfreezeTime() {
        return this.expectUnfreezeTime;
    }

    public void setExpectUnfreezeTime(Date expectUnfreezeTime) {
        this.expectUnfreezeTime = expectUnfreezeTime;
    }

    public BigDecimal getUnfreezeAmount() {
        return this.unfreezeAmount;
    }

    public void setUnfreezeAmount(BigDecimal unfreezeAmount) {
        this.unfreezeAmount = unfreezeAmount;
    }

    public BigDecimal getDeductionAmount() {
        return this.deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public Date getActualUnfreezeTime() {
        return this.actualUnfreezeTime;
    }

    public void setActualUnfreezeTime(Date actualUnfreezeTime) {
        this.actualUnfreezeTime = actualUnfreezeTime;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
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

    public Long getCarUsedId() {
        return this.carUsedId;
    }

    public void setCarUsedId(Long carUsedId) {
        this.carUsedId = carUsedId;
    }

	public List<TransferList> getUseVoucher() {
		return useVoucher;
	}

	public void setUseVoucher(List<TransferList> useVoucher) {
		this.useVoucher = useVoucher;
	}

	public List<TransferList> getViolationVoucher() {
		return violationVoucher;
	}

	public void setViolationVoucher(List<TransferList> violationVoucher) {
		this.violationVoucher = violationVoucher;
	}
	
}