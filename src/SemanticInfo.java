import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SemanticInfo 
{
	private static List<Question> returnQue = new ArrayList<Question>();
	private static ArrayList<Container> containers = new ArrayList<Container>();
	
	public SemanticInfo()
	{
		PropertyConfigurator.configure("log4j.properties");
		//fetchMovieInformation();
	}
	
	
	/*Prefixes - temporary
	 * 
	 * "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>"
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
    												+"PREFIX dc: <http://purl.org/dc/terms/>"*/

	
	
	//John sin oppgave
	private void makeQuestion(Container container) {
		
		Question question = new Question();
		question.tOQ = Question.TypeOfQuestion.values()[((int)(Math.random() * Question.TypeOfQuestion.values().length))];
		//sQuesType = Question.StringQuestionType.Director.toString();
		//System.out.println(sQuesType);//Question.TypeOfQuestion.values()[((int)(Math.random() * Question.TypeOfQuestion.values().length))];
		
		//container.questionType = sQuesType;
		//container.answers = new String[]{"Correct answer here","Wrong answer one string here", "Wrong answer two string here", "Wrong answer three string here"};
		//containerMoviePosterImage;
		
		//add container to the return containers:
		//tempQ.setContainer(container);
		//returnQue.add(tempQ);

	}
	
	
	/**
	 * Gets movies with low gross - difficult movies
	 * @author Maiken Beate
	 */
	private void getMoviesWithLowGross(){
		
		String queryString = 
			  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+ "PREFIX dc: <http://purl.org/dc/terms/>"
			+ "PREFIX dbprop: <http://live.dbpedia.org/property/>"
			+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			+ "SELECT DISTINCT ?film_title ?gross WHERE {" 
			+ "?film rdf:type <http://dbpedia.org/ontology/Film> .  " 
			+		 "OPTIONAL{?film <http://dbpedia.org/property/gross>  ?gross .} ."
			+		" ?film foaf:name ?film_title ."
			+		 "FILTER (?gross < 100000000)"
			+ "} LIMIT 10";

	        Query query = QueryFactory.create(queryString);
	        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
	        
	        ResultSet resultset = qExe.execSelect();  
	        
	        ArrayList<String> movieTitlesWithLowGross = filterOutMovieTitles(resultset);
	        for(String movieTitle : movieTitlesWithLowGross){
	        	fetchMovieInformationFromLinkedMDB(movieTitle);
	        }
	}

	
	/**
	 * Gets movies with medium gross - medium difficult movies
	 * @author Maiken Beate
	 */
	private void getMoviesWithMediumGross(){
			
			String queryString = 
				  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX dc: <http://purl.org/dc/terms/>"
				+ "PREFIX dbprop: <http://live.dbpedia.org/property/>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "SELECT DISTINCT ?film_title ?gross WHERE {" 
				+ "?film rdf:type <http://dbpedia.org/ontology/Film> .  " 
				+		 "OPTIONAL{?film <http://dbpedia.org/property/gross>  ?gross .} ."
				+		" ?film foaf:name ?film_title ."
				+		 "FILTER ((?gross > 100000000) && (?gross < 750000000))"
				+ "} ";
	
		        Query query = QueryFactory.create(queryString);
		        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		        
		        ResultSet resultset = qExe.execSelect();
		        ResultSetFormatter.out(System.out, resultset, query) ;    
		}
	
	
	/**
	 * Gets movies with high gross - easy movies
	 * @author Maiken Beate
	 */
	private void getMoviesWithHighGross(){
			
			String queryString = 
				  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX dc: <http://purl.org/dc/terms/>"
				+ "PREFIX dbprop: <http://live.dbpedia.org/property/>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "SELECT DISTINCT ?film_title ?gross WHERE {" 
				+ "?film rdf:type <http://dbpedia.org/ontology/Film> .  " 
				+		 "OPTIONAL{?film <http://dbpedia.org/property/gross>  ?gross .} ."
				+		" ?film foaf:name ?film_title ."
				+		 "FILTER (?gross > 750000000)"
				+ "} ";
	
		        Query query = QueryFactory.create(queryString);
		        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		        
		        ResultSet resultset = qExe.execSelect();
		        ResultSetFormatter.out(System.out, resultset, query) ;    
		}
	
	
	/**
	 * Queries LinkedMDB for information about a movie/movies with a specific movie tile, 
	 * saves the information and formulates a questions based on the movie information
	 * @param String movieTitle - a movie title
	 * @author Maiken Beate
	 */
	private void fetchMovieInformationFromLinkedMDB(String movieTitle){
			
			String queryString =
					  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "PREFIX dc: <http://purl.org/dc/terms/>"
					+ "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>"
					+ "SELECT ?movieuri ?title ?directorName ?date "
					+ "WHERE {"
					+ 		"?movieuri dc:title \"" + movieTitle + "\" ."
					+ 		"?movieuri movie:director ?directoruri ."
					+ 		"?directoruri movie:director_name ?directorName ."
					+ 		"?movieuri dc:date ?date " 
					+ "} ";
	
	        Query query = QueryFactory.create(queryString);
	        QueryExecution qExe = QueryExecutionFactory.sparqlService( "http://data.linkedmdb.org/sparql", query );
	        ResultSet resultset = qExe.execSelect();
	        
	        //Making a Container and formulating a question for each movie
	        while(resultset.hasNext())
		    {
	        	//System.out.println(resultset.next().toString());
	        	Container container = saveInformationInContainer(resultset.next().toString(), movieTitle);
	        	containers.add(container);
	        	
	        	makeQuestion(container);
		    }
	              
		}
	
	
	/**
	 * Extracts movietitles from a resultset of movies, and returns them in an arraylist
	 * @param Resultset - resultset
	 * @return ArrayList<String> movietitles
	 * @author Maiken Beate
	 */
	private ArrayList<String> filterOutMovieTitles(ResultSet resultset) {
		
		ArrayList<String> movieTitles = new ArrayList<String>();
		
		while(resultset.hasNext())
		{
			String movieResult = resultset.next().toString();
			String[] splitArray = movieResult.split("\"");
			
			for(int i = 0; i < splitArray.length; i++){
				//save movie title
				if(i == 1){
					movieTitles.add(splitArray[i]);
				}
			}
			
		}
		
		return movieTitles;
	}
	
	
	/**
	 * Gets a string of infomation about a movie, puts the information into a Container
	 * @param String queryAnswer
	 * @return Container container - the container with the information filled in
	 * @author Maiken Beate
	 */
	private Container saveInformationInContainer(String queryAnswer, String movieTitle) {
		
		Container container = new Container();
		String[] splitArray = queryAnswer.split("\"");
		
		//save movie title
		container.movieName = movieTitle;
		
		for(int i = 0; i <splitArray.length; i++){
			//save director name
			if(i == 1){
				container.directorName = splitArray[i];
			}
			//save release date
			else if(i == 3){
				container.releaseDate = splitArray[i];
			}

		}
		return container;
	}
	
	
	public Question[] getQuestion()
	{
		return (Question[])returnQue.toArray();
	}
	
	
	//temporary
	public static void main(String[] args) {
		
		SemanticInfo sem = new SemanticInfo();
		sem.getMoviesWithLowGross();
		 //temporary test
        for(Container container : containers){
        	System.out.println("Movie title: " + container.movieName + ", Director: " + container.directorName + ", Date: " + container.releaseDate);
        }
	}
}