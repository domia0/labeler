package de.hsrm.labeler.common;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiFile;


public class LabelerState {
    private int userId = -1;
    private String session = null;
    private String solutionId;
    private String nextSolutionId;
    private String prevSolutionId;
    private String html;
    private PsiFile psiFile;

    public static LabelerState getInstance() {
        return ApplicationManager.getApplication().getService(LabelerState.class);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
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

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public PsiFile getPsiFile() {
        return psiFile;
    }

    public void setPsiFile(PsiFile psiFile) {
        this.psiFile = psiFile;
    }
}