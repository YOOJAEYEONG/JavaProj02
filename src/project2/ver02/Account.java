package project2.ver02;



public class Account implements CustomSpecialRate{ //계좌 정보를 표현
	
	int myAccNum, myMoney;
	String owner;
	
	public Account() {	}
	public Account (int accNum, String who) {
		myAccNum = accNum;
		myMoney = 1000;
		owner = who;
	}
	
	
	public int adeptRate(int saveMoney) {
		myMoney = myMoney+saveMoney;
		return myMoney;
	}
}