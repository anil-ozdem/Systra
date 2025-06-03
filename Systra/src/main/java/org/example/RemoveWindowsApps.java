package org.example;

import java.io.*;

public class RemoveWindowsApps {

    public static void main(String[] args) {

        // Silinecek Uygulamalar
        String[] commands = {
                "Get-AppxPackage -allusers Microsoft.549981C3F5F10 | Remove-AppxPackage", // Cortana
                "Get-AppxPackage *3dbuilder* | Remove-AppxPackage",
                "Get-AppxPackage *3d* | Remove-AppxPackage",
                "Get-AppxPackage *alarms* | Remove-AppxPackage",
                "Get-AppxPackage *getstarted* | Remove-AppxPackage",
                "Get-AppxPackage *bingfinance* | Remove-AppxPackage",
                "Get-AppxPackage *bing* | Remove-AppxPackage",
                "Get-AppxPackage *feedback* | Remove-AppxPackage",
                "Get-AppxPackage *maps* | Remove-AppxPackage",
                "Get-AppxPackage *calculator* | Remove-AppxPackage",
                "Get-AppxPackage *camera* | Remove-AppxPackage",
                "Get-AppxPackage *people* | Remove-AppxPackage",
                "Get-AppxPackage *messaging* | Remove-AppxPackage",
                "Get-AppxPackage *solitaire* | Remove-AppxPackage",
                "Get-AppxPackage *wallet* | Remove-AppxPackage",
                "Get-AppxPackage *connectivitystore* | Remove-AppxPackage",
                "Get-AppxPackage *zune* | Remove-AppxPackage",
                "Get-AppxPackage *officehub* | Remove-AppxPackage",
                "Get-AppxPackage *onenote* | Remove-AppxPackage",
                "Get-AppxPackage *soundrecorder* | Remove-AppxPackage",
                "Get-AppxPackage *skypeapp* | Remove-AppxPackage",
                "Get-AppxPackage *sway* | Remove-AppxPackage",
                "Get-AppxPackage *communicationsapps* | Remove-AppxPackage",
                "Get-AppxPackage *commsphone* | Remove-AppxPackage",
                "Get-AppxPackage *windowsphone* | Remove-AppxPackage",
                "Get-AppxPackage *appconnector* | Remove-AppxPackage",
                "Get-AppxPackage *appinstaller* | Remove-AppxPackage",
                "Get-AppxPackage *oneconnect* | Remove-AppxPackage",
                "Get-AppxPackage *holographic* | Remove-AppxPackage",
                "Get-AppxPackage *xbox* | Remove-AppxPackage",
                "Get-AppxPackage *sticky* | Remove-AppxPackage"
        };

        for (String command : commands) {
            runPowerShell(command);
        }
    }

    // Powershell ile silinecek uygulamaları getirip çalıştırır
    public static void runPowerShell(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder("powershell.exe", "-Command", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
