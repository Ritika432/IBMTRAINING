package com.hibernate.FirstHibernateDemo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppMain {
	
	public static List<Product> getAllProducts(SessionFactory sessionFactory){
		Session session=sessionFactory.openSession();
		Query query=(Query) session.createQuery("from product");
		List<Product> productList=query.list();
		return productList;
	}
	public static void getAllProductsUsingSQL(SessionFactory sessionFactory)
	{
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	tx.begin();
	SQLQuery query=session.createSQLQuery("INSERT INTO PRODUCT_MASTER(P_ID,NAME,COST) VALUES(1477,'SDFGS',22222)");
	int update = query.executeUpdate();
	if(update == 0 || update == 1)
	{
	System.out.println(update + " row affected");
	}
	else
	System.out.println(update + " rows affected");
	System.out.println("Inserted Records Successfully");
	System.out.println("Successfully updated");
	SQLQuery query1=session.createSQLQuery("SELECT * FROM PRODUCT_MASTER");
	List<Object[]> productList=query1.list();
	for(Object[] obj:productList) {
	System.out.println(obj[0]+" "+obj[1]+" "+obj[2]);
	}
	tx.commit();

	}
	
public static void insert(SessionFactory sessionFactory) {
	Session session=sessionFactory.openSession();
	//Transaction tx=session.beginTransaction();
	//tx.begin();
	Product product=new Product("Chairs",5000);
	long productId=(Long)session.save(product);
	session.save(product);
	//tx.commit();
	System.out.println(productId);
	}
public static void update(SessionFactory sessionFactory) {
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	tx.begin();
	//Product product=session.load(Product.class,1L);
	Product product=session.get(Product.class,1L);
	product.setName("TVC");
	tx.commit();
	session.evict(product);
	session.close();
	
	
	}
public static void delete(SessionFactory sessionFactory) {
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	tx.begin();
	Product product=session.load(Product.class,1L);
    session.delete(product);
	tx.commit();
	session.close();
	}


	public static void main(String[] args) throws Exception{
		Configuration configuration=new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();

		Session session=sessionFactory.openSession();
		Query query = session.getNamedQuery("@Get_AVG_PRICE");
		System.out.println(query.list());
		//insert(sessionFactory);
		//	getAllProductsUsingSQL(sessionFactory);
		//update(sessionFactory);
		// delete(sessionFactory);
		
		//QUESTION 2A
		
	//	String sumHql = "SELECT SUM(p.price) FROM Product p";
	//	Query sumQuery = s.createQuery(sumHql);
	//System.out.println(sumQuery.list().get(0));
		
		//QUESTION 2B
		
		//String allEmp="from Product p order by p.price asc";
		//	Query allQuery = s.createQuery(allEmp); 
		//System.out.println(allQuery.list());
		
		//Criteria cr=s.createCriteria(Product.class);
				//cr.add(Restrictions.gt("price",0.0));
				//List results=cr.list();
				//System.out.println(results);
		
		//QUESTION 2C
		
		//String grpHql = "from Product p group by p.name ";
		//	Query grpQuery = s.createQuery(grpHql);
		//System.out.println(grpQuery.list());
		
		//QUESTION 2D
		
		//String avgHql = "SELECT AVG(p.price) FROM Product p";
		//Query avgQuery = s.createQuery(avgHql);
		//System.out.println(avgQuery.list().get(0));
		
		//QUESTION 2E
		
		//String maxHql = "from Product p WHERE p.price>19";
		//Query maxQuery = s.createQuery(maxHql);
		//System.out.println(maxQuery.list());
		
	
	}

}
