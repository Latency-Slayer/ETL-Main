package school.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Process {
    @JsonProperty("name")
    private String name;

    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("status")
    private String status;

    @JsonProperty("cpu_percent")
    private Double cpuPercent;

    @JsonProperty("memory_percent")
    private Double memoryPercent;

    @JsonProperty("memory_gb")
    private Double memoryGb;

    public String getName() {
        return name;
    }

    public Integer getPid() {
        return pid;
    }

    public String getStatus() {
        return status;
    }

    public Double getCpuPercent() {
        return this.round(cpuPercent);
    }

    public Double getMemoryPercent() {
        return this.round(memoryPercent);
    }

    public Double getMemoryGb() {
        return this.round(memoryGb);
    }


    private double round(double num) {
        return Math.round(num * 100.0) / 100.0;
    }
}
