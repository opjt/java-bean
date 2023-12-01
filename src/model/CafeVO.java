package model;

public class CafeVO {
	private int c_no; //카페번호
	private String name; //카페이름
	private String address; //카페주소
	private String tel; //카페연락처
	private String license; //사업자등록증
	private int state; //상태 1표시0표시안함
	public CafeVO() {
		super();
	}
	public CafeVO(int c_no, String name, String address, String tel, String license, int state) {
		super();
		this.c_no = c_no;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.license = license;
		this.state = state;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
