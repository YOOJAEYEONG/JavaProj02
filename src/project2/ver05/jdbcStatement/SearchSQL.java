package project2.ver05.jdbcStatement;

import java.sql.SQLException;

import project2.ver05.jdbcConnect.IConnectImpl;


public class SearchSQL extends IConnectImpl{


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
			String query = "SELECT * FROM phonebook_tb"
					+ "	WHERE \"이름\" LIKE '"+ scanValue("찾는이름")+"' ";
					
			
			
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String name = rs.getString("이름");
				String phoneNumber = rs.getString("전화번호");
				String birthday = rs.getString("생일");
				
				System.out.printf("이름: %-10s 전화번호: %-15s 생일: %-10s \n",
						name, phoneNumber, birthday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
}
