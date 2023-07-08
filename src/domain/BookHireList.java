package domain;

public class BookHireList {
	private int hireid;
	private int b_id;
	private String b_name;
	private int b_isbn;
	private String b_author;
	private String b_publisher;
	private String b_edition;
	private double price;
	private int quantity;
	private String returndate;
	private int userid;
	public BookHireList(int hireid, int b_id, String b_name, int b_isbn, String b_author, String b_publisher,	String b_edition, double price, int quantity, String returndate,int userid) 
	{
		super();
		this.hireid = hireid;	
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_isbn = b_isbn;
		this.b_author = b_author;
		this.b_publisher = b_publisher;
		this.b_edition = b_edition;
		this.price = price;
		this.quantity = quantity;
		this.returndate = returndate;
		this.userid=userid;
	}
	public int getHireid() {
		return hireid;
	}
	public void setHireid(int hireid) {
		this.hireid = hireid;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public int getB_isbn() {
		return b_isbn;
	}
	public void setB_isbn(int b_isbn) {
		this.b_isbn = b_isbn;
	}
	public String getB_author() {
		return b_author;
	}
	public void setB_author(String b_author) {
		this.b_author = b_author;
	}
	public String getB_publisher() {
		return b_publisher;
	}
	public void setB_publisher(String b_publisher) {
		this.b_publisher = b_publisher;
	}
	public String getB_edition() {
		return b_edition;
	}
	public void setB_edition(String b_edition) {
		this.b_edition = b_edition;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "BookHireList [hireid=" + hireid + ", b_id=" + b_id + ", b_name=" + b_name + ", b_isbn=" + b_isbn
				+ ", b_author=" + b_author + ", b_publisher=" + b_publisher + ", b_edition=" + b_edition + ", price="
				+ price + ", quantity=" + quantity + ", returndate=" + returndate + ", userid=" + userid + "]";
	}
	
	
}
