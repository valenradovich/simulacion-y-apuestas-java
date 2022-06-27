package apuestas_qatar22;

import java.util.Objects;

public class Wallet {
    private Float balance;
    private Float ganancia; //puede ser negativa
    private String dni; //para identificar una wallet con un usuario

    public Wallet(String dni) {
        this.balance = 0f;
        this.ganancia = 0f;
        this.dni = dni;
    }

    public String getDni() {
        return this.dni;
    }

    public Float getBalance() {
        return this.balance;
    }

    public void setBalance(Float balance) {
        this.balance = this.balance + balance;
    }

    public void setBalanceResta(Float balance) {
        this.balance = this.balance - balance;
    }

    public Float getGanancia() {
        return this.ganancia;
    }

    public void setGanancia(Float ganancia) {
        this.ganancia=ganancia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Wallet)) {
            return false;
        }
        Wallet wallet = (Wallet) o;
        return Objects.equals(balance, wallet.balance) && Objects.equals(ganancia, wallet.ganancia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, ganancia);
    }

    @Override
    public String toString() {
        return "{" +
            " cantidad_usd='" + getBalance() + "'" +
            ", ganancia='" + getGanancia() + "'" +
            "}";
    }

    
}
