import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;


/**
 * @author Maiken Beate Fjellanger
 *
 */
public class View extends JFrame implements ActionListener{
	
	private Container contentPane;
	private JLabel question;
	

	/**
	 * 
	 */
	public View(){
		setUpInterFace();
	}

	/**
	 * 
	 */
	private void setUpInterFace() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setFont(new Font("Serif", Font.PLAIN, 13));
		contentPane.setBackground(Color.WHITE);
		
		addFillers();
		addLogo();
		setUpQuestionPanel();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * 
	 */
	private void addFillers() {
		JPanel east = new JPanel();
		JPanel west = new JPanel();
		
		east.setSize(100, 500);
		west.setSize(100, 500);
		
		contentPane.add(east, BorderLayout.EAST);
		contentPane.add(west, BorderLayout.WEST);
	}
	
	private void addLogo() {
		JPanel north = new JPanel();
		JLabel logo = new JLabel("Semantic Quiz");
		
		north.add(logo);
		north.setSize(600, 30);
		contentPane.add(north, BorderLayout.NORTH);
	}

	/**
	 * 
	 */
	private void setUpQuestionPanel() {
		JPanel questionPanel = new JPanel();
		questionPanel.setBackground(Color.WHITE);
		contentPane.add(questionPanel, BorderLayout.CENTER);
		question = new JLabel("Here comes the big question!");
		questionPanel.add(question);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		View view = new View();

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
