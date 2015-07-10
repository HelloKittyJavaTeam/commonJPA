package it.hellokitty.gt.entity.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.AttachmentHistory;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.impl.AttachmentHistoryServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AttachmentHistoryServiceTest {
	private AttachmentHistoryServiceImpl attachmentHistoryRep = new AttachmentHistoryServiceImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static AttachmentHistory attachmentHistoryAdd;
	private static List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			attachmentHistoryAdd = new AttachmentHistory();
			attachmentHistoryAdd.setId(99999l+i);
			attachmentHistoryAdd.setUserCreated("testADD"+i);
			attachmentHistoryAdd.setCreateDate(new Date());
			attachmentHistoryAdd.setActive(true);

			em.persist(attachmentHistoryAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			attachmentHistoryAdd = new AttachmentHistory();
			attachmentHistoryAdd.setId(99999l+i);
			attachmentHistoryAdd.setUserCreated("testADD"+i);
			em.remove(em.find(AttachmentHistory.class, 99999l+i));

		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void attachmentHistoryFetchById(){
		try{
			attachmentHistoryRep.fetchById(null);
			fail("attachmentHistoryFetchById method with id = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryFetchById method with id = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.fetchById(-1l);
			fail("attachmentHistoryFetchById method with id = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryFetchById method with id = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			AttachmentHistory attachmentHistory = attachmentHistoryRep.fetchById(99999l);
			assertNotNull("No AttachmentHistory returned from fetchById", attachmentHistory);
			attachmentHistory= attachmentHistoryRep.fetchById(987654321l);
			assertNull(attachmentHistory);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in attachmentHistoryFetchById method.");
		}
	}
	
	@Test
	public void attachmentHistoryFetchAll(){
		cdList = new ArrayList<ColumnDirection>();
		ColumnDirection cd = new ColumnDirection("id", "asc");
		cdList.add(cd);
		try{
			attachmentHistoryRep.fetchAll(null, 10, cdList);
			fail("attachmentHistoryFetchAll method with start parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryFetchAll method with start parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.fetchAll(-1, 10, cdList);
			fail("attachmentHistoryFetchAll method with start parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryFetchAll method with start parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.fetchAll(0, null, cdList);
			fail("attachmentHistoryFetchAll method with limit parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryFetchAll method with limit parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.fetchAll(0, -1, cdList);
			fail("attachmentHistoryFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.fetchAll(0, 10, null);
			fail("attachmentHistoryFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			List<AttachmentHistory> attachmentHistoryList = attachmentHistoryRep.fetchAll(0, 20, cdList);
			assertTrue("attachmentHistoryFetchAll returned a empty list.", attachmentHistoryList.size() > 0);
			assertTrue("attachmentHistoryFetchAll didn't return all the elements.", attachmentHistoryList.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in attachmentHistoryFetchById method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void attachmentHistoryInsert(){
		AttachmentHistory attachmentHistoryToAdd = new AttachmentHistory();
		attachmentHistoryToAdd.setId(98989898l);
		
		try{
			attachmentHistoryRep.insert(null, "testADD");
			fail("attachmentHistoryInsert method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryInsert method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.insert(attachmentHistoryToAdd, null);
			fail("attachmentHistoryInsert method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryInsert method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.insert(attachmentHistoryToAdd, "");
			fail("attachmentHistoryInsert method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryInsert method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}

		try{
			attachmentHistoryRep.insert(attachmentHistoryToAdd, "testADD");
			assertNotNull(em.find(AttachmentHistory.class, 98989898l));
		} catch (Exception e){
			fail("attachmentHistoryInsert method failed. Unexpected Exception catched. "+e.getMessage());
		}
		
		em.remove(em.find(AttachmentHistory.class, 98989898l));
	}
	
	/*
	 *  MERGE TEST
	 */
	@Test
	public void attachmentHistoryMerge(){
		AttachmentHistory attachmentHistoryToMerge = new AttachmentHistory();
		attachmentHistoryToMerge = em.find(AttachmentHistory.class, 99999l);
		attachmentHistoryToMerge.setnDownload(10l);
		
		try{
			attachmentHistoryRep.merge(null, "testADD");
			fail("attachmentHistoryMerge method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryMerge method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.merge(attachmentHistoryToMerge, null);
			fail("attachmentHistoryMerge method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryMerge method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.merge(attachmentHistoryToMerge, "");
			fail("attachmentHistoryMerge method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryMerge method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.merge(attachmentHistoryToMerge, "testMERGE");
			attachmentHistoryToMerge = em.find(AttachmentHistory.class, 99999l);
			assertTrue("attachmentHistoryMErge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+attachmentHistoryToMerge.getnDownload()+" "
					+ "Expected value: 10.",attachmentHistoryToMerge.getnDownload() == 10l);
			assertTrue("attachmentHistoryMerge method failed. UserUpdate value not updated."
					+ "Current value: "+attachmentHistoryToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", attachmentHistoryToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("attachmentHistoryMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryToMerge = new AttachmentHistory();
			attachmentHistoryToMerge.setId(9898989898l);
			attachmentHistoryToMerge.setUserCreated("test");
			attachmentHistoryRep.merge(attachmentHistoryToMerge, "testMERGE");
			assertNotNull("attachmentHistoryMerge method fail. No element added.", em.find(AttachmentHistory.class, 9898989898l));
		} catch (Exception e){
			fail("attachmentHistoryMErge method fail during merge on inexistent attachmentHistory. Unexpected exception catched. "+e.toString());
		}
		em.remove(em.find(AttachmentHistory.class, 9898989898l));
	}
	
	/*
	 *  UPDATE TEST
	 */
	@Test
	public void attachmentHistoryUpdate(){
		AttachmentHistory attachmentHistoryToUpdate = new AttachmentHistory();
		attachmentHistoryToUpdate = em.find(AttachmentHistory.class, 99999l);
		attachmentHistoryToUpdate.setnDownload(10l);
		
		try{
			attachmentHistoryRep.update(null, "testADD");
			fail("attachmentHistoryUpdate method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryUpdate method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.update(attachmentHistoryToUpdate, null);
			fail("attachmentHistoryUpdate method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryUpdate method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.update(attachmentHistoryToUpdate, "");
			fail("attachmentHistoryUpdate method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryUpdate method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.update(attachmentHistoryToUpdate, "testUPDATE");
			attachmentHistoryToUpdate = em.find(AttachmentHistory.class, 99999l);
			assertTrue("attachmentHistoryUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+attachmentHistoryToUpdate.getnDownload()+" "
					+ "Expected value: 10.",attachmentHistoryToUpdate.getnDownload() == 10l);
			assertTrue("attachmentHistoryUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+attachmentHistoryToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", attachmentHistoryToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("attachmentHistoryUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryToUpdate = new AttachmentHistory();
			attachmentHistoryToUpdate.setId(9898989898l);
			attachmentHistoryToUpdate.setUserCreated("test");
			attachmentHistoryRep.update(attachmentHistoryToUpdate, "testUPDATE");
			fail("attachmentHistoryUpdate method failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentHistoryUpdate method fail during merge on inexistent attachmentHistory. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  DELETE TEST
	 */
	@Test
	public void attachmentHistoryDelete(){
		AttachmentHistory attachmentHistoryToDelete = new AttachmentHistory();
		attachmentHistoryToDelete = em.find(AttachmentHistory.class, 99999l);
		
		try{
			attachmentHistoryRep.update(null, "testADD");
			fail("attachmentHistoryDelete method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryDelete method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.update(attachmentHistoryToDelete, null);
			fail("attachmentHistoryDelete method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryDelete method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryRep.update(attachmentHistoryToDelete, "");
			fail("attachmentHistoryDelete method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentHistoryDelete method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			attachmentHistoryRep.delete(attachmentHistoryToDelete, "testDELETE");
			attachmentHistoryToDelete = em.find(AttachmentHistory.class, 99999l);
			assertFalse("attachmentHistoryDelete method failed. AttachmentHistory not disactivated.", attachmentHistoryToDelete.isActive());
		} catch (Exception e){
			fail("attachmentHistoryDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void attachmentHistoryCount(){
		Long result;
		
		try{
			result = attachmentHistoryRep.count(null);
			fail("attachmentHistoryCount method with user parameter = null failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("attachmentHistoryCount method with user parameter = null fail. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = attachmentHistoryRep.count("");
			fail("attachmentHistoryCount method with user parameter = '' failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("attachmentHistoryCount method with user parameter = '' fail. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = attachmentHistoryRep.count("testADD0");
			assertTrue("attachmentHistoryCount method failed. Number of AttachmentHistory expected: 1 Actual:"+result, result==1);
		} catch (Exception e){
			fail("attachmentHistoryCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = attachmentHistoryRep.count("unknowUser");
			assertTrue("attachmentHistoryCount method with user parameter = 'unknowUser' failed. Number of AttachmentHistory expected: 0 Actual:"+result, result==0);
		} catch (Exception e){
			fail("attachmentHistoryCount method with user parameter = 'unknowUser' failed. Unexpected exception catched. "+e.toString());
		}
	}
}
