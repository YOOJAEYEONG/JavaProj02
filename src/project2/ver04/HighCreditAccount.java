package project2.ver04;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HighCreditAccount extends Account implements Serializable {

	String grade;
	int rate;
	public HighCreditAccount() {
		super();
	}


	public HighCreditAccount(String myAccNum, String owner, int rate, String grade) {
		super(myAccNum,owner);
		this.grade = grade; 
		myMoney += 1000;
		this.rate = rate;
	}
	

	@Override
	public int rateWithSave(int saveMoney) {
		double addRate = 0.0;
		if(grade.equals("A"))	addRate = A_GRADE_RATE;
		else if(grade.equals("B"))	addRate = B_GRADE_RATE;
		else if(grade.equals("C"))	addRate = C_GRADE_RATE;
		
		myMoney = (int) (myMoney
				+(myMoney*(rate/100))
				+(myMoney*addRate)
				+saveMoney);
		return myMoney;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println("기본이자> "+rate+"%");
		System.out.println("신용등급> "+grade);
	}
	
	
}
