package netlane.tms.controllers;

import netlane.tms.models.LocationDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/locations")
public class LocationController {
    private KafkaTemplate<String, LocationDto> kafkaTemplate;

    public LocationController(KafkaTemplate<String, LocationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @MessageMapping("/location")
    @SendTo("/topic/locations")
    public void publish_via_websocket(LocationDto location) {
        kafkaTemplate.send("netlane", location);
    }

    @PostMapping
    public void publish_via_rest_api(@RequestBody LocationDto location) {
        kafkaTemplate.send("netlane", location);
    }
}
