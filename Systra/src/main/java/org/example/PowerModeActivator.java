package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerModeActivator {

    public static void main(String[] args) {
        try {
            // 1. Nihai performans planını ekler ( Eğer varsa tekrardan ekleme yapmaz)
            Process process1 = Runtime.getRuntime().exec("powercfg -duplicatescheme e9a42b02-d5df-448d-aa00-03f14749eb61");
            process1.waitFor();

            // 2. Tüm güç planlarını listeler
            Process process2 = Runtime.getRuntime().exec("powercfg /list");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process2.getInputStream()));

            String line;
            String ultimatePlanGUID = null;

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("ultimate performance") || line.toLowerCase().contains("nihai performans")) {
                    // Nihai performansı bulup kaydeder
                    int start = line.indexOf(':');
                    int end = line.indexOf('(', start);
                    if (start != -1 && end != -1) {
                        ultimatePlanGUID = line.substring(start + 1, end).trim();
                        break;
                    }
                }
            }

            process2.waitFor();

            // 3. Eğer bulunduysa aktifleştirir
            if (ultimatePlanGUID != null) {
                String cmd = "powercfg /setactive " + ultimatePlanGUID;
                Process process3 = Runtime.getRuntime().exec(cmd);
                process3.waitFor();
                System.out.println("Nihai Performans modu başarıyla aktif edildi.");
            } else {
                System.out.println("Nihai Performans güç planı bulunamadı.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
