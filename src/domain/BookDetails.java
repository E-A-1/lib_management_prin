package domain;

public class BookDetails {

	private int bookId;
	private String bookName;
	private int isbn;
	private String author;
	private String publisher;
	private String edition;
	private int price;
	private int quantity;
	private String categoryName;

	public int getBookId() {
		return bookId;
	}

	public BookDetails(int bookId, String bookName, int isbn, String author, String publisher, String edition,
			int price, int quantity, String categoryName) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.price = price;
		this.quantity = quantity;
		this.categoryName = categoryName;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
