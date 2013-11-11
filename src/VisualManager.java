//Singleton instantiation.
public class VisualManager{

	private static VisualManager visualManager = null;
	private static Question[] questionArray;
	private Question currentQue;
	
	private VisualManager(){}
	private VisualManager(Question[] questionArray){
		VisualManager.questionArray = questionArray;
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
	}
	public Question getCurrentQue()
	{
		return currentQue;
	}
}
