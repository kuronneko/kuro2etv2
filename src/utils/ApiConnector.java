package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static utils.Properties.getConfigParameters;

public class ApiConnector {

    public static String loginAndGetToken(String email, String password) throws IOException {
        // Construct login URL
        URL loginUrl = new URL(getConfigParameters().getProperty("BASE_URL") + "auth/login");

        // Create connection
        HttpURLConnection connection = (HttpURLConnection) loginUrl.openConnection();

        // Set the request method to POST
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json"); // Assuming JSON content
        connection.setRequestProperty("x-api-key", getConfigParameters().getProperty("API_KEY"));  // Include API key in headers

        // Enable input/output streams
        connection.setDoOutput(true);

        // Construct the request payload as JSON
        String payload = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";

        // Write the payload to the output stream
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = payload.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }

        // Get the response code
        int responseCode = connection.getResponseCode();

        // If the login was successful, read the token from the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                // Parse the response to get the token (assuming it's a JSON response)
                return parseTokenFromJsonResponse(response.toString());
            }
        } else {
            // Print the error response for debugging purposes
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Error Response: " + response.toString());
            }

            throw new IOException("Login failed. Response Code: " + responseCode);
        }
    }

    private static String parseTokenFromJsonResponse(String jsonResponse) {
        // Parse the JSON response and extract the access_token
        int startIndex = jsonResponse.indexOf("\"access_token\":\"") + "\"access_token\":\"".length();
        int endIndex = jsonResponse.indexOf("\"", startIndex);
        return jsonResponse.substring(startIndex, endIndex);
    }

}
