package apuestas_qatar22;

public class Apuesta{
    private Float monto; //U$$
    private Float ganancia; //0 ó positiva(+)
    private Integer que_aposto; ////0 gana A, 1 gana B, 2 empate
    private Integer id_partido; //identificamos a qué partido apostó    
    private Boolean estado; //false-> apuesta perdida, true-> apuesta ganada
    private boolean activo; //sigue activa o no la apuesta
    private Partido partido; //partido al que se realiza la apuesta

    public Apuesta (Float monto, Integer que_aposto, Integer id_partido, Partido partido) {
        this.monto = monto;
        this.que_aposto = que_aposto;
        this.id_partido = id_partido;
        this.activo = true;
        this.partido = partido;
        this.ganancia = 0f;
    }

    public Partido getPartido() {
        return this.partido;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public String getActivoToString() {
        boolean rta= getActivo();
        String str="";
        if(rta==true) {
            str = "Partido por disputar, apuesta en curso.";
        } else {
            str = "Apuesta finalizada, partido ya disputado.";
        }

        return str;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Float getMonto() {
        return this.monto;
    }

    public Float getGanancia() {
        return this.ganancia;
    }

    public void setGanancia(Float ganancia) {
        this.ganancia = ganancia;
    }
    
    public Float calculo_ganancia(float xPaga) {
        if (estado == false) {
            this.ganancia = 0f;
            
        } else {
            this.ganancia = monto * xPaga;
        }
         return this.ganancia;
    }

    public Integer getQue_aposto() {
        return this.que_aposto;
    }

    public String que_aposto_ToString() {
        int a = getQue_aposto();
        String apuesta="";

        if (a==0) {
            apuesta = getPartido().getEquipoA().getNombre();
        } else if (a==1) {
            apuesta = getPartido().getEquipoB().getNombre();
        } else {
            apuesta = "Empate.";
        }
        return apuesta;
    }

    public Integer getId_partido() {
        return this.id_partido;
    }

    public Boolean getEstado() {
        return this.estado;
    }
    
    public String getEstadoToString() {
        if (getEstado()==null) {
            return "En curso";
        } else if (getEstado() == true) {
            return "Ganaste.";
        } else {
            return "Perdiste.";
        }
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Monto apostado: $"+getMonto() +
               "\nPartido apostado: "+getPartido().toString() + 
               "\nEstado apuesta: "+getActivoToString() + 
               "\nApuesta realizada a: " + que_aposto_ToString() +
               "\nResultado apuesta: " + getEstadoToString() +
               "\nResultado del partido: " + getPartido().toStringResultado() +
               "\nGanancia: $"+getGanancia();
    }

    




    
}
