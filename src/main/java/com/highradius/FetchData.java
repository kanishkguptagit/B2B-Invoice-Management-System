package com.highradius;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

@WebServlet("/fetchdata")
public class FetchData extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/grey_goose?characterEncoding=latin1&useConfigs=maxPerformance&zeroDateTimeBehavior=convertToNull";
    static final String USER = "root";
    static final String PASS = "test";
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {    	
    	HashMap<Object, Object> Response = new HashMap<>();
    	ArrayList<Payment> Payments = new ArrayList<>();
    	try 
        {
    		int offset = Integer.valueOf(req.getParameter("offset"));
    		int limit = Integer.valueOf(req.getParameter("limit"));
    		String search = req.getParameter("search");
    		boolean advance = Boolean.valueOf(req.getParameter("advance"));
    		
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = connection.createStatement();
            ResultSet countres;
            String sql = "";
            String sqlcount = "SELECT count(*) from winter_internship";
            String sqlstart = "SELECT * FROM winter_internship";
            String sqlend = " limit ?,?;";
            
            if(advance)
            {
            	String byear = req.getParameter("byear");
            	String cnum = req.getParameter("cnum");
            	String invid = req.getParameter("invid");
            	String docid = req.getParameter("docid");
            	String searchparam = " where buisness_year="+byear+" and cust_number="+cnum+" and invoice_id="+invid
            			+" and doc_id="+docid;
            	sql = sqlstart+searchparam+sqlend;     	
            	countres = stmt.executeQuery(sqlcount+searchparam);
            }
            else if(search != null)
            {
            	String searchparam = " where buisness_year like '%"+search+"%' or cust_number like '%"+search+"%' or invoice_id like '%"+search
            			+"%' or doc_id like '%"+search+"%'";
            	sql = sqlstart+searchparam+sqlend;
            	countres = stmt.executeQuery(sqlcount+searchparam);
            }
            else 
            {
				sql = sqlstart+sqlend;
				countres = stmt.executeQuery(sqlcount);
			}
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
            	Payment payment = new Payment(rs.getInt("sl_no"),rs.getString("business_code"), rs.getInt("cust_number"),
            			rs.getString("clear_date"), rs.getInt("buisness_year"), rs.getString("doc_id"), rs.getString("posting_date"),
            			rs.getString("document_create_date"),rs.getString("due_in_date"),rs.getString("invoice_currency"),
            			rs.getString("document_type"),rs.getInt("posting_id"),rs.getDouble("total_open_amount"),
            			rs.getString("baseline_create_date"), rs.getString("cust_payment_terms"), rs.getString("invoice_id"));
            	
            	Payments.add(payment);
            }
            
            countres.next();
            int countRows = countres.getInt(1);
            
            Response.put("Payments", Payments);
            Response.put("rowCount", countRows);
        } 
        catch (Exception e) 
        {
            System.out.print(e);
        }
    	Gson gson = new Gson();
    	String jsonResponse= gson.toJson(Response);
    	res.setHeader("Access-Control-Allow-Origin", "*");
    	res.setContentType("Application/json");
    	res.getWriter().append(jsonResponse);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
    	doGet(req, resp);
    }
}
