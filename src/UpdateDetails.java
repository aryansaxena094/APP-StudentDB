import java.sql.Connection;
import java.sql.Statement;

public class UpdateDetails {
	public static void update(Connection connection, String tablename, StudentApp updateObject) {

		int Student_ID = Integer.parseInt(updateObject.getStudentID());
		int marks = Integer.parseInt(updateObject.getMarks());
		Statement statement;
		try {
			String query = String.format("UPDATE %s SET MARKS = %s WHERE studentid = %s", tablename, marks, Student_ID);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Marks Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean compareObject(StudentApp object1, StudentApp object2) {
		if (object1.getStudentID().trim().equals(object2.getStudentID().trim())
				&& object1.getName().trim().equals(object2.getName().trim())
				&& object1.getMarks().trim().equals(object2.getMarks().trim())) {
			return true;
		} else {
			return false;
		}

	}
}
