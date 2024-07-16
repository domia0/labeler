package de.hsrm.labeler.api.dto;

import java.util.Map;

public class DetectorResult {
    private Range range;
    private Map<String, Object> vars;

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public void setVars(Map<String, Object> vars) {
        this.vars = vars;
    }
}