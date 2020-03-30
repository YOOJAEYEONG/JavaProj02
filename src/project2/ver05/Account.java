package project2.ver05;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import project2.ver05.jdbcConnect.IConnectImpl;
import project2.ver05.jdbcStatement.DepositSQL;
import project2.ver05.jdbcStatement.InsertSQL;
import project2.ver05.jdbcStatement.ShowAllSQL;
import project2.ver05.jdbcStatement.WithdrawSQL;



public class Account implements MenuChoice{ //계좌 정보를 표현
	
	Scanner scan = new Scanner(System.in);
	IConnectImpl icoimpl = new IConnectImpl();
	
	public Account() {
		try {
			
			String sqlCreateTable = 
					" CREATE TABLE banking_tb( " + 
						"    \"계좌번호\" NUMBER(20) primary key, " + 
						"    \"이름\" NVARCHAR2(11), " + 
						"    \"잔액\" NUMBER(20) " +
						" )";
			icoimpl.stmt = icoimpl.con.createStatement();
			icoimpl.rs = icoimpl.stmt.executeQuery(sqlCreateTable);
			System.out.println("테이블생성됨");
			
			String sqlNewSequence = 
					"CREATE SEQUENCE seq_banking " + 
					"    increment by 1 " + 
					"    maxvalue 100 " + 
					"    minvalue 1 " + 
					"    nocycle " + 
					"    nocache ";
			icoimpl.stmt = icoimpl.con.createStatement();
			icoimpl.rs = icoimpl.stmt.executeQuery(sqlNewSequence);
			System.out.println("seq_banking 시퀀스 생성됨");
			
		} catch (SQLSyntaxErrorException e) {
			System.out.println("기존 테이블을 계속사용합니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void showMenu() {
	
		
		Scanner scan = new Scanner(System.in);
		
		
		try {
			while(true) {
				System.out.println("-----Menu-----\r" +
						"1.계 좌 개 설\r" + 
						"2.입	금\r" + 
						"3.출	금\r" + 
						"4.계좌정보출력\r" +
						"5.프로그램종료");
				
				switch (scan.nextInt()) {
				case MAKE:
					new InsertSQL().execute();	break;
				case DEPOSIT:
					new DepositSQL().execute();	break;
				case WITHDRAW:
					new WithdrawSQL().execute();break;
				case INQUIRE:
					new ShowAllSQL().execute();	break;
				case EXIT:
					icoimpl.close();
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}