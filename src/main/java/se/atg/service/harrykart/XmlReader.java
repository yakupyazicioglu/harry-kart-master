/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.atg.service.harrykart;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 *
 * @author YY
 */
@Configuration
public class XmlReader {

    @Bean
    public ItemReader<models.Game> itemReader() {
        Jaxb2Marshaller laneMarshaller = new Jaxb2Marshaller();
        laneMarshaller.setClassesToBeBound(models.Game.class);

        return new StaxEventItemReaderBuilder<models.Game>()
                .name("laneReader")
                .resource(new ClassPathResource("/src/main/resources/input_0.xml"))
                .addFragmentRootElements("lane")
                .unmarshaller(laneMarshaller)
                .build();
    }
}
