package exception;

public class LaFacturaYaSeEncuentraPagadaException extends Exception{
    
	private static final long serialVersionUID = 5443451644719712852L;

	public LaFacturaYaSeEncuentraPagadaException() {}

    public LaFacturaYaSeEncuentraPagadaException(String message)
    {
       super(message);
    }
}
