package de.hsrm.labeler.gui.mainToolbar.navigation;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.api.service.AppUpdateService;
import de.hsrm.labeler.api.service.LabelerService;
import de.hsrm.labeler.common.LabelerState;
import org.jetbrains.annotations.NotNull;

public class SolutionPrevAction extends AnAction {
    private Project project;
    private AppUpdateService appUpdateService;


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.project = e.getProject();
        this.appUpdateService = AppUpdateService.getInstance();
        LabelerState labelerState = LabelerState.getInstance();
        LabelerService labelerService = LabelerService.getInstance();
        Labeler response = labelerService.getLabeler(labelerService.buildGetPath(labelerState, labelerState.getPrevSolutionId(),true));
        labelerState.setSolutionId(response.getSolution().getCorrelationId());
        labelerState.setNextSolutionId(response.getNextSolutionId());
        labelerState.setPrevSolutionId(response.getPrevSolutionId());
        labelerState.setHtml(response.getSolution().getExercise().getHtml());

        appUpdateService.update(project, response);
    }
}