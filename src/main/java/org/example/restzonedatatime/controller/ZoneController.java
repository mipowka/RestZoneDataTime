package org.example.restzonedatatime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/date-time")
public class ZoneController {

    @GetMapping("/{region}/{city}")
    public ResponseEntity<?> getTime(@PathVariable String region, @PathVariable String city) {
        String dataFromUser = region.concat("/").concat(city);
        try {
            ZoneId zoneId = ZoneId.of(dataFromUser);
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy года, HH:mm:ss");
            String formattedDateTime = zonedDateTime.format(formatter);

            return ResponseEntity.ok(formattedDateTime + " " + region + " " + city);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid region or city: " + dataFromUser);
        }
    }
}
