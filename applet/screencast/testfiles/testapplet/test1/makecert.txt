I think you call methods from javascript:
http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java-security&F=&S=&P=4012

Posted this many times if you searched for AccessControlException you would have found it, here it is
again:

Signed Applet still throws an Access/security exception



The following examples are for a win 2000 machine, for linux
you have to use alternative paths but I think the commands
are about the same.

The SUN jre doesn't care about IE settings, if an applet is signed it will ask the user "do you trust", if
the user chooses yes or always the applet can do pretty much anything.

Because anybody can sign an applet so it will pop up the do
you trust dialog I prevent this dialog from popping up by
adding the following to the
C:\Program Files\Java\j2re****\lib\security\java.policy
under grant {
[color red]
permission java.lang.RuntimePermission "usePolicy";
[/color]

You now need to set up special permissions for sites that
need it, signed applets get no special treatment since you
specified in the java.policy that policy should allways be
used.

When your applet needs to do something it normally could
not do (applet security) and it needs to do this
when a user clicks on a html button (applet method called
from javascript), than all the signing and policy settings
in the world wouldn't work Unless you grant all permission
to all code.
This is because the Java Plug-in executes methods with
applet sandbox security restrictions.
http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java-security&F=&S=&P=4012

To solve this you can start a new thread that checks ...
times a second if a variable meets certain conditions.
These conditions are changed with public methods called
from JavaScript. When a variable meets certain Conditions
this thread will start the method that will perform
normally restricted tasks.

Here is an example where the applet doesn't work
(the batchfile, html file are OK)
Note that running this code with Mozilla on my w2k machine
crashes Mozilla (not on my Fedora machine)

Batch file to sign the applet: (please note this will
delete some files)

del *.cer
del *.com
del *.jar
del *.class
javac -classpath ".;C:\Program Files\Java\j2re1.4.2_04\lib\plugin.jar" test.java
keytool -genkey -keystore harm.com -keyalg rsa -dname "CN=Harm Meijer, OU=Technology, O=org, L=Amsterdam, ST=, C=NL" -alias harm -validity 3600 -keypass password -storepass password
jar cf0 test.jar *.class
jarsigner -keystore harm.com -storepass password -keypass password -signedjar sTest.jar test.jar harm 
del *.class



The html page:

<DIV id="dvObjectHolder">Applet comes here</DIV>
<br><br>
 
<script>
if(window.navigator.appName.toLowerCase().indexOf("netscape")!=-1){ // set object for Netscape:
	document.getElementById('dvObjectHolder').innerHTML = "        <object ID='appletTest1' classid=\"java:test.class\"" +
                "height=\"0\" width=\"0\" onError=\"changeObject();\"" +
		">" +
                "<param name=\"mayscript\" value=\"Y\">" +
                "<param name=\"archive\" value=\"sTest.jar\">" +
        "</object>";
}else if(window.navigator.appName.toLowerCase().indexOf('internet explorer')!=-1){ //set object for IE
	document.getElementById('dvObjectHolder').innerHTML =      "<object ID='appletTest1' classid=\"clsid:8AD9C840-044E-11D1-B3E9-00805F499D93\"" +
		     "         height=\"0\" width=\"0\" >" +
		     "   <param name=\"code\" value=\"test.class\" />" +
	             "<param name=\"archive\" value=\"sTest.jar\">" +
		     " </object>"
 
}
 
</script>
 
<LABEL id="lblOutputText">This text will be replaced by the applet</LABEL>
<BR>
<input value="Javascript to java" type=button onClick="document.appletTest1.fromJavaScript()"><br>



The applet:

// new class for jsObject!!!! since 1.4.2 compile this: 
// javac -classpath "C:\Program Files\Java\j2re1.4.2_01\lib\plugin.jar" test.java
// since jaws.jar does not exsist anymore
// to compile with jaws: javac -classpath "C:\j2sdk1.4.0_03\jre\lib\jaws.jar" test.java
import netscape.javascript.*;
public class test extends java.applet.Applet {
    JSObject win;
    JSObject outputLabel;
    public void init() {
    	try{
	        win = JSObject.getWindow(this);
	        outputLabel = (JSObject) win.eval("document.getElementById('lblOutputText')");
		outputLabel.setMember("innerHTML", "<center><h1>From Init<br>Your homedir " + System.getProperty("user.home") + "</h1></center>");
        }catch(Exception e){
        	e.printStackTrace();
	}
    }
    public void fromJavaScript(){
    	try{
	       	outputLabel.setMember("innerHTML", "<center><h1>From javascript<br>Your homedir: "+ System.getProperty("user.home") + "</h1></center>");
        }catch(Exception e){
        	e.printStackTrace();
	}
    }
}




When you put the files in c:\temp, run the batch file to
compile and sign the applet and then open the html file you
will be asked if you trust ... you can say yes and from
init the applet can read user.home. Click on the button and
you will get the following stack trace:

java.security.AccessControlException: access denied (java.util.PropertyPermission user.home read)
	at java.security.AccessControlContext.checkPermission(Unknown Source)
	at java.security.AccessController.checkPermission(Unknown Source)
	at java.lang.SecurityManager.checkPermission(Unknown Source)
	at java.lang.SecurityManager.checkPropertyAccess(Unknown Source)
	at java.lang.System.getProperty(Unknown Source)
	at test.fromJavaScript(test.java:20)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at sun.plugin.com.MethodDispatcher.invoke(Unknown Source)
	at sun.plugin.com.DispatchImpl.invokeImpl(Unknown Source)
	at sun.plugin.com.DispatchImpl$2.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.plugin.com.DispatchImpl.invoke(Unknown Source)



Conclusion: the applet can read user.home but not from
JavaScript.

Here is the applet that does work because a method called
from javaScript doesn't perform a restricted task.

// new class for jsObject!!!! since 1.4.2 compile this: 
// javac -classpath "C:\Program Files\Java\j2re1.4.2_01\lib\plugin.jar" test.java
// since jaws.jar does not exsist anymore
// to compile with jaws: javac -classpath "C:\j2sdk1.4.0_03\jre\lib\jaws.jar" test.java
import netscape.javascript.*;
public class test extends java.applet.Applet {
	JSObject win;
	JSObject outputLabel;
	boolean buttonFromJavaClicked = false;
	checkJavaScriptEvent evt = new checkJavaScriptEvent();
	public void init() {
		try {
			evt.start();
			win = JSObject.getWindow(this);
			outputLabel =
				(JSObject) win.eval("document.getElementById('lblOutputText')");
			outputLabel.setMember(
				"innerHTML",
				"<center><h1>From Init<br>Your homedir "
					+ System.getProperty("user.home")
					+ "</h1></center>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fromJavaScript() {
		buttonFromJavaClicked = true;
	}
 
	private void fromJavaScript2() {
		System.out.println("fromjavascript2 is started");
		try {
			String strLbl =
				"<center><h1>From javascript<br>Your homedir: "
					+ System.getProperty("user.home")
					+ "</h1></center>";
			outputLabel.setMember("innerHTML", strLbl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	class checkJavaScriptEvent extends Thread {
		public void run() {
			while (true) {
				if (test.this.buttonFromJavaClicked) {
					System.out.println("OK buttonfromjava is true");
					test.this.buttonFromJavaClicked = false;
					test.this.fromJavaScript2();
				}
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					System.out.println("exception in sleep");
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
	}
 
}
