package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT* FROM baochau";	
		List<UserModel> list = new ArrayList<>();
		try {
			Connection conn = super.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				list.add(new UserModel(
						rs.getInt("id"), 
						rs.getString("username"), 
						rs.getString("email"),
						rs.getString("password"), 
						rs.getString("fullname"), 
						rs.getString("images"), 
						rs.getString("phone"),
						rs.getInt("roleid"), 
						rs.getDate("createdate")));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String sql = "SELECT* FROM baochau WHERE id=?";
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			Connection conn = super.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UserModel model1 = new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"), rs.getString("fullname"), rs.getString("images"), rs.getString("phone"),
						rs.getInt("roleid"), rs.getDate("createdate"));
				list.add(model1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void insert(UserModel baochau) {

		String sql = "INSERT INTO baochau(username, email, password, fullname, images, phone, roleid) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = super.getDatabaseConnection();

			Object ps = conn.prepareStatement(sql);

			((PreparedStatement) ps).setString(1, baochau.getUsername());
			((PreparedStatement) ps).setString(2, baochau.getEmail());
			((PreparedStatement) ps).setString(3, baochau.getPassword());
			((PreparedStatement) ps).setString(4, baochau.getFullname());
			((PreparedStatement) ps).setString(5, baochau.getImages());
			((PreparedStatement) ps).setString(6, baochau.getPhone());
			((PreparedStatement) ps).setInt(7, baochau.getRoleid());
			((PreparedStatement) ps).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		try {
		UserDaoImpl userDao = new UserDaoImpl();
		System.out.println(userDao.findById(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// userDao.insert(new UserModel(4, "bcd", "bcd@gmail.com", "567", "bcdefg",""));

		//UserModel list1 = userDao.findById(3);

		// List<UserModel> list = userDao.findAll();

//		for (UserModel user : list1) {
//			System.out.println(user);
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM baochau WHERE username = ? ";
		try {
			new DBConnectMySQL();
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		UserModel user = null;
		user = this.findByEmail(email);
		if (user == null)
			return false;
		return true;

	}

	@Override
	public boolean checkExistUsername(String username) {
		UserModel user = null;
		user = this.findByUserName(username);
		if (user == null)
			return false;
		return true;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserModel findByEmail(String email) {
		String sql = "SELECT * FROM baochau WHERE email = ?";
		UserModel oneUser = new UserModel();

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println(1);
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("password"));
				oneUser.setImages(rs.getString("images"));
				return oneUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@Override
	public boolean changePasswordByMail(String mail, String password) {
		String sql = "UPDATE tableUser SET password=? WHERE email = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, mail);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}

	@Override
	public String findRoleByID(int id) {
		String sql = "SELECT * FROM tableRole WHERE id = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("role");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@Override
	public void updatePassword(String email, String password) {
	}

}
