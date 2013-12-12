import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;


/**
 * Singleton instantiation - The GUI of the program
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame{
	
	private static View view = null;
	private Container contentPane;
	private ActionController actionController;
	
	private JPanel scorePanel;
	private int questionsAnswerd;
	private int correctAnswers;
	private int score;


	private JPanel currentView; // question, fasit or finished
	
	//Question view
	private JPanel questionPanel;
	private JLabel currentQuestion;
	private JPanel currentAnswersPanel;
	private ArrayList<JRadioButton> currentAnswers;
	private ButtonGroup currentAnswersGroup;
	private JPanel currentQuestionPanel;
	
	//Fasit view
	private JPanel solutionPanel;
	private JPanel wrongRightImagePanel;
	private JPanel wrongRightLabelHolder;
	private JLabel currentWrongRightLabel;
	private JPanel movieDescriptionHolder;
	private JTextArea currentMovieDescription;
	
	//Finished view
	private JPanel finishedPanel;
	private JPanel scoreLabelHolder;
	private JLabel currentScoreLabel;
	
	//buttons
	private JPanel buttonPanel;
	private JButton currentButton;
	private JButton answerButton;
	private JButton startButton;
	private JButton nextButton;
	private JButton finishButton;

	/**
	 * Constructor of View
	 */
	private View(){
		setUpInterFace();
	}
	
	/**
	 * Instantiates View
	 * @return View view
	 */
	public static View instantiate(){
		
		if(view == null){
			view = new View();
		}
		
		return view;
	}
	
	/**
	 * Visualise a question and answer-list in view
	 * @param String question - the question
	 * @param ArrayList<String> answer-alternatives
	 */
	public void setQuestion(String question, ArrayList<String> answerList){
		updateScore();
		
		if(currentView != questionPanel){
			if(currentView != null){
				contentPane.remove(currentView);
			}
			currentView = questionPanel;
			contentPane.add(questionPanel, BorderLayout.CENTER);
		}
		
		currentQuestionPanel.remove(currentQuestion);
		currentQuestion = new JLabel(question);
		
		buttonPanel.remove(currentButton);
		currentButton = answerButton;
		buttonPanel.add(currentButton);
		
		if(currentAnswers.size() >= 1){
			for(JRadioButton answer : currentAnswers){
				currentAnswersGroup.remove(answer);
				currentAnswersPanel.remove(answer);
			}
			currentAnswers.clear();
		}

		for(String answer : answerList){
			JRadioButton button = new JRadioButton(answer);
			button.setBackground(Color.WHITE);
			button.setActionCommand(answer);
			currentAnswers.add(button);
			currentAnswersPanel.add(button);
			currentAnswersGroup.add(button);
	
		}
		
		currentQuestionPanel.add(currentQuestion);
		pack();

	}
	
	/**
	 * Returns the selected answer
	 * @return String - the selected answer
	 *
	 */
	public String getAnswer() {
		return currentAnswersGroup.getSelection().getActionCommand();
	}
	
	/**
	 * Presents the question-solution
	 * @param Boolean - according to if the answer is correct or false
	 * @param String movieDescription - description of the movie
	 * @param String correctAnswer - the correct answer
	 */
	public void soulution(Boolean correct, String movieDescription, String correctAnswer){
		questionsAnswerd ++;
		
		if(currentView != solutionPanel){
			if(currentView != null){
				contentPane.remove(currentView);
			}
			currentView = solutionPanel;
			contentPane.add(solutionPanel, BorderLayout.CENTER);
		}
		
		if(correct == true){
			correctAnswers ++;
			wrongRightLabelHolder.remove(currentWrongRightLabel);
			currentWrongRightLabel = new JLabel("Correct!");
			wrongRightLabelHolder.add(currentWrongRightLabel);
		}
		else{
			wrongRightLabelHolder.remove(currentWrongRightLabel);
			currentWrongRightLabel = new JLabel("Wrong, the correct answer is " + correctAnswer);
			wrongRightLabelHolder.add(currentWrongRightLabel);
		}
		
		updateScore();
		movieDescriptionHolder.remove(currentMovieDescription);
		currentMovieDescription = textAreaProperties(new JTextArea(movieDescription));
		movieDescriptionHolder.add(currentMovieDescription);
		
		buttonPanel.remove(currentButton);
		if(questionsAnswerd == 10){
			score = correctAnswers;
			questionsAnswerd = 0;
			correctAnswers = 0;
			currentButton = finishButton;
		}
		else{
			currentButton = nextButton;
		}
		buttonPanel.add(currentButton);
		
		pack();
		
	}

	/**
	 * Shows the view for finished quiz-round
	 */
	public void finish(){
		if(currentView != finishedPanel){
			if(currentView != null){
				contentPane.remove(currentView);
			}
			currentView = finishedPanel;
			contentPane.add(finishedPanel, BorderLayout.CENTER);
		}
		scoreLabelHolder.remove(currentScoreLabel);
		currentScoreLabel = new JLabel("Gratulerer din score ble " + score);
		scoreLabelHolder.add(currentScoreLabel);
		
		buttonPanel.remove(currentButton);
		currentButton = startButton;
		buttonPanel.add(currentButton);
		
		buttonPanel.repaint();
		pack();
	}

	/**
	 * Sets up the user-interface/view
	 */
	private void setUpInterFace() {
		actionController = new ActionController(this);
		questionsAnswerd = 0;
		correctAnswers = 0;
		
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
			
		addLogo();
		setUpAllViews();
		addFooter();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}
	
	/**
	 * Adds the semantic-quiz logo to the view
	 */
	private void addLogo() {
		JPanel west = new JPanel();
		west.setBackground(Color.WHITE);
		
		JLabel logo = new JLabel();
		ImageIcon imgThisImg = new ImageIcon("img/semantic_Quiz2.jpg");
		logo.setIcon(imgThisImg);
		west.add(logo);
		
		contentPane.add(west, BorderLayout.WEST);
	}
	
	/**
	 * Updates the current score in view
	 */
	private void updateScore(){
		
		if(scorePanel != null){
			contentPane.remove(scorePanel);
		}
		
		JLabel score = new JLabel(correctAnswers + " / " + questionsAnswerd);
		scorePanel = new JPanel();
		scorePanel.add(score);
		contentPane.add(scorePanel, BorderLayout.NORTH);
		contentPane.repaint();
		pack();
	}
	
	/**
	 * Adds the footer of the view. Initiates all the buttons, sets start-button in view
	 */
	private void addFooter(){
		answerButton = new JButton("Answer");
		answerButton.addActionListener(actionController);
		
		startButton = new JButton("Start");
		startButton.addActionListener(actionController);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(actionController);
		
		finishButton = new JButton("Finish");
		finishButton.addActionListener(actionController);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(startButton);
		currentButton = startButton;
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Sets up all the views
	 */
	private void setUpAllViews() {
		
		setUpQuestinView();
		
		setUpSolutionView();
		
		setUpFinishedView();
	}

	/**
	 * Sets up the quiz-is-finished view
	 */
	private void setUpFinishedView() {
		finishedPanel = new JPanel();
		finishedPanel.setLayout(new BoxLayout(finishedPanel, BoxLayout.Y_AXIS)); //shall be vertical
		finishedPanel.setPreferredSize(new Dimension(500, 500));
		finishedPanel.setBackground(Color.WHITE);
		
		scoreLabelHolder = new JPanel();
		scoreLabelHolder.setBackground(Color.WHITE);
		currentScoreLabel = new JLabel("Hei");
		scoreLabelHolder.add(currentScoreLabel);
		
		finishedPanel.add(scoreLabelHolder);
	}

	/**
	 * Sets up the solution-for-a-question-view
	 *
	 */
	private void setUpSolutionView() {
		solutionPanel = new JPanel();
		solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.Y_AXIS)); //shall be vertical
		solutionPanel.setPreferredSize(new Dimension(500, 500));
		solutionPanel.setBackground(Color.WHITE);
		
		wrongRightImagePanel = new JPanel();
		wrongRightImagePanel.setBackground(Color.WHITE);
		wrongRightLabelHolder = new JPanel();
		wrongRightLabelHolder.setBackground(Color.WHITE);
		currentWrongRightLabel = new JLabel("");
		wrongRightLabelHolder.add(currentWrongRightLabel);
		movieDescriptionHolder = new JPanel();
		movieDescriptionHolder.setBackground(Color.WHITE);
		currentMovieDescription = textAreaProperties(new JTextArea());
		movieDescriptionHolder.add(currentMovieDescription);
		
		solutionPanel.add(wrongRightImagePanel);
		solutionPanel.add(wrongRightLabelHolder);
		solutionPanel.add(movieDescriptionHolder);
	}

	/**
	 * Sets up the question-view
	 */
	private void setUpQuestinView() {
		questionPanel = new JPanel();
		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS)); //shall be vertical
		questionPanel.setPreferredSize(new Dimension(500, 500));
		questionPanel.setBackground(Color.WHITE);
		contentPane.add(questionPanel, BorderLayout.CENTER);
		currentView = questionPanel;
		
		currentQuestion = new JLabel("Welcome!");
		currentQuestionPanel = new JPanel();
		currentQuestionPanel.add(currentQuestion);
		currentQuestionPanel.setBackground(Color.WHITE);
		currentAnswersPanel = new JPanel();
		currentAnswersPanel.setBackground(Color.WHITE);
		currentAnswers = new ArrayList<JRadioButton>();
		currentAnswersGroup = new ButtonGroup();
		questionPanel.add(currentQuestionPanel);
		questionPanel.add(currentAnswersPanel);
	}
	
	/**
	 * Sets properties for a text-area
	 * @param JTextArea - a text-area
	 * @return JTextAre - the text-area with the new properties
	 *
	 */
	private JTextArea textAreaProperties(JTextArea textArea) {
	    textArea.setEditable(false);  
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    return textArea;
	}


}
