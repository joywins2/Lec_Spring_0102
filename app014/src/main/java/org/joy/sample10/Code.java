package org.joy.sample10;

public class Code {
	private String code;
	private String label;

	public Code() {
	}//기본 생성자
	
	public Code(String code, String value){
		this.code = code;
		this.label = value;
	}
	//setter, getter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	
	
}
