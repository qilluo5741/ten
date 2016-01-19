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
	
	//数据源对象
	public static DataSource ds;
	//dbcp数据源配置属性
	public static Properties dbcpProperties = new Properties();
	static{
		try {
			//加载配置文件
			dbcpProperties.load(DBUtil.class.getResourceAsStream("/dbcp.properties"));
			//通过BasicDataSourceFactory实例化数据源
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
	 * 获取链接对象
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
 * 关闭链接对象
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
