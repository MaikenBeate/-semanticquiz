import java.util.ArrayList;
import java.util.Collections;

/**
 * VisualManager - Singleton instantiatied
 */
public class VisualManager{

	private static VisualManager visualManager = null;
	private static Question[] questionArray;
	private Question currentQue;
	private VisualManager(){}
	
	/**
	 * VisualManager - Shuffles the various difficulties and orders them from easy to hard
	 */
	private VisualManager(Question[] questionArray)
	{
		ArrayList<Question> low, medium, high;
		low = medium = high = new ArrayList<Question>();
		for (int i = 0; i < questionArray.length; i++)
		{
	        if(questionArray[i].con().difficultyLevel == "hard")
	        {
	        	low.add(questionArray[i]);
	        }
	        else if(questionArray[i].con().difficultyLevel == "medium")
	        {
	        	medium.add(questionArray[i]);
	        }
	        else if(questionArray[i].con().difficultyLevel == "easy")
	        {
	        	high.add(questionArray[i]);
	        }
		}
		Collections.shuffle(low);
		Collections.shuffle(medium);
		Collections.shuffle(high);
		low.addAll(medium);
		low.addAll(high);
		
		VisualManager.questionArray = low.toArray(new Question[low.size()]);
	}
	
	public static VisualManager instantiate()
	{	
		if(visualManager == null)
		{
			return null;
		}
		return visualManager;
	}

	public static VisualManager instantiate(Question[] questionArray)
	{	
		if(visualManager == null)
		{
			visualManager = new VisualManager(questionArray);
		}
		return visualManager;
	}
	
	/**
	 * setNextQue, getCurrentQue - Gets and returns the current question, used to interact with 
	 */
	public void setNextQue(int i)
	{
		currentQue = VisualManager.questionArray[i];
	}
	
	public Question getCurrentQue()
	{
		return currentQue;
	}
	
	/**
	 * getAnswers - gets wrong answers from the containers of the other questions, 
	 * fitting the current question and shuffles order of answers.
	 */
	public ArrayList<String> getAnswers()
	{		
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(currentQue.getQueAns());
		while(answers.size() < 3)
		{
			Question tempQue = questionArray[(int)(Math.random() * questionArray.length)];
			String tempString = new String();
			switch(currentQue.tOQ())
			{
			case MovieQuestion:
				switch((MovieQuestionType)currentQue.getLow())
				{
				case directedÅ:
					tempString = tempQue.con().directorName;
					break;
					
				case dateÅ:
					tempString = tempQue.con().releaseDate;
					break;
				}
				break;
			}
			if(!answers.contains(tempString) && (tempString != null && tempString.length() > 0))
			{
				answers.add(tempString);
			}
		}
		
		Collections.shuffle(answers);
		return answers;
	}
}
