package logica;

import java.io.IOException;
import java.util.List;

import org.riot.ResponseException;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import mappingHibernate.Accounts;

public class AccountLogica {
	
	private Hibernate hib;
	private CouchDB couch;
	public static final int ERROR =403;
	public static final int OK =200;
	public AccountLogica(Hibernate hib, CouchDB couch) throws ResponseException, IOException{
		this.hib=hib;
		this.couch=couch;
		//createAccount("bb", "b", null);
		//edditAccount("bb", "bb", 23l);
		// TODO profiel maken
	}
	
	public int login(String name, String pass ){
		MD5Hashing md5 = new MD5Hashing();
		String check = (String) hib.getOneValueFromTheDatabase("SELECT name FROM Accounts WHERE name='" + name + "' AND password = '" + md5.getMD5Hash(pass) + "' ");
		if (check==null)
			return ERROR;
		return OK;
		
		
	}
	public void createAccount(String name, String password, Long summonerID) throws ResponseException, IOException{
		if (checkAccountExist(name)){
			System.out.println("This name already exist");
			// TODO exeption van maken
			return;
		}
		Accounts account = new Accounts();
		MD5Hashing md5 = new MD5Hashing();
		account.setName(name);
		account.setPassword(md5.getMD5Hash(password));
		if (summonerID!=null){
			SummonerLogica sum = new SummonerLogica(hib,couch);
			if (sum.getSummonerByID(summonerID)!=null)
				account.setSummonerID(summonerID);
			else{
				System.out.println("This summoner does not exist");
				// TODO exeption van maken
			}
		}
		hib.addToDatabase(account);
	}
	
	/**
	 * 
	 * @param name this is the current name
	 * @param password if null then not changed
	 * @param summonerID if null then not changed if -1 set to null
	 * @throws IOException 
	 * @throws ResponseException 
	 */
	@SuppressWarnings("rawtypes")
	public void edditAccount(String name,String password, Long summonerID) throws ResponseException, IOException{
		if (checkAccountExist(name)){
			List list = hib.getDataFromDatabase("FROM Accounts WHERE name='" + name + "'");
			if (!list.isEmpty()){
				Accounts account = (Accounts) list.get(0);
				if (password!=null){
					MD5Hashing md5 = new MD5Hashing();
					account.setPassword(md5.getMD5Hash(password));
				}
				if (summonerID!=null){
					if (summonerID!=-1){
						SummonerLogica sum = new SummonerLogica(hib,couch);
						if (sum.getSummonerByID(summonerID)!=null)
							account.setSummonerID(summonerID);
						else{
							System.out.println("This summoner does not exist");
							// TODO exeption van maken
						}
					}
					else{
						account.setSummonerID(null);
					}
				}
				hib.updateToDatabse(account);
			}
			else{
				System.out.println("ERROR List is empty");
				// TODO exeption van maken
			}
		}
		else{
			System.out.println("This name does not exist");
			// TODO exeption van maken
			return;
		}		
	}
	
	// TODO INLOG DING
	
	private boolean checkAccountExist(String name){
		String check = (String) hib.getOneValueFromTheDatabase("SELECT name FROM Accounts WHERE name='" + name + "'");
		if (check==null)
			return false;
		else 
			return true;
	}
}
