package apuestas_qatar22;

import java.util.ArrayList;
import java.util.Objects;

public class Equipo implements Comparable<Equipo>{
    private String nombre;
        private Integer ranking_fifa; //usado como dato y como id
        private int calificacion; //entre 0 y 100
        private ArrayList<String> plantilla_jugadores;
        private String director_tecnico;
        private Boolean como_clasifico; //true directo, false repechaje
        private Boolean estado; //true sigue en competencia, false quedó afuera
        private String grupo; //A, B, C, etc
        private int puntos;

    public Equipo (String nombre, Integer ranking_fifa, int calificacion, ArrayList<String> plantilla_jugadores, 
                   String director_tecnico, Boolean como_clasifico, String grupo) {
        this.nombre = nombre;
        this.ranking_fifa = ranking_fifa;
        this.calificacion = calificacion;
        this.plantilla_jugadores = plantilla_jugadores;
        this.como_clasifico = como_clasifico;
        this.director_tecnico = director_tecnico;
        this.estado = true;
        this.grupo = grupo;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = this.puntos + puntos;
    }

    public String getDirector_tecnico() {
        return this.director_tecnico;
    }

    public void setDirector_tecnico(String director_tecnico) {
        this.director_tecnico = director_tecnico;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getRanking_fifa() {
        return this.ranking_fifa;
    }

    public int getCalificacion() {
        return this.calificacion;
    }

    public ArrayList<String> getPlantilla_jugadores() {
        return this.plantilla_jugadores;
    }

    public Boolean getComo_clasifico() {
        return this.como_clasifico;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getGrupo() {
        return this.grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Equipo)) {
            return false;
        }
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre) && Objects.equals(ranking_fifa, equipo.ranking_fifa) && Objects.equals(calificacion, equipo.calificacion) && Objects.equals(plantilla_jugadores, equipo.plantilla_jugadores) && Objects.equals(como_clasifico, equipo.como_clasifico) && Objects.equals(estado, equipo.estado) && Objects.equals(grupo, equipo.grupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, ranking_fifa, calificacion, plantilla_jugadores, como_clasifico, estado, grupo);
    }

    @Override
    public String toString() {
        return "Selección: " + getNombre() +
               "\nRanking FIFA: "+getRanking_fifa() +
               "\nDirector Técnico: "+getDirector_tecnico() + 
               "\nLista de convocados: "+plantilla_jugadores +
               "\nGrupo: " + getGrupo();
    }

    @Override
    public int compareTo(Equipo e) {
        if(e.getPuntos()>this.puntos){
            return 1;
        }else if(e.getPuntos()==this.puntos){
            return 0;
        }else{
            return -1;
        }
    }
   
}
