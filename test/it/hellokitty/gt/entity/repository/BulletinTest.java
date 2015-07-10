package it.hellokitty.gt.entity.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.hellokitty.gt.entity.Bulletin;
import it.hellokitty.gt.entity.BulletinUser;
import it.hellokitty.gt.entity.Tag;
import it.hellokitty.gt.repository.impl.BulletinRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BulletinTest {
	private BulletinRepositoryImpl bullRep = new BulletinRepositoryImpl();
	private static EntityManager em = Persistence.createEntityManagerFactory("BULLETIN_PU").createEntityManager();
	private static Bulletin bullAdd;
	private static BulletinUser bullUserAdd;
	private static Tag tagAdd;
	private static List<BulletinUser> bullUserAddList = new ArrayList<BulletinUser>();
	private static List<Tag> tagAddList = new ArrayList<Tag>();

	@Before
	public void insert20Elements() {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		for(int i = 0; i < 20; i++){
			bullAdd = new Bulletin();
			tagAdd  = new Tag("testTAG"+i);
			bullUserAdd = new BulletinUser();
			bullUserAdd.setUserCreated("testADD");
			bullUserAdd.setUserName("user"+i);
			bullUserAddList = new ArrayList<BulletinUser>();
			bullUserAddList.add(bullUserAdd);
			tagAdd.setUserCreated("testUSER"+i);
			tagAddList = new ArrayList<Tag>();
			tagAddList.add(tagAdd);
			bullAdd.setId(99999l+i);
			bullAdd.setUserCreated("testADD"+i);
			bullAdd.setCreateDate(new Date());
			bullAdd.setItContent("CONTENUTOTEST "+i);
			bullAdd.setActive(true);
			bullAdd.setTags(tagAddList);
			bullAdd.setBulletinUsers(bullUserAddList);
			em.persist(bullAdd);
		}
		transaction.commit();
	}
	
	@After
	public void delete20Elements() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0; i < 20; i++){
			bullAdd = new Bulletin();
			bullAdd.setId(99999l+i);
			bullAdd.setUserCreated("testADD"+i);
			bullAdd.setItContent("CONTENUTOTEST "+i);
			em.remove(em.find(Bulletin.class, 99999l+i));

		}
		transaction.commit();
	}
	
	/*
	 *  FETCH BY ID TEST
	 */
	@Test
	public void bulletinFetchById(){
		try {
			Bulletin bull = bullRep.fetchById(99999l);
			assertNotNull("No Bulletin returned from fetchById", bull);
			assertTrue("bulletinFetchById method failed on retrieve content value. "
					+ "Actual value: "+bull.getItContent()+" "
					+ "Expected value: CONTENUTOTEST 0", bull.getItContent().equals("CONTENUTOTEST 0"));
			bull= bullRep.fetchById(987654321l);
			assertNull(bull);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in bulletinFetchById method. "+e.toString());
		}
	}
	
	@Test
	public void bulletinFetchAll(){
		try {
			LinkedHashMap<String, String> cdMap = new LinkedHashMap<String, String>();
			cdMap.put("id", "asc");
			List<Bulletin> bullList = bullRep.fetchAll(0, 20, cdMap);
			assertTrue("bulletinFetchAll returned a empty list.", bullList.size() > 0);
			assertTrue("bulletinFetchAll didn't return all the elements.", bullList.size() >= 20);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception in bulletinFetchById method. "+e.toString());
		}
	}
	
	/*
	 *  INSERT TEST
	 */
	@Test
	public void bulletinInsert(){
		Bulletin bullToAdd = new Bulletin();
		bullToAdd.setId(98989898l);

		try{
			bullRep.insert(bullToAdd, "testADD");
			assertNotNull(em.find(Bulletin.class, 98989898l));
		} catch (Exception e){
			fail("bulletinInsert method failed. Unexpected Exception catched. "+e.getMessage());
		}
		
		em.remove(em.find(Bulletin.class, 98989898l));
	}
	
	/*
	 *  MERGE TEST
	 */
	@Test
	public void bulletinMerge(){
		Bulletin bullToMerge = new Bulletin();
		bullToMerge = em.find(Bulletin.class, 99999l);
		bullToMerge.setItContent("CONTENUTO TEST ITA");
		
		try{
			bullRep.merge(bullToMerge, "testMERGE");
			bullToMerge = em.find(Bulletin.class, 99999l);
			assertTrue("bulletinMErge method failed. ItContent value wrong or not updated. "
					+ "Current value: "+bullToMerge.getItTitle()+" "
					+ "Expected value: CONTENUTO TEST ITA.",bullToMerge.getItContent().equals("CONTENUTO TEST ITA"));
			assertTrue("bulletinMerge method failed. UserUpdate value not updated."
					+ "Current value: "+bullToMerge.getUserUpdate()+" "
					+ "Expected value: testMERGE.", bullToMerge.getUserUpdate().equals("testMERGE"));
		} catch (Exception e){
			fail("bulletinMerge method failed. Unexpected Exception catched. "+e.toString());
		}
		
		try{
			bullToMerge = new Bulletin();
			bullToMerge.setId(9898989898l);
			bullToMerge.setUserCreated("test");
			bullToMerge.setItContent("CONTENUTO TEST ITA");
			bullRep.merge(bullToMerge, "testMERGE");
			assertNotNull("bulletinMerge method fail. No element added.", em.find(Bulletin.class, 9898989898l));
		} catch (Exception e){
			fail("bulletinMErge method fail during merge on inexistent bulletin. Unexpected exception catched. "+e.toString());
		}
		em.remove(em.find(Bulletin.class, 9898989898l));
	}
	
