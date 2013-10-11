import java.util.ArrayList;
import java.util.List;


public class SemanticInfo 
{
	private static Question.TypeOfSQuestion sQuesType;

	private static List<Container> returnCon = new ArrayList<Container>();
	
	public SemanticInfo()
	{
		sQuesType = Question.TypeOfSQuestion.values()[((int)(Math.random() * Question.TypeOfSQuestion.values().length))];
		//foreach query needed do:
		//Make query:
		
			/*Query query = QueryFactory.create(""+"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>"
		    		+"PREFIX owl: <http://www.w3.org/2002/07/owl#>"
		    			+"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
		    				+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
		    					+"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
		    						+"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
		    							+"PREFIX oddlinker: <http://data.linkedmdb.org/resource/oddlinker/>"
		    								+"PREFIX map: <file:/C:/d2r-server-0.4/mapping.n3#>"
		    									+"PREFIX db: <http://data.linkedmdb.org/resource/>"
		    										+"PREFIX dbpedia: <http://dbpedia.org/property/>"
		    											+"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
		    												+"PREFIX dc: <http://purl.org/dc/terms/>"
		    +"SELECT ? diruri WHERE{"+ "?diruri movie:director_name +\"Quentin Tarantino\" ."+"}");
		    
		    QueryExecution queryex = QueryExecutionFactory.create(query, dataset);
		    
		    ResultSet resultset = queryex.execSelect();
		    while(resultset.hasNext())
		    {
		    	System.out.println(resultset.next().toString());
		    }*/
		
		//Input info from query into new container:
		Container tCon = new Container();
		tCon.movieName = "this is an example";
		//add container to the return containers:
		returnCon.add(tCon);
		//foreach end
	}
	public Container[] getContainer()
	{
		return (Container[])returnCon.toArray();
	}
}
