
/**
 * Main - runs application
 */
public class Main {

	private static SemanticInfo semInfo;
    
    public static void main (String args[]) 
    {
    	semInfo = SemanticInfo.instantiate();
    	VisualManager.instantiate(semInfo.getQuestion());
    	View.instantiate();
    }
}