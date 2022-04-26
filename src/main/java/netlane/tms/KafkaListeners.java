package netlane.tms;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "netlane",
            groupId = "groupId" // should be unique
    )
    void listener(String data) {
        System.out.println( "Listener received: " + data);
    }
}
