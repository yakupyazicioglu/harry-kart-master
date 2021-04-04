/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.atg.service.harrykart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import models.Participant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author YY
 */
public class PopulateDTOWithParsedXML {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
   {
        List<Participant> participants = parseEmployeesXML();
        System.out.println(participants);
   }
 
   private static List<Participant> parseEmployeesXML() throws ParserConfigurationException, SAXException, IOException
   {
      //Initialize a list of employees
      List<Participant> participants = new ArrayList<Participant>();
      Participant participant = null;
       
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new File("src/main/resources/input_0.xml"));
      document.getDocumentElement().normalize();
      NodeList nList = document.getElementsByTagName("participant");
      for (int temp = 0; temp < nList.getLength(); temp++)
      {
         Node node = nList.item(temp);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
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
      return participants;
   }
}
