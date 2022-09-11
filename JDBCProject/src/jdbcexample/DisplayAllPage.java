package jdbcexample;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.*;
public class DisplayAllPage extends JFrame implements ActionListener
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lbenterquery;
	JTextArea txtenterquery;
	JButton bthome,btexit,bexecute;
	JPanel p1,p2,p3,p4,mp1,mp2,mp;
	JTable jt;
	JScrollPane jsp;
	JOptionPane op;
	DefaultTableModel dtm;
	
	String column[]={};
	String data[][]={{}};
	
 	Connection con;
 	PreparedStatement pst;
 	ResultSet rs;
 	ResultSetMetaData rsmd;
 	
	DisplayAllPage()
	{
		lbenterquery=new JLabel("Enter Query ");
		bexecute=new JButton("Execute");
		txtenterquery=new JTextArea(5,20);
		bthome=new JButton("Home");
		btexit=new JButton("Exit");
		
		int vsp=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int hsp=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		
		dtm=new DefaultTableModel(data,column);
		jt=new JTable(dtm);
		jsp=new JScrollPane(jt,vsp,hsp);	
		op=new JOptionPane();
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel(new GridLayout(1,2,10,10));
		p4=new JPanel();
		mp1=new JPanel(new GridLayout(3,1,10,10));
		mp2=new JPanel(new BorderLayout());
		mp=new JPanel(new BorderLayout());
		setLayout(new FlowLayout());
		
		p1.add(lbenterquery);
		p1.add(txtenterquery);
		
		p2.add(bexecute);

		p3.add(jsp);
		
		p4.add(bthome);
		p4.add(btexit);
		
		mp1.add(p1);
		mp1.add(p2);
		
		mp2.add(p3,BorderLayout.NORTH);
		mp2.add(p4,BorderLayout.CENTER);
		
		mp.add(mp1,BorderLayout.NORTH);
		mp.add(mp2,BorderLayout.CENTER);
		
		add(mp);

		setTitle("Table");
		setBounds(300,300,500,500);
		setVisible(true);
		bthome.addActionListener(this);
		btexit.addActionListener(this);
		bexecute.addActionListener(this);

		
	    MakeConnection();
	    
	}
	
 	void MakeConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","RUSHABH","Wadsa@123");  //for oracle,....
			JOptionPane.showMessageDialog(this,"database connected");	
		}catch(Exception ae) {
			JOptionPane.showMessageDialog(this,"Error in Connection!!");	
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==bexecute) {
			try {
				String query = txtenterquery.getText();
	
				if(query != null && query.length()>0) {
					pst = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = pst.executeQuery();
					rsmd = rs.getMetaData();
					int cols = rsmd.getColumnCount();
					column = new String[cols];
					int i;
					for(i=1; i<= cols; i++) {
						column[i-1] = rsmd.getColumnName(i);
					}
					int rows = 0;
					while(rs.next()) {
						rows++;
					}
					data = new String[rows][cols];
					rs.beforeFirst();
					int j=0;
					while(rs.next()) {
						for(i =1; i<=cols; i++) {
						data[j][i-1] = rs.getString(i);
						}
					j++;
					}
				}
				dtm = new DefaultTableModel(data,column);
				jt.setModel(dtm);
			}catch(Exception e) {
	 			JOptionPane.showMessageDialog(this,"Error in Query Execution!!");
	 			e.printStackTrace();
			}
		}
		
		if(ae.getSource()==btexit) {
			System.exit(0);
		}

		if(ae.getSource()==bthome) {
			this.setVisible(false);
			new HomePage();
		}
	}

	public static void main(String args[]) {
		new DisplayAllPage();
	}
}