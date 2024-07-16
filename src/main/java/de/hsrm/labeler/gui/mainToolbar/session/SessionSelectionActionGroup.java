package de.hsrm.labeler.gui.mainToolbar.session;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import de.hsrm.labeler.api.dto.Session;
import de.hsrm.labeler.api.service.SessionService;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SessionSelectionActionGroup extends ActionGroup {
    @NotNull
    @Override
    public AnAction[] getChildren(AnActionEvent event) {
        SessionService sessionService = SessionService.getInstance();
        List<Session> sessions = sessionService.getSessions();
        List<AnAction> actions = new ArrayList<>();

        actions.add(new GetSessionAction("Get Sessions", "Get sessions from backend"));

        if (sessions != null) {
            for (Session session : sessions) {
                actions.add(new SessionSelectionAction(session.getId(), "Select this session"));
            }
        }

        return actions.toArray(new AnAction[0]);
    }
}