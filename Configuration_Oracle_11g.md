## Database Setup ##

Get JDBC Driver from: http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-112010-090769.html

Place ojdbc6.jar into webapps/openmeetings/WEB-INF/lib/


## DB2 Sample Configuration ##

Copy webapps/openmeetings/WEB-INF/classes/oracle\_persistance.xml<br />
to webapps/openmeetings/WEB-INF/classes/persistance.xml

File for reference in the SVN:
http://code.google.com/p/openmeetings/source/browse/trunk/singlewebapp/src/META-INF/oracle_persistance.xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- persistence.xml schema -->
<persistence xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd" version="2.0">
	<persistence-unit name="openmeetings" transaction-type="RESOURCE_LOCAL">
 		<provider>org.apache.openjpa.persistence.PersistenceProviderImpl</provider>
		<class>org.openmeetings.app.persistence.beans.adresses.Adresses</class>
		<class>org.openmeetings.app.persistence.beans.adresses.States</class>
		<class>org.openmeetings.app.persistence.beans.basic.Configuration</class>
		<class>org.openmeetings.app.persistence.beans.basic.ErrorType</class>
		<class>org.openmeetings.app.persistence.beans.basic.ErrorValues</class>
		<class>org.openmeetings.app.persistence.beans.basic.LdapConfig</class>
		<class>org.openmeetings.app.persistence.beans.basic.Naviglobal</class>
		<class>org.openmeetings.app.persistence.beans.basic.Navimain</class>
		<class>org.openmeetings.app.persistence.beans.basic.Navisub</class>
		<class>org.openmeetings.app.persistence.beans.basic.OmTimeZone</class>
		<class>org.openmeetings.app.persistence.beans.basic.Sessiondata</class>
		<class>org.openmeetings.app.persistence.beans.basic.SOAPLogin</class>
		<class>org.openmeetings.app.persistence.beans.calendar.Appointment</class>
		<class>org.openmeetings.app.persistence.beans.calendar.AppointmentCategory</class>
		<class>org.openmeetings.app.persistence.beans.calendar.AppointmentReminderTyps</class>
		<class>org.openmeetings.app.persistence.beans.calendar.MeetingMember</class>
		<class>org.openmeetings.app.persistence.beans.domain.Organisation</class>
		<class>org.openmeetings.app.persistence.beans.domain.Organisation_Users</class>
		<class>org.openmeetings.app.persistence.beans.flvrecord.FlvRecording</class>
		<class>org.openmeetings.app.persistence.beans.flvrecord.FlvRecordingLog</class>
		<class>org.openmeetings.app.persistence.beans.flvrecord.FlvRecordingMetaData</class>
		<class>org.openmeetings.app.persistence.beans.flvrecord.FlvRecordingMetaDelta</class>
		<class>org.openmeetings.app.persistence.beans.invitation.Invitations</class>
		<class>org.openmeetings.app.persistence.beans.lang.FieldLanguage</class>
		<class>org.openmeetings.app.persistence.beans.lang.Fieldlanguagesvalues</class>
		<class>org.openmeetings.app.persistence.beans.lang.Fieldvalues</class>
		<class>org.openmeetings.app.persistence.beans.logs.ConferenceLog</class>
		<class>org.openmeetings.app.persistence.beans.logs.ConferenceLogType</class>
		<class>org.openmeetings.app.persistence.beans.recording.ChatvaluesEvent</class>
		<class>org.openmeetings.app.persistence.beans.recording.Recording</class>
		<class>org.openmeetings.app.persistence.beans.recording.RecordingClient</class>
		<class>org.openmeetings.app.persistence.beans.recording.RecordingConversionJob</class>
		<class>org.openmeetings.app.persistence.beans.recording.RoomClient</class>
		<class>org.openmeetings.app.persistence.beans.recording.RoomRecording</class>
		<class>org.openmeetings.app.persistence.beans.recording.RoomStream</class>
		<class>org.openmeetings.app.persistence.beans.recording.WhiteBoardEvent</class>
		<class>org.openmeetings.app.persistence.beans.rooms.RoomModerators</class>
		<class>org.openmeetings.app.persistence.beans.rooms.Rooms</class>
		<class>org.openmeetings.app.persistence.beans.rooms.RoomTypes</class>
		<class>org.openmeetings.app.persistence.beans.rooms.Rooms_Organisation</class>
		<class>org.openmeetings.app.persistence.beans.sip.OpenXGReturnObject</class>
		<class>org.openmeetings.app.persistence.beans.user.PrivateMessages</class>
		<class>org.openmeetings.app.persistence.beans.user.PrivateMessageFolder</class>
		<class>org.openmeetings.app.persistence.beans.user.Salutations</class>
		<class>org.openmeetings.app.persistence.beans.user.UserContacts</class>
		<class>org.openmeetings.app.persistence.beans.user.Userdata</class>
		<class>org.openmeetings.app.persistence.beans.user.Usergroups</class>
		<class>org.openmeetings.app.persistence.beans.user.Userlevel</class>
		<class>org.openmeetings.app.persistence.beans.user.Users_Usergroups</class>
		<class>org.openmeetings.app.persistence.beans.user.UserSipData</class>
		<class>org.openmeetings.app.persistence.beans.user.Users</class>
		<class>org.openmeetings.app.persistence.beans.files.FileExplorerItem</class>
		<exclude-unlisted-classes>false</exclude-unlisted-classes>
		<properties>
				<property name="openjpa.RuntimeUnenhancedClasses" value="supported"/>
				<property name="openjpa.ConnectionDriverName" value="oracle.jdbc.driver.OracleDriver"/>
                               <property name="openjpa.ConnectionProperties" value="username=SYSTEM, password=admin"/>
                               <property name="openjpa.ConnectionURL" value="jdbc:oracle:thin:@localhost:1521:openmeetings"/>
				<property name="openjpa.jdbc.SynchronizeMappings" value="buildSchema"/>
      			<property name="openjpa.Log" value="DefaultLevel=WARN, Tool=INFO" />
				<property name="openjpa.DataCache" value="true"/>
				<property name="openjpa.QueryCache" value="true"/> 
				<property name="openjpa.jdbc.DBDictionary"  value="batchLimit=100"/> 
				<property name="openjpa.jdbc.QuerySQLCache"  value="false"/> 
    	</properties>
	</persistence-unit>
</persistence>
}}}>```