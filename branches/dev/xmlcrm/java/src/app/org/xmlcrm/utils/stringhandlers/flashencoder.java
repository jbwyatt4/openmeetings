package org.xmlcrm.utils.stringhandlers;

public class flashencoder {
    public String substr( String s, String search, String replace )
    {
       StringBuffer s2 = new StringBuffer();
       int i = 0, j = 0;
       int len = search.length();

       while ( j > -1 )
       {
           j = s.indexOf( search, i );

           if ( j > -1 )
           {
             s2.append( s.substring(i,j) );
             s2.append( replace );
             i = j + len;
           }
       }
       s2.append( s.substring(i, s.length()) );

       return s2.toString();
    }
}
