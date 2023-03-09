import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

public class StudentApp implements ActionListener {
	private JFrame frame;
	private JTextField Field1;
	private JTextField Field2;
	public String StudentID;
	public String Name;
	public String Marks;
	static connectdb postgres = new connectdb();
	static Connection connnection = postgres.connect();
	StudentApp[] studentApp = new StudentApp[7];

	public static void main(String[] args) {
		postgres.createtable(connnection, "Students");
		ArrayList<String[]> values = new ArrayList<String[]>();
		values.add(new String[] { "949", "Aryan Saxena", "99" });
		values.add(new String[] { "284", "Rohan Dhiman", "74" });
		values.add(new String[] { "463", "Kairavi", "85" });
		values.add(new String[] { "234", "Nilay Kothari", "98" });
		values.add(new String[] { "346", "Swapnonil", "83" });
		values.add(new String[] { "623", "Asmita Upadhyay", "97" });
		values.add(new String[] { "723", "Arushi Mall", "73" });
		postgres.insertintotables(connnection, "Students", values);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentApp window = new StudentApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentApp() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 703, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JButton updatebutton = new JButton("Update");
		updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Student_ID = Field1.getText();
				String NewMarks = Field2.getText();
				StudentApp[] stapp_compare = new StudentApp[1];
				int objectindex = 0;
				for (int i = 0; i < studentApp.length; i++) {
					if (studentApp[i].getStudentID().equals(Student_ID)) {
						stapp_compare[0] = new StudentApp(studentApp[i].getStudentID(), studentApp[i].getName(),
								NewMarks);
						objectindex = i;
						break;
					}
				}

				Boolean checkobjectmodify = UpdateDetails.compareObject(studentApp[objectindex], stapp_compare[0]);

				if (checkobjectmodify == false) {
					JOptionPane.showMessageDialog(null, "Values have been updated. Please Re-Display all records");
					studentApp[objectindex].setMarks(NewMarks);
					UpdateDetails.update(connnection, "Students", studentApp[objectindex]);
				} else {
					JOptionPane.showMessageDialog(null, "Values are the same.");
				}
			}
		});

		sl_panel.putConstraint(SpringLayout.WEST, updatebutton, 66, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, updatebutton, -379, SpringLayout.EAST, panel);
		panel.add(updatebutton);

		JButton Quit = new JButton("Quit");
		sl_panel.putConstraint(SpringLayout.WEST, Quit, 66, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, Quit, -379, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, updatebutton, -8, SpringLayout.NORTH, Quit);
		sl_panel.putConstraint(SpringLayout.SOUTH, Quit, -61, SpringLayout.SOUTH, panel);
		Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent f) {
				Quit();
			}
		});
		panel.add(Quit);

		Field1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.EAST, Field1, -325, SpringLayout.EAST, panel);
		panel.add(Field1);
		Field1.setColumns(10);

		Field2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, Field2, 35, SpringLayout.SOUTH, Field1);
		sl_panel.putConstraint(SpringLayout.WEST, Field2, 0, SpringLayout.WEST, Field1);
		sl_panel.putConstraint(SpringLayout.EAST, Field2, -325, SpringLayout.EAST, panel);
		panel.add(Field2);
		Field2.setColumns(10);

		JLabel lblNewLabel = new JLabel("Enter the Sudents ID:");
		sl_panel.putConstraint(SpringLayout.WEST, Field1, 0, SpringLayout.EAST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 37, SpringLayout.WEST, panel);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Update Marks to:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 45, SpringLayout.SOUTH, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 37, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 5, SpringLayout.SOUTH, lblNewLabel_2);
		sl_panel.putConstraint(SpringLayout.NORTH, Field1, 0, SpringLayout.NORTH, lblNewLabel_2);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 160, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_2, 66, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_2, -66, SpringLayout.EAST, panel);
		panel.add(lblNewLabel_2);

		JTextPane textPane = new JTextPane();
		sl_panel.putConstraint(SpringLayout.NORTH, textPane, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textPane, 16, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textPane, -124, SpringLayout.EAST, panel);
		panel.add(textPane);

		JList<String> list = new JList<String>();
		sl_panel.putConstraint(SpringLayout.NORTH, list, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, list, 0, SpringLayout.EAST, Field1);
		sl_panel.putConstraint(SpringLayout.SOUTH, list, 294, SpringLayout.SOUTH, lblNewLabel_2);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		list.setForeground(Color.BLACK);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBackground(Color.WHITE);
		panel.add(list);

		JButton FetchRecords = new JButton("Display All Records");
		sl_panel.putConstraint(SpringLayout.WEST, FetchRecords, 66, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, FetchRecords, -129, SpringLayout.WEST, list);
		FetchRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FetchStudents display = new FetchStudents();
				ArrayList<String> details = display.fetchdata(connnection, "Students");
				DefaultListModel<String> listModel = new DefaultListModel<String>();
				for (int i = 0; i < details.size(); i++) {
					listModel.addElement(details.get(i));
				}
				list.setModel(listModel);
				String[] forObjectStrings = new String[3];
				for (int i = 0; i < 7; i++) {
					forObjectStrings[0] = "";
					forObjectStrings[1] = "";
					forObjectStrings[2] = "";
					forObjectStrings = details.get(i).split(",");
					studentApp[i] = new StudentApp(forObjectStrings[0], forObjectStrings[1], forObjectStrings[2]);
				}
				JOptionPane.showMessageDialog(null, "Data has been updated");
			}
		});

		sl_panel.putConstraint(SpringLayout.NORTH, FetchRecords, 55, SpringLayout.NORTH, panel);
		panel.add(FetchRecords);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public StudentApp(String StudentID, String Name, String Marks) {
		this.StudentID = StudentID;
		this.Name = Name;
		this.Marks = Marks;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getStudentID() {
		return StudentID;
	}

	public void setStudentID(String StudentID) {
		this.StudentID = StudentID;
	}

	public String getMarks() {
		return Marks;
	}

	public void setMarks(String Marks) {
		this.Marks = Marks;
	}

	public void Quit() {
		System.exit(0);
	}
}