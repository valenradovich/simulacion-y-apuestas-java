package apuestas_qatar22.excepciones;

public class EmailIncorrecto extends Exception{
    
    public EmailIncorrecto(String mensaje){
        super(mensaje);

    }

    @Override
    public String getMessage() {
        return "Error"+super.getMessage();
    }
}