//	/*
//	 *  UPDATE TEST
//	 */
//	@Test
//	public void bulletinUpdate(){
//		Bulletin bullToUpdate = new Bulletin();
//		bullToUpdate = em.find(Bulletin.class, 99999l);
//		bullToUpdate.setItContent("CONTENUTO MODIFICATO TEST ITA");
//		
//		try{
//			bullRep.update(bullToUpdate, "testUPDATE");
//			bullToUpdate = em.find(Bulletin.class, 99999l);
//			assertTrue("bulletinUpdate method failed. ItContent value wrong or not updated. "
//					+ "Current value: "+bullToUpdate.getItTitle()+" "
//					+ "Expected value: CONTENUTO MODIFICATO TEST ITA.",bullToUpdate.getItContent().equals("CONTENUTO MODIFICATO TEST ITA"));
//			assertTrue("bulletinUpdate method failed. UserUpdate value not updated."
//					+ "Current value: "+bullToUpdate.getUserUpdate()+" "
//					+ "Expected value: testUPDATE.", bullToUpdate.getUserUpdate().equals("testUPDATE"));
//		} catch (Exception e){
//			fail("bulletinUpdate method failed. Unexpected Exception catched. "+e.toString());
//		}
//		
//		try{
//			bullToUpdate = new Bulletin();
//			bullToUpdate.setId(9898989898l);
//			bullToUpdate.setUserCreated("test");
//			bullToUpdate.setItContent("CONTENUTO TEST ITA");
//			bullRep.update(bullToUpdate, "testUPDATE");
//			fail("bulletinUpdate method failed. No IllegalArgumentException thrown.");
//		} catch (IllegalArgumentException e){
//			assertTrue(true); // Just for visibility :)
//		} catch (Exception e){
//			fail("bulletinUpdate method fail during merge on inexistent bulletin. Unexpected exception catched. "+e.toString());
//		}
//	}
	
	/*
	 *  DELETE TEST
	 */
	@Test
	public void bulletinDelete(){
		Bulletin bullToDelete = new Bulletin();
		bullToDelete = em.find(Bulletin.class, 99999l);
		
		try {
			bullRep.delete(bullToDelete, "testDELETE");
			bullToDelete = em.find(Bulletin.class, 99999l);
			assertFalse("bulletinDelete method failed. Bulletin not disactivated.", bullToDelete.isActive());
		} catch (Exception e){
			fail("bulletinDelete method failed with user=\"\". Unexpected Exception catched. "+e.getMessage());
		}
	}

