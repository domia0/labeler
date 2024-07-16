package de.hsrm.labeler.api.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import de.hsrm.labeler.api.dto.UpdateLabelDto;


public class HttpService {
    private CloseableHttpClient httpClient;
    private String baseUrl;

    public HttpService() {
        this.httpClient = HttpClients.createDefault();
        this.baseUrl = loadBaseUrlFromProperties();
    }

    private String loadBaseUrlFromProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
            System.out.println(input);
            return properties.getProperty("labeler.restUrl");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load properties from application.properties", ex);
        }
    }

    public String sendGetRequest(String path) throws Exception {
        HttpGet httpGet = new HttpGet(baseUrl + path);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            } else {
                throw new Exception("No response received");
            }
        }
    }

    public String sendPostRequest(String path, UpdateLabelDto dto) throws Exception {
        HttpPost httpPost = new HttpPost(baseUrl + path);
        String json = new ObjectMapper().writeValueAsString(dto);
        System.out.println("postURL: "+httpPost);
        System.out.println("json"+json);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // Header der Antwort ausgeben
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println(header.getName() + ": " + header.getValue());
                System.out.println("Status: "+ response.getStatusLine().getStatusCode());
            }

            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            } else {
                throw new Exception("No response received");
            }
        }
    }
}