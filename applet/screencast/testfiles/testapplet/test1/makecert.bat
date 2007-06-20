set JAVA_HOME="C:\Programme\Java\jdk1.5.0_11"
set PATH=%PATH%;%JAVA_HOME%\bin;
keytool -genkey -keystore harm.com -keyalg rsa -dname "CN=Sebastian Wagner, OU=Technology, O=org, L=Pforzheim, ST=, C=DE" -alias harm -validity 3600 -keypass password -storepass password
jarsigner -keystore harm.cert -storepass password -keypass password -signedjar xmlcrm-applet.jar harm 