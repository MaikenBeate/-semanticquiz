public class Question {
	
	private static String questionString;
	private static String questionAnswer;
	private static boolean answered;
	private static MovieQuestionType sQ;
	public static TypeOfQuestion tOQ;
	private static Container con;
	
	public Question()
	{
		answered = false;
		//iQ = MovieImageQuestion.values()[((int)(Math.random() * MovieImageQuestion.values().length))];
		sQ = MovieQuestionType.values()[((int)(Math.random() * MovieQuestionType.values().length))];
		
	}
	public static void GenerateQuestion()
	{		
		String returnString = First.values()[tOQ.getValue().firstValue()].toString() 
				+ tOQ.getValue().toString() 
				+ (tOQ.getValue().secondValue() != 0? Second.values()[tOQ.getValue().secondValue()].toString() : "");
	}
	
	//Enum returns enum returns random enum type of selected enum type
	public enum TypeOfQuestion{
		MovieQuestion(sQ){
			//Overrides return with relevant type and return cast
			@Override 
			public MovieQuestionType getValue(){
				return (MovieQuestionType)this.value;
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
	/*public enum PrepositionalStatements{Which(0) movie was(0) released in releaseDate, Who(1) directed moviename
		
	}*/
	
	public enum MovieQuestionType implements lowerTree{
		movie(){
			@Override
			protected void setFirstSecond(){first = 1; second = 1;}
			}, 
			directed(){
				@Override
				protected void setFirstSecond(){first = 2; second = 0;}
				}, 
				≈genre≈(){
					@Override
					protected void setFirstSecond(){first = 0; second = 0;}
					}, 
					≈released≈in≈(){
						@Override
						protected void setFirstSecond(){first = 0; second = 0;}
						};
		
		private static int first;
		private static int second;
		MovieQuestionType(){}
		@Override
		public int firstValue() {
			return first;
		}
		@Override
		public int secondValue(){
			return second;
		}
		
		protected void setFirstSecond(){}
	}
	public enum First{≈, ≈which≈, ≈who≈, ≈what≈}
	public enum Second{≈, ≈was≈, ≈is≈}
	
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
interface lowerTree{
	public int firstValue();
	public int secondValue();
}