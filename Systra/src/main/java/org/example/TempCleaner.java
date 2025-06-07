package org.example;

import java.io.File;

public class TempCleaner {

    public void cleanTempFolders() {
        String tempEnv = System.getenv("TEMP");
        String tempFolder = "C:\\Windows\\Temp";
        String prefetchFolder = "C:\\Windows\\Prefetch";

        System.out.println("Starting cleaning process...");

        if (tempEnv != null) {
            System.out.println("Cleaning %TEMP% folder: " + tempEnv);
            deleteFiles(new File(tempEnv));
        } else {
            System.out.println("%TEMP% environment variable not found.");
        }

        System.out.println("Cleaning temp folder: " + tempFolder);
        deleteFiles(new File(tempFolder));

        System.out.println("Cleaning prefetch folder: " + prefetchFolder);
        deleteFiles(new File(prefetchFolder));

        System.out.println("All specified folders cleaned.");
    }

    private void deleteFiles(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            System.out.println("Skipping invalid directory: " + (dir == null ? "null" : dir.getAbsolutePath()));
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("Failed to list files in: " + dir.getAbsolutePath());
            return;
        }

        for (File file : files) {
            try {
                if (file.isDirectory()) {
                    deleteFiles(file);
                    if (file.list().length == 0) {
                        if (!file.delete()) {
                            System.out.println("Failed to delete empty directory: " + file.getAbsolutePath());
                        }
                    }
                } else {
                    if (!file.delete()) {
                        System.out.println("Failed to delete file: " + file.getAbsolutePath());
                    }
                }
            } catch (SecurityException e) {
                System.out.println("No permission to delete: " + file.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("Error deleting " + file.getAbsolutePath() + ": " + e.getMessage());
            }
        }
    }
}
