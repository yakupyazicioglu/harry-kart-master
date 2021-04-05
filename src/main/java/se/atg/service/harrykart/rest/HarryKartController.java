package se.atg.service.harrykart.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.batch.item.ItemReader;
import se.atg.service.harrykart.XmlReader;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import models.Game;
import models.Horse;
import models.Participant;
import models.PowerUp;
import org.xml.sax.SAXException;

@RestController
@RequestMapping("/api")
public class HarryKartController {

    Game game = new Game();

    @RequestMapping(method = RequestMethod.POST, path = "/play")
    public String playHarryKart() {
        List<Integer> times = new ArrayList<>(4);

        for (int temp = 0; temp < game.getNumberOfLoops(); temp++) {
            for (int temp2 = 0; temp2 < game.getParticipants().size(); temp2++) {
                int powerUp = game.getPowerUps().getLoopsMap().get(temp).get(temp2);
                int baseSpeed = game.getParticipants().get(temp).getBaseSpeed();
                int horseSpeed = baseSpeed + powerUp;
                Integer loopTime = game.getLaneDistance() / horseSpeed;
                times.add(temp, loopTime);
                System.out.println(" Loop: " + temp + " Participant: " + temp2 + " PowerUp: " + powerUp + 
                        " baseSpeed: " + baseSpeed + " horseSpeed: " + horseSpeed + " loopTime: " + loopTime);
            }

        }
        System.out.println(times.toString());
        return "{ \"message\": \"Don't know how to play yet\" }";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/participants")
    public String participants() throws SAXException, IOException, ParserConfigurationException {
        List<Participant> participants = new ArrayList<Participant>();
        Participant participant = new Participant();
        PowerUp powerUp = new PowerUp();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/input_0.xml"));
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
        //System.out.println(game.toString());
        return game.toString();
    }
}
