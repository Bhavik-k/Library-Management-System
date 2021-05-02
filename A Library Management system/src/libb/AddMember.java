package libb;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class AddMember extends JFrame implements ActionListener{
	
	JTextField Name   = new JTextField();
	JTextField Address = new JTextField();
	JTextField Phone  = new JTextField();
	JTextField ID = new JTextField();
	
	JLabel LName   = new JLabel("Name");
	JLabel LAddress = new JLabel("Address");
	JLabel LPhone  = new JLabel("Phone");
	JLabel LID = new JLabel("Id");
	
	JLabel Title   = new JLabel("Add BookMember");
	
	JButton add = new JButton("Add");
	
	int number;
	AddMember(){
		number = 0;
		try {
			Statement st = new connection().takeThis();
			ResultSet rs = st.executeQuery("SELECT MemId FROM `members`");
			
			while(rs.next()) {
				String str = rs.getString(1);
				if (Integer.valueOf(str) >= number){
					number = Integer.valueOf(str);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		number = number+1;
		ID.setText(String.valueOf(number));
		
		Font fon = new Font("Arial", Font.PLAIN, 20);

		Name   .setBounds(150, 20 +100, 300, 30);
		Address.setBounds(150, 70 +100, 300, 30);
		Phone  .setBounds(150, 130+100,300, 30);
		ID     .setBounds(150, 180+100,300, 30);

		LName   .setBounds(50, 20 +100, 300, 30);
		LAddress.setBounds(50, 70 +100, 300, 30);
		LPhone  .setBounds(50, 130+100,300, 30);
		LID     .setBounds(50, 180+100,300, 30);
		add     .setBounds(50, 350+100,70,50);
		Title   .setBounds(50, 20, 400, 100);
		
		Title.setFont(new Font("Arial", Font.PLAIN, 30));
		add  .addActionListener(this);
		ID   .setEditable(false);
		
		
		add(Title  );
		add(add    );
		add(Name   );
		add(Address );
		add(Phone  );
		add(ID );

		add(LName   );
		add(LAddress );
		add(LPhone  );
		add(LID );
		
		setSize(500,600);
		setLocationRelativeTo(null);
		setTitle("Add Boook");
		setDefaultCloseOperation(3);
		setLayout(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new AddMember();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			Statement st = new connection().takeThis();
			st.executeUpdate(
			"INSERT INTO `members`(MemId, Name, Address, Phone, MembershipE) "
			+ "VALUES ('"+number+"','"+Name.getText()+"','"+Address.getText()+"','"+Phone.getText()+"','0')");
			
			
			
			JOptionPane.showMessageDialog(null, "Added");
			
			number = number+1;
			
			Name   .setText("");
			Address .setText("");
			Phone  .setText("");
			ID .setText(String.valueOf(number));
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	

}
