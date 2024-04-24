package com.mycompany.principal.conversordemonedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;
import modelos.CurrencyER;

public class ConversorDeMonedas {

    public static void main(String[] args) throws IOException, InterruptedException {
        int opcion = 7;
        String currency = "";
        String currency2 = null;
        Double tipoCambio = null;

        String direccion = "";

        String menu = """
                      
                     Escriba el numero del cambio que desea realizar :
                     1- Dolar a Peso argentino
                     2- Peso Argentino a Dolar
                     3- Real a Dolar
                     4- Dolar a Real brasileño
                     5- Dolar a Peso colombiano
                     6- Peso Colombiano a Dolar
                     0- SALIR
                     """;

        Scanner scan = new Scanner(System.in);

        while (opcion != 0) {

            System.out.println(menu);
            opcion = scan.nextInt();

            if (opcion != 0) {
                System.out.println("Ingrese el valor a convertir:");
                double valor = scan.nextDouble();

                switch (opcion) {
                    case 1:
                        //DOLAR A PESO.El valor va en dolar, el tipoCambio es para peso.
                        direccion = "https://v6.exchangerate-api.com/v6/90f2fba6f1bd2cb4d49cc84f/latest/USD";
                        currency = "ARS";
                        currency2 = "USD";
                        break;
                    case 2:
                        //PESO A DOLAR
                        direccion = "https://v6.exchangerate-api.com/v6/90f2fba6f1bd2cb4d49cc84f/latest/USD";
                        currency = "ARS";
                        currency2 = "USD";
                        break;
                    case 3:
                        //REAL A DOLAR
                        direccion = "https://v6.exchangerate-api.com/v6/90f2fba6f1bd2cb4d49cc84f/latest/USD";
                        currency = "BRL";
                        currency2 = "USD";
                        break;
                    case 4:
                        //DOLAR A REAL
                        direccion = "https://v6.exchangerate-api.com/v6/90f2fba6f1bd2cb4d49cc84f/latest/USD";
                        currency = "BRL";
                        currency2 = "USD";
                        break;
                    case 5:
                        //DOLAR A PESO COLOMBIANO
                        direccion = "https://v6.exchangerate-api.com/v6/90f2fba6f1bd2cb4d49cc84f/latest/USD";
                        currency = "COP";
                        currency2 = "USD";
                        break;
                    case 6:
                        //PESO COLOMBIANO A DOLAR
                        direccion = "https://v6.exchangerate-api.com/v6/90f2fba6f1bd2cb4d49cc84f/latest/USD";
                        currency = "COP";
                        currency2 = "USD";
                        break;
                    default:
                        System.out.println("La opción indicada no es válida");

                }

                // obtener la respuesta JSON
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

// Deserializar el JSON en un objeto CurrencyER
                Gson gson = new Gson();
                CurrencyER currencyER = gson.fromJson(json, CurrencyER.class);

// Verificar si la base_code es válida
                if (!currencyER.base_code().equals(currency2)) {
                    System.out.println("La opción seleccionada no es válida para la moneda base actual.");
                } else {
                    // Obtener el valor de conversión para la moneda base seleccionada
                    tipoCambio = currencyER.conversion_rates().get(currency);
                }
                if (tipoCambio != null) {

                    System.out.println("Tipo de cambio para " + currency + ": " + tipoCambio);
                } else {
                    System.out.println("No se encontró el tipo de cambio para " + currency);
                }

                if (opcion == 1 || opcion == 4 || opcion == 5) {
                    Double cambioApesos = valor * tipoCambio;
                    System.out.println("Valor convertido de " + valor + " " + currency2 + " a " + currency + ": " + cambioApesos);
                } else {
                    Double cambioAdolar = valor / tipoCambio;

                    System.out.println("Valor convertido de " + valor + " " + currency + " a " + currency2 + ": " + cambioAdolar);
                }

            }
        }

        System.out.println("Gracias por utilizar nuestro servicio");
    }
}
