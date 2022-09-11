package jdbcexample;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

class DisplayPage extends JFrame implements ActionListener {
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lroll, lname, laddress;
 	JTextField troll, tname, taddress;
 	JButton bnext, bpre, bhome,bfirst,blast;
 	JPanel proll, pname, pbtn, paddress, pmain;
 	JOptionPane jop;
 	Connection con;
 	PreparedStatement pst;
 	ResultSet rs;

 	DisplayPage() {
 	lroll = new JLabel("UserId");
 	lname = new JLabel("Name");
 	laddress = new JLabel("Address");

 	troll = new JTextField(20);
 	tname = new JTextField(20);
 	taddress = new JTextField(20);

 	bnext = new JButton("Next");
 	bpre = new JButton("Previous");
 	bhome = new JButton("Home");
 	bfirst = new JButton("First");
 	blast = new JButton("Last");


 	proll = new JPanel(new GridLayout(1,2,10,10));
 	pname = new JPanel(new GridLayout(1,2,10,10));
 	paddress = new JPanel(new GridLayout(1,2,10,10));
 	pbtn = new JPanel(new GridLayout(1,5,10,10));
 	pmain = new JPanel(new GridLayout(4,1,10,10));

    setLayout(new FlowLayout());

    proll.add(lroll);
    proll.add(troll);

    pname.add(lname);
    pname.add(tname);

    paddress.add(laddress);
    paddress.add(taddress);

    pbtn.add(bfirst);
    pbtn.add(bnext);
    pbtn.add(bpre);
    pbtn.add(blast);
    pbtn.add(bhome);

    pmain.add(proll);
    pmain.add(pname);
    pmain.add(paddress);
    pmain.add(pbtn);

    add(pmain);

    setTitle("DisplayPage");
	setBounds(300, 300, 500, 500);
    setVisible(true);

    bnext.addActionListener(this);
	bpre.addActionListener(this);
	bhome.addActionListener(this);
	bfirst.addActionListener(this);
	blast.addActionListener(this);
    MakeConnection();
 	}
 	
	void MakeConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","RUSHABH","Wadsa@123");  //for oracle,....
			pst = con.prepareStatement("Select * from student",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
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
		if(ae.getSource()==bfirst){
			try {
				if(rs.first()) {
					showRecords();
				}
			}catch(Exception e) {
	 			JOptionPane.showMessageDialog(this,"Error in Display");	
			}
		}
		if(ae.getSource()==bnext){
			try {
				if(rs.next()) {
					showRecords();
				}
			}catch(Exception e) {
	 			JOptionPane.showMessageDialog(this,"Error in Display");	
			}
		}
		if(ae.getSource()==bpre){
			try {
				if(rs.previous()) {
					showRecords();
				}
			}catch(Exception e) {
	 			JOptionPane.showMessageDialog(this,"Error in Display");	
			}
		}
		if(ae.getSource()==blast){
			try {
				if(rs.last()) {
					showRecords();
				}
			}catch(Exception e) {
	 			JOptionPane.showMessageDialog(this,"Error in Display");	
			}
		}
		if(ae.getSource()==bhome){
			this.setVisible(false);
			new HomePage();
			}
	}

 public static void main(String args[]) {
 	new DisplayPage();
 }
 }