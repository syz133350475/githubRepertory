package cn.cuco.enums;

/**
* @ClassName: TransferType 
* @Description: 任务类型枚举
* @author huanghua
* @date 2017年2月28日 上午9:37:31
 */
public enum TransferType {
	//'附件类型 0:交接单照片  1:车辆照片 2:预授权押金凭证；3：预授权违章押金凭证',
	TRANSDERTYPE_RECEIPT(0,"交接单照片"),
	TRANSDERTYPE_CAR(1,"车辆照片"),
	AUTHORIZED_USE_VOUCHER(2,"预授权用车押金凭证"),
	AUTHORIZED_VIOLATION_VOUCHER(3,"预授权违章押金凭证");


	private Integer index;
	
	private String name;

	private TransferType(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(TransferType status: TransferType.values()){
			if(status.getIndex().intValue() == targetIndex.intValue()){
				return status.getName();
			}
		}
		return "";
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
