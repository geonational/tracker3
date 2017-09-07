package com.geonational.tracker3;



import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TrackerService {
    
    
    private static final Logger LOGGER = Logger.getLogger(TrackerService.class.getName());

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("tracker3");
	 EntityManager entitymanager = emfactory.createEntityManager();

	List<Contract> ContractList = (List<Contract>) getAllContracts();
	
	
	public  Connection getConnect()   {
		
           
		/* This is for jasper report to use the JPA connection */
		
		entitymanager.getTransaction().begin();
	java.sql.Connection connection = entitymanager.unwrap(java.sql.Connection.class);
	   entitymanager.getTransaction().commit();
           
           
     
           return connection;
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Contract> getAllContracts() {

		//String queryString = "SELECT c FROM Contract c ";
		//Query query = entitymanager.createQuery(queryString);
		
		// The named query is defined in the entity class
		Query query = entitymanager.createNamedQuery("Contract.findAll");

		return query.getResultList();

	}

	public User getUserFromId(Integer uId) {

		//String queryString = "SELECT u FROM User u where u.userId = ?1 ";
		Query query = entitymanager.createQuery("SELECT u FROM User u where u.userId = ?1 ").setParameter(1, uId);
		return (User) query.getSingleResult();

	}
	
	
	
	
	
	public List<Contract> searchContractByName(String name) {
		Comparator<Contract> groupByComparator = Comparator.comparing(Contract::getPoNumber)
				.thenComparing(Contract::getLabourCategory);
		List<Contract> result = ContractList.stream()
				.filter(e -> e.getPoNumber().equalsIgnoreCase(name)
						|| e.getLabourCategory().equalsIgnoreCase(name))
				.sorted(groupByComparator).collect(Collectors.toList());
		return result;
	}

	public Contract getContract(Integer conId) throws Exception {
		
		 Contract contract = entitymanager.find( Contract.class, conId );
		
			if (contract != null) {
			return contract;
		} else {
			throw new Exception("The Contract id " + conId + " not found");
		}
	}

	
	public  Integer addContract(Contract contract) {
	
	
		entitymanager.getTransaction().begin();
		//ContractList.add(contract);
		entitymanager.persist(contract);
	      entitymanager.getTransaction().commit();
			      
        return contract.getContractId();
    }
	
	public boolean updateContract(Contract contract) {
        /* int matchIdx = 0;
        Optional<Contract> match = ContractList.stream()
                .filter(c -> c.getContractId() == contract.getContractId())
                .findFirst();
        if (match.isPresent()) {
            matchIdx = ContractList.indexOf(match.get()); */
            
            entitymanager.getTransaction().begin();
            //ContractList.set(matchIdx, contract);
           entitymanager.merge(contract);
            
            
  	      entitymanager.getTransaction().commit();
            
            
            return true;
      /*  } else {
            return false;
        } */
	}

    public boolean deleteContract(Integer conId) {
    	 Contract contract = entitymanager.find( Contract.class, conId );
 		
			if (contract != null) {
				 entitymanager.getTransaction( ).begin( );
				 entitymanager.remove( contract );
			      entitymanager.getTransaction( ).commit( );
			      return true;	
			
			
		} else {
			return false;
			//throw new Exception("The Contract id " + conId + " not found");
		}
	
    	}

}