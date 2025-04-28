package school.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Json {
    @JsonProperty("process_data")
    private Process[] processesData;

    @JsonProperty("datetime")
    private String datetime;

    public Process[] getProcessesData() {
        return processesData;
    }

    public String getDatetime() {
        return datetime;
    }
}
