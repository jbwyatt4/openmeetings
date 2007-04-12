--
-- PostgreSQL database dump
--

-- Started on 2007-04-12 23:30:19

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 2323 (class 1262 OID 19580)
-- Name: xmlcrm; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE xmlcrm WITH TEMPLATE = template0 ENCODING = 'UTF8';


ALTER DATABASE xmlcrm OWNER TO postgres;

\connect xmlcrm

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = true;

--
-- TOC entry 1417 (class 1259 OID 60991)
-- Dependencies: 1857 1858 1859 5
-- Name: adresses; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE adresses (
    additionalname text,
    adresses_id bigint DEFAULT nextval(('adresses_ADRESSES_ID_seq'::text)::regclass) NOT NULL,
    "comment" text,
    fax character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    state_id bigint NOT NULL,
    street character varying(255) NOT NULL,
    town character varying(255) NOT NULL,
    updatetime timestamp without time zone,
    zip character varying(255) NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.adresses OWNER TO postgres;

--
-- TOC entry 1416 (class 1259 OID 60989)
-- Dependencies: 5
-- Name: adresses_adresses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE adresses_adresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.adresses_adresses_id_seq OWNER TO postgres;

--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 1416
-- Name: adresses_adresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('adresses_adresses_id_seq', 1, false);


--
-- TOC entry 1419 (class 1259 OID 61003)
-- Dependencies: 1860 1861 1862 5
-- Name: adresses_emails; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE adresses_emails (
    adresses_emails_id bigint DEFAULT nextval(('adresses_emails_ADRESSES_EMAILS_ID_seq'::text)::regclass) NOT NULL,
    adresses_id bigint NOT NULL,
    mail_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.adresses_emails OWNER TO postgres;

--
-- TOC entry 1418 (class 1259 OID 61001)
-- Dependencies: 5
-- Name: adresses_emails_adresses_emails_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE adresses_emails_adresses_emails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.adresses_emails_adresses_emails_id_seq OWNER TO postgres;

--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 1418
-- Name: adresses_emails_adresses_emails_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('adresses_emails_adresses_emails_id_seq', 1, false);


--
-- TOC entry 1421 (class 1259 OID 61012)
-- Dependencies: 1863 1864 1865 5
-- Name: adresses_phones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE adresses_phones (
    adresses_id bigint NOT NULL,
    adresses_phone_id bigint DEFAULT nextval(('adresses_phones_ADRESSES_PHONE_ID_seq'::text)::regclass) NOT NULL,
    phone_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.adresses_phones OWNER TO postgres;

--
-- TOC entry 1420 (class 1259 OID 61010)
-- Dependencies: 5
-- Name: adresses_phones_adresses_phone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE adresses_phones_adresses_phone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.adresses_phones_adresses_phone_id_seq OWNER TO postgres;

--
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 1420
-- Name: adresses_phones_adresses_phone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('adresses_phones_adresses_phone_id_seq', 1, false);


--
-- TOC entry 1423 (class 1259 OID 61021)
-- Dependencies: 1866 1867 1868 5
-- Name: articlegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articlegroups (
    articlegroup_id bigint DEFAULT nextval(('articlegroups_ARTICLEGROUP_ID_seq'::text)::regclass) NOT NULL,
    name character varying(255),
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.articlegroups OWNER TO postgres;

--
-- TOC entry 1422 (class 1259 OID 61019)
-- Dependencies: 5
-- Name: articlegroups_articlegroup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE articlegroups_articlegroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.articlegroups_articlegroup_id_seq OWNER TO postgres;

--
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 1422
-- Name: articlegroups_articlegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articlegroups_articlegroup_id_seq', 1, false);


--
-- TOC entry 1425 (class 1259 OID 61034)
-- Dependencies: 1869 1870 1871 5
-- Name: articles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articles (
    article_id bigint DEFAULT nextval(('articles_ARTICLE_ID_seq'::text)::regclass) NOT NULL,
    description text,
    icon_path text,
    image_path text,
    name character varying(255) NOT NULL,
    price double precision NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    supplier_id bigint,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.articles OWNER TO postgres;

--
-- TOC entry 1424 (class 1259 OID 61032)
-- Dependencies: 5
-- Name: articles_article_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE articles_article_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.articles_article_id_seq OWNER TO postgres;

--
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 1424
-- Name: articles_article_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articles_article_id_seq', 1, false);


--
-- TOC entry 1427 (class 1259 OID 61047)
-- Dependencies: 1872 1873 1874 5
-- Name: articles_articlegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articles_articlegroups (
    article_id bigint NOT NULL,
    articlegroup_id bigint NOT NULL,
    articles_articlesgroups_id bigint DEFAULT nextval(('articles_articlegroups_ARTICLES_ARTICLESGROUPS_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.articles_articlegroups OWNER TO postgres;

--
-- TOC entry 1426 (class 1259 OID 61045)
-- Dependencies: 5
-- Name: articles_articlegroups_articles_articlesgroups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE articles_articlegroups_articles_articlesgroups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.articles_articlegroups_articles_articlesgroups_id_seq OWNER TO postgres;

--
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 1426
-- Name: articles_articlegroups_articles_articlesgroups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articles_articlegroups_articles_articlesgroups_id_seq', 1, false);


--
-- TOC entry 1429 (class 1259 OID 61057)
-- Dependencies: 1875 1876 1877 5
-- Name: articles_lieferarten; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articles_lieferarten (
    articles_id bigint NOT NULL,
    articles_lieferarten_id bigint DEFAULT nextval(('articles_lieferarten_ARTICLES_LIEFERARTEN_ID_seq'::text)::regclass) NOT NULL,
    liefer_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.articles_lieferarten OWNER TO postgres;

--
-- TOC entry 1428 (class 1259 OID 61055)
-- Dependencies: 5
-- Name: articles_lieferarten_articles_lieferarten_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE articles_lieferarten_articles_lieferarten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.articles_lieferarten_articles_lieferarten_id_seq OWNER TO postgres;

--
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 1428
-- Name: articles_lieferarten_articles_lieferarten_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articles_lieferarten_articles_lieferarten_id_seq', 1, false);


--
-- TOC entry 1431 (class 1259 OID 61066)
-- Dependencies: 1878 1879 1880 1881 5
-- Name: configuration; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE configuration (
    "comment" text,
    conf_key character varying(255) NOT NULL,
    conf_value character varying(255) NOT NULL,
    configuration_id bigint DEFAULT nextval(('configuration_CONFIGURATION_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.configuration OWNER TO postgres;

--
-- TOC entry 1430 (class 1259 OID 61064)
-- Dependencies: 5
-- Name: configuration_configuration_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE configuration_configuration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.configuration_configuration_id_seq OWNER TO postgres;

--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 1430
-- Name: configuration_configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('configuration_configuration_id_seq', 1, false);


--
-- TOC entry 1433 (class 1259 OID 61080)
-- Dependencies: 1882 1883 1884 5
-- Name: contactfreigabe; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactfreigabe (
    "comment" text,
    description text,
    freigabe_id bigint DEFAULT nextval(('contactfreigabe_FREIGABE_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.contactfreigabe OWNER TO postgres;

--
-- TOC entry 1432 (class 1259 OID 61078)
-- Dependencies: 5
-- Name: contactfreigabe_freigabe_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contactfreigabe_freigabe_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.contactfreigabe_freigabe_id_seq OWNER TO postgres;

--
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 1432
-- Name: contactfreigabe_freigabe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contactfreigabe_freigabe_id_seq', 1, false);


--
-- TOC entry 1434 (class 1259 OID 61090)
-- Dependencies: 1885 1886 1887 5
-- Name: contactgroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactgroups (
    "comment" text,
    contactgroup_id bigint DEFAULT 0 NOT NULL,
    description text,
    freigabe_id bigint NOT NULL,
    name text,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.contactgroups OWNER TO postgres;

--
-- TOC entry 1436 (class 1259 OID 61102)
-- Dependencies: 1888 1889 1890 5
-- Name: contactgroups_contacts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactgroups_contacts (
    contact_id bigint NOT NULL,
    contactgroup_id bigint NOT NULL,
    contactgroups_contact_id bigint DEFAULT nextval(('contactgroups_contacts_CONTACTGROUPS_CONTACT_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.contactgroups_contacts OWNER TO postgres;

--
-- TOC entry 1435 (class 1259 OID 61100)
-- Dependencies: 5
-- Name: contactgroups_contacts_contactgroups_contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contactgroups_contacts_contactgroups_contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.contactgroups_contacts_contactgroups_contact_id_seq OWNER TO postgres;

--
-- TOC entry 2333 (class 0 OID 0)
-- Dependencies: 1435
-- Name: contactgroups_contacts_contactgroups_contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contactgroups_contacts_contactgroups_contact_id_seq', 1, false);


--
-- TOC entry 1438 (class 1259 OID 61111)
-- Dependencies: 1891 1892 1893 5
-- Name: contactgroups_conuser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactgroups_conuser (
    contactgroup_id bigint NOT NULL,
    contactgroup_user_id bigint DEFAULT nextval(('contactgroups_conuser_CONTACTGROUP_USER_ID_seq'::text)::regclass) NOT NULL,
    conuser_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.contactgroups_conuser OWNER TO postgres;

--
-- TOC entry 1437 (class 1259 OID 61109)
-- Dependencies: 5
-- Name: contactgroups_conuser_contactgroup_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contactgroups_conuser_contactgroup_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.contactgroups_conuser_contactgroup_user_id_seq OWNER TO postgres;

--
-- TOC entry 2334 (class 0 OID 0)
-- Dependencies: 1437
-- Name: contactgroups_conuser_contactgroup_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contactgroups_conuser_contactgroup_user_id_seq', 1, false);


--
-- TOC entry 1440 (class 1259 OID 61120)
-- Dependencies: 1894 1895 1896 1897 1898 5
-- Name: contacts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contacts (
    adresses_id bigint NOT NULL,
    age bigint NOT NULL,
    "comment" text NOT NULL,
    contact_id bigint DEFAULT nextval(('contacts_CONTACT_ID_seq'::text)::regclass) NOT NULL,
    firstname character varying(64),
    freigabe_id bigint DEFAULT 0 NOT NULL,
    lastname character varying(64),
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    titel_id bigint NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.contacts OWNER TO postgres;

--
-- TOC entry 1439 (class 1259 OID 61118)
-- Dependencies: 5
-- Name: contacts_contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contacts_contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.contacts_contact_id_seq OWNER TO postgres;

--
-- TOC entry 2335 (class 0 OID 0)
-- Dependencies: 1439
-- Name: contacts_contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contacts_contact_id_seq', 1, false);


--
-- TOC entry 1442 (class 1259 OID 61134)
-- Dependencies: 1899 1900 1901 5
-- Name: conuser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE conuser (
    conuser_id bigint DEFAULT nextval(('conuser_CONUSER_ID_seq'::text)::regclass) NOT NULL,
    freigabe_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.conuser OWNER TO postgres;

--
-- TOC entry 1441 (class 1259 OID 61132)
-- Dependencies: 5
-- Name: conuser_conuser_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE conuser_conuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.conuser_conuser_id_seq OWNER TO postgres;

--
-- TOC entry 2336 (class 0 OID 0)
-- Dependencies: 1441
-- Name: conuser_conuser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('conuser_conuser_id_seq', 1, false);


--
-- TOC entry 1444 (class 1259 OID 61143)
-- Dependencies: 1902 1903 1904 5
-- Name: emails; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE emails (
    "comment" text NOT NULL,
    email character varying(128) NOT NULL,
    mail_id bigint DEFAULT nextval(('emails_MAIL_ID_seq'::text)::regclass) NOT NULL,
    startdate timestamp without time zone DEFAULT now() NOT NULL,
    updatedate timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.emails OWNER TO postgres;

--
-- TOC entry 1443 (class 1259 OID 61141)
-- Dependencies: 5
-- Name: emails_mail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE emails_mail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.emails_mail_id_seq OWNER TO postgres;

--
-- TOC entry 2337 (class 0 OID 0)
-- Dependencies: 1443
-- Name: emails_mail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('emails_mail_id_seq', 1, false);


--
-- TOC entry 1446 (class 1259 OID 61155)
-- Dependencies: 1905 1906 1907 5
-- Name: employeegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employeegroups (
    "comment" text NOT NULL,
    employeegroup_id bigint DEFAULT nextval(('employeegroups_EMPLOYEEGROUP_ID_seq'::text)::regclass) NOT NULL,
    name text NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.employeegroups OWNER TO postgres;

--
-- TOC entry 1445 (class 1259 OID 61153)
-- Dependencies: 5
-- Name: employeegroups_employeegroup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employeegroups_employeegroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.employeegroups_employeegroup_id_seq OWNER TO postgres;

--
-- TOC entry 2338 (class 0 OID 0)
-- Dependencies: 1445
-- Name: employeegroups_employeegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employeegroups_employeegroup_id_seq', 1, false);


--
-- TOC entry 1448 (class 1259 OID 61167)
-- Dependencies: 1908 1909 5
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees (
    "comment" text NOT NULL,
    employee_id bigint DEFAULT nextval(('employees_EMPLOYEE_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL,
    deleted character varying(255)
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 1450 (class 1259 OID 61178)
-- Dependencies: 1910 1911 1912 5
-- Name: employees_articlegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_articlegroups (
    articlegroup_id bigint NOT NULL,
    employee_id bigint NOT NULL,
    employees_articlegroups_id bigint DEFAULT nextval(('employees_articlegroups_EMPLOYEES_ARTICLEGROUPS_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.employees_articlegroups OWNER TO postgres;

--
-- TOC entry 1449 (class 1259 OID 61176)
-- Dependencies: 5
-- Name: employees_articlegroups_employees_articlegroups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employees_articlegroups_employees_articlegroups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.employees_articlegroups_employees_articlegroups_id_seq OWNER TO postgres;

--
-- TOC entry 2339 (class 0 OID 0)
-- Dependencies: 1449
-- Name: employees_articlegroups_employees_articlegroups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_articlegroups_employees_articlegroups_id_seq', 1, false);


--
-- TOC entry 1452 (class 1259 OID 61187)
-- Dependencies: 1913 1914 1915 5
-- Name: employees_articles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_articles (
    article_id bigint NOT NULL,
    employee_id bigint NOT NULL,
    employees_articles_id bigint DEFAULT nextval(('employees_articles_EMPLOYEES_ARTICLES_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.employees_articles OWNER TO postgres;

--
-- TOC entry 1451 (class 1259 OID 61185)
-- Dependencies: 5
-- Name: employees_articles_employees_articles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employees_articles_employees_articles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.employees_articles_employees_articles_id_seq OWNER TO postgres;

--
-- TOC entry 2340 (class 0 OID 0)
-- Dependencies: 1451
-- Name: employees_articles_employees_articles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_articles_employees_articles_id_seq', 1, false);


--
-- TOC entry 1447 (class 1259 OID 61165)
-- Dependencies: 5
-- Name: employees_employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employees_employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.employees_employee_id_seq OWNER TO postgres;

--
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 1447
-- Name: employees_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_employee_id_seq', 1, false);


--
-- TOC entry 1454 (class 1259 OID 61196)
-- Dependencies: 1916 1917 1918 5
-- Name: employees_employeegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_employeegroups (
    employee_employeegroup_id bigint DEFAULT nextval(('employees_employeegroups_EMPLOYEE_EMPLOYEEGROUP_ID_seq'::text)::regclass) NOT NULL,
    employee_id bigint NOT NULL,
    employeegroup_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.employees_employeegroups OWNER TO postgres;

--
-- TOC entry 1453 (class 1259 OID 61194)
-- Dependencies: 5
-- Name: employees_employeegroups_employee_employeegroup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employees_employeegroups_employee_employeegroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.employees_employeegroups_employee_employeegroup_id_seq OWNER TO postgres;

--
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 1453
-- Name: employees_employeegroups_employee_employeegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_employeegroups_employee_employeegroup_id_seq', 1, false);


--
-- TOC entry 1455 (class 1259 OID 61203)
-- Dependencies: 5
-- Name: employees_suppliergroups_employees_suppliergroups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employees_suppliergroups_employees_suppliergroups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.employees_suppliergroups_employees_suppliergroups_id_seq OWNER TO postgres;

--
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 1455
-- Name: employees_suppliergroups_employees_suppliergroups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_suppliergroups_employees_suppliergroups_id_seq', 1, false);


SET default_with_oids = false;

--
-- TOC entry 1456 (class 1259 OID 61205)
-- Dependencies: 1919 1920 1921 5
-- Name: employees_suppliergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_suppliergroups (
    deleted character varying(255) DEFAULT 'false'::character varying,
    employee_id bigint NOT NULL,
    employees_suppliergroups_id bigint DEFAULT nextval('employees_suppliergroups_employees_suppliergroups_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    suppliergroup_id bigint NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.employees_suppliergroups OWNER TO postgres;

SET default_with_oids = true;

--
-- TOC entry 1458 (class 1259 OID 61214)
-- Dependencies: 1922 1923 1924 5
-- Name: employees_suppliers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_suppliers (
    employee_id bigint NOT NULL,
    employees_suplier_id bigint DEFAULT nextval(('employees_suppliers_EMPLOYEES_SUPLIER_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    supplier_id bigint NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.employees_suppliers OWNER TO postgres;

--
-- TOC entry 1457 (class 1259 OID 61212)
-- Dependencies: 5
-- Name: employees_suppliers_employees_suplier_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employees_suppliers_employees_suplier_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.employees_suppliers_employees_suplier_id_seq OWNER TO postgres;

--
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 1457
-- Name: employees_suppliers_employees_suplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_suppliers_employees_suplier_id_seq', 1, false);


--
-- TOC entry 1459 (class 1259 OID 61221)
-- Dependencies: 5
-- Name: fieldlanguage_language_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fieldlanguage_language_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.fieldlanguage_language_id_seq OWNER TO postgres;

--
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 1459
-- Name: fieldlanguage_language_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fieldlanguage_language_id_seq', 1, false);


SET default_with_oids = false;

--
-- TOC entry 1460 (class 1259 OID 61223)
-- Dependencies: 1925 1926 1927 5
-- Name: fieldlanguage; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fieldlanguage (
    deleted character varying(255) DEFAULT 'false'::character varying,
    language_id integer DEFAULT nextval('fieldlanguage_language_id_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.fieldlanguage OWNER TO postgres;

--
-- TOC entry 1461 (class 1259 OID 61233)
-- Dependencies: 5
-- Name: fieldlanguagesvalues_fieldlanguagesvalues_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fieldlanguagesvalues_fieldlanguagesvalues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.fieldlanguagesvalues_fieldlanguagesvalues_id_seq OWNER TO postgres;

--
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 1461
-- Name: fieldlanguagesvalues_fieldlanguagesvalues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fieldlanguagesvalues_fieldlanguagesvalues_id_seq', 1, false);


--
-- TOC entry 1462 (class 1259 OID 61235)
-- Dependencies: 1928 1929 1930 5
-- Name: fieldlanguagesvalues; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fieldlanguagesvalues (
    deleted character varying(255) DEFAULT 'false'::character varying,
    fieldlanguagesvalues_id integer DEFAULT nextval('fieldlanguagesvalues_fieldlanguagesvalues_id_seq'::regclass) NOT NULL,
    fieldvalues_id integer NOT NULL,
    language_id integer NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    value character varying(255) NOT NULL
);


ALTER TABLE public.fieldlanguagesvalues OWNER TO postgres;

--
-- TOC entry 1463 (class 1259 OID 61245)
-- Dependencies: 5
-- Name: fieldvalues_fieldvalues_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fieldvalues_fieldvalues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.fieldvalues_fieldvalues_id_seq OWNER TO postgres;

--
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 1463
-- Name: fieldvalues_fieldvalues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fieldvalues_fieldvalues_id_seq', 1, false);


--
-- TOC entry 1464 (class 1259 OID 61247)
-- Dependencies: 1931 1932 1933 5
-- Name: fieldvalues; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fieldvalues (
    deleted character varying(255) DEFAULT 'false'::character varying,
    fieldvalues_id integer DEFAULT nextval('fieldvalues_fieldvalues_id_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.fieldvalues OWNER TO postgres;

SET default_with_oids = true;

--
-- TOC entry 1466 (class 1259 OID 61259)
-- Dependencies: 1934 1935 1936 1937 5
-- Name: lieferarten; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lieferarten (
    beschreibung text NOT NULL,
    "comment" text NOT NULL,
    freigeschalten bigint DEFAULT 0 NOT NULL,
    liefer_id bigint DEFAULT nextval(('lieferarten_LIEFER_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.lieferarten OWNER TO postgres;

--
-- TOC entry 1465 (class 1259 OID 61257)
-- Dependencies: 5
-- Name: lieferarten_liefer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lieferarten_liefer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lieferarten_liefer_id_seq OWNER TO postgres;

--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 1465
-- Name: lieferarten_liefer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lieferarten_liefer_id_seq', 1, false);


--
-- TOC entry 1468 (class 1259 OID 61272)
-- Dependencies: 1938 1939 1940 1941 1942 5
-- Name: naviglobal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE naviglobal (
    naviorder bigint DEFAULT 0 NOT NULL,
    "action" character varying(64) NOT NULL,
    "comment" text NOT NULL,
    global_id bigint DEFAULT nextval(('naviglobal_GLOBAL_ID_seq'::text)::regclass) NOT NULL,
    icon character varying(64) NOT NULL,
    isleaf boolean NOT NULL,
    isopen boolean NOT NULL,
    updatetime timestamp without time zone,
    level_id integer DEFAULT 0 NOT NULL,
    name character varying(64) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    fieldvalues_id integer NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.naviglobal OWNER TO postgres;

--
-- TOC entry 1467 (class 1259 OID 61270)
-- Dependencies: 5
-- Name: naviglobal_global_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE naviglobal_global_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.naviglobal_global_id_seq OWNER TO postgres;

--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 1467
-- Name: naviglobal_global_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('naviglobal_global_id_seq', 1, false);


--
-- TOC entry 1470 (class 1259 OID 61286)
-- Dependencies: 1943 1944 1945 1946 1947 1948 5
-- Name: navimain; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE navimain (
    naviorder bigint DEFAULT 0 NOT NULL,
    "action" character varying(64) NOT NULL,
    "comment" text NOT NULL,
    global_id integer DEFAULT 0 NOT NULL,
    icon character varying(64) NOT NULL,
    isleaf boolean NOT NULL,
    isopen boolean NOT NULL,
    updatetime timestamp without time zone,
    level_id integer DEFAULT 0 NOT NULL,
    main_id bigint DEFAULT nextval(('navimain_MAIN_ID_seq'::text)::regclass) NOT NULL,
    name character varying(64) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    fieldvalues_id integer NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.navimain OWNER TO postgres;

--
-- TOC entry 1469 (class 1259 OID 61284)
-- Dependencies: 5
-- Name: navimain_main_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE navimain_main_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.navimain_main_id_seq OWNER TO postgres;

--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 1469
-- Name: navimain_main_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('navimain_main_id_seq', 1, false);


--
-- TOC entry 1472 (class 1259 OID 61301)
-- Dependencies: 1949 1950 1951 1952 1953 1954 5
-- Name: navisub; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE navisub (
    naviorder bigint DEFAULT 0 NOT NULL,
    "action" character varying(64) NOT NULL,
    "comment" text NOT NULL,
    icon character varying(64) NOT NULL,
    isleaf boolean NOT NULL,
    isopen boolean NOT NULL,
    updatetime timestamp without time zone,
    level_id bigint DEFAULT 0 NOT NULL,
    main_id bigint DEFAULT 0 NOT NULL,
    name character varying(64) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    sub_id bigint DEFAULT nextval(('navisub_SUB_ID_seq'::text)::regclass) NOT NULL,
    fieldvalues_id integer NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.navisub OWNER TO postgres;

--
-- TOC entry 1471 (class 1259 OID 61299)
-- Dependencies: 5
-- Name: navisub_sub_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE navisub_sub_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.navisub_sub_id_seq OWNER TO postgres;

--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 1471
-- Name: navisub_sub_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('navisub_sub_id_seq', 1, false);


--
-- TOC entry 1473 (class 1259 OID 61314)
-- Dependencies: 5
-- Name: organisation_organisation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE organisation_organisation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.organisation_organisation_id_seq OWNER TO postgres;

--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 1473
-- Name: organisation_organisation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organisation_organisation_id_seq', 1, false);


SET default_with_oids = false;

--
-- TOC entry 1474 (class 1259 OID 61316)
-- Dependencies: 1955 1956 5
-- Name: organisation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organisation (
    deleted character varying(255),
    insertedby bigint,
    name character varying(255) NOT NULL,
    organisation_id integer DEFAULT nextval('organisation_organisation_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatedby bigint,
    updatetime timestamp without time zone
);


ALTER TABLE public.organisation OWNER TO postgres;

--
-- TOC entry 1475 (class 1259 OID 61325)
-- Dependencies: 5
-- Name: organisation_users_organisation_users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE organisation_users_organisation_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.organisation_users_organisation_users_id_seq OWNER TO postgres;

--
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 1475
-- Name: organisation_users_organisation_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organisation_users_organisation_users_id_seq', 1, false);


--
-- TOC entry 1476 (class 1259 OID 61327)
-- Dependencies: 1957 1958 1959 5
-- Name: organisation_users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organisation_users (
    "comment" character varying(255),
    deleted character varying(255) DEFAULT 'false'::character varying,
    organisation_id integer NOT NULL,
    organisation_users_id integer DEFAULT nextval('organisation_users_organisation_users_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL
);


ALTER TABLE public.organisation_users OWNER TO postgres;

SET default_with_oids = true;

--
-- TOC entry 1478 (class 1259 OID 61339)
-- Dependencies: 1960 1961 1962 5
-- Name: phones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE phones (
    "comment" text NOT NULL,
    phone_id bigint DEFAULT nextval(('phones_PHONE_ID_seq'::text)::regclass) NOT NULL,
    phonevalue character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.phones OWNER TO postgres;

--
-- TOC entry 1477 (class 1259 OID 61337)
-- Dependencies: 5
-- Name: phones_phone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE phones_phone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.phones_phone_id_seq OWNER TO postgres;

--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 1477
-- Name: phones_phone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('phones_phone_id_seq', 1, false);


--
-- TOC entry 1479 (class 1259 OID 61349)
-- Dependencies: 5
-- Name: rooms_rooms_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE rooms_rooms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rooms_rooms_id_seq OWNER TO postgres;

--
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 1479
-- Name: rooms_rooms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rooms_rooms_id_seq', 1, false);


SET default_with_oids = false;

--
-- TOC entry 1480 (class 1259 OID 61351)
-- Dependencies: 1963 1964 1965 5
-- Name: rooms; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rooms (
    "comment" text,
    deleted character varying(255),
    ispublic boolean DEFAULT true,
    name character varying(255) NOT NULL,
    rooms_id bigint DEFAULT nextval('rooms_rooms_id_seq'::regclass) NOT NULL,
    roomtypes_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.rooms OWNER TO postgres;

--
-- TOC entry 1481 (class 1259 OID 61361)
-- Dependencies: 5
-- Name: rooms_organisation_rooms_organisation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE rooms_organisation_rooms_organisation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rooms_organisation_rooms_organisation_id_seq OWNER TO postgres;

--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 1481
-- Name: rooms_organisation_rooms_organisation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rooms_organisation_rooms_organisation_id_seq', 1, false);


--
-- TOC entry 1482 (class 1259 OID 61363)
-- Dependencies: 1966 1967 5
-- Name: rooms_organisation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rooms_organisation (
    deleted character varying(255),
    organisation_id bigint NOT NULL,
    rooms_id bigint NOT NULL,
    rooms_organisation_id bigint DEFAULT nextval('rooms_organisation_rooms_organisation_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.rooms_organisation OWNER TO postgres;

--
-- TOC entry 1483 (class 1259 OID 61369)
-- Dependencies: 5
-- Name: roomtypes_roomtypes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE roomtypes_roomtypes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.roomtypes_roomtypes_id_seq OWNER TO postgres;

--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 1483
-- Name: roomtypes_roomtypes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('roomtypes_roomtypes_id_seq', 1, false);


--
-- TOC entry 1484 (class 1259 OID 61371)
-- Dependencies: 1968 1969 5
-- Name: roomtypes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE roomtypes (
    deleted character varying(255),
    name character varying(255) NOT NULL,
    roomtypes_id bigint DEFAULT nextval('roomtypes_roomtypes_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.roomtypes OWNER TO postgres;

SET default_with_oids = true;

--
-- TOC entry 1486 (class 1259 OID 61382)
-- Dependencies: 1970 1971 1972 5
-- Name: salutations; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE salutations (
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    salutations_id bigint DEFAULT nextval(('salutations_salutations_id_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.salutations OWNER TO postgres;

--
-- TOC entry 1485 (class 1259 OID 61380)
-- Dependencies: 5
-- Name: salutations_salutations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE salutations_salutations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.salutations_salutations_id_seq OWNER TO postgres;

--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 1485
-- Name: salutations_salutations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('salutations_salutations_id_seq', 1, false);


--
-- TOC entry 1488 (class 1259 OID 61394)
-- Dependencies: 1973 1974 1975 1976 5
-- Name: sessiondata; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sessiondata (
    id bigint DEFAULT nextval(('sessiondata_ID_seq'::text)::regclass) NOT NULL,
    refresh_time timestamp without time zone DEFAULT now() NOT NULL,
    session_id character varying(64) NOT NULL,
    starttermin_time timestamp without time zone DEFAULT now() NOT NULL,
    user_id integer DEFAULT 0
);


ALTER TABLE public.sessiondata OWNER TO postgres;

--
-- TOC entry 1487 (class 1259 OID 61392)
-- Dependencies: 5
-- Name: sessiondata_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sessiondata_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sessiondata_id_seq OWNER TO postgres;

--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 1487
-- Name: sessiondata_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sessiondata_id_seq', 1, false);


--
-- TOC entry 1490 (class 1259 OID 61404)
-- Dependencies: 1977 1978 1979 5
-- Name: states; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE states (
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    state_id bigint DEFAULT nextval(('states_STATE_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.states OWNER TO postgres;

--
-- TOC entry 1489 (class 1259 OID 61402)
-- Dependencies: 5
-- Name: states_state_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE states_state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.states_state_id_seq OWNER TO postgres;

--
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 1489
-- Name: states_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('states_state_id_seq', 1, false);


--
-- TOC entry 1492 (class 1259 OID 61416)
-- Dependencies: 1980 1981 1982 5
-- Name: suppliergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE suppliergroups (
    description text NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    suppliergroup_id bigint DEFAULT nextval(('suppliergroups_SUPPLIERGROUP_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.suppliergroups OWNER TO postgres;

--
-- TOC entry 1491 (class 1259 OID 61414)
-- Dependencies: 5
-- Name: suppliergroups_suppliergroup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE suppliergroups_suppliergroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.suppliergroups_suppliergroup_id_seq OWNER TO postgres;

--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 1491
-- Name: suppliergroups_suppliergroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('suppliergroups_suppliergroup_id_seq', 1, false);


--
-- TOC entry 1494 (class 1259 OID 61428)
-- Dependencies: 1983 1984 1985 5
-- Name: suppliers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE suppliers (
    adresses_id integer NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    supplier_id bigint DEFAULT nextval(('suppliers_SUPPLIER_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.suppliers OWNER TO postgres;

--
-- TOC entry 1493 (class 1259 OID 61426)
-- Dependencies: 5
-- Name: suppliers_supplier_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE suppliers_supplier_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.suppliers_supplier_id_seq OWNER TO postgres;

--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 1493
-- Name: suppliers_supplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('suppliers_supplier_id_seq', 1, false);


--
-- TOC entry 1496 (class 1259 OID 61440)
-- Dependencies: 1986 1987 1988 5
-- Name: suppliers_suppliergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE suppliers_suppliergroups (
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    supplier_id bigint NOT NULL,
    supplier_suppliergroup_id bigint DEFAULT nextval(('suppliers_suppliergroups_SUPPLIER_SUPPLIERGROUP_ID_seq'::text)::regclass) NOT NULL,
    suppliergroup_id bigint NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.suppliers_suppliergroups OWNER TO postgres;

--
-- TOC entry 1495 (class 1259 OID 61438)
-- Dependencies: 5
-- Name: suppliers_suppliergroups_supplier_suppliergroup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE suppliers_suppliergroups_supplier_suppliergroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.suppliers_suppliergroups_supplier_suppliergroup_id_seq OWNER TO postgres;

--
-- TOC entry 2363 (class 0 OID 0)
-- Dependencies: 1495
-- Name: suppliers_suppliergroups_supplier_suppliergroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('suppliers_suppliergroups_supplier_suppliergroup_id_seq', 1, false);


--
-- TOC entry 1498 (class 1259 OID 61449)
-- Dependencies: 1989 1990 1991 1992 1993 1994 1995 1996 5
-- Name: termine; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE termine (
    "comment" text NOT NULL,
    description text NOT NULL,
    endtermin timestamp without time zone DEFAULT now() NOT NULL,
    open integer DEFAULT 0 NOT NULL,
    owner_id bigint DEFAULT 0,
    place text NOT NULL,
    starttermin timestamp without time zone DEFAULT now() NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    status_id bigint DEFAULT 0,
    termin_id bigint DEFAULT nextval(('termine_TERMIN_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.termine OWNER TO postgres;

--
-- TOC entry 1500 (class 1259 OID 61466)
-- Dependencies: 1997 1998 1999 5
-- Name: termine_participant; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE termine_participant (
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    termin_id bigint NOT NULL,
    termine_participant_id bigint DEFAULT nextval(('termine_participant_TERMINE_PARTICIPANT_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.termine_participant OWNER TO postgres;

--
-- TOC entry 1499 (class 1259 OID 61464)
-- Dependencies: 5
-- Name: termine_participant_termine_participant_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE termine_participant_termine_participant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.termine_participant_termine_participant_id_seq OWNER TO postgres;

--
-- TOC entry 2364 (class 0 OID 0)
-- Dependencies: 1499
-- Name: termine_participant_termine_participant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_participant_termine_participant_id_seq', 1, false);


--
-- TOC entry 1497 (class 1259 OID 61447)
-- Dependencies: 5
-- Name: termine_termin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE termine_termin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.termine_termin_id_seq OWNER TO postgres;

--
-- TOC entry 2365 (class 0 OID 0)
-- Dependencies: 1497
-- Name: termine_termin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_termin_id_seq', 1, false);


--
-- TOC entry 1502 (class 1259 OID 61475)
-- Dependencies: 2000 2001 2002 2003 2004 2005 2006 5
-- Name: termine_todo_user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE termine_todo_user (
    "comment" text NOT NULL,
    owner_id bigint DEFAULT 0,
    priority integer DEFAULT 0 NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    todo_id bigint DEFAULT 0 NOT NULL,
    termine_todo_user_id bigint DEFAULT nextval(('termine_todo_user_UID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.termine_todo_user OWNER TO postgres;

--
-- TOC entry 1501 (class 1259 OID 61473)
-- Dependencies: 5
-- Name: termine_todo_user_uid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE termine_todo_user_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.termine_todo_user_uid_seq OWNER TO postgres;

--
-- TOC entry 2366 (class 0 OID 0)
-- Dependencies: 1501
-- Name: termine_todo_user_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_todo_user_uid_seq', 1, false);


--
-- TOC entry 1504 (class 1259 OID 61491)
-- Dependencies: 2007 2008 2009 2010 5
-- Name: termine_todolist; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE termine_todolist (
    "comment" text NOT NULL,
    description text NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    status_id bigint DEFAULT 0 NOT NULL,
    teilnehmer text NOT NULL,
    termine_todolist_id bigint DEFAULT nextval(('termine_todolist_TODO_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.termine_todolist OWNER TO postgres;

--
-- TOC entry 1503 (class 1259 OID 61489)
-- Dependencies: 5
-- Name: termine_todolist_todo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE termine_todolist_todo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.termine_todolist_todo_id_seq OWNER TO postgres;

--
-- TOC entry 2367 (class 0 OID 0)
-- Dependencies: 1503
-- Name: termine_todolist_todo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_todolist_todo_id_seq', 1, false);


--
-- TOC entry 1506 (class 1259 OID 61504)
-- Dependencies: 2011 2012 2013 2014 2015 2016 2017 2018 2019 5
-- Name: termine_user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE termine_user (
    "comment" text NOT NULL,
    endtermin timestamp without time zone DEFAULT now() NOT NULL,
    invitor_id bigint DEFAULT 0,
    message text NOT NULL,
    starttermin timestamp without time zone DEFAULT now() NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    termin_id bigint DEFAULT 0,
    terminstatus integer DEFAULT 0 NOT NULL,
    termine_user_id bigint DEFAULT nextval(('termine_user_UID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.termine_user OWNER TO postgres;

--
-- TOC entry 1505 (class 1259 OID 61502)
-- Dependencies: 5
-- Name: termine_user_uid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE termine_user_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.termine_user_uid_seq OWNER TO postgres;

--
-- TOC entry 2368 (class 0 OID 0)
-- Dependencies: 1505
-- Name: termine_user_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_user_uid_seq', 1, false);


--
-- TOC entry 1507 (class 1259 OID 61520)
-- Dependencies: 5
-- Name: terminegroups_terminegroup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE terminegroups_terminegroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.terminegroups_terminegroup_id_seq OWNER TO postgres;

--
-- TOC entry 2369 (class 0 OID 0)
-- Dependencies: 1507
-- Name: terminegroups_terminegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('terminegroups_terminegroup_id_seq', 1, false);


--
-- TOC entry 1508 (class 1259 OID 61522)
-- Dependencies: 2020 2021 2022 2023 2024 2025 2026 2027 5
-- Name: terminegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE terminegroups (
    "comment" text,
    endtermin timestamp without time zone DEFAULT now() NOT NULL,
    invitor_id bigint DEFAULT 0,
    message text,
    starttermin timestamp without time zone DEFAULT now() NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    termin_id bigint DEFAULT 0 NOT NULL,
    terminegroup_id bigint DEFAULT nextval('terminegroups_terminegroup_id_seq'::regclass) NOT NULL,
    status_id bigint DEFAULT 0,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.terminegroups OWNER TO postgres;

--
-- TOC entry 1510 (class 1259 OID 61539)
-- Dependencies: 2028 2029 2030 2031 5
-- Name: terminestatus; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE terminestatus (
    "comment" text,
    description text,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    status_id bigint DEFAULT nextval(('terminestatus_STATUS_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.terminestatus OWNER TO postgres;

--
-- TOC entry 1509 (class 1259 OID 61537)
-- Dependencies: 5
-- Name: terminestatus_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE terminestatus_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.terminestatus_status_id_seq OWNER TO postgres;

--
-- TOC entry 2370 (class 0 OID 0)
-- Dependencies: 1509
-- Name: terminestatus_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('terminestatus_status_id_seq', 1, false);


--
-- TOC entry 1512 (class 1259 OID 61552)
-- Dependencies: 2032 2033 2034 5
-- Name: transstatus; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE transstatus (
    "comment" text NOT NULL,
    description text NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    status_id bigint DEFAULT nextval(('transstatus_STATUS_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.transstatus OWNER TO postgres;

--
-- TOC entry 1511 (class 1259 OID 61550)
-- Dependencies: 5
-- Name: transstatus_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE transstatus_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.transstatus_status_id_seq OWNER TO postgres;

--
-- TOC entry 2371 (class 0 OID 0)
-- Dependencies: 1511
-- Name: transstatus_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('transstatus_status_id_seq', 1, false);


--
-- TOC entry 1514 (class 1259 OID 61564)
-- Dependencies: 2035 2036 2037 2038 5
-- Name: userdata; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userdata (
    "comment" text,
    data text NOT NULL,
    data_id bigint DEFAULT nextval(('userdata_DATA_ID_seq'::text)::regclass) NOT NULL,
    data_key character varying(128) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.userdata OWNER TO postgres;

--
-- TOC entry 1513 (class 1259 OID 61562)
-- Dependencies: 5
-- Name: userdata_data_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE userdata_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.userdata_data_id_seq OWNER TO postgres;

--
-- TOC entry 2372 (class 0 OID 0)
-- Dependencies: 1513
-- Name: userdata_data_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userdata_data_id_seq', 1, false);


--
-- TOC entry 1516 (class 1259 OID 61577)
-- Dependencies: 2039 2040 2041 5
-- Name: usergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usergroups (
    level_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    usergroup_id bigint DEFAULT nextval(('usergroups_USERGROUP_ID_seq'::text)::regclass) NOT NULL,
    user_id bigint,
    description character(255),
    "comment" character(255),
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.usergroups OWNER TO postgres;

--
-- TOC entry 1515 (class 1259 OID 61575)
-- Dependencies: 5
-- Name: usergroups_usergroup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usergroups_usergroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.usergroups_usergroup_id_seq OWNER TO postgres;

--
-- TOC entry 2373 (class 0 OID 0)
-- Dependencies: 1515
-- Name: usergroups_usergroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usergroups_usergroup_id_seq', 1, false);


--
-- TOC entry 1518 (class 1259 OID 61589)
-- Dependencies: 2042 2043 2044 2045 5
-- Name: userlevel; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userlevel (
    description text,
    level_id bigint DEFAULT nextval(('userlevel_LEVEL_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    statuscode integer DEFAULT 0 NOT NULL,
    updatetime timestamp without time zone,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.userlevel OWNER TO postgres;

--
-- TOC entry 1517 (class 1259 OID 61587)
-- Dependencies: 5
-- Name: userlevel_level_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE userlevel_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.userlevel_level_id_seq OWNER TO postgres;

--
-- TOC entry 2374 (class 0 OID 0)
-- Dependencies: 1517
-- Name: userlevel_level_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userlevel_level_id_seq', 1, false);


--
-- TOC entry 1520 (class 1259 OID 61602)
-- Dependencies: 2046 2047 2048 2049 2050 2051 2052 2053 5
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    adresses_id bigint NOT NULL,
    age timestamp without time zone,
    availible integer DEFAULT 0 NOT NULL,
    firstname character varying(32) NOT NULL,
    lastlogin timestamp without time zone,
    lastname character varying(32) NOT NULL,
    lasttrans bigint DEFAULT 0 NOT NULL,
    level_id bigint DEFAULT 0 NOT NULL,
    "login" character varying(128) NOT NULL,
    "password" character varying(64) NOT NULL,
    regdate timestamp without time zone DEFAULT now() NOT NULL,
    status integer DEFAULT 0 NOT NULL,
    title_id bigint NOT NULL,
    user_id bigint DEFAULT nextval(('users_USER_ID_seq'::text)::regclass) NOT NULL,
    delivery_adress_id bigint,
    domicile_adress_id bigint,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    language_id integer,
    pictureuri character varying(255),
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 1519 (class 1259 OID 61600)
-- Dependencies: 5
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 2375 (class 0 OID 0)
-- Dependencies: 1519
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_user_id_seq', 1, false);


--
-- TOC entry 1522 (class 1259 OID 61619)
-- Dependencies: 2054 2055 2056 2057 2058 5
-- Name: users_usergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users_usergroups (
    "comment" text NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    users_usergroup_id bigint DEFAULT nextval(('users_usergroups_UID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    usergroup_id bigint DEFAULT 0 NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.users_usergroups OWNER TO postgres;

--
-- TOC entry 1521 (class 1259 OID 61617)
-- Dependencies: 5
-- Name: users_usergroups_uid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_usergroups_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.users_usergroups_uid_seq OWNER TO postgres;

--
-- TOC entry 2376 (class 0 OID 0)
-- Dependencies: 1521
-- Name: users_usergroups_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_usergroups_uid_seq', 1, false);


--
-- TOC entry 1524 (class 1259 OID 61633)
-- Dependencies: 2059 2060 2061 2062 2063 2064 2065 2066 2067 5
-- Name: userwaren; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userwaren (
    article_id bigint DEFAULT 0 NOT NULL,
    "comment" text NOT NULL,
    liefer_id bigint DEFAULT 0,
    menge bigint DEFAULT 0 NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    status bigint DEFAULT 0 NOT NULL,
    status_id bigint,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    userwaren_id bigint DEFAULT nextval(('userwaren_USERWAREN_ID_seq'::text)::regclass) NOT NULL,
    zahlungs_id bigint DEFAULT 0,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.userwaren OWNER TO postgres;

--
-- TOC entry 1523 (class 1259 OID 61631)
-- Dependencies: 5
-- Name: userwaren_userwaren_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE userwaren_userwaren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.userwaren_userwaren_id_seq OWNER TO postgres;

--
-- TOC entry 2377 (class 0 OID 0)
-- Dependencies: 1523
-- Name: userwaren_userwaren_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userwaren_userwaren_id_seq', 1, false);


--
-- TOC entry 1526 (class 1259 OID 61651)
-- Dependencies: 2068 2069 2070 2071 5
-- Name: zahlungsarten; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE zahlungsarten (
    beschreibung text NOT NULL,
    "comment" text NOT NULL,
    freigeschalten integer DEFAULT 0 NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    zahlungs_id bigint DEFAULT nextval(('zahlungsarten_ZAHLUNGS_ID_seq'::text)::regclass) NOT NULL,
    deleted character varying(255) DEFAULT 'false'::character varying
);


ALTER TABLE public.zahlungsarten OWNER TO postgres;

--
-- TOC entry 1525 (class 1259 OID 61649)
-- Dependencies: 5
-- Name: zahlungsarten_zahlungs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE zahlungsarten_zahlungs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.zahlungsarten_zahlungs_id_seq OWNER TO postgres;

--
-- TOC entry 2378 (class 0 OID 0)
-- Dependencies: 1525
-- Name: zahlungsarten_zahlungs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('zahlungsarten_zahlungs_id_seq', 1, false);


--
-- TOC entry 2265 (class 0 OID 60991)
-- Dependencies: 1417
-- Data for Name: adresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) WITH OIDS FROM stdin;
62070	no	1		fax	2007-04-12 09:12:05.244	1	street	town	\N	zip	\N
\.


--
-- TOC entry 2266 (class 0 OID 61003)
-- Dependencies: 1419
-- Data for Name: adresses_emails; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
62073	1	1	1	2007-04-12 09:12:05.303	\N	false
\.


--
-- TOC entry 2267 (class 0 OID 61012)
-- Dependencies: 1421
-- Data for Name: adresses_phones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY adresses_phones (adresses_id, adresses_phone_id, phone_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2268 (class 0 OID 61021)
-- Dependencies: 1423
-- Data for Name: articlegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY articlegroups (articlegroup_id, name, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2269 (class 0 OID 61034)
-- Dependencies: 1425
-- Data for Name: articles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY articles (article_id, description, icon_path, image_path, name, price, starttime, supplier_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2270 (class 0 OID 61047)
-- Dependencies: 1427
-- Data for Name: articles_articlegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY articles_articlegroups (article_id, articlegroup_id, articles_articlesgroups_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2271 (class 0 OID 61057)
-- Dependencies: 1429
-- Data for Name: articles_lieferarten; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY articles_lieferarten (articles_id, articles_lieferarten_id, liefer_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2272 (class 0 OID 61066)
-- Dependencies: 1431
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY configuration ("comment", conf_key, conf_value, configuration_id, starttime, updatetime, user_id, deleted) WITH OIDS FROM stdin;
62074		allow_frontend_register	1	1	2007-04-12 09:12:06.385	2007-04-12 09:12:06.385	1	\N
62075		default_group_id	1	2	2007-04-12 09:12:06.398	2007-04-12 09:12:06.398	1	\N
62076		default_domain_id	1	3	2007-04-12 09:12:06.403	2007-04-12 09:12:06.403	1	\N
62077	this is the smtp server to send messages	smtp_server	smtp.xmlcrm.org	4	2007-04-12 09:12:06.409	2007-04-12 09:12:06.409	1	\N
62078	all send EMails by the system will have this address	system_email_addr	openmeetings@xmlcrm.org	5	2007-04-12 09:12:06.425	2007-04-12 09:12:06.425	1	\N
62079	System auth email username	email_username	openmeetings@xmlcrm.org	6	2007-04-12 09:12:06.432	2007-04-12 09:12:06.432	1	\N
62080	System auth email password	email_userpass	tony123	7	2007-04-12 09:12:06.44	2007-04-12 09:12:06.44	1	\N
62081	Default System Language for tamplates	default_lang	EN	8	2007-04-12 09:12:06.446	2007-04-12 09:12:06.446	1	\N
62082	The Subject for Mails sended at registration	register_mail_subject	SignUp	9	2007-04-12 09:12:06.453	2007-04-12 09:12:06.453	1	\N
\.


--
-- TOC entry 2273 (class 0 OID 61080)
-- Dependencies: 1433
-- Data for Name: contactfreigabe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contactfreigabe ("comment", description, freigabe_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2274 (class 0 OID 61090)
-- Dependencies: 1434
-- Data for Name: contactgroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contactgroups ("comment", contactgroup_id, description, freigabe_id, name, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2275 (class 0 OID 61102)
-- Dependencies: 1436
-- Data for Name: contactgroups_contacts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contactgroups_contacts (contact_id, contactgroup_id, contactgroups_contact_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2276 (class 0 OID 61111)
-- Dependencies: 1438
-- Data for Name: contactgroups_conuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contactgroups_conuser (contactgroup_id, contactgroup_user_id, conuser_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2277 (class 0 OID 61120)
-- Dependencies: 1440
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contacts (adresses_id, age, "comment", contact_id, firstname, freigabe_id, lastname, starttime, titel_id, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2278 (class 0 OID 61134)
-- Dependencies: 1442
-- Data for Name: conuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY conuser (conuser_id, freigabe_id, starttime, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2279 (class 0 OID 61143)
-- Dependencies: 1444
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY emails ("comment", email, mail_id, startdate, updatedate, deleted) WITH OIDS FROM stdin;
62072		seba.wagner@gmail.com	1	2007-04-12 09:12:05.292	\N	false
\.


--
-- TOC entry 2280 (class 0 OID 61155)
-- Dependencies: 1446
-- Data for Name: employeegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employeegroups ("comment", employeegroup_id, name, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2281 (class 0 OID 61167)
-- Dependencies: 1448
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employees ("comment", employee_id, starttime, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2282 (class 0 OID 61178)
-- Dependencies: 1450
-- Data for Name: employees_articlegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employees_articlegroups (articlegroup_id, employee_id, employees_articlegroups_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2283 (class 0 OID 61187)
-- Dependencies: 1452
-- Data for Name: employees_articles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employees_articles (article_id, employee_id, employees_articles_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2284 (class 0 OID 61196)
-- Dependencies: 1454
-- Data for Name: employees_employeegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employees_employeegroups (employee_employeegroup_id, employee_id, employeegroup_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2285 (class 0 OID 61205)
-- Dependencies: 1456
-- Data for Name: employees_suppliergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employees_suppliergroups (deleted, employee_id, employees_suppliergroups_id, starttime, suppliergroup_id, updatetime) FROM stdin;
\.


--
-- TOC entry 2286 (class 0 OID 61214)
-- Dependencies: 1458
-- Data for Name: employees_suppliers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employees_suppliers (employee_id, employees_suplier_id, starttime, supplier_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2287 (class 0 OID 61223)
-- Dependencies: 1460
-- Data for Name: fieldlanguage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fieldlanguage (deleted, language_id, name, starttime, updatetime) FROM stdin;
\N	1	deutsch	2007-04-12 09:11:51.933	\N
\N	2	english	2007-04-12 09:11:52.003	\N
\N	3	french	2007-04-12 09:11:52.009	\N
\N	4	spanish	2007-04-12 09:11:52.011	\N
\.


--
-- TOC entry 2288 (class 0 OID 61235)
-- Dependencies: 1462
-- Data for Name: fieldlanguagesvalues; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) FROM stdin;
false	1	1	1	2007-04-12 09:11:52.026	\N	Konferenz
false	2	1	2	2007-04-12 09:11:52.041	\N	Confernce
false	3	1	3	2007-04-12 09:11:52.046	\N	Conférence
false	4	1	4	2007-04-12 09:11:52.053	\N	Conferencia
false	5	2	1	2007-04-12 09:11:52.059	\N	Meeting
false	6	2	2	2007-04-12 09:11:52.062	\N	Meeting
false	7	2	3	2007-04-12 09:11:52.067	\N	Meeting
false	8	2	4	2007-04-12 09:11:52.07	\N	Encuentro
false	9	3	1	2007-04-12 09:11:52.077	\N	Auditorium
false	10	3	2	2007-04-12 09:11:52.081	\N	Auditorium
false	11	3	3	2007-04-12 09:11:52.084	\N	Auditorium
false	12	3	4	2007-04-12 09:11:52.087	\N	Auditorio
false	13	4	1	2007-04-12 09:11:52.093	\N	Einstellungen
false	14	4	2	2007-04-12 09:11:52.098	\N	Settings
false	15	4	3	2007-04-12 09:11:52.101	\N	Paramètres
false	16	4	4	2007-04-12 09:11:52.104	\N	Calibración
false	17	5	1	2007-04-12 09:11:52.112	\N	Benutzer
false	18	5	2	2007-04-12 09:11:52.116	\N	User
false	19	5	3	2007-04-12 09:11:52.123	\N	Client
false	20	5	4	2007-04-12 09:11:52.125	\N	Usuario
false	21	6	1	2007-04-12 09:11:52.133	\N	Administration
false	22	6	2	2007-04-12 09:11:52.136	\N	Administration
false	23	6	3	2007-04-12 09:11:52.139	\N	Administration
false	24	6	4	2007-04-12 09:11:52.141	\N	Administration
false	25	7	1	2007-04-12 09:11:52.146	\N	Stop
false	26	7	2	2007-04-12 09:11:52.149	\N	Stop
false	27	7	3	2007-04-12 09:11:52.152	\N	Stop
false	28	7	4	2007-04-12 09:11:52.154	\N	Stop
false	29	8	1	2007-04-12 09:11:52.16	\N	Record
false	30	8	2	2007-04-12 09:11:52.163	\N	Record
false	31	8	3	2007-04-12 09:11:52.166	\N	Record
false	32	8	4	2007-04-12 09:11:52.17	\N	Record
false	33	9	1	2007-04-12 09:11:52.176	\N	Keine Datei vorhanden
false	34	9	2	2007-04-12 09:11:52.18	\N	Keine Datei vorhanden
false	35	9	3	2007-04-12 09:11:52.185	\N	Keine Datei vorhanden
false	36	9	4	2007-04-12 09:11:52.188	\N	Keine Datei vorhanden
false	37	10	1	2007-04-12 09:11:52.196	\N	Aufnahme nur für Lehrer verfügbar
false	38	10	2	2007-04-12 09:11:52.199	\N	Aufnahme nur für Lehrer verfügbar
false	39	10	3	2007-04-12 09:11:52.201	\N	Aufnahme nur für Lehrer verfügbar
false	40	10	4	2007-04-12 09:11:52.203	\N	Aufnahme nur für Lehrer verfügbar
false	41	11	1	2007-04-12 09:11:52.208	\N	verbunden Benutzer:
false	42	11	2	2007-04-12 09:11:52.21	\N	verbunden Benutzer:
false	43	11	3	2007-04-12 09:11:52.214	\N	verbunden Benutzer:
false	44	11	4	2007-04-12 09:11:52.217	\N	verbunden Benutzer:
false	45	12	1	2007-04-12 09:11:52.222	\N	Konferenz starten
false	46	12	2	2007-04-12 09:11:52.225	\N	Konferenz starten
false	47	12	3	2007-04-12 09:11:52.23	\N	Konferenz starten
false	48	12	4	2007-04-12 09:11:52.234	\N	Konferenz starten
false	49	13	1	2007-04-12 09:11:52.24	\N	Mein Name
false	50	13	2	2007-04-12 09:11:52.243	\N	Mein Name
false	51	13	3	2007-04-12 09:11:52.25	\N	Mein Name
false	52	13	4	2007-04-12 09:11:52.252	\N	Mein Name
false	53	14	1	2007-04-12 09:11:52.258	\N	VideoConference
false	54	14	2	2007-04-12 09:11:52.265	\N	VideoConference
false	55	14	3	2007-04-12 09:11:52.268	\N	VideoConference
false	56	14	4	2007-04-12 09:11:52.271	\N	VideoConference
false	57	15	1	2007-04-12 09:11:52.276	\N	Präsentation importieren
false	58	15	2	2007-04-12 09:11:52.28	\N	Präsentation importieren
false	59	15	3	2007-04-12 09:11:52.287	\N	Präsentation importieren
false	60	15	4	2007-04-12 09:11:52.29	\N	Präsentation importieren
false	61	16	1	2007-04-12 09:11:52.298	\N	Liste neu laden
false	62	16	2	2007-04-12 09:11:52.301	\N	Liste neu laden
false	63	16	3	2007-04-12 09:11:52.304	\N	Liste neu laden
false	64	16	4	2007-04-12 09:11:52.306	\N	Liste neu laden
false	65	17	1	2007-04-12 09:11:52.311	\N	Zum Hauptverzeichnis
false	66	17	2	2007-04-12 09:11:52.325	\N	Zum Hauptverzeichnis
false	67	17	3	2007-04-12 09:11:52.328	\N	Zum Hauptverzeichnis
false	68	17	4	2007-04-12 09:11:52.33	\N	Zum Hauptverzeichnis
false	69	18	1	2007-04-12 09:11:52.336	\N	neue Umfrage
false	70	18	2	2007-04-12 09:11:52.339	\N	neue Umfrage
false	71	18	3	2007-04-12 09:11:52.342	\N	neue Umfrage
false	72	18	4	2007-04-12 09:11:52.345	\N	neue Umfrage
false	73	19	1	2007-04-12 09:11:52.352	\N	Eine neue Umfrage für die Konferenz.
false	74	19	2	2007-04-12 09:11:52.354	\N	Eine neue Umfrage für die Konferenz.
false	75	19	3	2007-04-12 09:11:52.356	\N	Eine neue Umfrage für die Konferenz.
false	76	19	4	2007-04-12 09:11:52.359	\N	Eine neue Umfrage für die Konferenz.
false	77	20	1	2007-04-12 09:11:52.364	\N	Frage:
false	78	20	2	2007-04-12 09:11:52.367	\N	Frage:
false	79	20	3	2007-04-12 09:11:52.37	\N	Frage:
false	80	20	4	2007-04-12 09:11:52.373	\N	Frage:
false	81	21	1	2007-04-12 09:11:52.379	\N	Umfragenart:
false	82	21	2	2007-04-12 09:11:52.381	\N	Umfragenart:
false	83	21	3	2007-04-12 09:11:52.385	\N	Umfragenart:
false	84	21	4	2007-04-12 09:11:52.392	\N	Umfragenart:
false	85	22	1	2007-04-12 09:11:52.399	\N	anlegen
false	86	22	2	2007-04-12 09:11:52.402	\N	anlegen
false	87	22	3	2007-04-12 09:11:52.408	\N	anlegen
false	88	22	4	2007-04-12 09:11:52.41	\N	anlegen
false	89	23	1	2007-04-12 09:11:52.415	\N	Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.
false	90	23	2	2007-04-12 09:11:52.421	\N	Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.
false	91	23	3	2007-04-12 09:11:52.424	\N	Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.
false	92	23	4	2007-04-12 09:11:52.426	\N	Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.
false	93	24	1	2007-04-12 09:11:52.434	\N	Umfrage anlegen
false	94	24	2	2007-04-12 09:11:52.443	\N	Umfrage anlegen
false	95	24	3	2007-04-12 09:11:52.446	\N	Umfrage anlegen
false	98	25	2	2007-04-12 09:11:52.457	\N	abbrechen
false	102	26	2	2007-04-12 09:11:52.472	\N	Ja/Nein
false	104	26	4	2007-04-12 09:11:52.479	\N	Ja/Nein
false	105	27	1	2007-04-12 09:11:52.484	\N	Numerisch 1-10
false	107	27	3	2007-04-12 09:11:52.502	\N	Numerisch 1-10
false	113	29	1	2007-04-12 09:11:52.526	\N	Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.
false	114	29	2	2007-04-12 09:11:52.528	\N	Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.
false	115	29	3	2007-04-12 09:11:52.53	\N	Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.
false	116	29	4	2007-04-12 09:11:52.533	\N	Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.
false	117	30	1	2007-04-12 09:11:52.538	\N	Ihr Stimme wurde abgegeben.
false	118	30	2	2007-04-12 09:11:52.54	\N	Ihr Stimme wurde abgegeben.
false	119	30	3	2007-04-12 09:11:52.543	\N	Ihr Stimme wurde abgegeben.
false	120	30	4	2007-04-12 09:11:52.546	\N	Ihr Stimme wurde abgegeben.
false	121	31	1	2007-04-12 09:11:52.55	\N	Sie haben für diese Umfrage bereits ihr Votum abgegeben.
false	122	31	2	2007-04-12 09:11:52.552	\N	Sie haben für diese Umfrage bereits ihr Votum abgegeben.
false	123	31	3	2007-04-12 09:11:52.555	\N	Sie haben für diese Umfrage bereits ihr Votum abgegeben.
false	124	31	4	2007-04-12 09:11:52.557	\N	Sie haben für diese Umfrage bereits ihr Votum abgegeben.
false	125	32	1	2007-04-12 09:11:52.564	\N	abstimmen!
false	128	32	4	2007-04-12 09:11:52.573	\N	abstimmen!
false	129	33	1	2007-04-12 09:11:52.582	\N	Ihre Antwort:
false	136	34	4	2007-04-12 09:11:52.613	\N	Ja
false	138	35	2	2007-04-12 09:11:52.625	\N	Nein
false	140	35	4	2007-04-12 09:11:52.63	\N	Nein
false	141	36	1	2007-04-12 09:11:52.635	\N	will wissen:
false	142	36	2	2007-04-12 09:11:52.638	\N	will wissen:
false	143	36	3	2007-04-12 09:11:52.64	\N	will wissen:
false	144	36	4	2007-04-12 09:11:52.642	\N	will wissen:
false	145	37	1	2007-04-12 09:11:52.646	\N	Umfrageergebnisse
false	153	39	1	2007-04-12 09:11:52.666	\N	Antworten:
false	158	40	2	2007-04-12 09:11:52.68	\N	Ergebnis:
false	159	40	3	2007-04-12 09:11:52.682	\N	Ergebnis:
false	160	40	4	2007-04-12 09:11:52.684	\N	Ergebnis:
false	161	41	1	2007-04-12 09:11:52.688	\N	Es gibt zur Zeit keine Umfage.
false	163	41	3	2007-04-12 09:11:52.694	\N	Es gibt zur Zeit keine Umfage.
false	166	42	2	2007-04-12 09:11:52.707	\N	abstimmen!
false	168	42	4	2007-04-12 09:11:52.714	\N	abstimmen!
false	169	43	1	2007-04-12 09:11:52.73	\N	Meeting (max 4 Plätze)
false	171	43	3	2007-04-12 09:11:52.735	\N	Meeting (max 4 Plätze)
false	182	46	2	2007-04-12 09:11:52.776	\N	verbleibende Plätze
false	184	46	4	2007-04-12 09:11:52.784	\N	verbleibende Plätze
false	185	47	1	2007-04-12 09:11:52.79	\N	Bereits vergeben
false	186	47	2	2007-04-12 09:11:52.792	\N	Bereits vergeben
false	187	47	3	2007-04-12 09:11:52.794	\N	Bereits vergeben
false	188	47	4	2007-04-12 09:11:52.796	\N	Bereits vergeben
false	189	48	1	2007-04-12 09:11:52.80	\N	Eintreten
false	190	48	2	2007-04-12 09:11:52.802	\N	Eintreten
false	191	48	3	2007-04-12 09:11:52.804	\N	Eintreten
false	192	48	4	2007-04-12 09:11:52.805	\N	Eintreten
false	200	50	4	2007-04-12 09:11:52.833	\N	Systemnachricht
false	201	51	1	2007-04-12 09:11:52.84	\N	Geräteauswahl
false	203	51	3	2007-04-12 09:11:52.846	\N	Geräteauswahl
false	224	56	4	2007-04-12 09:11:52.905	\N	Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.
false	225	57	1	2007-04-12 09:11:52.91	\N	Einstellungen ändern.
false	227	57	3	2007-04-12 09:11:52.916	\N	Einstellungen ändern.
false	230	58	2	2007-04-12 09:11:52.931	\N	Kurs:
false	231	58	3	2007-04-12 09:11:52.934	\N	Kurs:
false	232	58	4	2007-04-12 09:11:52.936	\N	Kurs:
false	238	60	2	2007-04-12 09:11:52.958	\N	ok
false	239	60	3	2007-04-12 09:11:52.961	\N	ok
false	240	60	4	2007-04-12 09:11:52.963	\N	ok
false	241	61	1	2007-04-12 09:11:52.967	\N	abbrechen
false	242	61	2	2007-04-12 09:11:52.969	\N	abbrechen
false	243	61	3	2007-04-12 09:11:52.972	\N	abbrechen
false	246	62	2	2007-04-12 09:11:52.984	\N	Zeichenbrett leeren
false	248	62	4	2007-04-12 09:11:52.991	\N	Zeichenbrett leeren
false	249	63	1	2007-04-12 09:11:52.997	\N	Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?
false	251	63	3	2007-04-12 09:11:53.002	\N	Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?
false	252	63	4	2007-04-12 09:11:53.005	\N	Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?
false	253	64	1	2007-04-12 09:11:53.008	\N	Nicht nochmal fragen
false	254	64	2	2007-04-12 09:11:53.01	\N	Nicht nochmal fragen
false	255	64	3	2007-04-12 09:11:53.012	\N	Nicht nochmal fragen
false	256	64	4	2007-04-12 09:11:53.014	\N	Nicht nochmal fragen
false	257	65	1	2007-04-12 09:11:53.018	\N	nein
false	258	65	2	2007-04-12 09:11:53.02	\N	nein
false	259	65	3	2007-04-12 09:11:53.022	\N	nein
false	270	68	2	2007-04-12 09:11:53.052	\N	User Info
false	271	68	3	2007-04-12 09:11:53.056	\N	User Info
false	274	69	2	2007-04-12 09:11:53.068	\N	Clear DrawArea
false	278	70	2	2007-04-12 09:11:53.082	\N	Undo
false	280	70	4	2007-04-12 09:11:53.087	\N	Undo
false	281	71	1	2007-04-12 09:11:53.093	\N	Redo
false	284	71	4	2007-04-12 09:11:53.103	\N	Redo
false	285	72	1	2007-04-12 09:11:53.107	\N	Select an Object
false	286	72	2	2007-04-12 09:11:53.109	\N	Select an Object
false	287	72	3	2007-04-12 09:11:53.111	\N	Select an Object
false	288	72	4	2007-04-12 09:11:53.113	\N	Select an Object
false	289	73	1	2007-04-12 09:11:53.116	\N	Text
false	290	73	2	2007-04-12 09:11:53.118	\N	Text
false	291	73	3	2007-04-12 09:11:53.12	\N	Text
false	292	73	4	2007-04-12 09:11:53.122	\N	Text
false	96	24	4	2007-04-12 09:11:52.449	\N	Umfrage anlegen
false	97	25	1	2007-04-12 09:11:52.454	\N	abbrechen
false	99	25	3	2007-04-12 09:11:52.46	\N	abbrechen
false	100	25	4	2007-04-12 09:11:52.464	\N	abbrechen
false	101	26	1	2007-04-12 09:11:52.469	\N	Ja/Nein
false	103	26	3	2007-04-12 09:11:52.475	\N	Ja/Nein
false	106	27	2	2007-04-12 09:11:52.499	\N	Numerisch 1-10
false	108	27	4	2007-04-12 09:11:52.505	\N	Numerisch 1-10
false	109	28	1	2007-04-12 09:11:52.511	\N	Umfrage
false	110	28	2	2007-04-12 09:11:52.514	\N	Umfrage
false	111	28	3	2007-04-12 09:11:52.517	\N	Umfrage
false	112	28	4	2007-04-12 09:11:52.519	\N	Umfrage
false	126	32	2	2007-04-12 09:11:52.566	\N	abstimmen!
false	127	32	3	2007-04-12 09:11:52.569	\N	abstimmen!
false	130	33	2	2007-04-12 09:11:52.585	\N	Ihre Antwort:
false	131	33	3	2007-04-12 09:11:52.589	\N	Ihre Antwort:
false	132	33	4	2007-04-12 09:11:52.592	\N	Ihre Antwort:
false	133	34	1	2007-04-12 09:11:52.597	\N	Ja
false	134	34	2	2007-04-12 09:11:52.605	\N	Ja
false	135	34	3	2007-04-12 09:11:52.609	\N	Ja
false	137	35	1	2007-04-12 09:11:52.623	\N	Nein
false	139	35	3	2007-04-12 09:11:52.628	\N	Nein
false	146	37	2	2007-04-12 09:11:52.648	\N	Umfrageergebnisse
false	147	37	3	2007-04-12 09:11:52.65	\N	Umfrageergebnisse
false	148	37	4	2007-04-12 09:11:52.652	\N	Umfrageergebnisse
false	149	38	1	2007-04-12 09:11:52.656	\N	Frage:
false	150	38	2	2007-04-12 09:11:52.658	\N	Frage:
false	151	38	3	2007-04-12 09:11:52.66	\N	Frage:
false	152	38	4	2007-04-12 09:11:52.662	\N	Frage:
false	154	39	2	2007-04-12 09:11:52.669	\N	Antworten:
false	155	39	3	2007-04-12 09:11:52.672	\N	Antworten:
false	156	39	4	2007-04-12 09:11:52.674	\N	Antworten:
false	157	40	1	2007-04-12 09:11:52.678	\N	Ergebnis:
false	162	41	2	2007-04-12 09:11:52.691	\N	Es gibt zur Zeit keine Umfage.
false	164	41	4	2007-04-12 09:11:52.699	\N	Es gibt zur Zeit keine Umfage.
false	165	42	1	2007-04-12 09:11:52.704	\N	abstimmen!
false	167	42	3	2007-04-12 09:11:52.709	\N	abstimmen!
false	170	43	2	2007-04-12 09:11:52.733	\N	Meeting (max 4 Plätze)
false	172	43	4	2007-04-12 09:11:52.738	\N	Meeting (max 4 Plätze)
false	173	44	1	2007-04-12 09:11:52.745	\N	Conference (max 50 Plätze)
false	174	44	2	2007-04-12 09:11:52.747	\N	Conference (max 50 Plätze)
false	175	44	3	2007-04-12 09:11:52.749	\N	Conference (max 50 Plätze)
false	176	44	4	2007-04-12 09:11:52.755	\N	Conference (max 50 Plätze)
false	177	45	1	2007-04-12 09:11:52.759	\N	Modus
false	178	45	2	2007-04-12 09:11:52.761	\N	Modus
false	179	45	3	2007-04-12 09:11:52.763	\N	Modus
false	180	45	4	2007-04-12 09:11:52.765	\N	Modus
false	181	46	1	2007-04-12 09:11:52.774	\N	verbleibende Plätze
false	183	46	3	2007-04-12 09:11:52.779	\N	verbleibende Plätze
false	193	49	1	2007-04-12 09:11:52.809	\N	Der Moderator/Lehrer dieses Raums hat den Raum verlassen.
false	194	49	2	2007-04-12 09:11:52.813	\N	Der Moderator/Lehrer dieses Raums hat den Raum verlassen.
false	195	49	3	2007-04-12 09:11:52.815	\N	Der Moderator/Lehrer dieses Raums hat den Raum verlassen.
false	196	49	4	2007-04-12 09:11:52.818	\N	Der Moderator/Lehrer dieses Raums hat den Raum verlassen.
false	197	50	1	2007-04-12 09:11:52.824	\N	Systemnachricht
false	198	50	2	2007-04-12 09:11:52.828	\N	Systemnachricht
false	199	50	3	2007-04-12 09:11:52.83	\N	Systemnachricht
false	202	51	2	2007-04-12 09:11:52.843	\N	Geräteauswahl
false	204	51	4	2007-04-12 09:11:52.848	\N	Geräteauswahl
false	205	52	1	2007-04-12 09:11:52.855	\N	Kamera wählen:
false	206	52	2	2007-04-12 09:11:52.86	\N	Kamera wählen:
false	207	52	3	2007-04-12 09:11:52.862	\N	Kamera wählen:
false	208	52	4	2007-04-12 09:11:52.864	\N	Kamera wählen:
false	209	53	1	2007-04-12 09:11:52.868	\N	Mikrophon wählen:
false	210	53	2	2007-04-12 09:11:52.87	\N	Mikrophon wählen:
false	211	53	3	2007-04-12 09:11:52.871	\N	Mikrophon wählen:
false	212	53	4	2007-04-12 09:11:52.873	\N	Mikrophon wählen:
false	213	54	1	2007-04-12 09:11:52.879	\N	ok
false	214	54	2	2007-04-12 09:11:52.881	\N	ok
false	215	54	3	2007-04-12 09:11:52.883	\N	ok
false	216	54	4	2007-04-12 09:11:52.885	\N	ok
false	217	55	1	2007-04-12 09:11:52.888	\N	abbrechen
false	218	55	2	2007-04-12 09:11:52.89	\N	abbrechen
false	219	55	3	2007-04-12 09:11:52.892	\N	abbrechen
false	220	55	4	2007-04-12 09:11:52.895	\N	abbrechen
false	221	56	1	2007-04-12 09:11:52.899	\N	Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.
false	222	56	2	2007-04-12 09:11:52.901	\N	Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.
false	223	56	3	2007-04-12 09:11:52.903	\N	Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.
false	226	57	2	2007-04-12 09:11:52.913	\N	Einstellungen ändern.
false	228	57	4	2007-04-12 09:11:52.919	\N	Einstellungen ändern.
false	229	58	1	2007-04-12 09:11:52.924	\N	Kurs:
false	233	59	1	2007-04-12 09:11:52.941	\N	Kurssprache:
false	234	59	2	2007-04-12 09:11:52.943	\N	Kurssprache:
false	235	59	3	2007-04-12 09:11:52.945	\N	Kurssprache:
false	236	59	4	2007-04-12 09:11:52.947	\N	Kurssprache:
false	237	60	1	2007-04-12 09:11:52.955	\N	ok
false	244	61	4	2007-04-12 09:11:52.974	\N	abbrechen
false	245	62	1	2007-04-12 09:11:52.981	\N	Zeichenbrett leeren
false	247	62	3	2007-04-12 09:11:52.989	\N	Zeichenbrett leeren
false	250	63	2	2007-04-12 09:11:52.999	\N	Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?
false	260	65	4	2007-04-12 09:11:53.024	\N	nein
false	261	66	1	2007-04-12 09:11:53.03	\N	Einstellungen bearbeiten
false	262	66	2	2007-04-12 09:11:53.032	\N	Einstellungen bearbeiten
false	263	66	3	2007-04-12 09:11:53.034	\N	Einstellungen bearbeiten
false	264	66	4	2007-04-12 09:11:53.036	\N	Einstellungen bearbeiten
false	265	67	1	2007-04-12 09:11:53.04	\N	Bestätigung anfordern bevor das Zeichenbrett geleert wird.
false	266	67	2	2007-04-12 09:11:53.042	\N	Bestätigung anfordern bevor das Zeichenbrett geleert wird.
false	267	67	3	2007-04-12 09:11:53.044	\N	Bestätigung anfordern bevor das Zeichenbrett geleert wird.
false	268	67	4	2007-04-12 09:11:53.046	\N	Bestätigung anfordern bevor das Zeichenbrett geleert wird.
false	269	68	1	2007-04-12 09:11:53.049	\N	User Info
false	272	68	4	2007-04-12 09:11:53.058	\N	User Info
false	273	69	1	2007-04-12 09:11:53.065	\N	Clear DrawArea
false	275	69	3	2007-04-12 09:11:53.07	\N	Clear DrawArea
false	276	69	4	2007-04-12 09:11:53.073	\N	Clear DrawArea
false	277	70	1	2007-04-12 09:11:53.079	\N	Undo
false	279	70	3	2007-04-12 09:11:53.085	\N	Undo
false	282	71	2	2007-04-12 09:11:53.096	\N	Redo
false	283	71	3	2007-04-12 09:11:53.10	\N	Redo
false	293	74	1	2007-04-12 09:11:53.127	\N	Paint
false	295	74	3	2007-04-12 09:11:53.135	\N	Paint
false	310	78	2	2007-04-12 09:11:53.184	\N	Ellipse
false	312	78	4	2007-04-12 09:11:53.192	\N	Ellipse
false	313	79	1	2007-04-12 09:11:53.196	\N	Arrow
false	315	79	3	2007-04-12 09:11:53.201	\N	Arrow
false	325	82	1	2007-04-12 09:11:53.233	\N	apply
false	326	82	2	2007-04-12 09:11:53.237	\N	apply
false	327	82	3	2007-04-12 09:11:53.239	\N	apply
false	337	85	1	2007-04-12 09:11:53.268	\N	close
false	338	85	2	2007-04-12 09:11:53.273	\N	close
false	339	85	3	2007-04-12 09:11:53.275	\N	close
false	340	85	4	2007-04-12 09:11:53.277	\N	close
false	341	86	1	2007-04-12 09:11:53.28	\N	italic
false	342	86	2	2007-04-12 09:11:53.282	\N	italic
false	343	86	3	2007-04-12 09:11:53.284	\N	italic
false	344	86	4	2007-04-12 09:11:53.286	\N	italic
false	345	87	1	2007-04-12 09:11:53.291	\N	bold
false	347	87	3	2007-04-12 09:11:53.297	\N	bold
false	350	88	2	2007-04-12 09:11:53.316	\N	WAITING
false	352	88	4	2007-04-12 09:11:53.323	\N	WAITING
false	353	89	1	2007-04-12 09:11:53.328	\N	A User wants to apply for moderation:
false	355	89	3	2007-04-12 09:11:53.332	\N	A User wants to apply for moderation:
false	360	90	4	2007-04-12 09:11:53.344	\N	accept
false	361	91	1	2007-04-12 09:11:53.351	\N	reject
false	363	91	3	2007-04-12 09:11:53.356	\N	reject
false	366	92	2	2007-04-12 09:11:53.365	\N	cancel
false	368	92	4	2007-04-12 09:11:53.37	\N	cancel
false	369	93	1	2007-04-12 09:11:53.374	\N	Sending request to following Users
false	370	93	2	2007-04-12 09:11:53.376	\N	Sending request to following Users
false	371	93	3	2007-04-12 09:11:53.378	\N	Sending request to following Users
false	372	93	4	2007-04-12 09:11:53.38	\N	Sending request to following Users
false	373	94	1	2007-04-12 09:11:53.383	\N	Accepted
false	374	94	2	2007-04-12 09:11:53.385	\N	Accepted
false	375	94	3	2007-04-12 09:11:53.387	\N	Accepted
false	376	94	4	2007-04-12 09:11:53.389	\N	Accepted
false	378	95	2	2007-04-12 09:11:53.399	\N	Rejected
false	380	95	4	2007-04-12 09:11:53.404	\N	Rejected
false	382	96	2	2007-04-12 09:11:53.411	\N	Change Moderator
false	396	99	4	2007-04-12 09:11:53.448	\N	This Room is full. Sorry please try again later.
false	397	100	1	2007-04-12 09:11:53.465	\N	Ellipse
false	400	100	4	2007-04-12 09:11:53.498	\N	Ellipse
false	401	101	1	2007-04-12 09:11:53.504	\N	close
false	402	101	2	2007-04-12 09:11:53.507	\N	close
false	404	101	4	2007-04-12 09:11:53.514	\N	close
false	405	102	1	2007-04-12 09:11:53.52	\N	Eingabefehler
false	407	102	3	2007-04-12 09:11:53.526	\N	input data error
false	409	103	1	2007-04-12 09:11:53.533	\N	Der Benutzername muss mindestens 4 Zeichen lang sein
false	410	103	2	2007-04-12 09:11:53.535	\N	username must be 4 characters at least
false	423	106	3	2007-04-12 09:11:53.564	\N	The email is already registered
false	426	107	2	2007-04-12 09:11:53.574	\N	System error please contact System-Admins
false	428	107	4	2007-04-12 09:11:53.579	\N	System error please contact System-Admins
false	429	108	1	2007-04-12 09:11:53.584	\N	Login
false	431	108	3	2007-04-12 09:11:53.588	\N	Login
false	432	108	4	2007-04-12 09:11:53.592	\N	Login
false	436	109	4	2007-04-12 09:11:53.603	\N	User:
false	437	110	1	2007-04-12 09:11:53.608	\N	Pass:
false	438	110	2	2007-04-12 09:11:53.611	\N	Pass:
false	440	110	4	2007-04-12 09:11:53.618	\N	Pass:
false	441	111	1	2007-04-12 09:11:53.623	\N	Language
false	442	111	2	2007-04-12 09:11:53.625	\N	Language
false	443	111	3	2007-04-12 09:11:53.627	\N	Language
false	444	111	4	2007-04-12 09:11:53.629	\N	Language
false	445	112	1	2007-04-12 09:11:53.632	\N	Login
false	446	112	2	2007-04-12 09:11:53.634	\N	Login
false	447	112	3	2007-04-12 09:11:53.636	\N	Login
false	448	112	4	2007-04-12 09:11:53.637	\N	Login
false	450	113	2	2007-04-12 09:11:53.643	\N	Sign Up
false	452	113	4	2007-04-12 09:11:53.648	\N	Sign Up
false	453	114	1	2007-04-12 09:11:53.653	\N	Benutzer:
false	455	114	3	2007-04-12 09:11:53.657	\N	User:
false	461	116	1	2007-04-12 09:11:53.675	\N	Wiederhole:
false	473	119	1	2007-04-12 09:11:53.706	\N	EMail:
false	474	119	2	2007-04-12 09:11:53.708	\N	Mail:
false	475	119	3	2007-04-12 09:11:53.71	\N	Mail:
false	476	119	4	2007-04-12 09:11:53.712	\N	Mail:
false	477	120	1	2007-04-12 09:11:53.718	\N	Land:
false	478	120	2	2007-04-12 09:11:53.721	\N	Country:
false	479	120	3	2007-04-12 09:11:53.722	\N	Country:
false	480	120	4	2007-04-12 09:11:53.724	\N	Country:
false	481	121	1	2007-04-12 09:11:53.731	\N	Registrieren
false	483	121	3	2007-04-12 09:11:53.739	\N	Register
false	487	122	3	2007-04-12 09:11:53.751	\N	Cancel
false	294	74	2	2007-04-12 09:11:53.13	\N	Paint
false	296	74	4	2007-04-12 09:11:53.138	\N	Paint
false	297	75	1	2007-04-12 09:11:53.149	\N	Draw line
false	298	75	2	2007-04-12 09:11:53.153	\N	Draw line
false	299	75	3	2007-04-12 09:11:53.155	\N	Draw line
false	300	75	4	2007-04-12 09:11:53.157	\N	Draw line
false	301	76	1	2007-04-12 09:11:53.161	\N	Draw underline
false	302	76	2	2007-04-12 09:11:53.163	\N	Draw underline
false	303	76	3	2007-04-12 09:11:53.165	\N	Draw underline
false	304	76	4	2007-04-12 09:11:53.166	\N	Draw underline
false	305	77	1	2007-04-12 09:11:53.17	\N	Rectangle
false	306	77	2	2007-04-12 09:11:53.172	\N	Rectangle
false	307	77	3	2007-04-12 09:11:53.173	\N	Rectangle
false	308	77	4	2007-04-12 09:11:53.175	\N	Rectangle
false	309	78	1	2007-04-12 09:11:53.18	\N	Ellipse
false	311	78	3	2007-04-12 09:11:53.187	\N	Ellipse
false	314	79	2	2007-04-12 09:11:53.199	\N	Arrow
false	316	79	4	2007-04-12 09:11:53.204	\N	Arrow
false	317	80	1	2007-04-12 09:11:53.209	\N	delete chosen Item
false	318	80	2	2007-04-12 09:11:53.213	\N	delete chosen Item
false	319	80	3	2007-04-12 09:11:53.215	\N	delete chosen Item
false	320	80	4	2007-04-12 09:11:53.217	\N	delete chosen Item
false	321	81	1	2007-04-12 09:11:53.221	\N	Apply for moderation
false	322	81	2	2007-04-12 09:11:53.224	\N	Apply for moderation
false	323	81	3	2007-04-12 09:11:53.226	\N	Apply for moderation
false	324	81	4	2007-04-12 09:11:53.229	\N	Apply for moderation
false	328	82	4	2007-04-12 09:11:53.242	\N	apply
false	329	83	1	2007-04-12 09:11:53.248	\N	cancel
false	330	83	2	2007-04-12 09:11:53.25	\N	cancel
false	331	83	3	2007-04-12 09:11:53.251	\N	cancel
false	332	83	4	2007-04-12 09:11:53.253	\N	cancel
false	333	84	1	2007-04-12 09:11:53.258	\N	Become moderator
false	334	84	2	2007-04-12 09:11:53.26	\N	Become moderator
false	335	84	3	2007-04-12 09:11:53.262	\N	Become moderator
false	336	84	4	2007-04-12 09:11:53.264	\N	Become moderator
false	346	87	2	2007-04-12 09:11:53.294	\N	bold
false	348	87	4	2007-04-12 09:11:53.299	\N	bold
false	349	88	1	2007-04-12 09:11:53.311	\N	WAITING
false	351	88	3	2007-04-12 09:11:53.319	\N	WAITING
false	354	89	2	2007-04-12 09:11:53.33	\N	A User wants to apply for moderation:
false	356	89	4	2007-04-12 09:11:53.335	\N	A User wants to apply for moderation:
false	357	90	1	2007-04-12 09:11:53.339	\N	accept
false	358	90	2	2007-04-12 09:11:53.341	\N	accept
false	359	90	3	2007-04-12 09:11:53.342	\N	accept
false	362	91	2	2007-04-12 09:11:53.354	\N	reject
false	364	91	4	2007-04-12 09:11:53.358	\N	reject
false	365	92	1	2007-04-12 09:11:53.363	\N	cancel
false	367	92	3	2007-04-12 09:11:53.368	\N	cancel
false	377	95	1	2007-04-12 09:11:53.394	\N	Rejected
false	379	95	3	2007-04-12 09:11:53.401	\N	Rejected
false	381	96	1	2007-04-12 09:11:53.409	\N	Change Moderator
false	383	96	3	2007-04-12 09:11:53.414	\N	Change Moderator
false	384	96	4	2007-04-12 09:11:53.416	\N	Change Moderator
false	385	97	1	2007-04-12 09:11:53.421	\N	You are not moderating this course!
false	386	97	2	2007-04-12 09:11:53.423	\N	You are not moderating this course!
false	387	97	3	2007-04-12 09:11:53.425	\N	You are not moderating this course!
false	388	97	4	2007-04-12 09:11:53.426	\N	You are not moderating this course!
false	389	98	1	2007-04-12 09:11:53.43	\N	Moderator:
false	390	98	2	2007-04-12 09:11:53.432	\N	Moderator:
false	391	98	3	2007-04-12 09:11:53.434	\N	Moderator:
false	392	98	4	2007-04-12 09:11:53.436	\N	Moderator:
false	393	99	1	2007-04-12 09:11:53.44	\N	This Room is full. Sorry please try again later.
false	394	99	2	2007-04-12 09:11:53.442	\N	This Room is full. Sorry please try again later.
false	395	99	3	2007-04-12 09:11:53.444	\N	This Room is full. Sorry please try again later.
false	398	100	2	2007-04-12 09:11:53.47	\N	Ellipse
false	399	100	3	2007-04-12 09:11:53.474	\N	Ellipse
false	403	101	3	2007-04-12 09:11:53.509	\N	close
false	406	102	2	2007-04-12 09:11:53.524	\N	input data error
false	408	102	4	2007-04-12 09:11:53.529	\N	input data error
false	411	103	3	2007-04-12 09:11:53.537	\N	username must be 4 characters at least
false	412	103	4	2007-04-12 09:11:53.539	\N	username must be 4 characters at least
false	413	104	1	2007-04-12 09:11:53.543	\N	Das Passwort muss mindestens 4 Zeichen lang sein
false	414	104	2	2007-04-12 09:11:53.545	\N	userpass must be 4 characters at least
false	415	104	3	2007-04-12 09:11:53.546	\N	userpass must be 4 characters at least
false	416	104	4	2007-04-12 09:11:53.548	\N	userpass must be 4 characters at least
false	417	105	1	2007-04-12 09:11:53.552	\N	Der Benutzername ist bereits vergeben
false	418	105	2	2007-04-12 09:11:53.553	\N	The username is already taken
false	419	105	3	2007-04-12 09:11:53.555	\N	The username is already taken
false	420	105	4	2007-04-12 09:11:53.557	\N	The username is already taken
false	421	106	1	2007-04-12 09:11:53.56	\N	Die EMail ist bereits registriert
false	422	106	2	2007-04-12 09:11:53.562	\N	The email is already registered
false	424	106	4	2007-04-12 09:11:53.566	\N	The email is already registered
false	425	107	1	2007-04-12 09:11:53.572	\N	Ein Fehler wurde geworfen bitte kontaktieren Sie das Admin Team
false	427	107	3	2007-04-12 09:11:53.576	\N	System error please contact System-Admins
false	430	108	2	2007-04-12 09:11:53.586	\N	Login
false	433	109	1	2007-04-12 09:11:53.597	\N	Benutzer:
false	434	109	2	2007-04-12 09:11:53.599	\N	User:
false	435	109	3	2007-04-12 09:11:53.60	\N	User:
false	439	110	3	2007-04-12 09:11:53.614	\N	Pass:
false	449	113	1	2007-04-12 09:11:53.641	\N	Sign Up
false	451	113	3	2007-04-12 09:11:53.645	\N	Sign Up
false	454	114	2	2007-04-12 09:11:53.655	\N	User:
false	456	114	4	2007-04-12 09:11:53.66	\N	User:
false	457	115	1	2007-04-12 09:11:53.664	\N	Pass:
false	458	115	2	2007-04-12 09:11:53.665	\N	Pass:
false	459	115	3	2007-04-12 09:11:53.667	\N	Pass:
false	460	115	4	2007-04-12 09:11:53.669	\N	Pass:
false	462	116	2	2007-04-12 09:11:53.678	\N	ReType:
false	463	116	3	2007-04-12 09:11:53.681	\N	ReType:
false	464	116	4	2007-04-12 09:11:53.683	\N	ReType:
false	465	117	1	2007-04-12 09:11:53.687	\N	Vorname:
false	466	117	2	2007-04-12 09:11:53.689	\N	Firstname:
false	467	117	3	2007-04-12 09:11:53.691	\N	Firstname:
false	468	117	4	2007-04-12 09:11:53.693	\N	Firstname:
false	469	118	1	2007-04-12 09:11:53.696	\N	Nachname:
false	470	118	2	2007-04-12 09:11:53.698	\N	Lastname:
false	471	118	3	2007-04-12 09:11:53.70	\N	Lastname:
false	472	118	4	2007-04-12 09:11:53.702	\N	Lastname:
false	482	121	2	2007-04-12 09:11:53.736	\N	Register
false	484	121	4	2007-04-12 09:11:53.741	\N	Register
false	485	122	1	2007-04-12 09:11:53.747	\N	Abbrechen
false	486	122	2	2007-04-12 09:11:53.749	\N	Cancel
false	488	122	4	2007-04-12 09:11:53.753	\N	Cancel
false	489	123	1	2007-04-12 09:11:53.757	\N	Register
false	490	123	2	2007-04-12 09:11:53.759	\N	Register
false	491	123	3	2007-04-12 09:11:53.761	\N	Register
false	492	123	4	2007-04-12 09:11:53.762	\N	Register
false	493	124	1	2007-04-12 09:11:53.766	\N	Home
false	494	124	2	2007-04-12 09:11:53.767	\N	home
false	495	124	3	2007-04-12 09:11:53.769	\N	home
false	496	124	4	2007-04-12 09:11:53.771	\N	home
false	497	125	1	2007-04-12 09:11:53.774	\N	Benutzer
false	498	125	2	2007-04-12 09:11:53.776	\N	Users
false	499	125	3	2007-04-12 09:11:53.778	\N	Users
false	500	125	4	2007-04-12 09:11:53.78	\N	Users
false	501	126	1	2007-04-12 09:11:53.784	\N	Gruppen
false	502	126	2	2007-04-12 09:11:53.785	\N	Groups
false	503	126	3	2007-04-12 09:11:53.787	\N	Groups
false	504	126	4	2007-04-12 09:11:53.789	\N	groups
false	505	127	1	2007-04-12 09:11:53.792	\N	Organisationen
false	506	127	2	2007-04-12 09:11:53.794	\N	Organisations
false	507	127	3	2007-04-12 09:11:53.799	\N	Organisations
false	508	127	4	2007-04-12 09:11:53.801	\N	Organisations
false	509	128	1	2007-04-12 12:06:13.049	\N	Konferenzräume
false	510	128	2	2007-04-12 12:06:13.567	\N	Conference-Rooms
false	511	128	3	2007-04-12 12:06:13.587	\N	Conference-Rooms
false	512	128	4	2007-04-12 12:06:13.596	\N	Conference-Rooms
false	513	129	1	2007-04-12 12:06:13.627	\N	öffentlich
false	514	129	2	2007-04-12 12:06:13.643	\N	public
false	515	129	3	2007-04-12 12:06:13.66	\N	public
false	516	129	4	2007-04-12 12:06:13.678	\N	public
false	517	130	1	2007-04-12 12:06:13.714	\N	Organisation
false	518	130	2	2007-04-12 12:06:13.735	\N	organisation
false	519	130	3	2007-04-12 12:06:13.747	\N	organisation
false	520	130	4	2007-04-12 12:06:13.763	\N	organisation
false	521	131	1	2007-04-12 21:54:30.808	\N	betreten
false	522	131	2	2007-04-12 21:54:30.908	\N	enter
false	523	131	3	2007-04-12 21:54:30.912	\N	enter
false	524	131	4	2007-04-12 21:54:30.931	\N	enter
\.


--
-- TOC entry 2289 (class 0 OID 61247)
-- Dependencies: 1464
-- Data for Name: fieldvalues; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) FROM stdin;
false	1	conference	2007-04-12 09:11:52.015	\N
false	2	meeting	2007-04-12 09:11:52.056	\N
false	3	classroom	2007-04-12 09:11:52.074	\N
false	4	settings	2007-04-12 09:11:52.09	\N
false	5	benutzer	2007-04-12 09:11:52.109	\N
false	6	admin	2007-04-12 09:11:52.128	\N
false	7	stop	2007-04-12 09:11:52.144	\N
false	8	record	2007-04-12 09:11:52.157	\N
false	9	nofile	2007-04-12 09:11:52.173	\N
false	10	recordbyteacher	2007-04-12 09:11:52.191	\N
false	11	connectedusers	2007-04-12 09:11:52.205	\N
false	12	startconf	2007-04-12 09:11:52.22	\N
false	13	myname	2007-04-12 09:11:52.237	\N
false	14	videoconference	2007-04-12 09:11:52.255	\N
false	15	import	2007-04-12 09:11:52.273	\N
false	16	refreshfiles	2007-04-12 09:11:52.293	\N
false	17	tomainfile	2007-04-12 09:11:52.309	\N
false	18	newpoll	2007-04-12 09:11:52.333	\N
false	19	newpollheader	2007-04-12 09:11:52.349	\N
false	20	question	2007-04-12 09:11:52.361	\N
false	21	polltype	2007-04-12 09:11:52.377	\N
false	22	create	2007-04-12 09:11:52.395	\N
false	23	infomessage	2007-04-12 09:11:52.412	\N
false	24	creatpoll	2007-04-12 09:11:52.429	\N
false	25	cancel	2007-04-12 09:11:52.452	\N
false	26	yesno	2007-04-12 09:11:52.467	\N
false	27	numeric	2007-04-12 09:11:52.481	\N
false	28	poll	2007-04-12 09:11:52.508	\N
false	29	moderation	2007-04-12 09:11:52.523	\N
false	30	vote	2007-04-12 09:11:52.535	\N
false	31	alreadyvoted	2007-04-12 09:11:52.548	\N
false	32	voting	2007-04-12 09:11:52.56	\N
false	33	answer	2007-04-12 09:11:52.579	\N
false	34	yes	2007-04-12 09:11:52.594	\N
false	35	no	2007-04-12 09:11:52.619	\N
false	36	questionwant	2007-04-12 09:11:52.633	\N
false	37	pollresults	2007-04-12 09:11:52.644	\N
false	38	question	2007-04-12 09:11:52.654	\N
false	39	results	2007-04-12 09:11:52.664	\N
false	40	answers	2007-04-12 09:11:52.676	\N
false	41	nopoll	2007-04-12 09:11:52.686	\N
false	42	votings	2007-04-12 09:11:52.702	\N
false	43	meeting	2007-04-12 09:11:52.716	\N
false	44	conference	2007-04-12 09:11:52.743	\N
false	45	type	2007-04-12 09:11:52.757	\N
false	46	remainingseats	2007-04-12 09:11:52.772	\N
false	47	alreadychosen	2007-04-12 09:11:52.787	\N
false	48	enter	2007-04-12 09:11:52.798	\N
false	49	modleave	2007-04-12 09:11:52.807	\N
false	50	systemmessage	2007-04-12 09:11:52.82	\N
false	51	chossedevice	2007-04-12 09:11:52.836	\N
false	52	choosecam	2007-04-12 09:11:52.851	\N
false	53	choosemic	2007-04-12 09:11:52.866	\N
false	54	ok	2007-04-12 09:11:52.876	\N
false	55	cancel2	2007-04-12 09:11:52.887	\N
false	56	reconeectneeded	2007-04-12 09:11:52.897	\N
false	57	editsetup	2007-04-12 09:11:52.908	\N
false	58	course	2007-04-12 09:11:52.921	\N
false	59	language	2007-04-12 09:11:52.939	\N
false	60	ok2	2007-04-12 09:11:52.95	\N
false	61	cancel3	2007-04-12 09:11:52.965	\N
false	62	clearwhiteboard	2007-04-12 09:11:52.979	\N
false	63	clearwhiteboardquestion	2007-04-12 09:11:52.994	\N
false	64	dontaskagain	2007-04-12 09:11:53.007	\N
false	65	no	2007-04-12 09:11:53.016	\N
false	66	editsetup2	2007-04-12 09:11:53.028	\N
false	67	needconfirmationwhiteboard	2007-04-12 09:11:53.038	\N
false	68	userinfo	2007-04-12 09:11:53.047	\N
false	69	cleardrawarea	2007-04-12 09:11:53.062	\N
false	70	undo	2007-04-12 09:11:53.076	\N
false	71	redo	2007-04-12 09:11:53.09	\N
false	72	selectobject	2007-04-12 09:11:53.105	\N
false	73	text	2007-04-12 09:11:53.115	\N
false	74	paint	2007-04-12 09:11:53.124	\N
false	75	drawline	2007-04-12 09:11:53.142	\N
false	76	drawu	2007-04-12 09:11:53.159	\N
false	77	rect	2007-04-12 09:11:53.168	\N
false	78	ellipse	2007-04-12 09:11:53.178	\N
false	79	arrow	2007-04-12 09:11:53.194	\N
false	80	deletechosen	2007-04-12 09:11:53.207	\N
false	81	appliymod	2007-04-12 09:11:53.219	\N
false	82	apply	2007-04-12 09:11:53.231	\N
false	83	cancel	2007-04-12 09:11:53.245	\N
false	84	mod	2007-04-12 09:11:53.255	\N
false	85	close	2007-04-12 09:11:53.266	\N
false	86	italic	2007-04-12 09:11:53.278	\N
false	87	bold	2007-04-12 09:11:53.288	\N
false	88	waiting	2007-04-12 09:11:53.302	\N
false	89	applyMessage	2007-04-12 09:11:53.326	\N
false	90	accept	2007-04-12 09:11:53.337	\N
false	91	reject	2007-04-12 09:11:53.349	\N
false	92	cancel	2007-04-12 09:11:53.36	\N
false	93	sendmodrequestmessage	2007-04-12 09:11:53.373	\N
false	94	accept	2007-04-12 09:11:53.381	\N
false	95	reject	2007-04-12 09:11:53.391	\N
false	96	changemod	2007-04-12 09:11:53.407	\N
false	97	nonmoderrormessage	2007-04-12 09:11:53.418	\N
false	98	moderator	2007-04-12 09:11:53.429	\N
false	99	roomfullmessage	2007-04-12 09:11:53.438	\N
false	100	elllipse	2007-04-12 09:11:53.459	\N
false	101	close	2007-04-12 09:11:53.501	\N
false	102	AuthError	2007-04-12 09:11:53.517	\N
false	103	min4username	2007-04-12 09:11:53.531	\N
false	104	min4pass	2007-04-12 09:11:53.541	\N
false	105	usernametaken	2007-04-12 09:11:53.55	\N
false	106	emailtaken	2007-04-12 09:11:53.558	\N
false	107	emailtaken	2007-04-12 09:11:53.569	\N
false	108	Authlogin	2007-04-12 09:11:53.581	\N
false	109	Authuser	2007-04-12 09:11:53.595	\N
false	110	Authpass	2007-04-12 09:11:53.605	\N
false	111	Authlang	2007-04-12 09:11:53.62	\N
false	112	Authreg	2007-04-12 09:11:53.631	\N
false	113	regformhead	2007-04-12 09:11:53.639	\N
false	114	regformuser	2007-04-12 09:11:53.65	\N
false	115	regformpass	2007-04-12 09:11:53.662	\N
false	116	regformretype	2007-04-12 09:11:53.671	\N
false	117	regformfirstname	2007-04-12 09:11:53.685	\N
false	118	regformlastname	2007-04-12 09:11:53.694	\N
false	119	regformmail	2007-04-12 09:11:53.704	\N
false	121	regformbtn1	2007-04-12 09:11:53.727	\N
false	123	Authbtn2	2007-04-12 09:11:53.756	\N
false	124	dashboard	2007-04-12 09:11:53.764	\N
false	125	useradmin	2007-04-12 09:11:53.773	\N
false	126	groupadmin	2007-04-12 09:11:53.782	\N
false	127	orgadmin	2007-04-12 09:11:53.791	\N
false	120	regformstate	2007-04-12 09:11:53.716	\N
false	122	regformbtn2	2007-04-12 09:11:53.744	\N
false	128	headconf	2007-04-12 12:06:12.481	\N
false	129	conf_pub	2007-04-12 12:06:13.612	\N
false	130	head_org	2007-04-12 12:06:13.704	\N
false	131	btn_enterroom	2007-04-12 21:54:30.591	\N
\.


--
-- TOC entry 2290 (class 0 OID 61259)
-- Dependencies: 1466
-- Data for Name: lieferarten; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY lieferarten (beschreibung, "comment", freigeschalten, liefer_id, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2291 (class 0 OID 61272)
-- Dependencies: 1468
-- Data for Name: naviglobal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY naviglobal (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, name, starttime, fieldvalues_id, deleted) WITH OIDS FROM stdin;
62050	1	home		1		t	t	\N	1	home	2007-04-12 09:12:04.817	124	\N
62051	2	conf		2		f	t	\N	1	conference	2007-04-12 09:12:04.831	1	\N
62054	3	settings		3		f	t	\N	1	setings	2007-04-12 09:12:04.855	4	\N
62056	4	admin		4		f	t	\N	2	admin	2007-04-12 09:12:04.867	6	\N
\.


--
-- TOC entry 2292 (class 0 OID 61286)
-- Dependencies: 1470
-- Data for Name: navimain; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY navimain (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, main_id, name, starttime, fieldvalues_id, deleted) WITH OIDS FROM stdin;
62052	1	conf1		2		t	t	\N	1	1	meeting	2007-04-12 09:12:04.843	2	\N
62053	2	conf2		2		t	t	\N	1	2	classroom	2007-04-12 09:12:04.849	3	\N
62055	1	userself		3		t	f	\N	1	3	userself	2007-04-12 09:12:04.862	5	\N
62057	1	useradmin		4		t	f	\N	2	4	useradmin	2007-04-12 09:12:04.87	125	\N
62058	1	groupadmin		4		t	f	\N	2	5	groupadmin	2007-04-12 09:12:04.874	126	\N
62059	1	orgadmin		4		t	f	\N	3	6	orgadmin	2007-04-12 09:12:04.878	127	\N
\.


--
-- TOC entry 2293 (class 0 OID 61301)
-- Dependencies: 1472
-- Data for Name: navisub; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY navisub (naviorder, "action", "comment", icon, isleaf, isopen, updatetime, level_id, main_id, name, starttime, sub_id, fieldvalues_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2294 (class 0 OID 61316)
-- Dependencies: 1474
-- Data for Name: organisation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organisation (deleted, insertedby, name, organisation_id, starttime, updatedby, updatetime) FROM stdin;
false	1	default	1	2007-04-12 09:12:05.877	\N	\N
\.


--
-- TOC entry 2295 (class 0 OID 61327)
-- Dependencies: 1476
-- Data for Name: organisation_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organisation_users ("comment", deleted, organisation_id, organisation_users_id, starttime, updatetime, user_id) FROM stdin;
	false	1	1	2007-04-12 09:12:06.337	\N	1
\.


--
-- TOC entry 2296 (class 0 OID 61339)
-- Dependencies: 1478
-- Data for Name: phones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY phones ("comment", phone_id, phonevalue, starttime, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2297 (class 0 OID 61351)
-- Dependencies: 1480
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY rooms ("comment", deleted, ispublic, name, rooms_id, roomtypes_id, starttime, updatetime) FROM stdin;
\N	false	t	roomOrg	1	1	2007-04-12 09:12:40.018	\N
\N	false	t	roomOrg	2	1	2007-04-12 09:15:14.364	\N
\N	false	t	roomOrg	3	1	2007-04-12 09:16:07.765	\N
\N	false	t	roomOrg	4	1	2007-04-12 09:16:56.929	\N
\N	false	t	roomOrg	5	1	2007-04-12 09:17:27.447	\N
\N	false	t	roomOrg	6	1	2007-04-12 09:18:20.716	\N
\N	false	t	roomOrg	7	1	2007-04-12 09:18:57.973	\N
\N	false	t	roomOrg	8	1	2007-04-12 09:19:35.008	\N
\N	false	t	roomOrg	9	1	2007-04-12 09:19:54.384	\N
\N	false	t	roomOrg	10	1	2007-04-12 09:20:19.969	\N
\N	false	t	roomOrg	11	1	2007-04-12 09:24:01.075	\N
\N	false	t	roomOrg	12	1	2007-04-12 09:25:30.473	\N
\N	false	t	roomOrg	13	1	2007-04-12 09:25:44.425	\N
\N	false	t	roomOrg	14	1	2007-04-12 09:26:05.943	\N
\N	false	t	room1	15	1	2007-04-12 11:45:27.032	\N
\N	false	t	room1	16	2	2007-04-12 11:45:31.367	\N
\.


--
-- TOC entry 2298 (class 0 OID 61363)
-- Dependencies: 1482
-- Data for Name: rooms_organisation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY rooms_organisation (deleted, organisation_id, rooms_id, rooms_organisation_id, starttime, updatetime) FROM stdin;
false	1	13	1	2007-04-12 09:25:48.295	\N
false	1	14	2	2007-04-12 09:26:09.532	\N
\.


--
-- TOC entry 2299 (class 0 OID 61371)
-- Dependencies: 1484
-- Data for Name: roomtypes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY roomtypes (deleted, name, roomtypes_id, starttime, updatetime) FROM stdin;
false	conference	1	2007-04-12 09:12:18.427	\N
false	audience	2	2007-04-12 09:12:21.814	\N
\.


--
-- TOC entry 2300 (class 0 OID 61382)
-- Dependencies: 1486
-- Data for Name: salutations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY salutations (name, starttime, salutations_id, updatetime, deleted) WITH OIDS FROM stdin;
62060	Herr	2007-04-12 09:12:04.888	1	\N	false
62061	Frau	2007-04-12 09:12:04.893	2	\N	false
\.


--
-- TOC entry 2301 (class 0 OID 61394)
-- Dependencies: 1488
-- Data for Name: sessiondata; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sessiondata (id, refresh_time, session_id, starttermin_time, user_id) WITH OIDS FROM stdin;
62083	1	2007-04-12 12:08:34.676	1c4c6b0b4aedb9f007d1409057501104	2007-04-12 12:08:19.174	1
62084	2	2007-04-12 12:18:21.429	a8e6b6e8280511688159283f21bb2a4a	2007-04-12 12:18:21.429	\N
62085	3	2007-04-12 12:23:03.509	5f7f4e12422ac938ec587d1b0227cffb	2007-04-12 12:22:49.293	1
62086	4	2007-04-12 16:53:29.233	8b7fb3a82f03e0d2a26697dbcbed26e6	2007-04-12 12:31:14.356	1
62087	5	2007-04-12 16:55:40.794	a041c02214197a6c095e7d83d609e4f3	2007-04-12 16:55:40.794	\N
62088	6	2007-04-12 16:56:37.835	6ed414fa7fed7d68dfe593651740d10c	2007-04-12 16:56:20.948	1
62089	7	2007-04-12 20:31:02.149	4d54044bfd8981497adc6467bf41e327	2007-04-12 20:30:49.355	1
62090	8	2007-04-12 20:39:05.001	66f809ac55b30c2cbef57979e5b3fc66	2007-04-12 20:38:49.067	1
62091	9	2007-04-12 20:45:34.064	81157b7af3a1402f0a69a70a6f7c48da	2007-04-12 20:40:19.603	1
62092	10	2007-04-12 21:56:40.064	f86aae31fe9e4ab8c4a1d12a1b41f6df	2007-04-12 21:56:33.793	1
62093	11	2007-04-12 22:01:05.02	f270729839aa3053acee059791d0b362	2007-04-12 22:00:27.23	1
62094	12	2007-04-12 22:04:33.511	f6628648995f89907a9cf4ee3560919f	2007-04-12 22:03:15.096	1
62095	13	2007-04-12 22:06:50.241	92e6f03dcf38f3bdd6a88a20127990b7	2007-04-12 22:06:45.322	1
\.


--
-- TOC entry 2302 (class 0 OID 61404)
-- Dependencies: 1490
-- Data for Name: states; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY states (name, starttime, state_id, updatetime, deleted) WITH OIDS FROM stdin;
62062	Deutschland	2007-04-12 09:12:04.897	1	\N	false
62063	France	2007-04-12 09:12:04.902	2	\N	false
62064	Italy	2007-04-12 09:12:04.908	3	\N	false
62065	Spain	2007-04-12 09:12:04.911	4	\N	false
62066	USA	2007-04-12 09:12:04.915	5	\N	false
62067	United Kingdom	2007-04-12 09:12:04.918	6	\N	false
62068	Ireland	2007-04-12 09:12:04.923	7	\N	false
62069	Danemark	2007-04-12 09:12:04.926	8	\N	false
\.


--
-- TOC entry 2303 (class 0 OID 61416)
-- Dependencies: 1492
-- Data for Name: suppliergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY suppliergroups (description, name, starttime, suppliergroup_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2304 (class 0 OID 61428)
-- Dependencies: 1494
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY suppliers (adresses_id, name, starttime, supplier_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2305 (class 0 OID 61440)
-- Dependencies: 1496
-- Data for Name: suppliers_suppliergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY suppliers_suppliergroups (starttime, supplier_id, supplier_suppliergroup_id, suppliergroup_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2306 (class 0 OID 61449)
-- Dependencies: 1498
-- Data for Name: termine; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY termine ("comment", description, endtermin, open, owner_id, place, starttermin, starttime, status_id, termin_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2307 (class 0 OID 61466)
-- Dependencies: 1500
-- Data for Name: termine_participant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY termine_participant (starttime, termin_id, termine_participant_id, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2308 (class 0 OID 61475)
-- Dependencies: 1502
-- Data for Name: termine_todo_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY termine_todo_user ("comment", owner_id, priority, starttime, todo_id, termine_todo_user_id, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2309 (class 0 OID 61491)
-- Dependencies: 1504
-- Data for Name: termine_todolist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY termine_todolist ("comment", description, name, starttime, status_id, teilnehmer, termine_todolist_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2310 (class 0 OID 61504)
-- Dependencies: 1506
-- Data for Name: termine_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY termine_user ("comment", endtermin, invitor_id, message, starttermin, starttime, termin_id, terminstatus, termine_user_id, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2311 (class 0 OID 61522)
-- Dependencies: 1508
-- Data for Name: terminegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY terminegroups ("comment", endtermin, invitor_id, message, starttermin, starttime, termin_id, terminegroup_id, status_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2312 (class 0 OID 61539)
-- Dependencies: 1510
-- Data for Name: terminestatus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY terminestatus ("comment", description, starttime, status_id, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2313 (class 0 OID 61552)
-- Dependencies: 1512
-- Data for Name: transstatus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY transstatus ("comment", description, starttime, status_id, updatetime, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2314 (class 0 OID 61564)
-- Dependencies: 1514
-- Data for Name: userdata; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY userdata ("comment", data, data_id, data_key, starttime, updatetime, user_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2315 (class 0 OID 61577)
-- Dependencies: 1516
-- Data for Name: usergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usergroups (level_id, name, starttime, updatetime, usergroup_id, user_id, description, "comment", deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2316 (class 0 OID 61589)
-- Dependencies: 1518
-- Data for Name: userlevel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY userlevel (description, level_id, starttime, statuscode, updatetime, deleted) WITH OIDS FROM stdin;
62047	User	1	2007-04-12 09:12:04.736	1	\N	false
62048	Moderator	2	2007-04-12 09:12:04.805	2	\N	false
62049	Admin	3	2007-04-12 09:12:04.811	3	\N	false
\.


--
-- TOC entry 2317 (class 0 OID 61602)
-- Dependencies: 1520
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) WITH OIDS FROM stdin;
62071	1	2007-04-12 09:12:05.251	1	firstname	2007-04-12 22:06:47.817	lastname	0	3	swagner	098f6bcd4621d373cade4e832627b4f6	2007-04-12 09:12:05.286	1	1	1	\N	\N	2007-04-12 09:12:05.284	2007-04-12 09:12:05.284	\N	\N	false
\.


--
-- TOC entry 2318 (class 0 OID 61619)
-- Dependencies: 1522
-- Data for Name: users_usergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users_usergroups ("comment", starttime, users_usergroup_id, updatetime, user_id, usergroup_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2319 (class 0 OID 61633)
-- Dependencies: 1524
-- Data for Name: userwaren; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY userwaren (article_id, "comment", liefer_id, menge, starttime, status, status_id, updatetime, user_id, userwaren_id, zahlungs_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2320 (class 0 OID 61651)
-- Dependencies: 1526
-- Data for Name: zahlungsarten; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY zahlungsarten (beschreibung, "comment", freigeschalten, starttime, updatetime, zahlungs_id, deleted) WITH OIDS FROM stdin;
\.


--
-- TOC entry 2073 (class 2606 OID 61000)
-- Dependencies: 1417 1417
-- Name: pkadresses; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adresses
    ADD CONSTRAINT pkadresses PRIMARY KEY (adresses_id);


--
-- TOC entry 2075 (class 2606 OID 61009)
-- Dependencies: 1419 1419
-- Name: pkadresses_emails; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adresses_emails
    ADD CONSTRAINT pkadresses_emails PRIMARY KEY (adresses_emails_id);


--
-- TOC entry 2077 (class 2606 OID 61018)
-- Dependencies: 1421 1421
-- Name: pkadresses_phones; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adresses_phones
    ADD CONSTRAINT pkadresses_phones PRIMARY KEY (adresses_phone_id);


--
-- TOC entry 2080 (class 2606 OID 61030)
-- Dependencies: 1423 1423
-- Name: pkarticlegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articlegroups
    ADD CONSTRAINT pkarticlegroups PRIMARY KEY (articlegroup_id);


--
-- TOC entry 2083 (class 2606 OID 61043)
-- Dependencies: 1425 1425
-- Name: pkarticles; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT pkarticles PRIMARY KEY (article_id);


--
-- TOC entry 2086 (class 2606 OID 61053)
-- Dependencies: 1427 1427
-- Name: pkarticles_articlegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articles_articlegroups
    ADD CONSTRAINT pkarticles_articlegroups PRIMARY KEY (articles_articlesgroups_id);


--
-- TOC entry 2088 (class 2606 OID 61063)
-- Dependencies: 1429 1429
-- Name: pkarticles_lieferarten; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articles_lieferarten
    ADD CONSTRAINT pkarticles_lieferarten PRIMARY KEY (articles_lieferarten_id);


--
-- TOC entry 2091 (class 2606 OID 61076)
-- Dependencies: 1431 1431
-- Name: pkconfiguration; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY configuration
    ADD CONSTRAINT pkconfiguration PRIMARY KEY (configuration_id);


--
-- TOC entry 2093 (class 2606 OID 61089)
-- Dependencies: 1433 1433
-- Name: pkcontactfreigabe; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactfreigabe
    ADD CONSTRAINT pkcontactfreigabe PRIMARY KEY (freigabe_id);


--
-- TOC entry 2095 (class 2606 OID 61099)
-- Dependencies: 1434 1434
-- Name: pkcontactgroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactgroups
    ADD CONSTRAINT pkcontactgroups PRIMARY KEY (contactgroup_id);


--
-- TOC entry 2097 (class 2606 OID 61108)
-- Dependencies: 1436 1436
-- Name: pkcontactgroups_contacts; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactgroups_contacts
    ADD CONSTRAINT pkcontactgroups_contacts PRIMARY KEY (contactgroups_contact_id);


--
-- TOC entry 2099 (class 2606 OID 61117)
-- Dependencies: 1438 1438
-- Name: pkcontactgroups_conuser; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactgroups_conuser
    ADD CONSTRAINT pkcontactgroups_conuser PRIMARY KEY (contactgroup_user_id);


--
-- TOC entry 2101 (class 2606 OID 61131)
-- Dependencies: 1440 1440
-- Name: pkcontacts; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT pkcontacts PRIMARY KEY (contact_id);


--
-- TOC entry 2103 (class 2606 OID 61140)
-- Dependencies: 1442 1442
-- Name: pkconuser; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY conuser
    ADD CONSTRAINT pkconuser PRIMARY KEY (conuser_id);


--
-- TOC entry 2105 (class 2606 OID 61152)
-- Dependencies: 1444 1444
-- Name: pkemails; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY emails
    ADD CONSTRAINT pkemails PRIMARY KEY (mail_id);


--
-- TOC entry 2107 (class 2606 OID 61164)
-- Dependencies: 1446 1446
-- Name: pkemployeegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employeegroups
    ADD CONSTRAINT pkemployeegroups PRIMARY KEY (employeegroup_id);


--
-- TOC entry 2109 (class 2606 OID 61175)
-- Dependencies: 1448 1448
-- Name: pkemployees; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees
    ADD CONSTRAINT pkemployees PRIMARY KEY (employee_id);


--
-- TOC entry 2111 (class 2606 OID 61184)
-- Dependencies: 1450 1450
-- Name: pkemployees_articlegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_articlegroups
    ADD CONSTRAINT pkemployees_articlegroups PRIMARY KEY (employees_articlegroups_id);


--
-- TOC entry 2113 (class 2606 OID 61193)
-- Dependencies: 1452 1452
-- Name: pkemployees_articles; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_articles
    ADD CONSTRAINT pkemployees_articles PRIMARY KEY (employees_articles_id);


--
-- TOC entry 2115 (class 2606 OID 61202)
-- Dependencies: 1454 1454
-- Name: pkemployees_employeegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_employeegroups
    ADD CONSTRAINT pkemployees_employeegroups PRIMARY KEY (employee_employeegroup_id);


--
-- TOC entry 2117 (class 2606 OID 61211)
-- Dependencies: 1456 1456
-- Name: pkemployees_suppliergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_suppliergroups
    ADD CONSTRAINT pkemployees_suppliergroups PRIMARY KEY (employees_suppliergroups_id);


--
-- TOC entry 2119 (class 2606 OID 61220)
-- Dependencies: 1458 1458
-- Name: pkemployees_suppliers; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_suppliers
    ADD CONSTRAINT pkemployees_suppliers PRIMARY KEY (employees_suplier_id);


--
-- TOC entry 2121 (class 2606 OID 61232)
-- Dependencies: 1460 1460
-- Name: pkfieldlanguage; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fieldlanguage
    ADD CONSTRAINT pkfieldlanguage PRIMARY KEY (language_id);


--
-- TOC entry 2123 (class 2606 OID 61244)
-- Dependencies: 1462 1462
-- Name: pkfieldlanguagesvalues; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fieldlanguagesvalues
    ADD CONSTRAINT pkfieldlanguagesvalues PRIMARY KEY (fieldlanguagesvalues_id);


--
-- TOC entry 2125 (class 2606 OID 61256)
-- Dependencies: 1464 1464
-- Name: pkfieldvalues; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fieldvalues
    ADD CONSTRAINT pkfieldvalues PRIMARY KEY (fieldvalues_id);


--
-- TOC entry 2127 (class 2606 OID 61269)
-- Dependencies: 1466 1466
-- Name: pklieferarten; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lieferarten
    ADD CONSTRAINT pklieferarten PRIMARY KEY (liefer_id);


--
-- TOC entry 2129 (class 2606 OID 61283)
-- Dependencies: 1468 1468
-- Name: pknaviglobal; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY naviglobal
    ADD CONSTRAINT pknaviglobal PRIMARY KEY (global_id);


--
-- TOC entry 2131 (class 2606 OID 61298)
-- Dependencies: 1470 1470
-- Name: pknavimain; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT pknavimain PRIMARY KEY (main_id);


--
-- TOC entry 2133 (class 2606 OID 61313)
-- Dependencies: 1472 1472
-- Name: pknavisub; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT pknavisub PRIMARY KEY (sub_id);


--
-- TOC entry 2135 (class 2606 OID 61324)
-- Dependencies: 1474 1474
-- Name: pkorganisation; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisation
    ADD CONSTRAINT pkorganisation PRIMARY KEY (organisation_id);


--
-- TOC entry 2137 (class 2606 OID 61336)
-- Dependencies: 1476 1476
-- Name: pkorganisation_users; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisation_users
    ADD CONSTRAINT pkorganisation_users PRIMARY KEY (organisation_users_id);


--
-- TOC entry 2139 (class 2606 OID 61348)
-- Dependencies: 1478 1478
-- Name: pkphones; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY phones
    ADD CONSTRAINT pkphones PRIMARY KEY (phone_id);


--
-- TOC entry 2141 (class 2606 OID 61360)
-- Dependencies: 1480 1480
-- Name: pkrooms; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rooms
    ADD CONSTRAINT pkrooms PRIMARY KEY (rooms_id);


--
-- TOC entry 2143 (class 2606 OID 61368)
-- Dependencies: 1482 1482
-- Name: pkrooms_organisation; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rooms_organisation
    ADD CONSTRAINT pkrooms_organisation PRIMARY KEY (rooms_organisation_id);


--
-- TOC entry 2145 (class 2606 OID 61379)
-- Dependencies: 1484 1484
-- Name: pkroomtypes; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY roomtypes
    ADD CONSTRAINT pkroomtypes PRIMARY KEY (roomtypes_id);


--
-- TOC entry 2147 (class 2606 OID 61391)
-- Dependencies: 1486 1486
-- Name: pksalutations; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY salutations
    ADD CONSTRAINT pksalutations PRIMARY KEY (salutations_id);


--
-- TOC entry 2149 (class 2606 OID 61401)
-- Dependencies: 1488 1488
-- Name: pksessiondata; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sessiondata
    ADD CONSTRAINT pksessiondata PRIMARY KEY (id);


--
-- TOC entry 2151 (class 2606 OID 61413)
-- Dependencies: 1490 1490
-- Name: pkstates; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY states
    ADD CONSTRAINT pkstates PRIMARY KEY (state_id);


--
-- TOC entry 2153 (class 2606 OID 61425)
-- Dependencies: 1492 1492
-- Name: pksuppliergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY suppliergroups
    ADD CONSTRAINT pksuppliergroups PRIMARY KEY (suppliergroup_id);


--
-- TOC entry 2155 (class 2606 OID 61437)
-- Dependencies: 1494 1494
-- Name: pksuppliers; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pksuppliers PRIMARY KEY (supplier_id);


--
-- TOC entry 2157 (class 2606 OID 61446)
-- Dependencies: 1496 1496
-- Name: pksuppliers_suppliergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY suppliers_suppliergroups
    ADD CONSTRAINT pksuppliers_suppliergroups PRIMARY KEY (supplier_suppliergroup_id);


--
-- TOC entry 2159 (class 2606 OID 61463)
-- Dependencies: 1498 1498
-- Name: pktermine; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine
    ADD CONSTRAINT pktermine PRIMARY KEY (termin_id);


--
-- TOC entry 2161 (class 2606 OID 61472)
-- Dependencies: 1500 1500
-- Name: pktermine_participant; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_participant
    ADD CONSTRAINT pktermine_participant PRIMARY KEY (termine_participant_id);


--
-- TOC entry 2163 (class 2606 OID 61488)
-- Dependencies: 1502 1502
-- Name: pktermine_todo_user; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT pktermine_todo_user PRIMARY KEY (termine_todo_user_id);


--
-- TOC entry 2165 (class 2606 OID 61501)
-- Dependencies: 1504 1504
-- Name: pktermine_todolist; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_todolist
    ADD CONSTRAINT pktermine_todolist PRIMARY KEY (termine_todolist_id);


--
-- TOC entry 2167 (class 2606 OID 61519)
-- Dependencies: 1506 1506
-- Name: pktermine_user; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_user
    ADD CONSTRAINT pktermine_user PRIMARY KEY (termine_user_id);


--
-- TOC entry 2169 (class 2606 OID 61536)
-- Dependencies: 1508 1508
-- Name: pkterminegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY terminegroups
    ADD CONSTRAINT pkterminegroups PRIMARY KEY (terminegroup_id);


--
-- TOC entry 2171 (class 2606 OID 61549)
-- Dependencies: 1510 1510
-- Name: pkterminestatus; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY terminestatus
    ADD CONSTRAINT pkterminestatus PRIMARY KEY (status_id);


--
-- TOC entry 2173 (class 2606 OID 61561)
-- Dependencies: 1512 1512
-- Name: pktransstatus; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transstatus
    ADD CONSTRAINT pktransstatus PRIMARY KEY (status_id);


--
-- TOC entry 2175 (class 2606 OID 61574)
-- Dependencies: 1514 1514
-- Name: pkuserdata; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userdata
    ADD CONSTRAINT pkuserdata PRIMARY KEY (data_id);


--
-- TOC entry 2177 (class 2606 OID 61586)
-- Dependencies: 1516 1516
-- Name: pkusergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usergroups
    ADD CONSTRAINT pkusergroups PRIMARY KEY (usergroup_id);


--
-- TOC entry 2179 (class 2606 OID 61599)
-- Dependencies: 1518 1518
-- Name: pkuserlevel; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userlevel
    ADD CONSTRAINT pkuserlevel PRIMARY KEY (level_id);


--
-- TOC entry 2181 (class 2606 OID 61616)
-- Dependencies: 1520 1520
-- Name: pkusers; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pkusers PRIMARY KEY (user_id);


--
-- TOC entry 2183 (class 2606 OID 61630)
-- Dependencies: 1522 1522
-- Name: pkusers_usergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users_usergroups
    ADD CONSTRAINT pkusers_usergroups PRIMARY KEY (users_usergroup_id);


--
-- TOC entry 2185 (class 2606 OID 61648)
-- Dependencies: 1524 1524
-- Name: pkuserwaren; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT pkuserwaren PRIMARY KEY (userwaren_id);


--
-- TOC entry 2187 (class 2606 OID 61661)
-- Dependencies: 1526 1526
-- Name: pkzahlungsarten; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY zahlungsarten
    ADD CONSTRAINT pkzahlungsarten PRIMARY KEY (zahlungs_id);


--
-- TOC entry 2078 (class 1259 OID 61031)
-- Dependencies: 1423
-- Name: articlegroups_articlegroup_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX articlegroups_articlegroup_id_idx ON articlegroups USING btree (articlegroup_id);


--
-- TOC entry 2081 (class 1259 OID 61044)
-- Dependencies: 1425
-- Name: articles_article_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX articles_article_id_idx ON articles USING btree (article_id);


--
-- TOC entry 2084 (class 1259 OID 61054)
-- Dependencies: 1427
-- Name: articles_articlegroups_articles_articlesgroups_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX articles_articlegroups_articles_articlesgroups_id_idx ON articles_articlegroups USING btree (articles_articlesgroups_id);


--
-- TOC entry 2089 (class 1259 OID 61077)
-- Dependencies: 1431
-- Name: configuration_configuration_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX configuration_configuration_id_idx ON configuration USING btree (configuration_id);


--
-- TOC entry 2190 (class 2606 OID 61667)
-- Dependencies: 1419 1417 2072
-- Name: fk_adresses_emails_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_emails
    ADD CONSTRAINT fk_adresses_emails_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2189 (class 2606 OID 61672)
-- Dependencies: 1444 1419 2104
-- Name: fk_adresses_emails_emails; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_emails
    ADD CONSTRAINT fk_adresses_emails_emails FOREIGN KEY (mail_id) REFERENCES emails(mail_id);


--
-- TOC entry 2192 (class 2606 OID 61677)
-- Dependencies: 1417 1421 2072
-- Name: fk_adresses_phones_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_phones
    ADD CONSTRAINT fk_adresses_phones_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2191 (class 2606 OID 61682)
-- Dependencies: 1421 1478 2138
-- Name: fk_adresses_phones_phones; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_phones
    ADD CONSTRAINT fk_adresses_phones_phones FOREIGN KEY (phone_id) REFERENCES phones(phone_id);


--
-- TOC entry 2188 (class 2606 OID 61662)
-- Dependencies: 1417 1490 2150
-- Name: fk_adresses_states; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses
    ADD CONSTRAINT fk_adresses_states FOREIGN KEY (state_id) REFERENCES states(state_id);


--
-- TOC entry 2195 (class 2606 OID 61692)
-- Dependencies: 1423 1427 2079
-- Name: fk_articles_articlegroups_articlegroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_articlegroups
    ADD CONSTRAINT fk_articles_articlegroups_articlegroups FOREIGN KEY (articlegroup_id) REFERENCES articlegroups(articlegroup_id);


--
-- TOC entry 2194 (class 2606 OID 61697)
-- Dependencies: 1425 1427 2082
-- Name: fk_articles_articlegroups_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_articlegroups
    ADD CONSTRAINT fk_articles_articlegroups_articles FOREIGN KEY (article_id) REFERENCES articles(article_id);


--
-- TOC entry 2197 (class 2606 OID 61702)
-- Dependencies: 1425 1429 2082
-- Name: fk_articles_lieferarten_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_lieferarten
    ADD CONSTRAINT fk_articles_lieferarten_articles FOREIGN KEY (articles_id) REFERENCES articles(article_id);


--
-- TOC entry 2196 (class 2606 OID 61707)
-- Dependencies: 1466 1429 2126
-- Name: fk_articles_lieferarten_lieferarten; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_lieferarten
    ADD CONSTRAINT fk_articles_lieferarten_lieferarten FOREIGN KEY (liefer_id) REFERENCES lieferarten(liefer_id);


--
-- TOC entry 2193 (class 2606 OID 61687)
-- Dependencies: 2154 1425 1494
-- Name: fk_articles_suppliers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT fk_articles_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id);


--
-- TOC entry 2198 (class 2606 OID 61712)
-- Dependencies: 1520 1431 2180
-- Name: fk_configuration_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuration
    ADD CONSTRAINT fk_configuration_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2199 (class 2606 OID 61717)
-- Dependencies: 2092 1434 1433
-- Name: fk_contactgroups_contactfreigabe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups
    ADD CONSTRAINT fk_contactgroups_contactfreigabe FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe(freigabe_id);


--
-- TOC entry 2201 (class 2606 OID 61722)
-- Dependencies: 1434 1436 2094
-- Name: fk_contactgroups_contacts_contactgroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_contacts
    ADD CONSTRAINT fk_contactgroups_contacts_contactgroups FOREIGN KEY (contactgroup_id) REFERENCES contactgroups(contactgroup_id);


--
-- TOC entry 2200 (class 2606 OID 61727)
-- Dependencies: 1440 2100 1436
-- Name: fk_contactgroups_contacts_contacts; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_contacts
    ADD CONSTRAINT fk_contactgroups_contacts_contacts FOREIGN KEY (contact_id) REFERENCES contacts(contact_id);


--
-- TOC entry 2203 (class 2606 OID 61732)
-- Dependencies: 1434 2094 1438
-- Name: fk_contactgroups_conuser_contactgroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_conuser
    ADD CONSTRAINT fk_contactgroups_conuser_contactgroups FOREIGN KEY (contactgroup_id) REFERENCES contactgroups(contactgroup_id);


--
-- TOC entry 2202 (class 2606 OID 61737)
-- Dependencies: 1438 1442 2102
-- Name: fk_contactgroups_conuser_conuser; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_conuser
    ADD CONSTRAINT fk_contactgroups_conuser_conuser FOREIGN KEY (conuser_id) REFERENCES conuser(conuser_id);


--
-- TOC entry 2207 (class 2606 OID 61742)
-- Dependencies: 1440 1417 2072
-- Name: fk_contacts_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2206 (class 2606 OID 61747)
-- Dependencies: 1440 2092 1433
-- Name: fk_contacts_contactfreigabe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_contactfreigabe FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe(freigabe_id);


--
-- TOC entry 2205 (class 2606 OID 61752)
-- Dependencies: 1440 1486 2146
-- Name: fk_contacts_titles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_titles FOREIGN KEY (titel_id) REFERENCES salutations(salutations_id);


--
-- TOC entry 2204 (class 2606 OID 61757)
-- Dependencies: 1520 2180 1440
-- Name: fk_contacts_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2209 (class 2606 OID 61762)
-- Dependencies: 2092 1442 1433
-- Name: fk_conuser_contactfreigabe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conuser
    ADD CONSTRAINT fk_conuser_contactfreigabe FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe(freigabe_id);


--
-- TOC entry 2208 (class 2606 OID 61767)
-- Dependencies: 2180 1442 1520
-- Name: fk_conuser_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conuser
    ADD CONSTRAINT fk_conuser_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2212 (class 2606 OID 61777)
-- Dependencies: 2079 1423 1450
-- Name: fk_employees_articlegroups_articlegroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articlegroups
    ADD CONSTRAINT fk_employees_articlegroups_articlegroups FOREIGN KEY (articlegroup_id) REFERENCES articlegroups(articlegroup_id);


--
-- TOC entry 2211 (class 2606 OID 61782)
-- Dependencies: 1448 1450 2108
-- Name: fk_employees_articlegroups_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articlegroups
    ADD CONSTRAINT fk_employees_articlegroups_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2214 (class 2606 OID 61787)
-- Dependencies: 1452 1425 2082
-- Name: fk_employees_articles_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articles
    ADD CONSTRAINT fk_employees_articles_articles FOREIGN KEY (article_id) REFERENCES articles(article_id);


--
-- TOC entry 2213 (class 2606 OID 61792)
-- Dependencies: 2108 1452 1448
-- Name: fk_employees_articles_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articles
    ADD CONSTRAINT fk_employees_articles_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2216 (class 2606 OID 61797)
-- Dependencies: 1446 2106 1454
-- Name: fk_employees_employeegroups_employeegroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_employeegroups
    ADD CONSTRAINT fk_employees_employeegroups_employeegroups FOREIGN KEY (employeegroup_id) REFERENCES employeegroups(employeegroup_id);


--
-- TOC entry 2215 (class 2606 OID 61802)
-- Dependencies: 1454 1448 2108
-- Name: fk_employees_employeegroups_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_employeegroups
    ADD CONSTRAINT fk_employees_employeegroups_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2218 (class 2606 OID 61807)
-- Dependencies: 2108 1456 1448
-- Name: fk_employees_suppliergroups_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliergroups
    ADD CONSTRAINT fk_employees_suppliergroups_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2217 (class 2606 OID 61812)
-- Dependencies: 2152 1492 1456
-- Name: fk_employees_suppliergroups_suppliergroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliergroups
    ADD CONSTRAINT fk_employees_suppliergroups_suppliergroups FOREIGN KEY (employees_suppliergroups_id) REFERENCES suppliergroups(suppliergroup_id);


--
-- TOC entry 2220 (class 2606 OID 61817)
-- Dependencies: 2108 1458 1448
-- Name: fk_employees_suppliers_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliers
    ADD CONSTRAINT fk_employees_suppliers_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2219 (class 2606 OID 61822)
-- Dependencies: 2154 1458 1494
-- Name: fk_employees_suppliers_suppliers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliers
    ADD CONSTRAINT fk_employees_suppliers_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id);


--
-- TOC entry 2210 (class 2606 OID 61772)
-- Dependencies: 1520 1448 2180
-- Name: fk_employees_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees
    ADD CONSTRAINT fk_employees_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2222 (class 2606 OID 61827)
-- Dependencies: 1462 1460 2120
-- Name: fk_fieldvalues_fieldlanguagesvalues_fieldlanguage; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fieldlanguagesvalues
    ADD CONSTRAINT fk_fieldvalues_fieldlanguagesvalues_fieldlanguage FOREIGN KEY (language_id) REFERENCES fieldlanguage(language_id);


--
-- TOC entry 2221 (class 2606 OID 61832)
-- Dependencies: 1464 1462 2124
-- Name: fk_fieldvalues_fieldlanguagesvalues_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fieldlanguagesvalues
    ADD CONSTRAINT fk_fieldvalues_fieldlanguagesvalues_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2224 (class 2606 OID 61837)
-- Dependencies: 1464 2124 1468
-- Name: fk_naviglobal_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY naviglobal
    ADD CONSTRAINT fk_naviglobal_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2223 (class 2606 OID 61842)
-- Dependencies: 1468 1518 2178
-- Name: fk_naviglobal_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY naviglobal
    ADD CONSTRAINT fk_naviglobal_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2227 (class 2606 OID 61847)
-- Dependencies: 1470 2124 1464
-- Name: fk_navimain_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT fk_navimain_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2226 (class 2606 OID 61852)
-- Dependencies: 1470 1468 2128
-- Name: fk_navimain_naviglobal; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT fk_navimain_naviglobal FOREIGN KEY (global_id) REFERENCES naviglobal(global_id);


--
-- TOC entry 2225 (class 2606 OID 61857)
-- Dependencies: 2178 1518 1470
-- Name: fk_navimain_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT fk_navimain_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2230 (class 2606 OID 61862)
-- Dependencies: 1472 2124 1464
-- Name: fk_navisub_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT fk_navisub_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2229 (class 2606 OID 61867)
-- Dependencies: 1472 2130 1470
-- Name: fk_navisub_navimain; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT fk_navisub_navimain FOREIGN KEY (main_id) REFERENCES navimain(main_id);


--
-- TOC entry 2228 (class 2606 OID 61872)
-- Dependencies: 2178 1472 1518
-- Name: fk_navisub_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT fk_navisub_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2232 (class 2606 OID 61877)
-- Dependencies: 2134 1474 1476
-- Name: fk_organisation_users_organisation; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisation_users
    ADD CONSTRAINT fk_organisation_users_organisation FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id);


--
-- TOC entry 2231 (class 2606 OID 61882)
-- Dependencies: 1520 1476 2180
-- Name: fk_organisation_users_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisation_users
    ADD CONSTRAINT fk_organisation_users_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2235 (class 2606 OID 61892)
-- Dependencies: 1482 2134 1474
-- Name: fk_rooms_organisation_organisation; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rooms_organisation
    ADD CONSTRAINT fk_rooms_organisation_organisation FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id);


--
-- TOC entry 2234 (class 2606 OID 61897)
-- Dependencies: 2140 1482 1480
-- Name: fk_rooms_organisation_rooms; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rooms_organisation
    ADD CONSTRAINT fk_rooms_organisation_rooms FOREIGN KEY (rooms_id) REFERENCES rooms(rooms_id);


--
-- TOC entry 2233 (class 2606 OID 61887)
-- Dependencies: 1484 2144 1480
-- Name: fk_rooms_roomtypes; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rooms
    ADD CONSTRAINT fk_rooms_roomtypes FOREIGN KEY (roomtypes_id) REFERENCES roomtypes(roomtypes_id);


--
-- TOC entry 2236 (class 2606 OID 61902)
-- Dependencies: 2072 1494 1417
-- Name: fk_suppliers_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT fk_suppliers_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2238 (class 2606 OID 61907)
-- Dependencies: 2152 1492 1496
-- Name: fk_suppliers_suppliergroups_suppliergroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY suppliers_suppliergroups
    ADD CONSTRAINT fk_suppliers_suppliergroups_suppliergroups FOREIGN KEY (suppliergroup_id) REFERENCES suppliergroups(suppliergroup_id);


--
-- TOC entry 2237 (class 2606 OID 61912)
-- Dependencies: 2154 1494 1496
-- Name: fk_suppliers_suppliergroups_suppliers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY suppliers_suppliergroups
    ADD CONSTRAINT fk_suppliers_suppliergroups_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id);


--
-- TOC entry 2242 (class 2606 OID 61927)
-- Dependencies: 1500 1498 2158
-- Name: fk_termine_participant_termine; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_participant
    ADD CONSTRAINT fk_termine_participant_termine FOREIGN KEY (termin_id) REFERENCES termine(termin_id);


--
-- TOC entry 2241 (class 2606 OID 61932)
-- Dependencies: 1500 1520 2180
-- Name: fk_termine_participant_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_participant
    ADD CONSTRAINT fk_termine_participant_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2240 (class 2606 OID 61917)
-- Dependencies: 1510 1498 2170
-- Name: fk_termine_terminestatus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine
    ADD CONSTRAINT fk_termine_terminestatus FOREIGN KEY (status_id) REFERENCES terminestatus(status_id);


--
-- TOC entry 2245 (class 2606 OID 61937)
-- Dependencies: 1502 1520 2180
-- Name: fk_termine_todo_user_owner_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT fk_termine_todo_user_owner_users FOREIGN KEY (owner_id) REFERENCES users(user_id);


--
-- TOC entry 2244 (class 2606 OID 61942)
-- Dependencies: 1502 1504 2164
-- Name: fk_termine_todo_user_termine_todolist; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT fk_termine_todo_user_termine_todolist FOREIGN KEY (todo_id) REFERENCES termine_todolist(termine_todolist_id);


--
-- TOC entry 2243 (class 2606 OID 61947)
-- Dependencies: 1502 1520 2180
-- Name: fk_termine_todo_user_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT fk_termine_todo_user_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2247 (class 2606 OID 61952)
-- Dependencies: 1506 2158 1498
-- Name: fk_termine_user_termine; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_user
    ADD CONSTRAINT fk_termine_user_termine FOREIGN KEY (termin_id) REFERENCES termine(termin_id);


--
-- TOC entry 2246 (class 2606 OID 61957)
-- Dependencies: 2180 1520 1506
-- Name: fk_termine_user_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_user
    ADD CONSTRAINT fk_termine_user_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2239 (class 2606 OID 61922)
-- Dependencies: 1498 1520 2180
-- Name: fk_termine_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine
    ADD CONSTRAINT fk_termine_users FOREIGN KEY (owner_id) REFERENCES users(user_id);


--
-- TOC entry 2249 (class 2606 OID 61962)
-- Dependencies: 1508 2158 1498
-- Name: fk_terminegroups_termine; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY terminegroups
    ADD CONSTRAINT fk_terminegroups_termine FOREIGN KEY (termin_id) REFERENCES termine(termin_id);


--
-- TOC entry 2248 (class 2606 OID 61967)
-- Dependencies: 1508 2170 1510
-- Name: fk_terminegroups_terminestatus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY terminegroups
    ADD CONSTRAINT fk_terminegroups_terminestatus FOREIGN KEY (status_id) REFERENCES terminestatus(status_id);


--
-- TOC entry 2250 (class 2606 OID 61972)
-- Dependencies: 1520 2180 1514
-- Name: fk_userdata_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userdata
    ADD CONSTRAINT fk_userdata_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2251 (class 2606 OID 61977)
-- Dependencies: 2178 1516 1518
-- Name: fk_usergroups_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usergroups
    ADD CONSTRAINT fk_usergroups_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2259 (class 2606 OID 62012)
-- Dependencies: 2180 1522 1520
-- Name: fk_usergroups_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_usergroups
    ADD CONSTRAINT fk_usergroups_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2257 (class 2606 OID 61982)
-- Dependencies: 1417 2072 1520
-- Name: fk_users_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2256 (class 2606 OID 61987)
-- Dependencies: 1417 1520 2072
-- Name: fk_users_delivery_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_delivery_adresses FOREIGN KEY (delivery_adress_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2255 (class 2606 OID 61992)
-- Dependencies: 1417 1520 2072
-- Name: fk_users_domicile_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_domicile_adresses FOREIGN KEY (domicile_adress_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2254 (class 2606 OID 61997)
-- Dependencies: 1460 2120 1520
-- Name: fk_users_fieldlanguage; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_fieldlanguage FOREIGN KEY (language_id) REFERENCES fieldlanguage(language_id);


--
-- TOC entry 2253 (class 2606 OID 62002)
-- Dependencies: 2146 1486 1520
-- Name: fk_users_titles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_titles FOREIGN KEY (title_id) REFERENCES salutations(salutations_id);


--
-- TOC entry 2258 (class 2606 OID 62017)
-- Dependencies: 2176 1522 1516
-- Name: fk_users_usergroups_usergroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_usergroups
    ADD CONSTRAINT fk_users_usergroups_usergroups FOREIGN KEY (usergroup_id) REFERENCES usergroups(usergroup_id);


--
-- TOC entry 2252 (class 2606 OID 62007)
-- Dependencies: 2178 1518 1520
-- Name: fk_users_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2264 (class 2606 OID 62022)
-- Dependencies: 1524 1425 2082
-- Name: fk_userwaren_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_articles FOREIGN KEY (article_id) REFERENCES articles(article_id);


--
-- TOC entry 2263 (class 2606 OID 62027)
-- Dependencies: 1524 2126 1466
-- Name: fk_userwaren_lieferarten; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_lieferarten FOREIGN KEY (liefer_id) REFERENCES lieferarten(liefer_id);


--
-- TOC entry 2262 (class 2606 OID 62032)
-- Dependencies: 1512 1524 2172
-- Name: fk_userwaren_transstatus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_transstatus FOREIGN KEY (status_id) REFERENCES transstatus(status_id);


--
-- TOC entry 2261 (class 2606 OID 62037)
-- Dependencies: 1524 1520 2180
-- Name: fk_userwaren_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2260 (class 2606 OID 62042)
-- Dependencies: 2186 1524 1526
-- Name: fk_userwaren_zahlungsarten; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_zahlungsarten FOREIGN KEY (zahlungs_id) REFERENCES zahlungsarten(zahlungs_id);


-- Completed on 2007-04-12 23:30:25

--
-- PostgreSQL database dump complete
--

