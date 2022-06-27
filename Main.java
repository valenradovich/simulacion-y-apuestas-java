package apuestas_qatar22;

import java.util.Scanner;



public class Main {
    static Scanner teclado;
    static int FECHA = 1;

    public static void main(String[] args) {

        teclado = new Scanner(System.in);

        SimulacionMundialYApuesta app = new SimulacionMundialYApuesta();

        inicio_app(app);

        teclado.close();
    }

    public static void menu_user() {
        System.out.println("[1] Ver próximos partidos.");
        System.out.println("[2] Realizar apuesta.");
        System.out.println("[3] Generar resultados.");
        System.out.println("[4] Ver mis apuestas.");
        System.out.println("[5] Mi perfil.");
        System.out.println("[6] Ver Selecciones.");
        System.out.println("[7] Ver fechas, horarios y estadio fase de grupos.");
        System.out.println("[0] Salir.");

    }

    public static void menu_inicio_sesion() {
        System.out.println("[1] Login Usuario.");
        System.out.println("[0] Salir.");
    }

    public static void inicio_app(SimulacionMundialYApuesta app) {
        int key = -1;
        while (key != 0) {
            menu_inicio_sesion();
            key = teclado.nextInt();

            switch (key) {
                case 1:
                    System.out.println("Ingrese nombre de usuario: ");
                    String u = teclado.next();

                    System.out.println("Ingrese contraseña de usuario: ");
                    String c = teclado.next();

                    boolean logueado = app.login_usuario(u, c);

                    if (logueado == false) {
                        System.out.println("Inicio de sesión fallido.");

                    } else {
                        app.carga_datos();
                        launch_app(app);

                    }
                    break;

                case 0:
                    break;
            }

        }
    }

