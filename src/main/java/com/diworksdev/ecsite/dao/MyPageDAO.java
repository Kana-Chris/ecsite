package com.diworksdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.diworksdev.ecsite.dto.MyPageDTO;
import com.diworksdev.ecsite.util.DBConnector;

public class MyPageDAO {
	
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	
	public ArrayList<MyPageDTO> getMyPageUserInfo(String item_transaction_id,String user_master_id) throws SQLException{
		ArrayList<MyPageDTO> myPageDTO = new ArrayList<MyPageDTO>();
		
		String sql = "SELECT user_buy_item_transaction.id,item_name,total_price,total_count,pay,user_buy_item_transaction.insert_date FROM user_buy_item_transaction LEFT JOIN item_info_transaction ON item_transaction_id =item_info_transaction.id WHERE item_transaction_id=? AND user_master_id=?";
					
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				MyPageDTO dto = new MyPageDTO();
				dto.setId(resultSet.getString("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setTotalPrice(resultSet.getString("total_price"));
				dto.setTotalCount(resultSet.getString("total_count"));
				dto.setPayment(resultSet.getString("pay"));
				dto.setInsert_date(resultSet.getString("insert_date"));
				myPageDTO.add(dto);	
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return myPageDTO;
	}
	
	public int buyItemHistoryDelete(String item_transaction_id,String user_master_id)throws SQLException{
		String sql="DELETE FROM user_buy_item_transaction WHERE item_transaction_id=? AND user_master_id=?";
		
		PreparedStatement preparedStatement;
		int result = 0;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);
			result = preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		return result;
	}
	
}
 