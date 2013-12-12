public class Main {

	private static SemanticInfo semInfo;
	/**
	 * Main - Instantiates and runs the main functions needed to run the quiz
	 */
    public static void main (String args[]) 
    {
    	semInfo = SemanticInfo.instantiate();
    	VisualManager.instantiate(semInfo.getQuestion());
    	View.instantiate();
    }
}