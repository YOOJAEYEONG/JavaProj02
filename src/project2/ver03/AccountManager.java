package project2.ver03;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AccountManager implements MenuChoice{

	Scanner scan = new Scanner(System.in);
	
	Account[] arrAcc = new Account[50];
	Account acc = new Account();
	HighCreditAccount highAccnt = new HighCreditAccount();
	
	int index=0;
	
	
	
	public void showMenu() {
		
		while(true) {
			System.out.println("-----Menu-----\r" +
					"1.계 좌 개 설\r" + 
					"2.입	금\r" + 
					"3.출	금\r" + 
					"4.계좌정보출력\r" +
					"5.프로그램종료\r");
			try {
				int select = scan.nextInt();
				
				if(select <1 || 5<select) {
					throw new MenuSelectException();
				}
				switch (select) {
				case MAKE:
					makeAccount();
					break;
				case DEPOSIT:
					depositMoney();
					break;
				case WITHDRAW:
					withdrawMoney();
					break;
				case INQUIRE:
					showAccInfo();
					break;
				case EXIT:
					System.exit(0);
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해야합니다.");
				scan.nextLine();
			} catch (MenuSelectException e) {
				System.out.println("1~5번 메뉴중 선택하세요");
				//scan.next();
			} 
			
		
		
			
		}
	
	}
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		System.out.println("1.보통계좌  2.신용신뢰계좌");
		
		acc.myAccNum = (int)(Math.random()*100000);
		switch(scan.nextInt()) {
		case 1:
			System.out.println("고객이름: ");
			acc.owner = scan.next();
			
			arrAcc[index] = 
					new Account(
							acc.myAccNum, acc.owner);
			arrAcc[index++].info();
			break;
			
		case 2:
			System.out.println("고객이름: ");
			acc.owner = scan.next();
			System.out.println("기본이자%(정수만입력): ");
			int rateVal = scan.nextInt();
			if(rateVal==2)
				arrAcc[index] = 
						new NormalAccount(
								acc.myAccNum, acc.owner, rateVal);
			if(rateVal==3)
				arrAcc[index] = 
				new HighCreditAccount(
						acc.myAccNum, acc.owner, rateVal);
			arrAcc[index++].info();
			break;
		}
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
			if(a == null) { break; }
			a.info();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void inOutCal(String in_out) {
		System.out.println("계좌번호: "); 
		int index=0;
		int accNum = scan.nextInt();
		boolean existAcc = false;
		
		//계좌존재유무를 조회
		try {
			for(Account a: arrAcc)//null포인트예외발생중
				if(a.myAccNum==accNum) { 
					existAcc = true;
					break; 
				}
				else {
					existAcc = false;
					index++;
				}
		} catch (NullPointerException e) {
			System.out.println("해당계좌는 없습니다.");
		}
		
		if(existAcc) {
			int money;
			try {
				
				switch (in_out) {
				case "입금":
						System.out.println("입금액: ");
						money = scan.nextInt();
						if(money>0) {
							if(money%500!=0)
								System.out.println("500원단위로 입금 가능합니다.");
							else {
								arrAcc[index].rateWithSave(money);
								System.out.println("입금되었습니다.");
							}
						}
						else
							System.out.println("입금액이 0보다 작습니다");
					break;
				case "출금":
					System.out.println("출금액: ");
					money = scan.nextInt();
					if(money%1000!=0) {
						System.out.println("출금은 1000원 단위만 가능합니다.");
						break;
					}
					//계좌가 있고 출금액이 잔고보다 <= 이면 출금진행
					if(money<=arrAcc[index].myMoney) {
						arrAcc[index].myMoney -= money;
						System.out.println("출금되었습니다.");
					}
					else {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?\n"
								+ "YES	: 금액전체 출금처리\n"
								+ "NO	: 출금요청취소\n");
					
						String yesNo = scan.next();
						if("YES".equalsIgnoreCase(yesNo)) { 
							int sum=0; //출금은 1000원 단위로만 출금이 가능하도록 해야함
							sum = ((int)(arrAcc[index].myMoney/1000))*1000;
							arrAcc[index].myMoney -= sum;
							System.out.println(sum+"원이 출금되었습니다.");
							System.out.println("잔액: "+ arrAcc[index].myMoney);
						}
						else if("NO".equalsIgnoreCase(yesNo)) {
							System.out.println("출금을 취소합니다.");
							break;
						}
					}
				}//switch
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (InputMismatchException e) {//정수로입력해야하는데 문자입력시
				System.out.println("숫자를 입력하세요");
			}
		}
		
	}
	
}
