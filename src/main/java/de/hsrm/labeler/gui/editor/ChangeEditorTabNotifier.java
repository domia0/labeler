package de.hsrm.labeler.gui.editor;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;

public interface ChangeEditorTabNotifier {
    Topic<ChangeEditorTabNotifier> CHANGE_EDITOR_TAB_TOPIC = Topic.create("Change Solution", ChangeEditorTabNotifier.class);

    void changeEditorTab(Project project, VirtualFile virtualFile);
}