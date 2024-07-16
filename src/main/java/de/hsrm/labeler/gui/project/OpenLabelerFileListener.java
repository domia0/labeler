package de.hsrm.labeler.gui.project;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import de.hsrm.labeler.api.dto.File;
import de.hsrm.labeler.api.dto.Labeler;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;


public class OpenLabelerFileListener implements OpenLabelerFileNotifier {
    Project project;


    public OpenLabelerFileListener(Project project) {
        this.project = project;

        ProjectManager.getInstance().addProjectManagerListener(project, new ProjectManagerListener() {
            @Override
            public void projectClosing(@NotNull Project project) {
                WriteCommandAction.runWriteCommandAction(project, () -> {
                    LabelerFile.deleteDirectory(project, "src");
                });
            }
        });
    }

    @Override
    public void openLabelerFile(Project project, Labeler response) {
        List<File> files = Stream.concat(
                        response.getSolution().getFiles().stream(),
                        response.getSolution().getExercise().getFiles().stream()
                ).toList();

        try {
            WriteCommandAction.runWriteCommandAction(project, () -> {
                LabelerFile.deleteDirectory(project, "src");

                for (File file : files) {
                    new LabelerFile(project, file.getContent().replace("\r\n", "\n"), file.getFqcn());
                }
            });
        } catch (Exception e) {
            System.err.println("Ein Fehler ist aufgetreten beim Öffnen der Labeler-Dateien.");
            e.printStackTrace();
        }
    }
}