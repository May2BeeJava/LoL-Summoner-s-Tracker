package me.sk;

public class RiotAPIKey {
    private static String riotAPIKey;

    public RiotAPIKey() {
        this.riotAPIKey = "Your Riot API Key here :)";
    }

    public static String getRiotAPIKey() {
        return riotAPIKey;
    }
}
