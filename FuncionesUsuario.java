package apuestas_qatar22;

import apuestas_qatar22.excepciones.BalanceInsuficiente;
import apuestas_qatar22.excepciones.PasswordIncorrecta;
import apuestas_qatar22.excepciones.UsuarioIncorrecto;

public class FuncionesUsuario {
    private Usuario usuario;
    
    public FuncionesUsuario(Usuario usuario) {
        this.usuario = usuario;

    }

    public boolean validacion_datos_usuario(String user, String pass) throws UsuarioIncorrecto, PasswordIncorrecta {

        if (!user.equalsIgnoreCase(usuario.getUser())) {

            throw new UsuarioIncorrecto("Usuario incorrecto.");

        } else if (!pass.equals(usuario.getPassword())) {

            throw new PasswordIncorrecta("Contraseña incorrecta.");
        }   
        
        return true;
    }

    public boolean login (String user, String pass) {

        boolean rta = false;

        try {
            rta = validacion_datos_usuario(user, pass);
            
        } catch (UsuarioIncorrecto e) {
            System.out.println(e.getMessage()); 
            
        } catch (PasswordIncorrecta e) {
            System.out.println(e.getMessage());
        }
    
        return rta;
    }

    public boolean manejo_excepcion_balance(Float monto) throws BalanceInsuficiente {

        if (monto > usuario.getBilletera().getBalance()) {

            throw new BalanceInsuficiente("Balance insuficiente.");

        } 
        return true;
    }

    public boolean verificar_balance (Float monto) {

        boolean rta = false;

        try {
            rta = manejo_excepcion_balance(monto);
            
        } catch (BalanceInsuficiente e) {
            System.out.println(e.getMessage()); 
            
        }
    
        return rta;
    }
    
}
