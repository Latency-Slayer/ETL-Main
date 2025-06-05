package school.connection;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonMapper {
    public List<MainJson> mapearProcesso(InputStream input) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MainJson unico = objectMapper.readValue(input, MainJson.class);
        return List.of(unico);
    }
}