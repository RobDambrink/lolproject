package exeption;

public class NameNotExist extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322389103966203969L;

	public NameNotExist(String message)
    {
       super(message);
    }
}
