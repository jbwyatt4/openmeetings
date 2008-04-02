package org.xmlcrm.app.outpuhandlers.pdf.handler;

import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.RandomAccessFile;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

import org.xmlcrm.app.outpuhandlers.pdf.pdfobjects.pdfobject;
import org.xmlcrm.app.outpuhandlers.utils.HtmlHandler;
import org.xmlcrm.utils.math.CalendarPatterns;

import com.lowagie.text.Chunk;

public class Documentmanagement {
    XMLReader xmlReader = null;
    private CalendarPatterns CalenderI;
 
    private HtmlHandler HtmlHandler;
    public Documentmanagement() {
        super();
        // TODO Auto-generated constructor stub

        CalenderI = new CalendarPatterns();
        HtmlHandler = new HtmlHandler(this);
        try {
            SAXParserFactory spfactory =
                SAXParserFactory.newInstance();
                spfactory.setValidating(false);
                SAXParser saxParser = spfactory.newSAXParser();
                xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(HtmlHandler);
            } catch (Exception e){
                System.out.println("Error exc"+e);
            }        
    }    
    public String generatePDFByArrayList(ArrayList params,String FILENAME,String HEADLINE,int USER_ID,OutputStream out){
        String ret = "generatePDFList";
        System.out.println("My First PdfPTable");
        

//        try {
//            String USER_NAME = ResHandler.getUsermanagement().getUser(USER_ID).getLogin();
//            // step 1: creation of a document-object
//            Document document = new Document(PageSize.A4, 10, 10, 10, 10);           
//            // step 2:
//            // we create a writer that listens to the document
//            // and directs a PDF-stream to a file
//            String path = "/home/swagner/lps-3.1/Server/tomcat-5.0.24/webapps/axis/WEB-INF/pdf/";
//            PdfWriter writer = PdfWriter.getInstance(document, out);
//            String FontName = "Unispace.ttf";
//            String FontBoldName = "Unispace_Bold.ttf";
//            String FontItalicName = "Unispace_Italic.ttf";
//            FontFactory.register(path+"fonts/"+FontName);
//            FontFactory.register(path+"fonts/"+FontBoldName);
//            FontFactory.register(path+"fonts/"+FontItalicName);
//            Font font = FontFactory.getFont("Unispace", BaseFont.WINANSI, 12);
//            Font fonti = FontFactory.getFont("Unispace Italic", BaseFont.WINANSI, 12);
//            Font fontb = FontFactory.getFont("Unispace Bold", BaseFont.WINANSI, 12);
//            
//            document.addTitle("XMLcrm generated PDF");
//            document.addSubject(HEADLINE);
//            document.addKeywords(HEADLINE);
//            document.addCreator("XMLcrm");
//            document.addAuthor(USER_NAME);
//            
//            // step 3: we open the document
//            document.open();
//            HtmlHandler.setDocument(document);
//            //xmlcrmpdf.jpg
//            RandomAccessFile rf = new RandomAccessFile(path+"xmlcrmpdf.jpg", "r");
//            int size = (int)rf.length();
//            byte imext[] = new byte[size];
//            rf.readFully(imext);
//            rf.close();
//            Image img1 = Image.getInstance(imext);
//            //img1.setAbsolutePosition(0,600);
//            document.add(img1);
//            
//            Paragraph p = new Paragraph(HEADLINE,fontb);
//            document.add(p);
//            
//            for (Iterator it2 = params.iterator(); it2.hasNext();) {
//                ArrayList Alist = (ArrayList) it2.next();
//                
//                for (Iterator it3 = Alist.iterator(); it3.hasNext();) {
//                    pdfobject pdfobj = (pdfobject) it3.next();
//                    if (pdfobj.isHtml()){
//                        Paragraph pr = new Paragraph(pdfobj.getCellname()+": ",font);
//                        document.add(pr);
//                        System.out.println("HTML obj "+pdfobj.getCellvalues());
//                        StringReader charstream = new StringReader("<root>"+pdfobj.getCellvalues()+"</root>");
//                        InputSource input = new InputSource(charstream);
//                        xmlReader.parse( input );
//                    } else {
//                        Paragraph pr = new Paragraph(pdfobj.getCellname()+": "+pdfobj.getCellvalues(),font);
//                        document.add(pr);                  
//                    }
//
//                }
//                document.newPage();
//                
//
//            }
//
//            
//            document.close();
//            
//        } catch (DocumentException de) {
//            System.out.println(de);
//        } catch (IOException ioe) {
//            System.out.println(ioe);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        
        // step 5: we close the document
        
        
        return ret;
    }
    
