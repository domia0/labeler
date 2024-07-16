package de.hsrm.labeler.api.dto;

public class Labeler {
    private Solution solution;
    private String nextSolutionId;
    private String prevSolutionId;
    private int current;
    private int total;
    private double progress;


    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public String getNextSolutionId() {
        return nextSolutionId;
    }

    public void setNextSolutionId(String nextSolutionId) {
        this.nextSolutionId = nextSolutionId;
    }

    public String getPrevSolutionId() {
        return prevSolutionId;
    }

    public void setPrevSolutionId(String prevSolutionId) {
        this.prevSolutionId = prevSolutionId;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}