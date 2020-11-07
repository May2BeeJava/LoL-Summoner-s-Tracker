package me.sk;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Players extends JPanel {
    public JLabel linia = new JLabel("test", SwingConstants.CENTER);
    private JLabel avatar = new JLabel("");
    private Font font = new Font("Roboto", Font.PLAIN, 16);
    public JLabel summ1Time = new JLabel("", SwingConstants.CENTER), summ2Time = new JLabel("", SwingConstants.CENTER);
    public JButton summoner1 = new JButton(), summoner2 = new JButton();
    private GridLayout gridLayout = new GridLayout(3, 1);
    private GridLayout smallGridLayour = new GridLayout(1, 2);
    private JPanel summki = new JPanel();
    private JPanel timery = new JPanel();
    private SummonerSpells summonerSpells = SummonerSpells.get();
    private Player player;
    public double summonerSpellCD;


    public Players(Player player) throws IOException {
        this.player = player;
        setLayout(gridLayout);
        init();
    }

    public void init() throws IOException {
        setBackground(new Color(107, 106, 104));
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(linia);
        linia.setFont(font);
        linia.setText(player.getChampion().getName());

        add(summki);
        summki.setLayout(smallGridLayour);

        add(timery);
        timery.setLayout(smallGridLayour);

        summki.add(summoner1);
        summoner1.setActionCommand("Summoner1");
        String tmp = player.getSummonerSpellD().getImage().getURL();
        URL url = new URL(tmp);
        BufferedImage temp = ImageIO.read(url);
        temp = (BufferedImage) getScaledImage(temp);
        summoner1.setIcon(new ImageIcon(temp));
        summonerSpellCD = player.getSummonerSpellD().getCooldowns().get(0);
        summoner1.addActionListener(new ButtonActions(summoner1, summoner2, this, player));

        summki.add(summoner2);
        summoner2.setActionCommand("Summoner2");
        tmp = player.getSummonerSpellF().getImage().getURL();
        url = new URL(tmp);
        temp = ImageIO.read(url);
        temp = (BufferedImage) getScaledImage(temp);
        summoner2.setIcon(new ImageIcon(temp));
        summonerSpellCD = player.getSummonerSpellD().getCooldowns().get(0);

        summoner2.addActionListener(new ButtonActions(summoner1, summoner2, this, player));

        timery.add(summ1Time);
        summ1Time.setFont(font);

        timery.add(summ2Time);
        summ2Time.setFont(font);
    }

    private Image getScaledImage(Image srcImg){
        BufferedImage resizedImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, 32, 32, null);
        g2.dispose();

        return resizedImg;
    }

}
