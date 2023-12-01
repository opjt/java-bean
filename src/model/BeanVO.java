package model;

public class BeanVO {
	private int b_no; //원두 일련번호
	private String name; //원두이름
	private String country; //원산지
	private String area; //재배지
	private String farm; //농장
	private String farmer; //농부
	private int process; //해발고도
	private String roasting; //볶음도
	private String flavor; //향미
	private int volume; //용량(수량)
	private String makeDate; //제조일
	private int price; //판매가
	private int cost; //원가
	private int state; //상태 1:표시 0:표시안함
	public BeanVO() {
		super();
	}
	public BeanVO(int b_no, String name, String country, String area, String farm, String farmer, int process,
			String roasting, String flavor, int volume, String makeDate, int price, int cost, int state) {
		super();
		this.b_no = b_no;
		this.name = name;
		this.country = country;
		this.area = area;
		this.farm = farm;
		this.farmer = farmer;
		this.process = process;
		this.roasting = roasting;
		this.flavor = flavor;
		this.volume = volume;
		this.makeDate = makeDate;
		this.price = price;
		this.cost = cost;
		this.state = state;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFarm() {
		return farm;
	}
	public void setFarm(String farm) {
		this.farm = farm;
	}
	public String getFarmer() {
		return farmer;
	}
	public void setFarmer(String farmer) {
		this.farmer = farmer;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public String getRoasting() {
		return roasting;
	}
	public void setRoasting(String roasting) {
		this.roasting = roasting;
	}
	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(String makeDate) {
		this.makeDate = makeDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
