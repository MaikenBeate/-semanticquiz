import java.util.ArrayList;
import java.util.Collections;

//Singleton instantiation.
public class VisualManager{

	private static VisualManager visualManager = null;
	private static Question[] questionArray;
	private Question currentQue;
	private VisualManager(){}
	
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
	public void setNextQue(int i)
	{
		currentQue = VisualManager.questionArray[i];
		System.out.println(currentQue.getQue());
	}
	public Question getCurrentQue()
	{
		System.out.println(currentQue.con().movieName);
		return currentQue;
	}
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
			if(!answers.contains(tempString))
			{
				answers.add(tempString);
			}
		}
		
		Collections.shuffle(answers);
		return answers;
	}
}
