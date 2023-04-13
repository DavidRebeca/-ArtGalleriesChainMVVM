package view_model.command;

import model.ArtWork;
import model.persistence.ArtWorkPersistence;

import org.w3c.dom.Node;
import view_model.EmployeeVM;

import javax.swing.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import org.json.simple.JSONObject;

public class SaveCommand implements ICommand{
    private EmployeeVM employeeVM;
    public SaveCommand(EmployeeVM employeeVM){
        this.employeeVM=employeeVM;
    }
    @Override
    public void execute() {
        String format=employeeVM.getFormat().get();
        if(format.equals("TXT"))
            saveAsTxt();
        else if(format.equals("CSV"))
               saveAsCsv();
             else
                 if(format.equals("XML"))
                     saveAsXML();
                 else
                    if(format.equals("JSON"))
                       saveAsJSON();
                    else
                     JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Format should be TXT, CSV, XML or JSON!");


    }
    String putSpace(int nr){
        String st="";
        for(int i=0;i<nr;i++)
            st+=" ";
        return st;
    }
    void saveAsTxt(){
        try {
            PrintWriter out = new PrintWriter("ListTXT.txt");
            out.println("ID"+putSpace(5)+"TITLE"+putSpace(40)+"ARTIST"+putSpace(27)+"YEAR"+putSpace(8)+"TYPE"+putSpace(15)+"GALLERY"+putSpace(15)+"STATUS");
            List<ArtWork> sections = (new ArtWorkPersistence()).readAll();
            for (ArtWork artWork : sections) {
                out.println(Integer.toString(artWork.getIdArtWork())+putSpace(4)+artWork.getName()+putSpace(40-artWork.getName().length())+artWork.getArtist()+
                        putSpace(40-artWork.getArtist().length())+ String.valueOf(artWork.getYear())+putSpace(8)+artWork.getType()+
                        putSpace(15-artWork.getType().length())+artWork.getArtGallery().getName()+putSpace(20-artWork.getArtGallery().getName().length())+artWork.getStatus());
            }
            out.close();
        } catch (Exception ex){}
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"List saved! Check ListTXT.txt file");

    }
    public void saveAsCsv(){
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[]{"ID","TITLE","ARTIST","YEAR","TYPE","GALLERY","STATUS"});
        List<ArtWork> sections = (new ArtWorkPersistence()).readAll();
        for (ArtWork artWork : sections){
            dataLines.add(new String[]{Integer.toString(artWork.getIdArtWork()),artWork.getName(),artWork.getArtist(),
                    String.valueOf(artWork.getYear()),artWork.getType(),artWork.getArtGallery().getName(),artWork.getStatus()});
        }

        try (PrintWriter pw = new PrintWriter("ListCSV.csv")) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }catch (Exception ex){
            System.out.println("fail");
        }
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"List saved! Check ListCSV.csv file");

    }
    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }
    void saveAsXML()
    {
        try{
            Element root = new Element("list");
            List<ArtWork> sections = (new ArtWorkPersistence()).readAll();

            for (ArtWork artWork: sections){

                Element artElement = new Element("artWork");

                Element idElement = new Element("id");
                Element titleElement = new Element("title");
                Element artistElement = new Element("artist");
                Element yearElement = new Element("year");
                Element typeElement = new Element("type");
                Element galleryElement = new Element("gallery");
                Element statusElement = new Element("status");

                idElement.appendChild(Integer.toString(artWork.getIdArtWork()));
                titleElement.appendChild(artWork.getName());
                artistElement.appendChild(artWork.getArtist());
                yearElement.appendChild(Integer.toString(artWork.getYear()));
                typeElement.appendChild(artWork.getType());
                galleryElement.appendChild(artWork.getArtGallery().getName());
                statusElement.appendChild(artWork.getStatus());

                // add all contents to person
                artElement.appendChild(idElement);
                artElement.appendChild(titleElement);
                artElement.appendChild(artistElement);
                artElement.appendChild(yearElement);
                artElement.appendChild(typeElement);
                artElement.appendChild(galleryElement);
                artElement.appendChild(statusElement);

                // add person to root
                root.appendChild(artElement);
            }

            // create doc off of root
            Document doc = new Document(root);

            // the file it will be stored in
            File file = new File("ListXML.xml");
            if (!file.exists()){
                file.createNewFile();
            }

            // get a file output stream ready
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            // use the serializer class to write it all
            Serializer serializer = new Serializer(fileOutputStream, "UTF-8");
            serializer.setIndent(4);
            serializer.write(doc);}
        catch(Exception ex){}
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"List saved! Check ListXML.csv file");
    }

    // write doc to output stream
    private static void writeXml(Document doc, OutputStream output) {
     try {
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();

         // pretty print
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");

         DOMSource source = new DOMSource((Node) doc);
         StreamResult result = new StreamResult(output);

         transformer.transform(source, result);
     }catch (Exception ex){}

    }
    void saveAsJSON(){
        {
            try {
                    FileWriter file = new FileWriter("ListJSON.json");
                    JSONObject jsonObject = new JSONObject();
                    List<ArtWork> sections = (new ArtWorkPersistence()).readAll();
                    for (ArtWork artWork: sections){
                        jsonObject.put("ID", Integer.toString(artWork.getIdArtWork()));
                        jsonObject.put("TITLE", artWork.getName());
                        jsonObject.put("ARTIST", artWork.getArtist());
                        jsonObject.put("YEAR", Integer.toString(artWork.getYear()));
                        jsonObject.put("TYPE", artWork.getType());
                        jsonObject.put("GALLERY", artWork.getArtGallery().getName());
                        jsonObject.put("STATUS", artWork.getStatus());
                    file.append(jsonObject.toJSONString());
                    file.append("\n");
                }file.close();
            } catch (Exception e) {}

            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"List saved! Check ListJSON.json file");
        }
    }
}


