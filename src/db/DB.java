package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	private static Connection conn = null;
	
	//conecta com o banco de dados
	private static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				
				//pego do loadproperties() que esta em props -> que vem do db.porperties -> o link do dburl
				//e colooc em url que é uma String
	 			String url = props.getProperty("dburl");
	 			
	 			conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	return conn;
	}
	
	
	//ler o arquivo db.properties
	private static Properties loadProperties(){
		try(FileInputStream fs = new FileInputStream("db.properties")){
			//instancio o props
			Properties props = new Properties();
			
			//guarto o arquivo lido dentro do props
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	public static void closeStatment(Statement st) {
		if(st != null) {
			try {
				st.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	public static void colseResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
				// TODO: handle exception
			}
		}
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		
	}
	
	
	
}
