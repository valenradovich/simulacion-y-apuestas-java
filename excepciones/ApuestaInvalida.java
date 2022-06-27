package apuestas_qatar22.excepciones;

public class ApuestaInvalida extends Exception {

    public ApuestaInvalida(String mensaje){
        super(mensaje);

    }

    @Override
    public String getMessage() {
        return "Error "+super.getMessage();
    }
    
}
