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
//  manager.disableService("SCardSvr"); // Akıllı Kart hizmetini devre dışı bırakır
//        manager.disableService("BEService"); // Battle Eye
//        manager.disableService("ScDeviceEnum"); // Akıllı Kart Cihaz Numaralandırma Hizmeti
//        manager.disableService("XboxGipSvc"); // Xbox Accessory Management Service
//        manager.disableService("XboxNetApiSvc"); // Xbox Live Ağ Hizmeti
//        manager.disableService("XblAuthManager"); // Xbox Live Kimlik Doğrulama Yöneticisi
//        manager.disableService("XblGameSave"); // Xbox Live Oyun Kaydetme
//        manager.disableService("XblAuthManager"); // Xbox Live Kimlik Doğrulama Yöneticisi
//        manager.disableService("XblAuthManager"); // Xbox Live Kimlik Doğrulama Yöneticisi
//        manager.disableService("XblAuthManager"); // Xbox Live Kimlik Doğrulama Yöneticisi
//        manager.disableService("WpcMonSvc"); // Ebeveyn  Denetimleri
// String command = "powershell.exe -Command \"Stop-Service -Name '" + serviceName + "' -Force; " +
//                         "Set-Service -Name '" + serviceName + "' -StartupType Disabled\"";