package de.hsrm.labeler.gui.project;

import com.intellij.openapi.project.Project;
import com.intellij.util.messages.Topic;
import de.hsrm.labeler.api.dto.Labeler;


public interface OpenLabelerFileNotifier {
    Topic<OpenLabelerFileNotifier> OPEN_LABELER_FILE_TOPIC = Topic.create("Change Solution", OpenLabelerFileNotifier.class);

    void openLabelerFile(Project project, Labeler response);
}
