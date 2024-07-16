package de.hsrm.labeler.api.dto;

public class Test {
    private TestSuite testsuite;
    private int testcases;
    private int failed;
    private int passed;
    private Stats stats;
    private String leaks;
    private double correctness;
    private String output;
    private String coverage;

    public TestSuite getTestsuite() {
        return testsuite;
    }

    public void setTestsuite(TestSuite testsuite) {
        this.testsuite = testsuite;
    }

    public int getTestcases() {
        return testcases;
    }

    public void setTestcases(int testcases) {
        this.testcases = testcases;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getLeaks() {
        return leaks;
    }

    public void setLeaks(String leaks) {
        this.leaks = leaks;
    }

    public double getCorrectness() {
        return correctness;
    }

    public void setCorrectness(double correctness) {
        this.correctness = correctness;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
}
