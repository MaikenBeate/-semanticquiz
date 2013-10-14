import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Singleton instantiation.
public class VisualManager implements ActionListener{

	private static VisualManager visualManager = null;

	/**
	 * @param args
	 */
	private VisualManager(Question[] questionArray){
		
	}
	
	public static VisualManager instantiate(Question[] questionArray){
		
		if(visualManager == null)
		{
			visualManager = new VisualManager(questionArray);
		}
		return visualManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
