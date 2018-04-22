package exception;

public class LaUbicacionNoTieneEsteArticuloException extends Exception{
    
	private static final long serialVersionUID = 5954962634959723940L;

	public LaUbicacionNoTieneEsteArticuloException() {}

    public LaUbicacionNoTieneEsteArticuloException(String message)
    {
       super(message);
    }

}
