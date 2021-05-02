package libb;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class Recivebook extends JFrame implements ActionListener{
	
	JComboBox Book   = new JComboBox();
	JComboBox Member = new JComboBox();
	
	JLabel LBook   = new JLabel("Book");
	JLabel LMember = new JLabel("Member");
	
	JLabel Title   = new JLabel("Return Book");
	
	JButton add = new JButton("Give");
	JButton Go= new JButton("GO");
	
	Recivebook(){
		
		
		try {
			Statement st = new connection().takeThis();
			ResultSet rs = st.executeQuery("SELECT `MemberID` FROM disterbution");
			
			while(rs.next()) {
				
				Member.addItem  (rs.getString(1) );
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		AutoCompleteDecorator.decorate(Book);
		AutoCompleteDecorator.decorate(Member);
		
//		Member.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				
//				
//			}
//		});
		
		
		Font fon = new Font("Arial", Font.PLAIN, 20);

		Member  .setBounds(150, 20 +100, 300, 30);
		Book    .setBounds(150, 70 +100, 300, 30);

		LMember .setBounds(50, 20 +100, 300, 30);
		LBook   .setBounds(50, 70 +100, 300, 30);
		
		Go      .setBounds(150, 250,70,40);
		add     .setBounds(50, 250,70,40);
		Title   .setBounds(50, 20, 400, 100);
		
		Title.setFont(new Font("Arial", Font.PLAIN, 30));
		add     .addActionListener(this);
		Go      .addActionListener(this);	
		
		add(Go  );
		add(Title  );
		add(add    );
		add(Book   );
		add(Member );

		add(LBook   );
		add(LMember );
		
		setSize(500,400);
		setLocationRelativeTo(null);
		setTitle("Add Boook");
		setDefaultCloseOperation(3);
		setLayout(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Recivebook();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == Go) {
			
			Book.removeAllItems();
			
			String NameSt = String.valueOf(Member.getSelectedItem());
			
			
			try {
				Statement st = new connection().takeThis();
				ResultSet rs = st.executeQuery("SELECT `BookID` FROM disterbution WHERE MemberID = '"+NameSt+"'");
				
				while(rs.next()) {
					Book.addItem(rs.getString(1));
				}
				
			} catch (SQLException e1) {e1.printStackTrace();}
			
		}else {
			Date  dt = new Date();
			
			SimpleDateFormat formater = new SimpleDateFormat("d/M/yyyy");
			
			String BookSt = String.valueOf(Book.getSelectedItem());
			
			String MemberSt = String.valueOf(Member.getSelectedItem());
			
			String date = String.valueOf(formater.format(dt));
			
			
			try {
				
				Statement st = new connection().takeThis();
				st.executeUpdate("UPDATE disterbution SET SubmitionD = " + date + " WHERE MemberID = '"+MemberSt+"' AND BookID = '"+BookSt+"'");
				
				JOptionPane.showMessageDialog(null, "Added");
			} catch (Exception e1) { e1.printStackTrace(); }
		}
		
		
		
		
		
	}

	

}
