package libb;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Givebook extends JFrame implements ActionListener{
	
	JComboBox Name   = new JComboBox();
	JComboBox Author = new JComboBox();
	
	JLabel LName   = new JLabel("Book");
	JLabel LAuthor = new JLabel("Member");
	
	JLabel Title   = new JLabel("Give Book");
	
	JButton add = new JButton("Give");
	
	Givebook(){
		
		
		try {
			Statement st = new connection().takeThis();
			ResultSet rs = st.executeQuery("SELECT `name`,`ID` FROM `books`");
			while(rs.next()) {
				Name.addItem  (""+rs.getString(1)+"  -  "+rs.getString(2)+"");
			}
			rs = st.executeQuery("SELECT `Name`,`MemId` FROM `members`");
			
			while(rs.next()) {
				Author.addItem(""+rs.getString(1)+"  -  "+rs.getString(2)+"");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		AutoCompleteDecorator.decorate(Name);
		AutoCompleteDecorator.decorate(Author);
		
		Font fon = new Font("Arial", Font.PLAIN, 20);

		Name   .setBounds(150, 20 +100, 300, 30);
		Author .setBounds(150, 70 +100, 300, 30);

		LName   .setBounds(50, 20 +100, 300, 30);
		LAuthor .setBounds(50, 70 +100, 300, 30);
		
		add     .setBounds(50, 250,70,40);
		Title   .setBounds(50, 20, 400, 100);
		
		Title.setFont(new Font("Arial", Font.PLAIN, 30));
		add     .addActionListener(this);
		
		
		add(Title  );
		add(add    );
		add(Name   );
		add(Author );

		add(LName   );
		add(LAuthor );
		
		setSize(500,400);
		setLocationRelativeTo(null);
		setTitle("Add Boook");
		setDefaultCloseOperation(3);
		setLayout(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Givebook();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Date  dt = new Date();
		
		SimpleDateFormat formater = new SimpleDateFormat("d/M/yyyy");
		
		String NameSt = String.valueOf(Name.getSelectedItem());
		String[] NamearrOfStr = NameSt.split("  -  ", 3);
		
		String AuthorSt = String.valueOf(Author.getSelectedItem());
		String[] AuthorarrOfStr = AuthorSt.split("  -  ", 3);
		
		
		
		try {
			
			Statement st = new connection().takeThis();
			st.executeUpdate(
			"INSERT INTO `disterbution`(`BookID`, `MemberID`, `RecivingD`, `SubmitionD`)"
			+ " VALUES ('"+NamearrOfStr[1]+"','"+AuthorarrOfStr[1]+"','"+formater.format(dt)+"','0')");
			
			
			
			
			JOptionPane.showMessageDialog(null, "Added");
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	

}
