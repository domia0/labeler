package de.hsrm.labeler.gui.toolWin;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import de.hsrm.labeler.gui.toolWin.exercise.ExerciseToolWindowContent;
import de.hsrm.labeler.gui.toolWin.exercise.ExerciseToolWindowPanel;
import de.hsrm.labeler.gui.toolWin.solution.SolutionLabelerToolWindowContent;
import de.hsrm.labeler.gui.toolWin.solution.SolutionLabelerToolWindowPanel;
import org.jetbrains.annotations.NotNull;


public class LabelerToolWindowFactory implements ToolWindowFactory, DumbAware {

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    // Labeler Tab
    SolutionLabelerToolWindowContent labelerWindowContent = new SolutionLabelerToolWindowContent(new SolutionLabelerToolWindowPanel(project));
    Content content = ContentFactory.getInstance().createContent(labelerWindowContent.getContentPanel(), "Solution", false);
    toolWindow.getContentManager().addContent(content);

    // Exercise Tab
    ExerciseToolWindowContent exerciseWindowContent = new ExerciseToolWindowContent(new ExerciseToolWindowPanel(project));
    Content content2 = ContentFactory.getInstance().createContent(exerciseWindowContent.getContentPanel(), "Exercise", false);
    toolWindow.getContentManager().addContent(content2);
  }
}
