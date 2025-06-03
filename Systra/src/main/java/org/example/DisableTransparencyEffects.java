package org.example;

import java.io.IOException;

public class DisableTransparencyEffects {
    public static void main(String[] args) {
        try {
            // Saydamlık efektlerini kapatma komudu
            String command = "reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize\" /v EnableTransparency /t REG_DWORD /d 0 /f";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            System.out.println("Saydamlık efektleri kapatıldı.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
