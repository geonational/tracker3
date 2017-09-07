package com.geonational.tracker3;


import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TimesheetService {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("tracker3");
	 EntityManager entitymanager = emfactory.createEntityManager();

	//List<Timesheet> TimesheetList = (List<Timesheet>) getAllTimesheets();
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Timesheet> getAllTimesheets() {

		//String queryString = "SELECT t FROM Timesheet t ";
		//Query query = entitymanager.createQuery(queryString);
		
		// The named query is defined in the entity class
		Query query = entitymanager.createNamedQuery("Timesheet.findAllDate");

		return query.getResultList();

	}

	public User getUserFromId(Integer uId) {

		//String queryString = "SELECT u FROM User u where u.userId = ?1 ";
		Query query = entitymanager.createQuery("SELECT u FROM User u where u.userId = ?1 ").setParameter(1, uId);
		return (User) query.getSingleResult();

	}
	
	public Project getProjectFromId(Integer uId) {
	
			//String queryString = "SELECT u FROM User u where u.userId = ?1 ";
			Query query = entitymanager.createQuery("SELECT p FROM Project p where p.projectId = ?1 ").setParameter(1, uId);
			return (Project) query.getSingleResult();
	
	}
	
	
	// We need a project drop down
	@SuppressWarnings("unchecked")
	public List<Project> getAllProjects() {

		
		
		// The named query is defined in the entity class
		Query query = entitymanager.createNamedQuery("Project.findAll");

		return query.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		
		
		// The named query is defined in the entity class
		Query query = entitymanager.createNamedQuery("User.findAll");

		return query.getResultList();

	}
	
	
	
	
	
	

	public Timesheet getTimesheet(Integer timeId) throws Exception {
		
		 Timesheet timesheet = entitymanager.find( Timesheet.class, timeId );
		
			if (timesheet != null) {
			return timesheet;
		} else {
			throw new Exception("The Timesheet id " + timeId + " not found");
		}
	}

	
	public  void addTimesheet(Timesheet timesheet) {
	
	
		entitymanager.getTransaction().begin();
		
		entitymanager.persist(timesheet);
		
		
	      entitymanager.getTransaction().commit();
	      //maybe need to flush here?
			      
        
    }
	
	public boolean updateTimesheet(Timesheet timesheet) {
        
            
            entitymanager.getTransaction().begin();
            //TimesheetList.set(matchIdx, timesheet);
           entitymanager.merge(timesheet);
            
            
  	      entitymanager.getTransaction().commit();
            
            
            return true;
      
	}

    public boolean deleteTimesheet(Integer conId) {
    	 Timesheet timesheet = entitymanager.find( Timesheet.class, conId );
 		
			if (timesheet != null) {
				 entitymanager.getTransaction( ).begin( );
				 entitymanager.remove( timesheet );
			      entitymanager.getTransaction( ).commit( );
			      return true;	
			
			
		} else {
			return false;
			//throw new Exception("The Timesheet id " + conId + " not found");
		}
	
    	}

}