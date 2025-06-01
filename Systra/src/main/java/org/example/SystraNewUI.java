package org.example;

import javax.swing.*;
import javax.swing.ScrollPaneConstants;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;
import java.util.List;

public class SystraNewUI extends JFrame {

    // Butonlar
    private JButton btnCleanTemp;
    private JButton btnStopApps;
    private JButton btnUpdateServices;
    private JButton btnDisableTransparency;
    private JButton btnActivatePower;
    private JButton btnRemoveWindowsApps;

    // Tab'lerde gösterilecek paneller
    private JPanel appsPanel;
    private JPanel servicesPanel;

    // Veriler
    private Map<String, String> appsMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> appCheckBoxes = new LinkedHashMap<>();

    private Map<String, String> servicesMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> serviceCheckBoxes = new LinkedHashMap<>();

    // İşlem yapılacak hizmet kodları
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

    // Türkçe alfabe sıralaması için Collator
    private final Collator collator = Collator.getInstance(new Locale("tr", "TR"));

    public SystraNewUI() {
        super("Systra - Yeni Arayüz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        collator.setStrength(Collator.PRIMARY);

        loadApps();
        loadServices();

        buildAppsPanel();
        buildServicesPanel();

        // JTabbedPane kullanarak iki ayrı sekmeye ayırıyoruz.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Uygulamalar", new JScrollPane(appsPanel));
        tabbedPane.addTab("Hizmetler", new JScrollPane(servicesPanel));

        // Alt kısımda kontrol butonlarının bulunduğu panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCleanTemp = new JButton("Geçici Dosyaları Temizle");
        btnStopApps = new JButton("Arka Planda Çalışan Uygulamaları Kapat");
        btnUpdateServices = new JButton("Hizmetleri Güncelle");
        btnDisableTransparency = new JButton("Saydamlığı Kapat");
        btnActivatePower = new JButton("Nihai Performans Modunu Etkinleştir");
        btnRemoveWindowsApps = new JButton("Windows Uygulamalarını Kaldır");

        buttonPanel.add(btnCleanTemp);
        buttonPanel.add(btnStopApps);
        buttonPanel.add(btnUpdateServices);
        buttonPanel.add(btnDisableTransparency);
        buttonPanel.add(btnActivatePower);
        buttonPanel.add(btnRemoveWindowsApps);

        // Layout: TabbedPane CENTER, Button Panel SOUTH
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        wireActions();
    }

    // Uygulama verilerini yükler
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

    // Hizmet verilerini yükler
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

    // Uygulama panelini alfabetik sıraya göre oluşturur
    private void buildAppsPanel() {
        appsPanel = new JPanel();
        appsPanel.setLayout(new BoxLayout(appsPanel, BoxLayout.Y_AXIS));
        appsPanel.setBorder(BorderFactory.createTitledBorder("Açık kalmasını istediğiniz uygulamaları seçin"));

        List<String> sortedApps = new ArrayList<>(appsMap.keySet());
        sortedApps.sort(collator);
        for (String app : sortedApps) {
            JCheckBox cb = new JCheckBox(app);
            appCheckBoxes.put(app, cb);
            appsPanel.add(cb);
        }
    }

    // Hizmet panelini, recommendedServices dizisindeki kodlara göre,
    // aradaki eşleşmeden kullanıcı dostu isimleri bularak alfabetik sıraya göre oluşturur.
    private void buildServicesPanel() {
        servicesPanel = new JPanel();
        servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));
        servicesPanel.setBorder(BorderFactory.createTitledBorder("Çalışmaya Devam Edecek Hizmetleri Seçin"));

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
            serviceCheckBoxes.put(entry.getValue(), cb);
            servicesPanel.add(cb);
        }
    }

    // Aksiyonları (ActionListener'ları) bağlar
    private void wireActions() {
        btnCleanTemp.addActionListener(e -> {
            new TempCleaner().cleanTempFolders();
            JOptionPane.showMessageDialog(this, "Temp klasörleri temizlendi.");
        });

        // Uygulama bölümünde: yalnızca işaretli olanlar kalır; diğerleri kapatılır.
        btnStopApps.addActionListener(e -> {
            AppManager am = new AppManager();
            for (Map.Entry<String, JCheckBox> entry : appCheckBoxes.entrySet()) {
                if (!entry.getValue().isSelected()) {
                    am.stopApp(appsMap.get(entry.getKey()));
                }
            }
            JOptionPane.showMessageDialog(this, "Seçilen uygulamalar dışındaki uygulamaların çalışması durduruldu.");
        });

        // Hizmetlerde: sadece işaretli olanlar çalışmaya devam etsin,
        // diğerlerinin disable edilmesini sağlar.
        btnUpdateServices.addActionListener(e -> {
            WindowsServicesManager wsm = new WindowsServicesManager();
            for (Map.Entry<String, JCheckBox> entry : serviceCheckBoxes.entrySet()) {
                if (!entry.getValue().isSelected()) {
                    wsm.disableService(entry.getKey());
                }
            }
            JOptionPane.showMessageDialog(this,
                    "İşaretli olmayan hizmetler durduruldu; yalnızca seçili hizmetler çalışıyor.");
        });

        btnDisableTransparency.addActionListener(e -> {
            try {
                String cmd = "reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize\" " +
                        "/v EnableTransparency /t REG_DWORD /d 0 /f";
                Process p = Runtime.getRuntime().exec(cmd);
                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    JOptionPane.showMessageDialog(this, "Saydamlık efektleri başarıyla kapatıldı.");
                } else {
                    JOptionPane.showMessageDialog(this, "Saydamlık kapatma işlemi başarısız oldu.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Bir hata oluştu: " + ex.getMessage());
            }
        });

        btnActivatePower.addActionListener(e -> {
            new Thread(() -> PowerModeActivator.main(new String[]{})).start();
            JOptionPane.showMessageDialog(this, "Nihai Performans modu etkinleştirme işlemi başlatıldı.");
        });

        btnRemoveWindowsApps.addActionListener(e -> showWindowsAppsSelectionDialog());
    }

    // Windows Uygulamaları için seçim diyalogu
    private void showWindowsAppsSelectionDialog() {
        Map<String, String> winApps = new LinkedHashMap<>();
        winApps.put("Cortana", "Get-AppxPackage -allusers Microsoft.549981C3F5F10 | Remove-AppxPackage");
        winApps.put("3D Builder", "Get-AppxPackage *3dbuilder* | Remove-AppxPackage");
        winApps.put("3D Uygulamalar", "Get-AppxPackage *3d* | Remove-AppxPackage");
        winApps.put("Alarmlar", "Get-AppxPackage *alarms* | Remove-AppxPackage");
        winApps.put("Get Started", "Get-AppxPackage *getstarted* | Remove-AppxPackage");
        winApps.put("Bing Finance", "Get-AppxPackage *bingfinance* | Remove-AppxPackage");
        winApps.put("Bing", "Get-AppxPackage *bing* | Remove-AppxPackage");
        winApps.put("Feedback", "Get-AppxPackage *feedback* | Remove-AppxPackage");
        winApps.put("Haritalar", "Get-AppxPackage *maps* | Remove-AppxPackage");
        winApps.put("Calculator", "Get-AppxPackage *calculator* | Remove-AppxPackage");
        winApps.put("Camera", "Get-AppxPackage *camera* | Remove-AppxPackage");
        winApps.put("People", "Get-AppxPackage *people* | Remove-AppxPackage");
        winApps.put("Messaging", "Get-AppxPackage *messaging* | Remove-AppxPackage");
        winApps.put("Solitaire", "Get-AppxPackage *solitaire* | Remove-AppxPackage");
        winApps.put("Wallet", "Get-AppxPackage *wallet* | Remove-AppxPackage");
        winApps.put("Connectivity Store", "Get-AppxPackage *connectivitystore* | Remove-AppxPackage");
        winApps.put("Zune", "Get-AppxPackage *zune* | Remove-AppxPackage");
        winApps.put("Office Hub", "Get-AppxPackage *officehub* | Remove-AppxPackage");
        winApps.put("OneNote", "Get-AppxPackage *onenote* | Remove-AppxPackage");
        winApps.put("Sound Recorder", "Get-AppxPackage *soundrecorder* | Remove-AppxPackage");
        winApps.put("Skype", "Get-AppxPackage *skypeapp* | Remove-AppxPackage");
        winApps.put("Sway", "Get-AppxPackage *sway* | Remove-AppxPackage");
        winApps.put("Communications Apps", "Get-AppxPackage *communicationsapps* | Remove-AppxPackage");
        winApps.put("Comms Phone", "Get-AppxPackage *commsphone* | Remove-AppxPackage");
        winApps.put("Windows Phone", "Get-AppxPackage *windowsphone* | Remove-AppxPackage");
        winApps.put("App Connector", "Get-AppxPackage *appconnector* | Remove-AppxPackage");
        winApps.put("App Installer", "Get-AppxPackage *appinstaller* | Remove-AppxPackage");
        winApps.put("OneConnect", "Get-AppxPackage *oneconnect* | Remove-AppxPackage");
        winApps.put("Holographic", "Get-AppxPackage *holographic* | Remove-AppxPackage");
        winApps.put("Xbox", "Get-AppxPackage *xbox* | Remove-AppxPackage");
        winApps.put("Sticky Notes", "Get-AppxPackage *sticky* | Remove-AppxPackage");

        // Filan sıralama: alfabetik, case-insensitive
        List<String> sortedKeys = new ArrayList<>(winApps.keySet());
        Collections.sort(sortedKeys, String.CASE_INSENSITIVE_ORDER);

        JPanel winPanel = new JPanel();
        winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
        Map<String, JCheckBox> winCheckBoxes = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            JCheckBox cb = new JCheckBox(key);
            cb.setSelected(false);
            winCheckBoxes.put(key, cb);
            winPanel.add(cb);
        }
        winPanel.revalidate();

        JScrollPane winScroll = new JScrollPane(winPanel);
        winScroll.setPreferredSize(new Dimension(320, 400));
        winScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        int res = JOptionPane.showConfirmDialog(
                this,
                winScroll,
                "Kalmasını İstediğiniz Windows Uygulamalarını Seçin\n(Not Seçilmeyenler Kaldırılacaktır)",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (res == JOptionPane.OK_OPTION) {
            new Thread(() -> {
                for (Map.Entry<String, String> entry : winApps.entrySet()) {
                    String appName = entry.getKey();
                    String command = entry.getValue();
                    JCheckBox cb = winCheckBoxes.get(appName);
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
            Process p = builder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (br.readLine() != null) {
                // Çıktı loglanıyor (isteğe bağlı)
            }
            p.waitFor();
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
        SwingUtilities.invokeLater(() -> new SystraNewUI().setVisible(true));
    }
}