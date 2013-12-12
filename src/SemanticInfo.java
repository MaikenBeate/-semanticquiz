import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.PropertyConfigurator;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;

public class SemanticInfo 
{
	private static ArrayList<Question> returnQue = new ArrayList<Question>();
	private Model tdb;
	private static SemanticInfo semInf = null;
	
	public SemanticInfo()
	{
		
		PropertyConfigurator.configure("log4j.properties");

		String directory = "./tdb";
		Dataset dataset = TDBFactory.createDataset(directory);
		tdb = dataset.getDefaultModel();
		
		fetchEasyMovies();
		fetchMediumMovies();
		fetchDifficultMovies();
		
		//String source = "linkedmdb\\linkedmdb-latest-dump.nt";
		//FileManager.get().readModel(tdb, source, "N-TRIPLES");
		
		tdb.close() ;
	}
	
	/**
	 * Instantiates SemanticInfo
	 * @return SemanticInfo
	 */
	public static SemanticInfo instantiate()
	{
		if(semInf == null){
			semInf = new SemanticInfo();
		}
		
		return semInf;
	}	
	
	/**
	 * Returns the array of questions 
	 * @return SemanticInfo
	 */
	public Question[] getQuestion()
	{
		return returnQue.toArray(new Question[returnQue.size()]);
	}
	
	/**
	 * Gets information about difficult movies from DBpedia - movie-name and description
	 */
	private void fetchDifficultMovies(){
		
		String queryString = 
			  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+ "PREFIX dc: <http://purl.org/dc/terms/>"
			+ "PREFIX dbprop: <http://live.dbpedia.org/property/>"
			+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			+ "SELECT DISTINCT ?film_title ?description WHERE {" 
			+ "?film rdf:type <http://dbpedia.org/ontology/Film> .  " 
			+		"?film foaf:name ?film_title ."
			+       "?film dc:subject <http://dbpedia.org/resource/Category:English-language_films>  ."
			+		"?film rdfs:comment ?description ."
			+		"FILTER(langMatches(lang(?description),\"EN\")) "
			+ "} LIMIT 100";

	        Query query = QueryFactory.create(queryString);
	        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://live.dbpedia.org/sparql", query);
	        ResultSet resultset = qExe.execSelect();  
	        
	        HashMap<String,String> movieTitlesAndDescription = filterOutMovieTitles(resultset);
	        for(String movieTitle : movieTitlesAndDescription.keySet()){
	        	//the movie-information is used to get more required information from LinkedMDB
	        	fetchMovieInformationFromLinkedMDB(movieTitle, movieTitlesAndDescription.get(movieTitle), "hard");
	        }
	}

