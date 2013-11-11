import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
			
			view.fasit((answer == VisualManager.instantiate().getCurrentQue().getQueAns() ? true : false),
					VisualManager.instantiate().getCurrentQue().con().description,
					VisualManager.instantiate().getCurrentQue().getQueAns());
		}
		else if(event.getActionCommand().equals("Neste")){
			askAllTheQuestions();
		}
		else if(event.getActionCommand().equals("Ferdig")){
			view.finish();
		}	
	}
	private void askAllTheQuestions()
	{
		i++;
		VisualManager.instantiate().setNextQue(i);
		view.setQuestion(VisualManager.instantiate().getCurrentQue().getQue(), getWrongAnswers());
	}
	private ArrayList<String> getWrongAnswers()
	{
		ArrayList<String> answers = new ArrayList<String>();
		switch(VisualManager.instantiate().getCurrentQue().tOQ())
		{
		case MovieQuestion:
			
			switch((Question.MovieQuestionType)VisualManager.instantiate().getCurrentQue().tOQ().getValue())
			{
			case directed≈:
				break;
			case genre≈:
				break;
			case date≈: 
				break;
			}
			break;
		}
		answers.add("David");
		answers.add("Svein");
		answers.add("Per");
		return answers;
	}
}
