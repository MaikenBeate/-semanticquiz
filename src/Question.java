public class Question {
	private static String questionString;
	private static String questionAnswer;
	private static Container con;
	private static boolean answered;
	private static String[] answers;
	
	public Question(Container cont)
	{
		Question.con = cont;
	}
	public static void GenerateQuestion()
	{
		/*switch(con.questionType)
		{
		case String:
			//questionString = "What is the name of the movie directed by " + con.directorName + " and released in " + con.releaseDate;
			questionAnswer = con.movieName;
			break;
		case Image:
			questionString = "Who directed " + con.movieName;
			questionAnswer = con.directorName; 
			break;
		}*/
	}
	
	public String getQuestion()
	{
		return questionString;
	}
	public boolean getAnswered()
	{
		return answered;
	}
	public void setAnswered(boolean a)
	{
		answered = a;
	}
	public String getCorrectAnswer()
	{
		return questionAnswer;
	}
	public String[] getAllAnswers()
	{
		return answers;
	}
	public Container getContainer()
	{
		return con;
	}
	
	public enum TypeOfQuestion{StringQuestionType, ImageQuestion}
	public enum StringQuestionType{MovieName, Director, Genre, ReleaseYear}
	public enum ImageQuestion{}
	
}
