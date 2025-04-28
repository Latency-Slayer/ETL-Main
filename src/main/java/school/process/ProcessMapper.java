package school.process;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProcessMapper {
    public List<Json> mapearProcesso(InputStream input) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(input, new TypeReference<List<Json>>() {
        });
    }
}