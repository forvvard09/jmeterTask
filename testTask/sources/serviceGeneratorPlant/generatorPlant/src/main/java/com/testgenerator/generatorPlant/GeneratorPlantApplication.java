package com.testgenerator.generatorPlant;

import com.testgenerator.generatorPlant.model.Plant;
import com.testgenerator.generatorPlant.repository.Plants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.URL;

@SpringBootApplication
public class GeneratorPlantApplication {


	public static void main(String[] args) {
		SpringApplication.run(GeneratorPlantApplication.class, args);
	}


	@Bean
	ApplicationRunner init(Plants plants) {
		return (ApplicationArguments args) ->  dataSetup(plants);
	}

	public void dataSetup(Plants plants) throws XMLStreamException {
		XMLStreamReader reader = null;
		Plant plant = null;
		String tagContent = "";
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			URL url = new URL("https://www.w3schools.com/xml/plant_catalog.xml");
			reader = factory.createXMLStreamReader(url.openStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		while (reader.hasNext()) {
			int event = reader.next();
			switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("PLANT".equals(reader.getLocalName())) {
						plant = new Plant();
						plants.getListPlant().add(plant);
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
						case "COMMON":
							plant.setCommon(tagContent);
							break;
						case "BOTANICAL":
							plant.setBotanical(tagContent);
							break;
					}
					break;
			}
		}

	}

}
