
public class Question {
	private static String questionString;
	private static String questionAnswer;
	private static Container con;
	private static boolean answered;
	
	public Question(Container con)
	{
		this.con = con;
	}
	public String GenerateQuestion()
	{
		switch(con.questionType)
		{
		case MovieName:
			questionString = "What is the name of the movie directed by " + con.directorName + " and released in " + con.releaseDate;
			questionAnswer = con.movieName;
			break;
		case Director:
			questionString = "Who directed " + con.movieName;
			questionAnswer = con.directorName; 
			break;
		}
		
		return null;
	}
	
	public boolean getAnswered()
	{
		return answered;
	}
	
	public enum TypeOfSQuestion{MovieName, Director}
}
