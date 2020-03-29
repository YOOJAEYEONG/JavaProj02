package project2.ver01;

import java.util.Random;
import java.util.Scanner;


public class Account implements MenuChoice{ //계좌 정보를 표현
	
	Scanner scan = new Scanner(System.in);
	
	
	Account[] arrAcc = new Account[50];
	int myaccNum, mymoney, index=0;
	String owner;
	
	
	
	public Account() {	}
	public Account (int accNum, String who, int money) {
		myaccNum = accNum;
		mymoney = money;
		owner = who;
	}
	
	
	public void showMenu() {
		System.out.println("-----Menu-----\r" +
				"1.계 좌 개 설\r" + 
				"2.입	금\r" + 
				"3.출	금\r" + 
				"4.계좌정보출력\r" +
				"5.프로그램종료");
	
		
		Account acc = new Account();
		Scanner scan = new Scanner(System.in);
		while(true) {
			
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
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		
		myaccNum = new Random().nextInt(99998)+1;
		System.out.println("고객이름: ");
		owner = scan.next();
		mymoney = 1000;
		System.out.println("고객이름: "+owner);
		System.out.println("계좌번호: "+myaccNum);
		System.out.println("잔      고: "+mymoney);
		
		arrAcc[index++] = new Account(myaccNum, owner, mymoney);
		System.out.println("계좌개설이 완료되었습니다.");
	}
	public void depositMoney() {
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		inOutCal("입금");
	}
	public void withdrawMoney() {
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		inOutCal("출금");
	}
	public void showAccInfo() {
		System.out.println("***계좌 정보출력***");
		for(Account a : arrAcc) {
			if(a == null) break;
			
			System.out.printf("----------\n"
					+ "계좌번호: %s\n고객이름: %s\n잔고: %d\n"
					,a.myaccNum,a.owner,a.mymoney);
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void inOutCal(String in_out) {
		System.out.println("계좌번호: "); 
		int index=0;
		int accNum = scan.nextInt();
		boolean existAcc = false;
		
		//계좌존재유무를 조회
		for( ; index <= arrAcc.length ; index++) 
			if(arrAcc[index].myaccNum == accNum) 
				{existAcc = true;	break; }
			else
				existAcc = false;
		
		if(existAcc) {
			int money;
			
			switch (in_out) {
			case "입금":
				System.out.println("입금액: ");
				money = scan.nextInt();
				if(money>0) {
					arrAcc[index].mymoney += money;
					System.out.println("입금되었습니다.");
				}
				else
					System.out.println("입금액이 0보다 작습니다");
				break;
			case "출금":
				System.out.println("출금액: ");
				money = scan.nextInt();
				//계좌가 있고 출금액이 잔고보다 <= 이면 출금진행
				if(money<=arrAcc[index].mymoney) {
					arrAcc[index].mymoney -= money;
					System.out.println("출금되었습니다.");
				}
				else
					System.out.println("잔고가 부족합니다.");
			}//switch
		}//if
		else
			System.out.println("해당계좌가 없습니다.");
			
		
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