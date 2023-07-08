package dbfw;

@SuppressWarnings("serial")
public class DBException extends Exception {
	public DBException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}
	public DBException(String arg0)
	{
		super(arg0);
	}
}
 