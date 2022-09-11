package jdbcexample;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

class InsertPage extends JFrame implements ActionListener {
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lroll, lname, laddress;
 	JTextField troll, tname, taddress;
 	JButton binsert, bclear, bhome;
 	JPanel proll, pname, pbtn, paddress, pmain;
 	JOptionPane jop;
 	Connection con;
 	PreparedStatement pst;

 	InsertPage() {
 	lroll = new JLabel("UserId");
 	lname = new JLabel("Name");
 	laddress = new JLabel("Address");

 	troll = new JTextField(20);
 	tname = new JTextField(20);
 	taddress = new JTextField(20);

 	binsert = new JButton("Insert");
 	bclear = new JButton("Clear");
 	bhome = new JButton("Home");

 	proll = new JPanel(new GridLayout(1,2,10,10));
 	pname = new JPanel(new GridLayout(1,2,10,10));
 	paddress = new JPanel(new GridLayout(1,2,10,10));
 	pbtn = new JPanel(new GridLayout(1,2,10,10));
 	pmain = new JPanel(new GridLayout(4,1,10,10));

    setLayout(new FlowLayout());

    proll.add(lroll);
    proll.add(troll);

    pname.add(lname);
    pname.add(tname);

    paddress.add(laddress);
    paddress.add(taddress);

    pbtn.add(binsert);
    pbtn.add(bclear);
    pbtn.add(bhome);

    pmain.add(proll);
    pmain.add(pname);
    pmain.add(paddress);
    pmain.add(pbtn);

    add(pmain);

    setTitle("InsertPage");
	setBounds(300, 300, 500, 500);
    setVisible(true);

        binsert.addActionListener(this);
        bclear.addActionListener(this);
        bhome.addActionListener(this);
        MakeConnection();
 	}
 	void clearfield() {
 		troll.setText("");
 		tname.setText("");
 		taddress.setText("");
 		troll.requestFocus();
 	}
 	void MakeConnection() {
 		try {
 			Class.forName("oracle.jdbc.driver.OracleDriver");
 			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","RUSHABH","Wadsa@123");  //for oracle,....
 			pst = con.prepareStatement("Insert into student values(?,?,?)");
 			JOptionPane.showMessageDialog(this,"database connected");	
 		}catch(Exception ae) {
 			JOptionPane.showMessageDialog(this,"Error in Connection!!");	
 		}
 	}

 	public void actionPerformed(ActionEvent ae) {
				if(ae.getSource()==binsert){
					int roll = Integer.parseInt(troll.getText());
					String name = tname.getText();
					String adr = taddress.getText();
					if(name.length()>0 && adr.length()>0) {
						try {
							pst.setInt(1, roll);
							pst.setString(2, name);
							pst.setString(3, adr);
							int res = pst.executeUpdate();
							if(res >= 1) {
								JOptionPane.showMessageDialog(this, res + " record is inserted");
							}
							else {
								JOptionPane.showMessageDialog(this,"Data is incorect!!");
							}
						}catch(Exception e) {
							JOptionPane.showMessageDialog(this,"Error in insert","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					
					}
				if(ae.getSource()==bclear){
			        clearfield();
					}
				if(ae.getSource()==bhome){
					this.setVisible(false);
					new HomePage();
					}
	}
 public static void main(String args[]) {
 	new InsertPage();
 }
 }