package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class MainApp extends Application {

    private WindowsServicesManager serviceManager = new WindowsServicesManager();
    private TempCleaner tempCleaner = new TempCleaner();
    private AppManager appManager = new AppManager();

    private List<String> recommendedServices = Arrays.asList(
            "SCardSvr", "ScDeviceEnum", "XboxGipSvc", "XboxNetApiSvc", "XblAuthManager",
            "XblGameSave", "WpcMonSvc", "WSearch", "WPDBusEnum", "WMPNetworkSvc", "WerSvc",
            "wercplsupport", "WdiSystemHost", "upnphost", "UmRdpService", "TroubleshootingSvc",
            "tzautoupdate", "PcaSvc", "lfsvc", "DusmSvc", "DiagTrack", "SSDPSRV", "WalletService",
            "RetailDemo", "PhoneSvc", "dmwappushservice", "Fax", "RemoteRegistry", "SharedAccess",
            "MapsBroker"
    );

    private List<String> commonApps = Arrays.asList(
            "notepad.exe", "chrome.exe", "firefox.exe", "spotify.exe", "discord.exe"
    );

    @Override
    public void start(Stage stage) {

        // Geçici Dosyaları Temizleme Butonu
        Button btnCleanTemp = new Button("Geçici Dosyaları Temizle");
        btnCleanTemp.setOnAction(e -> {
            tempCleaner.cleanTempFolders();
            showAlert(Alert.AlertType.INFORMATION, "Bilgi", "Geçici dosyalar temizlendi.");
        });

        // Uygulamalar (arka plan) checkbox listesi ve kapatma butonu
        VBox appsBox = new VBox(5);
        appsBox.setPadding(new Insets(5));
        Label appsLabel = new Label("Arka planda çalışan uygulamalar:");
        CheckBox[] appCheckBoxes = new CheckBox[commonApps.size()];
        for (int i = 0; i < commonApps.size(); i++) {
            appCheckBoxes[i] = new CheckBox(commonApps.get(i));
            appsBox.getChildren().add(appCheckBoxes[i]);
        }
        Button btnStopApps = new Button("Seçilen Uygulamaları Kapat");
        btnStopApps.setOnAction(e -> {
            boolean anySelected = false;
            for (CheckBox cb : appCheckBoxes) {
                if (cb.isSelected()) {
                    appManager.stopApp(cb.getText());
                    anySelected = true;
                }
            }
            if (anySelected) {
                showAlert(Alert.AlertType.INFORMATION, "Bilgi", "Seçilen uygulamalar kapatıldı.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Uyarı", "Lütfen en az bir uygulama seçin.");
            }
        });

        VBox appsSection = new VBox(10, appsLabel, appsBox, btnStopApps);
        appsSection.setPadding(new Insets(10));
        appsSection.setStyle("-fx-border-color: gray; -fx-border-radius: 5; -fx-border-width: 1;");

        // Hizmetler checkbox listesi ve kapatma butonu
        VBox servicesBox = new VBox(5);
        servicesBox.setPadding(new Insets(5));
        Label servicesLabel = new Label("Tavsiye Edilen Gereksiz Hizmetler:");
        CheckBox[] serviceCheckBoxes = new CheckBox[recommendedServices.size()];
        for (int i = 0; i < recommendedServices.size(); i++) {
            serviceCheckBoxes[i] = new CheckBox(recommendedServices.get(i));
            servicesBox.getChildren().add(serviceCheckBoxes[i]);
        }
        Button btnDisableServices = new Button("Seçilen Hizmetleri Kapat");
        btnDisableServices.setOnAction(e -> {
            boolean anySelected = false;
            for (CheckBox cb : serviceCheckBoxes) {
                if (cb.isSelected()) {
                    serviceManager.disableService(cb.getText());
                    anySelected = true;
                }
            }
            if (anySelected) {
                showAlert(Alert.AlertType.INFORMATION, "Bilgi", "Seçilen hizmetler devre dışı bırakıldı.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Uyarı", "Lütfen en az bir hizmet seçin.");
            }
        });

        VBox servicesSection = new VBox(10, servicesLabel, new ScrollPane(servicesBox), btnDisableServices);
        servicesSection.setPadding(new Insets(10));
        servicesSection.setStyle("-fx-border-color: gray; -fx-border-radius: 5; -fx-border-width: 1;");
        servicesSection.setPrefHeight(250);

        // Ana layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(btnCleanTemp, appsSection, servicesSection);

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Windows PC Optimizer");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch();
    }
}
