package backEnd.Model;

import java.sql.SQLException;

public interface DataBase {

	public void connect() throws Exception;
	public void disconnect() throws Exception; 
	public void save () throws SQLException;
	public void load () throws SQLException;
	public void configure (int port, String username, String password) throws Exception;
}
