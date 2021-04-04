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
import models.Participant;
import models.PowerUp;
import org.xml.sax.SAXException;

@RestController
@RequestMapping("/api")
public class HarryKartController {

    @RequestMapping(method = RequestMethod.POST, path = "/play")
    public String playHarryKart() {

        return "{ \"message\": \"Don't know how to play yet\" }";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/participants")
    public String datas() {
        XmlReader reader = new XmlReader();
        ItemReader<models.Game> result = reader.itemReader();
        System.out.println(result);

        return "{ \"message\": \"Don't know how to play yet\" }";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/part")
    public String participants() throws SAXException, IOException, ParserConfigurationException {
        List<Participant> participants = new ArrayList<Participant>();
        Participant participant = new Participant();
        Game game = new Game();
        PowerUp powerUp = new PowerUp();
        List<Integer> powerUps = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/input_0.xml"));
        document.getDocumentElement().normalize();

        NodeList nCard = document.getElementsByTagName("harryKart");
        Element eCard = (Element) nCard.item(0);
        game.setNumberOfLoops(Integer.parseInt(eCard.getElementsByTagName("numberOfLoops").item(0).getTextContent()));

        NodeList parList = document.getElementsByTagName("participant");
        for (int temp = 0; temp < parList.getLength(); temp++) {
            Node node = parList.item(temp);
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
        //NodeList powerList = document.getElementsByTagName("lane");

        for (int temp = 0; temp < loopList1.getLength(); temp++) {
            Node node1 = loopList1.item(temp);
            if (node1.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement1 = (Element) node1;
                Integer loopNo = Integer.parseInt(eElement1.getAttribute("number"));
                
                for (int temp2 = 0; temp2 < loopList1.getLength(); temp2++) {
                    Node node2 = loopList1.item(temp2);
                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement2 = (Element) node2;
                        
                        powerUps.add(Integer.parseInt(eElement2.getElementsByTagName("lane").item(temp2).getTextContent()));
                    }

                }
                powerUp.getLoopsMap().put(loopNo, powerUps);

            }
            System.out.println(powerUp.toString());
            
        }
        return powerUp.toString();
    }
}

