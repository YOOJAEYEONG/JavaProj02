package project2.ver05.jdbcStatement;

import java.sql.SQLException;

import project2.ver05.jdbcConnect.IConnectImpl;


public class WithdrawSQL extends IConnectImpl {

	//쿼리작성 및 실행 메소드
	public void execute() {
		try {
			
			int number, affected; 
			String query;
			number = Integer.parseInt(scanValue("계좌번호: "));
			
			query = " SELECT \"계좌번호\" FROM banking_tb "
					+ " WHERE \"계좌번호\" LIKE "+number+" ";
			if(1==psmt.executeUpdate()) {
				number = Integer.parseInt(scanValue("출금액: "));
				if
				psmt = con.prepareStatement(query);
	            rs = psmt.executeQuery();

				
				
				
				
				query = " UPDATE banking_tb SET \"잔액\" = \"잔액\"+ "+number
						+ " WHERE \"계좌번호\" = "+number;
				affected = psmt.executeUpdate();
				System.out.println(affected+"행이 입력되었습니다.");
			}
			else {
				System.out.println("해당계좌가 없습니다.");
			}
			
			
			
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			//자원반납
			close();
		}
	}
}