	/**
	 * Gets information about medium hard movies from DBpedia - movie-name and description
	 */
	private void fetchMediumMovies(){
			
			String queryString = 
				  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX dc: <http://purl.org/dc/terms/>"
				+ "PREFIX dbprop: <http://live.dbpedia.org/property/>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "SELECT DISTINCT ?film_title ?description WHERE {" 
				+ "?film rdf:type <http://dbpedia.org/ontology/Film> .  " 
				+		"OPTIONAL{?film <http://dbpedia.org/property/gross>  ?gross .} ."
				+		"?film foaf:name ?film_title ."
				+       "?film dc:subject <http://dbpedia.org/resource/Category:English-language_films> ."
				+		"?film rdfs:comment ?description ."
				+		"FILTER(langMatches(lang(?description),\"EN\") && (?gross > 100000000))"
				+ "} LIMIT 100";
	
		        Query query = QueryFactory.create(queryString);
		        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://live.dbpedia.org/sparql", query); 
		        ResultSet resultset = qExe.execSelect();

		        HashMap<String,String> movieTitlesAndDescription = filterOutMovieTitles(resultset);
		        for(String movieTitle : movieTitlesAndDescription.keySet()){
		        	//the movie-information is used to get more required information from LinkedMDB
		        	fetchMovieInformationFromLinkedMDB(movieTitle, movieTitlesAndDescription.get(movieTitle), "medium");
		        }
	}
	
	/**
	 * Gets information about easy movies from DBpedia, gets movie-name and description
	 */
	private void fetchEasyMovies(){
			
			String queryString = 
				  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX dc: <http://purl.org/dc/terms/>"
				+ "PREFIX dbprop: <http://live.dbpedia.org/property/>"
				+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "SELECT DISTINCT ?film_title ?description WHERE {" 
				+ "?film rdf:type <http://dbpedia.org/ontology/Film> .  " 
				+		"OPTIONAL{?film <http://dbpedia.org/property/gross>  ?gross .} ."
				+		"?film foaf:name ?film_title ."
				+ 		"?film <http://dbpedia.org/ontology/releaseDate> ?released ."
				+		"?film rdfs:comment ?description ."
				+		"?film dc:subject <http://dbpedia.org/resource/Category:American_films> ."
				+		"FILTER(langMatches(lang(?description),\"EN\") && (?released  >= xsd:dateTime(\"1990-01-01\")) && (?gross > 750000000))" //må fungere
				+ "} LIMIT 100";
	
		        Query query = QueryFactory.create(queryString);
		        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://live.dbpedia.org/sparql", query);
		        ResultSet resultset = qExe.execSelect();
		        
		        HashMap<String,String> movieTitlesAndDescription = filterOutMovieTitles(resultset);
		        for(String movieTitle : movieTitlesAndDescription.keySet()){
		        	//the movie-information is used to get more required information from LinkedMDB
		        	fetchMovieInformationFromLinkedMDB(movieTitle, movieTitlesAndDescription.get(movieTitle), "easy");
		        }
		}
	
	
	/**
	 * Queries LinkedMDB for information about a movie/movies with a specific movie tile, 
	 * saves the information and formulates a questions based on the movie information
	 * @param String movieTitle - a movie title
	 * @param String description - the movie description
	 * @param String difficultyLevel - relative to gross
	 */
	private void fetchMovieInformationFromLinkedMDB(String title, String description, String difficultyLevel){
			
			String queryString =
					  "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
					+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "PREFIX dc: <http://purl.org/dc/terms/>"
					+ "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>"
					+ "SELECT DISTINCT "
					+ "?movieuri ?title ?directorName ?date "
					+ "WHERE {"
					+ 		"?movieuri dc:title \"" + title + "\" ."
					+ 		"?movieuri movie:director ?directoruri ."
					+ 		"?directoruri movie:director_name ?directorName ."
					+ 		"?movieuri dc:date ?date ." 
					+ "} LIMIT 1";

	        Query query = QueryFactory.create(queryString);
	        QueryExecution qexec = QueryExecutionFactory.create(query, tdb) ;
	        ResultSet resultset = qexec.execSelect() ;

	        while(resultset.hasNext())
		    {	        	
	        	returnQue.add(makeQuestion(saveInformationInContainer(resultset.next().toString(), title, description, difficultyLevel)));
		    }
	              
		}
	
	/**
	 * Extracts movie titles from a result set of movies, and returns them in a hash map
	 * @param Resultset - result set
	 * @return HashMap<String, String> containing movie titles and movie descriptions
	 */
	private HashMap<String, String> filterOutMovieTitles(ResultSet resultset) {
		
		HashMap<String, String> movieTitlesAndDescription = new HashMap<String, String>();
		
		while(resultset.hasNext())
		{
			String movieResult = resultset.next().toString();
			String[] splitArray = movieResult.split("\"");
			String title = "";
			String description = "";

			for (int i = 0; i < splitArray.length; i++) {
				if (i == 1) {
					description = splitArray[i];
				}
				else if( i == 3){
					title = splitArray[i];
				}
			}
			
			//remove misformed information
			if(title.length() <= 35 && title.length() > 6 && !title.equals("(Untitled)")){
				movieTitlesAndDescription.put(title, description);
			}
			
		}
		
		return movieTitlesAndDescription;
	}
	
	
	/**
	 * Receives information about a movie and puts the information into a container, and
	 * then returns the container
	 * @param String queryAnswer
	 * @param String title
	 * @param String description
	 * @param String difficultyLevel - relative to gross
	 * @return Container container - the container with the information filled in
	 */
	private Container saveInformationInContainer(String queryAnswer, String title, String description, String difficultyLevel) {
		
		Container container = new Container();
		//This is a query result from queries to LinkedMDB containing director-name and releasedate
		String[] splitArray = queryAnswer.split("\"");
		
		System.out.println(title);
		container.movieName = title;
		container.description = description;
		container.difficultyLevel = difficultyLevel;
		
		for(int i = 0; i <splitArray.length; i++){
			//save director name
			if(i == 1){
				container.releaseDate = splitArray[i];
			}
			//save release date
			else if(i == 3){
				container.directorName = splitArray[i];
			}

		}
		return container;
	}
	
	/**
	 * Make question for a container of information
	 * @param Container - container of information
	 * @return Question - question based on the container-information
	 */
	private Question makeQuestion(Container container) {
		
		Question question = new Question(container);
		
		return question;
	}
		
}