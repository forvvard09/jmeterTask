import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Test01 {
    public static void main(String[] args) throws XMLStreamException, IOException {
        JmUtil01 util = new JmUtil01("https://www.w3schools.com/xml/plant_catalog.xml", 2);
        System.out.println(util.getRandomText());
        util.generateFileSearch();
    }
}
