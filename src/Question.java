public class Question {
	
	public static String questionString;
	public static String questionAnswer;
	public boolean answered;
	public static boolean done;
	public static TypeOfQuestion tOQ;
	public static Container con;
	
	public Question(Container con)
	{
		Question.con = con;
		answered = false;
		done = false;
		tOQ = TypeOfQuestion.values()[((int)(Math.random() * Question.TypeOfQuestion.values().length))];
    	GenerateQuestion();
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
				return "the movie " + con.movieName;
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
	
	public enum MovieQuestionType implements lowerTree{
			directed≈(){
				@Override
				public void setFirstSecond(){first = 1; second = -1; ans = con.directorName;}
				}, 
				genre≈(){
					@Override
					public void setFirstSecond(){first = 2; second = 1; ans = "erh... action, I guess..?";}
					}, 
					date≈(){
						@Override
						public void setFirstSecond(){first = 2; second = 0; ans = con.releaseDate;}
						};
		
		private static int first;
		private static int second;
		private static String ans;
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
		public String answerS(){
			return ans;
		}
	}
}
//Empty interface to signify that object is lower in enum tree
interface lowerTree{
	public int firstValue();
	public int secondValue();
	public String answerS();
	public void setFirstSecond();
}