package project2.ver04;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NormalAccount extends Account implements Serializable{

	int rate;
	public NormalAccount(String myAccNum, String owner, int rate) {
		super(myAccNum,owner);
		this.rate = rate;
	}
	
	@Override
	public int rateWithSave(int saveMoney) {
		myMoney = (int)(myMoney
				+(myMoney*(rate/100))
				+saveMoney);
		return myMoney;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println("기본이자> "+rate+"%");
	}
}
