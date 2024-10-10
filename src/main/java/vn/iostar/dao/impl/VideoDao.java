package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.IVideoDao;
import vn.iostar.entity.Video;
public class VideoDao implements IVideoDao{
	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		
		 try {
			 trans.begin();
			 enma.persist(video);
			 trans.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		 }finally {
			 enma.close();
		 }
	}
	@Override
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		
		 try {
			 trans.begin();
			 enma.merge(video);
			 trans.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		 }finally {
			 enma.close();
		 }
	}
	@Override
	public void delete(String vidid) throws Exception{
		EntityManager enma = JPAConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		
		 try {
			 trans.begin();
			 Video video = enma.find(Video.class,vidid);
			 if(video!=null) {
				 enma.remove(video);
			 }
			 else {
				 throw new Exception("không tìm thấy");
			 }
			 trans.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		 }finally {
			 enma.close();
		 }
	}
	
	
	@Override
	public List<Video> findAll(){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query= enma.createNamedQuery("Video.findAll", Video.class);
		 return query.getResultList();
	}
	@Override
	public List<Video> findByTitlename(String vdtitle) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Video c WHERE c.vdtitle like :vdtitle";
		TypedQuery<Video> query= enma.createQuery(jpql, Video.class);
		query.setParameter("catename", "%" + vdtitle + "%");
		return query.getResultList();
	 }
	@Override
	public List<Video> findAll(int page, int pagesize) {
		 EntityManager enma = JPAConfig.getEntityManager();
		 TypedQuery<Video> query= enma.createNamedQuery("Video.findAll", Video.class);
		 query.setFirstResult(page*pagesize);
		 query.setMaxResults(pagesize);
		 return query.getResultList();
	}
	@Override
	public int count() {
		 EntityManager enma = JPAConfig.getEntityManager();
		 String jpql = "SELECT count(c) FROM Video c";
		 Query query = enma.createQuery(jpql);
		 return ((Long)query.getSingleResult()).intValue();
	}
	@Override
	public Video findById(String vidid) {
		EntityManager enma = JPAConfig.getEntityManager();
	    return enma.find(Video.class, vidid); 
	}
	
}
