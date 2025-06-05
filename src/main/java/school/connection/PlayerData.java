package school.connection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerData {
    private String ip;
    private int port;
    private GeoData geoData;

    @JsonCreator
    public PlayerData(JsonNode node) throws JsonProcessingException {
        if (node.isArray() && node.size() >= 3) {
            this.ip = node.get(0).asText();
            this.port = node.get(1).asInt();

            JsonNode geoNode = node.get(2);
            ObjectMapper mapper = new ObjectMapper();
            this.geoData = mapper.treeToValue(geoNode, GeoData.class);
        }
    }

    // Construtor padrão
    public PlayerData() {}

    // Getters e Setters
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public GeoData getGeoData() {
        return geoData;
    }

    public void setGeoData(GeoData geoData) {
        this.geoData = geoData;
    }
}
