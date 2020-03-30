package project2.ver03;



public class Account implements CustomSpecialRate{ //계좌 정보를 표현
	
	int myMoney;
	String owner, myAccNum;
	double defaultRate = 0.02;
	
	public Account() {	}
	public Account (String accNum, String who) {
		myAccNum = accNum;
		myMoney = 1000;
		owner = who;
	}
	
	
	public int rateWithSave(int saveMoney) {
		myMoney = (int)(myMoney+myMoney*defaultRate+saveMoney);
		return myMoney;
	}


	public void info() {
		System.out.println("---------------");
		System.out.println("계좌번호> "+myAccNum);
		System.out.println("고객이름> "+owner);
		System.out.println("잔      고> "+myMoney);
	}


}