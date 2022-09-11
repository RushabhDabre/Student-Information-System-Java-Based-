package jdbcexample;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

class SearchPage extends JFrame implements ActionListener {
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lroll, lname, laddress;
 	JTextField troll, tname, taddress;
 	JButton bsearch, bupdate, bhome;
 	JPanel proll, pname, pbtn, paddress, pmain;
 	JOptionPane jop;
 	Connection con;
 	PreparedStatement pst, pst1;
 	ResultSet rs;

 	SearchPage() {
 	lroll = new JLabel("UserId");
 	lname = new JLabel("Name");
 	laddress = new JLabel("Address");

 	troll = new JTextField(20);
 	tname = new JTextField(20);
 	taddress = new JTextField(20);

 	bsearch = new JButton("Search");
 	bupdate = new JButton("Update");
 	bhome = new JButton("Home");

 	proll = new JPanel(new GridLayout(1,2,10,10));
 	pname = new JPanel(new GridLayout(1,2,10,10));
 	paddress = new JPanel(new GridLayout(1,2,10,10));
 	pbtn = new JPanel(new GridLayout(1,3,10,10));
 	pmain = new JPanel(new GridLayout(4,1,10,10));

    setLayout(new FlowLayout());

    proll.add(lroll);
    proll.add(troll);

    pname.add(lname);
    pname.add(tname);

    paddress.add(laddress);
    paddress.add(taddress);

    pbtn.add(bsearch);
    pbtn.add(bupdate);
    pbtn.add(bhome);

    pmain.add(proll);
    pmain.add(pname);
    pmain.add(paddress);
    pmain.add(pbtn);

    add(pmain);

    setTitle("SearchPage");
	setBounds(300, 300, 500, 500);
    setVisible(true);

    bsearch.addActionListener(this);
	bupdate.addActionListener(this);
    bhome.addActionListener(this);
    MakeConnection();
 	}
 	
 	void MakeConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","RUSHABH","Wadsa@123");  //for oracle,....
			pst = con.prepareStatement("Select * from student where roll=?");
			pst1 = con.prepareStatement("Update student set name=?, address=? where roll=?");
			JOptionPane.showMessageDialog(this,"database connected");	
		}catch(Exception ae) {
			JOptionPane.showMessageDialog(this,"Error in Connection!!");	
		}
	}
 	
 	void showRecords() {
 		try {
 			troll.setText(""+rs.getInt(1));
 			tname.setText(rs.getString(2));
 			taddress.setText(rs.getString(3));
 		}catch(Exception e) {
 			JOptionPane.showMessageDialog(this,"Error in Search");	
 		}
 	}


 	public void actionPerformed(ActionEvent ae) {
					if(ae.getSource()==bsearch){
						int roll = Integer.parseInt(troll.getText());
						try {
							pst.setInt(1, roll);
							rs = pst.executeQuery();
							if(rs.next()) {
								JOptionPane.showMessageDialog(this,"Record is found!!");
								showRecords();
							}
							else {
								JOptionPane.showMessageDialog(this,"no Record found!!");
							}
						}catch(Exception e) {
							JOptionPane.showMessageDialog(this,"Error in Search","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					
					if(ae.getSource()==bupdate){
							try {
								int roll = Integer.parseInt(troll.getText());
								String name = tname.getText();
								String adr = taddress.getText();
								pst1.setString(1, name);
								pst1.setString(2, adr);
								pst1.setInt(3, roll);
								int res = pst1.executeUpdate();
								if(res >= 1) {
									JOptionPane.showMessageDialog(this, res + "record is Updated");
								}
								else {
									JOptionPane.showMessageDialog(this,"record is not updated");
								}
							}catch(Exception e) {
								JOptionPane.showMessageDialog(this,"Error in update","Error",JOptionPane.ERROR_MESSAGE);
							}
						
						
					}
					if(ae.getSource()==bhome){
						this.setVisible(false);
						new HomePage();
						}
	}

 public static void main(String args[]) {
 	new SearchPage();
 }
 }