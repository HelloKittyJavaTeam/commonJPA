package it.hellokitty.gt.entity.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.MailingList;
import it.hellokitty.gt.repository.impl.MailingListRepositoryImpl;
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


public class MailingListTest {
	private MailingListRepositoryImpl mailingListRep = new MailingListRepositoryImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static MailingList mailingAdd;

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			mailingAdd = new MailingList();
			mailingAdd.setUserCreated("testADD"+i);
			mailingAdd.setActive(true);
			mailingAdd.setId(99999l+i);
			mailingAdd.setName("testNAME"+i);
			em.persist(mailingAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			em.remove(em.find(MailingList.class, 99999l+i));
		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void mailingListFetchById(){
		try {
			MailingList emailContact = mailingListRep.fetchById(99999l);
			assertNotNull("No MailingList returned from fetchById", emailContact);
			assertTrue("mailingListFetchById method failed on retrieve content value. "
					+ "Actual value: "+emailContact.getName()+" "
					+ "Expected value: testNAME0", emailContact.getName().equals("testNAME0"));
			emailContact = mailingListRep.fetchById(987654321l);
			assertNull(emailContact);
		} catch (Exception e) {
			fail("Caught Exception in mailingListFetchById method. "+e.toString());
		}
	}
	
	@Test
	public void mailingListFetchAll(){
		ColumnDirection cd = new ColumnDirection("id", "asc");
		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
		cdList.add(cd);
		try {
			List<MailingList> mailingLists = mailingListRep.fetchAll(0, 20, cdList);
			assertTrue("mailingListFetchAll returned a empty list.", mailingLists.size() > 0);
			assertTrue("mailingListFetchAll didn't return all the elements.", mailingLists.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in mailingListFetchAll method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void mailingListInsert(){
		MailingList mailingAdd = new MailingList();
		mailingAdd.setId(98989898l);
		
		try{
			mailingListRep.insert(mailingAdd, "testADD");
			assertNotNull(em.find(MailingList.class, 98989898l));
		} catch (Exception e){
			fail("emailContactInsert method failed. Unexpected Exception catched. "+e.getMessage());
		}
		
		em.remove(em.find(MailingList.class, 98989898l));
	}
	
	/*
	 *  MERGE TEST
	 */
	@Test
	public void mailingListMerge(){
		MailingList mailingToMerge = new MailingList();
		mailingToMerge = em.find(MailingList.class, 99999l);
		mailingToMerge.setName("TESTNAMEMERGE");
		
		try{
			mailingListRep.merge(mailingToMerge, "testMERGE");
			mailingToMerge = em.find(MailingList.class, 99999l);
			assertTrue("mailingListMerge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+mailingToMerge.getName()+" "
					+ "Expected value: TESTNAMEMERGE.",mailingToMerge.getName().equals("TESTNAMEMERGE"));
			assertTrue("mailingListMerge method failed. UserUpdate value not updated."
					+ "Current value: "+mailingToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", mailingToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("mailingListMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			mailingToMerge = new MailingList();
			mailingToMerge.setId(9898989898l);
			mailingToMerge.setUserCreated("test");
			mailingToMerge.setName("TESTNAMEMERGE");
			mailingListRep.merge(mailingToMerge, "testMERGE");
			assertNotNull("mailingListMerge method fail. No element added.", em.find(MailingList.class, 9898989898l));
		} catch (Exception e){
			fail("mailingListMerge method fail during merge on inexistent mailingList. Unexpected exception catched. "+e.toString());
		}
		em.remove(em.find(MailingList.class, 9898989898l));
	}
	
	/*
	 *  UPDATE TEST
	 */
	@Test
	public void mailingListUpdate(){
		MailingList mailingToUpdate = new MailingList();
		mailingToUpdate = em.find(MailingList.class, 99999l);
		mailingToUpdate.setName("TESTNAMEMERGE");
		
		try{
			mailingListRep.update(mailingToUpdate, "testUPDATE");
			mailingToUpdate = em.find(MailingList.class, 99999l);
			assertTrue("emailContactUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+mailingToUpdate.getName()+" "
					+ "Expected value: TESTNAMEMERGE.",mailingToUpdate.getName().equals("TESTNAMEMERGE"));
			assertTrue("emailContactUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+mailingToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", mailingToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("emailContactUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			mailingToUpdate = new MailingList();
			mailingToUpdate.setId(9898989898l);
			mailingToUpdate.setUserCreated("test");
			mailingToUpdate.setName("NAME TEST ITA");
			mailingListRep.update(mailingToUpdate, "testUPDATE");
			fail("mailingListUpdate method failed. No IllegalArgumentException thrown.");
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
	public void mailingListDelete(){
		MailingList mailingListToDelete = new MailingList();
		mailingListToDelete = em.find(MailingList.class, 99999l);
		
		try {
			mailingListRep.delete(mailingListToDelete, "testDELETE");
			mailingListToDelete = em.find(MailingList.class, 99999l);
			assertFalse("mailingListDelete method failed. EmailContact not disactivated.", mailingListToDelete.isActive());
		} catch (Exception e){
			fail("mailingListDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
		
		mailingListToDelete = new MailingList();
		mailingListToDelete.setId(987987987l);
		mailingListToDelete.setUserCreated("testUSER");
		mailingListToDelete.setCreateDate(new Date());
		mailingListToDelete.setActive(false);
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		em.persist(mailingListToDelete);
		transaction.commit();
		
		try{
			mailingListRep.delete(mailingListToDelete, "admin");
			em = Persistence.createEntityManagerFactory("PERSISTENCE_UNIT_NAME").createEntityManager();
			assertNull("mailingListDelete method failed. EmailContact with id 987987987 not deleted. ID: "+mailingListToDelete.getId(), em.find(MailingList.class, 987987987l));

		} catch (Exception e){
			fail("mailingListDelete method failed during delete from admin. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void mailingListCount(){
		Long result;
		
		try{
			result = mailingListRep.count("testADD0");
			assertTrue("mailingListCount method failed. Number of MailingList expected: 1 Actual:"+result, result==1);
		} catch (Exception e){
			fail("mailingListCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = mailingListRep.count("unknowUser");
			assertTrue("mailingListCount method with user parameter = 'unknowUser' failed. Number of MailingList expected: 0 Actual:"+result, result==0);
		} catch (Exception e){
			fail("mailingListCount method with user parameter = 'unknowUser' failed. Unexpected exception catched. "+e.toString());
		}
	}
}