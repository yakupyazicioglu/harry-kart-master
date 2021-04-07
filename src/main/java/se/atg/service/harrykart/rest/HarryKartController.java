package se.atg.service.harrykart.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import se.atg.service.harrykart.models.Game;
import se.atg.service.harrykart.models.Participant;
import se.atg.service.harrykart.models.Rank;
import org.springframework.http.MediaType;
import se.atg.service.harrykart.utils.ParseXMLtoObject;

@RestController
@RequestMapping("/api")
public class HarryKartController {

    @RequestMapping(method = RequestMethod.POST, path = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
    public String playHarryKart() {
        ParseXMLtoObject parseXmlObject = new ParseXMLtoObject();
        Game game = parseXmlObject.getData();
        List<Rank> ranking = new ArrayList<>();

        //loop for participants list in every lane
        for (int lane = 0; lane < game.getParticipants().size(); lane++) {
            Rank horse = new Rank();
            Participant participant = game.getParticipants().get(lane);
            horse.setName(participant.getName());
            participant.setCurrentSpeed(participant.getBaseSpeed());

            //loop for powerUps in every lane for participants one by one
            for (int loop = 0; loop < game.getNumberOfLoops(); loop++) {
                int powerUp = game.getPowerUps().getLoopsMap().get(loop).get(lane);
                participant.setCurrentSpeed(participant.getCurrentSpeed() + powerUp);
                int loopTime = game.getLaneDistance() / participant.getCurrentSpeed();
                participant.setFinishTime(participant.getFinishTime() + loopTime);
                horse.setFinishTime(participant.getFinishTime());
            }
            ranking.add(horse);
        }

        Collections.sort(ranking);

        for (int rank = 0; rank < ranking.size(); rank++) {
            ranking.get(rank).setPosition(rank + 1);
        }

        ranking.removeIf(h -> h.getPosition() > 3);

        return "ranking:" + ranking.toString();
    }

}
