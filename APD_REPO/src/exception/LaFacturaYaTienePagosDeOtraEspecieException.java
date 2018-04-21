package exception;

public class LaFacturaYaTienePagosDeOtraEspecieException extends Exception{
    
	private static final long serialVersionUID = 5443451644719712852L;

	public LaFacturaYaTienePagosDeOtraEspecieException() {}

    public LaFacturaYaTienePagosDeOtraEspecieException(String message)
    {
       super(message);
    }
}
