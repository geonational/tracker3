package com.geonational.tracker3;



import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.text.ParseException;


import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet(name = "UserServlet", urlPatterns = { "/UserServlet" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserServlet.class.getName());
	
	 	 
	 
	UserService userService = new UserService();
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LOGGER.info("We have enterted the doGet class");
		
		String action = req.getParameter("searchAction");
		
		LOGGER.info("The action parameter is set to " + action);
		
		if (action != null) {
			switch (action) {
			case "searchById":
				searchUserById(req, resp);
				break;
			case "searchByName":
				searchUserByName(req, resp);
				break;		
									}
		} else {
			List<User> result = userService.getAllUsers();
			forwardListUsers(req, resp, result);
		}
	}

	private void searchUserById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer idUser = Integer.valueOf(req.getParameter("userId"));
		User user = null;
		
		
		LOGGER.info("We have enterted the searchcontrolby  id class");
		try {
			user = userService.getUser(idUser);
			
		} catch (Exception ex) {
			Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		req.setAttribute("user", user);
		req.setAttribute("action", "edit");
		String nextJSP = "/jsp/new-user.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	 
		
	}

	
	private void searchUserByName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uName = req.getParameter("userName");
		List<User> result = userService.searchUserByName(uName);
		forwardListUsers(req, resp, result);
	}
	
	
	

	private void forwardListUsers(HttpServletRequest req, HttpServletResponse resp, List userList)
			throws ServletException, IOException {
		String nextJSP = "/jsp/list-users.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		req.setAttribute("userList", userList);
		dispatcher.forward(req, resp);
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
		LOGGER.info("We have enterted the doPost class");
        String action = req.getParameter("action");
        
        LOGGER.info("The action is set to " + action);
        
        switch (action) {
            case "add":
			try {
				addUserAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "edit":
			try {
				editUserAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;            
            case "remove":
                removeUserByName(req, resp);
                break;            
        }

    }

    private void addUserAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the addUserAction class");
    	
          
        String username= req.getParameter("username");
        String street= req.getParameter("street");
        String city= req.getParameter("city");
	String zip = req.getParameter("zip");        
        String companyName= req.getParameter("companyname");
       

	
                          
        
      

        
             
             
        User user = new User(username,
        		street,city,zip,companyName);
        
        // Call the jpa method to add to database
        userService.addUser(user);
 
        
        List<User> userList = userService.getAllUsers();
        
        String message = "A new user has been successfully created.";
        req.setAttribute("message", message);
        forwardListUsers(req, resp, userList);
    }

    private void editUserAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the editUserAction class");
    	
    	Integer idUser = Integer.valueOf(req.getParameter("idUser"));
    	  String username= req.getParameter("username");
	         String street= req.getParameter("street");
	         String city= req.getParameter("city");
	 	String zip = req.getParameter("zip");        
        String companyName= req.getParameter("companyname");
	                           
	       
        
        
        // dont do this
        // I just want to get an user that exists
        User user = new User(idUser,username,street,city,zip,companyName);
        
        
        //user.setUserId(idUser);
        
        boolean success = userService.updateUser(user);
        
        String message = null;
        if (success) {
            message = "New user  has been successfully updated.";
        }
        List<User> userList = userService.getAllUsers();
        req.setAttribute("message", message);
        // we need something so we go to do get
        forwardListUsers(req, resp, userList);
    }  

    private void removeUserByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer idUser = Integer.valueOf(req.getParameter("idUser"));
        boolean confirm = userService.deleteUser(idUser);
        if (confirm){
            String message = "The user " + idUser + " has been successfully removed.";
            req.setAttribute("message", message);
        } else {
        	 String message = "The user " + idUser + " could not be found.";
        	 req.setAttribute("message", message);
        }
        List<User> userList = userService.getAllUsers();
        forwardListUsers(req, resp, userList);
    }

}
	
	
	
	

