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

public class AddBook extends JFrame implements ActionListener,KeyListener{
	
	JTextField Name   = new JTextField();
	JTextField Author = new JTextField();
	JTextField price  = new JTextField();
	JTextField RoomNo = new JTextField();
	JTextField ShelfNo= new JTextField();
	JTextField BookID = new JTextField("0.0.");
	
	JLabel LName   = new JLabel("Name");
	JLabel LAuthor = new JLabel("Author");
	JLabel Lprice  = new JLabel("Price");
	JLabel LRoomNo = new JLabel("Room No");
	JLabel LShelfNo= new JLabel("Shelf No");
	JLabel LBookID = new JLabel("BookId");
	
	JLabel Title   = new JLabel("Add Book");
	
	JButton add = new JButton("Add");
	
	int number;
	AddBook(){
		number = 0;
		try {
			Statement st = new connection().takeThis();
			ResultSet rs = st.executeQuery("SELECT `ID` FROM `books`");
			
			while(rs.next()) {
				String str = rs.getString(1);
				String[] arrOfStr = str.split(".", 2);
				String[] arrOfStr2 = arrOfStr[1].split(".", 4);
				String strNO = arrOfStr2[3];
				System.out.println(strNO);
				if (Integer.valueOf(strNO) >= number){
					number = Integer.valueOf(strNO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		number = number+1;
		
		Font fon = new Font("Arial", Font.PLAIN, 20);

		Name   .setBounds(150, 20 +100, 300, 30);
		Author .setBounds(150, 70 +100, 300, 30);
		price  .setBounds(150, 130+100,300, 30);
		RoomNo .setBounds(150, 180+100,300, 30);
		ShelfNo.setBounds(150, 230+100,300, 30);
		BookID .setBounds(150, 280+100,300, 30);

		LName   .setBounds(50, 20 +100, 300, 30);
		LAuthor .setBounds(50, 70 +100, 300, 30);
		Lprice  .setBounds(50, 130+100,300, 30);
		LRoomNo .setBounds(50, 180+100,300, 30);
		LShelfNo.setBounds(50, 230+100,300, 30);
		LBookID .setBounds(50, 280+100,300, 30);
		add     .setBounds(50, 350+100,70,50);
		Title   .setBounds(50, 20, 400, 100);
		
		Title.setFont(new Font("Arial", Font.PLAIN, 30));
		add     .addActionListener(this);
		BookID  .setEditable(false);
		
		RoomNo.addKeyListener(this);
		ShelfNo.addKeyListener(this);
		
		add(Title  );
		add(add    );
		add(Name   );
		add(Author );
		add(price  );
		add(RoomNo );
		add(ShelfNo);
		add(BookID );

		add(LName   );
		add(LAuthor );
		add(Lprice  );
		add(LRoomNo );
		add(LShelfNo);
		add(LBookID );
		
		setSize(500,600);
		setLocationRelativeTo(null);
		setTitle("Add Boook");
		setDefaultCloseOperation(3);
		setLayout(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new AddBook();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			Statement st = new connection().takeThis();
			st.executeUpdate("INSERT INTO `books`(ID, Name, Author, price) VALUES ('"+BookID.getText()+"','"+Name.getText()+"','"+Author.getText()+"','"+price.getText()+"')");
			
			
			
			JOptionPane.showMessageDialog(null, "Added");
			
			ResultSet rs = st.executeQuery("SELECT `ID` FROM `books`");
				
			
			number = number+1;
			
			Name   .setText("");
			Author .setText("");
			price  .setText("");
			RoomNo .setText("");
			ShelfNo.setText("");
			BookID .setText("");
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override public void keyTyped(KeyEvent e) { } @Override public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		BookID.setText(RoomNo.getText()+"."+ShelfNo.getText()+"."+number);
	}

}
