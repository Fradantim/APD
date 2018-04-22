package exception;

public class ObjetoInexistenteException extends Exception {

	private static final long serialVersionUID = 4172092758446860099L;

	public ObjetoInexistenteException() {}

    public ObjetoInexistenteException(String message)
    {
       super(message);
    }
}
