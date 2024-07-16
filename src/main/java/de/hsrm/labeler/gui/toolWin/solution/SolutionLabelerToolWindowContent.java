package de.hsrm.labeler.gui.toolWin.solution;

import javax.swing.*;


public class SolutionLabelerToolWindowContent {

    private final SolutionLabelerToolWindowPanel contentPanel;

    public SolutionLabelerToolWindowContent(SolutionLabelerToolWindowPanel panel) {
        contentPanel = panel;
    }

    public JPanel getContentPanel() {
        return contentPanel.getContentPanel();
    }
}