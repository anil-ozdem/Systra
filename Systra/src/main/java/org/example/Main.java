package org.example;

public class Main {
    public static void main(String[] args) {
        // GUI thread üzerinde başlatmak iyi bir pratik
        javax.swing.SwingUtilities.invokeLater(() -> {
            Systra systra = new Systra();
            systra.setVisible(true);
        });
    }
}
