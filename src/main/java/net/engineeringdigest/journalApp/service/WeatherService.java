package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.cache.AppCache.keys;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {
  @Value("${weather.api.key}")
  private String apiKey;

//  private static final String API ="https://api.weatherapi.com/v1/current.json?q=CITY&country=COUNTRY&key=API_KEY&aqi=yes";

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisService redisService;

  @Autowired
  private AppCache appCache;


  public WeatherResponse getWeather(String city, String country) {
    WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
    if(weatherResponse != null) {
      return weatherResponse;
    }else {
      String finalAPI = appCache.app_Cache.get(keys.WEATHER_API.toString()).replace("<city>", city)
          .replace("<country>", country).replace("<api_key>", apiKey);
      ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,
          null,
          WeatherResponse.class);

//    String requestBody= "{\n"+
//        "\"username\":\"Ranveer\",\n"+
//        "\"password\":\"Ranveer\",\n"+
//        "}";

//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.set("key","value");
//    User user = User.builder().username("Anshuman").password("Anshuman").build();
//    HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
//    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null,
//        WeatherResponse.class);

      WeatherResponse body = response.getBody();
      log.info("weather response", body.toString());
      if(body != null) {
        redisService.set("weather_of_" + city,body,300l);
      }
      return body;
    }
  }

}
