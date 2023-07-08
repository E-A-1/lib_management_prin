package domain;

public class Admin {
	private int adminId;
	private String adminName;
	private String password;

	public int getAdminId() {
		return adminId;
	}

	public Admin(int adminId, String adminName, String password, String email, String contactNumber) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	private String email;
	private String contactNumber;

}
