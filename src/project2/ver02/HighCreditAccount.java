package project2.ver02;

public class HighCreditAccount extends Account {

	String grade;

	public HighCreditAccount(int myAccNum, String owner) {
		super(myAccNum,owner);
		grade = "B"; 
	}
	

	@Override
	public int adeptRate(int saveMoney) {
		myMoney = (int) (myMoney
				+(myMoney*C_GRADE_RATE)
				+(myMoney*B_GRADE_RATE)
				+saveMoney);
		return myMoney;
	}
	
}
