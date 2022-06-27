package apuestas_qatar22.excepciones;

public class PasswordIncorrecta extends Exception {
    
    public PasswordIncorrecta(String mensaje){
        super(mensaje);

    }

    @Override
    public String getMessage() {
        return "Error "+super.getMessage();
    }
}
