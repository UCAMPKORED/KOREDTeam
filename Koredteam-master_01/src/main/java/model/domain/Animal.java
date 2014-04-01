package model.domain;

public class Animal {
	private int aNo;
	private String aName;
	private String aType;
	private String aGrade;
	private String aDesc;
	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Animal(int aNo, String aName, String aType, String aGrade,
			String aDesc) {
		super();
		this.aNo = aNo;
		this.aName = aName;
		this.aType = aType;
		this.aGrade = aGrade;
		this.aDesc = aDesc;
	}
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getaType() {
		return aType;
	}
	public void setaType(String aType) {
		this.aType = aType;
	}
	public String getaGrade() {
		return aGrade;
	}
	public void setaGrade(String aGrade) {
		this.aGrade = aGrade;
	}
	public String getaDesc() {
		return aDesc;
	}
	public void setaDesc(String aDesc) {
		this.aDesc = aDesc;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Animal [aNo=");
		builder.append(aNo);
		builder.append(", aName=");
		builder.append(aName);
		builder.append(", aType=");
		builder.append(aType);
		builder.append(", aGrade=");
		builder.append(aGrade);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
