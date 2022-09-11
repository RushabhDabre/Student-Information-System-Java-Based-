package jdbcexample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

 class LoginPage extends JFrame implements ActionListener {
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lid, lpass;
 	JTextField tid;
 	JPasswordField tpass;
 	JButton blogin, bexit;
 	JPanel pid, pname, pbtn,pmain;
 	JOptionPane jop;
 	Connection con;
 	PreparedStatement pst;
 	ResultSet rs;

 	LoginPage() {
 	lid = new JLabel("UserId");
 	lpass = new JLabel("Password");

 	tid = new JTextField(20);
 	tpass = new JPasswordField(20);

 	blogin = new JButton("Login");
 	bexit = new JButton("Exit");

 	pid = new JPanel(new GridLayout(1,2,10,10));
 	pname = new JPanel(new GridLayout(1,2,10,10));
 	pbtn = new JPanel(new GridLayout(1,2,10,10));
 	pmain = new JPanel(new GridLayout(3,1,10,10));

        setLayout(new FlowLayout());

        pid.add(lid);
        pid.add(tid);

        pname.add(lpass);
        pname.add(tpass);

        pbtn.add(blogin);
        pbtn.add(bexit);

        pmain.add(pid);
        pmain.add(pname);
        pmain.add(pbtn);

        add(pmain);

        setTitle("LoginPage");
		setBounds(300, 300, 500, 500);
        setVisible(true);

        blogin.addActionListener(this);
        bexit.addActionListener(this);
        MakeConnection();
 	}
 	
 	void MakeConnection() {
 		try {
 			Class.forName("oracle.jdbc.driver.OracleDriver");
 			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","RUSHABH","Wadsa@123");  //for oracle,....
 			pst = con.prepareStatement("select * from userdetails where username = ? and password = ?");
 			JOptionPane.showMessageDialog(this,"database connected");	
 		}catch(Exception ae) {
 			JOptionPane.showMessageDialog(this,"Error in Connection!!");	
 		}
 	}

 	public void actionPerformed(ActionEvent ae) {
					if(ae.getSource()==blogin){
						String user = tid.getText();
						String pass = new String(tpass.getPassword());
						if(ae.getSource() == blogin) {
							try {
								pst.setString(1, user);
								pst.setString(2, pass);
								rs = pst.executeQuery();
								if(rs.next()) {
									JOptionPane.showMessageDialog(this,"Login Successfull!!");
									this.setVisible(false);
									new HomePage();
								}else {
									JOptionPane.showMessageDialog(this,"Entered Username or Password is incorrect!!");
								}
							}catch(Exception e) {
								JOptionPane.showMessageDialog(this,"Error in Login!!");
							}
						}
					}
					if(ae.getSource()==bexit){
						System.exit(0);
						}
	}
 public static void main(String args[]) {
 	new LoginPage();
 }
 }