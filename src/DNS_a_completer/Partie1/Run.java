package Partie1;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientInterface clientInterface = new ClientInterface();
		clientInterface.show();
		
		ServerInterface serverInterface = new ServerInterface();
		serverInterface.show();
	}

}
