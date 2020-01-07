package com.exam.repository;
//package com.exam.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import com.exam.vo.CommentVO;
//
//public class CommentDao {
//	private static final CommentDao instance = new CommentDao();
//	
//	public static CommentDao getInstance() {
//		return instance;
//	}
//	
//	private CommentDao() {}
//	
//	public int nextCommentNum() {
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		String sql = "";
//		int num = 0;
//		
//		try {
//			con = DBManager.getConnection();
//			sql = "SELECT COUNT(*) FROM comments";
//			stmt = con.createStatement();
//			
//			rs = stmt.executeQuery(sql);
//			
//			if (rs.next()) {
//				num = rs.getInt(1) + 1;
//			} else {
//				num = 1;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(con, stmt, rs);
//		}
//		
//		return num;
//	} // nextCommentNum
//	
//	public void insertComment(CommentVO commentVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql = "";
//		
//		try {
//			con = DBManager.getConnection();
//			sql = "INSERT INTO comments (num, username, passwd, comments, ip, reg_date, re_ref, re_lev, re_seq) ";
//			sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
//			
//			pstmt = con.prepareStatement(sql);
//			
//			pstmt.setInt(1, commentVO.getNum());
//			pstmt.setString(2, commentVO.getUsername());
//			pstmt.setString(3, commentVO.getPasswd());
//			pstmt.setString(4, commentVO.getComments());
//			pstmt.setString(5, commentVO.getIp());
//			pstmt.setTimestamp(6, commentVO.getRegDate());
//			pstmt.setInt(7, commentVO.getReRef());
//			pstmt.setInt(8, commentVO.getReLev());
//			pstmt.setInt(9, commentVO.getReSeq());
//			
//			pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(con, pstmt);
//		}
//	} // insertComment
//	
//	public void deleteComment(int num) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql = "";
//		
//		try {
//			con = DBManager.getConnection();
//			sql = "DELETE FROM comment WHERE = ?";
//			
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, num);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(con, pstmt);
//		}
//	} // deleteComment
//	
//	public void reWriteComment () {
//		
//	}
//	
//} // CommentDao
