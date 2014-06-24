package databaseConnection;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import util.HibernateUtil;

public class Hibernate {
	
	private static Session session = HibernateUtil.getSessionFactory().openSession();;
	
	public Hibernate(){
		Configuration cfg = new Configuration().configure();
		// DIT MOET AAN STAAN ALS DE TABEL NOG NIET BESTAAT, ANDERS WORDEN BESTAANDE TABELEN OVERSCHREVEN
		//SchemaExport ex = new SchemaExport(cfg);
		//ex.create(true, true);
		// TOT HIER DUS DE 2 REGELS HIERBOVEN
		/*addToDatabase();
		getDataFromDatabase();
		updateToDatabase();
		deleteFromDatabase();
		System.out.println("iets uit de dabase: "+getOneValueFromTheDatabase());*/
		
	}
	
	public void addToDatabase(Object obj){
		try{ 
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		}
		catch(Exception ex){
			if (ex.getMessage().equals("Could not execute JDBC batch update")){
				// TODO exeption van maken
				System.out.println("ERROR IN INSERT THIS PRIMARY KEY MABY ALREADY EXISTED");
				ex.printStackTrace();
			}
			else{
				ex.printStackTrace();
			}
		}
	}
	
	public void updateToDatabse(Object obj){
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("rawtypes")
	public Object getOneValueFromTheDatabase(String queryIn){
		Object val=null;
		Query query = session.createQuery(queryIn);
		List list = query.list();
		if (!list.isEmpty()){
			val = list.get(0);
		}
		return val;
	}
	
	@SuppressWarnings("rawtypes")
	public List getDataFromDatabase(String queryIn){
		System.out.println(queryIn);
		List list = null;
		try{
			Query query = session.createQuery(queryIn);
			list = query.list();
		}
		catch(QueryException ex){
			System.out.println("Error in the query");
			// TODO exeption van maken
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteFromDatabase(String queryIn){
		Query query = session.createQuery(queryIn);
		List list = query.list();
		if (!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Object obj= list.get(i);
				session.beginTransaction();
				session.delete(obj);
				session.getTransaction().commit();
			}
		}
	}
	
	/**
	 * example how to add something to the database
	 */
	/*private void addToDatabase(){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ChampionNaamId user = new ChampionNaamId();
		user.setUserName("admin1");
		user.setPassWord("admin1");
		user.setRights(1);
		session.save(user);
		session.getTransaction().commit();
		session.beginTransaction();
		user = new ChampionNaamId();
		user.setUserName("admin2");
		user.setPassWord("admin2");
		user.setRights(1);
		session.save(user);
		session.getTransaction().commit();
	}
	
	/**
	 * example how to update something from the database
	 */
	/*@SuppressWarnings("rawtypes")
	private void updateToDatabase(){
		Query query = session.createQuery("from Users where userName=:userName");
		query.setParameter("userName", "admin1");
		List list = query.list();
		if (!list.isEmpty()){
			ChampionNaamId user= (ChampionNaamId) list.get(0);
			user.setPassWord("adminPass");
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		}
	}
	
	/**
	 * example how to delete something from the dabase
	 */
	/*@SuppressWarnings("rawtypes")
	private void deleteFromDatabase(){
		Query query = session.createQuery("from Users where userName=:userName");
		query.setParameter("userName", "admin2");
		List list = query.list();
		if (!list.isEmpty()){
			ChampionNaamId user= (ChampionNaamId) list.get(0);
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}
	}
	
	/**
	 * example how to get only 1 value from the database
	 * @return value
	 */
	/*@SuppressWarnings("rawtypes")
	private String getOneValueFromTheDatabase(){
		String pass="";
		Query query = session.createQuery("select passWord from Users where userName=:userName");
		query.setParameter("userName", "admin1");
		List list = query.list();
		if (!list.isEmpty()){
			pass = (String) list.get(0);
		}
		return pass;
	}
	
	/**
	 * example how you get data from database
	 */
	/*@SuppressWarnings("rawtypes")
	private void getDataFromDatabase(){
		Query query = session.createQuery("from Users");
		List list = query.list();
		if (!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				ChampionNaamId user = (ChampionNaamId) list.get(i);
				System.out.println("Gebruiker met de volgende data:");
				System.out.println("ID: "+user.getId());
				System.out.println("UserName: "+user.getUserName());
				System.out.println("PassWord: "+user.getPassWord());
				System.out.println("Rights: "+user.getRights());
			}
		}
	}*/
}
