package vn.iotstar.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.Constants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/update" })
@SuppressWarnings("serial")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class UpdateController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService service = new UserService();

	private String getFileName(Part part) {

		for (String content : part.getHeader("content-disposition").split(";")) {

			if (content.trim().startsWith("filename"))

				return content.substring(content.indexOf("=") + 2, content.length() - 1);

		}

		return Constant.DEFAULT_FILENAME;

	}

	// lấy toàn bộ hàm trong service

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user;
		if (session != null && session.getAttribute("account") != null) {
			user = (UserModel) session.getAttribute("account");

			req.getRequestDispatcher("update.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/login");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uploadPath = File.separator + Constant.UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ

		// String uploadPath = getServletContext().getRealPath("") + File.separator +
		// UPLOAD_DIRECTORY; //upload vào thư mục project

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists())

			uploadDir.mkdir();

		try {

			String fileName = "";

			for (Part part : req.getParts()) {

				fileName = getFileName(part);

				part.write(uploadPath + File.separator + fileName);

			}

			req.setAttribute("message", "File " + fileName + " has uploaded successfully!");

		} catch (FileNotFoundException fne) {

			req.setAttribute("message", "There was an error: " + fne.getMessage());

		}

		getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);

	}
}
