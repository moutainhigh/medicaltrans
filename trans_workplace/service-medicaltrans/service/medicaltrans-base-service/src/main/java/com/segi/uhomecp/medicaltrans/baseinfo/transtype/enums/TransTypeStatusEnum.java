package com.segi.uhomecp.medicaltrans.baseinfo.transtype.enums;


public enum TransTypeStatusEnum {
	
	STATUS_DEL("0","已删除"), 
	
	STATUS_NORMAL("1","启用"),
	
	STATUS_STOP("2","停用");
	
	private String code;
	private String name;
	
	private TransTypeStatusEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}
    
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getName(String code) {  
        for (TransTypeStatusEnum status : TransTypeStatusEnum.values()) {  
            if (status.getCode().equals(code)) {  
                return status.getName();
            }  
        }  
        return null;  
    }  

}
