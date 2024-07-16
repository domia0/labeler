package de.hsrm.labeler.api.dto;

public class SymptomCandidate {
    private String id;
    private String symptomId;
    private String fqcn;
    private DetectorResult detectorResult;
    private Label label1;
    private Label label2;
    private Object coverage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(String symptomId) {
        this.symptomId = symptomId;
    }

    public String getFqcn() {
        return fqcn;
    }

    public void setFqcn(String fqcn) {
        this.fqcn = fqcn;
    }

    public DetectorResult getDetectorResult() {
        return detectorResult;
    }

    public void setDetectorResult(DetectorResult detectorResult) {
        this.detectorResult = detectorResult;
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label label1) {
        this.label1 = label1;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label label2) {
        this.label2 = label2;
    }

    public Object getCoverage() {
        return coverage;
    }

    public void setCoverage(Object coverage) {
        this.coverage = coverage;
    }
}





