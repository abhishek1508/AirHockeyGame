package com.software.game.airhockeyandroid.Entities;

/**
 * Created by shardendu on 10/21/15.
 */
public class Player {

    private static String username;
    private static int points;
    private static int rank;
    private static int games_won;
    private static int games_lost;

    private static Player player = null;

    private Player() {

    }

    public static Player getInstance(String username, int coins, int rank, int games_won, int games_lost) {
        if (player == null) {
            player = new Player();
            player.setUsername(username);
            player.setGames_won(games_won);
            player.setGames_lost(games_lost);
            player.setPoints(coins);
            player.setRank(rank);
        }
        return player;
    }

    public static Player getInstance() {
        if (player == null)
            player = new Player();
        return player;
    }

    public static String getUsername() {
        return Player.username;
    }

    public static void setUsername(String username) {
        Player.username = username;
    }

    public static int getRank() {
        return Player.rank;
    }

    public static void setRank(int rank) {
        Player.rank = rank;
    }

    public static int getPoints() {
        return Player.points;
    }

    public static void setPoints(int points) {
        Player.points = points;
    }

    public static int getGames_won() {
        return Player.games_won;
    }

    public static void setGames_won(int games_won) {
        Player.games_won = games_won;
    }

    public static int getGames_lost() {
        return Player.games_lost;
    }

    public static void setGames_lost(int games_lost) {
        Player.games_lost = games_lost;
    }
}
