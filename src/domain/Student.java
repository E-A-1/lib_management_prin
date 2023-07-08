package domain;

public class Student 
{
	private int s_userid;
	private int s_rollid;
	private String s_email;
	private String s_mobile;
	private String s_password;
	public Student(int s_userid, int s_rollid, String s_email, String s_mobile, String s_password) 
	{
		super();
		this.s_userid = s_userid;
		this.s_rollid = s_rollid;
		this.s_email = s_email;
		this.s_mobile = s_mobile;
		this.s_password = s_password;
	}
	public int getS_userid() {
		return s_userid;
	}
	public void setS_userid(int s_userid) {
		this.s_userid = s_userid;
	}
	public int getS_rollid() {
		return s_rollid;
	}
	public void setS_rollid(int s_rollid) {
		this.s_rollid = s_rollid;
	}
	public String getS_email() {
		return s_email;
	}
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	public String getS_mobile() {
		return s_mobile;
	}
	public void setS_mobile(String s_mobile) {
		this.s_mobile = s_mobile;
	}
	public String getS_password() {
		return s_password;
	}
	public void setS_password(String s_password) {
		this.s_password = s_password;
	}
	
}
