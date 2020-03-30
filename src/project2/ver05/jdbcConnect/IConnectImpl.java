package project2.ver05.jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//인터페이스를 구현하는 클래스는 이름선언시 뒤에 Impl을 붙여주는것이 명시적으로 좋다.
public class IConnectImpl implements IConnect {

	//동적쿼리를 위한 객체
	public Connection con;
	public PreparedStatement psmt;//(dataInput() 이용
	public ResultSet rs;
	public Statement stmt;
	
	
	public IConnectImpl() {
		
		try {
			//드라이버 로드
			Class.forName(ORACLE_DRIVER);
			//DB연결
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	@Override
	public void connect() {

		try {
			con = DriverManager.getConnection(ORACLE_URL, ID, PASS);
			if(con!=null)	System.out.println("DB연결됨");
		} catch (SQLException e) {
			System.out.println("데이터페이스 연결 오류");
			e.printStackTrace();
		}		
	}

	//오버라이딩의 목적으로 정의한 메소드. 쿼리실행은 각 클래스에서 함.
	@Override
	public void execute() { /* 하는일은 없음 */	}

	@Override
	public void close() {
		try {
			if(con!=null)	con.close();
			if(psmt!=null)	psmt.close();
			if(rs!=null)	rs.close();
			if(stmt!=null)	stmt.close();
			System.out.println("자원반납완료");
		} catch (Exception e) {
			System.out.println("자원반납시에러");
			e.printStackTrace();
		}
		
	}

	//사용자로부터 입력값을 받기 위한 메소드
	@Override
	public String scanValue(String title) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println(title + "을 입력(exit->종료):");
		String inputStr = scan.nextLine();
		
		/*
		A.equalsIgnoreCase(B) 
			: equals()메소드와 동일하게 문자열이 같은지 비교하는 메소드로 
			다른점은 대소문자를 구분하지 않고 비교한다.
		 */
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			close();
			//프로그램 자체가 즉시 종료된다.
			System.exit(0);
		}
		return inputStr;
	}
}


















