/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.dao;

import com.journaldev.jsf.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefany
 */
public class LoginDAO {
    public static boolean validate(String user, String password) {
        
                Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
    }
    
    ////////////////////insert in blog//////////////////
    public static void insertInto(String title, String body, String tag) {
                
                Connection con = null;
                
		try {
			con = DataConnect.getConnection();
                        String query = "insert into Blogs (title, body, tag)" + " values (?,?,?)";
			PreparedStatement ps1 = con.prepareStatement(query);
			ps1.setString(1, title);
			ps1.setString(2, body);
                        ps1.setString(3, tag);

			ps1.execute();

		} catch (SQLException ex) {
			System.out.println("Insertion error -->" + ex.getMessage());
			//return false;
		} finally {
			DataConnect.close(con);
		}
    }
    /////////////////////////blog input/////////////////////////////
    
    /////////////////////////blog display//////////////////////////
    public static List<Blog> getDisplayAllBlogs() {
                
                ResultSet rs2 = null;
                Connection con = null;
                List<Blog> blogposts = new ArrayList<Blog>(); 
                
                
                try {
                    con = DataConnect.getConnection();
                    String query2 = "select * from  Blogs";
                    PreparedStatement ps2 = con.prepareStatement(query2);
                    ps2.execute();
                    rs2 = ps2.getResultSet();
                    
                    while (rs2.next()) {
                        Blog blog = new Blog();
                        blog.setBlogTitle(rs2.getString("title"));
                        blog.setBlogBody(rs2.getString("body"));
                        blog.setBlogTag(rs2.getString("tag"));
                        blogposts.add(blog);
                    }
                
                } catch (SQLException ex) {
			System.out.println("Displaying error -->" + ex.getMessage());
			//return false;
		} finally {
			DataConnect.close(con);
		}
                return blogposts;
    }
}
