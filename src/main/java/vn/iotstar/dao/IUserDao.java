package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface IUserDao {
	
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);

	UserModel findByUserName(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);
	
	UserModel findByEmail(String email);
	
	String findRoleByID(int id);
	
	boolean changePasswordByMail(String mail, String password);

	void updatePassword(String email, String password);
	
	boolean update(UserModel user);
}
