package apuestas_qatar22;

import java.util.Objects;

public class Partido {
    private Equipo equipoA;
    private Equipo equipoB;
    private Integer golesA;
    private Integer golesB;
    private Integer id;
    private Integer resultado; // 0 ganó equipoA, 1 ganó equipoB, 2 empate, 3 penales equipoA, 4 penales equipoB
    private float pagaA;
    private float pagaB;
    private Float pagaEmpate;
    private boolean estado; //false, no se jugó, true ya jugado
    public static Integer cont = 0;

    public Partido(Equipo equipoA, Equipo equipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.golesA = 0;
        this.golesB = 0;
        this.id = cont + 1;
        cont++;
        this.resultado = -1;
        this.pagaA = calcular_paga_equipoA();
        this.pagaB = calcular_paga_equipoB();
        this.pagaEmpate = calcular_paga_empate();
        this.estado = false;
    }
    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Float getPagaA(){
        return this.pagaA;
    }

    public Float getPagaB(){
        return this.pagaB;
    }

    public Float getPagaEmpate(){
        return this.pagaEmpate;
    }

    public Float calcular_paga_equipoA() {
        // Probabilidades de ganar en formate 0.x
        int suma_calificaciones = getEquipoA().getCalificacion() + getEquipoB().getCalificacion();
        float probabilidades_ganarA = (float)(getEquipoA().getCalificacion()) / suma_calificaciones;

        return this.pagaA = (1 / probabilidades_ganarA);
    }

    public Float calcular_paga_equipoB() {
        // Probabilidades de ganar en formate 0.x
        int suma_calificaciones = getEquipoA().getCalificacion() + getEquipoB().getCalificacion();
        float probabilidades_ganarB = (float)(getEquipoB().getCalificacion()) / suma_calificaciones;


        return this.pagaB = (1 / probabilidades_ganarB);
    }

    public Float calcular_paga_empate() {
        // Probabilidades de empate en formate 0.x
        float probabilidades_empate = (float) (Math.random() * (0.5 - 0.32) + 0.32);

        return this.pagaEmpate = (1 / probabilidades_empate);
    }

    public Integer setResultado(boolean eliminacion_directa) {
        // Probabilidades de ganar en formate 0.x
        int suma_calificaciones = getEquipoA().getCalificacion() + getEquipoB().getCalificacion();
        
        float probabilidades_ganarA = (float) (getEquipoA().getCalificacion()) / suma_calificaciones;
        
        float probabilidades_ganarB = (float) (getEquipoB().getCalificacion()) / suma_calificaciones;
        

        // Intervalos de 0-1000 para posible calculo
        Integer intervalo_eA = (int) (1000 * probabilidades_ganarA);    
        Integer intervalo_eB = intervalo_eA + 1;

        // 0 ganó equipoA, 1 ganó equipoB, 2 empate
        int resultado = -1;
        int golesPartido = (int) (Math.random() * 5 - 0);
        

        if (golesPartido != 0) {

            for (int i = 0; i < golesPartido; i++) {

                int n = (int) (Math.random() * 1000 + 1);

                if (n <= intervalo_eA) {
                    setGolesA();
                } else if (n > intervalo_eA) {
                    setGolesB();
                }
            }

        } else {
            resultado = 2;
            }

        resultado = quien_gano(eliminacion_directa);

        return this.resultado = resultado;

    }

    public Integer quien_gano(boolean eliminacion_directa) {
        Integer rta = -1;

        if (eliminacion_directa!=true) {
            if (getGolesA() == getGolesB()) {
                rta = 2; // 2 es empate
                getEquipoA().setPuntos(1);
                getEquipoB().setPuntos(1);
    
            } else if (getGolesA() > getGolesB()) {
                rta = 0; // 0 es gana equipoA
                getEquipoA().setPuntos(3);
    
            } else {
                rta = 1; // 1 es gana equipoB
                getEquipoB().setPuntos(3);
            }
        } else {
            if (getGolesA() == getGolesB()) {
                int n = (int) (Math.random() * (1000 - 1) + 1);

                if (n <= 500) {
                    rta = 3; //gana equipo A los penales
                } else {
                    rta = 4; //gana equipo B los penales
                }

            } else if (getGolesA() > getGolesB()) {
                rta = 0; // 0 es gana equipoA
                
    
            } else {
                rta = 1; // 1 es gana equipoB
                
            }
        }
        return rta;
    }

    public Integer getResultado() {
        return this.resultado;
    }

    public String getGanadorString() {
        String ganador = "";
        if (getResultado() == 2) {
            ganador = "Empate";

        } else if (getResultado() == 1) {
            ganador = getEquipoB().getNombre();

        } else if (getResultado() == 0){
            ganador = getEquipoA().getNombre();

        } else if (getResultado() == 3){
            ganador = getEquipoA().getNombre() + " por penales";

        } else {
            ganador = getEquipoB().getNombre() + " por penales";
        }
        return ganador;
    }

    public Equipo getEquipoA() {
        return this.equipoA;
    }

    public void setEquipoA(Equipo equipoA) {
        this.equipoA = equipoA;
    }

    public Equipo getEquipoB() {
        return this.equipoB;
    }

    public void setEquipoB(Equipo equipoB) {
        this.equipoB = equipoB;
    }

    public Integer getGolesA() {
        return this.golesA;
    }

    public void setGolesA() {
        this.golesA = golesA + 1;
    }

    public Integer getGolesB() {
        return this.golesB;
    }

    public void setGolesB() {
        this.golesB = golesB + 1;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Partido)) {
            return false;
        }
        Partido partido = (Partido) o;
        return Objects.equals(equipoA, partido.equipoA) && Objects.equals(equipoB, partido.equipoB)
                && Objects.equals(golesA, partido.golesA) && Objects.equals(golesB, partido.golesB)
                && Objects.equals(id, partido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipoA, equipoB, golesA, golesB, id);
    }

    @Override
    public String toString() {
        return getEquipoA().getNombre() + " - " + getEquipoB().getNombre();
    }

    public String toStringResultado() {
        if (this.estado == true) {
            return "=> " + getEquipoA().getNombre() + " " + getGolesA() + "-" + getGolesB() + " " + getEquipoB().getNombre()
                +
                "\nGanador: " + getGanadorString();
        } else {
            return "Partido por jugar.";
        }
        
    }

}
