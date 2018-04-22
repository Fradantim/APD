package exception;

public class UbicacionInexistenteException extends Exception {

	private static final long serialVersionUID = 682166466571975924L;

	public UbicacionInexistenteException() {}

    public UbicacionInexistenteException(String message)
    {
       super(message);
    }
}
