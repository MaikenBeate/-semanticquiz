<<<<<<< HEAD

public class StringManipulation {

	public StringManipulation(){}
	
	public static String AddSpacesToReplaceZero(String s, boolean beginFromOne)
	{
		if(s == null){return "";}
		
		StringBuilder sB = new StringBuilder(s.length());
		for(int i = (Integer)(beginFromOne == true ? 1 : 0); i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(c == 'Å')
			{
				c = ' ';
			}
			
			sB.append(c);
		}
		
		return sB.toString();
	}
	public static String FirstToUpperCase(String s)
	{
		if(s == null){return "";}
		
		StringBuilder sB = new StringBuilder(s.length());
		sB.append(Character.toUpperCase(s.charAt(0)));
		for(int i = 1; i < s.length(); i++){sB.append(s.charAt(i));}
		
		return sB.toString();
	}
}
=======

public class StringManipulation {

	public StringManipulation(){}
	
	public static String AddSpacesToReplaceZero(String s, boolean beginFromOne)
	{
		if(s == null){return "";}
		
		StringBuilder sB = new StringBuilder(s.length());
		for(int i = (Integer)(beginFromOne == true ? 1 : 0); i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(c == 'Å')
			{
				c = ' ';
			}
			
			sB.append(c);
		}
		
		return sB.toString();
	}
	public static String FirstToUpperCase(String s)
	{
		if(s == null){return "";}
		
		StringBuilder sB = new StringBuilder(s.length());
		sB.append(Character.toUpperCase(s.charAt(0)));
		for(int i = 1; i < s.length(); i++){sB.append(s.charAt(i));}
		
		return sB.toString();
	}
}
>>>>>>> eb696f04ae77797e9bcb9f82be31f712a6af2d32
