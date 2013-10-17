
public class Question {
	
	private static String questionString;
	private static String questionAnswer;
	private static boolean answered;
	private static ImageQuestion iQ;
	private static StringQuestionType sQ;
	
	public TypeOfQuestion tOQ;
	private static Container con;
	
	public Question()
	{
		answered = false;
		//iQ = ImageQuestion.values()[((int)(Math.random() * ImageQuestion.values().length-1))];
		sQ = StringQuestionType.values()[((int)(Math.random() * StringQuestionType.values().length))];
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
	public enum StringQuestionType implements lowerTree{MovieName, Director, Genre, ReleaseYear}
	public enum ImageQuestion implements lowerTree{}
	//Enum returns enum returns random enum type of selected enum type
	public enum TypeOfQuestion{
		StringQuestionType(sQ){
			//Overrides return with relevant type and return cast
			@Override 
			public StringQuestionType getValue(){
				return (StringQuestionType)this.value;
			}
		},
		ImageQuestion(iQ){
			@Override 
			public ImageQuestion getValue(){
				return (ImageQuestion)this.value;
			}
		};
		protected lowerTree value;
		private TypeOfQuestion(lowerTree value){
			this.value = value;
	    }
	    public lowerTree getValue(){
	    	return null;
	    }
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
		return con.answers;
	}
	public Container getContainer()
	{
		return con;
	}
	public void setContainer(Container a)
	{
		con = a;
	}
}
//Empty interface to signify that object is lower in enum tree
interface lowerTree{}