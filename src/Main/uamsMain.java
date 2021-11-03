package Main;

public class uamsMain {

	public static void main(String[] args) {
		
		try {
			
			signInStd signIn = new signInStd();
			signIn.setVisible(true);
			signIn.setResizable(false);
			signIn.setTitle("UAMS | Sign In");
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}

}
