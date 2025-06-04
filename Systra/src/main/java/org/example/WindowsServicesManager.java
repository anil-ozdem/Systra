/*
 * The MIT License
 *
 * Copyright (c) 2025 Anıl Özdem
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.example;

import java.io.IOException;

public class WindowsServicesManager {

    public void disableService(String serviceName) {
        // Önce hizmeti durdur, sonra startupType'ı Disabled yap
        String command = "powershell.exe -Command \"Stop-Service -Name '" + serviceName + "' -Force; " +
                "Set-Service -Name '" + serviceName + "' -StartupType Disabled\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println(serviceName + " hizmeti devre dışı bırakıldı.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(serviceName + " hizmetini kapatırken hata oluştu.");

        }
    }


    public static void main(String[] args) {
        WindowsServicesManager manager = new WindowsServicesManager();
        manager.disableService("SCardSvr"); // Akıllı Kart hizmetini devre dışı bırakır
        manager.disableService("ScDeviceEnum"); // Akıllı Kart Cihaz Numaralandırma Hizmeti
        manager.disableService("SCPolicySvc"); // Akıllı Kart Kaldırma İlkesi
        manager.disableService("ScDeviceEnum"); // Akıllı Kart Cihaz Numaralandırma Hizmeti
        manager.disableService("XboxGipSvc"); // Xbox Accessory Management Service
        manager.disableService("XboxNetApiSvc"); // Xbox Live Ağ Hizmeti
        manager.disableService("XblAuthManager"); // Xbox Live Kimlik Doğrulama Yöneticisi
        manager.disableService("XblGameSave"); // Xbox Live Oyun Kaydetme
        manager.disableService("WpcMonSvc"); // Ebeveyn Denetimleri
        manager.disableService("WSearch"); // Windows Search
        manager.disableService("WPDBusEnum"); // Taşınabilir Aygıt Numaralandırma Hizmeti
        manager.disableService("WMPNetworkSvc"); // Windows Media Player Ağ Paylaşımı Hizmeti
        manager.disableService("WerSvc"); // Windows Hata Raporlama Hizmeti
        manager.disableService("wercplsupport"); // Sorun Raporları Denetim Masası Desteği
        manager.disableService("WdiSystemHost"); // Tanılama Sistemi Ana Bilgisayarı
        manager.disableService("upnphost"); // UPnP Aygıt Ana Makinesi
        manager.disableService("UmRdpService"); // Uzak Masaüstü Hizmetleri Kullanıcı Modu Bağlantı Noktası Yeniden Yönlendiricisi
        manager.disableService("TroubleshootingSvc"); // Önerilen Sorun Giderme Hizmeti
        manager.disableService("tzautoupdate"); // Otomatik Saat Dilimi Güncelleştirici
        manager.disableService("PcaSvc"); // Program Uyumluluk Yardımcısı Hizmeti
        manager.disableService("lfsvc"); // Coğrafi Konum Hizmeti
        manager.disableService("DusmSvc"); // Veri Kullanımı
        manager.disableService("DiagTrack"); // Bağlı Kullanıcı Deneyimleri ve Telemetrisi
        manager.disableService("SSDPSRV"); // SSDP Bulma
        manager.disableService("WalletService"); // Cüzdan Hizmeti
        manager.disableService("RetailDemo"); // Perakende Gösteri Hizmeti
        manager.disableService("PhoneSvc"); // Telefon Hizmeti
        manager.disableService("dmwappushservice"); // Tanılama Mesajları
        manager.disableService("Fax"); // Faks Hizmeti
        manager.disableService("RemoteRegistry"); // Uzak Kayıt Defteri
        manager.disableService("SharedAccess"); // İnternet Bağlantısı Paylaşımı
        manager.disableService("MapsBroker"); // Haritalar uygulaması arka plan hizmeti
    }
}
