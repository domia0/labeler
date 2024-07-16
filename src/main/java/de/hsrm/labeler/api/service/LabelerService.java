package de.hsrm.labeler.api.service;

import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.api.dto.UpdateLabelDto;
import de.hsrm.labeler.api.http.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.application.ApplicationManager;
import de.hsrm.labeler.common.LabelerState;


public class LabelerService {

    private static HttpService httpService;

    public static LabelerService getInstance() {
        if (httpService == null) {
            httpService = new HttpService();
        }
        return ApplicationManager.getApplication().getService(LabelerService.class);
    }

    public Labeler getLabeler(String path) {
        String response;
        try {
            response = httpService.sendGetRequest(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send GET request", e);
        }
        System.out.println(path);
        Labeler parsedResponse = parseLabeler(response);

        return parsedResponse;
    }

    public String buildGetPath(LabelerState labelerState, String solutionId, boolean hasSolutionId) {
        if (hasSolutionId == true) {
            return "/session/" + labelerState.getSession() + "/review?userId=" + labelerState.getUserId() + "&solutionId=" + solutionId;
        } else {
            return "/session/" + labelerState.getSession() + "/review?userId=" + labelerState.getUserId();
        }
    }

    public String buildPostPath(String solutionId) {
        return "/solution/" + solutionId + "/label";
    }

    public void updateLabel(String solutionId, UpdateLabelDto dto) {
        String path = buildPostPath(solutionId);
        String response;
        try {
            System.out.println("post: "+path);
            response = httpService.sendPostRequest(path, dto);
            System.out.println("response: "+response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send POST request" + e.getMessage(), e);
        }
    }

    public Labeler parseLabeler(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(json, Labeler.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }
}
