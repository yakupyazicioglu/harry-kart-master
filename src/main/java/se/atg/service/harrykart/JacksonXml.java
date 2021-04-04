/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.atg.service.harrykart;

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

/**
 *
 * @author YY
 */
public class JacksonXml {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        InputStream is = JacksonXml.class.getResourceAsStream("/src/main/resources/input_0.xml");

        XmlMapper xmlMapper = new XmlMapper();

        List<models.Participant> participants = xmlMapper.readValue(is, new TypeReference<List<models.Participant>>() {
        });

        Map<String, models.Participant> nameToParticipant = participants.stream()
                                                      .collect(Collectors.toMap(models.Participant::getName, Function.identity()));

        System.out.println(nameToParticipant.get(1)
                                        .getName());
    }

}
