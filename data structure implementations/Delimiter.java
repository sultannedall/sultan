import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Delimiter {

	public static void main(String[] args) {

		File file = new File ("test.txt" );
		String delimiter = " ";
		
		try { 
			Scanner scan= new Scanner (file) ;
			while ( scan.hasNext()) { 
			String line = scan.next();
			
			for ( int i=0; i<line.length() ; i++ ) { 
				char c = line.charAt(i);
				if ( c == '{' || c == '}' || c == '(' || c==')' || c == '[' || c ==']') { 
					delimiter += c;
				}
			}
			}
			
			System.out.println(delimiter);
		}
		catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
	}
	
	
	
	
}
