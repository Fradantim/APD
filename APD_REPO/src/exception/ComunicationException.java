package exception;

public class ComunicationException extends Exception{
    
	private static final long serialVersionUID = -4772322129401310324L;

	public ComunicationException() {}

    public ComunicationException(String message)
    {
       super(message);
    }

}
