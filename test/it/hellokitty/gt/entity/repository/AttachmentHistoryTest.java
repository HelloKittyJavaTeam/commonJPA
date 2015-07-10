package it.hellokitty.gt.entity.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.AttachmentHistory;
import it.hellokitty.gt.repository.impl.AttachmentHistoryRepositoryImpl;
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

public class AttachmentHistoryTest {
	private AttachmentHistoryRepositoryImpl attachmentHistoryRep = new AttachmentHistoryRepositoryImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static AttachmentHistory attachmentHistoryAdd;

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			attachmentHistoryAdd = new AttachmentHistory();
			attachmentHistoryAdd.setUserCreated("testADD"+i);
			attachmentHistoryAdd.setActive(true);
			attachmentHistoryAdd.setId(99999l+i);
			em.persist(attachmentHistoryAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			em.remove(em.find(AttachmentHistory.class, 99999l+i));
		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void attachmentHistoryFetchById(){
		try {
			AttachmentHistory emailContact = attachmentHistoryRep.fetchById(99999l);
			assertNotNull("No AttachmentHistory returned from fetchById", emailContact);
			emailContact = attachmentHistoryRep.fetchById(987654321l);
			assertNull(emailContact);
		} catch (Exception e) {
			fail("Caught Exception in attachmentHistoryFetchById method. "+e.toString());
		}
	}
	
	@Test
	public void attachmentHistoryFetchAll(){
		ColumnDirection cd = new ColumnDirection("id", "asc");
		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
		cdList.add(cd);
		try {
			List<AttachmentHistory> attachmentHistorys = attachmentHistoryRep.fetchAll(0, 20, cdList);
			assertTrue("attachmentHistoryFetchAll returned a empty list.", attachmentHistorys.size() != 0);
			assertTrue("attachmentHistoryFetchAll didn't return all the elements.", attachmentHistorys.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in attachmentHistoryFetchAll method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void attachmentHistoryInsert(){
		AttachmentHistory attachmentHistoryAdd = new AttachmentHistory();
		attachmentHistoryAdd.setId(98989898l);
		
		try{
			attachmentHistoryRep.insert(attachmentHistoryAdd, "testADD");
			assertNotNull(em.find(AttachmentHistory.class, 98989898l));
		} catch (Exception e){
			fail("emailContactInsert method failed. Unexpected Exception catched. "+e.getMessage());
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
		attachmentHistoryToMerge.setUserUpdate("TESTUSERMERGE");
		
		try{
			attachmentHistoryRep.merge(attachmentHistoryToMerge, "TESTUSERMERGE");
			attachmentHistoryToMerge = em.find(AttachmentHistory.class, 99999l);
			assertTrue("attachmentHistoryMerge method failed. UserUpdate value not updated."
					+ "Current value: "+attachmentHistoryToMerge.getUserUpdate()+" "
					+ "Expected value: TESTUSERMERGE.", attachmentHistoryToMerge.getUserUpdate().equals("TESTUSERMERGE"));
		} catch (Exception e){
			fail("attachmentHistoryMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryToMerge = new AttachmentHistory();
			attachmentHistoryToMerge.setId(9898989898l);
			attachmentHistoryToMerge.setUserCreated("test");
			attachmentHistoryToMerge.setUserUpdate("TESTNAMEMERGE");
			attachmentHistoryRep.merge(attachmentHistoryToMerge, "testMERGE");
			assertNotNull("attachmentHistoryMerge method fail. No element added.", em.find(AttachmentHistory.class, 9898989898l));
		} catch (Exception e){
			fail("attachmentHistoryMerge method fail during merge on inexistent attachmentHistory. Unexpected exception catched. "+e.toString());
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
		attachmentHistoryToUpdate.setUserUpdate("TESTNAMEMERGE");
		
		try{
			attachmentHistoryRep.update(attachmentHistoryToUpdate, "testUPDATE");
			attachmentHistoryToUpdate = em.find(AttachmentHistory.class, 99999l);
			assertTrue("emailContactUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+attachmentHistoryToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", attachmentHistoryToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("emailContactUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			attachmentHistoryToUpdate = new AttachmentHistory();
			attachmentHistoryToUpdate.setId(9898989898l);
			attachmentHistoryToUpdate.setUserCreated("test");
			attachmentHistoryToUpdate.setUserUpdate("NAME TEST ITA");
			attachmentHistoryRep.update(attachmentHistoryToUpdate, "testUPDATE");
			fail("attachmentHistoryUpdate method failed. No IllegalArgumentException thrown.");
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
	public void attachmentHistoryDelete(){
		AttachmentHistory attachmentHistoryToDelete = new AttachmentHistory();
		attachmentHistoryToDelete = em.find(AttachmentHistory.class, 99999l);
		
		try {
			attachmentHistoryRep.delete(attachmentHistoryToDelete, "testDELETE");
			attachmentHistoryToDelete = em.find(AttachmentHistory.class, 99999l);
			assertFalse("attachmentHistoryDelete method failed. EmailContact not disactivated.", attachmentHistoryToDelete.isActive());
		} catch (Exception e){
			fail("attachmentHistoryDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
		
		attachmentHistoryToDelete = new AttachmentHistory();
		attachmentHistoryToDelete.setId(987987987l);
		attachmentHistoryToDelete.setUserCreated("testUSER");
		attachmentHistoryToDelete.setCreateDate(new Date());
		attachmentHistoryToDelete.setActive(false);
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		em.persist(attachmentHistoryToDelete);
		transaction.commit();
		
		try{
			attachmentHistoryRep.delete(attachmentHistoryToDelete, "admin");
			em = Persistence.createEntityManagerFactory("PERSISTENCE_UNIT_NAME").createEntityManager();
			assertNull("attachmentHistoryDelete method failed. EmailContact with id 987987987 not deleted. ID: "+attachmentHistoryToDelete.getId(), em.find(AttachmentHistory.class, 987987987l));

		} catch (Exception e){
			fail("attachmentHistoryDelete method failed during delete from admin. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void attachmentHistoryCount(){
		Long result;
		
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