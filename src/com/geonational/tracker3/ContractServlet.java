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






@WebServlet(name = "ContractServlet", urlPatterns = { "/ContractServlet" })
public class ContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ContractServlet.class.getName());
	
	 	 
	 
	TrackerService trackerService = new TrackerService();
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LOGGER.info("We have enterted the doGet class");
		
		String action = req.getParameter("searchAction");
		if (action != null) {
			switch (action) {
			case "searchById":
				searchContractById(req, resp);
				break;
			case "searchByName":
				searchContractByName(req, resp);
				break;
			}
		} else {
			List<Contract> result = trackerService.getAllContracts();
			forwardListContracts(req, resp, result);
		}
	}

	private void searchContractById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer idContract = Integer.valueOf(req.getParameter("contractId"));
		Contract contract = null;
		
		LOGGER.info("We have enterted the searchcontrolby  id class");
		try {
			contract = trackerService.getContract(idContract);
		} catch (Exception ex) {
			Logger.getLogger(ContractServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		req.setAttribute("contract", contract);
		req.setAttribute("action", "edit");
		String nextJSP = "/jsp/new-contract.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	 
		
	}

	private void searchContractByName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String contractName = req.getParameter("contractName");
		List<Contract> result = trackerService.searchContractByName(contractName);
		forwardListContracts(req, resp, result);
	}

	private void forwardListContracts(HttpServletRequest req, HttpServletResponse resp, List contractList)
			throws ServletException, IOException {
		String nextJSP = "/jsp/list-contracts.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		req.setAttribute("contractList", contractList);
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
				addContractAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "edit":
			try {
				editContractAction(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;            
            case "remove":
                removeContractByName(req, resp);
                break;            
        }

    }

    private void addContractAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the addContractAction class");
    	
        Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("enddate"));
        
        Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("startdate"));
        String labourCategory = req.getParameter("labourcategory");
        String poNumber = req.getParameter("ponumber");
        BigDecimal rate=new BigDecimal(req.getParameter("rate"));
        BigDecimal totalHours=new BigDecimal(req.getParameter("totalhours"));
        BigDecimal totalDollars=new  BigDecimal(req.getParameter("totaldollars"));
        Integer userID = Integer.valueOf(req.getParameter("userid"));
      
        // We need to get the whole user based on userID
        User theUser = trackerService.getUserFromId(userID);
             
        Contract contract = new Contract(endDate,startDate,
        		labourCategory,poNumber,rate,totalHours,totalDollars,theUser);
        
        // Call the jpa method to add to database
        Integer idContract = trackerService.addContract(contract);
        
        List<Contract> contractList = trackerService.getAllContracts();
        req.setAttribute("idContract", idContract);
        String message = "The new contract " + idContract + " has been successfully created.";
        req.setAttribute("message", message);
        forwardListContracts(req, resp, contractList);
    }

    private void editContractAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    	
    	LOGGER.info("We have enterted the editContractAction class");
    	
    	 Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("enddate"));
         Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("startdate"));
         String labourCategory = req.getParameter("labourcategory");
         String poNumber = req.getParameter("ponumber");
         BigDecimal rate=new BigDecimal(req.getParameter("rate"));
         BigDecimal totalHours=new BigDecimal(req.getParameter("totalhours"));
         BigDecimal totalDollars=new  BigDecimal(req.getParameter("totaldollars"));
         Integer userID = Integer.valueOf(req.getParameter("userid"));
        Integer idContract = Integer.valueOf(req.getParameter("idContract"));
       
        User theUser = trackerService.getUserFromId(userID);
        
        
        // dont do this
        // I just want to get an contract that exists
        Contract contract = new Contract(idContract,endDate,startDate,
        		labourCategory,poNumber,rate,totalHours,totalDollars,theUser);
        
        
        //contract.setContractId(idContract);
        
        boolean success = trackerService.updateContract(contract);
        
        String message = null;
        if (success) {
            message = "The contract " + idContract + " has been successfully updated.";
        }
        List<Contract> contractList = trackerService.getAllContracts();
        req.setAttribute("idContract", idContract);
        req.setAttribute("message", message);
        // we need something so we go to do get
        forwardListContracts(req, resp, contractList);
    }  

    private void removeContractByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer idContract = Integer.valueOf(req.getParameter("idContract"));
        boolean confirm = trackerService.deleteContract(idContract);
        if (confirm){
            String message = "The contract " + idContract + " has been successfully removed.";
            req.setAttribute("message", message);
        } else {
        	 String message = "The contract " + idContract + " could not be found.";
        	 req.setAttribute("message", message);
        }
        List<Contract> contractList = trackerService.getAllContracts();
        forwardListContracts(req, resp, contractList);
    }

}
	
	
	
	

