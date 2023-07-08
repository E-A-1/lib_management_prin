package dbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.IIOException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

@SuppressWarnings("unused")

public class ConnectionHolder {
	private static ConnectionHolder conhol=null;
	private DataSource ds=null;
	
	public void inDataSource() throws DBCException
	{
		try {
		BasicDataSource bds=new BasicDataSource();
		Properties ps =new Properties();
		ps.load(new FileInputStream ("jdbc.properties"));
		String user=ps.getProperty("uid");
		String pwd=ps.getProperty("pwd");
		String url=ps.getProperty("url");
		String driverclazz=ps.getProperty("Driver");
		
		bds.setUsername(user);
		bds.setPassword(pwd);
		bds.setUrl(url);
		bds.setDriverClassName(driverclazz);
		this.ds=bds;
		} 
		catch(FileNotFoundException fe)		
		{
			throw new DBCException("File not found"+fe);
		}
		catch(IOException ioe)		
		{
			throw new DBCException("unable to connect with the DataBase"+ioe);
		}
	
	}
	public static ConnectionHolder getInstance() throws DBCException
	{
		synchronized(ConnectionHolder.class)
		{
			if(conhol==null)
			{
				conhol=new ConnectionHolder();
				conhol.inDataSource();
			}
		}
		return conhol;
	}
	public Connection getConnection() throws DBCException
	{
		try 
		{
			return ds.getConnection();
		}
		catch(SQLException sqle)
		{
			throw new DBCException("unable to connect to the database "+sqle);
		}
	}
	public void closeDB() throws DBCException
	{
		BasicDataSource bds=(BasicDataSource)ds;
		try
		{
			bds.close();
		}
		catch(SQLException sqle)
		{
			throw new DBCException("unable to close database connection"+sqle);
		}
	}

}

