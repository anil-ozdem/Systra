package org.example;

import java.io.IOException;

public class RestorePointCreator {

    public static void createRestorePoint(String description) {
        String command = "powershell.exe -Command \"Checkpoint-Computer -Description '" + description + "' -RestorePointType 'MODIFY_SETTINGS'\"";

        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("✅ Sistem geri yükleme noktası başarıyla oluşturuldu.");
            } else {
                System.out.println("❌ Geri yükleme noktası oluşturulamadı. Çıkış kodu: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("🚫 Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createRestorePoint("Systra - Kayıt Değişikliği Öncesi");
    }
}
