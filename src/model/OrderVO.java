package model;

public class OrderVO {
	private int o_no; // 발주번호
	private int c_no; // 카페번호
	private int b_no; // 원두번호
	private int volume; // 용량(수량)
	private String o_date; // 발주날짜
	private int price; // 가겨
	private String address; // 배송지
	private int state; // 상태 1표시 0표시안함

	public OrderVO() {
		super();
	}

	public OrderVO(int o_no, int c_no, int b_no, int volume, String o_date, int price, String address, int state) {
		super();
		this.o_no = o_no;
		this.c_no = c_no;
		this.b_no = b_no;
		this.volume = volume;
		this.o_date = o_date;
		this.price = price;
		this.address = address;
		this.state = state;
	}

	public int getO_no() {
		return o_no;
	}

	public void setO_no(int o_no) {
		this.o_no = o_no;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getO_date() {
		return o_date;
	}

	public void setO_date(String o_date) {
		this.o_date = o_date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
