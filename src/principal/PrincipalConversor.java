package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conversor.ConvertorAPI;
import conversor.DatosConversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConversor {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);

        String menuOpciones = """
                ******************************************************
                Sea Bienvenido/a al Conversor de Moneda
                
                1) Dólar -> Peso Argentino
                2) Peso Argentino -> Dólar
                3) Dólar -> Real Brasileño
                4) Real Brasileño -> Dólar
                5) Dólar -> Peso Colombiano
                6) Peso Colombiano -> Dólar
                7) Salir
                
                Por favor, digite una opción válida
                ******************************************************
                """;


        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create();




        while (true) {
            System.out.println(menuOpciones);
            String opcionUsuario = teclado.nextLine();

            if (opcionUsuario.equalsIgnoreCase("7")) {
                break;
            }

            String urlConvertor = "";
            String monedaSeleccionada = "";


            if (opcionUsuario.equals("1") || opcionUsuario.equals("3") || opcionUsuario.equals("5") ) {
                monedaSeleccionada = "USD";
                urlConvertor = "https://v6.exchangerate-api.com/v6/4910a1dd1e9e006d13fb8c48/latest/"+"USD"+"/";
            } else if (opcionUsuario.equals("2")) {
                monedaSeleccionada = "ARS";
                urlConvertor = "https://v6.exchangerate-api.com/v6/4910a1dd1e9e006d13fb8c48/latest/"+"ARS"+"/";
            } else if (opcionUsuario.equals("4")) {
                monedaSeleccionada = "BRL";
                urlConvertor = "https://v6.exchangerate-api.com/v6/4910a1dd1e9e006d13fb8c48/latest/"+"BRL"+"/";
            } else if (opcionUsuario.equals("6")) {
                monedaSeleccionada = "COP";
                urlConvertor = "https://v6.exchangerate-api.com/v6/4910a1dd1e9e006d13fb8c48/latest/"+"COP"+"/";
            }
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlConvertor))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                ConvertorAPI convertorAPI = gson.fromJson(json, ConvertorAPI.class);
                DatosConversor datosConversor = new DatosConversor(convertorAPI);
                System.out.println("Cantidad en " + monedaSeleccionada + " que deseas convertir:");
                int cantidadaParaConvertir = teclado.nextInt();

                double convertor = datosConversor.convertorMoneda(opcionUsuario, cantidadaParaConvertir);

                System.out.println("Convertiste " + cantidadaParaConvertir + monedaSeleccionada + " a " +
                        datosConversor.getValorConvertir() + ": " + convertor + "$"
                );
                opcionUsuario = teclado.nextLine();
            } catch (IllegalArgumentException i){
                System.out.println("Ocurrió un Error con el tipo de entrada que quisiste ingresar.\n" +
                        "Por favor, asegurese de que la entrada este en las opciones anteriores.");
            }

        }

        System.out.println("El programa finalizó, gracias por usar nuestro Convertor de Monedas." +
                "\nVuelva pronto!");
        teclado.close();
    }
}
