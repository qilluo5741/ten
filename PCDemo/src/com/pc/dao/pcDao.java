package com.pc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pc.util.DBUtil;

public class pcDao {
	/**
	 * 添加类型
	 * @param typeId
	 * @param typeName
	 */
	public static void addType(int typeId,String typeName){
		Connection con=DBUtil.getConnect();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement("insert into type values(?,?)");
			ps.setInt(1, typeId);
			ps.setString(2, typeName);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB(null,ps,con);
		}
	}
	/**
	 * 添加菜谱
	 * @param recipeId
	 * @param recipeName
	 * @param recipeImg
	 * @param typeId
	 */
	public static void addRecipe(int recipeId,String recipeName,String recipeImg,int typeId){
		Connection con=DBUtil.getConnect();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement("insert into recipe values(?,?,?,?)");
			ps.setInt(1, recipeId);
			ps.setString(2, recipeName);
			ps.setString(3, recipeImg);
			ps.setInt(4, typeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB(null,ps,con);
		}
	}
	/**
	 * 添加菜谱详情
	 * @param recipeShowId
	 * @param recipeShowCailiao
	 * @param recipeShowZuofa
	 * @param recipeImg
	 * @param recipeId
	 */
	public static void addRecipeShow(int recipeShowId,String recipeShowCailiao,String recipeShowZuofa,String recipeImg,int recipeId){
		Connection con=DBUtil.getConnect();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement("insert into recipeShow values(?,?,?,?,?)");
			ps.setInt(1, recipeShowId);
			ps.setString(2, recipeShowCailiao);
			ps.setString(3, recipeShowZuofa);
			ps.setString(4, recipeImg);
			ps.setInt(5, recipeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB(null,ps,con);
		}
	}
}
