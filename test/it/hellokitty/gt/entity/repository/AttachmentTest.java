package it.hellokitty.gt.entity.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.Attachment;
import it.hellokitty.gt.repository.impl.AttachmentRepositoryImpl;
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

public class AttachmentTest {
	private AttachmentRepositoryImpl attachmentRep = new AttachmentRepositoryImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static Attachment attachmentAdd;

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			attachmentAdd = new Attachment();
			attachmentAdd.setUserCreated("testADD"+i);
			attachmentAdd.setActive(true);
			attachmentAdd.setId(99999l+i);
			attachmentAdd.setFileName("testFILE"+i);
			em.persist(attachmentAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			em.remove(em.find(Attachment.class, 99999l+i));
		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void attachmentFetchById(){
		try {
			Attachment attachment = attachmentRep.fetchById(99999l);
			assertNotNull("No Attachment returned from fetchById", attachment);
			assertTrue("attachmentFetchById method failed on retrieve content value. "
					+ "Actual value: "+attachment.getFileName()+" "
					+ "Expected value: testFILE0", attachment.getFileName().equals("testFILE0"));
			attachment = attachmentRep.fetchById(987654321l);
			assertNull(attachment);
		} catch (Exception e) {
			fail("Caught Exception in attachmentFetchById method. "+e.toString());
		}
	}
	
	@Test
	public void attachmentFetchAll(){
		ColumnDirection cd = new ColumnDirection("id", "asc");
		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
		cdList.add(cd);
		try {
			List<Attachment> attachments = attachmentRep.fetchAll(0, 20, cdList);
			assertTrue("attachmentFetchAll returned a empty list.", attachments.size() > 0);
			assertTrue("attachmentFetchAll didn't return all the elements.", attachments.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in attachmentFetchAll method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void attachmentInsert(){
		Attachment attachmentAdd = new Attachment();
		attachmentAdd.setId(98989898l);
		
		try{
			attachmentRep.insert(attachmentAdd, "testADD");
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
		attachmentToMerge.setFileName("TESTNAMEMERGE");
		
		try{
			attachmentRep.merge(attachmentToMerge, "testMERGE");
			attachmentToMerge = em.find(Attachment.class, 99999l);
			assertTrue("attachmentMerge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+attachmentToMerge.getFileName()+" "
					+ "Expected value: TESTNAMEMERGE.",attachmentToMerge.getFileName().equals("TESTNAMEMERGE"));
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
			attachmentToMerge.setFileName("TESTNAMEMERGE");
			attachmentRep.merge(attachmentToMerge, "testMERGE");
			assertNotNull("attachmentMerge method fail. No element added.", em.find(Attachment.class, 9898989898l));
		} catch (Exception e){
			fail("attachmentMerge method fail during merge on inexistent attachment. Unexpected exception catched. "+e.toString());
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
		attachmentToUpdate.setFileName("TESTNAMEMERGE");
		
		try{
			attachmentRep.update(attachmentToUpdate, "testUPDATE");
			attachmentToUpdate = em.find(Attachment.class, 99999l);
			assertTrue("attachmentUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+attachmentToUpdate.getFileName()+" "
					+ "Expected value: TESTNAMEMERGE.",attachmentToUpdate.getFileName().equals("TESTNAMEMERGE"));
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
			attachmentToUpdate.setFileName("NAME TEST ITA");
			attachmentRep.update(attachmentToUpdate, "testUPDATE");
			fail("attachmentUpdate method failed. IllegalArgumentException not thrown.");
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
		
		try {
			attachmentRep.delete(attachmentToDelete, "testDELETE");
			attachmentToDelete = em.find(Attachment.class, 99999l);
			assertFalse("attachmentDelete method failed. EmailContact not disactivated.", attachmentToDelete.isActive());
		} catch (Exception e){
			fail("attachmentDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
		
		attachmentToDelete = new Attachment();
		attachmentToDelete.setId(987987987l);
		attachmentToDelete.setUserCreated("testUSER");
		attachmentToDelete.setCreateDate(new Date());
		attachmentToDelete.setActive(false);
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		em.persist(attachmentToDelete);
		transaction.commit();
		
		try{
			attachmentRep.delete(attachmentToDelete, "admin");
			em = Persistence.createEntityManagerFactory("PERSISTENCE_UNIT_NAME").createEntityManager();
			assertNull("attachmentDelete method failed. EmailContact with id 987987987 not deleted. ID: "+attachmentToDelete.getId(), em.find(Attachment.class, 987987987l));

		} catch (Exception e){
			fail("attachmentDelete method failed during delete from admin. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void attachmentCount(){
		Long result;
		
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
