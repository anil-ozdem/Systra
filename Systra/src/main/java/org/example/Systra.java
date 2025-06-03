package org.example;

import javax.swing.*;
import javax.swing.ScrollPaneConstants;
import java.awt.*;
import java.io.*;
import java.text.Collator;
import java.util.*;
import java.util.List;

public class Systra extends JFrame {

    private JButton cleanTempButton;
    private JButton stopAppsButton;
    private JButton stopServicesButton;
    private JButton disableTransparencyButton;
    private JButton activatePowerModeButton;
    private JButton removeWindowsAppsButton;

    private JPanel appsPanel;
    private JPanel servicesPanel;

    private Map<String, String> appsMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> appCheckBoxes = new LinkedHashMap<>();

    private Map<String, String> servicesMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> recommendedServiceCheckBoxes = new LinkedHashMap<>();

    // İşlem yapılacak hizmet kodlarını içeren dizi
    private final String[] recommendedServices = {
            "SCardSvr",
            "ScDeviceEnum",
            "SCPolicySvc",
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

    // Alfabetik sıralama
    private final Collator collator = Collator.getInstance(new Locale("tr", "TR"));

    public Systra() {
        setTitle("Systra");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        collator.setStrength(Collator.PRIMARY);

        loadApps();
        loadServices();

        buildAppsPanel();
        buildServicesPanel();

        // Butonlar
        cleanTempButton = new JButton("Geçici Dosyaları Temizle");
        stopAppsButton = new JButton("Arka Planda Çalışan Uygulamaları Kapat");
        stopServicesButton = new JButton("Tavsiye Edilen Hizmetleri Durdur");
        disableTransparencyButton = new JButton("Saydamlığı Kapat");
        activatePowerModeButton = new JButton("Nihai Performans Modunu Etkinleştir");
        removeWindowsAppsButton = new JButton("Windows Uygulamalarını Kaldır");

        buildButtonsPanel();
        buildLayout();
        wireActions();
    }

    // Uygulama ekleme alanaı
    private void loadApps() {
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
    }

    // Tavsiye Edilen Hizmetler
    private void loadServices() {
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
    }

    // Alfabetik sıralama
    private void buildAppsPanel() {
        appsPanel = new JPanel();
        appsPanel.setLayout(new BoxLayout(appsPanel, BoxLayout.Y_AXIS));
        appsPanel.setBorder(BorderFactory.createTitledBorder("Açık kalmasını istediğiniz uygulamaları seçin"));

        List<String> sortedApps = new ArrayList<>(appsMap.keySet());
        sortedApps.sort(collator);
        for (String appName : sortedApps) {
            JCheckBox cb = new JCheckBox(appName);
            appCheckBoxes.put(appName, cb);
            appsPanel.add(cb);
        }
    }

    // Hizmet panelini, önerilen hizmet kodlarına göre (kullanıcı dostu isimleri) alfabetik sıralı oluşturur
    private void buildServicesPanel() {
        servicesPanel = new JPanel();
        servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));
        servicesPanel.setBorder(BorderFactory.createTitledBorder("Çalışmaya Devam Edecek Hizmetleri Seçin"));

