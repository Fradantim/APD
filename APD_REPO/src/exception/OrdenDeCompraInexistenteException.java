package exception;

public class OrdenDeCompraInexistenteException extends Exception {

	private static final long serialVersionUID = -2602976753163170932L;

	public OrdenDeCompraInexistenteException() {}

    public OrdenDeCompraInexistenteException(String message)
    {
       super(message);
    }
}
