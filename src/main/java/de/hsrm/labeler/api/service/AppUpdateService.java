package de.hsrm.labeler.api.service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.project.Project;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.common.LabelerState;
import de.hsrm.labeler.gui.editor.CodeHighlighter;
import de.hsrm.labeler.gui.project.OpenLabelerFileNotifier;
import de.hsrm.labeler.gui.toolWin.UpdatePanelNotifier;


public class AppUpdateService {
    private CodeHighlighter codeHighlighter;
    private RangeHighlighter currentHighlighter;
    private LabelerState labelerState;

    public static AppUpdateService getInstance() {
        return ApplicationManager.getApplication().getService(AppUpdateService.class);
    }

    public void update(Project project, Labeler response){
        labelerState = LabelerState.getInstance();

        if (currentHighlighter != null && currentHighlighter.isValid()) {
            codeHighlighter.removeHighlight(currentHighlighter);
            currentHighlighter = null;
        }

        project.getMessageBus()
                .syncPublisher(UpdatePanelNotifier.UPDATE_PANEL_TOPIC)
                .sessionSelected(response);

        project.getMessageBus()
                .syncPublisher(OpenLabelerFileNotifier.OPEN_LABELER_FILE_TOPIC)
                .openLabelerFile(project, response);

    }

    public void setCodeHighlighter(CodeHighlighter codeHighlighter) {
        this.codeHighlighter = codeHighlighter;
    }

    public void setCurrentHighlighter(RangeHighlighter currentHighlighter) {
        this.currentHighlighter = currentHighlighter;
    }

}