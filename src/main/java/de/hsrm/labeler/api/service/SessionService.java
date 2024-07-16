package de.hsrm.labeler.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.application.ApplicationManager;
import de.hsrm.labeler.api.dto.Session;
import de.hsrm.labeler.api.http.HttpService;

import java.util.List;

public class SessionService {

    private static HttpService httpService;
    List<Session> sessions = null;

    public static SessionService getInstance() {
        if (httpService == null) {
            httpService = new HttpService();
        }
        return ApplicationManager.getApplication().getService(SessionService.class);
    }
    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessionRequest() {
        String response;
        String path = "/session";
        try {
            response = httpService.sendGetRequest(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send GET request", e);
        }

        List<Session> parsedResponse = parseSession(response);
        setSessions(parsedResponse);
        return parsedResponse;
    }

    public List<Session> parseSession(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(json, new TypeReference<List<Session>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }
}
