package com.pc.util; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
 
public class DBUtil {
	
	//����Դ����
	public static DataSource ds;
	//dbcp����Դ��������
	public static Properties dbcpProperties = new Properties();
	static{
		try {
			//���������ļ�
			dbcpProperties.load(DBUtil.class.getResourceAsStream("/dbcp.properties"));
			//ͨ��BasicDataSourceFactoryʵ��������Դ
			ds = BasicDataSourceFactory.createDataSource(dbcpProperties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ���Ӷ���
	 * @return
	 */
	public static Connection getConnect(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
/***
 * �ر����Ӷ���
 * @param rs
 * @param smt
 * @param con
 */
	public static void closeDB(ResultSet rs,Statement smt, Connection con){
		try {
			if(rs != null){
				rs.close();
			}
			if(smt != null){
				smt.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
