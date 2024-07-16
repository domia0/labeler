package de.hsrm.labeler.api.dto;

public class Compilation {
    private boolean compiling;
    private String output;

    public boolean isCompiling() {
        return compiling;
    }

    public void setCompiling(boolean compiling) {
        this.compiling = compiling;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}