package org.xmlcrm.webstart.jmf;

import java.awt.*;
import java.awt.event.*;

import com.sun.media.util.JMFI18N;
import com.sun.media.ui.TabControl;

import jmapps.ui.*;
import jmapps.registry.*;

public class JMFManager extends JMFrame {

    private TabControl  tabs;
    private Panel       panelPM;
    private Panel       panelPIM;
    private Panel       panelCDM;
    private Panel       panelMime;
    private Panel       panelOther;


    public JMFManager () {
        super(JMFI18N.getResource("jmfregistry.title"));

        this.setLayout ( new BorderLayout() );
        tabs = new TabControl();
        this.add ( tabs, BorderLayout.CENTER );

        panelOther = new SettingsPanel();
        tabs.addPage ( panelOther, JMFI18N.getResource("jmfregistry.settings") );

        panelCDM = new CDMPanel();
        tabs.addPage ( panelCDM, JMFI18N.getResource("jmfregistry.capture") );

        panelPIM = new PIMPanel();
        tabs.addPage ( panelPIM, JMFI18N.getResource("jmfregistry.plugins") );

        panelMime = new MimePanel();
        tabs.addPage ( panelMime, JMFI18N.getResource("jmfregistry.mimetypes") );

        panelPM = new PMPanel();
        tabs.addPage ( panelPM, JMFI18N.getResource("jmfregistry.package") );

        setSize ( 700, 400 );
    }

    public void windowClosing ( WindowEvent event ) {
        this.setVisible ( false );
    }

    public static void main ( String [] args ) {
    	JMFManager jmfr = new JMFManager ();
        jmfr.addWindowListener (
            new WindowAdapter () {
                public void windowClosing ( WindowEvent event ) {
                    event.getWindow().dispose ();
                    System.exit ( 0 );
                }
            }
        );
        jmfr.setVisible(true);
    }

}

