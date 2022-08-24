package com.highradius;

import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddData
 */
@WebServlet("/adddata")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/grey_goose?characterEncoding=latin1&useConfigs=maxPerformance&zeroDateTimeBehavior=convertToNull";
    static final String USER = "root";
    static final String PASS = "test";       
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HashMap<Object, Object> Response = new HashMap<>();
		try 
		{
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
		        buffer.append(line);
		        buffer.append(System.lineSeparator());
		    }
		    String data = buffer.toString();
		    HashMap<String, String> hm = new Gson().fromJson(data, HashMap.class);
		    
		    
			String bcode = hm.get("bcode");			
			int cnum = Integer.valueOf(hm.get("cnum"));
			String cdate = hm.get("cdate");
			int byear = Integer.valueOf(hm.get("byear"));
			String docid = hm.get("docid");
			String pdate = hm.get("pdate");
			String dcdate = hm.get("dcdate");
			String ddate = hm.get("ddate");
			String invcurr = hm.get("invcurr");
			String doctype = hm.get("doctype");
			int pid = Integer.valueOf(hm.get("pid"));
			double amt = Double.valueOf(hm.get("amt"));
			String bcdate = hm.get("bcdate");
			String cpterms = hm.get("cpterms");
			String invid = hm.get("invid");
			
			Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from winter_internship");
            rs.next();
            int sl_no = rs.getInt(1);
            sl_no +=1;
            
            String sql = "INSERT INTO winter_internship(business_code,cust_number,clear_date,buisness_year,doc_id,"
            		+ "posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,"
            		+ "total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,sl_no) "
            		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bcode);
            ps.setInt(2, cnum);
            ps.setString(3, cdate);
            ps.setInt(4, byear);
            ps.setString(5, docid);
            ps.setString(6, pdate);
            ps.setString(7, dcdate);
            ps.setString(8, ddate);
            ps.setString(9, invcurr);
            ps.setString(10, doctype);
            ps.setInt(11, pid);
            ps.setDouble(12, amt);
            ps.setString(13, bcdate);
            ps.setString(14, cpterms);
            ps.setString(15, invid);
            ps.setInt(16, sl_no);
            
            if(ps.executeUpdate() > 0)
            {
            	Response.put("insert", "successfull");
            	ResultSet countres = stmt.executeQuery("select count(*) from winter_internship");
            	countres.next();
            	int count = countres.getInt(1);
            	Response.put("sl_no", count);
            }
            else 
            {
				Response.put("insert", "failed");
			}
            connection.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			Response.put("insert", "error");
		}
		Gson gson = new Gson();
		String jsonresponse = gson.toJson(Response);
		res.setContentType("Application/json");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Credentials", "*");
		res.getWriter().append(jsonresponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub		
	}

}
