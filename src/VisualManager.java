//Singleton instantiation.
public class VisualManager{

	private static VisualManager visualManager = null;
	private static Question[] questionArray;
	
	private Question currentQue;
	//current state here:
	
	/**
	 * @param args
	 */
	private VisualManager(Question[] questionArray){
		VisualManager.questionArray = questionArray;
	}
	
	public static VisualManager instantiate(Question[] questionArray)
	{	
		if(visualManager == null)
		{
			visualManager = new VisualManager(questionArray);
		}
		return visualManager;
	}

	public Question getCurrentQue()
	{
		return currentQue;
	}
	public void setCurrentQueAnswered(boolean answered)
	{
		currentQue.answered = answered;
	}
	public void setCurrentQueDone(boolean done)
	{
		currentQue.done = done;
	}
}
