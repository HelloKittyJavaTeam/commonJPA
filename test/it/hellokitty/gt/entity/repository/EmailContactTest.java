package it.hellokitty.gt.entity.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.EmailContact;
import it.hellokitty.gt.repository.impl.EmailContactRepositoryImpl;
import it.hellokitty.gt.repository.utils.ColumnDirection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EmailContactTest {
	private EmailContactRepositoryImpl emailContactRep = new EmailContactRepositoryImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static EmailContact emailAdd;

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			emailAdd = new EmailContact();
			emailAdd.setUserCreated("testADD"+i);
			emailAdd.setActive(true);
			emailAdd.setId(99999l+i);
			emailAdd.setName("testNAME"+i);
			emailAdd.setSurname("testSURNAME"+i);
			emailAdd.setCountry("testCOUNTRY"+i);
			emailAdd.setEmail("nome"+i+".cognome"+i+"@email.it");
			em.persist(emailAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			em.remove(em.find(EmailContact.class, 99999l+i));
		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void emailContactFetchById(){
		try {
			EmailContact emailContact = emailContactRep.fetchById(99999l);
			assertNotNull("No EmailContact returned from fetchById", emailContact);
			assertTrue("emailContactFetchById method failed on retrieve content value. "
					+ "Actual value: "+emailContact.getName()+" "
					+ "Expected value: testNAME0", emailContact.getName().equals("testNAME0"));
			emailContact= emailContactRep.fetchById(987654321l);
			assertNull(emailContact);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in emailContactFetchById method.");
		}
	}
	
	@Test
	public void emailContactFetchAll(){
		ColumnDirection cd = new ColumnDirection("id", "asc");
		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
		cdList.add(cd);
		try {
			List<EmailContact> emailContactList = emailContactRep.fetchAll(0, 20, cdList);
			assertTrue("emailContactFetchAll returned a empty list.", emailContactList.size() > 0);
			assertTrue("emailContactFetchAll didn't return all the elements.", emailContactList.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in emailContactFetchById method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void emailContactInsert(){
		EmailContact bullToAdd = new EmailContact();
		bullToAdd.setId(98989898l);
		
		try{
			emailContactRep.insert(bullToAdd, "testADD");
			assertNotNull(em.find(EmailContact.class, 98989898l));
		} catch (Exception e){
			fail("emailContactInsert method failed. Unexpected Exception catched. "+e.getMessage());
		}
		
		em.remove(em.find(EmailContact.class, 98989898l));
	}
	
	/*
	 *  MERGE TEST
	 */
	@Test
	public void emailContactMerge(){
		EmailContact bullToMerge = new EmailContact();
		bullToMerge = em.find(EmailContact.class, 99999l);
		bullToMerge.setName("TESTNAMEMERGE");
		
		try{
			emailContactRep.merge(bullToMerge, "testMERGE");
			bullToMerge = em.find(EmailContact.class, 99999l);
			assertTrue("emailContactMErge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+bullToMerge.getName()+" "
					+ "Expected value: TESTNAMEMERGE.",bullToMerge.getName().equals("TESTNAMEMERGE"));
			assertTrue("emailContactMerge method failed. UserUpdate value not updated."
					+ "Current value: "+bullToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", bullToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("emailContactMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			bullToMerge = new EmailContact();
			bullToMerge.setId(9898989898l);
			bullToMerge.setUserCreated("test");
			bullToMerge.setName("TESTNAMEMERGE");
			emailContactRep.merge(bullToMerge, "testMERGE");
			assertNotNull("emailContactMerge method fail. No element added.", em.find(EmailContact.class, 9898989898l));
		} catch (Exception e){
			fail("emailContactMErge method fail during merge on inexistent emailContact. Unexpected exception catched. "+e.toString());
		}
		em.remove(em.find(EmailContact.class, 9898989898l));
	}
	
	/*
	 *  UPDATE TEST
	 */
	@Test
	public void emailContactUpdate(){
		EmailContact bullToUpdate = new EmailContact();
		bullToUpdate = em.find(EmailContact.class, 99999l);
		bullToUpdate.setName("TESTNAMEMERGE");
		
		try{
			emailContactRep.update(bullToUpdate, "testUPDATE");
			bullToUpdate = em.find(EmailContact.class, 99999l);
			assertTrue("emailContactUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+bullToUpdate.getName()+" "
					+ "Expected value: TESTNAMEMERGE.",bullToUpdate.getName().equals("TESTNAMEMERGE"));
			assertTrue("emailContactUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+bullToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", bullToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("emailContactUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			bullToUpdate = new EmailContact();
			bullToUpdate.setId(9898989898l);
			bullToUpdate.setUserCreated("test");
			bullToUpdate.setName("NAME TEST ITA");
			emailContactRep.update(bullToUpdate, "testUPDATE");
			fail("emailContactUpdate method failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactUpdate method fail during merge on inexistent emailContact. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  DELETE TEST
	 */
	@Test
	public void emailContactDelete(){
		EmailContact emailContactToDelete = new EmailContact();
		emailContactToDelete = em.find(EmailContact.class, 99999l);
		
		try {
			emailContactRep.delete(emailContactToDelete, "testDELETE");
			emailContactToDelete = em.find(EmailContact.class, 99999l);
			assertFalse("emailContactDelete method failed. EmailContact not disactivated.", emailContactToDelete.isActive());
		} catch (Exception e){
			fail("emailContactDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
		
		emailContactToDelete = new EmailContact();
		emailContactToDelete.setId(987987987l);
		emailContactToDelete.setUserCreated("testUSER");
		emailContactToDelete.setCreateDate(new Date());
		emailContactToDelete.setActive(false);
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		em.persist(emailContactToDelete);
		transaction.commit();
		
		try{
			emailContactRep.delete(emailContactToDelete, "admin");
			em = Persistence.createEntityManagerFactory("PERSISTENCE_UNIT_NAME").createEntityManager();
			assertNull("emailContactDelete method failed. EmailContact with id 987987987 not deleted. ID: "+emailContactToDelete.getId(), em.find(EmailContact.class, 987987987l));

		} catch (Exception e){
			fail("emailContactDelete method failed during delete from admin. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void emailContactCount(){
		Long result;
		
		try{
			result = emailContactRep.count("testADD0");
			assertTrue("emailContactCount method failed. Number of EmailContact expected: 1 Actual:"+result, result==1);
		} catch (Exception e){
			fail("emailContactCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = emailContactRep.count("unknowUser");
			assertTrue("emailContactCount method with user parameter = 'unknowUser' failed. Number of EmailContact expected: 0 Actual:"+result, result==0);
		} catch (Exception e){
			fail("emailContactCount method with user parameter = 'unknowUser' failed. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  SEARCH TEST
	 */
	@Test
	public void emailContactSearch(){
		List<EmailContact> emailContactList = new ArrayList<EmailContact>();
		ColumnDirection cd = new ColumnDirection("id", "asc");
		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
		cdList.add(cd);
		try{
			emailContactList = emailContactRep.search("testSURNAME", "admin", 0, 20, cdList);
			assertTrue("emailContactSearch method search = testSURNAME failed. Number of EmailContact expected: 20 Actual:"+emailContactList.size(), emailContactList.size()==20);
		} catch (Exception e){
			fail("emailContactSearch method search = testSURNAME failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactList = emailContactRep.search("testNAME", "admin", 0, 20, cdList);
			assertTrue("emailContactSearch method search = testNAME failed. Number of EmailContact expected: 20 Actual:"+emailContactList.size(), emailContactList.size()==20);
		} catch (Exception e){
			fail("emailContactSearch method search = testNAME failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactList = emailContactRep.search("testCOUNTRY", "admin", 0, 20, cdList);
			assertTrue("emailContactSearch method search = testCOUNTRY failed. Number of EmailContact expected: 20 Actual:"+emailContactList.size(), emailContactList.size()==20);
		} catch (Exception e){
			fail("emailContactSearch method search = testCOUNTRY failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactList = emailContactRep.search("email.it", "admin", 0, 20, cdList);
			assertTrue("emailContactSearch method with search = email.it failed. Number of EmailContact expected: 20 Actual:"+emailContactList.size(), emailContactList.size()==20);
		} catch (Exception e){
			fail("emailContactSearch method with search = email.it failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactList = emailContactRep.search("testSURNAME0", "admin", 0, 20, cdList);
			assertTrue("emailContactSearch method failed. Number of EmailContact expected: 1 Actual:"+emailContactList.size(), emailContactList.size()==1);
		} catch (Exception e){
			fail("emailContactSearch method failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactList = emailContactRep.search("testSURNAMEUNKNOW", "admin", 0, 20, cdList);
			assertTrue("emailContactSearch method failed. Number of EmailContact expected: 0 Actual:"+emailContactList.size(), emailContactList.size()==0);
		} catch (Exception e){
			fail("emailContactSearch method failed. Unexpected exception catched. "+e.toString());
		}
	}
}