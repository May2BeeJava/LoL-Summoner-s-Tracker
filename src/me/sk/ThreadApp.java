package me.sk;

import java.io.IOException;

public class ThreadApp extends Thread {
    private String nickname;

    public ThreadApp(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void run() {
        try {
            Frame frame = new Frame(nickname);
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