    public static void launch_app(SimulacionMundialYApuesta app) {
        int key = -1;
        while (key != 0) {
            menu_user();
            key = teclado.nextInt();

            switch (key) {
                // muestra partidos
                case 1:

                    if (FECHA <= 3) {
                        // ver los partidos de la fase de grupos
                        app.ver_partidos_fase_grupos();

                    } else if (FECHA == 4) {
                        // ver partidos octavos
                        app.ver_partidos_octavos();

                    } else if (FECHA == 5) {
                        app.ver_partidos_cuartos();

                    } else if (FECHA == 6) {
                        app.ver_partidos_semis();

                    } else if (FECHA == 7) {
                        app.ver_partido_final();
                    }

                    break;

                // realizacion de la apuesta
                case 2:
                    if (FECHA == 1) {
                        // ver los partidos de la fase de grupos
                        app.ver_partidos_fase_grupos_por_fecha(0, 16);

                        // comienza la apuesta
                        System.out.println("\nIngrese el numero del partido al que desea apostar: ");
                        int id_partido = teclado.nextInt();
                        id_partido = id_partido - 1;
                        Partido p = app.getListaPartidosGrupos().get(id_partido);

                        app.info_apuesta(id_partido, app.getListaPartidosGrupos());

                        System.out.println("Ingrese el monto que desea apostar: ");
                        Float monto = teclado.nextFloat();

                        boolean existe_monto = app.verificar_si_existe_monto(monto);

                        if (existe_monto == false) {

                            System.out.println("El monto ingresado es mayor al del balance.");
                            System.out
                                    .println("El balance actual es de: $" + app.getUser().getBilletera().getBalance());

                        } else {

                            System.out.println("\nRealice su apuesta.");
                            System.out.println(
                                    "[0] " + app.getListaPartidosGrupos().get(id_partido).getEquipoA().getNombre());
                            System.out.println(
                                    "[1] " + app.getListaPartidosGrupos().get(id_partido).getEquipoB().getNombre());
                            System.out.println("[2] Empate.");
                            int que_apuesta = teclado.nextInt();

                            boolean apuesta = app.realizar_apuesta(que_apuesta, false);

                            if (apuesta == false) {
                                System.out.println("Error al realizar la apuesta");

                            } else {
                                app.getUser().getBilletera().setBalance(-monto);
                                app.crear_apuesta(id_partido, monto, que_apuesta, p);

                            }
                        }

                    } else if (FECHA == 2) {
                        // ver los partidos de la fase de grupos
                        app.ver_partidos_fase_grupos_por_fecha(16, 32);

                        // comienza la apuesta
                        System.out.println("\nIngrese el numero del partido al que desea apostar: ");
                        int id_partido = teclado.nextInt();
                        id_partido = id_partido - 1;
                        Partido p = app.getListaPartidosGrupos().get(id_partido);

                        app.info_apuesta(id_partido, app.getListaPartidosGrupos());

                        System.out.println("Ingrese el monto que desea apostar: ");
                        Float monto = teclado.nextFloat();

                        boolean existe_monto = app.verificar_si_existe_monto(monto);

                        if (existe_monto == false) {

                            System.out.println("El monto ingresado es mayor al del balance.");
                            System.out
                                    .println("El balance actual es de: $" + app.getUser().getBilletera().getBalance());

                        } else {

                            System.out.println("\nRealice su apuesta.");
                            System.out.println(
                                    "[0] " + app.getListaPartidosGrupos().get(id_partido).getEquipoA().getNombre());
                            System.out.println(
                                    "[1] " + app.getListaPartidosGrupos().get(id_partido).getEquipoB().getNombre());
                            System.out.println("[2] Empate.");
                            int que_apuesta = teclado.nextInt();

                            boolean apuesta = app.realizar_apuesta(que_apuesta, false);

                            if (apuesta == false) {
                                System.out.println("Error al realizar la apuesta");

                            } else {
                                app.getUser().getBilletera().setBalance(-monto);
                                app.crear_apuesta(id_partido, monto, que_apuesta, p);

                            }
                        }
                    } else if (FECHA == 3) {
                        // ver los partidos de la fase de grupos
                        app.ver_partidos_fase_grupos_por_fecha(32, 48);

                        // comienza la apuesta
                        System.out.println("\nIngrese el numero del partido al que desea apostar: ");
                        int id_partido = teclado.nextInt();
                        id_partido = id_partido - 1;
                        Partido p = app.getListaPartidosGrupos().get(id_partido);

                        app.info_apuesta(id_partido, app.getListaPartidosGrupos());

                        System.out.println("Ingrese el monto que desea apostar: ");
                        Float monto = teclado.nextFloat();

                        boolean existe_monto = app.verificar_si_existe_monto(monto);

                        if (existe_monto == false) {

                            System.out.println("El monto ingresado es mayor al del balance.");
                            System.out
                                    .println("El balance actual es de: $" + app.getUser().getBilletera().getBalance());

                        } else {

                            System.out.println("\nRealice su apuesta.");
                            System.out.println(
                                    "[0] " + app.getListaPartidosGrupos().get(id_partido).getEquipoA().getNombre());
                            System.out.println(
                                    "[1] " + app.getListaPartidosGrupos().get(id_partido).getEquipoB().getNombre());
                            System.out.println("[2] Empate.");
                            int que_apuesta = teclado.nextInt();

                            boolean apuesta = app.realizar_apuesta(que_apuesta, false);

                            if (apuesta == false) {
                                System.out.println("Error al realizar la apuesta");

                            } else {
                                app.getUser().getBilletera().setBalance(-monto);
                                app.crear_apuesta(id_partido, monto, que_apuesta, p);

                            }
                        }
                    } else if (FECHA == 4) {
                        // ver partidos octavos
                        app.ver_partidos_octavos();
                        System.out.println("\nIngrese el numero del partido al que desea apostar: ");
                        int id_partido = teclado.nextInt();
                        id_partido = id_partido - 1;
                        Partido p = app.getLista_octavos_final().get(id_partido);

                        app.info_apuesta(id_partido, app.getLista_octavos_final());

                        System.out.println("Ingrese el monto que desea apostar: ");
                        Float monto = teclado.nextFloat();

                        boolean existe_monto = app.verificar_si_existe_monto(monto);

                        if (existe_monto == false) {

                            System.out.println("El monto ingresado es mayor al del balance.");
                            System.out
                                    .println("El balance actual es de: $" + app.getUser().getBilletera().getBalance());
                        } else {
                            System.out.println("\nRealice su apuesta.");
                            System.out.println(
                                    "[0] " + app.getLista_octavos_final().get(id_partido).getEquipoA().getNombre());
                            System.out.println(
                                    "[1] " + app.getLista_octavos_final().get(id_partido).getEquipoB().getNombre());

                            int que_apuesta = teclado.nextInt();

                            boolean apuesta = app.realizar_apuesta(que_apuesta, true);

                            if (apuesta == false) {
                                System.out.println("Error al realizar la apuesta");

                            } else {
                                app.getUser().getBilletera().setBalance(-monto);
                                app.crear_apuesta(id_partido, monto, que_apuesta, p);

                            }
                        }

                    } else if (FECHA == 5) {
                        app.ver_partidos_cuartos();
                        System.out.println("\nIngrese el numero del partido al que desea apostar: ");
                        int id_partido = teclado.nextInt();
                        id_partido = id_partido - 1;
                        Partido p = app.getLista_cuartos_final().get(id_partido);

                        app.info_apuesta(id_partido, app.getLista_cuartos_final());

                        System.out.println("Ingrese el monto que desea apostar: ");
                        Float monto = teclado.nextFloat();

                        boolean existe_monto = app.verificar_si_existe_monto(monto);

                        if (existe_monto == false) {

                            System.out.println("El monto ingresado es mayor al del balance.");
                            System.out
                                    .println("El balance actual es de: $" + app.getUser().getBilletera().getBalance());
                        } else {
                            System.out.println("\nRealice su apuesta.");
                            System.out.println(
                                    "[0] " + app.getLista_cuartos_final().get(id_partido).getEquipoA().getNombre());
                            System.out.println(
                                    "[1] " + app.getLista_cuartos_final().get(id_partido).getEquipoB().getNombre());

                            int que_apuesta = teclado.nextInt();

                            boolean apuesta = app.realizar_apuesta(que_apuesta, true);

                            if (apuesta == false) {
                                System.out.println("Error al realizar la apuesta");

                            } else {
                                app.getUser().getBilletera().setBalance(-monto);
                                app.crear_apuesta(id_partido, monto, que_apuesta, p);

                            }
                        }

                    } else if (FECHA == 6) {
                        app.ver_partidos_semis();
                        System.out.println("\nIngrese el numero del partido al que desea apostar: ");
                        int id_partido = teclado.nextInt();
                        id_partido = id_partido - 1;
                        Partido p = app.getLista_semis_final().get(id_partido);

                        app.info_apuesta(id_partido, app.getLista_semis_final());

                        System.out.println("Ingrese el monto que desea apostar: ");
                        Float monto = teclado.nextFloat();

                        boolean existe_monto = app.verificar_si_existe_monto(monto);

                        if (existe_monto == false) {

                            System.out.println("El monto ingresado es mayor al del balance.");
                            System.out
                                    .println("El balance actual es de: $" + app.getUser().getBilletera().getBalance());
                        } else {
                            System.out.println("\nRealice su apuesta.");
                            System.out.println(
                                    "[0] " + app.getLista_semis_final().get(id_partido).getEquipoA().getNombre());
                            System.out.println(
                                    "[1] " + app.getLista_semis_final().get(id_partido).getEquipoB().getNombre());

                            int que_apuesta = teclado.nextInt();

                            boolean apuesta = app.realizar_apuesta(que_apuesta, true);

                            if (apuesta == false) {
                                System.out.println("Error al realizar la apuesta");

                            } else {
                                app.getUser().getBilletera().setBalance(-monto);
                                app.crear_apuesta(id_partido, monto, que_apuesta, p);

                            }
                        }

                    } else if (FECHA == 7) {
                        app.ver_partido_final();
                        System.out.println("\nIngrese el numero del partido al que desea apostar: ");
                        int id_partido = teclado.nextInt();
                        id_partido = id_partido - 1;
                        Partido p = app.getLista_final_mundial().get(id_partido);

                        app.info_apuesta(id_partido, app.getLista_final_mundial());

                        System.out.println("Ingrese el monto que desea apostar: ");
                        Float monto = teclado.nextFloat();

                        boolean existe_monto = app.verificar_si_existe_monto(monto);

                        if (existe_monto == false) {

                            System.out.println("El monto ingresado es mayor al del balance.");
                            System.out
                                    .println("El balance actual es de: $" + app.getUser().getBilletera().getBalance());
                        } else {
                            System.out.println("\nRealice su apuesta.");
                            System.out.println(
                                    "[0] " + app.getLista_final_mundial().get(id_partido).getEquipoA().getNombre());
                            System.out.println(
                                    "[1] " + app.getLista_final_mundial().get(id_partido).getEquipoB().getNombre());

                            int que_apuesta = teclado.nextInt();

                            boolean apuesta = app.realizar_apuesta(que_apuesta, true);

                            if (apuesta == false) {
                                System.out.println("Error al realizar la apuesta");

                            } else {
                                app.getUser().getBilletera().setBalance(-monto);
                                app.crear_apuesta(id_partido, monto, que_apuesta, p);

                            }
                        }
                    }

                    break;

                case 3:
                    // simular partido
                    if (FECHA == 1) {

                        System.out.println("SIMULANDO FECHA " + FECHA + "...");
                        app.simular_partidos_grupos(0, 16); // iria un 16, no un 6
                        FECHA++;
                        System.out.println("=====PUNTAJES GRUPOS=====");
                        app.ver_puntajes();

                    } else if (FECHA == 2) {

                        // otra ejecucion, de la fecha 2 en este caso
                        System.out.println("SIMULANDO FECHA " + FECHA + "...");
                        app.simular_partidos_grupos(16, 32);
                        FECHA++;
                        System.out.println("=====PUNTAJES GRUPOS=====");
                        app.ver_puntajes();

                    } else if (FECHA == 3) {

                        // otra ejecucion de simular_partidos_grupos() pero con los index
                        // correspondientes
                        System.out.println("SIMULANDO FECHA " + FECHA + "...");
                        app.simular_partidos_grupos(32, 48);
                        // tambien ejecutar acá abajo agregar_clasificados()
                        FECHA++;
                        System.out.println("=====PUNTAJES GRUPOS=====");
                        app.ver_puntajes();
                        app.agrego_clasificados();
                        app.crear_cruces_octavos();

                    } else if (FECHA == 4) {
                        System.out.println("SIMULANDO OCTAVOS...");
                        app.simular_partidos_octavos();
                        app.crear_cruces_cuartos();
                        FECHA++;
                    } else if (FECHA == 5) {
                        System.out.println("SIMULANDO CUARTOS...");
                        app.simular_partidos_cuartos();
                        app.crear_cruces_semifinal();
                        FECHA++;
                    } else if (FECHA == 6) {
                        System.out.println("SIMULANDO SEMIFINALES...");
                        app.simular_partidos_semis();
                        app.crear_final();
                        FECHA++;
                    } else if (FECHA == 7) {
                        System.out.println("SIMULANDO FINAL...");
                        System.out.println("==============");
                        app.simular_final();
                        System.out.println("==============");
                    }
                    break;

                case 4:
                    System.out.println("===============");
                    System.out.println("|| Mis apuestas:");
                    if (FECHA <= 4) {

                        app.resultado_apuesta(app.getListaPartidosGrupos());

                    } else if (FECHA == 5) {

                        app.resultado_apuesta(app.getLista_octavos_final());

                    } else if (FECHA == 6) {

                        app.resultado_apuesta(app.getLista_cuartos_final());

                    } else if (FECHA == 7) {

                        app.resultado_apuesta(app.getLista_semis_final());

                    } else {

                        app.resultado_apuesta(app.getLista_final_mundial());
                    }

                    app.ver_apuestas();
                    System.out.println("===============");

                    break;

                case 5:
                    System.out.println("DATOS DE SU CUENTA:");
                    System.out.println("====================");
                    System.out.println("Usuario: " + app.getUser().getUser());
                    System.out.println("Email: " + app.getUser().getEmail());
                    System.out.println("DNI: " + app.getUser().getDni());
                    System.out.println("Balance: $" + app.getUser().getBilletera().getBalance());
                    System.out.println("====================");
                    System.out.println("Desea cargar dinero a su cuenta? (s/n)");
                    String tecla = teclado.next();

                    if (tecla.equals("s")) {

                        System.out.println("Ingrese monto a depositar: ");
                        float monto = teclado.nextFloat();

                        app.cargar_balance(monto);
                        
                    }

                    System.out.println("Desea retirar dinero de su cuenta? (s/n)");
                    tecla = teclado.next();
                    if (tecla.equals("s")) {

                        System.out.println("Ingrese monto a retirar: ");
                        float monto = teclado.nextFloat();

                        app.retirar_balance(monto);
                    
                    }
                    break;
                case 6:
                    // ver selecciones
                    app.ver_equipos();
                    break;
                case 7:
                    // ver info completa partidos grupos
                    String fuente2 = JsonUtiles.leer("partidos_grupos");
                    System.out.println(fuente2.toString());
                    break;
                case 0:
                    // salir
                    break;
            }

        }

    }

}
