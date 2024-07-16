package de.hsrm.labeler.gui.toolWin.exercise;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.gui.toolWin.ActiveUserPanel;
import de.hsrm.labeler.gui.toolWin.UpdatePanelNotifier;
import javax.swing.*;
import java.awt.*;


public class ExerciseToolWindowPanel extends SimpleToolWindowPanel {
    private JPanel contentPanel;
    private JEditorPane editorPane;
    ActiveUserPanel activeUserPanel;

    public ExerciseToolWindowPanel(Project project) {
        super(true);

        project.getMessageBus().connect().subscribe(
                UpdatePanelNotifier.UPDATE_PANEL_TOPIC,
                (UpdatePanelNotifier) this::renderPanel);

        activeUserPanel = new ActiveUserPanel(project);

        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JScrollPane(editorPane), BorderLayout.CENTER);
        contentPanel.add(activeUserPanel, BorderLayout.SOUTH);
    }

    public JPanel getContentPanel() {
        return this.contentPanel;
    }

    public void setHtmlContent(String htmlContent) {
        editorPane.setText(htmlContent);
    }

    public void renderPanel(Labeler response) {
        String content = response.getSolution().getExercise().getHtml();
        setHtmlContent(content);
        editorPane.revalidate();
        editorPane.repaint();
    }
}
