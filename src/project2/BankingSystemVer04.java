package project2;


import project2.ver04.AccountManager;

public class BankingSystemVer04  {

	public static void main(String[] args) {

		
		AccountManager accMan = new AccountManager();
		accMan.loadFile();
		accMan.showMenu();
	}
	
	
}



