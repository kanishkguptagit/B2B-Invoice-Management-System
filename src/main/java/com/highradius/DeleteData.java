package com.highradius;

import jakarta.servlet.http.HttpServlet;
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
 * Servlet implementation class deletedata
 */
@WebServlet("/deletedata")
public class DeleteData extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/grey_goose?characterEncoding=latin1&useConfigs=maxPerformance&zeroDateTimeBehavior=convertToNull";
    static final String USER = "root";
    static final String PASS = "test";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HashMap<Object, Object> Response = new HashMap<Object, Object>();
		try 
		{
			String data[] = request.getParameterValues("sl_no");			
			
			Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from winter_internship where sl_no=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            boolean flag=true;
            
            for(String i:data)
            {
            	ps.setInt(1, Integer.valueOf(i));
            	int result = ps.executeUpdate();
            	if(result<0)
            	{
            		flag=false;
            		break;
            	}
            }
            
            if(flag)
            {
            	Response.put("deletion", "successfull");
            }
            else 
            {
				Response.put("deletion", "failed");
			}
            connection.close();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonresponse = gson.toJson(Response);
		response.setContentType("Application/json");
		response.getWriter().append(jsonresponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
