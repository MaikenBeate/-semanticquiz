import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author Maiken Beate Fjellanger
 *
 */
public class View extends JFrame implements ActionListener{
	
	private Container contentPane;
	

	public View(){
		setUpInterFace();
	}

	private void setUpInterFace() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		View view = new View();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
