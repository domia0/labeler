package de.hsrm.labeler.api.dto;

public enum Decision {
    UNDECIDED,
    TRUE,
    FALSE;

    @com.fasterxml.jackson.annotation.JsonValue
    public int getValue() {
        return ordinal();
    }
}
