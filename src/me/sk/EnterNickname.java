package me.sk;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterNickname extends JFrame implements ActionListener {
    private JTextField nickname = new JTextField(14);
    private Font font = new Font("Roboto", Font.PLAIN, 24);
    private Font font2 = new Font("Roboto", Font.PLAIN, 16);
    private JButton goNext = new JButton("Potwierdź");
    private Region[] serwery = {Region.BRAZIL, Region.EUROPE_NORTH_EAST, Region.EUROPE_WEST, Region.JAPAN, Region.KOREA, Region.LATIN_AMERICA_NORTH, Region.LATIN_AMERICA_SOUTH, Region.NORTH_AMERICA, Region.OCEANIA, Region.RUSSIA, Region.TURKEY};
    private JList jList = new JList(serwery);

    public EnterNickname() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 400, 300);
        setLocationRelativeTo(null);
        setTitle("LoL Summoner Spells Tracker");
        setLayout(new FlowLayout());

        Orianna.setRiotAPIKey(new RiotAPIKey().getRiotAPIKey());

        init();
    }

    private void init() {
        add(nickname);
        nickname.setFont(font);

        add(jList);
        jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jList.setFont(font2);

        add(goNext);
        goNext.setFont(font);
        goNext.addActionListener(this);

    }

    private void goNext(String nickname) {
        ThreadApp threadApp = new ThreadApp(nickname);
        threadApp.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!nickname.getText().isEmpty()) {
            if (!jList.isSelectionEmpty()) {
                if (e.getSource().equals(goNext)) {
                    Orianna.setDefaultRegion((Region) jList.getSelectedValue());
                    if (Summoner.named(nickname.getText()).get().getCurrentMatch().exists()) {
                        goNext(nickname.getText());
                        setVisible(false);
                    }
                }
            }
        }
    }
}
