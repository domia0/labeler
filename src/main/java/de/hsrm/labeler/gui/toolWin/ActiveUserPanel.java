package de.hsrm.labeler.gui.toolWin;

import com.intellij.openapi.project.Project;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.api.dto.SymptomCandidate;
import de.hsrm.labeler.common.LabelerState;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ActiveUserPanel extends JPanel {
    private JLabel userLabel;
    private LabelerState labelerState;

    public ActiveUserPanel(Project project) {
        project.getMessageBus().connect().subscribe(
                UpdatePanelNotifier.UPDATE_PANEL_TOPIC,
                (UpdatePanelNotifier) this::renderPanel);

        labelerState = LabelerState.getInstance();
        setLayout(new BorderLayout());

        userLabel = new JLabel("Aktiver Benutzer: " + getUserString(labelerState.getUserId()));
        add(userLabel, BorderLayout.CENTER);
    }

    public void renderPanel(Labeler response) {
        userLabel.setText("Aktiver Benutzer: " + getUserString(labelerState.getUserId()));
        userLabel.revalidate();
        userLabel.repaint();
    }

    public String getUserString(int userId){
        if (userId == 0) {
            return "Bjoern";
        } else if (userId == 1) {
            return "Sven Eric";
        } else {
            return "<none>";
        }
    }
}