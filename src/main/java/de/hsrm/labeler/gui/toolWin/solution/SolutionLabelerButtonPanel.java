package de.hsrm.labeler.gui.toolWin.solution;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import de.hsrm.labeler.api.dto.Decision;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.api.dto.SymptomCandidate;
import de.hsrm.labeler.api.dto.UpdateLabelDto;
import de.hsrm.labeler.api.service.LabelerService;
import de.hsrm.labeler.common.LabelerState;
import de.hsrm.labeler.gui.toolWin.UpdatePanelNotifier;
import javax.swing.*;
import java.awt.event.ActionListener;


public class SolutionLabelerButtonPanel extends SimpleToolWindowPanel {

    private final JPanel buttonPanel = new JPanel();
    private final JRadioButton symptomButton;
    private final JRadioButton keinSymptomButton;
    private final JRadioButton unentschiedenButton;
    private JRadioButton invisibleButton;
    private boolean isCandidateSelected = false;
    private SymptomCandidate selectedCandidate;
    private LabelerState labelerState;
    private Decision decision = null;
    LabelerService labelerService;


    public SolutionLabelerButtonPanel(Project project) {
        super(true);
        labelerState = LabelerState.getInstance();
        labelerService = LabelerService.getInstance();

        ButtonGroup buttonGroup = new ButtonGroup();
        symptomButton = new JRadioButton("Symptom");
        keinSymptomButton = new JRadioButton("Kein Symptom");
        unentschiedenButton = new JRadioButton("Unentschieden");
        invisibleButton = new JRadioButton();
        invisibleButton.setVisible(false);

        buttonGroup.add(symptomButton);
        buttonGroup.add(keinSymptomButton);
        buttonGroup.add(unentschiedenButton);
        buttonGroup.add(invisibleButton);

        setCandidateSelected(false);

        buttonPanel.add(symptomButton);
        buttonPanel.add(keinSymptomButton);
        buttonPanel.add(unentschiedenButton);

        project.getMessageBus().connect().subscribe(
                UpdatePanelNotifier.UPDATE_PANEL_TOPIC,
                (UpdatePanelNotifier) this::clearSelection);

        ActionListener actionListener = e -> {
            if (isCandidateSelected) {
                String command = e.getActionCommand();
                System.out.println("Command: " + command);
                UpdateLabelDto dto = new UpdateLabelDto();
                dto.setSymptomCandidateId(selectedCandidate.getId());
                dto.setUserId(labelerState.getUserId());
                parseDecision(command);
                dto.setDecision(decision);
                System.out.println("Decision: " + decision + "  " + dto.isConfirmed());
                labelerService.updateLabel(labelerState.getSolutionId(), dto);
            }
        };
        symptomButton.addActionListener(actionListener);
        keinSymptomButton.addActionListener(actionListener);
        unentschiedenButton.addActionListener(actionListener);
    }

    public void setCandidateSelected(boolean selected) {
        this.isCandidateSelected = selected;
        symptomButton.setEnabled(selected);
        keinSymptomButton.setEnabled(selected);
        unentschiedenButton.setEnabled(selected);

        if (!selected) {
            invisibleButton.setSelected(true);
        }
    }

    public void parseDecision(String command) {
        if (command.equals(symptomButton.getText())) {
            decision = Decision.TRUE;
        }
        if (command.equals(keinSymptomButton.getText())) {
            decision = Decision.FALSE;
        }
        if (command.equals(unentschiedenButton.getText())) {
            decision = Decision.UNDECIDED;
        }
    }

    public void setSelectedCandidate(SymptomCandidate selectedCandidate) {
        this.selectedCandidate = selectedCandidate;
    }

    public JPanel getContentPanel() {
        return buttonPanel;
    }

    public void selectButtonBasedOnStatus(Integer status) {

        if (status == null) {
            invisibleButton.setSelected(true);
        } else {
            switch (status) {
                case 0:
                    unentschiedenButton.setSelected(true);
                    break;
                case 1:
                    symptomButton.setSelected(true);
                    break;
                case 2:
                    keinSymptomButton.setSelected(true);
                    break;
                default:
                    invisibleButton.setSelected(true);
                    break;
            }
        }
        this.repaint();
        this.revalidate();
    }

    public void clearSelection(Labeler labeler){
        invisibleButton.setSelected(true);
        this.repaint();
        this.revalidate();
    }
}