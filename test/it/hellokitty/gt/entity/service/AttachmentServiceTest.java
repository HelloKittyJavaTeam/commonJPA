package it.hellokitty.gt.entity.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.Attachment;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.impl.AttachmentServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AttachmentServiceTest {
	private AttachmentServiceImpl attachmentRep = new AttachmentServiceImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static Attachment attachmentAdd;
	private static List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			attachmentAdd = new Attachment();
			attachmentAdd.setId(99999l+i);
			attachmentAdd.setUserCreated("testADD"+i);
			attachmentAdd.setCreateDate(new Date());
			attachmentAdd.setActive(true);

			em.persist(attachmentAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			attachmentAdd = new Attachment();
			attachmentAdd.setId(99999l+i);
			attachmentAdd.setUserCreated("testADD"+i);
			em.remove(em.find(Attachment.class, 99999l+i));

		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void attachmentFetchById(){
		try{
			attachmentRep.fetchById(null);
			fail("attachmentFetchById method with id = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentFetchById method with id = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.fetchById(-1l);
			fail("attachmentFetchById method with id = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentFetchById method with id = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			Attachment attachment = attachmentRep.fetchById(99999l);
			assertNotNull("No Attachment returned from fetchById", attachment);
			attachment= attachmentRep.fetchById(987654321l);
			assertNull(attachment);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in attachmentFetchById method.");
		}
	}
	
	@Test
	public void attachmentFetchAll(){
		cdList = new ArrayList<ColumnDirection>();
		ColumnDirection cd = new ColumnDirection("id", "asc");
		cdList.add(cd);
		try{
			attachmentRep.fetchAll(null, 10, cdList);
			fail("attachmentFetchAll method with start parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentFetchAll method with start parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.fetchAll(-1, 10, cdList);
			fail("attachmentFetchAll method with start parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentFetchAll method with start parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.fetchAll(0, null, cdList);
			fail("attachmentFetchAll method with limit parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentFetchAll method with limit parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.fetchAll(0, -1, cdList);
			fail("attachmentFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.fetchAll(0, 10, null);
			fail("attachmentFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			List<Attachment> attachmentList = attachmentRep.fetchAll(0, 20, cdList);
			assertTrue("attachmentFetchAll returned a empty list.", attachmentList.size() > 0);
			assertTrue("attachmentFetchAll didn't return all the elements.", attachmentList.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in attachmentFetchById method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void attachmentInsert(){
		Attachment attachmentToAdd = new Attachment();
		attachmentToAdd.setId(98989898l);
		
		try{
			attachmentRep.insert(null, "testADD");
			fail("attachmentInsert method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentInsert method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.insert(attachmentToAdd, null);
			fail("attachmentInsert method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentInsert method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.insert(attachmentToAdd, "");
			fail("attachmentInsert method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentInsert method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}

		try{
			attachmentRep.insert(attachmentToAdd, "testADD");
			assertNotNull(em.find(Attachment.class, 98989898l));
		} catch (Exception e){
			fail("attachmentInsert method failed. Unexpected Exception catched. "+e.getMessage());
		}
		
		em.remove(em.find(Attachment.class, 98989898l));
	}
	
	/*
	 *  MERGE TEST
	 */
	@Test
	public void attachmentMerge(){
		Attachment attachmentToMerge = new Attachment();
		attachmentToMerge = em.find(Attachment.class, 99999l);
		attachmentToMerge.setFileName("NOME MODIFICATO");
		
		try{
			attachmentRep.merge(null, "testADD");
			fail("attachmentMerge method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentMerge method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.merge(attachmentToMerge, null);
			fail("attachmentMerge method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentMerge method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.merge(attachmentToMerge, "");
			fail("attachmentMerge method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentMerge method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.merge(attachmentToMerge, "testMERGE");
			attachmentToMerge = em.find(Attachment.class, 99999l);
			assertTrue("attachmentMErge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+attachmentToMerge.getFileName()+" "
					+ "Expected value: NOME MODIFICATO.",attachmentToMerge.getFileName().equals("NOME MODIFICATO"));
			assertTrue("attachmentMerge method failed. UserUpdate value not updated."
					+ "Current value: "+attachmentToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", attachmentToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("attachmentMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			attachmentToMerge = new Attachment();
			attachmentToMerge.setId(9898989898l);
			attachmentToMerge.setUserCreated("test");
			attachmentRep.merge(attachmentToMerge, "testMERGE");
			assertNotNull("attachmentMerge method fail. No element added.", em.find(Attachment.class, 9898989898l));
		} catch (Exception e){
			fail("attachmentMErge method fail during merge on inexistent attachment. Unexpected exception catched. "+e.toString());
		}
		em.remove(em.find(Attachment.class, 9898989898l));
	}
	
	/*
	 *  UPDATE TEST
	 */
	@Test
	public void attachmentUpdate(){
		Attachment attachmentToUpdate = new Attachment();
		attachmentToUpdate = em.find(Attachment.class, 99999l);
		attachmentToUpdate.setFileName("NOME MODIFICATO");
		
		try{
			attachmentRep.update(null, "testADD");
			fail("attachmentUpdate method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentUpdate method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.update(attachmentToUpdate, null);
			fail("attachmentUpdate method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentUpdate method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.update(attachmentToUpdate, "");
			fail("attachmentUpdate method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentUpdate method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.update(attachmentToUpdate, "testUPDATE");
			attachmentToUpdate = em.find(Attachment.class, 99999l);
			assertTrue("attachmentUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+attachmentToUpdate.getFileName()+" "
					+ "Expected value: NOME MODIFICATO.",attachmentToUpdate.getFileName().equals("NOME MODIFICATO"));
			assertTrue("attachmentUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+attachmentToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", attachmentToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("attachmentUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			attachmentToUpdate = new Attachment();
			attachmentToUpdate.setId(9898989898l);
			attachmentToUpdate.setUserCreated("test");
			attachmentRep.update(attachmentToUpdate, "testUPDATE");
			fail("attachmentUpdate method failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("attachmentUpdate method fail during merge on inexistent attachment. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  DELETE TEST
	 */
	@Test
	public void attachmentDelete(){
		Attachment attachmentToDelete = new Attachment();
		attachmentToDelete = em.find(Attachment.class, 99999l);
		
		try{
			attachmentRep.update(null, "testADD");
			fail("attachmentDelete method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentDelete method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.update(attachmentToDelete, null);
			fail("attachmentDelete method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentDelete method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			attachmentRep.update(attachmentToDelete, "");
			fail("attachmentDelete method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("attachmentDelete method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			attachmentRep.delete(attachmentToDelete, "testDELETE");
			attachmentToDelete = em.find(Attachment.class, 99999l);
			assertFalse("attachmentDelete method failed. Attachment not disactivated.", attachmentToDelete.isActive());
		} catch (Exception e){
			fail("attachmentDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void attachmentCount(){
		Long result;
		
		try{
			result = attachmentRep.count(null);
			fail("attachmentCount method with user parameter = null failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("attachmentCount method with user parameter = null fail. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = attachmentRep.count("");
			fail("attachmentCount method with user parameter = '' failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("attachmentCount method with user parameter = '' fail. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = attachmentRep.count("testADD0");
			assertTrue("attachmentCount method failed. Number of Attachment expected: 1 Actual:"+result, result==1);
		} catch (Exception e){
			fail("attachmentCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = attachmentRep.count("unknowUser");
			assertTrue("attachmentCount method with user parameter = 'unknowUser' failed. Number of Attachment expected: 0 Actual:"+result, result==0);
		} catch (Exception e){
			fail("attachmentCount method with user parameter = 'unknowUser' failed. Unexpected exception catched. "+e.toString());
		}
	}
}
