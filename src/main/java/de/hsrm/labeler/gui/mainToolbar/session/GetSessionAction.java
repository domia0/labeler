package de.hsrm.labeler.gui.mainToolbar.session;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import de.hsrm.labeler.api.dto.Session;
import de.hsrm.labeler.api.service.SessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GetSessionAction extends AnAction {
    SessionService sessionService;

    public GetSessionAction(@Nullable String session, @Nullable String description) {
        super(session, description, null);
        this.sessionService = SessionService.getInstance();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        List<Session> sessions = sessionService.getSessionRequest();
        System.out.println(sessions);
    }
}
