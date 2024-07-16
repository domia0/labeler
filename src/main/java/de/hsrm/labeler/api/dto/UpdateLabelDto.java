package de.hsrm.labeler.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateLabelDto {
    private String symptomCandidateId;
    private Decision decision;
    private Integer userId;

    public String getSymptomCandidateId() {
        return symptomCandidateId;
    }

    public void setSymptomCandidateId(String symptomCandidateId) {
        this.symptomCandidateId = symptomCandidateId;
    }

    @JsonProperty("decision")
    public Decision isConfirmed() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

