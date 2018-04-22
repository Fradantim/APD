package exception;

public class FacturaInexistenteException extends Exception {

	private static final long serialVersionUID = 1947103004856778880L;

	public FacturaInexistenteException() {}

    public FacturaInexistenteException(String message)
    {
       super(message);
    }
}
