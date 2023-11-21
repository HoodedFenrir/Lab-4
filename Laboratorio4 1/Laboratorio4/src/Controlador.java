import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    Scanner scn = new Scanner(System.in);
    public void creacionCSV(){ // Creación de un archivo CSV que permita almacenar los datos de los usuarios
    
        File datosUsuarios = new File("datosUsuarios.csv");
        if (!datosUsuarios.exists()){
            try {
                datosUsuarios.createNewFile();
                System.out.println("Archivo de datosUsuarios.csv creado");
            } catch (IOException e) {
                System.out.println("Error encontrado" + e.getMessage());
            }
        }
    }

    public void creacionCSVprestamos(){ // Creación de un archivo CSV que permita almacenar los datos de los préstamos
    File datosUsuarios = new File("prestamosCSV.csv");
    if (!datosUsuarios.exists()){
        try {
            datosUsuarios.createNewFile();
            System.out.println("Archivo de prestamosCSV.csv creado");
        } catch (IOException e) {
            System.out.println("Error encontrado" + e.getMessage());
        }
    
    }
    }

    public void registroUsuario(String nombreUsuarioN, String contraseñaUsuarioN, String tipoPlan){ // El método para ir agregando a los usuarios
        String archivoCSV = "datosUsuarios.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCSV, true))) {
            writer.write(nombreUsuarioN + "," + contraseñaUsuarioN + "," + tipoPlan);
            writer.newLine();
            System.out.println("\nTe hemos registrado con éxito, " + nombreUsuarioN);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    public void iniciarSesion(String nombreUsuario, String contraseña){
        String archivoCSV = "datosUsuarios.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");

                if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseña)) {
                    System.out.println("INICIO DE SESIÓN CORRECTO.\nTe damos la bienvenida de vuelta, " + nombreUsuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modoReservas(String nombreUsuario, String contraseñaUsuario){
        scn = new Scanner(System.in);
        System.out.print("Ingresa tu fecha de viaje (dd/MM/yyyy): ");
        String fechaViaje = scn.nextLine();
        System.out.print("El viaje es solo ida o vuelta incluida: ");
        String preguntaidaVuelta = scn.nextLine();
        System.out.print("Cantidad de boletos: ");
        String cantidadBoletos = scn.nextLine();
        System.out.print("Definir aerolinea: ");
        String nombreAerolinea = scn.nextLine();
    } 
    

    public void modoConfirmacion(String nombreUsuario, String contraseñaUsuario){
        String archivoCSV = "datosUsuarios.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");

                if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Premium")) {
                    scn = new Scanner(System.in);
                    System.out.print("Seleccionar número de asiento: ");
                    int numeroAsiento = scn.nextInt();
                    System.out.println("Definir cantidad de maletas: ");
                    int cantidadMaletas = scn.nextInt();
                } else if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Gratis")){
                    System.out.print("Cantidad de cuotas: ");
                    int elegirSucursal = scn.nextInt();
                    scn = new Scanner(System.in);
                    System.out.print("clase para vuelo (Coach o Primera Clase): ");
                    String horaRecogida = scn.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modoPerfil(String nombreUsuario, String contraseñaUsuario){
        while (true){
            System.out.println("1. Cambiar tipo de plan.\n2. Cambiar contraseña.");
            System.out.print("Ingresa el número de opción que deseas: ");
            int opcion = scn.nextInt();
            switch (opcion) {
                case 1:
                String archivoCSV = "datosUsuarios.csv";
                List<String> lineas = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] columnas = linea.split(",");
                        if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Premium")) {
                            System.out.println("Gracias por ser nuestro cliente premium, te daremos un cupón del 10%.");
                        } else if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Base")){
                            System.out.println("Puedes cambiar de plan");
                            columnas[2] = "premium";
                        }
                        lineas.add(String.join(",", columnas));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
                    for (String linea : lineas) {
                        bw.write(linea);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                String archivoCSV2 = "datosUsuarios.csv";
                List<String> lineas2 = new ArrayList<>();
                
                try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV2))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] columnas = linea.split(",");
                        if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario)) {
                            scn = new Scanner(System.in);
                            System.out.print("Ingresa la nueva contraseña: ");
                            String nuevaContraseña = scn.nextLine();
                            // Cambiar la contraseña del usuario
                            columnas[1] = nuevaContraseña;
                            System.out.println("Contraseña cambiada con éxito.");
                        }
                        lineas2.add(String.join(",", columnas));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                // Escribir las líneas modificadas en el archivo
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV2))) {
                    for (String linea : lineas2) {
                        bw.write(linea);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        }
    }
