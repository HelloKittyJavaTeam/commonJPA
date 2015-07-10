package it.hellokitty.gt.entity.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.EmailContact;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.impl.EmailContactServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailContactServiceTest {
	private EmailContactServiceImpl emailContactRep = new EmailContactServiceImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static EmailContact emailContactAdd;
	private static List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			emailContactAdd = new EmailContact();
			emailContactAdd.setId(99999l+i);
			emailContactAdd.setUserCreated("testADD"+i);
			emailContactAdd.setCreateDate(new Date());
			emailContactAdd.setActive(true);

			em.persist(emailContactAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			emailContactAdd = new EmailContact();
			emailContactAdd.setId(99999l+i);
			emailContactAdd.setUserCreated("testADD"+i);
			em.remove(em.find(EmailContact.class, 99999l+i));

		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void emailContactFetchById(){
		try{
			emailContactRep.fetchById(null);
			fail("emailContactFetchById method with id = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactFetchById method with id = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.fetchById(-1l);
			fail("emailContactFetchById method with id = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactFetchById method with id = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			EmailContact emailContact = emailContactRep.fetchById(99999l);
			assertNotNull("No EmailContact returned from fetchById", emailContact);
			emailContact= emailContactRep.fetchById(987654321l);
			assertNull(emailContact);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in emailContactFetchById method.");
		}
	}
	
	@Test
	public void emailContactFetchAll(){
		cdList = new ArrayList<ColumnDirection>();
		ColumnDirection cd = new ColumnDirection("id", "asc");
		cdList.add(cd);
		try{
			emailContactRep.fetchAll(null, 10, cdList);
			fail("emailContactFetchAll method with start parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactFetchAll method with start parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.fetchAll(-1, 10, cdList);
			fail("emailContactFetchAll method with start parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactFetchAll method with start parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.fetchAll(0, null, cdList);
			fail("emailContactFetchAll method with limit parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactFetchAll method with limit parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.fetchAll(0, -1, cdList);
			fail("emailContactFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.fetchAll(0, 10, null);
			fail("emailContactFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("emailContactFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
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
		EmailContact emailContactToAdd = new EmailContact();
		emailContactToAdd.setId(98989898l);
		
		try{
			emailContactRep.insert(null, "testADD");
			fail("emailContactInsert method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactInsert method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.insert(emailContactToAdd, null);
			fail("emailContactInsert method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactInsert method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.insert(emailContactToAdd, "");
			fail("emailContactInsert method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactInsert method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}

		try{
			emailContactRep.insert(emailContactToAdd, "testADD");
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
		EmailContact emailContactToMerge = new EmailContact();
		emailContactToMerge = em.find(EmailContact.class, 99999l);
		emailContactToMerge.setName("MODIFIED NAME");
		
		try{
			emailContactRep.merge(null, "testADD");
			fail("emailContactMerge method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactMerge method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.merge(emailContactToMerge, null);
			fail("emailContactMerge method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactMerge method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.merge(emailContactToMerge, "");
			fail("emailContactMerge method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactMerge method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.merge(emailContactToMerge, "testMERGE");
			emailContactToMerge = em.find(EmailContact.class, 99999l);
			assertTrue("emailContactMErge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+emailContactToMerge.getName()+" "
					+ "Expected value: MODIFIED NAME.",emailContactToMerge.getName().equals("MODIFIED NAME"));
			assertTrue("emailContactMerge method failed. UserUpdate value not updated."
					+ "Current value: "+emailContactToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", emailContactToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("emailContactMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			emailContactToMerge = new EmailContact();
			emailContactToMerge.setId(9898989898l);
			emailContactToMerge.setUserCreated("test");
			emailContactRep.merge(emailContactToMerge, "testMERGE");
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
		EmailContact emailContactToUpdate = new EmailContact();
		emailContactToUpdate = em.find(EmailContact.class, 99999l);
		emailContactToUpdate.setName("MODIFIED NAME");
		
		try{
			emailContactRep.update(null, "testADD");
			fail("emailContactUpdate method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactUpdate method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.update(emailContactToUpdate, null);
			fail("emailContactUpdate method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactUpdate method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.update(emailContactToUpdate, "");
			fail("emailContactUpdate method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactUpdate method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.update(emailContactToUpdate, "testUPDATE");
			emailContactToUpdate = em.find(EmailContact.class, 99999l);
			assertTrue("emailContactUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+emailContactToUpdate.getName()+" "
					+ "Expected value: 10.",emailContactToUpdate.getName().equals("MODIFIED NAME"));
			assertTrue("emailContactUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+emailContactToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", emailContactToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("emailContactUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			emailContactToUpdate = new EmailContact();
			emailContactToUpdate.setId(9898989898l);
			emailContactToUpdate.setUserCreated("test");
			emailContactRep.update(emailContactToUpdate, "testUPDATE");
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
		
		try{
			emailContactRep.update(null, "testADD");
			fail("emailContactDelete method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactDelete method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.update(emailContactToDelete, null);
			fail("emailContactDelete method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactDelete method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			emailContactRep.update(emailContactToDelete, "");
			fail("emailContactDelete method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("emailContactDelete method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			emailContactRep.delete(emailContactToDelete, "testDELETE");
			emailContactToDelete = em.find(EmailContact.class, 99999l);
			assertFalse("emailContactDelete method failed. EmailContact not disactivated.", emailContactToDelete.isActive());
		} catch (Exception e){
			fail("emailContactDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void emailContactCount(){
		Long result;
		
		try{
			result = emailContactRep.count(null);
			fail("emailContactCount method with user parameter = null failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("emailContactCount method with user parameter = null fail. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = emailContactRep.count("");
			fail("emailContactCount method with user parameter = '' failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("emailContactCount method with user parameter = '' fail. Unexpected exception catched. "+e.toString());
		}
		
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
}
