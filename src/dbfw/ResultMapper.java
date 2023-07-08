package dbfw;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

@SuppressWarnings("unused")
public interface ResultMapper 
{
	public Object mapRow(ResultSet rs) throws SQLException;
}
