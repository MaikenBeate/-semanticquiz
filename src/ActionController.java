import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Actionlistener for View
 */
public class ActionController implements ActionListener{
	
	private View view;
	private int i = -1;
	
	/**
	 * Constructor for Actioncontroller
	 * @param View view
	 */
	public ActionController(View view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//if start button is pressed - start quiz
		if(event.getActionCommand().equals("Start")){
			askAllTheQuestions();
		}
		//if answer-button is pressed - serve answer to view
		else if(event.getActionCommand().equals("Answer")){
			String answer = view.getAnswer();
			
			view.soulution((answer == VisualManager.instantiate().getCurrentQue().getQueAns() ? true : false),
				VisualManager.instantiate().getCurrentQue().con().description,
					VisualManager.instantiate().getCurrentQue().getQueAns());
		}
		//if next-button is pressed - keep on with the quiz
		else if(event.getActionCommand().equals("Next")){
			askAllTheQuestions();
		}
		//if finished-button is pressed - present finished-view
		else if(event.getActionCommand().equals("Finish")){
			view.finish();
		}	
	}
	
	/**
	 * Gets new question - present question in view
	 */
	private void askAllTheQuestions()
	{
		i++;
		VisualManager.instantiate().setNextQue(i);
		view.setQuestion(VisualManager.instantiate().getCurrentQue().getQue(), VisualManager.instantiate().getAnswers());
	}
}
