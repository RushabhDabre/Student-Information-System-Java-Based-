package jdbcexample;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

 class DeletePage extends JFrame implements ActionListener {
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lroll;
 	JTextField troll;
 	JButton bdelete, bhome;
 	JPanel proll, pbtn, pmain;
 	JOptionPane jop;
 	Connection con;
 	PreparedStatement pst;

 	DeletePage() {

 	lroll = new JLabel("Roll No");

 	troll = new JTextField(20);

 	bdelete = new JButton("Delete");
 	bhome = new JButton("Home");

 	proll = new JPanel(new GridLayout(1,2,10,10));
 	pbtn = new JPanel(new GridLayout(1,2,10,10));
 	pmain = new JPanel(new GridLayout(3,1,10,10));

    setLayout(new FlowLayout());

        proll.add(lroll);
        proll.add(troll);

        pbtn.add(bdelete);
        pbtn.add(bhome);

        pmain.add(proll);
        pmain.add(pbtn);

        add(pmain);

        setTitle("DeletePage");
		setBounds(300, 300, 500, 500);
        setVisible(true);

        bdelete.addActionListener(this);
        bhome.addActionListener(this);
        MakeConnection();

 	}
	void MakeConnection() {
 		try {
 			Class.forName("oracle.jdbc.driver.OracleDriver");
 			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","RUSHABH","Wadsa@123");  //for oracle,....
 			pst = con.prepareStatement("Delete from student where roll=?");
 			JOptionPane.showMessageDialog(this,"database connected");	
 		}catch(Exception ae) {
 			JOptionPane.showMessageDialog(this,"Error in Connection!!");	
 		}
 	}

 	public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==bdelete){
				int roll = Integer.parseInt(troll.getText());
					try {
						pst.setInt(1, roll);
					 int res = pst.executeUpdate();
						if(res >= 1) {
							JOptionPane.showMessageDialog(this, res + "record is deleted!!");
						}
						else {
							JOptionPane.showMessageDialog(this,"no record found!!");
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(this,"Error in delete","Error",JOptionPane.ERROR_MESSAGE);
					}
				
				}
			if(ae.getSource()==bhome){
				this.setVisible(false);
				new HomePage();
				}
	}

 public static void main(String args[]) {
 	new DeletePage();
 }
 }