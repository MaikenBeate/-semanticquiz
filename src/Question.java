public class Question {
	
	public static String questionString;
	public static String questionAnswer;
	public static boolean answered, done;
	public static TypeOfQuestion tOQ;
	public static Container con;
	
	public static void main (String args[]) 
    {
		tOQ = TypeOfQuestion.values()[((int)(Math.random() * Question.TypeOfQuestion.values().length))];
    	GenerateQuestion();
    	System.out.println(tOQ.getValue());
    	System.out.println(questionString);
    }
	public Question()
	{
		answered = false;
	}
	public static void GenerateQuestion()
	{		
		String[] Preposition = new String[]{"which ", "who ", "what "};
		String[] Postposition = new String[]{"was ", "is "};
		String tempString = (tOQ.getValue().firstValue() > -1 ? Preposition[tOQ.getValue().firstValue()] : "")
				+ tOQ.getValue().toString() 
				+ (tOQ.getValue().secondValue() > -1 ? Postposition[tOQ.getValue().secondValue()] : "") 
				+ tOQ.getTypeString() 
				+ "?";
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
				return "the movie ";
			}
		};
		protected lowerTree value;
		private TypeOfQuestion(){
			this.value = MovieQuestionType.values()[((int)(Math.random() * MovieQuestionType.values().length))];
			this.value.setFirstSecond();
		}
	    public lowerTree getValue(){
	    	return null;
	    }
	    public String getTypeString(){
	    	return null;
	    }
	}
	/*public enum PrepositionalStatements{Which(0) movie was(0) released in releaseDate, Who(1) directed moviename*/
	/*String[] First = new String[]{" ", " which ", " who ", " what "};
	String[] Second = new String[]{" ", " was ", " is "};*/
	
	public enum MovieQuestionType implements lowerTree{
			directed≈(){
				@Override
				public void setFirstSecond(){first = 1; second = -1;}
				}, 
				genre≈(){
					@Override
					public void setFirstSecond(){first = 2; second = 1;}
					}, 
					date≈(){
						@Override
						public void setFirstSecond(){first = 2; second = 0;}
						};
		
		private static int first;
		private static int second;
		MovieQuestionType(){}
		@Override
		public int firstValue(){
			return first;
		}
		@Override
		public int secondValue(){
			return second;
		}
	}
	
	/*public String getQuestion()
	{
		return questionString;
	}
	public String getCorrectAnswer()
	{
		return questionAnswer;
	}
	public boolean getAnswered()
	{
		return answered;
	}
	public void setAnswered(boolean a)
	{
		answered = a;
	}
	public boolean getDone()
	{
		return done;
	}
	public void setDone(boolean d)
	{
		done = d;
	}
	public String[] getAllAnswers()
	{
		return con.answers;
	}
	public Container getContainer()
	{
		return con;
	}
	public void setContainer(Container c)
	{
		con = c;
	}*/
}
//Empty interface to signify that object is lower in enum tree
interface lowerTree{
	public int firstValue();
	public int secondValue();
	public void setFirstSecond();
}