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
