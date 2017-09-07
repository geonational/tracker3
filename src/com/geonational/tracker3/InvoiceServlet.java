package com.geonational.tracker3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.engine.util.JRLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;



/**
 * Servlet implementation class InvoiceServlet
 */
@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(InvoiceServlet.class.getName());
	TrackerService trackerService = new TrackerService();
       
   
    public InvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        ServletOutputStream out = response.getOutputStream();
      

        try {
           
        	/* Get the connection from the entity manager */
        	java.sql.Connection jasperConnect = trackerService.getConnect();
        	
        	
        	LOGGER.info("Finding the report");
        	JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath("rpt/invoice.jasper"));
        	
        	
        	
        	
        	
        	
        	
        	
            
// We need to check that the paramters are ok
        	
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("GN_RPTNUM", request.getParameter("reportnumber"));
            parametros.put("GN_YEAR", Integer.valueOf(request.getParameter("reportyear")));
            parametros.put("GN_MONTH", request.getParameter("reportmonth"));                               
            parametros.put("GN_CONTRACT_ID", Integer.valueOf(request.getParameter("reportcontractid")));
           
            
           
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,jasperConnect);

            //String  destFile = "my_file.pdf";
            
            LOGGER.info("Sending the file to pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint,out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void forwardListContracts(HttpServletRequest req, HttpServletResponse resp, List contractList)
			throws ServletException, IOException {
		String nextJSP = "/jsp/new-invoice.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		req.setAttribute("contractList", contractList);
		dispatcher.forward(req, resp);
	}
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LOGGER.info("We have enterted the doGet class");
    	List<Contract> result = trackerService.getAllContracts();
    	forwardListContracts(request, response, result);
    	
    		
    	    	
      
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("We have enterted the doPost class");
    	
        processRequest(request, response);
    }
}
