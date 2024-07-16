package de.hsrm.labeler.gui.editor;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

public class ChangeEditorTabListener implements ChangeEditorTabNotifier {

    private final Project project;

    public ChangeEditorTabListener(Project project) {
        this.project = project;
    }

    @Override
    public void changeEditorTab(Project project, VirtualFile virtualFile) {
        PsiManager psiManager = PsiManager.getInstance(project);
        PsiFile psiFile = psiManager.findFile(virtualFile);

        if (psiFile != null) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(psiFile.getProject());
            fileEditorManager.openFile(psiFile.getVirtualFile(), true);
        }
    }
}
