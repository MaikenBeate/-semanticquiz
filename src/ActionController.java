import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener{
	
	private View view;
	private int i = -1;
	
	public ActionController(View view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getActionCommand().equals("Start")){
			askAllTheQuestions();
		}
		else if(event.getActionCommand().equals("Answer")){
			String answer = view.getAnswer();
			
			view.soulution((answer == VisualManager.instantiate().getCurrentQue().getQueAns() ? true : false),
				VisualManager.instantiate().getCurrentQue().con().description,
					VisualManager.instantiate().getCurrentQue().getQueAns());
		}
		else if(event.getActionCommand().equals("Next")){
			askAllTheQuestions();
		}
		else if(event.getActionCommand().equals("Finish")){
			view.finish();
		}	
	}
	private void askAllTheQuestions()
	{
		i++;
		VisualManager.instantiate().setNextQue(i);
		view.setQuestion(VisualManager.instantiate().getCurrentQue().getQue(), VisualManager.instantiate().getAnswers());
	}
}
