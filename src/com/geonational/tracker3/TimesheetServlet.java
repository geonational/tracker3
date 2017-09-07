package com.geonational.tracker3;



import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet(name = "TimesheetServlet", urlPatterns = { "/TimesheetServlet" })
public class TimesheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(TimesheetServlet.class.getName());
	
	 	 
	 
	TimesheetService timesheetService = new TimesheetService();
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LOGGER.info("We have enterted the doGet class");
		
		String action = req.getParameter("searchAction");
		
		LOGGER.info("The action parameter is set to " + action);
		
		if (action != null) {
			switch (action) {
			case "searchById":
				searchTimesheetById(req, resp);
				break;
			case "searchForAdd":
				searchForAdd(req, resp);
				break;
						}
		} else {
			List<Timesheet> result = timesheetService.getAllTimesheets();
			forwardListTimesheets(req, resp, result);
		}
	}

	private void searchTimesheetById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer idTimesheet = Integer.valueOf(req.getParameter("timesheetId"));
		Timesheet timesheet = null;
		List<Project> projectList = null;
		List<User> userList = null;
		
		LOGGER.info("We have enterted the searchcontrolby  id class");
		try {
			timesheet = timesheetService.getTimesheet(idTimesheet);
			projectList = timesheetService.getAllProjects();
			userList = timesheetService.getAllUsers();
		} catch (Exception ex) {
			Logger.getLogger(TimesheetServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		req.setAttribute("timesheet", timesheet);
		req.setAttribute("projectList", projectList);
		req.setAttribute("userList", userList);
		req.setAttribute("action", "edit");
		String nextJSP = "/jsp/new-timesheet.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	 
		
	}

	
	
	private void searchForAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Project> projectList = null;
		List<User> userList = null;
		
		LOGGER.info("We have enterted the search for add class");
		try {
			projectList = timesheetService.getAllProjects();
			userList = timesheetService.getAllUsers();
		} catch (Exception ex) {
			Logger.getLogger(TimesheetServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		req.setAttribute("projectList", projectList);
		req.setAttribute("userList", userList);
		req.setAttribute("action", "add");
		String nextJSP = "/jsp/new-timesheet.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	 
		
	}

	
	
	
	

	private void forwardListTimesheets(HttpServletRequest req, HttpServletResponse resp, List timesheetList)
			throws ServletException, IOException {
		String nextJSP = "/jsp/list-timesheets.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		req.setAttribute("timesheetList", timesheetList);
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
				addTimesheetAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "edit":
			try {
				editTimesheetAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;            
            case "remove":
                removeTimesheetByName(req, resp);
                break;            
        }

    }

    private void addTimesheetAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the addTimesheetAction class");
    	
            
        Date weekStart = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("weekstart"));
       
        BigDecimal monday=new BigDecimal(req.getParameter("monday"));
        BigDecimal tuesday=new  BigDecimal(req.getParameter("tuesday"));
        BigDecimal wednesday=new BigDecimal(req.getParameter("wednesday"));
	BigDecimal thursday=new BigDecimal(req.getParameter("thursday"));
	BigDecimal friday=new  BigDecimal(req.getParameter("friday"));
        BigDecimal saturday=new BigDecimal(req.getParameter("saturday"));
        BigDecimal sunday=new BigDecimal(req.getParameter("sunday"));
                          
        Integer userID = Integer.valueOf(req.getParameter("userid"));
        Integer projectID = Integer.valueOf(req.getParameter("projectid"));
      
        // We need to get the whole user based on userID
        User theUser = timesheetService.getUserFromId(userID);
        Project theProject = timesheetService.getProjectFromId(projectID);
             
             
        Timesheet timesheet = new Timesheet(weekStart,
        		monday,tuesday,wednesday,thursday,friday,saturday,sunday,theUser,theProject);
        
        // Call the jpa method to add to database
        timesheetService.addTimesheet(timesheet);
 
        
        List<Timesheet> timesheetList = timesheetService.getAllTimesheets();
        
        String message = "A new timesheet has been successfully created.";
        req.setAttribute("message", message);
        forwardListTimesheets(req, resp, timesheetList);
    }

    private void editTimesheetAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the editTimesheetAction class");
    	
    	Integer idTimesheet = Integer.valueOf(req.getParameter("idTimesheet"));
    	 Date weekStart = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("weekstart"));
	        
	         BigDecimal monday=new BigDecimal(req.getParameter("monday"));
	         BigDecimal tuesday=new  BigDecimal(req.getParameter("tuesday"));
	         BigDecimal wednesday=new BigDecimal(req.getParameter("wednesday"));
	 	BigDecimal thursday=new BigDecimal(req.getParameter("thursday"));
	 	BigDecimal friday=new  BigDecimal(req.getParameter("friday"));
	         BigDecimal saturday=new BigDecimal(req.getParameter("saturday"));
	         BigDecimal sunday=new BigDecimal(req.getParameter("sunday"));
	                           
	         Integer userID = Integer.valueOf(req.getParameter("userid"));
        Integer projectID = Integer.valueOf(req.getParameter("projectid"));
        
        
        User theUser = timesheetService.getUserFromId(userID);
        Project theProject = timesheetService.getProjectFromId(projectID);
        
        
        // dont do this
        // I just want to get an timesheet that exists
        Timesheet timesheet = new Timesheet(idTimesheet,weekStart,
        		monday,tuesday,wednesday,thursday,friday,saturday,sunday,theUser,theProject);
        
        
        //timesheet.setTimesheetId(idTimesheet);
        
        boolean success = timesheetService.updateTimesheet(timesheet);
        
        String message = null;
        if (success) {
            message = "The timesheet " + idTimesheet + " has been successfully updated.";
        }
        List<Timesheet> timesheetList = timesheetService.getAllTimesheets();
        req.setAttribute("idTimesheet", idTimesheet);
        req.setAttribute("message", message);
        // we need something so we go to do get
        forwardListTimesheets(req, resp, timesheetList);
    }  

    private void removeTimesheetByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer idTimesheet = Integer.valueOf(req.getParameter("idTimesheet"));
        boolean confirm = timesheetService.deleteTimesheet(idTimesheet);
        if (confirm){
            String message = "The timesheet " + idTimesheet + " has been successfully removed.";
            req.setAttribute("message", message);
        } else {
        	 String message = "The timesheet " + idTimesheet + " could not be found.";
        	 req.setAttribute("message", message);
        }
        List<Timesheet> timesheetList = timesheetService.getAllTimesheets();
        forwardListTimesheets(req, resp, timesheetList);
    }

}
	
	
	
	

