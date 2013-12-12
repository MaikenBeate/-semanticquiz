public class Main {

	private static SemanticInfo semInfo;
    
	/**
	 * Main - Starts the application
	 */
    public static void main (String args[]) 
    {
    	semInfo = SemanticInfo.instantiate();
    	VisualManager.instantiate(semInfo.getQuestion());
    	View.instantiate();
    }
}