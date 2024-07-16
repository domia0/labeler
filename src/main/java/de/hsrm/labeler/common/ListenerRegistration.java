package de.hsrm.labeler.common;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import de.hsrm.labeler.gui.editor.ChangeEditorTabListener;
import de.hsrm.labeler.gui.editor.ChangeEditorTabNotifier;
import de.hsrm.labeler.gui.project.OpenLabelerFileListener;
import de.hsrm.labeler.gui.project.OpenLabelerFileNotifier;


public class ListenerRegistration {
    private boolean listenersRegistered = false;

    public static ListenerRegistration getInstance() {
        return ApplicationManager.getApplication().getService(ListenerRegistration.class);
    }

    public void initializeListeners(Project project) {
        System.out.println("listener: "+listenersRegistered);
        if (!listenersRegistered) {
            project.getMessageBus().connect().subscribe(
                    OpenLabelerFileNotifier.OPEN_LABELER_FILE_TOPIC,
                    new OpenLabelerFileListener(project)
            );
            project.getMessageBus().connect().subscribe(
                    ChangeEditorTabNotifier.CHANGE_EDITOR_TAB_TOPIC,
                    new ChangeEditorTabListener(project)
            );
            listenersRegistered = true;
        }
    }

    public boolean isRegistered (){
        return listenersRegistered;
    }
}
