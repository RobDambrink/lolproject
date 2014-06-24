package exeption;

public class PageNotExist extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322389103966203969L;

	public PageNotExist(String message)
    {
       super(message);
    }
}
