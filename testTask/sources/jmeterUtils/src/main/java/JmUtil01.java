import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JmUtil01 {

    private String pathXml;
    private int countThreads;
    private List<Plant> listPlant;


    public JmUtil01(String pathXml, int countThreads) {
        this.pathXml = pathXml;
        this.countThreads = countThreads;
        this.listPlant = new ArrayList<>();
    }

    public JmUtil01(String pathXml) {
        this(pathXml, 1);
    }

    private int getRandomNumber(int max) {
        int min = 0;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff);
        i += min;
        return i;
    }

    private void fillDataFromFile() throws XMLStreamException {
        XMLStreamReader reader = null;
        Plant plant = null;
        String tagContent = "";
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            URL url = new URL(pathXml);
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
                        listPlant.add(plant);
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

    public String getRandomText() throws XMLStreamException {
        fillDataFromFile();
        int randomPlaint = getRandomNumber(listPlant.size());
        return listPlant.get(randomPlaint).toString();
    }

    public String getRandomTextWithoutFillFile() throws XMLStreamException {
        int randomPlaint = getRandomNumber(listPlant.size());
        return listPlant.get(randomPlaint).toString();
    }

    public void generateFileSearch() throws IOException, XMLStreamException {
        fillDataFromFile();
        for (int i = 1; i <= countThreads; i++) {
            String nameFile = String.format("./out/%sthread.csv", i);
            File file = new File(nameFile);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            Writer writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            writer.write(getRandomTextWithoutFillFile());
            writer.write(System.lineSeparator());
            writer.flush();
        }
    }
}
