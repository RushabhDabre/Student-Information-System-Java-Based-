package jdbcexample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HomePage extends JFrame implements ActionListener {

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton binsert, bdelete, bsearch, bdisplay, bdisplayall;
 	JPanel pinsert, pdelete, psearch, pdisplay, pdisplayall, pmain;

 	HomePage() {

 	binsert = new JButton("Insert");
 	bdelete = new JButton("Delete");
 	bsearch = new JButton("Search");
 	bdisplay = new JButton("Display");
 	bdisplayall = new JButton("Display All");

 	pinsert = new JPanel();
 	pdelete = new JPanel();
 	pdisplay = new JPanel();
 	psearch = new JPanel();
 	pdisplayall = new JPanel();
 	pmain = new JPanel(new GridLayout(5,1,10,10));

    setLayout(new FlowLayout());

    pinsert.add(binsert);
    pdelete.add(bdelete);
    psearch.add(bsearch);
    pdisplay.add(bdisplay);
    pdisplayall.add(bdisplayall);

    pmain.add(pinsert);
    pmain.add(pdelete);
    pmain.add(psearch);
    pmain.add(pdisplay);
    pmain.add(pdisplayall);

    add(pmain);

    setTitle("HomePage");
	setBounds(300, 300, 500, 500);
    setVisible(true);

	binsert.addActionListener(this);
	bdelete.addActionListener(this);
	bsearch.addActionListener(this);
	bdisplay.addActionListener(this);
	bdisplayall.addActionListener(this);

 	}

 public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==binsert){
			//int roll = Integer.parseInt();
			this.setVisible(false);
			new InsertPage();
			}
		if(ae.getSource()==bdelete){
			this.setVisible(false);
			new DeletePage();
			}
		if(ae.getSource()==bsearch){
			this.setVisible(false);
			new SearchPage();
			}
		if(ae.getSource()==bdisplay){
			this.setVisible(false);
			new DisplayPage();
			}
		if(ae.getSource()==bdisplayall){
			this.setVisible(false);
			new DisplayAllPage();
			}
}

 public static void main(String args[]) {
 	new HomePage();
 }
 }