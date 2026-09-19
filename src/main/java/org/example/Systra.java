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

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.*;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Systra extends JFrame {

    private JButton cleanTempButton;
    private JButton stopAppsButton;
    private JButton stopServicesButton;
    private JButton disableTransparencyButton;
    private JButton activatePowerModeButton;
    private JButton removeWindowsAppsButton;
    private JButton createRestorePointButton;
    private JButton applyPerformanceSettingsButton;

    private JPanel appsPanel;
    private JPanel servicesPanel;
    private JTextArea logArea;

    private Map<String, String> appsMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> appCheckBoxes = new LinkedHashMap<>();

    private Map<String, String> servicesMap = new LinkedHashMap<>();
    private Map<String, JCheckBox> recommendedServiceCheckBoxes = new LinkedHashMap<>();

    private final String[] recommendedServices = {
            "SCardSvr", "ScDeviceEnum", "SCPolicySvc", "XboxGipSvc", "XboxNetApiSvc",
            "XblAuthManager", "XblGameSave", "WpcMonSvc", "WSearch", "WPDBusEnum",
            "WMPNetworkSvc", "WerSvc", "wercplsupport", "WdiSystemHost", "upnphost",
            "UmRdpService", "TroubleshootingSvc", "tzautoupdate", "PcaSvc", "lfsvc",
            "DusmSvc", "DiagTrack", "SSDPSRV", "WalletService", "RetailDemo",
            "PhoneSvc", "dmwappushservice", "Fax", "RemoteRegistry", "SharedAccess", "MapsBroker"
    };

    private final Collator collator = Collator.getInstance(new Locale("tr", "TR"));
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    // Stil Tanımlamaları
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 13);
    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 14);
    private final Font listFont = new Font("Segoe UI", Font.PLAIN, 13);
    private final Font smallBtnFont = new Font("Segoe UI", Font.BOLD, 11);

    private final Color accentColor = new Color(64, 158, 255);
    private final Color buttonBgColor = new Color(42, 50, 68);
    private final Color buttonTextColor = new Color(240, 243, 246);

    public Systra() {
        setTitle("Systra - Professional System Optimizer");
        setSize(1100, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        collator.setStrength(Collator.PRIMARY);

        loadApps();
        loadServices();

        buildAppsPanel();
        buildServicesPanel();

        cleanTempButton = createStyledButton("Geçici Dosyaları Temizle");
        stopAppsButton = createStyledButton("Arka Plan Uygulamalarını Kapat");
        stopServicesButton = createStyledButton("Hizmetleri Durdur");
        disableTransparencyButton = createStyledButton("Saydamlığı Kapat");
        activatePowerModeButton = createStyledButton("Nihai Performans Modu");
        removeWindowsAppsButton = createStyledButton("Windows Uygulamalarını Kaldır");
        createRestorePointButton = createStyledButton("Geri Yükleme Noktası");
        applyPerformanceSettingsButton = createStyledButton("En İyi Performans Ayarları");

        buildLayout();
        wireActions();

        log("Systra başlatıldı. Tüm koruma ve optimizasyon seçenekleri hazır.");
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(buttonFont);
        btn.setForeground(buttonTextColor);
        btn.setBackground(buttonBgColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 45));
        btn.putClientProperty("JButton.buttonType", "roundRect");
        return btn;
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            if (logArea != null) {
                String time = timeFormat.format(new Date());
                logArea.append("[" + time + "] " + message + "\n");
                logArea.setCaretPosition(logArea.getDocument().getLength());
            }
        });
    }

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

    private void buildAppsPanel() {
        JPanel container = new JPanel(new BorderLayout());

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(60, 68, 85)),
                "Açık kalmasını istediğiniz uygulamaları seçin",
                TitledBorder.LEFT, TitledBorder.TOP, titleFont, accentColor);
        container.setBorder(border);

        JPanel ctrlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        ctrlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnSelectAll = new JButton("Tümünü Seç");
        JButton btnDeselectAll = new JButton("Tümünü Kaldır");

        btnSelectAll.setFont(smallBtnFont);
        btnDeselectAll.setFont(smallBtnFont);
        btnSelectAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeselectAll.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSelectAll.addActionListener(e -> appCheckBoxes.values().forEach(cb -> cb.setSelected(true)));
        btnDeselectAll.addActionListener(e -> appCheckBoxes.values().forEach(cb -> cb.setSelected(false)));

        ctrlPanel.add(btnSelectAll);
        ctrlPanel.add(btnDeselectAll);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(ctrlPanel);
        listPanel.add(Box.createVerticalStrut(5));

        List<String> sortedApps = new ArrayList<>(appsMap.keySet());
        sortedApps.sort(collator);
        for (String appName : sortedApps) {
            JCheckBox cb = new JCheckBox(appName);
            cb.setFont(listFont);
            cb.setSelected(false); // Varsayılan olarak SEÇİLSİZ (Boş)
            cb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);
            appCheckBoxes.put(appName, cb);
            listPanel.add(cb);
        }

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.add(listPanel, BorderLayout.NORTH);

        container.add(topWrapper, BorderLayout.CENTER);
        appsPanel = container;
    }

    private void buildServicesPanel() {
        JPanel container = new JPanel(new BorderLayout());

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(60, 68, 85)),
                "Çalışmaya Devam Edecek Hizmetleri Seçin",
                TitledBorder.LEFT, TitledBorder.TOP, titleFont, accentColor);
        container.setBorder(border);

        JPanel ctrlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        ctrlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnSelectAll = new JButton("Tümünü Seç");
        JButton btnDeselectAll = new JButton("Tümünü Kaldır");

        btnSelectAll.setFont(smallBtnFont);
        btnDeselectAll.setFont(smallBtnFont);
        btnSelectAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeselectAll.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSelectAll.addActionListener(e -> recommendedServiceCheckBoxes.values().forEach(cb -> cb.setSelected(true)));
        btnDeselectAll.addActionListener(e -> recommendedServiceCheckBoxes.values().forEach(cb -> cb.setSelected(false)));

        ctrlPanel.add(btnSelectAll);
        ctrlPanel.add(btnDeselectAll);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(ctrlPanel);
        listPanel.add(Box.createVerticalStrut(5));

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
            cb.setFont(listFont);
            cb.setSelected(false); // Varsayılan olarak SEÇİLSİZ (Boş)
            cb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);
            recommendedServiceCheckBoxes.put(entry.getValue(), cb);
            listPanel.add(cb);
        }

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.add(listPanel, BorderLayout.NORTH);

        container.add(topWrapper, BorderLayout.CENTER);
        servicesPanel = container;
    }

    private void buildLayout() {
        setLayout(new BorderLayout(12, 12));

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 6, 12));

        buttonsPanel.add(cleanTempButton);
        buttonsPanel.add(stopAppsButton);
        buttonsPanel.add(stopServicesButton);
        buttonsPanel.add(disableTransparencyButton);
        buttonsPanel.add(activatePowerModeButton);
        buttonsPanel.add(removeWindowsAppsButton);
        buttonsPanel.add(createRestorePointButton);
        buttonsPanel.add(applyPerformanceSettingsButton);

        add(buttonsPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        JScrollPane appsScroll = new JScrollPane(appsPanel);
        JScrollPane servicesScroll = new JScrollPane(servicesPanel);
        appsScroll.getVerticalScrollBar().setUnitIncrement(12);
        servicesScroll.getVerticalScrollBar().setUnitIncrement(12);

        centerPanel.add(appsScroll);
        centerPanel.add(servicesScroll);

        add(centerPanel, BorderLayout.CENTER);

        logArea = new JTextArea(8, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        logArea.setBackground(new Color(18, 22, 28));
        logArea.setForeground(new Color(0, 230, 118));

        JScrollPane logScroll = new JScrollPane(logArea);
        TitledBorder logBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(60, 68, 85)),
                "İşlem Günlüğü (Console Log)",
                TitledBorder.LEFT, TitledBorder.TOP, titleFont, accentColor);
        logScroll.setBorder(logBorder);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(6, 12, 12, 12));
        bottomPanel.add(logScroll, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void wireActions() {
        cleanTempButton.addActionListener(e -> {
            log("Gelişmiş temp temizliği başlatılıyor...");
            new Thread(() -> {
                TempCleaner cleaner = new TempCleaner();
                cleaner.cleanTempFolders();
                log("✓ Temp ve önbellek klasörleri temizlendi.");
            }).start();
        });

        stopAppsButton.addActionListener(e -> {
            log("Arka plan uygulamaları durduruluyor...");
            new Thread(() -> {
                AppManager appManager = new AppManager();
                for (Map.Entry<String, JCheckBox> entry : appCheckBoxes.entrySet()) {
                    if (!entry.getValue().isSelected()) {
                        String processName = appsMap.get(entry.getKey());
                        appManager.stopApp(processName);
                    }
                }
                log("✓ Seçilenler dışındaki arka plan uygulamaları kapatıldı.");
            }).start();
        });

        stopServicesButton.addActionListener(e -> {
            log("Arka plan hizmetleri optimize ediliyor...");
            new Thread(() -> {
                WindowsServicesManager serviceManager = new WindowsServicesManager();
                for (Map.Entry<String, JCheckBox> entry : recommendedServiceCheckBoxes.entrySet()) {
                    if (!entry.getValue().isSelected()) {
                        serviceManager.disableService(entry.getKey());
                    }
                }
                log("✓ Seçilmeyen arka plan hizmetleri durduruldu.");
            }).start();
        });

        disableTransparencyButton.addActionListener(e -> {
            log("Saydamlık efektleri kapatılıyor...");
            new Thread(() -> {
                try {
                    String command = "reg add \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize\" " +
                            "/v EnableTransparency /t REG_DWORD /d 0 /f";
                    Process process = Runtime.getRuntime().exec(command);
                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        log("✓ Saydamlık efektleri başarıyla kapatıldı.");
                    } else {
                        log("✗ Saydamlık kapatma işlemi başarısız oldu.");
                    }
                } catch (Exception ex) {
                    log("✗ Hata oluştu: " + ex.getMessage());
                }
            }).start();
        });

        activatePowerModeButton.addActionListener(e -> {
            log("Nihai Performans Modu etkinleştiriliyor...");
            new Thread(() -> {
                PowerModeActivator.main(new String[]{});
                log("✓ Nihai Performans Modu uygulandı.");
            }).start();
        });

        removeWindowsAppsButton.addActionListener(e -> showWindowsAppsSelectionDialog());

        createRestorePointButton.addActionListener(e -> {
            String description = JOptionPane.showInputDialog(this, "Geri yükleme noktası açıklaması:");
            if (description != null && !description.trim().isEmpty()) {
                log("Geri yükleme noktası oluşturuluyor: " + description);
                new Thread(() -> {
                    RestorePointCreator.createRestorePoint(description);
                    log("✓ Sistem geri yükleme noktası oluşturuldu.");
                }).start();
            } else {
                log("⚠️ Geri yükleme noktası oluşturma iptal edildi.");
            }
        });

        applyPerformanceSettingsButton.addActionListener(e -> {
            log("En iyi performans görsel ayarları uygulanıyor...");
            new Thread(() -> {
                PerformanceSettings.applyBestPerformance();
                log("✓ Görsel efektler en iyi performansa ayarlandı.");
            }).start();
        });
    }

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

        List<String> keys = new ArrayList<>(windowsAppsMap.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Map<String, JCheckBox> checkBoxMap = new LinkedHashMap<>();
        for (String key : keys) {
            JCheckBox cb = new JCheckBox(key);
            cb.setFont(listFont);
            cb.setSelected(false);
            checkBoxMap.put(key, cb);
            panel.add(cb);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(350, 420));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        int result = JOptionPane.showConfirmDialog(
                this,
                scrollPane,
                "Kalmasını İstediğiniz Windows Uygulamalarını Seçin (Seçilmeyenler Kaldırılır)",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            log("Gömülü Windows uygulamaları temizleniyor...");
            new Thread(() -> {
                for (Map.Entry<String, String> entry : windowsAppsMap.entrySet()) {
                    String appName = entry.getKey();
                    String command = entry.getValue();
                    JCheckBox cb = checkBoxMap.get(appName);
                    if (cb != null && !cb.isSelected()) {
                        log("Kaldırılıyor: " + appName);
                        runPowerShell(command);
                    }
                }
                log("✓ Seçilen Windows uygulamaları temizlendi.");
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
                if (!line.trim().isEmpty()) {
                    log("PS: " + line);
                }
            }
            process.waitFor();
        } catch (Exception e) {
            log("✗ PowerShell hatası: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
        }
        SwingUtilities.invokeLater(() -> {
            Systra ui = new Systra();
            ui.setVisible(true);
        });
    }
}