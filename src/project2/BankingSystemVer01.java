package project2;

import java.util.Scanner;

import project2.ver01.Account;
import project2.ver01.MenuChoice;

public class BankingSystemVer01 implements MenuChoice {

	public static void main(String[] args) {

		
		banking();
		
	}
	public static void banking() {
		
		Account acc = new Account();
		Scanner scan = new Scanner(System.in);
		while(true) {
			acc.showMenu();
			switch (scan.nextInt()) {
			case MAKE:
				acc.makeAccount();
				break;
			case DEPOSIT:
				acc.depositMoney();
				break;
			case WITHDRAW:
				acc.withdrawMoney();
				break;
			case INQUIRE:
				acc.showAccInfo();
				break;
			case EXIT:
				System.exit(0);
				
			}
			
			
			
		}
		
	}
	
}



