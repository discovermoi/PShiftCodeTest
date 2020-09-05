import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Folders {
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {

        /* Solution to the CODE TEST - REM */
        ArrayList<String> list = new ArrayList<String>();

        try {
            //read XML from the given string
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);

            //this will return a list of xml tags whose name is `folder`
            NodeList hiList = doc.getElementsByTagName("folder");

            //Iterate and Extract folder names starting with letter U and add them to List
            for (int i = 0; i < hiList.getLength(); i++) {
                Node child = hiList.item(i);
                Node name = child.getAttributes().getNamedItem("name");
                String contents = name.getTextContent();
                if(contents.startsWith("u") || contents.startsWith("U")){
                    list.add(contents);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;

        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }
}