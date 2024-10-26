import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConteoPalabras {

    public static void main(String[] args) {
        Map<String, Integer> conteoPalabras = new HashMap<>();
        String archivoEntrada = "AirQuality.csv";  // Archivo donde busco las palabras
        String archivoSalida = "ConteoPalabras.csv"; // Archivo donde se guarda cada palabra con su conteo
        String palabraBuscada;
        Scanner inputScanner = new Scanner(System.in);
        try {
            Scanner scanner = new Scanner(new File(archivoEntrada));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().toLowerCase(); //convertir mayusculas a minusculas

                //quitar caracteres especiales
                String[] palabras = linea.replaceAll("[^a-zA-Záéíóúñ ]", "").split(" ");

                for (String palabra : palabras) {
                    if (!palabra.isEmpty()) {
                        conteoPalabras.put(palabra, conteoPalabras.getOrDefault(palabra, 0) + 1);
                    }
                }
            }
            scanner.close();

            // Archivo con conteo
            guardarConteo(archivoSalida, conteoPalabras);
            System.out.println("Se guardó el archivo correctamente en: " + archivoSalida);

        } catch (IOException ex) {
            System.out.println("Ocurrió un error al leer el archivo: ");
            return;
        }

        // Ingresar palabra en consola
        while (true) {
            System.out.print("Ingresa una palabra para buscar: ");
            palabraBuscada = inputScanner.nextLine();
            // mostrar conteo de la palabra
            System.out.println(palabraBuscada+" = "+conteoPalabras.getOrDefault(palabraBuscada.toLowerCase(),0)+" veces.");
        }
    }

    private static void guardarConteo(String archivoSalida, Map<String, Integer> conteoPalabras) {
        try (FileWriter writer = new FileWriter(archivoSalida)) {
            for (Map.Entry<String, Integer> entrada : conteoPalabras.entrySet()) {
                writer.write(entrada.getKey()+","+entrada.getValue()+"\n");
            }
        } catch (IOException ex) {
            System.out.println("Ocurrió un error al escribir en el archivo: ");
        }
    }
}
