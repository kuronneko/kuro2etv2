package dao;

import dto.Filek2et;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static utils.Properties.getConfigParameters;

public class Filek2etDAO {

    private String authToken;
    private String API_KEY = getConfigParameters().getProperty("API_KEY");

    public Filek2etDAO(String authToken) {
        this.authToken = authToken;
    }

    public List<Filek2et> getAll() throws IOException {

        List<Filek2et> filek2etArray = new ArrayList<>();

        // Check if the authToken is set
        if (authToken == null || authToken.isEmpty()) {
            throw new IOException("Token not available. Login first.");
        }

        // Construct other API endpoint URL
        URL otherEndpointUrl = new URL(getConfigParameters().getProperty("BASE_URL") + "file2es/get-all?text=decrypt");

        // Create connection
        HttpURLConnection connection = (HttpURLConnection) otherEndpointUrl.openConnection();

        // Set the request method and headers
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("x-api-key", API_KEY);
        connection.setRequestProperty("Authorization", "Bearer " + authToken);
        connection.setRequestProperty("User-Agent", "Kuro2etv2");

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Process the response based on the response code
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Parse JSON response into a list of Filek2et objects
                filek2etArray = parseJsonResponse(response.toString());
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

            throw new IOException("Other API request failed. Response Code: " + responseCode);
        }

        return filek2etArray;
    }

    private List<Filek2et> parseJsonResponse(String jsonResponse) {

        List<Filek2et> filek2etList = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try {
            // Parse the JSON response
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);

            if ("success".equals(jsonObject.get("status"))) {
                JSONArray dataArray = (JSONArray) jsonObject.get("data");

                // Iterate over the data array
                for (Object dataObject : dataArray) {
                    JSONObject record = (JSONObject) dataObject;
                    int id = Integer.parseInt(record.get("id").toString());
                    String name = (String) record.get("name");
                    String text = (String) record.get("text");
                    String created_at = (String) record.get("created_at");
                    String updated_at = (String) record.get("updated_at");

                    filek2etList.add(new Filek2et(id, name, text, created_at, updated_at));
                }
            }
        } catch (ParseException e) {
            // Handle the parsing exception
            e.printStackTrace();
        }

        return filek2etList;
    }

}



