package org.openmeetings.app.outpuhandlers.utils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;


import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.BaseFont;

import org.openmeetings.app.outpuhandlers.pdf.handler.Documentmanagement;

public class HtmlHandler implements ContentHandler {
    private Documentmanagement DocumentmanagementI;
    private String textBuffer = "";
    private ArrayList ParsedElements;
    private ArrayList CurrentElementAttr;
    private ArrayList FONTElements;
    private ArrayList BOLDElements;
    private ArrayList ITALICElements;
    private ArrayList UNDERLINEElements;  
    private Document document;
    private String path;
    public HtmlHandler(Documentmanagement Docman){
        DocumentmanagementI = Docman;
        textBuffer = "";
        try {
            path = "/home/swagner/lps-3.1/Server/tomcat-5.0.24/webapps/axis/WEB-INF/pdf/";  
        } catch (Exception e){
            System.out.println("Error: "+e);
        }
    }


    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        ParsedElements = new ArrayList();
        FONTElements = new ArrayList();
        BOLDElements = new ArrayList();
        ITALICElements = new ArrayList();
        UNDERLINEElements = new ArrayList();
    }
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        int SIZE = -1;
        try {        
            for (Iterator it = ParsedElements.iterator(); it.hasNext(); ) {
               ArrayList AList = (ArrayList)it.next();
               String[] name = (String[]) AList.get(0);
               ArrayList List = (ArrayList) AList.get(1);
               /* HTML-Output not implemented with Color-Object because awt-issues on Debian-Servers
               */
               //System.out.println(List.size());
              
               int[] color = new int[3];
               color[0]=-1;
               for (Iterator it3 = List.iterator(); it3.hasNext(); ) {
                   String[] attr = (String[]) it3.next();
                   //System.out.println("attr name: "+attr[0]);
                   //System.out.println("attr value: "+attr[1]);
                   attr[0] = attr[0].toLowerCase();
                   if (attr[0].equals("color")){
                       convertRGB(attr[1]);
                   } else if(attr[0].equals("size")){
                       SIZE = Integer.valueOf(attr[1]).intValue();
                       System.out.println("SIZE FOUND: "+SIZE);
                   }
               }
               name[0] = name[0].toLowerCase();
               if (SIZE==-1){
                   SIZE=12;
               }
               if (color[0]==-1){
                   color[0]=0;
                   color[1]=0;
                   color[2]=0;
               }
               //System.out.println("Elementname: "+name[0]);
               //System.out.println("Elementvalue: "+name[1]);
               System.out.println("SIZE: "+SIZE+" Type: "+name[0]+" Txt: "+name[1]);
               if (name[0].equals("font")){
                   if (!FontFactory.isRegistered("Unispace")){
                       String FontName = "Unispace.ttf";
                       FontFactory.register(path+"fonts/"+FontName);
                   }
                   /* ... s.o.
                    System.out.println("1  +++ Color: "+color[0]+" "+color[1]+" "+color[2]);
                    
                   Color Cl = new Color(0xFF, 0x00, 0x00);
                   System.out.println("2  +++ Color: "+Cl);
                   System.out.println("3  +++ Color: "+Cl.getBlue());
                   System.out.println("4  +++ Color: "+Cl.getGreen());
                   System.out.println("5  +++ Color: "+Cl.getRed());
                   */
                   Font ff = FontFactory.getFont("Unispace", BaseFont.WINANSI, SIZE);
                   FONTElements.add(name[1]);
                   Paragraph pr = new Paragraph(name[1],ff);
                   document.add(pr);
                   SIZE = -1;
               } else if(name[0].equals("b")){
                   if (!FontFactory.isRegistered("Unispace Bold")){
                       String FontBoldName = "Unispace_Bold.ttf";
                       FontFactory.register(path+"fonts/"+FontBoldName);
                   }             
                   Font ff = FontFactory.getFont("Unispace", BaseFont.WINANSI, SIZE);
                   BOLDElements.add(name[1]);
                   Paragraph pr = new Paragraph(name[1],ff);
                   document.add(pr);   
                   SIZE = -1;
               } else if(name[0].equals("i")){  
                   if (!FontFactory.isRegistered("Unispace Bold")){
                       String FontBoldName = "Unispace_Italic.ttf";
                       FontFactory.register(path+"fonts/"+FontBoldName);
                   }             
                   Font ff = FontFactory.getFont("Unispace Italic", BaseFont.WINANSI, SIZE);                   
                   ITALICElements.add(name[1]);
                   Paragraph pr = new Paragraph(name[1],ff);
                   document.add(pr);
                   SIZE = -1;
               } else if(name[0].equals("u")){
                   if (!FontFactory.isRegistered("Unispace")){
                       String FontName = "Unispace.ttf";
                       FontFactory.register(path+"fonts/"+FontName);
                   }
                   Font ff = FontFactory.getFont("Unispace", BaseFont.WINANSI, SIZE, Font.UNDERLINE);
                   UNDERLINEElements.add(name[1]);
                   Paragraph pr = new Paragraph(name[1],ff);
                   document.add(pr);   
                   SIZE = -1;
               }
            }
        } catch (Exception e){
            System.out.println("Error: "+e);
        }        
        
    }
    private int[] convertRGB(String color) throws Exception{
        int[] values = new int[3];
        int r1 = convertString(color.substring(1,2));
        int r2 = convertString(color.substring(2,3));
        values[0]= r1*16+r2;
        //System.out.println("Red: "+values[1]);
        int g1 = convertString(color.substring(3,4));
        int g2 = convertString(color.substring(4,5));
        values[1]= g1*16+g2;
        //System.out.println("Green: "+values[2]);
        int b1 = convertString(color.substring(5,6));
        int b2 = convertString(color.substring(6,7));
        values[2]= b1*16+b2;
        //System.out.println("Blue: "+values[2]);
        return values;
    }
    private int convertString(String val) throws Exception{
        if (val.equals("A")){
            val="10";
        } else if(val.equals("B")){
            val="11";
        } else if (val.equals("C")){
            val="12";
        } else if(val.equals("D")){
            val="13";
        } else if(val.equals("E")){
            val="14";
        } else if(val.equals("F")){
            val="15";
        }
        return Integer.valueOf(val).intValue();
    }
    public void startElement( String namespaceURI, String localName, String qName, Attributes atts ) throws SAXException {
        // TODO Auto-generated method stub
        CurrentElementAttr = new ArrayList();
        for ( int i = 0; i < atts.getLength(); i++ ){
            String[] Current = new String[2];
            Current[0] =  atts.getQName( i );
            Current[1] =  atts.getValue( i );
            CurrentElementAttr.add(Current);
            //System.out.println( "Attribut Nr. " + i + ": " +
             // atts.getQName( i ) + " = " + atts.getValue( i ) );
        }        
    }
    public void endElement( String namespaceURI, String localName, String qName ) throws SAXException {
        // TODO Auto-generated method stub
        String[] Current = new String[2];
        Current[0] = qName;
        Current[1] = textBuffer;
        ArrayList myElements = new ArrayList();
        myElements.add(Current);
        myElements.add(CurrentElementAttr);
        ParsedElements.add(myElements);
        textBuffer = "";        
    }
    public void processingInstruction(String arg0, String arg1) throws SAXException {
        // TODO Auto-generated method stub
        
    }
    public void setDocumentLocator(Locator arg0) {
        // TODO Auto-generated method stub
        
    }

    public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
        // TODO Auto-generated method stub
        
    }
    public void characters( char ch[], int start, int length ) throws SAXException {
        // TODO Auto-generated method stub
        for ( int i = start; i < (start + length); i++ ){
              //System.out.print( "." + ch[i] );
                textBuffer += ch[i];
            }
    }
    public void skippedEntity(String arg0) throws SAXException {
        // TODO Auto-generated method stub
        
    }


    public void startPrefixMapping(String arg0, String arg1) throws SAXException {
        // TODO Auto-generated method stub
        
    }
    public void endPrefixMapping(String arg0) throws SAXException {
        // TODO Auto-generated method stub
        
    }
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }
}
