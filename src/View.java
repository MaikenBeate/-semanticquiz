import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;


/**
 * @author Maiken Beate Fjellanger
 * Singleton instantiation
 *
 */
public class View extends JFrame{
	
	private Container contentPane;
	private JLabel question;
	private static View view = null;
	

	/**
	 * 
	 */
	private View(){
		setUpInterFace();
	}
	
	public static View instantiate(){
		
		if(view == null){
			view = new View();
		}
		
		return view;
	}

	/**
	 * 
	 */
	private void setUpInterFace() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setFont(new Font("Serif", Font.PLAIN, 13));
		contentPane.setBackground(Color.WHITE);
		
		addLogo();
		setUpQuestionPanel();
		addFooter();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
	
	private void addLogo() {
		JPanel west = new JPanel();
		west.setBackground(Color.WHITE);
		
		JLabel logo = new JLabel();
		ImageIcon imgThisImg = new ImageIcon("img/semantic_Quiz2.jpg");
		logo.setIcon(imgThisImg);
		west.add(logo);
		
		contentPane.add(west, BorderLayout.WEST);
	}
	
	private void addFooter(){
		JButton okButton = new JButton();
		okButton.setIcon(new ImageIcon("img/answer.jpg"));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(okButton);
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
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
	 * Temporary
	 */
	public static void main(String[] args) {
		View view = new View();

	}



}
