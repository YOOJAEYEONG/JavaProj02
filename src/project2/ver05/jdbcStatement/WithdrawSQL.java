package project2.ver05.jdbcStatement;

import java.sql.SQLException;

import project2.ver05.jdbcConnect.IConnectImpl;


public class WithdrawSQL extends IConnectImpl {

	//쿼리작성 및 실행 메소드
	public void execute() {
		try {
			
			int num1, num2, affected; 
			String query;
			num1 = Integer.parseInt(scanValue("계좌번호: "));
			num2 = Integer.parseInt(scanValue("출금액: "));
			
			
			query = " UPDATE banking_tb SET \"잔액\" = \"잔액\"- "+num2
					+ " WHERE \"계좌번호\" = "+num1;
			
			psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();

			System.out.println("출금되었습니다.");
			
			
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			//자원반납
			close();
		}
	}
}
