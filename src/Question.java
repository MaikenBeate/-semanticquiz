
public class Question {
	
	private String questionString;
	private String questionAnswer;
	private Container con;
	private boolean done;
	private TypeOfQuestion tOQ;
	private lowerTree lT;
	
	/**
	 * Question - Individual question generation
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
	 * GenerateQuestion - Generates questions based on information from the container, enum tree and prepositions/postpositions
	 */
	public void GenerateQuestion()
	{	
		//sets preposition if applicable
		//gets and sets questions type specific value (director or date)
		//sets postposition if applicable
		//Associated value based on type of question (movie) + name of the value gotten from container
		//sets postposition if applicable
		
		String[] Preposition = new String[]{"which ", "who ", "what "};
		String[] Postposition = new String[]{"was ", "is "};
		String tempString = (tOQ.getValue().firstValue() > -1 ? Preposition[tOQ.getValue().firstValue()] : "") 
				+ tOQ.getValue().toString() 
				+ (tOQ.getValue().secondValue() > -1 ? Postposition[tOQ.getValue().secondValue()] : "") 
				+ tOQ.getTypeString() 
				+ (tOQ.getValue().thirdValue() != null ? tOQ.getValue().thirdValue() : "") 
				+ "?";
		
		questionAnswer = tOQ.getValue().answerS(); //sets answer as correct value from container based on question type
		
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
 * lowerTree - Empty interface to signify that object is lower in enum tree, note lowerTree can be used to signify multiple branches of the lower tree
 */
interface lowerTree{
	public int firstValue();
	public int secondValue();
	public String thirdValue();
	public String answerS();
	public void setFirstSecond(Container con);
}
/**
 * TypeOfQuestion - Determines and gets values associated with the top question type
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
 * Movie type question - Determines and gets values associated with specific sub type of question (note lower tree)
 */
enum MovieQuestionType implements lowerTree{
		directed≈(){
			@Override
			public void setFirstSecond(Container con){first = 1; second = -1; third = null; ans = con.directorName;}
			}, 
			/*genre≈(){
			 * //Removed due to unreliable data from linkedmdb
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