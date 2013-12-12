/**
* Question - Chooses question type and generates a question based on the container class
*/
public class Question {
	
	private String questionString;
	private String questionAnswer;
	private Container con;
	private boolean done;
	private TypeOfQuestion tOQ;
	private lowerTree lT;
	
	/**
	* Question - Sets up the type of question
	*/
	public Question(Container con)
	{
		this.con = con;
		done = false;
		tOQ = TypeOfQuestion.values()[((int)(Math.random() * TypeOfQuestion.values().length))];
		tOQ.setLowerTree(con);
		lT = tOQ.getValue();
		lT.setFirstSecond(con);
    	GenerateQuestion();
	}
	/**
	* GenerateQuestion - Generates the question and it's answer based on the type of question and the local container
	*/
	public void GenerateQuestion()
	{		
		String[] Preposition = new String[]{"which ", "who ", "what "};
		String[] Postposition = new String[]{"was ", "is "};
		String tempString = (tOQ.getValue().firstValue() > -1 ? Preposition[tOQ.getValue().firstValue()] : "") /*adds preposition if applicable*/
				+ tOQ.getValue().toString() /*adds the lowerTree type of the question type*/
				+ (tOQ.getValue().secondValue() > -1 ? Postposition[tOQ.getValue().secondValue()] : "") /*adds postposition if applicable*/
				+ tOQ.getTypeString() /*adds question type string and the name of the type object*/
				+ (tOQ.getValue().thirdValue() != null ? tOQ.getValue().thirdValue() : "") /*adds postposition if applicable*/
				+ "?";
		//Gets correct answer associated to question lowerTree type
		questionAnswer = tOQ.getValue().answerS();
		
		tempString = StringManipulation.AddSpacesToReplaceZero(StringManipulation.FirstToUpperCase(tempString, false));
		questionString = tempString;
	}
	public String getQue()
	{
		return questionString;
	}
	public String getQueAns()
	{
		return questionAnswer;
	}
	public Container con()
	{
		return con;
	}
	public TypeOfQuestion tOQ()
	{
		return tOQ;
	}
	public boolean getDone()
	{
		return done;
	}
	public void setDone(boolean setDone)
	{
		done = setDone;
	}
	public lowerTree getLow()
	{
		lT.setFirstSecond(con);
		return lT;
	}
}
/**
* lowerTree - Interface to signify that object is lower in enum tree (lower question type)
*/
interface lowerTree{
	public int firstValue();
	public int secondValue();
	public String thirdValue();
	public String answerS();
	public void setFirstSecond(Container con);
}
/**
* TypeOfQuestion - Enum of top level question types
*/
enum TypeOfQuestion{
	MovieQuestion(){
		//Overrides return with relevant type and return cast
		@Override 
		public MovieQuestionType getValue(){
			return (MovieQuestionType)tree;
		}
		@Override
		public String getTypeString(){
			return "the movie " + cont.movieName;
		}
		@Override
		protected lowerTree getCorrectEnum()
		{
			return MovieQuestionType.values()[((int)(Math.random() * MovieQuestionType.values().length))];
	    }
	};
	protected Container cont;
	protected lowerTree tree;
	private TypeOfQuestion(){
	}
	protected lowerTree getCorrectEnum(){
    	return null;
    }
	public void setLowerTree(Container con)
	{
		cont = con;
		tree = getCorrectEnum();
	}
    public lowerTree getValue(){
    	return null;
    }
    public String getTypeString(){
    	return null;
    }
}
/**
* MovieQuestionType - Enum of movie specific question types, note lower tree
*/
enum MovieQuestionType implements lowerTree{
		directed≈(){
			@Override
			public void setFirstSecond(Container con){first = 1; second = -1; third = null; ans = con.directorName;}
			}, 
			/*genre≈(){
				@Override
				public void setFirstSecond(Container con){first = 2; second = 1; third = null; ans = "erh... action, I guess..?";}
				}, */
				date≈(){
					@Override
					public void setFirstSecond(Container con){first = 2; second = 0; third = " released"; ans = con.releaseDate;}
					};

	protected int first;
	protected int second;
	protected String third;
	protected String ans;
	MovieQuestionType(){}
	@Override
	public int firstValue(){
		return first;
	}
	@Override
	public int secondValue(){
		return second;
	}
	@Override
	public String thirdValue()
	{
		return third;
	}
	@Override
	public String answerS(){
		return ans;
	}
}