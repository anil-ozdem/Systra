package org.example;

public class Main {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            Systra systra = new Systra();
            systra.setVisible(true);
        });
    }
}
