package exception;

public class ArticuloInexistenteException extends Exception {

	private static final long serialVersionUID = -7304761193922012909L;

	public ArticuloInexistenteException() {}

    public ArticuloInexistenteException(String message)
    {
       super(message);
    }
}
