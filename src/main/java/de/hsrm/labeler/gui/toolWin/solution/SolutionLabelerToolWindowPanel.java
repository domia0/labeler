package de.hsrm.labeler.gui.toolWin.solution;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import de.hsrm.labeler.gui.toolWin.ActiveUserPanel;

import javax.swing.*;
import java.awt.*;

public class SolutionLabelerToolWindowPanel extends SimpleToolWindowPanel {
    JPanel contentPanel;
    ActiveUserPanel activeUserPanel;

    public SolutionLabelerToolWindowPanel (Project project) {
        super(true);
        contentPanel = new JPanel(new BorderLayout());
        activeUserPanel = new ActiveUserPanel(project);
        SolutionLabelerButtonPanel solutionLabelerButtonPanel = new SolutionLabelerButtonPanel(project);
        SolutionSymptomCandidatesPanel solutionSymptomCandidatesPanel = new SolutionSymptomCandidatesPanel(project, solutionLabelerButtonPanel);
        contentPanel.add(activeUserPanel, BorderLayout.SOUTH);
        contentPanel.add(solutionLabelerButtonPanel.getContentPanel(), BorderLayout.NORTH);
        contentPanel.add(solutionSymptomCandidatesPanel.getContentPanel(), BorderLayout.CENTER);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
