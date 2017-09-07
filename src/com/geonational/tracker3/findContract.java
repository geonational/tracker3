package com.geonational.tracker3;

import java.util.List;

public class findContract {
	
	
	
	public static void main(String[] args) {

		TrackerService trackerService = new TrackerService();
		
		List<Contract> result = trackerService.getAllContracts();
		

		for (Contract cr: result) {  
            System.out.println("Id " + cr.getContractId() + " Category " + cr.getLabourCategory() );
            //System.out.println(cr.getLabourCategory());
         }  
     }  
 }  
		

		