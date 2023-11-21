import java.util.Scanner;

public class Vista {
    Controlador c = new Controlador();
    Scanner scn = new Scanner(System.in);

    public void registroInicioSesion(){
        System.out.print("¿Deseas registrarte o iniciar sesión? (R/I): ");
        String ans = scn.nextLine();
        if (ans.equalsIgnoreCase("R")){
            c.creacionCSV();
            System.out.println("Te damos la bienvenida. \nPor favor ingresar los datos que se le solicitan: ");
            System.out.print("Ingresa tu nombre de usuario: ");
            String nombreUsuarioN = scn.nextLine();
            System.out.print("Ingresa tu contraseña: ");
            String contraseñaUsuarioN = scn.nextLine();
            System.out.print("Ingresa el tipo de plan que deseas (gratis o VIP): ");
            String tipoPlan = scn.nextLine();
            c.registroUsuario(nombreUsuarioN, contraseñaUsuarioN, tipoPlan);
            c.iniciarSesion(nombreUsuarioN, contraseñaUsuarioN);
        } else if (ans.equalsIgnoreCase("I")){
            System.out.print("Ingresa tu nombre de usuario: ");
            String nombreUsuario = scn.nextLine();
            System.out.print("Ingresa tu contraseña: ");
            String contraseñaUsuario = scn.nextLine();
            c.iniciarSesion(nombreUsuario, contraseñaUsuario);
            while (true) {
                    System.out.println("1. Modo reservas\n2. Modo préstamo\n3. Mi perfil\n0. Salir del programa"); 
                    System.out.print("Ingresa el número de opción: ");
                    int opcion = scn.nextInt();
                    scn = new Scanner(System.in);
                    switch (opcion) {
                        case 1:
                        System.out.println("MODO RESERVAS");
                        c.modoReservas(nombreUsuario, contraseñaUsuario);
                        break;
                        case 2:
                        System.out.print("Ingresa el número de tarjeta: ");
                        int numTarjeta = scn.nextInt();
                        c.modoConfirmacion(nombreUsuario, contraseñaUsuario);
                        break;
                        case 3:
                        c.modoPerfil(nombreUsuario, contraseñaUsuario);
                        break;
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opción inválida. \nPor favor, ingrese una opción válida.");
                            break;
                    }
                }
            }
    }
}
