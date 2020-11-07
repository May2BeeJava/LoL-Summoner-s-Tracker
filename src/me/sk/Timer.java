package me.sk;

import javax.swing.*;

public class Timer extends Thread {
    private double seconds;
    private JLabel summ2Time;
    private JButton summoner2;

    public Timer(double seconds, JLabel summ2Time, JButton summoner2) {
        this.seconds = seconds;
        this.summ2Time = summ2Time;
        this.summoner2 = summoner2;
    }

    public void run() {
        try {
            for (int i = (int) seconds; i >= 0; i--) {
                summ2Time.setText(Integer.toString(i));
                Thread.sleep(1000);
            }
            summoner2.setEnabled(true);
            summ2Time.setText("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
