keytool -genkey -keystore screeencast.keystore -keyalg rsa -dname "CN=Sebastian Wagner, OU=Technology, O=org, L=pforzheim, ST=, C=DE" -alias screencastAlias
jarsigner -keystore screeencast.keystore screencast.jar screencastAlias 
jarsigner -keystore screeencast.keystore customizer.jar screencastAlias 
jarsigner -keystore screeencast.keystore jmf.jar screencastAlias 
jarsigner -keystore screeencast.keystore mediaplayer.jar screencastAlias 
jarsigner -keystore screeencast.keystore multiplayer.jar screencastAlias 