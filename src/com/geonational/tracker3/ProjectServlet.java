package com.geonational.tracker3;



import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.text.ParseException;
import java.math.BigDecimal;


import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet(name = "ProjectServlet", urlPatterns = { "/ProjectServlet" })
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProjectServlet.class.getName());
	
	 	 
	 
	ProjectService projectService = new ProjectService();
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LOGGER.info("We have enterted the doGet class");
		
		String action = req.getParameter("searchAction");
		
		LOGGER.info("The action parameter is set to " + action);
		
		if (action != null) {
			switch (action) {
			case "searchById":
				searchProjectById(req, resp);
				break;
			case "searchByName":
				searchProjectByName(req, resp);
				break;			
									}
		} else {
			List<Project> result = projectService.getAllProjects();
			forwardListProjects(req, resp, result);
		}
	}

	private void searchProjectById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer idProject = Integer.valueOf(req.getParameter("projectId"));
		Project project = null;
		
		
		LOGGER.info("We have enterted the searchcontrolby  id class");
		try {
			project = projectService.getProject(idProject);
			
		} catch (Exception ex) {
			Logger.getLogger(ProjectServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		req.setAttribute("project", project);
		req.setAttribute("action", "edit");
		String nextJSP = "/jsp/new-project.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	 
		
	}

	private void searchProjectByName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String projectName = req.getParameter("projectName");
		List<Project> result = projectService.searchProjectByName(projectName);
		forwardListProjects(req, resp, result);
	}
	
	
	
	

	private void forwardListProjects(HttpServletRequest req, HttpServletResponse resp, List projectList)
			throws ServletException, IOException {
		String nextJSP = "/jsp/list-projects.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		req.setAttribute("projectList", projectList);
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
				addProjectAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "edit":
			try {
				editProjectAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;            
            case "remove":
                removeProjectByName(req, resp);
                break;            
        }

    }

    private void addProjectAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the addProjectAction class");
    	
          
        String projectname= req.getParameter("projectname");
         BigDecimal totalhours=new BigDecimal(req.getParameter("totalhours"));
          BigDecimal totaldollars=new BigDecimal(req.getParameter("totaldollars"));
        
       

	
                          
        
      

        
             
             
        Project project = new Project(projectname,totalhours,totaldollars);
        
        // Call the jpa method to add to database
        projectService.addProject(project);
 
        
        List<Project> projectList = projectService.getAllProjects();
        
        String message = "A new project has been successfully created.";
        req.setAttribute("message", message);
        forwardListProjects(req, resp, projectList);
    }

    private void editProjectAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the editProjectAction class");
    	
    	Integer idProject = Integer.valueOf(req.getParameter("idProject"));
    	  String projectname= req.getParameter("projectname");
	          BigDecimal totalhours=new BigDecimal(req.getParameter("totalhours"));
          BigDecimal totaldollars=new BigDecimal(req.getParameter("totaldollars"));
	                           
	       
        
        
        // dont do this
        // I just want to get an project that exists
        Project project = new Project(idProject,projectname,totalhours,totaldollars);
        
        
        //project.setProjectId(idProject);
        
        boolean success = projectService.updateProject(project);
        
        String message = null;
        if (success) {
            message = "Project has been successfully updated.";
        }
        List<Project> projectList = projectService.getAllProjects();
        req.setAttribute("message", message);
        // we need something so we go to do get
        forwardListProjects(req, resp, projectList);
    }  

    private void removeProjectByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer idProject = Integer.valueOf(req.getParameter("idProject"));
        boolean confirm = projectService.deleteProject(idProject);
        if (confirm){
            String message = "The project " + idProject + " has been successfully removed.";
            req.setAttribute("message", message);
        } else {
        	 String message = "The project " + idProject + " could not be found.";
        	 req.setAttribute("message", message);
        }
        List<Project> projectList = projectService.getAllProjects();
        forwardListProjects(req, resp, projectList);
    }

}
	
	
	
	

