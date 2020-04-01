package project2.ver05.jdbcStatement;

import java.sql.SQLException;

import project2.ver05.jdbcConnect.IConnectImpl;


public class ShowAllSQL extends IConnectImpl{

	@Override
	public void execute() {
		try {
			String query = " SELECT * FROM banking_tb ";
			
			
			psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();

            
			while(rs.next()) {
				int accNum = rs.getInt("계좌번호");
				String owner = rs.getString("이름");
				int myMoney = rs.getInt("잔액");		

				System.out.printf(
						"계좌번호: %-10d 이름: %-10s 잔액: %-10d \n",
						accNum, owner, myMoney);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
}
