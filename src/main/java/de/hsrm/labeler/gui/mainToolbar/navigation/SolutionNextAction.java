package de.hsrm.labeler.gui.mainToolbar.navigation;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.api.service.AppUpdateService;
import de.hsrm.labeler.api.service.LabelerService;
import de.hsrm.labeler.common.LabelerState;

import javax.swing.*;


public class SolutionNextAction extends AnAction {
    private Project project;
    private AppUpdateService appUpdateService;


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.project = e.getProject();
        this.appUpdateService = AppUpdateService.getInstance();
        LabelerState labelerState = LabelerState.getInstance();
        LabelerService labelerService = LabelerService.getInstance();

        if (labelerState.getUserId() == -1 || labelerState.getSession() == null) {
            JOptionPane.showMessageDialog(
                    null, "Bitte waehlen Sie einen Benutzer und eine Session aus.",
                    "Benutzer oder Session nicht ausgewaehlt",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Labeler response = labelerService.getLabeler(labelerService.buildGetPath(labelerState, labelerState.getNextSolutionId(), true));
            System.out.println(response.getProgress());
            labelerState.setSolutionId(response.getSolution().getCorrelationId());
            labelerState.setNextSolutionId(response.getNextSolutionId());
            labelerState.setPrevSolutionId(response.getPrevSolutionId());
            labelerState.setHtml(response.getSolution().getExercise().getHtml());
            appUpdateService.update(project, response);

        }
    }
}