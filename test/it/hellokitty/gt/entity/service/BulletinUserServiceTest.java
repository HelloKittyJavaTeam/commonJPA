package it.hellokitty.gt.entity.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.BulletinUser;
import it.hellokitty.gt.repository.utils.ColumnDirection;
import it.hellokitty.gt.service.impl.BulletinUserServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BulletinUserServiceTest {
	private BulletinUserServiceImpl bulletinUserRep = new BulletinUserServiceImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static BulletinUser bulletinUserAdd;
	private static List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			bulletinUserAdd = new BulletinUser();
			bulletinUserAdd.setId(99999l+i);
			bulletinUserAdd.setUserCreated("testADD"+i);
			bulletinUserAdd.setCreateDate(new Date());
			bulletinUserAdd.setActive(true);

			em.persist(bulletinUserAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			bulletinUserAdd = new BulletinUser();
			bulletinUserAdd.setId(99999l+i);
			bulletinUserAdd.setUserCreated("testADD"+i);
			em.remove(em.find(BulletinUser.class, 99999l+i));

		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void bulletinUserFetchById(){
		try{
			bulletinUserRep.fetchById(null);
			fail("bulletinUserFetchById method with id = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserFetchById method with id = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.fetchById(-1l);
			fail("bulletinUserFetchById method with id = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserFetchById method with id = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			BulletinUser bulletinUser = bulletinUserRep.fetchById(99999l);
			assertNotNull("No BulletinUser returned from fetchById", bulletinUser);
			bulletinUser= bulletinUserRep.fetchById(987654321l);
			assertNull(bulletinUser);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in bulletinUserFetchById method.");
		}
	}
	
	@Test
	public void bulletinUserFetchAll(){
		cdList = new ArrayList<ColumnDirection>();
		ColumnDirection cd = new ColumnDirection("id", "asc");
		cdList.add(cd);
		try{
			bulletinUserRep.fetchAll(null, 10, cdList);
			fail("bulletinUserFetchAll method with start parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserFetchAll method with start parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.fetchAll(-1, 10, cdList);
			fail("bulletinUserFetchAll method with start parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserFetchAll method with start parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.fetchAll(0, null, cdList);
			fail("bulletinUserFetchAll method with limit parameter = null failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserFetchAll method with limit parameter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.fetchAll(0, -1, cdList);
			fail("bulletinUserFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.fetchAll(0, 10, null);
			fail("bulletinUserFetchAll method with limit parameter = -1 failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserFetchAll method with limit parameter = -1 failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			List<BulletinUser> bulletinUserList = bulletinUserRep.fetchAll(0, 20, cdList);
			assertTrue("bulletinUserFetchAll returned a empty list.", bulletinUserList.size() > 0);
			assertTrue("bulletinUserFetchAll didn't return all the elements.", bulletinUserList.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in bulletinUserFetchById method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void bulletinUserInsert(){
		BulletinUser bulletinUserToAdd = new BulletinUser();
		bulletinUserToAdd.setId(98989898l);
		
		try{
			bulletinUserRep.insert(null, "testADD");
			fail("bulletinUserInsert method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserInsert method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.insert(bulletinUserToAdd, null);
			fail("bulletinUserInsert method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserInsert method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.insert(bulletinUserToAdd, "");
			fail("bulletinUserInsert method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserInsert method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}

		try{
			bulletinUserRep.insert(bulletinUserToAdd, "testADD");
			assertNotNull(em.find(BulletinUser.class, 98989898l));
		} catch (Exception e){
			fail("bulletinUserInsert method failed. Unexpected Exception catched. "+e.getMessage());
		}
		
		em.remove(em.find(BulletinUser.class, 98989898l));
	}
	
	/*
	 *  MERGE TEST
	 */
	@Test
	public void bulletinUserMerge(){
		BulletinUser bulletinUserToMerge = new BulletinUser();
		bulletinUserToMerge = em.find(BulletinUser.class, 99999l);
		bulletinUserToMerge.setnRead(10l);
		
		try{
			bulletinUserRep.merge(null, "testADD");
			fail("bulletinUserMerge method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserMerge method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.merge(bulletinUserToMerge, null);
			fail("bulletinUserMerge method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserMerge method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.merge(bulletinUserToMerge, "");
			fail("bulletinUserMerge method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserMerge method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.merge(bulletinUserToMerge, "testMERGE");
			bulletinUserToMerge = em.find(BulletinUser.class, 99999l);
			assertTrue("bulletinUserMErge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+bulletinUserToMerge.getnRead()+" "
					+ "Expected value: 10.",bulletinUserToMerge.getnRead()==10l);
			assertTrue("bulletinUserMerge method failed. UserUpdate value not updated."
					+ "Current value: "+bulletinUserToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", bulletinUserToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("bulletinUserMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			bulletinUserToMerge = new BulletinUser();
			bulletinUserToMerge.setId(9898989898l);
			bulletinUserToMerge.setUserCreated("test");
			bulletinUserRep.merge(bulletinUserToMerge, "testMERGE");
			assertNotNull("bulletinUserMerge method fail. No element added.", em.find(BulletinUser.class, 9898989898l));
		} catch (Exception e){
			fail("bulletinUserMErge method fail during merge on inexistent bulletinUser. Unexpected exception catched. "+e.toString());
		}
		em.remove(em.find(BulletinUser.class, 9898989898l));
	}
	
	/*
	 *  UPDATE TEST
	 */
	@Test
	public void bulletinUserUpdate(){
		BulletinUser bulletinUserToUpdate = new BulletinUser();
		bulletinUserToUpdate = em.find(BulletinUser.class, 99999l);
		bulletinUserToUpdate.setnRead(10l);;
		
		try{
			bulletinUserRep.update(null, "testADD");
			fail("bulletinUserUpdate method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserUpdate method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.update(bulletinUserToUpdate, null);
			fail("bulletinUserUpdate method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserUpdate method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.update(bulletinUserToUpdate, "");
			fail("bulletinUserUpdate method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserUpdate method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.update(bulletinUserToUpdate, "testUPDATE");
			bulletinUserToUpdate = em.find(BulletinUser.class, 99999l);
			assertTrue("bulletinUserUpdate method failed. ItContent value wrong or not updated. "
					+ "Current value: "+bulletinUserToUpdate.getnRead()+" "
					+ "Expected value: 10.",bulletinUserToUpdate.getnRead() == 10l);
			assertTrue("bulletinUserUpdate method failed. UserUpdate value not updated."
					+ "Current value: "+bulletinUserToUpdate.getUserUpdate()+" "
					+ "Expected value: testUPDATE.", bulletinUserToUpdate.getUserUpdate().equals("testUPDATE"));
		} catch (Exception e){
			fail("bulletinUserUpdate method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			bulletinUserToUpdate = new BulletinUser();
			bulletinUserToUpdate.setId(9898989898l);
			bulletinUserToUpdate.setUserCreated("test");
			bulletinUserRep.update(bulletinUserToUpdate, "testUPDATE");
			fail("bulletinUserUpdate method failed. No IllegalArgumentException thrown.");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch (Exception e){
			fail("bulletinUserUpdate method fail during merge on inexistent bulletinUser. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  DELETE TEST
	 */
	@Test
	public void bulletinUserDelete(){
		BulletinUser bulletinUserToDelete = new BulletinUser();
		bulletinUserToDelete = em.find(BulletinUser.class, 99999l);
		
		try{
			bulletinUserRep.update(null, "testADD");
			fail("bulletinUserDelete method with elem parameter = null failed. No IllegalArgumentException thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserDelete method with elem parmeter = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.update(bulletinUserToDelete, null);
			fail("bulletinUserDelete method with user parameter = null failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserDelete method with elem user = null failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			bulletinUserRep.update(bulletinUserToDelete, "");
			fail("bulletinUserDelete method with user parameter = '' failed. No IllegalArgumentExcepion thrown");
		} catch (IllegalArgumentException e){
			assertTrue(true); // Just for visibility
		} catch(Exception e){
			fail("bulletinUserDelete method with elem user = '' failed. Unexpected exception catched. "+e.toString());
		}
		
		try {
			bulletinUserRep.delete(bulletinUserToDelete, "testDELETE");
			bulletinUserToDelete = em.find(BulletinUser.class, 99999l);
			assertFalse("bulletinUserDelete method failed. BulletinUser not disactivated.", bulletinUserToDelete.isActive());
		} catch (Exception e){
			fail("bulletinUserDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void bulletinUserCount(){
		Long result;
		
		try{
			result = bulletinUserRep.count(null);
			fail("bulletinUserCount method with user parameter = null failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("bulletinUserCount method with user parameter = null fail. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = bulletinUserRep.count("");
			fail("bulletinUserCount method with user parameter = '' failed. No IllegalArgumentException thrown.");
		} catch(IllegalArgumentException e){
			assertTrue(true); // Just for visibility :)
		} catch(Exception e){
			fail("bulletinUserCount method with user parameter = '' fail. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = bulletinUserRep.count("testADD0");
			assertTrue("bulletinUserCount method failed. Number of BulletinUser expected: 1 Actual:"+result, result==1);
		} catch (Exception e){
			fail("bulletinUserCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		try{
			result = bulletinUserRep.count("unknowUser");
			assertTrue("bulletinUserCount method with user parameter = 'unknowUser' failed. Number of BulletinUser expected: 0 Actual:"+result, result==0);
		} catch (Exception e){
			fail("bulletinUserCount method with user parameter = 'unknowUser' failed. Unexpected exception catched. "+e.toString());
		}
	}
}
