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
