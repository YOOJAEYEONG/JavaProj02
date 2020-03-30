package project2.ver05.jdbcStatement;

import project2.ver05.jdbcConnect.IConnectImpl;

public class DeleteSQL extends IConnectImpl{

	@Override
	public void execute() {
		try {
			//1.DB연결 부모생성자호출시 자동연결됨
			//2.쿼리문 준비
			String query = "DELETE FROM phonebook_tb WHERE \"이름\" = ?";
			//3.prepared 객체생성
			psmt = con.prepareStatement(query);
			//4. 인파라미터 값 설정
			psmt.setString(1, scanValue("삭제할아이디"));
			//5.쿼리실행 후 결과값 반환
			System.out.println(psmt.executeUpdate()!= 0 ?"삭제되었습니다." : "삭제할 데이터가 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
}

























