package me.sk;

import com.merakianalytics.orianna.types.core.spectator.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActions extends JFrame implements ActionListener {
    private JButton summoner1, summoner2;
    private Players players;
    private Timer timer;
    private Player player;
    private double summonerSpellsCD;


    public ButtonActions(JButton summoner1, JButton summoner2, Players players, Player player) {
        this.summoner1 = summoner1;
        this.summoner2 = summoner2;
        this.players = players;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Summoner1")) {

            players.summoner1.setEnabled(false);
            summonerSpellsCD = player.getSummonerSpellD().getCooldowns().get(0);
            isTeleport(player.getSummonerSpellD().getName());
            timer = new Timer(summonerSpellsCD, players.summ1Time, players.summoner1);
            timer.start();

        } else if (action.equals("Summoner2")) {

            players.summoner2.setEnabled(false);
            summonerSpellsCD = player.getSummonerSpellF().getCooldowns().get(0);
            isTeleport(player.getSummonerSpellF().getName());
            timer = new Timer(summonerSpellsCD, players.summ2Time, players.summoner2);
            timer.start();

        }
    }

    private void isTeleport(String summonerSpell) {
        if (summonerSpell.equals("Teleport")) {
            this.summonerSpellsCD = 420;
        }
    }
}
