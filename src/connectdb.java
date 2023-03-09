import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class connectdb {
	private final String url = "jdbc:postgresql://localhost/postgres";
	private final String user = "postgres";
	private final String password = "dontlogin";
	private String table = "";
	private int ID = 0;

	public Connection connect() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			if (connection != null) {
				System.out.println("Connection Established");
			} else {
				System.out.println("Failed to Connect");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void createtable(Connection connection, String tablename) {
		Statement statement;
		try {
			tablename.toUpperCase();
			String query = "CREATE TABLE IF NOT EXISTS " + tablename
					+ "(StudentID int primary key, Name varchar(30), Marks int);";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			table = tablename;
			System.out.println("Table " + table + " Created");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insertintotables(Connection connection, String tablename, ArrayList<String[]> v) {
		Statement statement;

		try {
			String deletequeryString = "DELETE FROM " + table + " ;";
			statement = connection.createStatement();
			statement.execute(deletequeryString);

			for (int i = 0; i < v.size(); i++) {
				String values[] = v.get(i);
				ID++;
				String query = "INSERT INTO " + table + " VALUES (" + values[0] + ",\'" + values[1] + "\' , "
						+ values[2] + ");";
				statement = connection.createStatement();
				statement.execute(query);
			}
			System.out.println(ID + " Rows Inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
