package com.example.shoappinglist.services.SOAP;

import android.os.StrictMode;
import com.example.shoappinglist.models.Product;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProductClient {

    public ProductClient(){}
    public void createProduct(Product product){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            // Define the SOAP request XML
            String soapRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:prod=\"http://localhost:8080/soap/product\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <prod:SaveProductRequest>\n" +
                    "         <prod:name>"+product.getName()+"</prod:name>\n" +
                    "         <prod:note>"+product.getNote()+"</prod:note>\n" +
                    "         <prod:price>"+product.getPrice()+"</prod:price>\n" +
                    "      </prod:SaveProductRequest>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";

            // Define the SOAP endpoint URL
            String soapEndpointUrl = "http://10.0.2.2:8080/ws";

            // Create a URL object for the SOAP endpoint
            URL url = new URL(soapEndpointUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the HTTP request method to POST
            connection.setRequestMethod("POST");

            // Set the content type for SOAP requests
            connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            connection.setRequestProperty("SOAPAction", "");

            // Enable input and output streams for the connection
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // Send the SOAP request
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(soapRequest);
            writer.flush();
            writer.close();

            // Get the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the SOAP response
            System.out.println("SOAP Response:\n" + response.toString());

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
