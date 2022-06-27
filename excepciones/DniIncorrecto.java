package apuestas_qatar22.excepciones;

public class DniIncorrecto extends Exception{

    public DniIncorrecto(String mensaje){
        super(mensaje);

    }

    @Override
    public String getMessage() {
        return "Error"+super.getMessage();
    }
    
}
