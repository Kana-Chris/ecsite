package com.diworksdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.ecsite.dao.BuyItemDAO;
import com.diworksdev.ecsite.dao.LoginDAO;
import com.diworksdev.ecsite.dto.BuyItemDTO;
import com.diworksdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private String loginUserId;
	private String loginPassword;
	public Map<String,Object> session;
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	private BuyItemDAO buyItemDAO = new BuyItemDAO();
	
	public String execute() {
		String result = ERROR;
		loginDTO = loginDAO.getLoginUserInfo(loginUserId,loginPassword);
		session.put("loginUser",loginDTO);
		
		//ログイン情報できたら（loginFlgがtrueなら)セッションにlogin_user_id,id,biyItem_name,buyItem_priceを保存
		
		if(((LoginDTO)session.get("loginUser")).getLoginFlg()) { 
			result = SUCCESS;
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			
			session.put("login_user_id", loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());
			
		}
		return result;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}
	
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
	
	
}
