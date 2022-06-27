package apuestas_qatar22;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import apuestas_qatar22.excepciones.ApuestaInvalida;

public class SimulacionMundialYApuesta {

    private ArrayList<Equipo> lista_equipos;
    private ArrayList<Partido> partidos_grupos;
    private ArrayList<Equipo> clasificados_grupos;
    private ArrayList<Partido> octavos_final;
    private ArrayList<Partido> cuartos_final;
    private ArrayList<Partido> semis_final;
    private ArrayList<Partido> final_mundial;
    private ArrayList<Apuesta> lista_apuestas;
    private Usuario user;
    static ArrayList<String> lista_letras = new ArrayList<>();

    public SimulacionMundialYApuesta() {
        lista_equipos = new ArrayList<>();
        partidos_grupos = new ArrayList<>();
        clasificados_grupos = new ArrayList<>();
        octavos_final = new ArrayList<>();
        cuartos_final = new ArrayList<>();
        semis_final = new ArrayList<>();
        final_mundial = new ArrayList<>();
        lista_apuestas = new ArrayList<>();
        lista_letras.add("A");
        lista_letras.add("B");
        lista_letras.add("C");
        lista_letras.add("D");
        lista_letras.add("E");
        lista_letras.add("F");
        lista_letras.add("G");
        lista_letras.add("H");
    }

    public Usuario getUser() {
        return this.user;
    }

    public ArrayList<Partido> getListaPartidosGrupos() {
        return this.partidos_grupos;
    }

    public ArrayList<Apuesta> getListaApuestas() {
        return this.lista_apuestas;
    }

    public ArrayList<Partido> getLista_octavos_final() {
        return this.octavos_final;
    }

    public ArrayList<Partido> getLista_cuartos_final() {
        return this.cuartos_final;
    }

    public ArrayList<Partido> getLista_semis_final() {
        return this.semis_final;
    }

    public ArrayList<Partido> getLista_final_mundial() {
        return this.final_mundial;
    }
    /*
     * =====<FUNCIONALIDADES VARIAS USUARIO>=====
     */

    public boolean login_usuario(String nombre_usuario, String contraseña) {

        Usuario usuario = new Usuario("valentin", "12345", "email@gmail.com", "4359999");
        this.user = usuario;
        FuncionesUsuario funciones = new FuncionesUsuario(usuario);

        boolean respuesta = funciones.login(nombre_usuario, contraseña);

        return respuesta;

    }

    public boolean verificar_si_existe_monto(Float monto) {
        FuncionesUsuario funciones = new FuncionesUsuario(user);

        boolean rta = funciones.verificar_balance(monto);

        return rta;
    }

    public void cargar_balance(float dinero) {

        user.cargar_dinero(dinero);

    }

    public void retirar_balance(float dinero) {

        if (dinero <= user.getBilletera().getBalance()) {
            user.retirar_dinero(dinero);
        } else {
            System.out.println("Saldo insuficiente para realizar retiro.");
        }
        

    }

    /*
     * ==== <APUESTA DE LOS PARTIDOS> ====
     */
    public boolean validar_apuesta(int que_apuesta, boolean fase_final) throws ApuestaInvalida {

        if (fase_final == false) {
            if (que_apuesta > 2 || que_apuesta < 0) {

                throw new ApuestaInvalida("Por favor realice una apuesta válida.");
            }
        } else {
            if (que_apuesta > 1 || que_apuesta < 0) {

                throw new ApuestaInvalida("Por favor realice una apuesta válida.");
            }
        }

        return true;
    }

    public boolean realizar_apuesta(int que_apuesta, boolean fase_final) {

        boolean rta = false;

        try {
            rta = validar_apuesta(que_apuesta, fase_final);

        } catch (ApuestaInvalida e) {
            System.out.println(e.getMessage());

        }
        return rta;
    }

    public void resultado_apuesta(ArrayList<Partido> lista_partido) {

        // obtengo su apuesta
        for (Apuesta apuesta : lista_apuestas) {

            for (Partido partido : lista_partido) {

                if (apuesta.getPartido().getId() == partido.getId() && partido.getEstado() == true) {

                    if (apuesta.getActivo() == true) {

                        if (apuesta.getQue_aposto() == partido.getResultado() ||
                                apuesta.getQue_aposto() == 0 && partido.getResultado() == 3 ||
                                apuesta.getQue_aposto() == 1 && partido.getResultado() == 4) {
                            apuesta.setEstado(true);

                            if (apuesta.getQue_aposto() == 0) {

                                float ganancia = apuesta
                                        .calculo_ganancia(lista_partido.get(apuesta.getId_partido()).getPagaA());
                                user.getBilletera().setBalance(ganancia);

                            } else if (apuesta.getQue_aposto() == 1) {

                                float ganancia = apuesta
                                        .calculo_ganancia(lista_partido.get(apuesta.getId_partido()).getPagaB());
                                user.getBilletera().setBalance(ganancia);
                            } else {

                                float ganancia = apuesta
                                        .calculo_ganancia(lista_partido.get(apuesta.getId_partido()).getPagaEmpate());
                                user.getBilletera().setBalance(ganancia);
                            }
                        } else {
                            apuesta.setEstado(false);

                        }

                    }
                    apuesta.setActivo(false);
                }
            }
        }
    }

