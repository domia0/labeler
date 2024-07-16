package de.hsrm.labeler.gui.toolWin;

import com.intellij.util.messages.Topic;
import de.hsrm.labeler.api.dto.Labeler;

public interface UpdatePanelNotifier {

    @Topic.ProjectLevel
    Topic<UpdatePanelNotifier> UPDATE_PANEL_TOPIC =
            Topic.create("Change Solution", UpdatePanelNotifier.class);

    void sessionSelected(Labeler labeler);
}