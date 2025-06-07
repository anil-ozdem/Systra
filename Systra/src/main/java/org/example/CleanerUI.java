package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class CleanerUI extends JFrame {

    private JButton cleanTempButton;
    private JButton stopAppsButton;
    private JButton stopServicesButton;
    private JButton stopRecommendedServicesButton;

    private JPanel appsPanel;
    private JPanel servicesPanel;

    private Map<String, String> appsMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> appCheckBoxes = new LinkedHashMap<>();

    private Map<String, JCheckBox> recommendedServiceCheckBoxes = new LinkedHashMap<>();
    private Map<String, String> servicesMap = new LinkedHashMap<>();

    private final String[] recommendedServices = {
            "SCardSvr", // Akıllı Kart Hizmeti
            "ScDeviceEnum", // Akıllı Kart Cihaz Numaralandırma Hizmeti
            "SCPolicySvc", // Akıllı Kart Kaldırma İlkesi
            "XboxGipSvc", // Xbox Accessory Management Service
            "XboxNetApiSvc", // Xbox Live Ağ Hizmeti
            "XblAuthManager", // Xbox Live Kimlik Doğrulama Yöneticisi
            "XblGameSave", // Xbox Live Oyun Kaydetme
            "WpcMonSvc", // Ebeveyn Denetimleri
            "WSearch", // Windows Search
            "WPDBusEnum", // Taşınabilir Aygıt Numaralandırma Hizmeti
            "WMPNetworkSvc", // Windows Media Player Ağ Paylaşımı Hizmeti
            "WerSvc", // Windows Hata Raporlama Hizmeti
            "wercplsupport", // Sorun Raporları Denetim Masası Desteği
            "WdiSystemHost", // Tanılama Sistemi Ana Bilgisayarı
            "upnphost", // UPnP Aygıt Ana Makinesi
            "UmRdpService", // Uzak Masaüstü Hizmetleri Kullanıcı Modu Bağlantı Noktası Yeniden Yönlendiricisi
            "TroubleshootingSvc", // Önerilen Sorun Giderme Hizmeti
            "tzautoupdate", // Otomatik Saat Dilimi Güncelleştirici
            "PcaSvc", // Program Uyumluluk Yardımcısı Hizmeti
            "lfsvc", // Coğrafi Konum Hizmeti
            "DusmSvc", // Veri Kullanımı
            "DiagTrack", // Bağlı Kullanıcı Deneyimleri ve Telemetrisi
            "SSDPSRV", // SSDP Bulma
            "WalletService", // Cüzdan Hizmeti
            "RetailDemo", // Perakende Gösteri Hizmeti
            "PhoneSvc", // Telefon Hizmeti
            "dmwappushservice", //Tanılama Mesajları
            "Fax", // Faks Hizmeti
            "RemoteRegistry", // Uzak Kayıt Defteri
            "SharedAccess", // İnternet Bağlantısı Paylaşımı
            "MapsBroker" // Haritalar uygulaması arka plan hizmeti

    };
    public CleanerUI() {
        setTitle("CosmicAutomation");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        appsMap.put("Ses Kaydedici", "SoundRecorder.exe");
        appsMap.put("Paint", "mspaint.exe");
        appsMap.put("Not Defteri", "notepad.exe");
        appsMap.put("Saat", "Timedate.cpl");
        appsMap.put("Medya Oynatıcı", "wmplayer.exe");
        appsMap.put("Kamera", "WindowsCamera.exe");
        appsMap.put("Microsoft Clipchamp", "Clipchamp.exe");
        appsMap.put("Microsoft Edge", "msedge.exe");
        appsMap.put("Ekran Alıntısı Aracı", "SnippingTool.exe");
        appsMap.put("WhatsApp", "WhatsApp.exe");
        appsMap.put("Fotoğraflar", "Microsoft.Photos.exe");
        appsMap.put("Copilot", "Copilot.exe");
        appsMap.put("Microsoft Teams", "Teams.exe");
        appsMap.put("Outlook", "Outlook.exe");
        appsMap.put("Solitaire", "Solitaire.exe");
        appsMap.put("Xbox", "XboxApp.exe");
        appsMap.put("Hesap Makinesi", "Calculator.exe");
        appsMap.put("Microsoft Store", "WinStore.App.exe");

        servicesMap.put("Akıllı Kart Hizmeti", "SCardSvr");
        servicesMap.put("Battle Eye", "BEService");
        servicesMap.put("Akıllı Kart Cihaz Numaralandırma Hizmeti", "ScDeviceEnum");
        servicesMap.put("Xbox Accessory Management Service", "XboxGipSvc");
        servicesMap.put("Xbox Live Ağ Hizmeti", "XboxNetApiSvc");
        servicesMap.put("Xbox Live Kimlik Doğrulama Yöneticisi", "XblAuthManager");
        servicesMap.put("Xbox Live Oyun Kaydetme", "XblGameSave");
        servicesMap.put("Ebeveyn Denetimleri", "WpcMonSvc");
        servicesMap.put("Windows Search", "WSearch");
        servicesMap.put("Taşınabilir Aygıt Numaralandırma Hizmeti", "WPDBusEnum");
        servicesMap.put("Windows Media Player Ağ Paylaşımı Hizmeti", "WMPNetworkSvc");
        servicesMap.put("Windows Hata Raporlama Hizmeti", "WerSvc");
        servicesMap.put("Sorun Raporları Denetim Masası Desteği", "wercplsupport");
        servicesMap.put("Tanılama Sistemi Ana Bilgisayarı", "WdiSystemHost");
        servicesMap.put("UPnP Aygıt Ana Makinesi", "upnphost");
        servicesMap.put("Uzak Masaüstü Kullanıcı Modu Yönlendirme", "UmRdpService");
        servicesMap.put("Önerilen Sorun Giderme Hizmeti", "TroubleshootingSvc");
        servicesMap.put("Otomatik Saat Dilimi Güncelleştirici", "tzautoupdate");
        servicesMap.put("Program Uyumluluk Yardımcısı", "PcaSvc");
        servicesMap.put("Coğrafi Konum Hizmeti", "lfsvc");
        servicesMap.put("Veri Kullanımı", "DusmSvc");
        servicesMap.put("Telemetri ve Kullanıcı Deneyimi", "DiagTrack");
        servicesMap.put("SSDP Bulma", "SSDPSRV");
        servicesMap.put("Cüzdan Hizmeti", "WalletService");
        servicesMap.put("WMI Performans Bağdaştırıcısı", "wmiApSrv");
        servicesMap.put("Perakende Gösteri Hizmeti", "RetailDemo");
        servicesMap.put("Telefon Hizmeti", "PhoneSvc");

        cleanTempButton = new JButton("Geçici Dosyaları Temizle");
        stopAppsButton = new JButton("Seçili Uygulamaları Kapat");
        stopServicesButton = new JButton("Seçili Servisleri Durdur");
        stopRecommendedServicesButton = new JButton("Tavsiye Edilenleri Otomatik Durdur");

        appsPanel = new JPanel();
        appsPanel.setLayout(new BoxLayout(appsPanel, BoxLayout.Y_AXIS));
        appsPanel.setBorder(BorderFactory.createTitledBorder("Kapatmak İstediğiniz Uygulamaları Seçin"));

        for (Map.Entry<String, String> entry : appsMap.entrySet()) {
            JCheckBox checkBox = new JCheckBox(entry.getKey());
            appCheckBoxes.put(entry.getKey(), checkBox);
            appsPanel.add(checkBox);
        }

        servicesPanel = new JPanel();
        servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));
        servicesPanel.setBorder(BorderFactory.createTitledBorder("Çalışmaya Devam Edecek Hizmetleri Seçin"));

        for (String serviceName : recommendedServices) {
            JCheckBox checkBox = new JCheckBox(serviceName);
            recommendedServiceCheckBoxes.put(serviceName, checkBox);
            servicesPanel.add(checkBox);
        }

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(cleanTempButton);
        buttonsPanel.add(stopAppsButton);
        buttonsPanel.add(stopServicesButton);
        buttonsPanel.add(stopRecommendedServicesButton);

        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(appsPanel));
        centerPanel.add(new JScrollPane(servicesPanel));
        add(centerPanel, BorderLayout.CENTER);

        cleanTempButton.addActionListener(e -> {
            TempCleaner cleaner = new TempCleaner();
            cleaner.cleanTempFolders();
            JOptionPane.showMessageDialog(this, "Temp klasörleri temizlendi.");
        });

        stopAppsButton.addActionListener(e -> {
            AppManager appManager = new AppManager();
            for (Map.Entry<String, JCheckBox> entry : appCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    String processName = appsMap.get(entry.getKey());
                    appManager.stopApp(processName);
                }
            }
            JOptionPane.showMessageDialog(this, "Seçili uygulamalar kapatıldı.");
        });

        stopServicesButton.addActionListener(e -> {
            WindowsServicesManager serviceManager = new WindowsServicesManager();
            for (Map.Entry<String, JCheckBox> entry : recommendedServiceCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    serviceManager.disableService(entry.getKey());
                }
            }
            JOptionPane.showMessageDialog(this, "Seçili hizmetler durduruldu.");
        });

        stopRecommendedServicesButton.addActionListener(e -> {
            WindowsServicesManager serviceManager = new WindowsServicesManager();
            for (String service : recommendedServices) {
                JCheckBox checkBox = recommendedServiceCheckBoxes.get(service);
                if (checkBox != null && !checkBox.isSelected()) {
                    serviceManager.disableService(service);
                }
            }
            JOptionPane.showMessageDialog(this, "Seçmediğiniz tavsiye edilen hizmetler durduruldu.");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CleanerUI ui = new CleanerUI();
            ui.setVisible(true);
        });
    }
}
