package domain;

public class Admin
{
	private int a_userid;
	private int a_rollid;
	private String a_email;
	private String a_mobile;
	private String a_password;
	public Admin(int a_userid, int a_rollid, String a_email, String a_mobile, String a_password)
	{
		super();
		this.a_userid = a_userid;
		this.a_rollid = a_rollid;
		this.a_email = a_email;
		this.a_mobile = a_mobile;
		this.a_password = a_password;
	}
	public int getA_userid() {
		return a_userid;
	}
	public void setA_userid(int a_userid) {
		this.a_userid = a_userid;
	}
	public int getA_rollid() {
		return a_rollid;
	}
	public void setA_rollid(int a_rollid) {
		this.a_rollid = a_rollid;
	}
	public String getA_email() {
		return a_email;
	}
	public void setA_email(String a_email) {
		this.a_email = a_email;
	}
	public String getA_mobile() {
		return a_mobile;
	}
	public void setA_mobile(String a_mobile) {
		this.a_mobile = a_mobile;
	}
	public String getA_password() {
		return a_password;
	}
	public void setA_password(String a_password) {
		this.a_password = a_password;
	}
	@Override
	public String toString() {
		return "Admin [a_userid=" + a_userid + ", a_rollid=" + a_rollid + ", a_email=" + a_email + ", a_mobile="
				+ a_mobile + ", a_password=" + a_password + "]";
	}
	
	

}
