/**
* StringManipulation - Statically called methods that add spaces (instead of the signifier Å) 
* and switches the first char in string to uppercase
*/
public class StringManipulation {

	public StringManipulation(){}
	
	public static String AddSpacesToReplaceZero(String s)
	{
		if(s == null){return "";}
		
		StringBuilder sB = new StringBuilder(s.length());
		for(int i = 0; i < s.length(); i++)
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
	public static String FirstToUpperCase(String s, boolean beginFromOne)
	{
		if(s == null){return "";}
		
		StringBuilder sB = new StringBuilder(s.length());
		sB.append(Character.toUpperCase(s.charAt((Integer)(beginFromOne == true ? 1 : 0))));
		for(int i = (Integer)(beginFromOne == true ? 2 : 1); i < s.length(); i++){sB.append(s.charAt(i));}
		
		return sB.toString();
	}
}