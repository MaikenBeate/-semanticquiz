public class Question {
	
	private String questionString;
	private String questionAnswer;
	private Container con;
	private TypeOfQuestion tOQ;
	public boolean done;
	
	public Question(Container con)
	{
		this.con = con;
		done = false;
		tOQ = TypeOfQuestion.values()[((int)(Math.random() * Question.TypeOfQuestion.values().length))];
		tOQ.setCon(con);
    	GenerateQuestion();
	}
	public void GenerateQuestion()
	{		
		String[] Preposition = new String[]{"which ", "who ", "what "};
		String[] Postposition = new String[]{"was ", "is "};
		String tempString = (tOQ.getValue().firstValue() > -1 ? Preposition[tOQ.getValue().firstValue()] : "")
				+ tOQ.getValue().toString() 
				+ (tOQ.getValue().secondValue() > -1 ? Postposition[tOQ.getValue().secondValue()] : "") 
				+ tOQ.getTypeString()
				+ (tOQ.getValue().thirdValue() != null ? tOQ.getValue().thirdValue() : "")
				+ "?";
		
		questionAnswer = tOQ.getValue().answerS();
		
		tempString = StringManipulation.AddSpacesToReplaceZero(StringManipulation.FirstToUpperCase(tempString), false);
		questionString = tempString;
	}
	
	//Enum returns enum returns random enum type of selected enum type
	public enum TypeOfQuestion{
		MovieQuestion(){
			//Overrides return with relevant type and return cast
			@Override 
			public MovieQuestionType getValue(){
				return (MovieQuestionType)this.value;
			}
			@Override
			public String getTypeString(){
				return "the movie " + cont.movieName;
			}
		};
		protected lowerTree value;
		protected Container cont;
		private TypeOfQuestion(){
			this.value = MovieQuestionType.values()[((int)(Math.random() * MovieQuestionType.values().length))];
			this.value.setFirstSecond(cont);
			
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
	
	public enum MovieQuestionType implements lowerTree{
			directed≈(){
				@Override
				public void setFirstSecond(Container con){first = 1; second = -1; third = null; ans = con.directorName;}
				}, 
				genre≈(){
					@Override
					public void setFirstSecond(Container con){first = 2; second = 1; third = null; ans = "erh... action, I guess..?";}
					}, 
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
}
//Empty interface to signify that object is lower in enum tree
interface lowerTree{
	public int firstValue();
	public int secondValue();
	public String thirdValue();
	public String answerS();
	public void setFirstSecond(Container con);
}