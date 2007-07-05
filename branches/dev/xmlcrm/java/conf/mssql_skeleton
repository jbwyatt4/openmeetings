USE xmlcrm;


/************ Update: Schemas ***************/

/************ WARNING: In MS SQL Server 2000 schemas are added as 'users', 
which is outside DbWrench's functionality, at this time. ***************/
;


/************ Update: Tables ***************/

/******************** Add Table: adresses ************************/

/* Build Table Structure */
CREATE TABLE adresses
(
	additionalname TEXT NULL,
	adresses_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('adresses_ADRESSES_ID_seq'::text)',
	[comment] TEXT NULL,
	fax VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	state_id BIGINT NOT NULL,
	street VARCHAR(255) NOT NULL,
	town VARCHAR(255) NOT NULL,
	updatetime DATETIME NULL,
	zip VARCHAR(255) NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: adresses */
ALTER TABLE adresses ADD CONSTRAINT pkadresses
	PRIMARY KEY (adresses_id);

/******************** Add Table: adresses_emails ************************/

/* Build Table Structure */
CREATE TABLE adresses_emails
(
	adresses_emails_id BIGINT NULL IDENTITY (1, 1) DEFAULT 'nextval('adresses_emails_ADRESSES_EMAILS_ID_seq'::text)',
	adresses_id BIGINT NOT NULL,
	mail_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: adresses_emails */
ALTER TABLE adresses_emails ADD CONSTRAINT pkadresses_emails
	PRIMARY KEY (adresses_emails_id);

/******************** Add Table: adresses_phones ************************/

/* Build Table Structure */
CREATE TABLE adresses_phones
(
	adresses_id BIGINT NOT NULL,
	adresses_phone_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('adresses_phones_ADRESSES_PHONE_ID_seq'::text)',
	phone_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: adresses_phones */
ALTER TABLE adresses_phones ADD CONSTRAINT pkadresses_phones
	PRIMARY KEY (adresses_phone_id);

/******************** Add Table: articlegroups ************************/

/* Build Table Structure */
CREATE TABLE articlegroups
(
	articlegroup_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('articlegroups_ARTICLEGROUP_ID_seq'::text)',
	name VARCHAR(255) NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: articlegroups */
ALTER TABLE articlegroups ADD CONSTRAINT pkarticlegroups
	PRIMARY KEY (articlegroup_id);

/* Add Indexes for: articlegroups */
CREATE UNIQUE NONCLUSTERED INDEX articlegroups_articlegroup_id_idx ON articlegroups (articlegroup_id);

/******************** Add Table: articles ************************/

/* Build Table Structure */
CREATE TABLE articles
(
	article_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('articles_ARTICLE_ID_seq'::text)',
	description TEXT NULL,
	icon_path TEXT NULL,
	image_path TEXT NULL,
	name VARCHAR(255) NOT NULL,
	price FLOAT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	supplier_id BIGINT NULL,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: articles */
ALTER TABLE articles ADD CONSTRAINT pkarticles
	PRIMARY KEY (article_id);

/* Add Indexes for: articles */
CREATE UNIQUE NONCLUSTERED INDEX articles_article_id_idx ON articles (article_id);

/******************** Add Table: articles_articlegroups ************************/

/* Build Table Structure */
CREATE TABLE articles_articlegroups
(
	article_id BIGINT NOT NULL,
	articlegroup_id BIGINT NOT NULL,
	articles_articlesgroups_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('articles_articlegroups_ARTICLES_ARTICLESGROUPS_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: articles_articlegroups */
ALTER TABLE articles_articlegroups ADD CONSTRAINT pkarticles_articlegroups
	PRIMARY KEY (articles_articlesgroups_id);

/* Add Indexes for: articles_articlegroups */
CREATE UNIQUE NONCLUSTERED INDEX articles_articlegroups_articles_articlesgroups_id_idx ON articles_articlegroups (articles_articlesgroups_id);

/******************** Add Table: articles_lieferarten ************************/

/* Build Table Structure */
CREATE TABLE articles_lieferarten
(
	articles_id BIGINT NOT NULL,
	articles_lieferarten_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('articles_lieferarten_ARTICLES_LIEFERARTEN_ID_seq'::text)',
	liefer_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: articles_lieferarten */
ALTER TABLE articles_lieferarten ADD CONSTRAINT pkarticles_lieferarten
	PRIMARY KEY (articles_lieferarten_id);

/******************** Add Table: configuration ************************/

/* Build Table Structure */
CREATE TABLE configuration
(
	[comment] TEXT NULL,
	conf_key VARCHAR(255) NOT NULL,
	conf_value VARCHAR(255) NOT NULL,
	configuration_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('configuration_CONFIGURATION_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	user_id BIGINT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: configuration */
ALTER TABLE configuration ADD CONSTRAINT pkconfiguration
	PRIMARY KEY (configuration_id);

/* Add Indexes for: configuration */
CREATE UNIQUE NONCLUSTERED INDEX configuration_configuration_id_idx ON configuration (configuration_id);

/******************** Add Table: contactfreigabe ************************/

/* Build Table Structure */
CREATE TABLE contactfreigabe
(
	[comment] TEXT NULL,
	description TEXT NULL,
	freigabe_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('contactfreigabe_FREIGABE_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: contactfreigabe */
ALTER TABLE contactfreigabe ADD CONSTRAINT pkcontactfreigabe
	PRIMARY KEY (freigabe_id);

/******************** Add Table: contactgroups ************************/

/* Build Table Structure */
CREATE TABLE contactgroups
(
	[comment] TEXT NULL,
	contactgroup_id BIGINT NOT NULL DEFAULT 0,
	description TEXT NULL,
	freigabe_id BIGINT NOT NULL,
	name TEXT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: contactgroups */
ALTER TABLE contactgroups ADD CONSTRAINT pkcontactgroups
	PRIMARY KEY (contactgroup_id);

/******************** Add Table: contactgroups_contacts ************************/

/* Build Table Structure */
CREATE TABLE contactgroups_contacts
(
	contact_id BIGINT NOT NULL,
	contactgroup_id BIGINT NOT NULL,
	contactgroups_contact_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('contactgroups_contacts_CONTACTGROUPS_CONTACT_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: contactgroups_contacts */
ALTER TABLE contactgroups_contacts ADD CONSTRAINT pkcontactgroups_contacts
	PRIMARY KEY (contactgroups_contact_id);

/******************** Add Table: contactgroups_conuser ************************/

/* Build Table Structure */
CREATE TABLE contactgroups_conuser
(
	contactgroup_id BIGINT NOT NULL,
	contactgroup_user_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('contactgroups_conuser_CONTACTGROUP_USER_ID_seq'::text)',
	conuser_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: contactgroups_conuser */
ALTER TABLE contactgroups_conuser ADD CONSTRAINT pkcontactgroups_conuser
	PRIMARY KEY (contactgroup_user_id);

/******************** Add Table: contacts ************************/

/* Build Table Structure */
CREATE TABLE contacts
(
	adresses_id BIGINT NOT NULL,
	age BIGINT NOT NULL,
	[comment] TEXT NOT NULL,
	contact_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('contacts_CONTACT_ID_seq'::text)',
	firstname VARCHAR(64) NULL,
	freigabe_id BIGINT NOT NULL DEFAULT 0,
	lastname VARCHAR(64) NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	titel_id BIGINT NOT NULL,
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: contacts */
ALTER TABLE contacts ADD CONSTRAINT pkcontacts
	PRIMARY KEY (contact_id);

/******************** Add Table: conuser ************************/

/* Build Table Structure */
CREATE TABLE conuser
(
	conuser_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('conuser_CONUSER_ID_seq'::text)',
	freigabe_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: conuser */
ALTER TABLE conuser ADD CONSTRAINT pkconuser
	PRIMARY KEY (conuser_id);

/******************** Add Table: emails ************************/

/* Build Table Structure */
CREATE TABLE emails
(
	[comment] TEXT NOT NULL,
	email VARCHAR(128) NOT NULL,
	mail_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('emails_MAIL_ID_seq'::text)',
	startdate DATETIME NOT NULL DEFAULT NOW(),
	updatedate DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: emails */
ALTER TABLE emails ADD CONSTRAINT pkemails
	PRIMARY KEY (mail_id);

/******************** Add Table: employeegroups ************************/

/* Build Table Structure */
CREATE TABLE employeegroups
(
	[comment] TEXT NOT NULL,
	employeegroup_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('employeegroups_EMPLOYEEGROUP_ID_seq'::text)',
	name TEXT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: employeegroups */
ALTER TABLE employeegroups ADD CONSTRAINT pkemployeegroups
	PRIMARY KEY (employeegroup_id);

/******************** Add Table: employees ************************/

/* Build Table Structure */
CREATE TABLE employees
(
	[comment] TEXT NOT NULL,
	employee_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('employees_EMPLOYEE_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL,
	deleted VARCHAR(255) NULL
) WITH OIDS;

/* Table Items: employees */
ALTER TABLE employees ADD CONSTRAINT pkemployees
	PRIMARY KEY (employee_id);

/******************** Add Table: employees_articlegroups ************************/

/* Build Table Structure */
CREATE TABLE employees_articlegroups
(
	articlegroup_id BIGINT NOT NULL,
	employee_id BIGINT NOT NULL,
	employees_articlegroups_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('employees_articlegroups_EMPLOYEES_ARTICLEGROUPS_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: employees_articlegroups */
ALTER TABLE employees_articlegroups ADD CONSTRAINT pkemployees_articlegroups
	PRIMARY KEY (employees_articlegroups_id);

/******************** Add Table: employees_articles ************************/

/* Build Table Structure */
CREATE TABLE employees_articles
(
	article_id BIGINT NOT NULL,
	employee_id BIGINT NOT NULL,
	employees_articles_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('employees_articles_EMPLOYEES_ARTICLES_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: employees_articles */
ALTER TABLE employees_articles ADD CONSTRAINT pkemployees_articles
	PRIMARY KEY (employees_articles_id);

/******************** Add Table: employees_employeegroups ************************/

/* Build Table Structure */
CREATE TABLE employees_employeegroups
(
	employee_employeegroup_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('employees_employeegroups_EMPLOYEE_EMPLOYEEGROUP_ID_seq'::text)',
	employee_id BIGINT NOT NULL,
	employeegroup_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: employees_employeegroups */
ALTER TABLE employees_employeegroups ADD CONSTRAINT pkemployees_employeegroups
	PRIMARY KEY (employee_employeegroup_id);

/******************** Add Table: employees_suppliergroups ************************/

/* Build Table Structure */
CREATE TABLE employees_suppliergroups
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	employee_id BIGINT NOT NULL,
	employees_suppliergroups_id BIGINT NOT NULL IDENTITY (1, 1),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	suppliergroup_id BIGINT NOT NULL,
	updatetime DATETIME NULL
);

/* Table Items: employees_suppliergroups */
ALTER TABLE employees_suppliergroups ADD CONSTRAINT pkemployees_suppliergroups
	PRIMARY KEY (employees_suppliergroups_id);

/******************** Add Table: employees_suppliers ************************/

/* Build Table Structure */
CREATE TABLE employees_suppliers
(
	employee_id BIGINT NOT NULL,
	employees_suplier_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('employees_suppliers_EMPLOYEES_SUPLIER_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	supplier_id BIGINT NOT NULL,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: employees_suppliers */
ALTER TABLE employees_suppliers ADD CONSTRAINT pkemployees_suppliers
	PRIMARY KEY (employees_suplier_id);

/******************** Add Table: fieldlanguage ************************/

/* Build Table Structure */
CREATE TABLE fieldlanguage
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	language_id INTEGER NOT NULL IDENTITY (1, 1),
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL
);

/* Table Items: fieldlanguage */
ALTER TABLE fieldlanguage ADD CONSTRAINT pkfieldlanguage
	PRIMARY KEY (language_id);

/******************** Add Table: fieldlanguagesvalues ************************/

/* Build Table Structure */
CREATE TABLE fieldlanguagesvalues
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	fieldlanguagesvalues_id INTEGER NOT NULL IDENTITY (1, 1),
	fieldvalues_id INTEGER NOT NULL,
	language_id INTEGER NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	value VARCHAR(255) NOT NULL
);

/* Table Items: fieldlanguagesvalues */
ALTER TABLE fieldlanguagesvalues ADD CONSTRAINT pkfieldlanguagesvalues
	PRIMARY KEY (fieldlanguagesvalues_id);

/******************** Add Table: fieldvalues ************************/

/* Build Table Structure */
CREATE TABLE fieldvalues
(
	deleted VARCHAR(255) NULL DEFAULT 'false',
	fieldvalues_id INTEGER NOT NULL IDENTITY (1, 1),
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL
);

/* Table Items: fieldvalues */
ALTER TABLE fieldvalues ADD CONSTRAINT pkfieldvalues
	PRIMARY KEY (fieldvalues_id);

/******************** Add Table: lieferarten ************************/

/* Build Table Structure */
CREATE TABLE lieferarten
(
	beschreibung TEXT NOT NULL,
	[comment] TEXT NOT NULL,
	freigeschalten BIGINT NOT NULL DEFAULT 0,
	liefer_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('lieferarten_LIEFER_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: lieferarten */
ALTER TABLE lieferarten ADD CONSTRAINT pklieferarten
	PRIMARY KEY (liefer_id);

/******************** Add Table: naviglobal ************************/

/* Build Table Structure */
CREATE TABLE naviglobal
(
	naviorder BIGINT NOT NULL DEFAULT 0,
	[action] VARCHAR(64) NOT NULL,
	[comment] TEXT NOT NULL,
	global_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('naviglobal_GLOBAL_ID_seq'::text)',
	icon VARCHAR(64) NOT NULL,
	isleaf BIT NOT NULL,
	isopen BIT NOT NULL,
	updatetime DATETIME NULL,
	level_id INTEGER NOT NULL DEFAULT 0,
	name VARCHAR(64) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	fieldvalues_id INTEGER NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: naviglobal */
ALTER TABLE naviglobal ADD CONSTRAINT pknaviglobal
	PRIMARY KEY (global_id);

/******************** Add Table: navimain ************************/

/* Build Table Structure */
CREATE TABLE navimain
(
	naviorder BIGINT NOT NULL DEFAULT 0,
	[action] VARCHAR(64) NOT NULL,
	[comment] TEXT NOT NULL,
	global_id INTEGER NOT NULL DEFAULT 0,
	icon VARCHAR(64) NOT NULL,
	isleaf BIT NOT NULL,
	isopen BIT NOT NULL,
	updatetime DATETIME NULL,
	level_id INTEGER NOT NULL DEFAULT 0,
	main_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('navimain_MAIN_ID_seq'::text)',
	name VARCHAR(64) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	fieldvalues_id INTEGER NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: navimain */
ALTER TABLE navimain ADD CONSTRAINT pknavimain
	PRIMARY KEY (main_id);

/******************** Add Table: navisub ************************/

/* Build Table Structure */
CREATE TABLE navisub
(
	naviorder BIGINT NOT NULL DEFAULT 0,
	[action] VARCHAR(64) NOT NULL,
	[comment] TEXT NOT NULL,
	icon VARCHAR(64) NOT NULL,
	isleaf BIT NOT NULL,
	isopen BIT NOT NULL,
	updatetime DATETIME NULL,
	level_id BIGINT NOT NULL DEFAULT 0,
	main_id BIGINT NOT NULL DEFAULT 0,
	name VARCHAR(64) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	sub_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('navisub_SUB_ID_seq'::text)',
	fieldvalues_id INTEGER NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: navisub */
ALTER TABLE navisub ADD CONSTRAINT pknavisub
	PRIMARY KEY (sub_id);

/******************** Add Table: organisation ************************/

/* Build Table Structure */
CREATE TABLE organisation
(
	deleted VARCHAR(255) NULL,
	insertedby BIGINT NULL,
	name VARCHAR(255) NOT NULL,
	organisation_id INTEGER NOT NULL IDENTITY (1, 1),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatedby BIGINT NULL,
	updatetime DATETIME NULL
);

/* Table Items: organisation */
ALTER TABLE organisation ADD CONSTRAINT pkorganisation
	PRIMARY KEY (organisation_id);

/******************** Add Table: organisation_users ************************/

/* Build Table Structure */
CREATE TABLE organisation_users
(
	[comment] VARCHAR(255) NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false',
	organisation_id INTEGER NOT NULL,
	organisation_users_id INTEGER NOT NULL IDENTITY (1, 1),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL
);

/* Table Items: organisation_users */
ALTER TABLE organisation_users ADD CONSTRAINT pkorganisation_users
	PRIMARY KEY (organisation_users_id);

/******************** Add Table: phones ************************/

/* Build Table Structure */
CREATE TABLE phones
(
	[comment] TEXT NOT NULL,
	phone_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('phones_PHONE_ID_seq'::text)',
	phonevalue VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: phones */
ALTER TABLE phones ADD CONSTRAINT pkphones
	PRIMARY KEY (phone_id);

/******************** Add Table: rooms ************************/

/* Build Table Structure */
CREATE TABLE rooms
(
	[comment] TEXT NULL,
	deleted VARCHAR(255) NULL,
	ispublic BIT NULL DEFAULT 'true',
	name VARCHAR(255) NOT NULL,
	rooms_id BIGINT NOT NULL IDENTITY (1, 1),
	roomtypes_id BIGINT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL
);

/* Table Items: rooms */
ALTER TABLE rooms ADD CONSTRAINT pkrooms
	PRIMARY KEY (rooms_id);

/******************** Add Table: rooms_organisation ************************/

/* Build Table Structure */
CREATE TABLE rooms_organisation
(
	deleted VARCHAR(255) NULL,
	organisation_id BIGINT NOT NULL,
	rooms_id BIGINT NOT NULL,
	rooms_organisation_id BIGINT NOT NULL IDENTITY (1, 1),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL
);

/* Table Items: rooms_organisation */
ALTER TABLE rooms_organisation ADD CONSTRAINT pkrooms_organisation
	PRIMARY KEY (rooms_organisation_id);

/******************** Add Table: roomtypes ************************/

/* Build Table Structure */
CREATE TABLE roomtypes
(
	deleted VARCHAR(255) NULL,
	name VARCHAR(255) NOT NULL,
	roomtypes_id BIGINT NOT NULL IDENTITY (1, 1),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL
);

/* Table Items: roomtypes */
ALTER TABLE roomtypes ADD CONSTRAINT pkroomtypes
	PRIMARY KEY (roomtypes_id);

/******************** Add Table: salutations ************************/

/* Build Table Structure */
CREATE TABLE salutations
(
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	salutations_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('salutations_salutations_id_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: salutations */
ALTER TABLE salutations ADD CONSTRAINT pksalutations
	PRIMARY KEY (salutations_id);

/******************** Add Table: sessiondata ************************/

/* Build Table Structure */
CREATE TABLE sessiondata
(
	id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('sessiondata_ID_seq'::text)',
	refresh_time DATETIME NOT NULL DEFAULT NOW(),
	session_id VARCHAR(64) NOT NULL,
	starttermin_time DATETIME NOT NULL DEFAULT NOW(),
	user_id INTEGER NULL DEFAULT 0
) WITH OIDS;

/* Table Items: sessiondata */
ALTER TABLE sessiondata ADD CONSTRAINT pksessiondata
	PRIMARY KEY (id);

/******************** Add Table: states ************************/

/* Build Table Structure */
CREATE TABLE states
(
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	state_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('states_STATE_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: states */
ALTER TABLE states ADD CONSTRAINT pkstates
	PRIMARY KEY (state_id);

/******************** Add Table: suppliergroups ************************/

/* Build Table Structure */
CREATE TABLE suppliergroups
(
	description TEXT NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	suppliergroup_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('suppliergroups_SUPPLIERGROUP_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: suppliergroups */
ALTER TABLE suppliergroups ADD CONSTRAINT pksuppliergroups
	PRIMARY KEY (suppliergroup_id);

/******************** Add Table: suppliers ************************/

/* Build Table Structure */
CREATE TABLE suppliers
(
	adresses_id INTEGER NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	supplier_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('suppliers_SUPPLIER_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: suppliers */
ALTER TABLE suppliers ADD CONSTRAINT pksuppliers
	PRIMARY KEY (supplier_id);

/******************** Add Table: suppliers_suppliergroups ************************/

/* Build Table Structure */
CREATE TABLE suppliers_suppliergroups
(
	starttime DATETIME NOT NULL DEFAULT NOW(),
	supplier_id BIGINT NOT NULL,
	supplier_suppliergroup_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('suppliers_suppliergroups_SUPPLIER_SUPPLIERGROUP_ID_seq'::text)',
	suppliergroup_id BIGINT NOT NULL,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: suppliers_suppliergroups */
ALTER TABLE suppliers_suppliergroups ADD CONSTRAINT pksuppliers_suppliergroups
	PRIMARY KEY (supplier_suppliergroup_id);

/******************** Add Table: termine ************************/

/* Build Table Structure */
CREATE TABLE termine
(
	[comment] TEXT NOT NULL,
	description TEXT NOT NULL,
	endtermin DATETIME NOT NULL DEFAULT NOW(),
	open INTEGER NOT NULL DEFAULT 0,
	owner_id BIGINT NULL DEFAULT 0,
	place TEXT NOT NULL,
	starttermin DATETIME NOT NULL DEFAULT NOW(),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	status_id BIGINT NULL DEFAULT 0,
	termin_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('termine_TERMIN_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: termine */
ALTER TABLE termine ADD CONSTRAINT pktermine
	PRIMARY KEY (termin_id);

/******************** Add Table: termine_participant ************************/

/* Build Table Structure */
CREATE TABLE termine_participant
(
	starttime DATETIME NOT NULL DEFAULT NOW(),
	termin_id BIGINT NOT NULL,
	termine_participant_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('termine_participant_TERMINE_PARTICIPANT_ID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: termine_participant */
ALTER TABLE termine_participant ADD CONSTRAINT pktermine_participant
	PRIMARY KEY (termine_participant_id);

/******************** Add Table: termine_todo_user ************************/

/* Build Table Structure */
CREATE TABLE termine_todo_user
(
	[comment] TEXT NOT NULL,
	owner_id BIGINT NULL DEFAULT 0,
	priority INTEGER NOT NULL DEFAULT 0,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	todo_id BIGINT NOT NULL DEFAULT 0,
	termine_todo_user_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('termine_todo_user_UID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: termine_todo_user */
ALTER TABLE termine_todo_user ADD CONSTRAINT pktermine_todo_user
	PRIMARY KEY (termine_todo_user_id);

/******************** Add Table: termine_todolist ************************/

/* Build Table Structure */
CREATE TABLE termine_todolist
(
	[comment] TEXT NOT NULL,
	description TEXT NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	status_id BIGINT NOT NULL DEFAULT 0,
	teilnehmer TEXT NOT NULL,
	termine_todolist_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('termine_todolist_TODO_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: termine_todolist */
ALTER TABLE termine_todolist ADD CONSTRAINT pktermine_todolist
	PRIMARY KEY (termine_todolist_id);

/******************** Add Table: termine_user ************************/

/* Build Table Structure */
CREATE TABLE termine_user
(
	[comment] TEXT NOT NULL,
	endtermin DATETIME NOT NULL DEFAULT NOW(),
	invitor_id BIGINT NULL DEFAULT 0,
	message TEXT NOT NULL,
	starttermin DATETIME NOT NULL DEFAULT NOW(),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	termin_id BIGINT NULL DEFAULT 0,
	terminstatus INTEGER NOT NULL DEFAULT 0,
	termine_user_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('termine_user_UID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: termine_user */
ALTER TABLE termine_user ADD CONSTRAINT pktermine_user
	PRIMARY KEY (termine_user_id);

/******************** Add Table: terminegroups ************************/

/* Build Table Structure */
CREATE TABLE terminegroups
(
	[comment] TEXT NULL,
	endtermin DATETIME NOT NULL DEFAULT NOW(),
	invitor_id BIGINT NULL DEFAULT 0,
	message TEXT NULL,
	starttermin DATETIME NOT NULL DEFAULT NOW(),
	starttime DATETIME NOT NULL DEFAULT NOW(),
	termin_id BIGINT NOT NULL DEFAULT 0,
	terminegroup_id BIGINT NOT NULL IDENTITY (1, 1),
	status_id BIGINT NULL DEFAULT 0,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: terminegroups */
ALTER TABLE terminegroups ADD CONSTRAINT pkterminegroups
	PRIMARY KEY (terminegroup_id);

/******************** Add Table: terminestatus ************************/

/* Build Table Structure */
CREATE TABLE terminestatus
(
	[comment] TEXT NULL,
	description TEXT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	status_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('terminestatus_STATUS_ID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: terminestatus */
ALTER TABLE terminestatus ADD CONSTRAINT pkterminestatus
	PRIMARY KEY (status_id);

/******************** Add Table: transstatus ************************/

/* Build Table Structure */
CREATE TABLE transstatus
(
	[comment] TEXT NOT NULL,
	description TEXT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	status_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('transstatus_STATUS_ID_seq'::text)',
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: transstatus */
ALTER TABLE transstatus ADD CONSTRAINT pktransstatus
	PRIMARY KEY (status_id);

/******************** Add Table: userdata ************************/

/* Build Table Structure */
CREATE TABLE userdata
(
	[comment] TEXT NULL,
	data TEXT NOT NULL,
	data_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('userdata_DATA_ID_seq'::text)',
	data_key VARCHAR(128) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: userdata */
ALTER TABLE userdata ADD CONSTRAINT pkuserdata
	PRIMARY KEY (data_id);

/******************** Add Table: usergroups ************************/

/* Build Table Structure */
CREATE TABLE usergroups
(
	level_id BIGINT NOT NULL,
	name VARCHAR(255) NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	usergroup_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('usergroups_USERGROUP_ID_seq'::text)',
	user_id BIGINT NULL,
	description CHAR(255) NULL,
	[comment] CHAR(255) NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: usergroups */
ALTER TABLE usergroups ADD CONSTRAINT pkusergroups
	PRIMARY KEY (usergroup_id);

/******************** Add Table: userlevel ************************/

/* Build Table Structure */
CREATE TABLE userlevel
(
	description TEXT NULL,
	level_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('userlevel_LEVEL_ID_seq'::text)',
	starttime DATETIME NOT NULL DEFAULT NOW(),
	statuscode INTEGER NOT NULL DEFAULT 0,
	updatetime DATETIME NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: userlevel */
ALTER TABLE userlevel ADD CONSTRAINT pkuserlevel
	PRIMARY KEY (level_id);

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
	regdate DATETIME NOT NULL DEFAULT NOW(),
	status INTEGER NOT NULL DEFAULT 0,
	title_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('users_USER_ID_seq'::text)',
	delivery_adress_id BIGINT NULL,
	domicile_adress_id BIGINT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	language_id INTEGER NULL,
	pictureuri VARCHAR(255) NULL,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: users */
ALTER TABLE users ADD CONSTRAINT pkusers
	PRIMARY KEY (user_id);

/******************** Add Table: users_usergroups ************************/

/* Build Table Structure */
CREATE TABLE users_usergroups
(
	[comment] TEXT NOT NULL,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	users_usergroup_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('users_usergroups_UID_seq'::text)',
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	usergroup_id BIGINT NOT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: users_usergroups */
ALTER TABLE users_usergroups ADD CONSTRAINT pkusers_usergroups
	PRIMARY KEY (users_usergroup_id);

/******************** Add Table: userwaren ************************/

/* Build Table Structure */
CREATE TABLE userwaren
(
	article_id BIGINT NOT NULL DEFAULT 0,
	[comment] TEXT NOT NULL,
	liefer_id BIGINT NULL DEFAULT 0,
	menge BIGINT NOT NULL DEFAULT 0,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	status BIGINT NOT NULL DEFAULT 0,
	status_id BIGINT NULL,
	updatetime DATETIME NULL,
	user_id BIGINT NOT NULL DEFAULT 0,
	userwaren_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('userwaren_USERWAREN_ID_seq'::text)',
	zahlungs_id BIGINT NULL DEFAULT 0,
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: userwaren */
ALTER TABLE userwaren ADD CONSTRAINT pkuserwaren
	PRIMARY KEY (userwaren_id);

/******************** Add Table: zahlungsarten ************************/

/* Build Table Structure */
CREATE TABLE zahlungsarten
(
	beschreibung TEXT NOT NULL,
	[comment] TEXT NOT NULL,
	freigeschalten INTEGER NOT NULL DEFAULT 0,
	starttime DATETIME NOT NULL DEFAULT NOW(),
	updatetime DATETIME NULL,
	zahlungs_id BIGINT NOT NULL IDENTITY (1, 1) DEFAULT 'nextval('zahlungsarten_ZAHLUNGS_ID_seq'::text)',
	deleted VARCHAR(255) NULL DEFAULT 'false'
) WITH OIDS;

/* Table Items: zahlungsarten */
ALTER TABLE zahlungsarten ADD CONSTRAINT pkzahlungsarten
	PRIMARY KEY (zahlungs_id);


/************ Add Foreign Keys to Database ***************/

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