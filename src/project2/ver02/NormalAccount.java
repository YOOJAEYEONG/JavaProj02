package project2.ver02;

public class NormalAccount extends Account{

	String grade;
	double rate;///??
	public NormalAccount(int myAccNum, String owner) {
		super(myAccNum,owner);
		grade = "C"; 
	}
	
	@Override
	public int adeptRate(int saveMoney) {
		myMoney = (int) (myMoney
				+(myMoney*C_GRADE_RATE)
				+saveMoney);
		return myMoney;
	}
}
