package project2.ver04;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HighCreditAccount extends Account implements Serializable {

	String grade;
	int Rate;
	public HighCreditAccount() {
		super();
	}


	public HighCreditAccount(String myAccNum, String owner, int Rate, String grade) {
		super(myAccNum,owner);
		this.grade = grade; 
		myMoney += 1000;
		this.Rate = Rate;
	}
	

	@Override
	public int rateWithSave(int saveMoney) {
		myMoney = (int) (myMoney
				+(myMoney*defaultRate)
				+(myMoney*B_GRADE_RATE)
				+saveMoney);
		return myMoney;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println("기본이자> "+Rate+"%");
		System.out.println("신용등급> "+grade);
	}
	
	
}
