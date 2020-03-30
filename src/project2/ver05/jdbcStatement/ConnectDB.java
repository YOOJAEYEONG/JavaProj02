package project2.ver05.jdbcStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//객체 생성의 목적이 아닌 상속의 목적으로 정의된 추상 클래스
public abstract class ConnectDB {

	/*
	맴버변수 : 상속받은 하위클래스에서 접근을 허용하기 위해
		접근지정자는 protected로 선언함
	 */
	protected Connection con;
	protected Statement stmt;
	protected ResultSet rs;
	
	
	//UpdateSQL에서 사용할 인자생성자
	public ConnectDB(String id, String pw) {
		try {
			//드라이버로드
			Class.forName("oracle.jdbc.OracleDriver");
			//커넥션(매개변수로 전달된 id, pw를 통해 연결
			con = DriverManager.getConnection(
					"jdbc:oracle:thin://@localhost:1521:orcl", id, pw);
			System.out.println("오라클 DB 연결성공");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결실패");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수없는 예외");
		}
	}
	public ConnectDB() {
		try {
			//1.오라클 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			//2.커넥션 객체를 통해 연결
			con = DriverManager.getConnection(
					"jdbc:oracle:thin://@localhost:1521:orcl",
					"scott","1234"
					);
			System.out.println("오라클 DB연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수없는 예외");
		}
	}
	public void close() {
		try {
			if(stmt!=null)	stmt.close();
			if(con!=null)	con.close();
			if(rs!=null)	rs.close();
			System.out.println("DB자원반납완료");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	/*
	오버라이딩을 목적으로 정의한 추상메소드
	 */
	abstract void execute();
}





























