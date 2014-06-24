package exeption;

public class SummonerNotExist extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322389103966203969L;

	public SummonerNotExist(String message)
    {
       super(message);
    }
}
