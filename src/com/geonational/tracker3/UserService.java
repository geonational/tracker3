package com.geonational.tracker3;


import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserService {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("tracker3");
	 EntityManager entitymanager = emfactory.createEntityManager();

	//List<User> UserList = (List<User>) getAllUsers();
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		//String queryString = "SELECT t FROM User t ";
		//Query query = entitymanager.createQuery(queryString);
		
		// The named query is defined in the entity class
		Query query = entitymanager.createNamedQuery("User.findAll");

		return query.getResultList();

	}

	public User getUserFromId(Integer uId) {

		//String queryString = "SELECT u FROM User u where u.userId = ?1 ";
		Query query = entitymanager.createQuery("SELECT u FROM User u where u.userId = ?1 ").setParameter(1, uId);
		return (User) query.getSingleResult();

	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> searchUserByName(String name) {
			
		Query query = entitymanager.createQuery(
				"SELECT u FROM User u WHERE u.username LIKE :uName")
		.setParameter("uName",name);
		
		return (List<User>) query.getResultList();
		}

	
	
	

	public User getUser(Integer userId) throws Exception {
		
		 User user = entitymanager.find( User.class, userId );
		
			if (user != null) {
			return user;
		} else {
			throw new Exception("The User id " + userId + " not found");
		}
	}

	
	public  void addUser(User user) {
	
	
		entitymanager.getTransaction().begin();
		
		entitymanager.persist(user);
		
		
	      entitymanager.getTransaction().commit();
	      //maybe need to flush here?
			      
        
    }
	
	public boolean updateUser(User user) {
        
            
            entitymanager.getTransaction().begin();
            //UserList.set(matchIdx, user);
           entitymanager.merge(user);
            
            
  	      entitymanager.getTransaction().commit();
            
            
            return true;
      
	}

    public boolean deleteUser(Integer conId) {
    	 User user = entitymanager.find( User.class, conId );
 		
			if (user != null) {
				 entitymanager.getTransaction( ).begin( );
				 entitymanager.remove( user );
			      entitymanager.getTransaction( ).commit( );
			      return true;	
			
			
		} else {
			return false;
			//throw new Exception("The User id " + conId + " not found");
		}
	
    	}

}