package it.hellokitty.gt.main;
//package it.ferrari.gt.main;
//
//import it.ferrari.gt.bulletin.entity.Attachment;
//import it.ferrari.gt.bulletin.entity.Bulletin;
//import it.ferrari.gt.bulletin.entity.Dealer;
//import it.ferrari.gt.bulletin.entity.Tag;
//import it.ferrari.gt.bulletin.repository.impl.BulletinRepositoryImpl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class BulletinTest {
//	public static void main(String[] args) {
//		String user;
//		Date startDate;
//		Date endDate;
//		int startPage;
//		int limitPage;
//		List<Bulletin> bullList = new ArrayList<Bulletin>();
//		BulletinRepositoryImpl bullRepo = new BulletinRepositoryImpl();
//		
////		startDate = new Date();
////		insert1000things();
////		endDate = new Date();
////		System.out.println("Tempo per il deploy: "+(endDate.getTime()-startDate.getTime())+"ms");
//
//
//		/*
//		 * FETCH ALL
//		 */
//		System.out.println("-------------");
//		System.out.println("  FETCH ALL");
//		System.out.println("-------------");
//
//		startDate = new Date();
//		try {
//			bullList = bullRepo.fetchAll("admin");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
////		for(Bulletin b : bullList){
////			System.out.println("bulletin id: " + b.getId());
////		}
//		endDate = new Date();
//		System.out.println("Start date:"+startDate);
//		System.out.println("End date: "+endDate);
//		System.out.println("Done in "+(endDate.getTime()-startDate.getTime())+"ms.");
//		System.out.println("found "+bullList.size()+" elem.");
//
//		/*
//		 * FETCH PAGE LIMIT
//		 */
//		int start = 15;
//		int limit = 80;
//		String order = "desc";
//		String column = "id";
//		System.out.println("");
//		System.out.println("--------------------------------");
//		System.out.println("        FETCH PAGE LIMIT");
//		System.out.println(" start:"+start+" lim:"+limit+" col:"+column+" order:"+order);
//		System.out.println("--------------------------------");
//
//		startDate = new Date();
//		bullList = bullRepo.fetchPageLimit(start, limit, column, order, "admin");
////		for(Bulletin b : bullList){
////			System.out.println("id: " + b.getId() +" priority: "+b.getPriority());
////		}
//		endDate = new Date();
//		System.out.println("Start date:"+startDate);
//		System.out.println("End date: "+endDate);
//		System.out.println("Done in "+(endDate.getTime()-startDate.getTime())+"ms.");
//		System.out.println("found "+bullList.size()+" elem.");
//
//		/*
//		 * FETCH BY TAG 
//		 */
//		String tag = "TAG21";
//		user = "admin";
//		startPage = 0;
//		limitPage = 20;
//		System.out.println("");
//		System.out.println("----------------------");
//		System.out.println("     FETCH BY TAG");
//		System.out.println("   tag="+tag+" user="+user);
//		System.out.println("----------------------");
//
//		startDate = new Date();
//		try {
//			bullList = bullRepo.fetchByTag(tag, user, startPage, limitPage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		for(Bulletin b : bullList){
////			System.out.println("bulletin with tag '"+tag+"' id: " + b.getId());
////		}
//		endDate = new Date();
//		System.out.println("Start date:"+startDate);
//		System.out.println("End date: "+endDate);
//		System.out.println("Done in "+(endDate.getTime()-startDate.getTime())+"ms.");
//		System.out.println("found "+bullList.size()+" elem.");
//
//		/*
//		 * FETCH BY TAG LIKE
//		 */
//		tag = tag.substring(0, 3);
//		user = "admin";
//		startPage = 0;
//		limitPage = 20;
//		System.out.println("");
//		System.out.println("---------------------");
//		System.out.println("  FETCH BY TAG LIKE");
//		System.out.println("   tag="+tag);
//		System.out.println("---------------------");
//
//		startDate = new Date();
//		try {
//			bullList = bullRepo.fetchByTagLike(tag, user, startPage, limitPage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		for(Bulletin b : bullList){
////			System.out.println("bulletin with tag '"+tag+"' id: " + b.getId());
////		}
//		endDate = new Date();
//		System.out.println("Start date:"+startDate);
//		System.out.println("End date: "+endDate);
//		System.out.println("Done in "+(endDate.getTime()-startDate.getTime())+"ms.");
//		System.out.println("found "+bullList.size()+" elem.");
//	}
//
//	public static void insert1000things(){
//		Attachment attach = new Attachment();
//		Dealer dealer = new Dealer();
//		Tag tag = new Tag();
//		Bulletin bull;
//		List<Attachment> attachList = new ArrayList<Attachment>();
//		List<Dealer> dealerList = new ArrayList<Dealer>();
//		List<Tag> tagList;
//		BulletinRepositoryImpl bullRepo = new BulletinRepositoryImpl();
//
//		for(int i = 0; i < 1000; i++){
//			bull = new Bulletin();
//			bull.setCreateDate(new Date());
//			bull.setItTitle("titolo"+i);
//			bull.setItContent("contenuto della comunicazione n:"+i);
//			bull.setPriority((i%50)+"");
//			tagList = new ArrayList<Tag>();
//			int maxIndex = 10;
//			if(i % 2 == 0){
//				maxIndex = 20;
//			}
//			for(int t = 0; t < maxIndex; t++){
//				tag = new Tag();
//
//				tag.setCreateDate(new Date());
//				tag.setUserCreated("gb");
//				tag.setWord("TAG"+t);
//				tag.setActive(true);
//
//				tagList.add(tag);
//			}
//
//			dealer = new Dealer();
//			dealer.setName("dealer"+i);
//			dealer.setDateIns(new Date());
//			dealer.setUserIns("gb");
//			dealerList = new ArrayList<Dealer>();
//			dealerList.add(dealer);
//			bull.setDealers(dealerList);
//
//			attach = new Attachment();
//			attach.setFileName("file"+i+".png");
//			attach.setCreateDate(new Date());
//			attach.setUserCreated("gb");
//			attachList = new ArrayList<Attachment>();
//			attachList.add(attach);
//			bull.setBulletinAttachments(attachList);
//
//			bull.setTags(tagList);
//			bull.setUserCreated("gb");
//
//			try {
//				bullRepo.insert(bull, "user");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
