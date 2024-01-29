package dao;

import dto.Filek2et;
import org.json.JSONArray;
import org.json.JSONObject;

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
        URL otherEndpointUrl = new URL("http://localhost/api/v1/file2es/get-all");

        // Create connection
        HttpURLConnection connection = (HttpURLConnection) otherEndpointUrl.openConnection();

        // Set the request method and headers
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("x-api-key", API_KEY);
        connection.setRequestProperty("Authorization", "Bearer " + authToken);

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

        JSONObject jsonObject = new JSONObject(jsonResponse);

        if ("success".equals(jsonObject.getString("status"))) {
            JSONArray dataArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject record = dataArray.getJSONObject(i);
                int id = record.getInt("id");
                String name = record.getString("name");
                String text = record.getString("text");
                String created_at = record.getString("created_at");
                String updated_at = record.getString("updated_at");

                filek2etList.add(new Filek2et(id, name, text, created_at, updated_at));
            }
        }

        return filek2etList;
    }

}



