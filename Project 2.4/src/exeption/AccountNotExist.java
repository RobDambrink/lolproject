package exeption;

public class AccountNotExist extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322389103966203969L;

	public AccountNotExist(String message)
    {
       super(message);
    }
}
