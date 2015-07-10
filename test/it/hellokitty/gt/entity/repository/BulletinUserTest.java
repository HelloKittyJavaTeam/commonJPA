package it.hellokitty.gt.entity.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.BulletinUser;
import it.hellokitty.gt.repository.impl.BulletinUserRepositoryImpl;
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

public class BulletinUserTest {
	private BulletinUserRepositoryImpl bulletinUserRep = new BulletinUserRepositoryImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static BulletinUser bulletinUserAdd;

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			bulletinUserAdd = new BulletinUser();
			bulletinUserAdd.setUserCreated("testADD"+i);
			bulletinUserAdd.setActive(true);
			bulletinUserAdd.setId(99999l+i);
			em.persist(bulletinUserAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			em.remove(em.find(BulletinUser.class, 99999l+i));
		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void bulletinUserFetchById(){
		try {
			BulletinUser bulletinUser = bulletinUserRep.fetchById(99999l);
			assertNotNull("No BulletinUser returned from fetchById", bulletinUser);
			bulletinUser = bulletinUserRep.fetchById(987654321l);
			assertNull(bulletinUser);
		} catch (Exception e) {
			fail("Caught Exception in bulletinUserFetchById method. "+e.toString());
		}
	}
	
	@Test
	public void bulletinUserFetchAll(){
		ColumnDirection cd = new ColumnDirection("id", "asc");
		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
		cdList.add(cd);
		try {
			List<BulletinUser> bulletinUsers = bulletinUserRep.fetchAll(0, 20, cdList);
			assertTrue("bulletinUserFetchAll returned a empty list.", bulletinUsers.size() > 0);
			assertTrue("bulletinUserFetchAll didn't return all the elements.", bulletinUsers.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in bulletinUserFetchAll method.");
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void bulletinUserInsert(){
		BulletinUser bulletinUserAdd = new BulletinUser();
		bulletinUserAdd.setId(98989898l);
		
		try{
			bulletinUserRep.insert(bulletinUserAdd, "testADD");
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
		
		try{
			bulletinUserRep.merge(bulletinUserToMerge, "testMERGE");
			bulletinUserToMerge = em.find(BulletinUser.class, 99999l);
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
			fail("bulletinUserMerge method fail during merge on inexistent bulletinUser. Unexpected exception catched. "+e.toString());
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
		
		try{
			bulletinUserRep.update(bulletinUserToUpdate, "testUPDATE");
			bulletinUserToUpdate = em.find(BulletinUser.class, 99999l);
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
			fail("bulletinUserUpdate method failed. IllegalArgumentException not thrown.");
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
		
		try {
			bulletinUserRep.delete(bulletinUserToDelete, "testDELETE");
			bulletinUserToDelete = em.find(BulletinUser.class, 99999l);
			assertFalse("bulletinUserDelete method failed. EmailContact not disactivated.", bulletinUserToDelete.isActive());
		} catch (Exception e){
			fail("bulletinUserDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
		
		bulletinUserToDelete = new BulletinUser();
		bulletinUserToDelete.setId(987987987l);
		bulletinUserToDelete.setUserCreated("testUSER");
		bulletinUserToDelete.setCreateDate(new Date());
		bulletinUserToDelete.setActive(false);
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		em.persist(bulletinUserToDelete);
		transaction.commit();
		
		try{
			bulletinUserRep.delete(bulletinUserToDelete, "admin");
			em = Persistence.createEntityManagerFactory("PERSISTENCE_UNIT_NAME").createEntityManager();
			assertNull("bulletinUserDelete method failed. EmailContact with id 987987987 not deleted. ID: "+bulletinUserToDelete.getId(), em.find(BulletinUser.class, 987987987l));

		} catch (Exception e){
			fail("bulletinUserDelete method failed during delete from admin. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void bulletinUserCount(){
		Long result;
		
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
