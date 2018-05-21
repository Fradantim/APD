package exception;

public class LaUbicacionNoTieneSuficientesArticulosParaRemoverException extends Exception{
    
	private static final long serialVersionUID = 4249878586509680098L;

	public LaUbicacionNoTieneSuficientesArticulosParaRemoverException() {}

    public LaUbicacionNoTieneSuficientesArticulosParaRemoverException(String message)
    {
       super(message);
    }

}
