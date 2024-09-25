package vn.iotstar.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/update" })
public class UpdateController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// lấy toàn bộ hàm trong service
	IUserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user;
		if (session != null && session.getAttribute("account") != null) {
			user = (UserModel) session.getAttribute("account");
			
			req.getRequestDispatcher("update.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath()+"/login");
		}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("account");

		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		if (fullname !=null && fullname.length()>0) {
			user.setFullname(fullname);
		
		}
		if (phone !=null && phone.length()>0) {
			user.setPhone(phone);
		
		}
		System.out.println(fullname + phone);
		UserDaoImpl dao = new UserDaoImpl();
		if (dao.update(user)) {
			System.out.println("Cap nhat thanh cong");
			session.setAttribute("account", user);
		}
		else
		{
			System.out.println("Cap nhat khong thanh cong");
		}
		req.getRequestDispatcher("update.jsp").forward(req, resp);
	}
}
