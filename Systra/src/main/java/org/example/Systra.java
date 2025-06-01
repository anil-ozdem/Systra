package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Systra extends JFrame {

    private JButton cleanTempButton;
    private JButton stopAppsButton;
    private JButton stopServicesButton;
    private JButton stopRecommendedServicesButton;
    private JButton disableTransparencyButton;
    private JButton activatePowerModeButton;
    private JButton removeWindowsAppsButton;

    private JPanel appsPanel;
    private JPanel servicesPanel;

    private Map<String, String> appsMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> appCheckBoxes = new LinkedHashMap<>();

    // recommendedServiceCheckBoxes haritasının key'i artık hizmet kodu olarak kalıyor,
    // biz checkbox üzerinde kullanıcı dostu adı gösteriyoruz.
    private Map<String, JCheckBox> recommendedServiceCheckBoxes = new LinkedHashMap<>();
    // servicesMap: kullanıcı dostu isim -> hizmet kodu
    private Map<String, String> servicesMap = new LinkedHashMap<>();

    // Bu dizide, kapatılması veya kontrol edilmesi hedeflenen hizmetlerin kodları yer alıyor.
    private final String[] recommendedServices = {
            "SCardSvr",
            "ScDeviceEnum",
            "SCPolicySvc",  // Eğer bu kod için mapping yoksa, kod gösterilecektir.
            "XboxGipSvc",
            "XboxNetApiSvc",
            "XblAuthManager",
            "XblGameSave",
            "WpcMonSvc",
            "WSearch",
            "WPDBusEnum",
            "WMPNetworkSvc",
            "WerSvc",
            "wercplsupport",
            "WdiSystemHost",
            "upnphost",
            "UmRdpService",
            "TroubleshootingSvc",
            "tzautoupdate",
            "PcaSvc",
            "lfsvc",
            "DusmSvc",
            "DiagTrack",
            "SSDPSRV",
            "WalletService",
            "RetailDemo",
            "PhoneSvc",
            "dmwappushservice",
            "Fax",
            "RemoteRegistry",
            "SharedAccess",
            "MapsBroker"
    };

    public Systra() {
        setTitle("Systra");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Uygulamalar için kullanıcı dostu isim -> process name eşleşmeleri
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

        // Hizmetler için kullanıcı dostu isim -> hizmet kodu eşleşmeleri
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

        // Butonların oluşturulması
        cleanTempButton = new JButton("Geçici Dosyaları Temizle");
        stopAppsButton = new JButton("Seçili Uygulamaları Kapat");
        stopServicesButton = new JButton("Seçili Servisleri Durdur");
        stopRecommendedServicesButton = new JButton("Tavsiye Edilenleri Otomatik Durdur");
        disableTransparencyButton = new JButton("Saydamlığı Kapat");
        activatePowerModeButton = new JButton("Nihai Performans Modunu Etkinleştir");
        removeWindowsAppsButton = new JButton("Windows Uygulamalarını Kaldır");

        // Uygulama panelinin oluşturulması
        appsPanel = new JPanel();
        appsPanel.setLayout(new BoxLayout(appsPanel, BoxLayout.Y_AXIS));
        appsPanel.setBorder(BorderFactory.createTitledBorder("Kapatmak İstediğiniz Uygulamaları Seçin"));
        for (Map.Entry<String, String> entry : appsMap.entrySet()) {
            JCheckBox checkBox = new JCheckBox(entry.getKey());
            appCheckBoxes.put(entry.getKey(), checkBox);
            appsPanel.add(checkBox);
        }

        // Hizmet panelinin oluşturulması
        servicesPanel = new JPanel();
        servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));
        servicesPanel.setBorder(BorderFactory.createTitledBorder("Çalışmaya Devam Edecek Hizmetleri Seçin"));
        // Hizmet kodunu alıp, karşılığında bulunan kullanıcı dostu adı gösterecek şekilde düzenliyoruz.
        for (String serviceCode : recommendedServices) {
            String displayName = null;
            for (Map.Entry<String, String> entry : servicesMap.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(serviceCode)) {
                    displayName = entry.getKey();
                    break;
                }
            }
            if (displayName == null) {
                displayName = serviceCode;
            }
            JCheckBox checkBox = new JCheckBox(displayName);
            recommendedServiceCheckBoxes.put(serviceCode, checkBox);
            servicesPanel.add(checkBox);
        }

        // Buton panelinin oluşturulması ve eklenmesi
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(cleanTempButton);
        buttonsPanel.add(stopAppsButton);
        buttonsPanel.add(stopServicesButton);
        buttonsPanel.add(stopRecommendedServicesButton);
        buttonsPanel.add(disableTransparencyButton);
        buttonsPanel.add(activatePowerModeButton);
        buttonsPanel.add(removeWindowsAppsButton);

        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(appsPanel));
        centerPanel.add(new JScrollPane(servicesPanel));
        add(centerPanel, BorderLayout.CENTER);

        // Temp temizleme işlemi
        cleanTempButton.addActionListener(e -> {
            TempCleaner cleaner = new TempCleaner();
            cleaner.cleanTempFolders();
            JOptionPane.showMessageDialog(this, "Temp klasörleri temizlendi.");
        });

        // Seçili uygulamaları kapatma işlemi
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

        // Seçili servisleri durdurma işlemi
        stopServicesButton.addActionListener(e -> {
            WindowsServicesManager serviceManager = new WindowsServicesManager();
            for (Map.Entry<String, JCheckBox> entry : recommendedServiceCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    serviceManager.disableService(entry.getKey());
                }
            }
            JOptionPane.showMessageDialog(this, "Seçili hizmetler durduruldu.");
        });

        // Tavsiye edilen, fakat seçilmeyen servislerin durdurulması
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

        // Saydamlık efektlerini kapatma işlemi
        disableTransparencyButton.addActionListener(e -> {
            try {
                String command = "reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize\" /v EnableTransparency /t REG_DWORD /d 0 /f";
                Process process = Runtime.getRuntime().exec(command);
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    JOptionPane.showMessageDialog(this, "Saydamlık efektleri başarıyla kapatıldı.");
                } else {
                    JOptionPane.showMessageDialog(this, "Saydamlık kapatma işlemi başarısız oldu.");
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Bir hata oluştu: " + ex.getMessage());
            }
        });

        // Nihai Performans modunu etkinleştirme işlemi
        activatePowerModeButton.addActionListener(e -> {
            new Thread(() -> {
                PowerModeActivator.main(new String[]{});
            }).start();
            JOptionPane.showMessageDialog(this, "Nihai Performans modu etkinleştirme işlemi başlatıldı.");
        });

        // Windows uygulamalarını kaldırma işlemi
        removeWindowsAppsButton.addActionListener(e -> {
            new Thread(() -> {
                RemoveWindowsApps.main(new String[]{});
            }).start();
            JOptionPane.showMessageDialog(this, "Windows uygulamaları kaldırma işlemleri başlatıldı.");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Systra ui = new Systra();
            ui.setVisible(true);
        });
    }
}