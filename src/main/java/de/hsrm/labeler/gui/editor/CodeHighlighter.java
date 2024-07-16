package de.hsrm.labeler.gui.editor;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.markup.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.ui.JBColor;
import de.hsrm.labeler.common.LabelerState;


public class CodeHighlighter {
    private final PsiFile psiFile;

    public CodeHighlighter(PsiFile psiFile) {
        this.psiFile = psiFile;
    }

    public RangeHighlighter highlight(int startLine, int startColumn, int endLine, int endColumn) {
        Document document = psiFile.getViewProvider().getDocument();
        if (document == null) {
            return null;
        }

        int startOffset = document.getLineStartOffset(startLine - 1) + startColumn - 1;
        int endOffset = document.getLineStartOffset(endLine - 1) + endColumn;

        Editor editor = FileEditorManager.getInstance(psiFile.getProject()).getSelectedTextEditor();
        if (editor == null) {
            return null;
        }

        MarkupModel markupModel = editor.getMarkupModel();
        TextAttributes textAttributes = new TextAttributes();
        textAttributes.setForegroundColor(JBColor.BLACK);
        textAttributes.setBackgroundColor(JBColor.YELLOW);
        textAttributes.setEffectColor(JBColor.GREEN);
        textAttributes.setEffectType(EffectType.LINE_UNDERSCORE);

        RangeHighlighter highlighter = markupModel.addRangeHighlighter(startOffset, endOffset, 0, textAttributes, HighlighterTargetArea.EXACT_RANGE);

        LogicalPosition startLogicalPosition = editor.offsetToLogicalPosition(startOffset);
        editor.getScrollingModel().scrollTo(startLogicalPosition, ScrollType.CENTER);

        return highlighter;
    }

    public void removeHighlight(RangeHighlighter highlighter) {
        if (highlighter == null || !highlighter.isValid()) {
            return;
        }

        LabelerState labelerState = LabelerState.getInstance();
        Editor editor = FileEditorManager.getInstance(labelerState.getPsiFile().getProject()).getSelectedTextEditor();
        if (editor != null) {
            MarkupModel markupModel = editor.getMarkupModel();
            try {
                markupModel.removeHighlighter(highlighter);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        }
    }
}