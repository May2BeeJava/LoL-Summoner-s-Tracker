package me.sk;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Frame extends JFrame {
    private Players gracz;
    private int x = 0, y = 0;
    private String[] linie = {"top", "jng", "mid", "adc", "supp"};
    private JPanel mainPanel = new JPanel();
    private GridLayout gridLayout = new GridLayout(1,5);
    private Side enemyTeam;
    private String summonerName;

    public Frame(String nickname) throws IOException {
        this.summonerName = nickname;
        frame();
    }

    public void frame() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 982, 320, 98);
        setAlwaysOnTop(true);
        setUndecorated(true);
        add(mainPanel);
        mainPanel.setLayout(gridLayout);

        Summoner summoner = Summoner.named(summonerName).get();

        if (summoner.getCurrentMatch().exists()) {
            for (Player player : summoner.getCurrentMatch().getParticipants()) {
                if (player.getCoreData().getSummonerName().equals(summonerName)) {
                    if (player.getTeam().getSide().equals(Side.RED))
                        this.enemyTeam = Side.BLUE;
                    else
                        this.enemyTeam = Side.RED;
                }
            }

            for (Player player : summoner.getCurrentMatch().getParticipants()) {
                if (player.getTeam().getSide().equals(enemyTeam)) {
                    mainPanel.add(gracz = new Players(player));
                }
            }
        }

    }
}
