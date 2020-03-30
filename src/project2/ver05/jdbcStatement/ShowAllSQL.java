package project2.ver05.jdbcStatement;

import java.sql.SQLException;

import project2.ver05.jdbcConnect.IConnectImpl;


public class ShowAllSQL extends IConnectImpl{


	
	/*
	ResultSet 클래스
	 : select문 실행시 쿼리의 실행결과가 ResultSet객체에 저장된다.
	 	결과로 반환된 레코드의 처음부터 마지막까지 next()메소드를 통해
	 	확인후 반복하면서 추출하게 된다.
	 	
	 	-getXXX() 계열의 메소드
	 	오라클의 자료형이
	 	number타입 : getInt()
	 	char/varchar2타입 : getString()
	 	date타입 : getDate()
	 	메소드로 각 컬럼의 데이터를 추출한다.
	 	-인자는 select절의 컬럼순서대로 인덱스(1부터시작)를 사용하거나
	 	컬럼명을 사용할 수 있다.
	 	***자료형에 상관없이 getString()으로 모든 데이터를 추출할 수 있다.
	 		(출력할때 결국 문자열로 출력하기 때문임)
	 */
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
				/*
				오라클의 date타입을 getDate()로 추출하면 2020-03-15
				포맷으로 출력된다. 이경우 date형 자료가 되기때문에
				java.sql.Date클래스의 참조변수로 저장해야한다.
				 */

				System.out.printf("계좌번호: %-10d 이름: %-10s 잔액: %-10d \n",
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
