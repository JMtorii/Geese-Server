package flock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


/*
 Sample card
 ===========
{
        "card":{
        "templateId":1,
        "user":{
        "fullName":"Billy Bee",
        "email":"not_today_mofo@gmail.com",
        "phoneNumber":4151112222,
        },
        "imageLogo":{
        "src":"Images/HappyBee.png",
        "name":"Haps Bee"
        },
        "company":{
        "name":"Lumosity",
        "position":"Useless"
        }
        }}*/

@RestController
public class CardController {
    private final AtomicLong cardCounter = new AtomicLong();

    @RequestMapping("/flock/sample")
    public Card sampleCard() {
        return new Card(cardCounter.incrementAndGet(), new User("Billy Bee", "sample_email@gmail.com", 1416123456), new ImageLogo("Images/HappyBee.png", "Haps Bee"), new Company("Taktme", "Dictator-For-Life"));
    }
}