//	/*
//	 *  FETCH BY TAG TEST
//	 */
//	@Test
//	public void bulletinFetchByTag(){
//		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
//		
//		try{
//			LinkedHashMap<String, String> cdMap = new LinkedHashMap<String, String>();
//			cdMap.put("id", "asc");
//			bulletinList = bullRep.fetchByTag("testTAG0", "user0", 0, 20, cdMap);
//			assertTrue("bulletinFetchByTag method failed. Expected list size:1 actual size:"+bulletinList.size(),bulletinList.size()==1);
//			assertTrue(bulletinList.get(0).getTags().get(0).getWord().equals("testTAG0"));
//		} catch(Exception e){
//			fail("bulletinFetchByTag method fail. Unexpected exception catched. "+e.toString());
//		}
//	}
//	
//	/*
//	 * FETCH BY TAG LIKE TEST
//	 */
//	@Test
//	public void bulletinFetchByTagLike(){
//		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
//		
//		ColumnDirection cd = new ColumnDirection("id", "asc");
//		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
//		cdList.add(cd);
//		
//		try{
//			bulletinList = bullRep.fetchByTagLike("testTAG0", "admin", 0, 20, cdList);
//			assertTrue("bulletinFetchByTag method failed. Expected list size:1 actual size:"+bulletinList.size(),bulletinList.size()==1);
//			assertTrue(bulletinList.get(0).getTags().get(0).getWord().equals("testTAG0"));
//		} catch(Exception e){
//			fail("bulletinFetchByTag method fail. Unexpected exception catched. "+e.toString());
//		}
//		
//		try{
//			bulletinList = bullRep.fetchByTagLike("testTAG", "admin", 0, 20, cdList);
//			assertTrue("bulletinFetchByTag method failed. Expected list size:20 actual size:"+bulletinList.size(),bulletinList.size()==20);
//		} catch(Exception e){
//			fail("bulletinFetchByTag method fail. Unexpected exception catched. "+e.toString());
//		}
//		
//		try{
//			bulletinList = bullRep.fetchByTagLike("testTAG", "user0", 0, 20, cdList);
//			assertTrue("bulletinFetchByTag method failed. Expected list size:1 actual size:"+bulletinList.size(),bulletinList.size()==1);
//		} catch(Exception e){
//			fail("bulletinFetchByTag method fail. Unexpected exception catched. "+e.toString());
//		}
//	}
//	
//	/*
//	 *  FETCH BY USER TEST
//	 */
//	@Test
//	public void bulletinFetchByUser(){
//		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
//		
//		ColumnDirection cd = new ColumnDirection("id", "asc");
//		List<ColumnDirection> cdList = new ArrayList<ColumnDirection>();
//		cdList.add(cd);
//		
//		try{
//			bulletinList = bullRep.fetchByUser("user0", 0, 20, cdList);
//			assertTrue("bulletinFetchByUser method failed. Expected list size:1 actual size:"+bulletinList.size(),bulletinList.size()==1);
//			assertTrue(bulletinList.get(0).getTags().get(0).getWord().equals("testTAG0"));
//		} catch(Exception e){
//			fail("bulletinFetchByUser method failed. Unexpected exception catched. "+e.toString());
//		}
//		
//		try{
//			bulletinList = bullRep.fetchByUser("unknowUser", 0, 20, cdList);
//			assertTrue("bulletinFetchByUser method failed. Expected list size:1 actual size:"+bulletinList.size(),bulletinList.size()==0);
//		} catch(Exception e){
//			fail("bulletinFetchByUser method failed. Unexpected exception catched. "+e.toString());
//		}
//	}
	
	/*
	 *  COUNT TEST
	 */
	@Test
	public void bulletinCount(){
		Long result;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userCreated", "testADD0");
		HashMap<String, Object> emptyMap = new HashMap<String, Object>();
		
		try{
			result = bullRep.count(map, emptyMap, emptyMap, emptyMap);
			assertTrue("bulletinCount method failed. Number of Bulletin expected: 1 Actual:"+result, result==1);
		} catch (Exception e){
			fail("bulletinCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		map = new HashMap<String, Object>();
		map.put("userCreated", "testADDUNKNOW");
		
		try{
			result = bullRep.count(map, emptyMap, emptyMap, emptyMap);
			assertTrue("bulletinCount method failed. Number of Bulletin expected: 0 Actual:"+result, result==0);
		} catch (Exception e){
			fail("bulletinCount method failed. Unexpected exception catched. "+e.toString());
		}
		
		map = new HashMap<String, Object>();
		map.put("userCreated", "testADD");
		
		try{
			result = bullRep.count(emptyMap, map, emptyMap, emptyMap);
			assertTrue("bulletinCount method with user parameter = 'unknowUser' failed. Number of Bulletin expected: 20 Actual:"+result, result == 20);
		} catch (Exception e){
			fail("bulletinCount method with user parameter = 'unknowUser' failed. Unexpected exception catched. "+e.toString());
		}
	}
	
	/*
	 *  SEARCH TEST
	 */
	@Test
	public void bulletinSearch(){
		List<Bulletin> bulletinList = new ArrayList<Bulletin>();
		
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("id", "asc");
		HashMap<String, Object> emptyMap = new HashMap<String, Object>();
		
		try{
			bulletinList = bullRep.search(0, 20, map, emptyMap, emptyMap, emptyMap, emptyMap);
			assertTrue("bulletinSearch method failed. Expected List of Bulletin size: 1 Actual: "+bulletinList.size(),bulletinList.size() >= 1);
		} catch (Exception e){
			fail("bulletinSearch method failed. Unexpected exception catched. "+e.toString());
		}
		
//		try{
//			bulletinList = bullRep.search("CONTENUTOTEST", "admin", "it", 0, 30, cdList);
//			assertTrue("bulletinSearch method failed. Expected List of Bulletin size: 20 Actual: "+bulletinList.size(),bulletinList.size() >= 20);
//		} catch (Exception e){
//			fail("bulletinSearch method failed. Unexpected exception catched. "+e.toString());
//		}
	}
}