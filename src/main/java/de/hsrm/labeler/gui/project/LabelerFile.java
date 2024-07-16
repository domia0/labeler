package de.hsrm.labeler.gui.project;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiElement;
import de.hsrm.labeler.common.LabelerState;


public class LabelerFile {
    private PsiFile labelerPsiFile;
    private PsiDirectory projectPsiDirectory;
    private PsiDirectory filesPsiDirectory;
    private String content;
    private String packageName;
    private String className;
    private OpenFileDescriptor openFileDescriptor;
    private String filePath;


    public LabelerFile(Project project, String content, String fqcn) {

        VirtualFile projectDir = project.getBaseDir();
        this.projectPsiDirectory = PsiManager.getInstance(project).findDirectory(projectDir);
        this.content = content;

        if (projectDir != null) {

            if (projectPsiDirectory != null) {

                try {
                    this.packageName = getPackageName(fqcn);

                    if (this.packageName == null) {
                        this.filePath = "src";

                        filesPsiDirectory = projectPsiDirectory.findSubdirectory(this.filePath);

                        if (filesPsiDirectory == null) {
                            filesPsiDirectory = projectPsiDirectory.createSubdirectory(filePath);
                        }
                    } else {
                        this.filePath = "src."+this.packageName;
                        filesPsiDirectory = createDirectories(projectPsiDirectory, this.filePath);
                    }

                    this.className = getClassName(fqcn);

                    PsiFile existingFile = filesPsiDirectory.findFile(className + ".java");

                    if (existingFile == null) {
                        labelerPsiFile = PsiFileFactory.getInstance(project).createFileFromText(className + ".java", FileTypeManager.getInstance().getFileTypeByExtension("java"), content);

                        PsiElement addedElement = filesPsiDirectory.add(labelerPsiFile);

                        if (addedElement instanceof PsiFile) {
                            labelerPsiFile = (PsiFile) addedElement;
                            openInEditor(project, labelerPsiFile);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                LabelerState labelerState = LabelerState.getInstance();
                labelerState.setPsiFile(labelerPsiFile);
            }
        }
    }



    public void openInEditor(Project project, PsiFile psiFile) {
        openFileDescriptor = new OpenFileDescriptor(project, psiFile.getViewProvider().getVirtualFile());
        openFileDescriptor.navigate(true);
    }

    public String getPackageName(String fqcn) {
        int lastDotIndex = fqcn.lastIndexOf('.');

        if (lastDotIndex == -1) {
            return "";
        }
        return fqcn.substring(0, lastDotIndex);
    }

    public String getFilePath(){
        return filePath;
    }

    public String getClassName(String fqcn) {
        int lastDotIndex = fqcn.lastIndexOf('.');

        if (lastDotIndex == -1) {
            return fqcn;
        }
        return fqcn.substring(lastDotIndex + 1);
    }

    public PsiDirectory createDirectories(PsiDirectory baseDir, String path) {
        String[] parts = path.split("\\.");
        PsiDirectory[] currentDir = new PsiDirectory[]{baseDir};

            for (String part : parts) {
                PsiDirectory nextDir = currentDir[0].findSubdirectory(part);
                if (nextDir == null) {
                    nextDir = currentDir[0].createSubdirectory(part);
                }
                currentDir[0] = nextDir;
            }

        return currentDir[0];
    }

    public static void deleteDirectory(Project project, String relativePath) {
        VirtualFile projectDir = project.getBaseDir();
        PsiDirectory projectPsiDirectory = PsiManager.getInstance(project).findDirectory(projectDir);

        if (projectPsiDirectory != null) {
            PsiDirectory targetDirectory = projectPsiDirectory.findSubdirectory(relativePath);
            if (targetDirectory != null) {
                try {
                    targetDirectory.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
