package exception;

public class ExisteUnPedidoConArticulosDeEsosReservadosException extends Exception{
    
	private static final long serialVersionUID = 5954962634959723940L;

	public ExisteUnPedidoConArticulosDeEsosReservadosException() {}

    public ExisteUnPedidoConArticulosDeEsosReservadosException(String message)
    {
       super(message);
    }

}
