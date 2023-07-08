package domain;

public class BookReqList {
	
	private int book_id;
	private String date;
	private int userid;
	public BookReqList( int book_id, String date, int userid) {
		super();
	
		this.book_id = book_id;
		this.date = date;
		this.userid = userid;
	
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "BookReqList [book_id=" + book_id + ", date=" + date + ", userid=" + userid + "]";
	}
	
	
}
