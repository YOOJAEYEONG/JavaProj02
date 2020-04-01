package project2.ver04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;




@SuppressWarnings("serial")
public class AccountManager implements MenuChoice, Serializable{

	Scanner scan = new Scanner(System.in);
	HashSet<Account> accountSet = new HashSet<Account>();
	
	
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
					saveFile();
					System.out.println(accountSet.size()
							+"개의 뱅킹DB을 저장했습니다\n프로그램을 종료합니다.");
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
		String owner, grade;
		String accountNum;
		int rateVal;
		int select = scan.nextInt();	scan.nextLine();
		switch(select) {
		case 1://일반계좌
			System.out.println("계좌번호: ");				
			accountNum = scan.next();	scan.nextLine();
			System.out.println("고객이름: ");				owner = scan.nextLine();
			System.out.println("기본이자%(정수만입력): ");	rateVal = scan.nextInt(); 
			scan.nextLine();
			
			saveCheckData(new NormalAccount(accountNum, owner, rateVal));
			
			break;
			
		case 2://신용계좌
			System.out.println("계좌번호: ");
			accountNum = scan.next();	scan.nextLine();
			System.out.println("고객이름: ");				owner = scan.nextLine();
			System.out.println("기본이자%(정수만입력): ");	rateVal = scan.nextInt();
			scan.nextLine();
			System.out.println("신용등급(A,B,C등급): ");	grade = scan.next();
			saveCheckData(new HighCreditAccount(accountNum, owner, rateVal, grade));
			
			break;
		default :
			System.out.println("잘못선택하셨습니다."); return;
		}
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
		Iterator<Account> itr = accountSet.iterator();
		while(itr.hasNext()) { 	itr.next().info();	}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void inOutCal(String in_out) {
		System.out.println("계좌번호: "); 
		String accNum = scan.next();
		boolean existAcc = false;
		Account myAccount = null;
		
		
		//계좌존재유무를 조회
		Iterator<Account> itr = accountSet.iterator();
		Iterator<Account> itrForInOut = accountSet.iterator();
		
		while(itr.hasNext()) {
			
			if(itr.next().myAccNum.equals(accNum)) { 
				existAcc = true;
				myAccount = itrForInOut.next();
				System.out.println("계좌를 찾았습니다.");
				break; 
			}
			else {
				myAccount = itrForInOut.next(); 
				existAcc = false;
			}
		}
		if(existAcc) {
			int money;
			try {
				switch (in_out) {
				case "입금":
						System.out.println("입금액: ");
						money = scan.nextInt();
						if(money>0) 
							if(money%500!=0)
								System.out.println("500원단위로 입금 가능합니다.");
							else {
								myAccount.rateWithSave(money);
								System.out.println("입금되었습니다.");
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
					
					if(money<=myAccount.myMoney) {
						myAccount.myMoney -= money;
						System.out.println("출금되었습니다.");
					}
					else {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?\n"
								+ "YES	: 금액전체 출금처리\n"
								+ "NO	: 출금요청취소\n");
					
						String yesNo = scan.next();
						if("YES".equalsIgnoreCase(yesNo)) { 
							int sum=0; //출금은 1000원 단위로만 출금이 가능하도록 해야함
							sum = ((int)(myAccount.myMoney/1000))*1000;
							
							myAccount.myMoney -= sum;
							
							System.out.println(sum+"원이 출금되었습니다.");
							System.out.println("잔액: "+ myAccount.myMoney);
						}
						else if("NO".equalsIgnoreCase(yesNo)) {
							System.out.println("출금을 취소합니다.");
							break;
						}
					}
				}//switch
			} catch (NumberFormatException e) {
				System.out.println("잘못입력하였습니다.");
			} catch (InputMismatchException e) {//정수로입력해야하는데 문자입력시
				System.out.println("숫자를 입력하세요");
			}
		}//if(existAcc)
		else System.out.println("해당계좌를 찾을 수없습니다.");
	}//inOutCal()
		
	public void saveCheckData(Account newAccount) {
		if(false == accountSet.add(newAccount)) {
			System.out.println(newAccount.myAccNum+
					" 의 중복된 계좌가 발견되었습니다. (덮어쓰기 1:예 / 2:아니오)");

			if(scan.nextInt()==1) {
				accountSet.remove(newAccount);
				accountSet.add(newAccount);
				newAccount.info();
				System.out.println("계좌생성이 완료되었습니다.");
			}
			else	System.out.println("취소되었습니다");
		}
		else	{
			newAccount.info();
			System.out.println("계좌생성이 완료되었습니다.");
		}
	}

	public void saveFile() {
		try {
			String src = 
					"C:/03WorkSpace/JavaProj02/src/project2/" +
					"ver04/AccountManager.obj";
			FileOutputStream fileOut = new FileOutputStream(src);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			
			objOut.writeObject(accountSet);
			objOut.close();
			fileOut.close();
			System.out.println("뱅킹 DB 저장완료");
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("IO에러발생");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void loadFile() {
		String src = 
				"C:/03WorkSpace/JavaProj02/src/" +
				"project2/ver04/AccountManager.obj";
		try {
			FileInputStream fileIn = new FileInputStream(src);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			accountSet = (HashSet<Account>)objIn.readObject();
			
			
			System.out.println("기존뱅킹 DB 로드완료");
			objIn.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("기존 데이터가 없습니다.");
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}


	public String scanMethod(String message) {
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		return scan.nextLine();
	}
}
