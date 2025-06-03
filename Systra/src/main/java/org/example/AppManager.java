package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppManager {


    public void stopApp(String processName) {
        if (processName == null || processName.isEmpty()) {
            System.out.println("Process adı boş ya da null: " + processName);
            return;
        }

        try {
            // Önce process var mı kontrol et
            if (!isProcessRunning(processName)) {
                System.out.println(processName + " bulunamadı, kapatma işlemi yapılmadı.");
                return;
            }

            // Süreç varsa kapat
            Process process = Runtime.getRuntime().exec("taskkill /IM " + processName + " /F");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            while ((s = stdError.readLine()) != null) {
                System.err.println(s);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(processName + " başarıyla kapatıldı.");
            } else {
                System.out.println(processName + " kapatılamadı, exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(processName + " kapatılırken hata oluştu.");
        }
    }

    private boolean isProcessRunning(String processName) {
        try {
            Process process = Runtime.getRuntime().exec("tasklist");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // tasklist çıktısını satır satır okur
            while ((line = reader.readLine()) != null) {
                // Satır küçük harfe çevrilip processName içeriyorsa true döner
                if (line.toLowerCase().contains(processName.toLowerCase())) {
                    return true;
                }
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
