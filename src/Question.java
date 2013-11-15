import java.util.HashMap;

public class Question {
	
	private String questionString;
	private String questionAnswer;
	private Container con;
	private int thisKey;
	private boolean done;
	private static int tOQKey = -1;
	private static HashMap<Integer, TypeOfQuestion> tOQMap = new HashMap<Integer, TypeOfQuestion>();
	
	public Question(Container con)
	{
		tOQKey++;
		thisKey = tOQKey;
		this.con = con;
		done = false;
		tOQMap.put(thisKey, TypeOfQuestion.values()[((int)(Math.random() * TypeOfQuestion.values().length))]);
		tOQMap.get(thisKey).setCon(con);
    	GenerateQuestion();
	}
	public void GenerateQuestion()
	{		
		String[] Preposition = new String[]{"which ", "who ", "what "};
		String[] Postposition = new String[]{"was ", "is "};
		String tempString = (tOQMap.get(thisKey).getValue().firstValue() > -1 ? Preposition[tOQMap.get(thisKey).getValue().firstValue()] : "")
				+ tOQMap.get(thisKey).getValue().toString() 
				+ (tOQMap.get(thisKey).getValue().secondValue() > -1 ? Postposition[tOQMap.get(thisKey).getValue().secondValue()] : "") 
				+ tOQMap.get(thisKey).getTypeString()
				+ (tOQMap.get(thisKey).getValue().thirdValue() != null ? tOQMap.get(thisKey).getValue().thirdValue() : "")
				+ "?";
		
		questionAnswer = tOQMap.get(thisKey).getValue().answerS();
		
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
		return tOQMap.get(thisKey);
	}
	public boolean getDone()
	{
		return done;
	}
	public void setDone(boolean setDone)
	{
		done = setDone;
	}
}
//Empty interface to signify that object is lower in enum tree
interface lowerTree{
	public int firstValue();
	public int secondValue();
	public String thirdValue();
	public String answerS();
	public void setFirstSecond(Container con);
}
enum TypeOfQuestion{
	MovieQuestion(){
		//Overrides return with relevant type and return cast
		@Override 
		public MovieQuestionType getValue(){
			return (MovieQuestionType)lowMap.get(thisLowKey);
		}
		@Override
		public String getTypeString(){
			return "the movie " + cont.movieName;
		}
	};
	private static int lowKey = -1;
	protected Container cont;
	protected int thisLowKey;
	protected static HashMap<Integer, lowerTree> lowMap = new HashMap<Integer, lowerTree>();
	private TypeOfQuestion(){
		setLowerTree();
	}
	private void setLowerTree()
	{
		TypeOfQuestion.lowKey++;
		thisLowKey = TypeOfQuestion.lowKey;
		lowMap.put(thisLowKey, MovieQuestionType.values()[((int)(Math.random() * MovieQuestionType.values().length))]);
		lowMap.get(thisLowKey).setFirstSecond(cont);
	}
    public lowerTree getValue(){
    	return null;
    }
    public String getTypeString(){
    	return null;
    }
    public void setCon(Container con)
    {
    	this.cont = con;
    }
}

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