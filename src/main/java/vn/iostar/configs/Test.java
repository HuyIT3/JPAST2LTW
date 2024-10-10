package vn.iostar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iostar.entity.Category;

public class Test {
	public static void main(String[] args) {
		 EntityManager enma = JPAConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		 Category cate = new Category();
		 cate.setCategoryname("IPhone");
		 cate.setImages("ip.jpg");
		 cate.setStatus(1);
		 try {
			 trans.begin();
			 enma.persist(cate);
			 trans.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		 }finally {
			 enma.close();
		 }
	}
}
