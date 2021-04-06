package se.atg.service.harrykart.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.Game;
import models.Horse;
import models.Participant;
import models.PowerUp;
import models.Ranking;
import org.springframework.http.MediaType;
import org.xml.sax.SAXException;

@RestController
@RequestMapping("/api")
public class HarryKartController {

    Game game = new Game();

    @RequestMapping(method = RequestMethod.POST, path = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
    public String playHarryKart() throws SAXException, ParserConfigurationException, IOException {
        List<Horse> horses = new ArrayList<>();        
        List<Ranking> ranking = new ArrayList<>();

        for (int lane = 0; lane < game.getParticipants().size(); lane++) {
            Horse horse = new Horse();
            Participant participant = game.getParticipants().get(lane);
            horse.setName(participant.getName());
            participant.setCurrentSpeed(participant.getBaseSpeed());
            for (int loop = 0; loop < game.getNumberOfLoops(); loop++) {

                int powerUp = game.getPowerUps().getLoopsMap().get(loop).get(lane);
                participant.setCurrentSpeed(participant.getCurrentSpeed() + powerUp);
                Integer loopTime = game.getLaneDistance() / participant.getCurrentSpeed();
                participant.setFinishTime(participant.getFinishTime() + loopTime);
                horse.setFinishTime(participant.getFinishTime());
            }
            horses.add(horse);
        }

        Collections.sort(horses);

        for (int rank = 0; rank < horses.size() - 1; rank++) {
            Ranking newRanking = new Ranking();
            horses.get(rank).setPosition(rank);
            newRanking.setName(horses.get(rank).getName());
            newRanking.setPosition(horses.get(rank).getPosition() + 1);
            ranking.add(newRanking);
        }
        
        return "ranking: " + ranking.toString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
    private Game ParseXMLtoObject() throws SAXException, ParserConfigurationException, IOException {
        List<Participant> participants = new ArrayList<>();
        Participant participant;
        PowerUp powerUp = new PowerUp();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/input_1.xml"));
        document.getDocumentElement().normalize();

        NodeList nCard = document.getElementsByTagName("harryKart");
        Element eCard = (Element) nCard.item(0);
        game.setNumberOfLoops(Integer.parseInt(eCard.getElementsByTagName("numberOfLoops").item(0).getTextContent()));

        NodeList participantList = document.getElementsByTagName("participant");
        for (int temp = 0; temp < participantList.getLength(); temp++) {
            Node node = participantList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                //Create new Employee Object
                participant = new Participant();
                participant.setLane(eElement.getElementsByTagName("lane").item(0).getTextContent());
                participant.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                participant.setBaseSpeed(Integer.parseInt(eElement.getElementsByTagName("baseSpeed").item(0).getTextContent()));

                //Add Employee to list
                participants.add(participant);
            }
        }

        game.setParticipants(participants);

        NodeList loopList1 = document.getElementsByTagName("loop");

        for (int temp = 0; temp < loopList1.getLength(); temp++) {
            Node node1 = loopList1.item(temp);
            if (node1.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement1 = (Element) node1;
                Integer loopNo = Integer.parseInt(eElement1.getAttribute("number"));

                List<Integer> powerUps = new ArrayList<>();

                for (int temp2 = 0; temp2 < 4; temp2++) {
                    powerUps.add(Integer.parseInt(eElement1.getElementsByTagName("lane").item(temp2).getTextContent()));

                }
                powerUp.getLoopsMap().put(loopNo, powerUps);

            }
        }

        game.setPowerUps(powerUp);

        return game;
    }
}