    // FALTA SUMAR LA GANANCIA AL BALANCE

    public void crear_apuesta(int id_partido, float monto, int que_aposto, Partido partido) {

        Apuesta ap = new Apuesta(monto, que_aposto, id_partido, partido);
        lista_apuestas.add(ap);
    }

    public void info_apuesta(int id, ArrayList<Partido> lista) {
        System.out.println("Partido a apostar: " + lista.get(id).toString());
        System.out.println("Pagas: ");
        System.out.println(lista.get(id).getEquipoA().getNombre() + ": x" + lista.get(id).getPagaA());
        System.out.println(lista.get(id).getEquipoB().getNombre() + ": x" + lista.get(id).getPagaB());

        if (lista.size() > 8) {
            System.out.println("Empate: x" + lista.get(id).getPagaEmpate());
        }

    }

    public void ver_apuestas() {
        for (Apuesta apuesta : lista_apuestas) {
            System.out.println(apuesta.toString());
            System.out.println("===============");
        }
    }

    /*
     * ==== <FINAL DEL MUNDIAL> ====
     */
    public void simular_final() {

        for (Partido partido : final_mundial) {
            partido.setResultado(true);
            partido.setEstado(true);
            System.out.println("Resultado: " + partido.toStringResultado());
            int rta = partido.getResultado();
            if (rta == 0 || rta == 3) {

                System.out.println("|| CAMPEÓN QATAR 2022: " + partido.getEquipoA().getNombre());

            } else if (rta == 1 || rta == 4) {

                System.out.println("|| CAMPEÓN QATAR 2022:: " + partido.getEquipoB().getNombre());
            }

        }

    }

    public void ver_partido_final() {
        int i = 1;
        for (Partido partido : final_mundial) {
            System.out.println("Partido " + i + ": " + partido.toString());
            i++;
        }
    }

    /*
     * ==== <SEMIS DEL MUNDIAL> ====
     */

    public void crear_final() {
        Equipo e1 = null;
        Equipo e2 = null;
        int i = 0;

        int rta = semis_final.get(i).getResultado();

        // gano local
        if (rta == 0 || rta == 3) {

            e1 = semis_final.get(i).getEquipoA();
            // gano visita
        } else if (rta == 1 || rta == 4) {

            e1 = semis_final.get(i).getEquipoB();
        }
        i++;
        int rta2 = semis_final.get(i).getResultado();

        if (rta2 == 0 || rta2 == 3) {

            e2 = semis_final.get(i).getEquipoA();
        } else if (rta2 == 1 || rta2 == 4) {

            e2 = semis_final.get(i).getEquipoB();
        }

        Partido p1 = new Partido(e1, e2);

        final_mundial.add(p1);
    }

    public void simular_partidos_semis() {
        int n_partidos = 1;

        for (Partido partido : semis_final) {
            partido.setResultado(true);
            partido.setEstado(true);
            System.out.println("|| Partido " + n_partidos + ": " + partido.toStringResultado());
            n_partidos++;
        }
    }

    public void ver_partidos_semis() {
        int i = 1;
        for (Partido partido : semis_final) {
            System.out.println("Partido " + i + ": " + partido.toString());
            i++;
        }
    }

    /*
     * ==== <CUARTOS DEL MUNDIAL> ====
     */

    public void crear_cruces_semifinal() {
        for (int i = 0; i < cuartos_final.size(); i++) {
            Equipo e1 = null;
            Equipo e2 = null;

            int rta = cuartos_final.get(i).getResultado();

            // gano local
            if (rta == 0 || rta == 3) {

                e1 = cuartos_final.get(i).getEquipoA();
                // gano visita
            } else if (rta == 1 || rta == 4) {

                e1 = cuartos_final.get(i).getEquipoB();
            }
            i++;
            int rta2 = cuartos_final.get(i).getResultado();

            if (rta2 == 0 || rta2 == 3) {

                e2 = cuartos_final.get(i).getEquipoA();
            } else if (rta2 == 1 || rta2 == 4) {

                e2 = cuartos_final.get(i).getEquipoB();
            }

            Partido p1 = new Partido(e1, e2);

            semis_final.add(p1);

        }
    }

