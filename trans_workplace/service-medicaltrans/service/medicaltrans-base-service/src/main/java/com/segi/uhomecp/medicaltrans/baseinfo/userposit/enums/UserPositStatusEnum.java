package com.segi.uhomecp.medicaltrans.baseinfo.userposit.enums;


public enum UserPositStatusEnum {
	
	STATUS_LEISURE("1","空闲"),
	
	STATUS_USE("2","运送中"),
	
	STATUS_LEAVE("3","离开");
	
	private String code;
	private String name;
	
	private UserPositStatusEnum(String code, String name) {
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
        for (UserPositStatusEnum status : UserPositStatusEnum.values()) {  
            if (status.getCode().equals(code)) {  
                return status.getName();
            }  
        }  
        return null;  
    }  

}
