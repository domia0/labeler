package de.hsrm.labeler.gui.toolWin.solution;

import de.hsrm.labeler.api.dto.Label;
import de.hsrm.labeler.api.dto.Labeler;
import de.hsrm.labeler.api.dto.SymptomCandidate;
import de.hsrm.labeler.api.service.AppUpdateService;
import de.hsrm.labeler.gui.editor.ChangeEditorTabNotifier;
import de.hsrm.labeler.gui.toolWin.UpdatePanelNotifier;
import de.hsrm.labeler.api.service.LabelerService;
import de.hsrm.labeler.common.LabelerState;
import de.hsrm.labeler.gui.editor.CodeHighlighter;

import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SolutionSymptomCandidatesPanel extends SimpleToolWindowPanel {

    private final JPanel contentPanel = new JPanel();
    private final JList<SymptomCandidate> symptomCandidatesList = new JList<>();
    private RangeHighlighter currentHighlighter;
    private LabelerState labelerState;
    private SolutionLabelerButtonPanel solutionLabelerButtonPanel;

    public SolutionSymptomCandidatesPanel(Project project, SolutionLabelerButtonPanel solutionLabelerButtonPanel) {
        super(true);

        LabelerService labelerService = LabelerService.getInstance();
        this.labelerState = LabelerState.getInstance();
        this.solutionLabelerButtonPanel = solutionLabelerButtonPanel;

        project.getMessageBus().connect().subscribe(
                UpdatePanelNotifier.UPDATE_PANEL_TOPIC,
                (UpdatePanelNotifier) this::renderPanel);

        contentPanel.add(new JScrollPane(symptomCandidatesList));

        symptomCandidatesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    SymptomCandidate selectedCandidate = symptomCandidatesList.getSelectedValue();
                    boolean isSelected = selectedCandidate != null;
                    solutionLabelerButtonPanel.setSelectedCandidate(selectedCandidate);
                    solutionLabelerButtonPanel.setCandidateSelected(isSelected);
                    if (isSelected) {
                        String packageName = selectedCandidate.getFqcn();

                        String relativeFilePath = packageName.replace('.', '/') + ".java";

                        PsiManager psiManager = PsiManager.getInstance(project);
                        System.out.println("pathrel:"+ relativeFilePath);
                        System.out.println("Highlights:"+ selectedCandidate.getDetectorResult().getRange().getBeginLine()+" "+ selectedCandidate.getDetectorResult().getRange().getBeginColumn() +" "+selectedCandidate.getDetectorResult().getRange().getEndLine()+" "+selectedCandidate.getDetectorResult().getRange().getEndColumn());
                        PsiFile psiFile = psiManager.findFile(project.getBaseDir().findFileByRelativePath("src/"+relativeFilePath));


                        if (psiFile != null) {
                            project.getMessageBus()
                                    .syncPublisher(ChangeEditorTabNotifier.CHANGE_EDITOR_TAB_TOPIC)
                                    .changeEditorTab(project, psiFile.getVirtualFile());

                            CodeHighlighter codeHighlighter = new CodeHighlighter(psiFile);

                            if (currentHighlighter != null && currentHighlighter.isValid()) {
                                codeHighlighter.removeHighlight(currentHighlighter);
                            }

                            System.out.println("Highlights:"+ selectedCandidate.getDetectorResult().getRange().getBeginLine()+" "+ selectedCandidate.getDetectorResult().getRange().getBeginColumn() +" "+selectedCandidate.getDetectorResult().getRange().getEndLine()+" "+selectedCandidate.getDetectorResult().getRange().getEndColumn());
                            currentHighlighter = codeHighlighter.highlight(
                                    selectedCandidate.getDetectorResult().getRange().getBeginLine(),
                                    selectedCandidate.getDetectorResult().getRange().getBeginColumn(),
                                    selectedCandidate.getDetectorResult().getRange().getEndLine(),
                                    selectedCandidate.getDetectorResult().getRange().getEndColumn());

                            AppUpdateService.getInstance().setCodeHighlighter(codeHighlighter);
                            AppUpdateService.getInstance().setCurrentHighlighter(currentHighlighter);
                        }

                        Label label = null;
                        if (labelerState.getUserId() == 0){
                            label = selectedCandidate.getLabel1();
                        } else if (labelerState.getUserId() == 1){
                            label = selectedCandidate.getLabel2();
                        }
                        Integer decision = label != null ? label.getDecision() : null;
                        solutionLabelerButtonPanel.selectButtonBasedOnStatus(decision);
                    }
                }
            }
        });
    }


    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void renderPanel(Labeler response) {
        List<SymptomCandidate> symptomCandidates = response.getSolution().getSymptomCandidates();
        SymptomCandidate[] symptomCandidatesArray = symptomCandidates.toArray(new SymptomCandidate[0]);
        symptomCandidatesList.setListData(symptomCandidatesArray);
        symptomCandidatesList.revalidate();
        symptomCandidatesList.repaint();
    }
}