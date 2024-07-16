package de.hsrm.labeler.gui.mainToolbar.session;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import de.hsrm.labeler.gui.project.LabelerFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.api.service.*;
import de.hsrm.labeler.common.LabelerState;
import de.hsrm.labeler.common.ListenerRegistration;

import javax.swing.*;


public class SessionSelectionAction extends AnAction {
    private String session;
    private Project project;
    private AppUpdateService appUpdateService;
    private ListenerRegistration listenerRegistration;


    public SessionSelectionAction(@Nullable String session, @Nullable String description) {
        super(session, description, null);
        this.session = session;
        this.appUpdateService = AppUpdateService.getInstance();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.project = e.getProject();
        WriteCommandAction.runWriteCommandAction(project, () -> {
            LabelerFile.deleteDirectory(project, "src");
        });

        LabelerState labelerState = LabelerState.getInstance();
        LabelerService labelerService = LabelerService.getInstance();
        if (labelerState.getUserId() == -1) {
            JOptionPane.showMessageDialog(
                    null, "Bitte waehlen Sie einen Benutzer aus.",
                    "Benutzer nicht ausgewaehlt",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            labelerState.setSession(session);
            Labeler response = labelerService.getLabeler(labelerService.buildGetPath(labelerState, "", false));
            labelerState.setSolutionId(response.getSolution().getCorrelationId());
            labelerState.setNextSolutionId(response.getNextSolutionId());
            labelerState.setPrevSolutionId(response.getPrevSolutionId());
            labelerState.setHtml(response.getSolution().getExercise().getHtml());

            listenerRegistration = ListenerRegistration.getInstance();

            if (listenerRegistration.isRegistered() == false) {
                listenerRegistration.initializeListeners(project);
            }
            appUpdateService.update(project, response);
        }
    }
}