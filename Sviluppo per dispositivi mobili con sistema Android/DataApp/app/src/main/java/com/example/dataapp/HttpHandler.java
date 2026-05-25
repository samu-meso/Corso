package com.example.dataapp;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpHandler {

    private static final String TAG = "HttpHandler";
    private HttpURLConnection conn;

    public String makeServiceCall(String reqUrl) {
        String response = null;

        try {
            URL url = new URL(reqUrl);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // IMPORTANT
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            } else {
                Log.e(TAG, "HTTP error code: " + responseCode);
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException", e);
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException", e);
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, java.nio.charset.StandardCharsets.UTF_8)
        );

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading stream", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing stream", e);
            }
        }

        return sb.toString();
    }
}