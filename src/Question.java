
public class Question {
	//lag flere spørsmål
	public static String questionString;
	public static String questionAnswer;
	public static Container con;
	
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
	public enum TypeOfSQuestion{MovieName, Director}
}
