package org.example;

import java.io.IOException;

public class AppManager {

    public void stopApp(String processName) {
        try {
            Process process = Runtime.getRuntime().exec("taskkill /IM " + processName + " /F");
            process.waitFor();
            System.out.println(processName + " kapatıldı.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(processName + " kapatılırken hata oluştu.");
        }
    }
}
