USE xmlcrm;


/************ Update: Tables ***************/

/******************** Add Table: adresses ************************/

/* Build Table Structure */
CREATE TABLE adresses
(
	additionalname TEXT NULL,
	adresses_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('adresses_ADRESSES_ID_seq'::text)',
	`comment` TEXT NULL,
	fax VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	state_id BIGINT NOT NULL,
	street VARCHAR(255) NOT NULL,
	town VARCHAR(255) NOT NULL,
	updatetime DATETIME NULL,
	zip VARCHAR(255) NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: adresses_emails ************************/

/* Build Table Structure */
CREATE TABLE adresses_emails
(
	adresses_emails_id BIGINT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('adresses_emails_ADRESSES_EMAILS_ID_seq'::text)',
	adresses_id BIGINT NOT NULL,
	mail_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: adresses_phones ************************/

/* Build Table Structure */
CREATE TABLE adresses_phones
(
	adresses_id BIGINT NOT NULL,
	adresses_phone_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('adresses_phones_ADRESSES_PHONE_ID_seq'::text)',
	phone_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: articlegroups ************************/

/* Build Table Structure */
CREATE TABLE articlegroups
(
	articlegroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('articlegroups_ARTICLEGROUP_ID_seq'::text)',
	name VARCHAR(255) NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/* Table Items: articlegroups */

/* Add Indexes for: articlegroups */
CREATE UNIQUE INDEX articlegroups_articlegroup_id_idx ON articlegroups (articlegroup_id);

/******************** Add Table: articles ************************/

/* Build Table Structure */
CREATE TABLE articles
(
	article_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('articles_ARTICLE_ID_seq'::text)',
	description TEXT NULL,
	icon_path TEXT NULL,
	image_path TEXT NULL,
	name VARCHAR(255) NOT NULL,
	price FLOAT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	supplier_id BIGINT NULL,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/* Table Items: articles */

/* Add Indexes for: articles */
CREATE UNIQUE INDEX articles_article_id_idx ON articles (article_id);

/******************** Add Table: articles_articlegroups ************************/

/* Build Table Structure */
CREATE TABLE articles_articlegroups
(
	article_id BIGINT NOT NULL,
	articlegroup_id BIGINT NOT NULL,
	articles_articlesgroups_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('articles_articlegroups_ARTICLES_ARTICLESGROUPS_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/* Table Items: articles_articlegroups */

/* Add Indexes for: articles_articlegroups */
CREATE UNIQUE INDEX articles_articlegroups_articles_articlesgroups_id_idx ON articles_articlegroups (articles_articlesgroups_id);

/******************** Add Table: articles_lieferarten ************************/

/* Build Table Structure */
CREATE TABLE articles_lieferarten
(
	articles_id BIGINT NOT NULL,
	articles_lieferarten_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('articles_lieferarten_ARTICLES_LIEFERARTEN_ID_seq'::text)',
	liefer_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: configuration ************************/

/* Build Table Structure */
CREATE TABLE configuration
(
	`comment` TEXT NULL,
	conf_key VARCHAR(255) NOT NULL,
	conf_value VARCHAR(255) NOT NULL,
	configuration_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('configuration_CONFIGURATION_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	user_id BIGINT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/* Table Items: configuration */

/* Add Indexes for: configuration */
CREATE UNIQUE INDEX configuration_configuration_id_idx ON configuration (configuration_id);

/******************** Add Table: contactfreigabe ************************/

/* Build Table Structure */
CREATE TABLE contactfreigabe
(
	`comment` TEXT NULL,
	description TEXT NULL,
	freigabe_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('contactfreigabe_FREIGABE_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: contactgroups ************************/

/* Build Table Structure */
CREATE TABLE contactgroups
(
	`comment` TEXT NULL,
	contactgroup_id BIGINT NOT NULL DEFAULT 0,
	description TEXT NULL,
	freigabe_id BIGINT NOT NULL,
	name TEXT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/* Table Items: contactgroups */
ALTER TABLE contactgroups ADD CONSTRAINT pkcontactgroups
	PRIMARY KEY (contactgroup_id);

/******************** Add Table: contactgroups_contacts ************************/

/* Build Table Structure */
CREATE TABLE contactgroups_contacts
(
	contact_id BIGINT NOT NULL,
	contactgroup_id BIGINT NOT NULL,
	contactgroups_contact_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('contactgroups_contacts_CONTACTGROUPS_CONTACT_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: contactgroups_conuser ************************/

/* Build Table Structure */
CREATE TABLE contactgroups_conuser
(
	contactgroup_id BIGINT NOT NULL,
	contactgroup_user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('contactgroups_conuser_CONTACTGROUP_USER_ID_seq'::text)',
	conuser_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: contacts ************************/

/* Build Table Structure */
CREATE TABLE contacts
(
	adresses_id BIGINT NOT NULL,
	age BIGINT NOT NULL,
	`comment` TEXT NOT NULL,
	contact_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('contacts_CONTACT_ID_seq'::text)',
	firstname VARCHAR(64) NULL,
	freigabe_id BIGINT NOT NULL DEFAULT 0,
	lastname VARCHAR(64) NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	titel_id BIGINT NOT NULL,
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: conuser ************************/

/* Build Table Structure */
CREATE TABLE conuser
(
	conuser_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('conuser_CONUSER_ID_seq'::text)',
	freigabe_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: emails ************************/

/* Build Table Structure */
CREATE TABLE emails
(
	`comment` TEXT NOT NULL,
	email VARCHAR(128) NOT NULL,
	mail_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('emails_MAIL_ID_seq'::text)',
	startdate DATETIME NOT NULL DEFAULT 'NOW()',
	updatedate DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: employeegroups ************************/

/* Build Table Structure */
CREATE TABLE employeegroups
(
	`comment` TEXT NOT NULL,
	employeegroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('employeegroups_EMPLOYEEGROUP_ID_seq'::text)',
	name TEXT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: employees ************************/

/* Build Table Structure */
CREATE TABLE employees
(
	`comment` TEXT NOT NULL,
	employee_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('employees_EMPLOYEE_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL,
	deleted VARCHAR(255) NULL
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: employees_articlegroups ************************/

/* Build Table Structure */
CREATE TABLE employees_articlegroups
(
	articlegroup_id BIGINT NOT NULL,
	employee_id BIGINT NOT NULL,
	employees_articlegroups_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('employees_articlegroups_EMPLOYEES_ARTICLEGROUPS_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: employees_articles ************************/

/* Build Table Structure */
CREATE TABLE employees_articles
(
	article_id BIGINT NOT NULL,
	employee_id BIGINT NOT NULL,
	employees_articles_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('employees_articles_EMPLOYEES_ARTICLES_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: employees_employeegroups ************************/

/* Build Table Structure */
CREATE TABLE employees_employeegroups
(
	employee_employeegroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('employees_employeegroups_EMPLOYEE_EMPLOYEEGROUP_ID_seq'::text)',
	employee_id BIGINT NOT NULL,
	employeegroup_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: employees_suppliergroups ************************/

/* Build Table Structure */
CREATE TABLE employees_suppliergroups
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	employee_id BIGINT NOT NULL,
	employees_suppliergroups_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	suppliergroup_id BIGINT NOT NULL,
	updatetime DATETIME NULL
) TYPE=InnoDB;

/******************** Add Table: employees_suppliers ************************/

/* Build Table Structure */
CREATE TABLE employees_suppliers
(
	employee_id BIGINT NOT NULL,
	employees_suplier_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('employees_suppliers_EMPLOYEES_SUPLIER_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	supplier_id BIGINT NOT NULL,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: fieldlanguage ************************/

/* Build Table Structure */
CREATE TABLE fieldlanguage
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	language_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL
) TYPE=InnoDB;

/******************** Add Table: fieldlanguagesvalues ************************/

/* Build Table Structure */
CREATE TABLE fieldlanguagesvalues
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	fieldlanguagesvalues_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fieldvalues_id INTEGER NOT NULL,
	language_id INTEGER NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	value VARCHAR(255) NOT NULL
) TYPE=InnoDB;

/******************** Add Table: fieldvalues ************************/

/* Build Table Structure */
CREATE TABLE fieldvalues
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	fieldvalues_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL
) TYPE=InnoDB;

/******************** Add Table: lieferarten ************************/

/* Build Table Structure */
CREATE TABLE lieferarten
(
	beschreibung TEXT NOT NULL,
	`comment` TEXT NOT NULL,
	freigeschalten BIGINT NOT NULL DEFAULT 0,
	liefer_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('lieferarten_LIEFER_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: naviglobal ************************/

/* Build Table Structure */
CREATE TABLE naviglobal
(
	naviorder BIGINT NOT NULL DEFAULT 0,
	`action` VARCHAR(64) NOT NULL,
	`comment` TEXT NOT NULL,
	global_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('naviglobal_GLOBAL_ID_seq'::text)',
	icon VARCHAR(64) NOT NULL,
	isleaf TINYINT NOT NULL,
	isopen TINYINT NOT NULL,
	updatetime DATETIME NULL,
	level_id INTEGER NOT NULL DEFAULT 0,
	name VARCHAR(64) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	fieldvalues_id INTEGER NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: navimain ************************/

/* Build Table Structure */
CREATE TABLE navimain
(
	naviorder BIGINT NOT NULL DEFAULT 0,
	`action` VARCHAR(64) NOT NULL,
	`comment` TEXT NOT NULL,
	global_id INTEGER NOT NULL DEFAULT 0,
	icon VARCHAR(64) NOT NULL,
	isleaf TINYINT NOT NULL,
	isopen TINYINT NOT NULL,
	updatetime DATETIME NULL,
	level_id INTEGER NOT NULL DEFAULT 0,
	main_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('navimain_MAIN_ID_seq'::text)',
	name VARCHAR(64) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	fieldvalues_id INTEGER NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: navisub ************************/

/* Build Table Structure */
CREATE TABLE navisub
(
	naviorder BIGINT NOT NULL DEFAULT 0,
	`action` VARCHAR(64) NOT NULL,
	`comment` TEXT NOT NULL,
	icon VARCHAR(64) NOT NULL,
	isleaf TINYINT NOT NULL,
	isopen TINYINT NOT NULL,
	updatetime DATETIME NULL,
	level_id BIGINT NOT NULL DEFAULT 0,
	main_id BIGINT NOT NULL DEFAULT 0,
	name VARCHAR(64) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	sub_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('navisub_SUB_ID_seq'::text)',
	fieldvalues_id INTEGER NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: organisation ************************/

/* Build Table Structure */
CREATE TABLE organisation
(
	deleted VARCHAR(255) NULL,
	insertedby BIGINT NULL,
	name VARCHAR(255) NOT NULL,
	organisation_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatedby BIGINT NULL,
	updatetime DATETIME NULL
) TYPE=InnoDB;

/******************** Add Table: organisation_users ************************/

/* Build Table Structure */
CREATE TABLE organisation_users
(
	`comment` VARCHAR(255) NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false',
	organisation_id INTEGER NOT NULL,
	organisation_users_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL
) TYPE=InnoDB;

/******************** Add Table: phones ************************/

/* Build Table Structure */
CREATE TABLE phones
(
	`comment` TEXT NOT NULL,
	phone_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('phones_PHONE_ID_seq'::text)',
	phonevalue VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: rooms ************************/

/* Build Table Structure */
CREATE TABLE rooms
(
	`comment` TEXT NULL,
	deleted VARCHAR(255) NULL,
	ispublic TINYINT NULL DEFAULT 'true',
	name VARCHAR(255) NOT NULL,
	rooms_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	roomtypes_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL
) TYPE=InnoDB;

/******************** Add Table: rooms_organisation ************************/

/* Build Table Structure */
CREATE TABLE rooms_organisation
(
	deleted VARCHAR(255) NULL,
	organisation_id BIGINT NOT NULL,
	rooms_id BIGINT NOT NULL,
	rooms_organisation_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL
) TYPE=InnoDB;

/******************** Add Table: roomtypes ************************/

/* Build Table Structure */
CREATE TABLE roomtypes
(
	deleted VARCHAR(255) NULL,
	name VARCHAR(255) NOT NULL,
	roomtypes_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL
) TYPE=InnoDB;

/******************** Add Table: salutations ************************/

/* Build Table Structure */
CREATE TABLE salutations
(
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	salutations_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('salutations_salutations_id_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: sessiondata ************************/

/* Build Table Structure */
CREATE TABLE sessiondata
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('sessiondata_ID_seq'::text)',
	refresh_time DATETIME NOT NULL DEFAULT 'NOW()',
	session_id VARCHAR(64) NOT NULL,
	starttermin_time DATETIME NOT NULL DEFAULT 'NOW()',
	user_id INTEGER NULL DEFAULT 0
) WITH OIDS;

/******************** Add Table: states ************************/

/* Build Table Structure */
CREATE TABLE states
(
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	state_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('states_STATE_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: suppliergroups ************************/

/* Build Table Structure */
CREATE TABLE suppliergroups
(
	description TEXT NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	suppliergroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('suppliergroups_SUPPLIERGROUP_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: suppliers ************************/

/* Build Table Structure */
CREATE TABLE suppliers
(
	adresses_id INTEGER NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	supplier_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('suppliers_SUPPLIER_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: suppliers_suppliergroups ************************/

/* Build Table Structure */
CREATE TABLE suppliers_suppliergroups
(
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	supplier_id BIGINT NOT NULL,
	supplier_suppliergroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('suppliers_suppliergroups_SUPPLIER_SUPPLIERGROUP_ID_seq'::text)',
	suppliergroup_id BIGINT NOT NULL,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: termine ************************/

/* Build Table Structure */
CREATE TABLE termine
(
	`comment` TEXT NOT NULL,
	description TEXT NOT NULL,
	endtermin DATETIME NOT NULL DEFAULT 'NOW()',
	open INTEGER NOT NULL DEFAULT 0,
	owner_id BIGINT NULL DEFAULT 0,
	place TEXT NOT NULL,
	starttermin DATETIME NOT NULL DEFAULT 'NOW()',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	status_id BIGINT NULL DEFAULT 0,
	termin_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('termine_TERMIN_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: termine_participant ************************/

/* Build Table Structure */
CREATE TABLE termine_participant
(
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	termin_id BIGINT NOT NULL,
	termine_participant_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('termine_participant_TERMINE_PARTICIPANT_ID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: termine_todo_user ************************/

/* Build Table Structure */
CREATE TABLE termine_todo_user
(
	`comment` TEXT NOT NULL,
	owner_id BIGINT NULL DEFAULT 0,
	priority INTEGER NOT NULL DEFAULT 0,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	todo_id BIGINT NOT NULL DEFAULT 0,
	termine_todo_user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('termine_todo_user_UID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: termine_todolist ************************/

/* Build Table Structure */
CREATE TABLE termine_todolist
(
	`comment` TEXT NOT NULL,
	description TEXT NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	status_id BIGINT NOT NULL DEFAULT 0,
	teilnehmer TEXT NOT NULL,
	termine_todolist_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('termine_todolist_TODO_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: termine_user ************************/

/* Build Table Structure */
CREATE TABLE termine_user
(
	`comment` TEXT NOT NULL,
	endtermin DATETIME NOT NULL DEFAULT 'NOW()',
	invitor_id BIGINT NULL DEFAULT 0,
	message TEXT NOT NULL,
	starttermin DATETIME NOT NULL DEFAULT 'NOW()',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	termin_id BIGINT NULL DEFAULT 0,
	terminstatus INTEGER NOT NULL DEFAULT 0,
	termine_user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('termine_user_UID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: terminegroups ************************/

/* Build Table Structure */
CREATE TABLE terminegroups
(
	`comment` TEXT NULL,
	endtermin DATETIME NOT NULL DEFAULT 'NOW()',
	invitor_id BIGINT NULL DEFAULT 0,
	message TEXT NULL,
	starttermin DATETIME NOT NULL DEFAULT 'NOW()',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	termin_id BIGINT NOT NULL DEFAULT 0,
	terminegroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	status_id BIGINT NULL DEFAULT 0,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: terminestatus ************************/

/* Build Table Structure */
CREATE TABLE terminestatus
(
	`comment` TEXT NULL,
	description TEXT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	status_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('terminestatus_STATUS_ID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: transstatus ************************/

/* Build Table Structure */
CREATE TABLE transstatus
(
	`comment` TEXT NOT NULL,
	description TEXT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	status_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('transstatus_STATUS_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: userdata ************************/

/* Build Table Structure */
CREATE TABLE userdata
(
	`comment` TEXT NULL,
	data TEXT NOT NULL,
	data_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('userdata_DATA_ID_seq'::text)',
	data_key VARCHAR(128) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: usergroups ************************/

/* Build Table Structure */
CREATE TABLE usergroups
(
	level_id BIGINT NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	usergroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('usergroups_USERGROUP_ID_seq'::text)',
	user_id BIGINT NULL,
	description CHAR(255) NULL,
	`comment` CHAR(255) NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: userlevel ************************/

/* Build Table Structure */
CREATE TABLE userlevel
(
	description TEXT NULL,
	level_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('userlevel_LEVEL_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	statuscode INTEGER NOT NULL DEFAULT 0,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: users ************************/

/* Build Table Structure */
CREATE TABLE users
(
	adresses_id BIGINT NOT NULL,
	age DATETIME NULL,
	availible INTEGER NOT NULL DEFAULT 0,
	firstname VARCHAR(32) NOT NULL,
	lastlogin DATETIME NULL,
	lastname VARCHAR(32) NOT NULL,
	lasttrans BIGINT NOT NULL DEFAULT 0,
	level_id BIGINT NOT NULL DEFAULT 0,
	login VARCHAR(128) NOT NULL,
	password VARCHAR(64) NOT NULL,
	regdate DATETIME NOT NULL DEFAULT 'NOW()',
	status INTEGER NOT NULL DEFAULT 0,
	title_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('users_USER_ID_seq'::text)',
	delivery_adress_id BIGINT NULL,
	domicile_adress_id BIGINT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	language_id INTEGER NULL,
	pictureuri VARCHAR(255) NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: users_usergroups ************************/

/* Build Table Structure */
CREATE TABLE users_usergroups
(
	`comment` TEXT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	users_usergroup_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('users_usergroups_UID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	usergroup_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: userwaren ************************/

/* Build Table Structure */
CREATE TABLE userwaren
(
	article_id BIGINT NOT NULL DEFAULT 0,
	`comment` TEXT NOT NULL,
	liefer_id BIGINT NULL DEFAULT 0,
	menge BIGINT NOT NULL DEFAULT 0,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	status BIGINT NOT NULL DEFAULT 0,
	status_id BIGINT NULL,
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	userwaren_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('userwaren_USERWAREN_ID_seq'::text)',
	zahlungs_id BIGINT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;

/******************** Add Table: zahlungsarten ************************/

/* Build Table Structure */
CREATE TABLE zahlungsarten
(
	beschreibung TEXT NOT NULL,
	`comment` TEXT NOT NULL,
	freigeschalten INTEGER NOT NULL DEFAULT 0,
	starttime DATETIME NOT NULL DEFAULT 'NOW()',
	updatetime DATETIME NULL,
	zahlungs_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 'nextval('zahlungsarten_ZAHLUNGS_ID_seq'::text)',
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS TYPE=InnoDB;


/************ Add Foreign Keys to Database ***************/
/*-----------------------------------------------------------
Warning: Versions of MySQL prior to 4.1.2 require indexes on all columns involved in a foreign key. The following indexes may be required: 
fk_adresses_states may require an index on table: adresses, column: state_id
fk_adresses_emails_adresses may require an index on table: adresses_emails, column: adresses_id
fk_adresses_emails_emails may require an index on table: adresses_emails, column: mail_id
fk_adresses_phones_adresses may require an index on table: adresses_phones, column: adresses_id
fk_adresses_phones_phones may require an index on table: adresses_phones, column: phone_id
fk_articles_suppliers may require an index on table: articles, column: supplier_id
fk_articles_articlegroups_articlegroups may require an index on table: articles_articlegroups, column: articlegroup_id
fk_articles_articlegroups_articles may require an index on table: articles_articlegroups, column: article_id
fk_articles_lieferarten_articles may require an index on table: articles_lieferarten, column: articles_id
fk_articles_lieferarten_lieferarten may require an index on table: articles_lieferarten, column: liefer_id
fk_configuration_users may require an index on table: configuration, column: user_id
fk_contactgroups_contactfreigabe may require an index on table: contactgroups, column: freigabe_id
fk_contactgroups_contacts_contactgroups may require an index on table: contactgroups_contacts, column: contactgroup_id
fk_contactgroups_contacts_contacts may require an index on table: contactgroups_contacts, column: contact_id
fk_contactgroups_conuser_contactgroups may require an index on table: contactgroups_conuser, column: contactgroup_id
fk_contactgroups_conuser_conuser may require an index on table: contactgroups_conuser, column: conuser_id
fk_contacts_adresses may require an index on table: contacts, column: adresses_id
fk_contacts_contactfreigabe may require an index on table: contacts, column: freigabe_id
fk_contacts_titles may require an index on table: contacts, column: titel_id
fk_contacts_users may require an index on table: contacts, column: user_id
fk_conuser_contactfreigabe may require an index on table: conuser, column: freigabe_id
fk_conuser_users may require an index on table: conuser, column: user_id
fk_employees_users may require an index on table: employees, column: user_id
fk_employees_articlegroups_articlegroups may require an index on table: employees_articlegroups, column: articlegroup_id
fk_employees_articlegroups_employees may require an index on table: employees_articlegroups, column: employee_id
fk_employees_articles_articles may require an index on table: employees_articles, column: article_id
fk_employees_articles_employees may require an index on table: employees_articles, column: employee_id
fk_employees_employeegroups_employeegroups may require an index on table: employees_employeegroups, column: employeegroup_id
fk_employees_employeegroups_employees may require an index on table: employees_employeegroups, column: employee_id
fk_employees_suppliergroups_employees may require an index on table: employees_suppliergroups, column: employee_id
fk_employees_suppliers_employees may require an index on table: employees_suppliers, column: employee_id
fk_employees_suppliers_suppliers may require an index on table: employees_suppliers, column: supplier_id
fk_fieldvalues_fieldlanguagesvalues_fieldlanguage may require an index on table: fieldlanguagesvalues, column: language_id
fk_fieldvalues_fieldlanguagesvalues_fieldvalues may require an index on table: fieldlanguagesvalues, column: fieldvalues_id
fk_naviglobal_fieldvalues may require an index on table: naviglobal, column: fieldvalues_id
fk_naviglobal_userlevel may require an index on table: naviglobal, column: level_id
fk_navimain_fieldvalues may require an index on table: navimain, column: fieldvalues_id
fk_navimain_naviglobal may require an index on table: navimain, column: global_id
fk_navimain_userlevel may require an index on table: navimain, column: level_id
fk_navisub_fieldvalues may require an index on table: navisub, column: fieldvalues_id
fk_navisub_navimain may require an index on table: navisub, column: main_id
fk_navisub_userlevel may require an index on table: navisub, column: level_id
fk_organisation_users_organisation may require an index on table: organisation_users, column: organisation_id
fk_organisation_users_users may require an index on table: organisation_users, column: user_id
fk_rooms_roomtypes may require an index on table: rooms, column: roomtypes_id
fk_rooms_organisation_organisation may require an index on table: rooms_organisation, column: organisation_id
fk_rooms_organisation_rooms may require an index on table: rooms_organisation, column: rooms_id
fk_suppliers_adresses may require an index on table: suppliers, column: adresses_id
fk_suppliers_suppliergroups_suppliergroups may require an index on table: suppliers_suppliergroups, column: suppliergroup_id
fk_suppliers_suppliergroups_suppliers may require an index on table: suppliers_suppliergroups, column: supplier_id
fk_termine_terminestatus may require an index on table: termine, column: status_id
fk_termine_users may require an index on table: termine, column: owner_id
fk_termine_participant_termine may require an index on table: termine_participant, column: termin_id
fk_termine_participant_users may require an index on table: termine_participant, column: user_id
fk_termine_todo_user_owner_users may require an index on table: termine_todo_user, column: owner_id
fk_termine_todo_user_termine_todolist may require an index on table: termine_todo_user, column: todo_id
fk_termine_todo_user_users may require an index on table: termine_todo_user, column: user_id
fk_termine_user_termine may require an index on table: termine_user, column: termin_id
fk_termine_user_users may require an index on table: termine_user, column: user_id
fk_terminegroups_termine may require an index on table: terminegroups, column: termin_id
fk_terminegroups_terminestatus may require an index on table: terminegroups, column: status_id
fk_userdata_users may require an index on table: userdata, column: user_id
fk_usergroups_userlevel may require an index on table: usergroups, column: level_id
fk_users_adresses may require an index on table: users, column: adresses_id
fk_users_delivery_adresses may require an index on table: users, column: delivery_adress_id
fk_users_domicile_adresses may require an index on table: users, column: domicile_adress_id
fk_users_fieldlanguage may require an index on table: users, column: language_id
fk_users_titles may require an index on table: users, column: title_id
fk_users_userlevel may require an index on table: users, column: level_id
fk_usergroups_users may require an index on table: users_usergroups, column: user_id
fk_users_usergroups_usergroups may require an index on table: users_usergroups, column: usergroup_id
fk_userwaren_articles may require an index on table: userwaren, column: article_id
fk_userwaren_lieferarten may require an index on table: userwaren, column: liefer_id
fk_userwaren_transstatus may require an index on table: userwaren, column: status_id
fk_userwaren_users may require an index on table: userwaren, column: user_id
fk_userwaren_zahlungsarten may require an index on table: userwaren, column: zahlungs_id
-----------------------------------------------------------
*/

/************ Foreign Key: fk_adresses_states ***************/
ALTER TABLE adresses ADD CONSTRAINT fk_adresses_states
	FOREIGN KEY (state_id) REFERENCES states (state_id);

/************ Foreign Key: fk_adresses_emails_adresses ***************/
ALTER TABLE adresses_emails ADD CONSTRAINT fk_adresses_emails_adresses
	FOREIGN KEY (adresses_id) REFERENCES adresses (adresses_id);

/************ Foreign Key: fk_adresses_emails_emails ***************/
ALTER TABLE adresses_emails ADD CONSTRAINT fk_adresses_emails_emails
	FOREIGN KEY (mail_id) REFERENCES emails (mail_id);

/************ Foreign Key: fk_adresses_phones_adresses ***************/
ALTER TABLE adresses_phones ADD CONSTRAINT fk_adresses_phones_adresses
	FOREIGN KEY (adresses_id) REFERENCES adresses (adresses_id);

/************ Foreign Key: fk_adresses_phones_phones ***************/
ALTER TABLE adresses_phones ADD CONSTRAINT fk_adresses_phones_phones
	FOREIGN KEY (phone_id) REFERENCES phones (phone_id);

/************ Foreign Key: fk_articles_suppliers ***************/
ALTER TABLE articles ADD CONSTRAINT fk_articles_suppliers
	FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id);

/************ Foreign Key: fk_articles_articlegroups_articlegroups ***************/
ALTER TABLE articles_articlegroups ADD CONSTRAINT fk_articles_articlegroups_articlegroups
	FOREIGN KEY (articlegroup_id) REFERENCES articlegroups (articlegroup_id);

/************ Foreign Key: fk_articles_articlegroups_articles ***************/
ALTER TABLE articles_articlegroups ADD CONSTRAINT fk_articles_articlegroups_articles
	FOREIGN KEY (article_id) REFERENCES articles (article_id);

/************ Foreign Key: fk_articles_lieferarten_articles ***************/
ALTER TABLE articles_lieferarten ADD CONSTRAINT fk_articles_lieferarten_articles
	FOREIGN KEY (articles_id) REFERENCES articles (article_id);

/************ Foreign Key: fk_articles_lieferarten_lieferarten ***************/
ALTER TABLE articles_lieferarten ADD CONSTRAINT fk_articles_lieferarten_lieferarten
	FOREIGN KEY (liefer_id) REFERENCES lieferarten (liefer_id);

/************ Foreign Key: fk_configuration_users ***************/
ALTER TABLE configuration ADD CONSTRAINT fk_configuration_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_contactgroups_contactfreigabe ***************/
ALTER TABLE contactgroups ADD CONSTRAINT fk_contactgroups_contactfreigabe
	FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe (freigabe_id);

/************ Foreign Key: fk_contactgroups_contacts_contactgroups ***************/
ALTER TABLE contactgroups_contacts ADD CONSTRAINT fk_contactgroups_contacts_contactgroups
	FOREIGN KEY (contactgroup_id) REFERENCES contactgroups (contactgroup_id);

/************ Foreign Key: fk_contactgroups_contacts_contacts ***************/
ALTER TABLE contactgroups_contacts ADD CONSTRAINT fk_contactgroups_contacts_contacts
	FOREIGN KEY (contact_id) REFERENCES contacts (contact_id);

/************ Foreign Key: fk_contactgroups_conuser_contactgroups ***************/
ALTER TABLE contactgroups_conuser ADD CONSTRAINT fk_contactgroups_conuser_contactgroups
	FOREIGN KEY (contactgroup_id) REFERENCES contactgroups (contactgroup_id);

/************ Foreign Key: fk_contactgroups_conuser_conuser ***************/
ALTER TABLE contactgroups_conuser ADD CONSTRAINT fk_contactgroups_conuser_conuser
	FOREIGN KEY (conuser_id) REFERENCES conuser (conuser_id);

/************ Foreign Key: fk_contacts_adresses ***************/
ALTER TABLE contacts ADD CONSTRAINT fk_contacts_adresses
	FOREIGN KEY (adresses_id) REFERENCES adresses (adresses_id);

/************ Foreign Key: fk_contacts_contactfreigabe ***************/
ALTER TABLE contacts ADD CONSTRAINT fk_contacts_contactfreigabe
	FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe (freigabe_id);

/************ Foreign Key: fk_contacts_titles ***************/
ALTER TABLE contacts ADD CONSTRAINT fk_contacts_titles
	FOREIGN KEY (titel_id) REFERENCES salutations (salutations_id);

/************ Foreign Key: fk_contacts_users ***************/
ALTER TABLE contacts ADD CONSTRAINT fk_contacts_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_conuser_contactfreigabe ***************/
ALTER TABLE conuser ADD CONSTRAINT fk_conuser_contactfreigabe
	FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe (freigabe_id);

/************ Foreign Key: fk_conuser_users ***************/
ALTER TABLE conuser ADD CONSTRAINT fk_conuser_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_employees_users ***************/
ALTER TABLE employees ADD CONSTRAINT fk_employees_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_employees_articlegroups_articlegroups ***************/
ALTER TABLE employees_articlegroups ADD CONSTRAINT fk_employees_articlegroups_articlegroups
	FOREIGN KEY (articlegroup_id) REFERENCES articlegroups (articlegroup_id);

/************ Foreign Key: fk_employees_articlegroups_employees ***************/
ALTER TABLE employees_articlegroups ADD CONSTRAINT fk_employees_articlegroups_employees
	FOREIGN KEY (employee_id) REFERENCES employees (employee_id);

/************ Foreign Key: fk_employees_articles_articles ***************/
ALTER TABLE employees_articles ADD CONSTRAINT fk_employees_articles_articles
	FOREIGN KEY (article_id) REFERENCES articles (article_id);

/************ Foreign Key: fk_employees_articles_employees ***************/
ALTER TABLE employees_articles ADD CONSTRAINT fk_employees_articles_employees
	FOREIGN KEY (employee_id) REFERENCES employees (employee_id);

/************ Foreign Key: fk_employees_employeegroups_employeegroups ***************/
ALTER TABLE employees_employeegroups ADD CONSTRAINT fk_employees_employeegroups_employeegroups
	FOREIGN KEY (employeegroup_id) REFERENCES employeegroups (employeegroup_id);

/************ Foreign Key: fk_employees_employeegroups_employees ***************/
ALTER TABLE employees_employeegroups ADD CONSTRAINT fk_employees_employeegroups_employees
	FOREIGN KEY (employee_id) REFERENCES employees (employee_id);

/************ Foreign Key: fk_employees_suppliergroups_employees ***************/
ALTER TABLE employees_suppliergroups ADD CONSTRAINT fk_employees_suppliergroups_employees
	FOREIGN KEY (employee_id) REFERENCES employees (employee_id);

/************ Foreign Key: fk_employees_suppliergroups_suppliergroups ***************/
ALTER TABLE employees_suppliergroups ADD CONSTRAINT fk_employees_suppliergroups_suppliergroups
	FOREIGN KEY (employees_suppliergroups_id) REFERENCES suppliergroups (suppliergroup_id);

/************ Foreign Key: fk_employees_suppliers_employees ***************/
ALTER TABLE employees_suppliers ADD CONSTRAINT fk_employees_suppliers_employees
	FOREIGN KEY (employee_id) REFERENCES employees (employee_id);

/************ Foreign Key: fk_employees_suppliers_suppliers ***************/
ALTER TABLE employees_suppliers ADD CONSTRAINT fk_employees_suppliers_suppliers
	FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id);

/************ Foreign Key: fk_fieldvalues_fieldlanguagesvalues_fieldlanguage ***************/
ALTER TABLE fieldlanguagesvalues ADD CONSTRAINT fk_fieldvalues_fieldlanguagesvalues_fieldlanguage
	FOREIGN KEY (language_id) REFERENCES fieldlanguage (language_id);

/************ Foreign Key: fk_fieldvalues_fieldlanguagesvalues_fieldvalues ***************/
ALTER TABLE fieldlanguagesvalues ADD CONSTRAINT fk_fieldvalues_fieldlanguagesvalues_fieldvalues
	FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues (fieldvalues_id);

/************ Foreign Key: fk_naviglobal_fieldvalues ***************/
ALTER TABLE naviglobal ADD CONSTRAINT fk_naviglobal_fieldvalues
	FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues (fieldvalues_id);

/************ Foreign Key: fk_naviglobal_userlevel ***************/
ALTER TABLE naviglobal ADD CONSTRAINT fk_naviglobal_userlevel
	FOREIGN KEY (level_id) REFERENCES userlevel (level_id);

/************ Foreign Key: fk_navimain_fieldvalues ***************/
ALTER TABLE navimain ADD CONSTRAINT fk_navimain_fieldvalues
	FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues (fieldvalues_id);

/************ Foreign Key: fk_navimain_naviglobal ***************/
ALTER TABLE navimain ADD CONSTRAINT fk_navimain_naviglobal
	FOREIGN KEY (global_id) REFERENCES naviglobal (global_id);

/************ Foreign Key: fk_navimain_userlevel ***************/
ALTER TABLE navimain ADD CONSTRAINT fk_navimain_userlevel
	FOREIGN KEY (level_id) REFERENCES userlevel (level_id);

/************ Foreign Key: fk_navisub_fieldvalues ***************/
ALTER TABLE navisub ADD CONSTRAINT fk_navisub_fieldvalues
	FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues (fieldvalues_id);

/************ Foreign Key: fk_navisub_navimain ***************/
ALTER TABLE navisub ADD CONSTRAINT fk_navisub_navimain
	FOREIGN KEY (main_id) REFERENCES navimain (main_id);

/************ Foreign Key: fk_navisub_userlevel ***************/
ALTER TABLE navisub ADD CONSTRAINT fk_navisub_userlevel
	FOREIGN KEY (level_id) REFERENCES userlevel (level_id);

/************ Foreign Key: fk_organisation_users_organisation ***************/
ALTER TABLE organisation_users ADD CONSTRAINT fk_organisation_users_organisation
	FOREIGN KEY (organisation_id) REFERENCES organisation (organisation_id);

/************ Foreign Key: fk_organisation_users_users ***************/
ALTER TABLE organisation_users ADD CONSTRAINT fk_organisation_users_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_rooms_roomtypes ***************/
ALTER TABLE rooms ADD CONSTRAINT fk_rooms_roomtypes
	FOREIGN KEY (roomtypes_id) REFERENCES roomtypes (roomtypes_id);

/************ Foreign Key: fk_rooms_organisation_organisation ***************/
ALTER TABLE rooms_organisation ADD CONSTRAINT fk_rooms_organisation_organisation
	FOREIGN KEY (organisation_id) REFERENCES organisation (organisation_id);

/************ Foreign Key: fk_rooms_organisation_rooms ***************/
ALTER TABLE rooms_organisation ADD CONSTRAINT fk_rooms_organisation_rooms
	FOREIGN KEY (rooms_id) REFERENCES rooms (rooms_id);

/************ Foreign Key: fk_suppliers_adresses ***************/
ALTER TABLE suppliers ADD CONSTRAINT fk_suppliers_adresses
	FOREIGN KEY (adresses_id) REFERENCES adresses (adresses_id);

/************ Foreign Key: fk_suppliers_suppliergroups_suppliergroups ***************/
ALTER TABLE suppliers_suppliergroups ADD CONSTRAINT fk_suppliers_suppliergroups_suppliergroups
	FOREIGN KEY (suppliergroup_id) REFERENCES suppliergroups (suppliergroup_id);

/************ Foreign Key: fk_suppliers_suppliergroups_suppliers ***************/
ALTER TABLE suppliers_suppliergroups ADD CONSTRAINT fk_suppliers_suppliergroups_suppliers
	FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id);

/************ Foreign Key: fk_termine_terminestatus ***************/
ALTER TABLE termine ADD CONSTRAINT fk_termine_terminestatus
	FOREIGN KEY (status_id) REFERENCES terminestatus (status_id);

/************ Foreign Key: fk_termine_users ***************/
ALTER TABLE termine ADD CONSTRAINT fk_termine_users
	FOREIGN KEY (owner_id) REFERENCES users (user_id);

/************ Foreign Key: fk_termine_participant_termine ***************/
ALTER TABLE termine_participant ADD CONSTRAINT fk_termine_participant_termine
	FOREIGN KEY (termin_id) REFERENCES termine (termin_id);

/************ Foreign Key: fk_termine_participant_users ***************/
ALTER TABLE termine_participant ADD CONSTRAINT fk_termine_participant_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_termine_todo_user_owner_users ***************/
ALTER TABLE termine_todo_user ADD CONSTRAINT fk_termine_todo_user_owner_users
	FOREIGN KEY (owner_id) REFERENCES users (user_id);

/************ Foreign Key: fk_termine_todo_user_termine_todolist ***************/
ALTER TABLE termine_todo_user ADD CONSTRAINT fk_termine_todo_user_termine_todolist
	FOREIGN KEY (todo_id) REFERENCES termine_todolist (termine_todolist_id);

/************ Foreign Key: fk_termine_todo_user_users ***************/
ALTER TABLE termine_todo_user ADD CONSTRAINT fk_termine_todo_user_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_termine_user_termine ***************/
ALTER TABLE termine_user ADD CONSTRAINT fk_termine_user_termine
	FOREIGN KEY (termin_id) REFERENCES termine (termin_id);

/************ Foreign Key: fk_termine_user_users ***************/
ALTER TABLE termine_user ADD CONSTRAINT fk_termine_user_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_terminegroups_termine ***************/
ALTER TABLE terminegroups ADD CONSTRAINT fk_terminegroups_termine
	FOREIGN KEY (termin_id) REFERENCES termine (termin_id);

/************ Foreign Key: fk_terminegroups_terminestatus ***************/
ALTER TABLE terminegroups ADD CONSTRAINT fk_terminegroups_terminestatus
	FOREIGN KEY (status_id) REFERENCES terminestatus (status_id);

/************ Foreign Key: fk_userdata_users ***************/
ALTER TABLE userdata ADD CONSTRAINT fk_userdata_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_usergroups_userlevel ***************/
ALTER TABLE usergroups ADD CONSTRAINT fk_usergroups_userlevel
	FOREIGN KEY (level_id) REFERENCES userlevel (level_id);

/************ Foreign Key: fk_users_adresses ***************/
ALTER TABLE users ADD CONSTRAINT fk_users_adresses
	FOREIGN KEY (adresses_id) REFERENCES adresses (adresses_id);

/************ Foreign Key: fk_users_delivery_adresses ***************/
ALTER TABLE users ADD CONSTRAINT fk_users_delivery_adresses
	FOREIGN KEY (delivery_adress_id) REFERENCES adresses (adresses_id);

/************ Foreign Key: fk_users_domicile_adresses ***************/
ALTER TABLE users ADD CONSTRAINT fk_users_domicile_adresses
	FOREIGN KEY (domicile_adress_id) REFERENCES adresses (adresses_id);

/************ Foreign Key: fk_users_fieldlanguage ***************/
ALTER TABLE users ADD CONSTRAINT fk_users_fieldlanguage
	FOREIGN KEY (language_id) REFERENCES fieldlanguage (language_id);

/************ Foreign Key: fk_users_titles ***************/
ALTER TABLE users ADD CONSTRAINT fk_users_titles
	FOREIGN KEY (title_id) REFERENCES salutations (salutations_id);

/************ Foreign Key: fk_users_userlevel ***************/
ALTER TABLE users ADD CONSTRAINT fk_users_userlevel
	FOREIGN KEY (level_id) REFERENCES userlevel (level_id);

/************ Foreign Key: fk_usergroups_users ***************/
ALTER TABLE users_usergroups ADD CONSTRAINT fk_usergroups_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_users_usergroups_usergroups ***************/
ALTER TABLE users_usergroups ADD CONSTRAINT fk_users_usergroups_usergroups
	FOREIGN KEY (usergroup_id) REFERENCES usergroups (usergroup_id);

/************ Foreign Key: fk_userwaren_articles ***************/
ALTER TABLE userwaren ADD CONSTRAINT fk_userwaren_articles
	FOREIGN KEY (article_id) REFERENCES articles (article_id);

/************ Foreign Key: fk_userwaren_lieferarten ***************/
ALTER TABLE userwaren ADD CONSTRAINT fk_userwaren_lieferarten
	FOREIGN KEY (liefer_id) REFERENCES lieferarten (liefer_id);

/************ Foreign Key: fk_userwaren_transstatus ***************/
ALTER TABLE userwaren ADD CONSTRAINT fk_userwaren_transstatus
	FOREIGN KEY (status_id) REFERENCES transstatus (status_id);

/************ Foreign Key: fk_userwaren_users ***************/
ALTER TABLE userwaren ADD CONSTRAINT fk_userwaren_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

/************ Foreign Key: fk_userwaren_zahlungsarten ***************/
ALTER TABLE userwaren ADD CONSTRAINT fk_userwaren_zahlungsarten
	FOREIGN KEY (zahlungs_id) REFERENCES zahlungsarten (zahlungs_id);