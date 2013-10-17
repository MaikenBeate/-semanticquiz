import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class SemanticInfo 
{
	private static List<Question> returnQue = new ArrayList<Question>();
	
	public SemanticInfo()
	{
		//foreach query needed do:
		Container tCon = new Container();
		Question tempQ = new Question();
		tempQ.tOQ = Question.TypeOfQuestion.values()[((int)(Math.random() * Question.TypeOfQuestion.values().length))];
		
		//sQuesType = Question.StringQuestionType.Director.toString();
		//System.out.println(sQuesType);//Question.TypeOfQuestion.values()[((int)(Math.random() * Question.TypeOfQuestion.values().length))];
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
		    
		    QueryExecution queyex = QueryExecutionFactory.create(query, dataset);
		    
		    ResultSet resultset = queryex.execSelect();
		    while(resultset.hasNext())
		    {
		    	System.out.println(resultset.next().toString());
		    }*/
		
		//Input info from query into new container:
		
		//tCon.questionType = sQuesType;
		tCon.movieName = "this is an example";
		tCon.directorName = "Input director string";
		tCon.answers = new String[]{"Correct answer here","Wrong answer one string here", "Wrong answer two string here", "Wrong answer three string here"};
		tCon.releaseDate = "release date here";
		//tConmoviePosterImage;
		//add container to the return containers:
		tempQ.setContainer(tCon);
		returnQue.add(tempQ);
		//foreach end
	}
	public Question[] getQuestion()
	{
		return (Question[])returnQue.toArray();
	}
}
