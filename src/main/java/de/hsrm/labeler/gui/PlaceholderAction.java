package de.hsrm.labeler.gui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.addTextOverride("0", "test");
        this.displayTextInToolbar();
        System.out.println("klappt");
    }
}