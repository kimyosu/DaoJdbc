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

    //Iniicia uma conexao com o banco de dados
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties(); //Carrega o arquivo de propriedades
				String url = props.getProperty("dburl"); //Pega a URL do banco de dados
				conn = DriverManager.getConnection(url, props); //Cria a conexao com o banco de dados usando a URL e as propriedades
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() { //Fecha a conexao com o banco de dados
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) { //Tenta ler o arquivo de propriedades
			Properties props = new Properties();
			props.load(fs); //Carrega as propriedades do arquivo
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close(); //Fecha o Statement
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close(); //Fecha o ResultSet
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
