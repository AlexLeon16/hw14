package ru.netology.stats;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    public void register(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null || player2 == null) {
            String unregisteredPlayer = player1 == null ? playerName1 : playerName2;
            throw new NotRegisteredException("Player " + unregisteredPlayer + " is not registered");
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }

    private Player findByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
