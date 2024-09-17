package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {

	UserModel login(String username, String password);
	
	UserModel get(String username);
	
	UserModel findByUserName (String username);	
		
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean register(String email, String password, String username, String fullname, String phone);
	
	void insert(UserModel user);
	
	void updatePassword(String password, String email);
	
}
