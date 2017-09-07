package com.geonational.tracker3;


import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProjectService {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("tracker3");
	 EntityManager entitymanager = emfactory.createEntityManager();

	//List<Project> ProjectList = (List<Project>) getAllProjects();
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Project> getAllProjects() {

		//String queryString = "SELECT t FROM Project t ";
		//Query query = entitymanager.createQuery(queryString);
		
		// The named query is defined in the entity class
		Query query = entitymanager.createNamedQuery("Project.findAll");

		return query.getResultList();

	}

	public Project getProjectFromId(Integer uId) {

		//String queryString = "SELECT u FROM Project u where u.projectId = ?1 ";
		Query query = entitymanager.createQuery("SELECT u FROM Project u where u.projectId = ?1 ").setParameter(1, uId);
		return (Project) query.getSingleResult();

	}
	
	
@SuppressWarnings("unchecked")
public List<Project> searchProjectByName(String name) {
		
	Query query = entitymanager.createQuery(
			"SELECT p FROM Project p WHERE p.projectName LIKE :projName")
	.setParameter("projName",name);
	
	return (List<Project>) query.getResultList();
	}

	
	
	

	public Project getProject(Integer projectId) throws Exception {
		
		 Project project = entitymanager.find( Project.class, projectId );
		
			if (project != null) {
			return project;
		} else {
			throw new Exception("The Project id " + projectId + " not found");
		}
	}

	
	public  void addProject(Project project) {
	
	
		entitymanager.getTransaction().begin();
		
		entitymanager.persist(project);
		
		
	      entitymanager.getTransaction().commit();
	      //maybe need to flush here?
			      
        
    }
	
	public boolean updateProject(Project project) {
        
            
            entitymanager.getTransaction().begin();
            //ProjectList.set(matchIdx, project);
           entitymanager.merge(project);
            
            
  	      entitymanager.getTransaction().commit();
            
            
            return true;
      
	}

    public boolean deleteProject(Integer conId) {
    	 Project project = entitymanager.find( Project.class, conId );
 		
			if (project != null) {
				 entitymanager.getTransaction( ).begin( );
				 entitymanager.remove( project );
			      entitymanager.getTransaction( ).commit( );
			      return true;	
			
			
		} else {
			return false;
			//throw new Exception("The Project id " + conId + " not found");
		}
	
    	}

}