package it.hellokitty.gt.entity.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.Tag;
import it.hellokitty.gt.repository.impl.TagRepositoryImpl;
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

public class TagTest {
	private TagRepositoryImpl tagRep = new TagRepositoryImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static Tag tagAdd;

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			tagAdd = new Tag();
			tagAdd.setUserCreated("testADD"+i);
			tagAdd.setActive(true);
			tagAdd.setId(99999l+i);
			tagAdd.setWord("testWORD"+i);
			em.persist(tagAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			em.remove(em.find(Tag.class, 99999l+i));
		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void tagFetchById(){
		try {
			Tag emailContact = tagRep.fetchById(99999l);
			assertNotNull("No Tag returned from fetchById", emailContact);
			assertTrue("tagFetchById method failed on retrieve content value. "
					+ "Actual value: "+emailContact.getWord()+" "
					+ "Expected value: testWORD0", emailContact.getWord().equals("testWORD0"));
			emailContact = tagRep.fetchById(987654321l);
			assertNull(emailContact);
		} catch (Exception e) {
			fail("Caught Exception in tagFetchById method. "+e.toString());
		}
	}
	
	@Test
	public void tagFetchAll(){
		ColumnDirection cd = new ColumnDirection("id", "asc");
		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
		cdList.add(cd);
		try {
			List<Tag> tags = tagRep.fetchAll(0, 20, cdList);
			assertTrue("tagFetchAll returned a empty list.", tags.size() > 0);
			assertTrue("tagFetchAll didn't return all the elements.", tags.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in tagFetchAll method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void tagInsert(){
		Tag tagAdd = new Tag();
		tagAdd.setId(98989898l);
		
		try{
			tagRep.insert(tagAdd, "testADD");
			assertNotNull(em.find(Tag.class, 98989898l));
		} catch (Exception e){
			fail("emailContactInsert method failed. Unexpected Exception catched. "+e.getMessage());
		}
		
		em.remove(em.find(Tag.class, 98989898l));
	}
	
	/*
	 *  MERGE TEST
	 */
	@Test
	public void tagMerge(){
		Tag tagToMerge = new Tag();
		tagToMerge = em.find(Tag.class, 99999l);
		tagToMerge.setWord("TESTNAMEMERGE");
		
		try{
			tagRep.merge(tagToMerge, "testMERGE");
			tagToMerge = em.find(Tag.class, 99999l);
			assertTrue("tagMerge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+tagToMerge.getWord()+" "
					+ "Expected value: TESTNAMEMERGE.",tagToMerge.getWord().equals("TESTNAMEMERGE"));
			assertTrue("tagMerge method failed. UserUpdate value not updated."
					+ "Current value: "+tagToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", tagToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("tagMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			tagToMerge = new Tag();
			tagToMerge.setId(9898989898l);
			tagToMerge.setUserCreated("test");
			tagToMerge.setWord("TESTNAMEMERGE");
			tagRep.merge(tagToMerge, "testMERGE");
			assertNotNull("tagMerge method fail. No element added.", em.find(Tag.class, 9898989898l));
		} catch (Exception e){
			fail("tagMerge method fail during merge on inexistent tag. Unexpected exception catched. "+e.toString());
		}
		em.remove(em.find(Tag.class, 9898989898l));
	}
	
	/*
	 *  UPDATE TEST
	 */
	@Test
	public void tagUpdate(){
		Tag tagToUpdate = new Tag();
		tagToUpdate = em.find(Tag.class, 99999l);
		tagToUpdate.setWord("TESTNAMEMERGE");
		
		try{
			tagRep.update(tagToUpdate, "testUPDATE");
			tagToUpdate = em.find(Tag.class, 99999l);
			assertTrue("emailContactUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+tagToUpdate.getWord()+" "
					+ "Expected value: TESTNAMEMERGE.",tagToUpdate.getWord().equals("TESTNAMEMERGE"));
			assertTrue("emailContactUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+tagToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", tagToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("emailContactUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			tagToUpdate = new Tag();
			tagToUpdate.setId(9898989898l);
			tagToUpdate.setUserCreated("test");
			tagToUpdate.setWord("NAME TEST ITA");
			tagRep.update(tagToUpdate, "testUPDATE");
			fail("tagUpdate method failed. No IllegalArgumentException thrown.");
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
	public void tagDelete(){
		Tag tagToDelete = new Tag();
		tagToDelete = em.find(Tag.class, 99999l);
		
		try {
			tagRep.delete(tagToDelete, "testDELETE");
			tagToDelete = em.find(Tag.class, 99999l);
			assertFalse("tagDelete method failed. EmailContact not disactivated.", tagToDelete.isActive());
		} catch (Exception e){
			fail("tagDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
		
		tagToDelete = new Tag();
		tagToDelete.setId(987987987l);
		tagToDelete.setUserCreated("testUSER");
		tagToDelete.setCreateDate(new Date());
		tagToDelete.setActive(false);
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		em.persist(tagToDelete);
		transaction.commit();
		
		try{
			tagRep.delete(tagToDelete, "admin");
			em = Persistence.createEntityManagerFactory("PERSISTENCE_UNIT_NAME").createEntityManager();
			assertNull("tagDelete method failed. EmailContact with id 987987987 not deleted. ID: "+tagToDelete.getId(), em.find(Tag.class, 987987987l));

		} catch (Exception e){
			fail("tagDelete method failed during delete from admin. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void tagCount(){
		Long result;
		
		try{
			result = tagRep.count("testADD0");
			assertTrue("tagCount method failed. Number of Tag expected: 1 Actual:"+result, result==1);
		} catch (Exception e){
			fail("tagCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = tagRep.count("unknowUser");
			assertTrue("tagCount method with user parameter = 'unknowUser' failed. Number of Tag expected: 0 Actual:"+result, result==0);
		} catch (Exception e){
			fail("tagCount method with user parameter = 'unknowUser' failed. Unexpected exception catched. "+e.toString());
		}
	}
}
