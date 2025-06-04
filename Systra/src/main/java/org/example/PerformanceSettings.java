package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PerformanceSettings {
    public static void applyBestPerformance() {
        try {
            System.out.println("Performans ayarları uygulanıyor...");

            // Windows'un performans önceliğine geçmesini sağlar
            String cmdVisualFX = "reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v VisualFXSetting /t REG_DWORD /d 2 /f";
            runCommand(cmdVisualFX);

            // 2. "Ekran yazı tipi kenarlarını düzelt" seçeneğini aktif bırak
            String cmdFontSmoothing = "reg add \"HKCU\\Control Panel\\Desktop\" /v FontSmoothing /t REG_SZ /d 2 /f";
            runCommand(cmdFontSmoothing);

            // Şeffaf seçim dikdörtgeni göster (Show translucent selection rectangle)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v ListviewAlphaSelect /t REG_DWORD /d 0 /f");
            // Pencere içindeki denetim ve öğelere solma efekti (Fade or slide menus into view)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v MenuAnimation /t REG_DWORD /d 0 /f");
            // Açılır menüleri sol veya kaydır (Fade or slide tooltips into view)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v TooltipAnimation /t REG_DWORD /d 0 /f");
            // Genişletilmiş klasörler için ortak görevleri kullan (Animate controls and elements inside windows)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v AnimateControls /t REG_DWORD /d 0 /f");
            // Menü öğeleri sol veya kaydır (Fade or slide menus into view - often same as MenuAnimation)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v MenuFade /t REG_DWORD /d 0 /f");
            // Fare işaretçisinin altına gölge göster (Show shadows under mouse pointer)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v CursorShadow /t REG_DWORD /d 0 /f");
            // Pencereleri sürüklerken pencere içeriğini göster (Show window contents while dragging)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v FullWindowDrag /t REG_DWORD /d 0 /f");
            // Simgelerin etiket gölgesini kullan (Use drop shadows for icon labels on the desktop)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v IconShadow /t REG_DWORD /d 0 /f");
            // Açılış veya kapanışta Windows animasyonlarını etkinleştir (Animate windows when minimizing and maximizing)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v MinimizeAnimate /t REG_DWORD /d 0 /f");
            // Açılır kutuları sol veya kaydır (Fade or slide combo boxes into view)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v ComboAnimation /t REG_DWORD /d 0 /f");
            // Görev çubuğunda animasyonları etkinleştir (Animate windows on taskbar)
            runCommand("reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects\" /v TaskbarAnimations /t REG_DWORD /d 0 /f");
            // Windows ve düğmelerde görsel stilleri etkinleştir (Enable visual styles on windows and buttons)

            System.out.println("✅ Performans ayarları başarıyla uygulandı.");
            System.out.println("⚠️ Ayarların tamamen geçerli olması için oturumu kapatıp açmak veya bilgisayarı yeniden başlatmak gerekebilir.");

        } catch (Exception e) {
            System.out.println("🚫 Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void runCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("Komut başarısız oldu: " + command + " Çıkış kodu: " + exitCode);
        }
    }

    public static void main(String[] args) {
        System.out.println("Performans ayarları uygulanıyor...");
        applyBestPerformance();
    }
}