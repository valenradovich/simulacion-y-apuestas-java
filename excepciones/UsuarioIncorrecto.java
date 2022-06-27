package apuestas_qatar22.excepciones;

public class UsuarioIncorrecto extends Exception{
    
    public UsuarioIncorrecto(String mensaje){
        super(mensaje);

    }

    @Override
    public String getMessage() {
        return "Error "+super.getMessage();
    }

    
}