    public void simular_partidos_cuartos() {
        int n_partidos = 1;

        for (Partido partido : cuartos_final) {
            partido.setResultado(true);
            partido.setEstado(true);
            System.out.println("|| Partido " + n_partidos + ": " + partido.toStringResultado());
            n_partidos++;
        }
    }

    public void ver_partidos_cuartos() {
        int i = 1;
        for (Partido partido : cuartos_final) {
            System.out.println("Partido " + i + ": " + partido.toString());
            i++;
        }
    }

    /*
     * ==== <OCTAVOS DEL MUNDIAL> ====
     */

    public void crear_cruces_cuartos() {
        for (int i = 0; i < octavos_final.size(); i++) {
            Equipo e1 = null;
            Equipo e2 = null;

            int rta = octavos_final.get(i).getResultado();

            // gano local
            if (rta == 0 || rta == 3) {

                e1 = octavos_final.get(i).getEquipoA();
                // gano visita
            } else if (rta == 1 || rta == 4) {

                e1 = octavos_final.get(i).getEquipoB();
            }
            i++;
            int rta2 = octavos_final.get(i).getResultado();

            if (rta2 == 0 || rta2 == 3) {

                e2 = octavos_final.get(i).getEquipoA();
            } else if (rta2 == 1 || rta2 == 4) {

                e2 = octavos_final.get(i).getEquipoB();
            }

            Partido p1 = new Partido(e1, e2);

            cuartos_final.add(p1);
        }
    }

    public void simular_partidos_octavos() {
        int num_partidos = 1;

        for (Partido partido : octavos_final) {
            partido.setResultado(true);
            partido.setEstado(true);
            System.out.println("|| Partido " + num_partidos + ": " + partido.toStringResultado());
            num_partidos++;
        }
    }

    public void ver_partidos_octavos() {
        int i = 1;
        for (Partido partido : octavos_final) {
            System.out.println("Partido " + i + ": " + partido.toString());
            i++;
        }
    }

    /*
     * ==== <GRUPOS DEL MUNDIAL> ====
     */

    public void crear_cruces_octavos() {
        int i = 0;
        while (i < clasificados_grupos.size()) {

            Partido p = new Partido(clasificados_grupos.get(i), clasificados_grupos.get(i + 3));
            octavos_final.add(p);
            i = i + 4;
        }

        int k = clasificados_grupos.size() - 2;
        while (k >= 0) {

            Partido p = new Partido(clasificados_grupos.get(k), clasificados_grupos.get(k - 1));
            octavos_final.add(p);
            k = k - 4;
        }

    }

    public void agrego_clasificados() {

        // acá agrego a los clasificados de cada grupo en un mismo arreglo, y así quedan
        // sus posiciones

        // A: 0 - 1
        clasificados_grupos.addAll(punteros_grupos("A"));
        // B: 2 - 3
        clasificados_grupos.addAll(punteros_grupos("B"));
        // C: 4 - 5
        clasificados_grupos.addAll(punteros_grupos("C"));
        // D: 6 - 7
        clasificados_grupos.addAll(punteros_grupos("D"));
        // E: 8 - 9
        clasificados_grupos.addAll(punteros_grupos("E"));
        // F: 10 - 11
        clasificados_grupos.addAll(punteros_grupos("F"));
        // G: 12 - 13
        clasificados_grupos.addAll(punteros_grupos("G"));
        // H: 14 - 15
        clasificados_grupos.addAll(punteros_grupos("H"));

        for (Equipo e : clasificados_grupos) {
            System.out.println("|| Equipo clasificado grupo " + e.getGrupo() + ": " + e.getNombre());
        }

    }

    public void ver_partidos_fase_grupos() {
        int i = 1;
        for (Partido partido : partidos_grupos) {

            System.out.println("Partido " + i + ": " + partido.toString());
            i++;
        }
    }

    public void ver_partidos_fase_grupos_por_fecha(int fecha_min, int fecha_max) {

        for (int j = fecha_min; j < fecha_max; j++) {
            System.out.println("Partido " + (j + 1) + ": " + partidos_grupos.get(j).toString());
        }
    }

    // creo un arreglo para trabajar con los punteros de grupo
    public ArrayList<Equipo> punteros_grupos(String letra_grupo) {
        ArrayList<Equipo> lista_punteros_grupo = new ArrayList<>();
        // System.out.println("SIZE LISTA EQUIPOS: "+lista_equipos.size());

        // paso a un arreglo a los del grupo pedido para después ordenar
        for (int i = 0; i < lista_equipos.size(); i++) {

            if (letra_grupo.equals(lista_equipos.get(i).getGrupo())) {

                lista_punteros_grupo.add(lista_equipos.get(i));

            }
        }
        // ordena el arreglo de clasificados del grupo por puntaje
        Collections.sort(lista_punteros_grupo);

        // elimina de los clasificados del grupo a los que no clasificaron (3ro y 4to)
        for (int i = 0; i < lista_punteros_grupo.size(); i++) {
            lista_punteros_grupo.remove(2);
        }

        return lista_punteros_grupo;
    }

