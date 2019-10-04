import java.util.Random;

public class ttrr {

	public static void main(String[] args) {
		
		System.out.println(randomString());
	}

	
	public static String randomCountry () {
		
		String []arr = {"United States","Albania","Australia","Belgium","Canada","India","Germany"};
		Random r = new Random();
		
		int randomNumber = r.nextInt(arr.length);
		
		return (arr[randomNumber]);
		
	}
	
	public void randomStringFromArray () {
		String []arr = {"1","2","3","4","5","6"};
		Random r = new Random();
		
		int randomNumber = r.nextInt(arr.length);
		System.out.println(arr[randomNumber]);
	} 
	
	public static String randomString() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomString = "";
		int lenght = 10;

		Random rand = new Random();

		char[] text = new char[lenght];

		for (int i = 0; i < lenght; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}

		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		return randomString;
	}
}
