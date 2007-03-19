package org.xmlcrm.servlet.outputhandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xmlcrm.app.remote.ResHandler;

public class pdfout extends HttpServlet  {
    private ResHandler ResHandler;
    public pdfout(){
        ResHandler = new ResHandler();
    }
    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        
        // Arbeit an doPost() delegieren
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        String SID = request.getParameter("SID");
        String doc = request.getParameter("doc");
        String pass = request.getParameter("pass");
        String UID = request.getParameter("UID");
        String doccomplex = request.getParameter("doccomplex");
//        int USER_ID = ResHandler.getSessionmanagement().checkSession(SID);
//        int User_LEVEL = ResHandler.getUsermanagement().getUserLevelByID(USER_ID);
//        if(User_LEVEL>1 && request.getParameter("doc")!=null && request.getParameter("pass")== null && request.getParameter("doccomplex")!=null ){
//            response.setContentType("application/pdf");
//            OutputStream out = response.getOutputStream();
//            pdfdocumenthandler pdfdocumenthandler = new pdfdocumenthandler(ResHandler);
//            pdfdocumenthandler.getDocument(doc,out,USER_ID,UID,request);
//        } else if(User_LEVEL>1 && request.getParameter("doc")!=null && request.getParameter("pass")!= null && request.getParameter("doccomplex")!=null  ){
//            if (doccomplex.equals("yes")){
//                response.setContentType("application/pdf");
//                OutputStream out = response.getOutputStream();
//                pdfdocumenthandler pdfdocumenthandler = new pdfdocumenthandler(ResHandler);
//                pdfdocumenthandler.getDocument(doc,out,USER_ID,pass,UID,request);
//            } else {
//                response.setContentType("text/html");
//                PrintWriter out = response.getWriter();
//                out.println("<html><body>");
//                out.println("Bad request! ");
//                out.println("</body></html>");  
//            }
//        } else if(User_LEVEL>1 && request.getParameter("doc")!=null && request.getParameter("pass")== null){
//            response.setContentType("application/pdf");
//            OutputStream out = response.getOutputStream();
//            pdfdocumenthandler pdfdocumenthandler = new pdfdocumenthandler(ResHandler);
//            pdfdocumenthandler.getDocument(doc,out,USER_ID,UID);
//        } else if(User_LEVEL>1 && request.getParameter("doc")!=null && request.getParameter("pass")!= null){
//            response.setContentType("application/pdf");
//            OutputStream out = response.getOutputStream();
//            pdfdocumenthandler pdfdocumenthandler = new pdfdocumenthandler(ResHandler);
//            pdfdocumenthandler.getDocument(doc,out,USER_ID,pass,UID);
//        } else {
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//            out.println("<html><body>");
//            out.println("login required! ");
//            out.println("</body></html>");  
//        }
        /*response.setContentType("application/pdf");
        //PrintWriter out = response.getWriter();
        FileInputStream Input = new FileInputStream(getServletContext().getRealPath("WEB-INF/pdf")+"/"+"403forbidden.pdf");
        // HTML-Ausgabe erzeugen
        DataOutputStream out = new DataOutputStream(response.getOutputStream());
        out.w
        
        */
    }
    
}