    /*
     * simulo y muestro el resultado de los partidos de grupos y después pasa los
     * clasificados
     * le paso por parametro los límites de las fechas para jugar
     * ejemplo:
     * de la 0 a la 6 sería la fecha 1
     */
    public void simular_partidos_grupos(int n_primer_partido, int n_ultimo_partido) {

        for (int i = n_primer_partido; i < n_ultimo_partido; i++) {
            partidos_grupos.get(i).setResultado(false);
            partidos_grupos.get(i).setEstado(true);
            System.out.println("Partido " + (i + 1) + ": " + partidos_grupos.get(i).toStringResultado());
        }
    }

    public void ver_equipos() {
        for (Equipo e : lista_equipos) {
            System.out.println(e.toString());
            System.out.println("================================");
        }
    }

    public void ver_puntajes() {
        int j = 0;
        //System.out.println("==============================");
        System.out.println("|| PUNTAJES GRUPO " + lista_letras.get(j));
        for (int i = 0; i < lista_equipos.size(); i++) {
            if (lista_equipos.get(i).getGrupo().equals(lista_letras.get(j))) {
                System.out.println("|| " + lista_equipos.get(i).getNombre() + ": " + lista_equipos.get(i).getPuntos());
            } else {
                i--;
                j++;
                System.out.println("==============================");
                System.out.println("|| PUNTAJES GRUPO " + lista_letras.get(j));
                System.out.println("==============================");
            }
            

        }
    }

    /*
     * los datos del json equipo son cargados a un auxiliar que descomprime y crea
     * al equipo con sus datos/atributos
     * y también descomprime un arreglo con los jugadores, que va a ser parte del
     * objeto equipo, se crea abajo y es
     * agregado a la lista que contiene los equipos
     */
    public boolean carga_equipos() {
        // extraigo los datos del json
        String fuente = JsonUtiles.leer("equipos");
        JSONArray aux_lista_equipos;

        try {
            aux_lista_equipos = new JSONArray(fuente);

            for (int i = 0; i < aux_lista_equipos.length(); i++) {
                JSONObject temp = aux_lista_equipos.getJSONObject(i);

                String nombre = temp.getString("nombre");
                int ranking_fifa = temp.getInt("ranking_fifa");
                int calificacion = temp.getInt("calificacion");

                ArrayList<String> array_jugadores = new ArrayList<>();

                for (int j = 0; j < temp.getJSONArray("plantilla_jugadores").length(); j++) {
                    array_jugadores.add(temp.getJSONArray("plantilla_jugadores").getString(j));

                }

                String director_tecnico = temp.getString("director_tecnico");
                Boolean como_clasifico = temp.getBoolean("como_clasifico");
                String grupo = temp.getString("grupo");

                Equipo e = new Equipo(nombre, ranking_fifa, calificacion, array_jugadores, director_tecnico,
                        como_clasifico, grupo);

                lista_equipos.add(e);
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }

        return true;
    }

    /*
     * Carga los partidos del grupo en su respectiva lista "partidos_grupos",
     * extraidos de un json que tiene la info
     * completa del partido.
     * Existe un partido objeto, y la lista que va a almacenar cada uno del grupo
     */
    public boolean cargar_partidos() {

        String fuente = JsonUtiles.leer("partidos_grupos");
        JSONArray aux_lista_partidos;

        try {

            aux_lista_partidos = new JSONArray(fuente);
            Equipo e1 = null;
            Equipo e2 = null;

            for (int i = 0; i < aux_lista_partidos.length(); i++) {
                JSONObject temp = aux_lista_partidos.getJSONObject(i);

                String nombre_equipo1 = temp.getString("local");
                String nombre_equipo2 = temp.getString("visitante");

                for (int j = 0; j < lista_equipos.size(); j++) {
                    if (nombre_equipo1.equals(lista_equipos.get(j).getNombre())) {
                        e1 = lista_equipos.get(j);
                    }

                    if (nombre_equipo2.equals(lista_equipos.get(j).getNombre())) {
                        e2 = lista_equipos.get(j);
                    }
                }

                Partido partido = new Partido(e1, e2);
                partidos_grupos.add(partido);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return true;
    }

    public void carga_datos() {
        carga_equipos();
        cargar_partidos();
    }
}
