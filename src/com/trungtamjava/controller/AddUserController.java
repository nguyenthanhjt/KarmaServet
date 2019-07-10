package com.trungtamjava.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trungtamjava.model.User;
import com.trungtamjava.service.UserService;
import com.trungtamjava.service.UserServiceImpl;

@WebServlet(urlPatterns = "/admin/user/add")
public class AddUserController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// get Session
		HttpSession session = req.getSession();
		Object object = session.getAttribute("loginUser");
//		if (object != null) {
//			User user2 = (User) object;
//			System.err.println(user2.getUsername());
		
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/user/add-user.jsp");
			dispatcher.forward(req, resp);
			
//		} else {
//			resp.sendRedirect(req.getContextPath() + "/login");
//		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("yourname");
		String gender = req.getParameter("gender");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		String city = req.getParameter("address");

		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		user.setCity(city);
		user.setGender(gender);

		// goi Service der luu du lieu vao DB
		UserService userService = new UserServiceImpl();
		userService.add(user);
		// forward page - chuyen trang
		// resp.sendRedirect("/HelloServlet/user/list");
		resp.sendRedirect(req.getContextPath() + "/admin/user/list"); // getContextPath=/Ten Project

		System.out.println(name);
		System.out.println(gender);
		System.out.println(username);
		System.out.println(password);
		System.out.println(role);
		System.out.println(city);
	}
}
