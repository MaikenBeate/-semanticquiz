import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ActionController implements ActionListener{
	
	private View view;
	
	public ActionController(View view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getActionCommand().equals("Start")){
			//get the question and answers from visualmanager and call view.setQuestion
			
			//test
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("David");
			answers.add("Svein");
			answers.add("Per");
			view.setQuestion("Hvem regisserte Titanic?", answers);
		}
		else if(event.getActionCommand().equals("Answer")){
			String answer = view.getAnswer();
			//get information from visualmanager and call view.fasit()
			
			//test
			System.out.println(answer);
			view.fasit(false, "Bra film!", "Per");
		}
		else if(event.getActionCommand().equals("Neste")){
			//generate new question - and call view.setQuestion
			
			//test
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("Mari");
			answers.add("Ola");
			answers.add("Lisa");
			view.setQuestion("Hvem regisserte Lion King?", answers);
		}
		else if(event.getActionCommand().equals("Ferdig")){
			view.finish();
		}
		
	}

}
