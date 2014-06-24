package exeption;

public class ItemNotExist extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322389103966203969L;

	public ItemNotExist(String message)
    {
       super(message);
    }
}
