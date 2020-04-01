package project2.ver04;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements CustomSpecialRate, Serializable{ //계좌 정보를 표현
	
	int  myMoney;
	String owner, myAccNum;
	
	public Account() {	}
	public Account (String accNum, String who) {
		myAccNum = accNum;
		myMoney = 1000;
		owner = who;
	}
	
	
	public int rateWithSave(int saveMoney) {
		myMoney +=saveMoney;
		return myMoney;
	}


	public void info() {
		System.out.println("---------------");
		System.out.println("계좌번호> "+myAccNum);
		System.out.println("고객이름> "+owner);
		System.out.println("잔      고> "+myMoney);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myAccNum == null) ? 0 : myAccNum.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (obj == null)	return false;
		if (getClass() != obj.getClass())	return true;
		
		Account other = (Account) obj;
		if (myAccNum == null) 
			if (other.myAccNum != null)return false;
		else if (!myAccNum.equals(other.myAccNum))	return false;
		return true;
	}
}