package ru.netology.stats;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    void setUp() {
        game = new Game();
        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 8);
        player3 = new Player(3, "Charlie", 10);
    }

    @Test
    void shouldRegisterPlayer() {
        game.register(player1);
        assertEquals(1, game.getPlayers().size());
        assertTrue(game.getPlayers().contains(player1));
    }

    @Test
    void shouldNotRegisterDuplicatePlayer() {
        game.register(player1);
        game.register(player1);
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    void shouldReturn1WhenFirstPlayerWins() {
        game.register(player1);
        game.register(player2);

        int result = game.round("Alice", "Bob");
        assertEquals(1, result);
    }

    @Test
    void shouldReturn2WhenSecondPlayerWins() {
        game.register(player1);
        game.register(player2);

        int result = game.round("Bob", "Alice");
        assertEquals(2, result);
    }

    @Test
    void shouldReturn0WhenDraw() {
        game.register(player1);
        game.register(player3);

        int result = game.round("Alice", "Charlie");
        assertEquals(0, result);
    }

    @Test
    void shouldThrowExceptionWhenFirstPlayerNotRegistered() {
        game.register(player2);

        NotRegisteredException exception = assertThrows(NotRegisteredException.class,
                () -> game.round("Alice", "Bob"));
        assertEquals("Player Alice is not registered", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSecondPlayerNotRegistered() {
        game.register(player1);

        NotRegisteredException exception = assertThrows(NotRegisteredException.class,
                () -> game.round("Alice", "Bob"));
        assertEquals("Player Bob is not registered", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBothPlayersNotRegistered() {
        NotRegisteredException exception = assertThrows(NotRegisteredException.class,
                () -> game.round("Alice", "Bob"));
        assertEquals("Player Alice is not registered", exception.getMessage());
    }

    @Test
    void shouldFindPlayerByNameAfterRegistration() {
        game.register(player1);
        game.register(player2);

        assertTrue(game.getPlayers().stream().anyMatch(p -> p.getName().equals("Alice")));
        assertTrue(game.getPlayers().stream().anyMatch(p -> p.getName().equals("Bob")));
    }

    @Test
    void shouldHandleMultipleRegistrations() {
        game.register(player1);
        game.register(player2);
        game.register(player3);

        assertEquals(3, game.getPlayers().size());
    }
}