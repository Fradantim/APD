package exception;

public class ClienteInexistenteException extends Exception {

	private static final long serialVersionUID = 4172092758446860099L;

	public ClienteInexistenteException() {}

    public ClienteInexistenteException(String message)
    {
       super(message);
    }
}
