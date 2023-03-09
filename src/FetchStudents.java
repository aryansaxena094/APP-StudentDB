import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FetchStudents {

	public ArrayList<String> fetchdata(Connection connection, String tablename) {
		Statement statement;
		ResultSet output = null;
		String a = "";
		ArrayList<String> details = new ArrayList<String>();
		try {
			String query = String.format("Select * from %s", tablename);
			statement = connection.createStatement();
			output = statement.executeQuery(query);
			while (output.next()) {
				a = "";
				a = a + output.getString("StudentID") + ",";
				a = a + " " + output.getString("Name") + ",";
				a = a + " " + output.getString("Marks");
				a = a + "";
				details.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return details;
	}

}
