package project2.ver02;

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
			switch (scan.nextInt()) {
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
		}
	}
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		System.out.println("1.보통계좌  2.신용신뢰계좌");
		
		acc.myAccNum = new Random().nextInt(99998)+1;
		switch(scan.nextInt()) {
		case 1:
			System.out.println("고객이름: ");
			acc.owner = scan.next();
			System.out.println("기본이자%(정수만입력): ");
			
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
			System.out.println("신용등급(A,B,C등급): ");
			String grade = scan.next();
			
			if(rateVal==2)
				arrAcc[index] = 
						new NormalAccount(
								acc.myAccNum, acc.owner, rateVal, grade);
			if(rateVal==3)
				arrAcc[index] = 
				new HighCreditAccount(
						acc.myAccNum, acc.owner, rateVal, grade);
			arrAcc[index++].info();
			break;
		default :
			System.out.println("잘못선택하셨습니다."); return;
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
		for(Account a: arrAcc)//null포인트예외발생중
			if(a.myAccNum == accNum) { 
				existAcc = true;
				break; 
			}
			else {
				existAcc = false;
				index++;
			}
		
		if(existAcc) {
			int money;
			switch (in_out) {
			case "입금":
				System.out.println("입금액: ");
				money = scan.nextInt();
				if(money>0) {
					arrAcc[index].rateWithSave(money);
					System.out.println("입금되었습니다.");
				}
				else
					System.out.println("입금액이 0보다 작습니다");
				break;
			case "출금":
				System.out.println("출금액: ");
				money = scan.nextInt();
				//계좌가 있고 출금액이 잔고보다 <= 이면 출금진행
				if(money<=arrAcc[index].myMoney) {
					arrAcc[index].myMoney -= money;
					System.out.println("출금되었습니다.");
				}
				else
					System.out.println("잔고가 부족합니다.");
			}//switch
		}//if
		else
			System.out.println("해당계좌가 없습니다.");
	}
	
	
	














}
