package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{
  private Current current;

  @Getter
  @Setter
  public class Current {

    @JsonProperty("last_updated_epoch")
    private long lastUpdatedEpoch;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("temp_c")
    private double tempC;

    @JsonProperty("temp_f")
    private double tempF;

    @JsonProperty("is_day")
    private int isDay;

    @JsonProperty("humidity")
    private int humidity;

  }

}



