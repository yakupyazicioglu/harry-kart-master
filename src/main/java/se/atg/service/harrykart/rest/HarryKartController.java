package se.atg.service.harrykart.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.batch.item.ItemReader;
import se.atg.service.harrykart.XmlReader;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import models.Game;
import models.Participant;
import models.PowerUp;
import org.xml.sax.SAXException;
import se.atg.service.harrykart.PopulateDTOWithParsedXML;

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
        List<PowerUp> powerUps = new ArrayList<PowerUp>();

        Participant participant = new Participant();
        Game game = new Game();
        PowerUp powerUp = new PowerUp();

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
        
        
        NodeList loopList = document.getElementsByTagName("loop");
        //NodeList powerList = document.getElementsByTagName("lane");

        for (int temp = 0; temp < loopList.getLength(); temp++) {
            Node node = loopList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                powerUp.setLoopNo(Integer.parseInt(eElement.getAttribute("number")));
                powerUp.setLaneNo(Integer.parseInt(eElement.getAttribute("number")));
                powerUp.setPower(Integer.parseInt(eElement.getElementsByTagName("lane").item(0).getTextContent()));

                powerUps.add(powerUp);
            }
        }
        System.out.println(powerUps.toString());
        return powerUps.toString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/par")
    public String data() throws IOException, SAXException, ParserConfigurationException {
        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File("src/main/resources/input_0.xml"));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all datas
        NodeList nList1 = document.getElementsByTagName("harryKart");

        System.out.println("");    //Just a separator
        //Print each employee's detail
        Element element = (Element) nList1.item(0);
        System.out.println("Number Of Loops : " + element.getElementsByTagName("numberOfLoops").item(0).getTextContent());

        //Get all datas
        NodeList nList = document.getElementsByTagName("participant");

        System.out.println("============================");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //Print each employee's detail
                Element eElement = (Element) node;
                System.out.println("Lane : " + eElement.getElementsByTagName("lane").item(0).getTextContent());
                System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("Base Speed : " + eElement.getElementsByTagName("baseSpeed").item(0).getTextContent());
            }
        }

        return "{ \"message\": \"Don't know how to play yet\" }";
    }

}