        // TreeMap kullanarak kullanıcı dostu isimlere göre sıralama
        TreeMap<String, String> sortedServices = new TreeMap<>(collator);
        for (String code : recommendedServices) {
            String display = null;
            for (Map.Entry<String, String> entry : servicesMap.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(code)) {
                    display = entry.getKey();
                    break;
                }
            }
            if (display == null) {
                display = code;
            }
            sortedServices.put(display, code);
        }
        for (Map.Entry<String, String> entry : sortedServices.entrySet()) {
            JCheckBox cb = new JCheckBox(entry.getKey());
            recommendedServiceCheckBoxes.put(entry.getValue(), cb);
            servicesPanel.add(cb);
        }
    }

    private void buildButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(stopAppsButton);              // Arka Planda Çalışan Uygulamaları Kapat
        buttonsPanel.add(cleanTempButton);             // Geçici Dosyaları Temizle
        buttonsPanel.add(activatePowerModeButton);       // Nihai Performans Modunu Etkinleştir
        buttonsPanel.add(disableTransparencyButton);     // Saydamlığı Kapat
        buttonsPanel.add(stopServicesButton);          // Hizmetleri Durdur
        buttonsPanel.add(removeWindowsAppsButton);       // Windows Uygulamalarını Kaldır
        add(buttonsPanel, BorderLayout.NORTH);
    }

    private void buildLayout() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(appsPanel));
        centerPanel.add(new JScrollPane(servicesPanel));
        add(centerPanel, BorderLayout.CENTER);
    }

    private void wireActions() {
        cleanTempButton.addActionListener(e -> {
            TempCleaner cleaner = new TempCleaner();
            cleaner.cleanTempFolders();
            JOptionPane.showMessageDialog(this, "Temp klasörleri temizlendi.");
        });

        // Uygulama bölümünde: işaretli olanlar açık kalsın, işaretlenmeyenler kapatılsın.
        stopAppsButton.addActionListener(e -> {
            AppManager appManager = new AppManager();
            for (Map.Entry<String, JCheckBox> entry : appCheckBoxes.entrySet()) {
                if (!entry.getValue().isSelected()) {
                    String processName = appsMap.get(entry.getKey());
                    appManager.stopApp(processName);
                }
            }
            JOptionPane.showMessageDialog(this,
                    "Seçilen uygulamalar dışındaki uygulamaların çalışması durduruldu.");
        });

        // Hizmetlerde: sadece işaretli olanlar çalışmaya devam etsin; işaretli olmayanlar disable edilsin.
        stopServicesButton.addActionListener(e -> {
            WindowsServicesManager serviceManager = new WindowsServicesManager();
            for (Map.Entry<String, JCheckBox> entry : recommendedServiceCheckBoxes.entrySet()) {
                if (!entry.getValue().isSelected()) {
                    serviceManager.disableService(entry.getKey());
                }
            }
            JOptionPane.showMessageDialog(this,
                    "İşaretli olmayan hizmetler durduruldu; yalnızca seçili hizmetler çalışıyor.");
        });

        disableTransparencyButton.addActionListener(e -> {
            try {
                String command = "reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize\" " +
                        "/v EnableTransparency /t REG_DWORD /d 0 /f";
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

        activatePowerModeButton.addActionListener(e -> {
            new Thread(() -> PowerModeActivator.main(new String[]{})).start();
            JOptionPane.showMessageDialog(this, "Nihai Performans modu etkinleştirme işlemi başlatıldı.");
        });

        removeWindowsAppsButton.addActionListener(e -> {
            showWindowsAppsSelectionDialog();
        });
    }

    // Windows Uygulamaları için seçim oluşturur
    private void showWindowsAppsSelectionDialog() {
        Map<String, String> windowsAppsMap = new LinkedHashMap<>();
        windowsAppsMap.put("Cortana", "Get-AppxPackage -allusers Microsoft.549981C3F5F10 | Remove-AppxPackage");
        windowsAppsMap.put("3D Builder", "Get-AppxPackage *3dbuilder* | Remove-AppxPackage");
        windowsAppsMap.put("3D Uygulamalar", "Get-AppxPackage *3d* | Remove-AppxPackage");
        windowsAppsMap.put("Alarmlar", "Get-AppxPackage *alarms* | Remove-AppxPackage");
        windowsAppsMap.put("Get Started", "Get-AppxPackage *getstarted* | Remove-AppxPackage");
        windowsAppsMap.put("Bing Finance", "Get-AppxPackage *bingfinance* | Remove-AppxPackage");
        windowsAppsMap.put("Bing", "Get-AppxPackage *bing* | Remove-AppxPackage");
        windowsAppsMap.put("Feedback", "Get-AppxPackage *feedback* | Remove-AppxPackage");
        windowsAppsMap.put("Haritalar", "Get-AppxPackage *maps* | Remove-AppxPackage");
        windowsAppsMap.put("Calculator", "Get-AppxPackage *calculator* | Remove-AppxPackage");
        windowsAppsMap.put("Camera", "Get-AppxPackage *camera* | Remove-AppxPackage");
        windowsAppsMap.put("People", "Get-AppxPackage *people* | Remove-AppxPackage");
        windowsAppsMap.put("Messaging", "Get-AppxPackage *messaging* | Remove-AppxPackage");
        windowsAppsMap.put("Solitaire", "Get-AppxPackage *solitaire* | Remove-AppxPackage");
        windowsAppsMap.put("Wallet", "Get-AppxPackage *wallet* | Remove-AppxPackage");
        windowsAppsMap.put("Connectivity Store", "Get-AppxPackage *connectivitystore* | Remove-AppxPackage");
        windowsAppsMap.put("Zune", "Get-AppxPackage *zune* | Remove-AppxPackage");
        windowsAppsMap.put("Office Hub", "Get-AppxPackage *officehub* | Remove-AppxPackage");
        windowsAppsMap.put("OneNote", "Get-AppxPackage *onenote* | Remove-AppxPackage");
        windowsAppsMap.put("Sound Recorder", "Get-AppxPackage *soundrecorder* | Remove-AppxPackage");
        windowsAppsMap.put("Skype", "Get-AppxPackage *skypeapp* | Remove-AppxPackage");
        windowsAppsMap.put("Sway", "Get-AppxPackage *sway* | Remove-AppxPackage");
        windowsAppsMap.put("Communications Apps", "Get-AppxPackage *communicationsapps* | Remove-AppxPackage");
        windowsAppsMap.put("Comms Phone", "Get-AppxPackage *commsphone* | Remove-AppxPackage");
        windowsAppsMap.put("Windows Phone", "Get-AppxPackage *windowsphone* | Remove-AppxPackage");
        windowsAppsMap.put("App Connector", "Get-AppxPackage *appconnector* | Remove-AppxPackage");
        windowsAppsMap.put("App Installer", "Get-AppxPackage *appinstaller* | Remove-AppxPackage");
        windowsAppsMap.put("OneConnect", "Get-AppxPackage *oneconnect* | Remove-AppxPackage");
        windowsAppsMap.put("Holographic", "Get-AppxPackage *holographic* | Remove-AppxPackage");
        windowsAppsMap.put("Xbox", "Get-AppxPackage *xbox* | Remove-AppxPackage");
        windowsAppsMap.put("Sticky Notes", "Get-AppxPackage *sticky* | Remove-AppxPackage");

        // Alfabetik sıralama: case-insensitive
        List<String> keys = new ArrayList<>(windowsAppsMap.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Map<String, JCheckBox> checkBoxMap = new LinkedHashMap<>();
        for (String key : keys) {
            JCheckBox cb = new JCheckBox(key);
            cb.setSelected(false);
            checkBoxMap.put(key, cb);
            panel.add(cb);
        }
        panel.revalidate();

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(320, 400));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        int result = JOptionPane.showConfirmDialog(
                this,
                scrollPane,
                "Kalmasını İstediğiniz Windows Uygulamalarını Seçin\n(Not Seçilmeyenler Kaldırılacaktır)",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            new Thread(() -> {
                for (Map.Entry<String, String> entry : windowsAppsMap.entrySet()) {
                    String appName = entry.getKey();
                    String command = entry.getValue();
                    JCheckBox cb = checkBoxMap.get(appName);
                    if (cb != null && !cb.isSelected()) {
                        runPowerShell(command);
                    }
                }
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Seçilmeyen Windows uygulamaları kaldırıldı.");
                });
            }).start();
        }
    }

    private void runPowerShell(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder("powershell.exe", "-Command", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            Systra ui = new Systra();
            ui.setVisible(true);
        });
    }
}