    public String generateSecurePDFByArrayList(ArrayList params,String FILENAME,String HEADLINE,int USER_ID,OutputStream out,String pass){
        String ret = "generatePDFList";
        System.out.println("My First PdfPTable");
        

//        try {
//            String USER_NAME = ResHandler.getUsermanagement().getUser(USER_ID).getLogin();
//            // step 1: creation of a document-object
//            Document document = new Document(PageSize.A4, 10, 10, 10, 10);           
//            // step 2:
//            // we create a writer that listens to the document
//            // and directs a PDF-stream to a file
//            String path = "/home/swagner/lps-3.1/Server/tomcat-5.0.24/webapps/axis/WEB-INF/pdf/";
//            PdfWriter writer = PdfWriter.getInstance(document, out);
//            writer.setEncryption(PdfWriter.STRENGTH128BITS, pass, USER_NAME, PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
//            String FontName = "Unispace.ttf";
//            String FontBoldName = "Unispace_Bold.ttf";
//            String FontItalicName = "Unispace_Italic.ttf";
//            FontFactory.register(path+"fonts/"+FontName);
//            FontFactory.register(path+"fonts/"+FontBoldName);
//            FontFactory.register(path+"fonts/"+FontItalicName);
//            Font font = FontFactory.getFont("Unispace", BaseFont.WINANSI, 12);
//            Font fonti = FontFactory.getFont("Unispace Italic", BaseFont.WINANSI, 12);
//            Font fontb = FontFactory.getFont("Unispace Bold", BaseFont.WINANSI, 12);
//            
//            document.addTitle("XMLcrm generated PDF");
//            document.addSubject(HEADLINE);
//            document.addKeywords(HEADLINE);
//            document.addCreator("XMLcrm");
//            document.addAuthor(USER_NAME);
//            
//            // step 3: we open the document
//            document.open();
//            HtmlHandler.setDocument(document);
//            //xmlcrmpdf.jpg
//            RandomAccessFile rf = new RandomAccessFile(path+"xmlcrmpdf.jpg", "r");
//            int size = (int)rf.length();
//            byte imext[] = new byte[size];
//            rf.readFully(imext);
//            rf.close();
//            Image img1 = Image.getInstance(imext);
//            //img1.setAbsolutePosition(0,600);
//            document.add(img1);
//            
//            Paragraph p = new Paragraph(HEADLINE,fontb);
//            document.add(p);
//            
//            for (Iterator it2 = params.iterator(); it2.hasNext();) {
//                ArrayList Alist = (ArrayList) it2.next();
//                
//                for (Iterator it3 = Alist.iterator(); it3.hasNext();) {
//                    pdfobject pdfobj = (pdfobject) it3.next();
//                    if (pdfobj.isHtml()){
//                        Paragraph pr = new Paragraph(pdfobj.getCellname()+": ",font);
//                        document.add(pr);
//                        System.out.println("HTML obj "+pdfobj.getCellvalues());
//                        StringReader charstream = new StringReader("<root>"+pdfobj.getCellvalues()+"</root>");
//                        InputSource input = new InputSource(charstream);
//                        xmlReader.parse( input );
//                    } else {
//                        Paragraph pr = new Paragraph(pdfobj.getCellname()+": "+pdfobj.getCellvalues(),font);
//                        document.add(pr);                  
//                    }
//
//                }
//                document.newPage();
//                
//
//            }
//
//            
//            document.close();
//            
//        } catch (DocumentException de) {
//            System.out.println(de);
//        } catch (IOException ioe) {
//            System.out.println(ioe);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        
        // step 5: we close the document
        
        
        return ret;
    }    
}
