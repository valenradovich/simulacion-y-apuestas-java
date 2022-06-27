package apuestas_qatar22.excepciones;

public class BalanceInsuficiente extends Exception{

    public BalanceInsuficiente(String mensaje){
        super(mensaje);

    }

    @Override
    public String getMessage() {
        return "Error"+super.getMessage();
    }
    
}
