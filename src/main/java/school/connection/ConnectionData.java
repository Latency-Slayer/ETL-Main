package school.connection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionData {
    @JsonProperty("quant_players")
    private int quantPlayers;

    @JsonProperty("players_data")
    private PlayerData[] playersData;

    // Getters e Setters
    public int getQuantPlayers() {
        return quantPlayers;
    }

    public void setQuantPlayers(int quantPlayers) {
        this.quantPlayers = quantPlayers;
    }

    public PlayerData[] getPlayersData() {
        return playersData;
    }

    public void setPlayersData(PlayerData[] playersData) {
        this.playersData = playersData;
    }
}