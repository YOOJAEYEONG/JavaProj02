package project2.ver01;

import java.util.Random;
import java.util.Scanner;

public class Account{ //계좌 정보를 표현
	
	Scanner scan = new Scanner(System.in);
	
	
	Account[] arrAcc = new Account[50];
	int accNumber, money, index=0;
	String owner;
	
	
	
	public Account (int _accNumber, String _owner, int _money) {
		accNumber = _accNumber;
		money = _money;
		owner = _owner;
	}
	
	
	
	public Account() {
		// TODO Auto-generated constructor stub
	}



	public void showMenu() {
		System.out.println("-----Menu-----\r"
				+ "1.계 좌 개 설\r" + 
				"2.입	금\r" + 
				"3.출	금\r" + 
				"4.계좌정보출력\r");
	}
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		
		accNumber = new Random().nextInt(99998)+1;
		System.out.println("고객이름: ");
		owner = scan.next();
		money = 1000;
		System.out.println("고객이름: "+owner);
		System.out.println("계좌번호: "+accNumber);
		System.out.println("잔      고: "+money);
		
		arrAcc[index++] = new Account(accNumber, owner, money);
		System.out.println("계좌개설이 완료되었습니다.");
	}
	public void depositMoney() {
		
	}
	public void withdrawMoney() {
		
	}
	public void showAccInfo() {
		System.out.println("***계좌 정보 출력***");
		for(Account a : arrAcc) {
			if(a == null) break;
			System.out.printf("----------\n"
					+ "계좌번호: %s\n고객이름: %s\n잔고: %d"
					,a.accNumber,a.owner,a.money);
		}
	}
	public class Scan<ScType>{
		ScType scVal;
		public ScType scanMethod(String s){
			Scanner scan = new Scanner(System.in);
			ScType scVal = (ScType) scan.next();
			return  scVal;
		}
	}
	
}