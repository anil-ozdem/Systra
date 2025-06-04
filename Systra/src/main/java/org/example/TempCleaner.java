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

import java.io.File;

public class TempCleaner {

    public void cleanTempFolders() {
        String tempEnv = System.getenv("TEMP");
        String tempFolder = "C:\\Windows\\Temp";
        String prefetchFolder = "C:\\Windows\\Prefetch";

        System.out.println("Temizleme işlemi başlatılıyor...");

        if (tempEnv != null) {
            System.out.println("%TEMP% klasörü temizleniyor: " + tempEnv);
            deleteFiles(new File(tempEnv));
        } else {
            System.out.println("%TEMP% ortam değişkeni bulunamadı.");
        }

        System.out.println("Temp klasörü temizleniyor: " + tempFolder);
        deleteFiles(new File(tempFolder));

        System.out.println("Prefetch klasörü temizleniyor: " + prefetchFolder);
        deleteFiles(new File(prefetchFolder));

        System.out.println("Tüm belirtilen klasörler temizlendi.");
    }

    private void deleteFiles(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            System.out.println("Geçersiz dizin atlanıyor: " + (dir == null ? "null" : dir.getAbsolutePath()));
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("Dosyalar listelenemedi: " + dir.getAbsolutePath());
            return;
        }

        for (File file : files) {
            try {
                if (file.isDirectory()) {
                    deleteFiles(file);
                    if (file.list().length == 0) {
                        if (!file.delete()) {
                            System.out.println("Boş dizin silinemedi: " + file.getAbsolutePath());
                        }
                    }
                } else {
                    if (!file.delete()) {
                        System.out.println("Dosya silinemedi: " + file.getAbsolutePath());
                    }
                }
            } catch (SecurityException e) {
                System.out.println("Silme izni yok: " + file.getAbsolutePath());
            } catch (Exception e) {
                System.out.println(file.getAbsolutePath() + " silinirken hata oluştu: " + e.getMessage());
            }
        }
    }
}
