package exception;

public class UsuarioContrasenaIncorrectosException extends Exception{
    
	private static final long serialVersionUID = 8672206046520183078L;

	public UsuarioContrasenaIncorrectosException() {}

    public UsuarioContrasenaIncorrectosException(String message)
    {
       super(message);
    }

}
