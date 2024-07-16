package de.hsrm.labeler.gui.toolWin.exercise;

import javax.swing.*;

public class ExerciseToolWindowContent {
    private final ExerciseToolWindowPanel contentPanel;

    public ExerciseToolWindowContent(ExerciseToolWindowPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel.getContentPanel();
    }
}