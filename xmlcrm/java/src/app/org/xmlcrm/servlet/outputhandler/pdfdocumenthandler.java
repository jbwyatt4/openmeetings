package org.xmlcrm.servlet.outputhandler;

import java.util.ArrayList;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.xmlcrm.app.outpuhandlers.pdf.pdfobjects.pdfobject;
import org.xmlcrm.app.remote.ResHandler;

public class pdfdocumenthandler {
    private ResHandler ResHandler;
    public pdfdocumenthandler(ResHandler ResHand){
        ResHandler = ResHand;
    }
    public void getDocument(String docCode,OutputStream out,int USER_ID,String UID){
        if(docCode.equals("TodoList")){
            ArrayList iList = ResHandler.getTerminmanagement().getTerminTodolistManagement().generatePDFDoc(USER_ID);
            ResHandler.getDocumentmanagement().generatePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out);
        } else if(docCode.equals("TodoItem")){
            try {
                int ID = Integer.valueOf(UID).intValue();
                ArrayList iList = ResHandler.getTerminmanagement().getTerminTodolistManagement().generateSinglePDFDoc(USER_ID,ID);
                ResHandler.getDocumentmanagement().generatePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out);
            } catch (Exception e){
                BadRequest(docCode,out,USER_ID);
            }
        } else {
            BadRequest(docCode,out,USER_ID);
        }
    }
    public void getDocument(String docCode,OutputStream out,int USER_ID,String pass,String UID){
        
        if(docCode.equals("TodoList")){
            ArrayList iList = ResHandler.getTerminmanagement().getTerminTodolistManagement().generatePDFDoc(USER_ID);
            ResHandler.getDocumentmanagement().generateSecurePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out,pass);
        } else if(docCode.equals("TodoItem")){
            try {
                int ID = Integer.valueOf(UID).intValue();
                ArrayList iList = ResHandler.getTerminmanagement().getTerminTodolistManagement().generateSinglePDFDoc(USER_ID,ID);
                ResHandler.getDocumentmanagement().generateSecurePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out,pass);
            } catch (Exception e){
                BadRequest(docCode,out,USER_ID);
            }
        } else {
            BadRequest(docCode,out,USER_ID);
        }
    }
    public void getDocument(String docCode,OutputStream out,int USER_ID,String UID,HttpServletRequest request){
        try {
            if(docCode.equals("terminday")){
                int syear = Integer.valueOf(request.getParameter("syear")).intValue();
                int smonth = Integer.valueOf(request.getParameter("smonth")).intValue();
                int sday = Integer.valueOf(request.getParameter("sday")).intValue();
                ArrayList iList = ResHandler.getTerminmanagement().PrintUserTermineDay(3,USER_ID,syear,smonth,sday);
                ResHandler.getDocumentmanagement().generatePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out);
            } else if(docCode.equals("terminweek")){
                int syear = Integer.valueOf(request.getParameter("syear")).intValue();
                int smonth = Integer.valueOf(request.getParameter("smonth")).intValue();
                int sday = Integer.valueOf(request.getParameter("sday")).intValue();
                System.out.println("Print Monat"+smonth);
                ArrayList iList = ResHandler.getTerminmanagement().PrintUserTermineWeek(3,USER_ID,syear,smonth,sday);
                ResHandler.getDocumentmanagement().generatePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out);
            } else if(docCode.equals("terminmonth")){
                int syear = Integer.valueOf(request.getParameter("syear")).intValue();
                int smonth = Integer.valueOf(request.getParameter("smonth")).intValue();
                System.out.println("Print Monat"+smonth);
                ArrayList iList = ResHandler.getTerminmanagement().PrintUserTermineMonth(3,USER_ID,syear,smonth);
                ResHandler.getDocumentmanagement().generatePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out);
            } else {
                BadRequest(docCode,out,USER_ID);
            }
        } catch (Exception e){
            BadRequest(docCode,out,USER_ID);
        }        
    }
    public void getDocument(String docCode,OutputStream out,int USER_ID,String pass,String UID,HttpServletRequest request){
        try {
            if(docCode.equals("terminday")){
                int syear = Integer.valueOf(request.getParameter("syear")).intValue();
                int smonth = Integer.valueOf(request.getParameter("smonth")).intValue();
                int sday = Integer.valueOf(request.getParameter("sday")).intValue();
                ArrayList iList = ResHandler.getTerminmanagement().PrintUserTermineDay(3,USER_ID,syear,smonth,sday);
                ResHandler.getDocumentmanagement().generateSecurePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out,pass);
            } else if(docCode.equals("terminweek")){
                int syear = Integer.valueOf(request.getParameter("syear")).intValue();
                int smonth = Integer.valueOf(request.getParameter("smonth")).intValue();
                int sday = Integer.valueOf(request.getParameter("sday")).intValue();
                ArrayList iList = ResHandler.getTerminmanagement().PrintUserTermineWeek(3,USER_ID,syear,smonth,sday);
                ResHandler.getDocumentmanagement().generateSecurePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out,pass);
            } else if(docCode.equals("terminmonth")){
                int syear = Integer.valueOf(request.getParameter("syear")).intValue();
                int smonth = Integer.valueOf(request.getParameter("smonth")).intValue();
                ArrayList iList = ResHandler.getTerminmanagement().PrintUserTermineMonth(3,USER_ID,syear,smonth);
                ResHandler.getDocumentmanagement().generateSecurePDFByArrayList(iList,docCode,"Ihre Todoliste",USER_ID,out,pass);
            } else {
                BadRequest(docCode,out,USER_ID);
            }
        } catch (Exception e){
            BadRequest(docCode,out,USER_ID);
        } 
    }    
    private void BadRequest(String docCode,OutputStream out,int USER_ID){
        ArrayList ListInner = new ArrayList();
        ArrayList iList = new ArrayList();
        iList.add(new pdfobject("Error 405 Bad Request","Die Anfrage wurde nicht verstanden. Der Client sollte die Anfrage nicht wiederholen, ohne dass Änderungen vorgenommen wurden.",false));
        iList.add(new pdfobject("Informationen","http://www.xmlcrm.de",false));
        ListInner.add(iList);
        ResHandler.getDocumentmanagement().generatePDFByArrayList(ListInner,docCode,"Ihre Todoliste",USER_ID,out);
 
    }
    
}
