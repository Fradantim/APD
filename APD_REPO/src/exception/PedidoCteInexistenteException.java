package exception;

public class PedidoCteInexistenteException extends Exception {

	private static final long serialVersionUID = -2325178026862893510L;

	public PedidoCteInexistenteException() {}

    public PedidoCteInexistenteException(String message)
    {
       super(message);
    }
}
