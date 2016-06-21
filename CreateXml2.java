import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import java.io.File;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;

public class CreateXml2{

      private Document doc;
      private String path;
      //if the xml was empty the value is true
      private boolean wasEmpty;

      public Document getDocument(){
          return doc;
      }
      private void setPath(String path){
          this.path = path;
      }
      public String getPath(){
          return path;
      }
      public CreateXml2(String path)throws ParserConfigurationException{

              setPath(path);
              wasEmpty = verifyFile1();
              loadFile();
            }

     private void changeWasEmpty(){
        wasEmpty = !wasEmpty;
      }

      //verifies if the xml file is empty or not
      //if isn't empty loads the file to the doc
      //thus we can avoid lost of data or replacing the old content

      private boolean verifyFile1(){
        File file = new File(getPath());
        return file.length() < 100;
        }
        

          
      private void loadFile(){
          File file = new File(getPath());
          try{

            if (getWasEmpty()){
                //changeWasEmpty();
                doc = createDocument();
            }
            else{
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = docFactory.newDocumentBuilder();
                Document documentTest = db.parse(file);    
                doc = documentTest;
            }
          }    
        catch(Exception e){
          e.printStackTrace();
        }
        
      }    

      public boolean getWasEmpty(){
          return wasEmpty;
        }
      /*/public boolean loadDocument(){
          return verifyFile();
      }/*/
      public Document createDocument() throws ParserConfigurationException{
          //throw new ParserConfigurationException();
          //EX de chamada: Document doc = createDocument();
          DocumentBuilderFactory documentF = DocumentBuilderFactory.newInstance();
          //Criação de DocumentBuilder
          DocumentBuilder docBuilder = documentF.newDocumentBuilder();
          // partir do documentBuilder podemos criar Document

          return docBuilder.newDocument();

        }

      public Element createElement(String name) throws DOMException{
          //throw new Exception();
          return getDocument().createElement(name);
        }
      //Adiciona childs dentro do elemento root;
      public void appendGlobalChild(Element element) throws Exception{
          getDocument().appendChild(element);

          //throw new Exception();
        }
      //Adiciona childs dentro do um elemento qualquer;
      public void appendChildToElement(Element rootElement , Element addTo) throws DOMException{
          rootElement.appendChild(addTo);
          //throw new Exception();
      }
      //Adiciona childs dentro do um elemento qualquer, mas com text node <ex> issoéTextNode </ex>;
      public void appendTextNode(Element rootElement,String textNode) throws Exception{
          rootElement.appendChild(getDocument().createTextNode(textNode));
          //throw new Exception();
      }
      public void appendChildTextNode(Element rootElement , Element addTo, String textNode) throws Exception{
          rootElement.appendChild(addTo);
          addTo.appendChild(getDocument().createTextNode(textNode));
          //throw new Exception();
      }

      //Adiciona atributos a um determinado elemento
      public void addAttibute(Element element,String nameAtribute, String value ) throws DOMException{
          element.setAttribute(nameAtribute,value);
          //throw new Exception();
      }
      //Transforma e salva documento criado;
      public void transformAndSave() throws Exception{
        TransformerFactory transformF = TransformerFactory.newInstance();
        //Será usada para transformar o que já foi feito para a forma final que será salva
        Transformer transformer = transformF.newTransformer();
        //pega source do Document criado
        DOMSource source = new DOMSource(getDocument());

        //pega caminho e file para salvar resultado
        StreamResult result = new StreamResult(new File(getPath()));

        //faz a transformação
        transformer.transform(source,result);
        //throw new Exception();
      }


/*/
      public static void main(String[] args) {

      try{
        //cria objetos necessários para que seja prossível criar Document

        //Primeiramente cria-se um objeto DocumentBuilderFactory para que se possa criar um DocumentBuilder.
        DocumentBuilderFactory documentF = DocumentBuilderFactory.newInstance();

        //Criação de DocumentBuilder
        DocumentBuilder docBuilder = documentF.newDocumentBuilder();

        // partir do documentBuilder podemos criar Document
        Document doc = docBuilder.newDocument();

        //a partir de Document podemos criar Element
        Element pessoa = doc.createElement("Pessoa");
        Element outro = doc.createElement("outro");
        outro.appendChild(doc.createTextNode("texto"));

        //adiciona Element no escopo do arquivo xml
        doc.appendChild(pessoa);
        pessoa.appendChild(outro);
        //cria atributos dentro do Element pessoa
        pessoa.setAttribute("name","joao");
        pessoa.setAttribute("age","12");

        //TransformerFactory utilizada para gerar um Transformer
        TransformerFactory transformF = TransformerFactory.newInstance();

        //Será usada para transformar o que já foi feito para a forma final que será salva
        Transformer transformer = transformF.newTransformer();

        //pega source do Document criado
        DOMSource source = new DOMSource(doc);

        //pega caminho e file para salvar resultado
        StreamResult result = new StreamResult(new File("/home/ramon/Desktop/JV/teste.xml") );

        //faz a transformação
        transformer.transform(source,result);

        StreamResult results = new StreamResult(System.out);


      }catch(Exception e){
        e.printStackTrace();


      }
    }/*/
}
