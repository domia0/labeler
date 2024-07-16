package de.hsrm.labeler.api.dto;

import java.util.List;

public class TestSuite {
    private List<TestCase> testcases;
    private String stdout;
    private String stderr;
    private String frees;
    private String allocations;

    public List<TestCase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<TestCase> testcases) {
        this.testcases = testcases;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public String getFrees() {
        return frees;
    }

    public void setFrees(String frees) {
        this.frees = frees;
    }

    public String getAllocations() {
        return allocations;
    }

    public void setAllocations(String allocations) {
        this.allocations = allocations;
    }
}