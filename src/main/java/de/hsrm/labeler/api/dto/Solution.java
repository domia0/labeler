package de.hsrm.labeler.api.dto;

import java.util.List;

public class Solution {
    private String correlationId;
    private String uid;
    private String termId;
    private String submittedAt;
    private Exercise exercise;
    private String courseId;
    private EvalResult evalResult;
    private List<File> files;
    private List<SymptomCandidate> symptomCandidates;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public EvalResult getEvalResult() {
        return evalResult;
    }

    public void setEvalResult(EvalResult evalResult) {
        this.evalResult = evalResult;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<SymptomCandidate> getSymptomCandidates() {
        return symptomCandidates;
    }

    public void setSymptomCandidates(List<SymptomCandidate> symptomCandidates) {
        this.symptomCandidates = symptomCandidates;
    }
}