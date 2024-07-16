package de.hsrm.labeler.gui.mainToolbar.user;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import de.hsrm.labeler.common.LabelerState;
import org.jetbrains.annotations.NotNull;

public class UserBjoernAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        LabelerState labelerState = LabelerState.getInstance();
        labelerState.setUserId(0);
    }
}