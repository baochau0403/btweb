package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.impl.UserService;

@WebServlet(urlPatterns = "/forgot")
public class ForgotController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/forgot.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		UserService service = new UserService();
		String alertMsg = "";
		if (!service.checkExistEmail(email)) {
			alertMsg = "Email không tồn tại!";
		}
		else {
			String newPassword = service.RandomPassword();
			alertMsg = "Mật khẩu mới của bạn là " + newPassword;
			service.updatePassword(newPassword, email);
		}
		req.setAttribute("alert", alertMsg);
		req.getRequestDispatcher("/forgot.jsp").forward(req, resp);
	}
}
