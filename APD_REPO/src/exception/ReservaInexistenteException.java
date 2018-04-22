package exception;

public class ReservaInexistenteException extends Exception {

	private static final long serialVersionUID = 2972266436298940525L;

	public ReservaInexistenteException() {}

    public ReservaInexistenteException(String message)
    {
       super(message);
    }
}
