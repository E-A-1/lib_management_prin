package dbc;

//@SuppressWarnings("serial")
public class DBCException extends Exception{
	public DBCException(String mesg, Throwable cause) {

		super(mesg, cause);
	}

	public DBCException(String mesg) {
		super(mesg);
	}
}
