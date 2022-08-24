package com.highradius;

import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateData
 */
@WebServlet("/updatedata")
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/grey_goose?characterEncoding=latin1&useConfigs=maxPerformance&zeroDateTimeBehavior=convertToNull";
    static final String USER = "root";
    static final String PASS = "test";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HashMap<Object, Object> Response = new HashMap<>();
		try 
		{		    
			int sl_no = Integer.valueOf(req.getParameter("sl_no"));
			String invcurr = req.getParameter("invcurr");
			String cpterms = req.getParameter("cpterms");
			
			Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "UPDATE winter_internship set invoice_currency=?,cust_payment_terms=? where sl_no=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, invcurr);
            ps.setString(2, cpterms);
            ps.setInt(3, sl_no);
            
            if(ps.executeUpdate() > 0)
            {
            	Response.put("updation", "successfull");
            }
            else 
            {
				Response.put("updation", "failed");
			}
            connection.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonresponse = gson.toJson(Response);
		res.setContentType("Application/json");
		res.getWriter().append(jsonresponse);
	}

}
