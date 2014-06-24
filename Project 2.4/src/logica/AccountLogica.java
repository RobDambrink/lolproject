package logica;

import java.io.IOException;
import java.util.List;

import org.riot.ResponseException;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import exeption.AccountNotExist;
import exeption.NameNotExist;
import exeption.SummonerNotExist;
import mappingHibernate.Accounts;

public class AccountLogica {
	
	private Hibernate hib;
	private CouchDB couch;
	public static final int ERROR =403;
	public static final int OK =200;
	public AccountLogica(Hibernate hib, CouchDB couch) throws ResponseException, IOException, SummonerNotExist, NameNotExist, AccountNotExist{
		this.hib=hib;
		this.couch=couch;
		createAccount("bbdc", "b", null);
		edditAccount("bbdc", "bb", 37268473l);
	}
	
	public int login(String name, String pass ){
		MD5Hashing md5 = new MD5Hashing();
		String check = (String) hib.getOneValueFromTheDatabase("SELECT name FROM Accounts WHERE name='" + name + "' AND password = '" + md5.getMD5Hash(pass) + "' ");
		if (check==null)
			return ERROR;
		return OK;
		
		
	}
	public void createAccount(String name, String password, Long summonerID) throws ResponseException, IOException, SummonerNotExist, NameNotExist{
		if (checkAccountExist(name)){
			throw new NameNotExist("This name already exist");
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
				throw new SummonerNotExist("This summoner does not exist");
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
	 * @throws SummonerNotExist 
	 * @throws AccountNotExist 
	 */
	@SuppressWarnings("rawtypes")
	public void edditAccount(String name,String password, Long summonerID) throws ResponseException, IOException, SummonerNotExist, AccountNotExist{
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
							throw new SummonerNotExist("This summoner does not exist");
						}
					}
					else{
						account.setSummonerID(null);
					}
				}
				hib.updateToDatabse(account);
			}
			else{
				throw new AccountNotExist("Account not found");
			}
		}
		else{
			throw new AccountNotExist("Account not found");
		}		
	}
	
	private boolean checkAccountExist(String name){
		String check = (String) hib.getOneValueFromTheDatabase("SELECT name FROM Accounts WHERE name='" + name + "'");
		if (check==null)
			return false;
		else 
			return true;
	}
}
