/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.journaldev.jsf.dao.LoginDAO;
import com.journaldev.jsf.dao.Blog;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



/**
 *
 * @author Estefany
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {
    
    private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;
        private boolean loggedIn = false;
       
        private String blogTitle;
        private String blogBody;
        private String blogTag;


	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
        
        ///////////blogs////////
        public String getBlogTitle() {
                return blogTitle;
        }
        
        public void setBlogTitle(String blogTitle) {
                this.blogTitle = blogTitle;
        }
        
        public String getBlogBody() {
                return blogBody;
        }
        
        public void setBlogBody(String blogBody) {
                this.blogBody = blogBody;
        }
        
        public String getBlogTag() {
                return blogTag;
        }
        
        public void setBlogTag(String blogTag) {
                this.blogTag = blogTag;
        }
        ////////////////blogs/////
        

	public String validateUsernamePassword() {
           
		boolean valid = LoginDAO.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
                        loggedIn = true;
                       
			return "store";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Password",
							"Please enter correct username and Password"));
			return "login";
		}
	}
        
        public String validateUsernamePassword2() {
           
		boolean valid = LoginDAO.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
                        loggedIn = true;
                       
			return "blog";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Password",
							"Please enter correct username and Password"));
			return "login";
		}
	}
        public boolean isLogin(){
         if(loggedIn){
             return true;
         }
         else{
             return false;
         }
        }
	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
        
                ///////////////input blog////
        public String inputBlogInDB() {
                LoginDAO.insertInto(blogTitle, blogBody, blogTag);
                return "blog";
        }
        ////////////////////////////////
        
        //////////////display blogs////////
        public List<Blog> getDisplayBlogs() {
                
            List<Blog> blogposts = new ArrayList<Blog>(); 
            blogposts = LoginDAO.getDisplayAllBlogs();
            return blogposts;
        }

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    
}
