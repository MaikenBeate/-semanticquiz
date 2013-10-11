import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class Main {

	public static SemanticInfo semInfo;
	public static Container[] con;
	public static List<Question> que = new ArrayList<Question>();
    
    public static void main (String args[]) 
    {
    	semInfo = new SemanticInfo();
    	//con = semInfo.getContainer();
    	
    	/*for(Container c : con)
    	{
    		que.add(new Question(c));
    	}*/
    	
    }
}
