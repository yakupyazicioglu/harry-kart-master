/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.atg.service.harrykart.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import se.atg.service.harrykart.models.Game;
import se.atg.service.harrykart.models.Participant;
import se.atg.service.harrykart.models.PowerUp;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author YY
 */
public class ParseXMLtoObject {

    public ParseXMLtoObject() {
    }

    public Game getData() {
        Game game = new Game();
        List<Participant> participants = new ArrayList<>();
        Participant participant;
        PowerUp powerUp = new PowerUp();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParseXMLtoObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document document = null;
        try {
            try {
                document = builder.parse(new File("src/main/resources/input_1.xml"));
            } catch (SAXException ex) {
                Logger.getLogger(ParseXMLtoObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ParseXMLtoObject.class.getName()).log(Level.SEVERE, null, ex);
        }
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
