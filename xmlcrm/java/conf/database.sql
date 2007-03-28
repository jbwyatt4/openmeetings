--
-- PostgreSQL database dump
--

-- Started on 2007-03-28 17:31:39

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = true;

--
-- TOC entry 1400 (class 1259 OID 36064)
-- Dependencies: 1834 1835 1836 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.adresses OWNER TO postgres;

--
-- TOC entry 1399 (class 1259 OID 36062)
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
-- TOC entry 2282 (class 0 OID 0)
-- Dependencies: 1399
-- Name: adresses_adresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('adresses_adresses_id_seq', 1, false);


--
-- TOC entry 1402 (class 1259 OID 36076)
-- Dependencies: 1837 1838 1839 5
-- Name: adresses_emails; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE adresses_emails (
    adresses_emails_id bigint DEFAULT nextval(('adresses_emails_ADRESSES_EMAILS_ID_seq'::text)::regclass) NOT NULL,
    adresses_id bigint NOT NULL,
    mail_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.adresses_emails OWNER TO postgres;

--
-- TOC entry 1401 (class 1259 OID 36074)
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
-- TOC entry 2283 (class 0 OID 0)
-- Dependencies: 1401
-- Name: adresses_emails_adresses_emails_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('adresses_emails_adresses_emails_id_seq', 1, false);


--
-- TOC entry 1404 (class 1259 OID 36085)
-- Dependencies: 1840 1841 1842 5
-- Name: adresses_phones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE adresses_phones (
    adresses_id bigint NOT NULL,
    adresses_phone_id bigint DEFAULT nextval(('adresses_phones_ADRESSES_PHONE_ID_seq'::text)::regclass) NOT NULL,
    phone_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.adresses_phones OWNER TO postgres;

--
-- TOC entry 1403 (class 1259 OID 36083)
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
-- TOC entry 2284 (class 0 OID 0)
-- Dependencies: 1403
-- Name: adresses_phones_adresses_phone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('adresses_phones_adresses_phone_id_seq', 1, false);


--
-- TOC entry 1406 (class 1259 OID 36094)
-- Dependencies: 1843 1844 1845 5
-- Name: articlegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articlegroups (
    articlegroup_id bigint DEFAULT nextval(('articlegroups_ARTICLEGROUP_ID_seq'::text)::regclass) NOT NULL,
    name character varying(255),
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.articlegroups OWNER TO postgres;

--
-- TOC entry 1405 (class 1259 OID 36092)
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
-- TOC entry 2285 (class 0 OID 0)
-- Dependencies: 1405
-- Name: articlegroups_articlegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articlegroups_articlegroup_id_seq', 1, false);


--
-- TOC entry 1408 (class 1259 OID 36104)
-- Dependencies: 1846 1847 1848 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.articles OWNER TO postgres;

--
-- TOC entry 1407 (class 1259 OID 36102)
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
-- TOC entry 2286 (class 0 OID 0)
-- Dependencies: 1407
-- Name: articles_article_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articles_article_id_seq', 1, false);


--
-- TOC entry 1410 (class 1259 OID 36117)
-- Dependencies: 1849 1850 1851 5
-- Name: articles_articlegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articles_articlegroups (
    article_id bigint NOT NULL,
    articlegroup_id bigint NOT NULL,
    articles_articlesgroups_id bigint DEFAULT nextval(('articles_articlegroups_ARTICLES_ARTICLESGROUPS_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.articles_articlegroups OWNER TO postgres;

--
-- TOC entry 1409 (class 1259 OID 36115)
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
-- TOC entry 2287 (class 0 OID 0)
-- Dependencies: 1409
-- Name: articles_articlegroups_articles_articlesgroups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articles_articlegroups_articles_articlesgroups_id_seq', 1, false);


--
-- TOC entry 1412 (class 1259 OID 36127)
-- Dependencies: 1852 1853 1854 5
-- Name: articles_lieferarten; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articles_lieferarten (
    articles_id bigint NOT NULL,
    articles_lieferarten_id bigint DEFAULT nextval(('articles_lieferarten_ARTICLES_LIEFERARTEN_ID_seq'::text)::regclass) NOT NULL,
    liefer_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.articles_lieferarten OWNER TO postgres;

--
-- TOC entry 1411 (class 1259 OID 36125)
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
-- TOC entry 2288 (class 0 OID 0)
-- Dependencies: 1411
-- Name: articles_lieferarten_articles_lieferarten_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articles_lieferarten_articles_lieferarten_id_seq', 1, false);


--
-- TOC entry 1414 (class 1259 OID 36136)
-- Dependencies: 1855 1856 1857 1858 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.configuration OWNER TO postgres;

--
-- TOC entry 1413 (class 1259 OID 36134)
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
-- TOC entry 2289 (class 0 OID 0)
-- Dependencies: 1413
-- Name: configuration_configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('configuration_configuration_id_seq', 1, false);


--
-- TOC entry 1416 (class 1259 OID 36150)
-- Dependencies: 1859 1860 1861 5
-- Name: contactfreigabe; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactfreigabe (
    "comment" text,
    description text,
    freigabe_id bigint DEFAULT nextval(('contactfreigabe_FREIGABE_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.contactfreigabe OWNER TO postgres;

--
-- TOC entry 1415 (class 1259 OID 36148)
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
-- TOC entry 2290 (class 0 OID 0)
-- Dependencies: 1415
-- Name: contactfreigabe_freigabe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contactfreigabe_freigabe_id_seq', 1, false);


--
-- TOC entry 1417 (class 1259 OID 36160)
-- Dependencies: 1862 1863 1864 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.contactgroups OWNER TO postgres;

--
-- TOC entry 1419 (class 1259 OID 36172)
-- Dependencies: 1865 1866 1867 5
-- Name: contactgroups_contacts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactgroups_contacts (
    contact_id bigint NOT NULL,
    contactgroup_id bigint NOT NULL,
    contactgroups_contact_id bigint DEFAULT nextval(('contactgroups_contacts_CONTACTGROUPS_CONTACT_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.contactgroups_contacts OWNER TO postgres;

--
-- TOC entry 1418 (class 1259 OID 36170)
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
-- TOC entry 2291 (class 0 OID 0)
-- Dependencies: 1418
-- Name: contactgroups_contacts_contactgroups_contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contactgroups_contacts_contactgroups_contact_id_seq', 1, false);


--
-- TOC entry 1421 (class 1259 OID 36181)
-- Dependencies: 1868 1869 1870 5
-- Name: contactgroups_conuser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactgroups_conuser (
    contactgroup_id bigint NOT NULL,
    contactgroup_user_id bigint DEFAULT nextval(('contactgroups_conuser_CONTACTGROUP_USER_ID_seq'::text)::regclass) NOT NULL,
    conuser_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.contactgroups_conuser OWNER TO postgres;

--
-- TOC entry 1420 (class 1259 OID 36179)
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
-- TOC entry 2292 (class 0 OID 0)
-- Dependencies: 1420
-- Name: contactgroups_conuser_contactgroup_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contactgroups_conuser_contactgroup_user_id_seq', 1, false);


--
-- TOC entry 1423 (class 1259 OID 36190)
-- Dependencies: 1871 1872 1873 1874 1875 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.contacts OWNER TO postgres;

--
-- TOC entry 1422 (class 1259 OID 36188)
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
-- TOC entry 2293 (class 0 OID 0)
-- Dependencies: 1422
-- Name: contacts_contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contacts_contact_id_seq', 1, false);


--
-- TOC entry 1425 (class 1259 OID 36204)
-- Dependencies: 1876 1877 1878 5
-- Name: conuser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE conuser (
    conuser_id bigint DEFAULT nextval(('conuser_CONUSER_ID_seq'::text)::regclass) NOT NULL,
    freigabe_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL,
    deleted boolean DEFAULT false
);


ALTER TABLE public.conuser OWNER TO postgres;

--
-- TOC entry 1424 (class 1259 OID 36202)
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
-- TOC entry 2294 (class 0 OID 0)
-- Dependencies: 1424
-- Name: conuser_conuser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('conuser_conuser_id_seq', 1, false);


--
-- TOC entry 1427 (class 1259 OID 36213)
-- Dependencies: 1879 1880 1881 5
-- Name: emails; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE emails (
    "comment" text NOT NULL,
    email character varying(128) NOT NULL,
    mail_id bigint DEFAULT nextval(('emails_MAIL_ID_seq'::text)::regclass) NOT NULL,
    startdate timestamp without time zone DEFAULT now() NOT NULL,
    updatedate timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.emails OWNER TO postgres;

--
-- TOC entry 1426 (class 1259 OID 36211)
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
-- TOC entry 2295 (class 0 OID 0)
-- Dependencies: 1426
-- Name: emails_mail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('emails_mail_id_seq', 1, false);


--
-- TOC entry 1429 (class 1259 OID 36225)
-- Dependencies: 1882 1883 1884 5
-- Name: employeegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employeegroups (
    "comment" text NOT NULL,
    employeegroup_id bigint DEFAULT nextval(('employeegroups_EMPLOYEEGROUP_ID_seq'::text)::regclass) NOT NULL,
    name text NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.employeegroups OWNER TO postgres;

--
-- TOC entry 1428 (class 1259 OID 36223)
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
-- TOC entry 2296 (class 0 OID 0)
-- Dependencies: 1428
-- Name: employeegroups_employeegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employeegroups_employeegroup_id_seq', 1, false);


--
-- TOC entry 1431 (class 1259 OID 36237)
-- Dependencies: 1885 1886 5
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees (
    "comment" text NOT NULL,
    employee_id bigint DEFAULT nextval(('employees_EMPLOYEE_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL,
    deleted boolean
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 1433 (class 1259 OID 36248)
-- Dependencies: 1887 1888 1889 5
-- Name: employees_articlegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_articlegroups (
    articlegroup_id bigint NOT NULL,
    employee_id bigint NOT NULL,
    employees_articlegroups_id bigint DEFAULT nextval(('employees_articlegroups_EMPLOYEES_ARTICLEGROUPS_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.employees_articlegroups OWNER TO postgres;

--
-- TOC entry 1432 (class 1259 OID 36246)
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
-- TOC entry 2297 (class 0 OID 0)
-- Dependencies: 1432
-- Name: employees_articlegroups_employees_articlegroups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_articlegroups_employees_articlegroups_id_seq', 1, false);


--
-- TOC entry 1435 (class 1259 OID 36257)
-- Dependencies: 1890 1891 1892 5
-- Name: employees_articles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_articles (
    article_id bigint NOT NULL,
    employee_id bigint NOT NULL,
    employees_articles_id bigint DEFAULT nextval(('employees_articles_EMPLOYEES_ARTICLES_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.employees_articles OWNER TO postgres;

--
-- TOC entry 1434 (class 1259 OID 36255)
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
-- TOC entry 2298 (class 0 OID 0)
-- Dependencies: 1434
-- Name: employees_articles_employees_articles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_articles_employees_articles_id_seq', 1, false);


--
-- TOC entry 1430 (class 1259 OID 36235)
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
-- TOC entry 2299 (class 0 OID 0)
-- Dependencies: 1430
-- Name: employees_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_employee_id_seq', 1, false);


--
-- TOC entry 1437 (class 1259 OID 36266)
-- Dependencies: 1893 1894 1895 5
-- Name: employees_employeegroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_employeegroups (
    employee_employeegroup_id bigint DEFAULT nextval(('employees_employeegroups_EMPLOYEE_EMPLOYEEGROUP_ID_seq'::text)::regclass) NOT NULL,
    employee_id bigint NOT NULL,
    employeegroup_id bigint NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.employees_employeegroups OWNER TO postgres;

--
-- TOC entry 1436 (class 1259 OID 36264)
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
-- TOC entry 2300 (class 0 OID 0)
-- Dependencies: 1436
-- Name: employees_employeegroups_employee_employeegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_employeegroups_employee_employeegroup_id_seq', 1, false);


--
-- TOC entry 1438 (class 1259 OID 36273)
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
-- TOC entry 2301 (class 0 OID 0)
-- Dependencies: 1438
-- Name: employees_suppliergroups_employees_suppliergroups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_suppliergroups_employees_suppliergroups_id_seq', 1, false);


SET default_with_oids = false;

--
-- TOC entry 1439 (class 1259 OID 36275)
-- Dependencies: 1896 1897 1898 5
-- Name: employees_suppliergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_suppliergroups (
    deleted boolean DEFAULT false,
    employee_id bigint NOT NULL,
    employees_suppliergroups_id bigint DEFAULT nextval('employees_suppliergroups_employees_suppliergroups_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    suppliergroup_id bigint NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.employees_suppliergroups OWNER TO postgres;

SET default_with_oids = true;

--
-- TOC entry 1441 (class 1259 OID 36284)
-- Dependencies: 1899 1900 1901 5
-- Name: employees_suppliers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employees_suppliers (
    employee_id bigint NOT NULL,
    employees_suplier_id bigint DEFAULT nextval(('employees_suppliers_EMPLOYEES_SUPLIER_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    supplier_id bigint NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.employees_suppliers OWNER TO postgres;

--
-- TOC entry 1440 (class 1259 OID 36282)
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
-- TOC entry 2302 (class 0 OID 0)
-- Dependencies: 1440
-- Name: employees_suppliers_employees_suplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employees_suppliers_employees_suplier_id_seq', 1, false);


--
-- TOC entry 1442 (class 1259 OID 36291)
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
-- TOC entry 2303 (class 0 OID 0)
-- Dependencies: 1442
-- Name: fieldlanguage_language_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fieldlanguage_language_id_seq', 1, false);


SET default_with_oids = false;

--
-- TOC entry 1443 (class 1259 OID 36293)
-- Dependencies: 1902 1903 1904 5
-- Name: fieldlanguage; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fieldlanguage (
    deleted boolean DEFAULT false,
    language_id integer DEFAULT nextval('fieldlanguage_language_id_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.fieldlanguage OWNER TO postgres;

--
-- TOC entry 1444 (class 1259 OID 36300)
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
-- TOC entry 2304 (class 0 OID 0)
-- Dependencies: 1444
-- Name: fieldlanguagesvalues_fieldlanguagesvalues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fieldlanguagesvalues_fieldlanguagesvalues_id_seq', 1, false);


--
-- TOC entry 1445 (class 1259 OID 36302)
-- Dependencies: 1905 1906 1907 5
-- Name: fieldlanguagesvalues; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fieldlanguagesvalues (
    deleted boolean DEFAULT false,
    fieldlanguagesvalues_id integer DEFAULT nextval('fieldlanguagesvalues_fieldlanguagesvalues_id_seq'::regclass) NOT NULL,
    fieldvalues_id integer NOT NULL,
    language_id integer NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    value character varying(255) NOT NULL
);


ALTER TABLE public.fieldlanguagesvalues OWNER TO postgres;

--
-- TOC entry 1446 (class 1259 OID 36309)
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
-- TOC entry 2305 (class 0 OID 0)
-- Dependencies: 1446
-- Name: fieldvalues_fieldvalues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fieldvalues_fieldvalues_id_seq', 1, false);


--
-- TOC entry 1447 (class 1259 OID 36311)
-- Dependencies: 1908 1909 1910 5
-- Name: fieldvalues; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fieldvalues (
    deleted boolean DEFAULT false,
    fieldvalues_id integer DEFAULT nextval('fieldvalues_fieldvalues_id_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone
);


ALTER TABLE public.fieldvalues OWNER TO postgres;

SET default_with_oids = true;

--
-- TOC entry 1449 (class 1259 OID 36320)
-- Dependencies: 1911 1912 1913 1914 5
-- Name: lieferarten; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lieferarten (
    beschreibung text NOT NULL,
    "comment" text NOT NULL,
    freigeschalten bigint DEFAULT 0 NOT NULL,
    liefer_id bigint DEFAULT nextval(('lieferarten_LIEFER_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.lieferarten OWNER TO postgres;

--
-- TOC entry 1448 (class 1259 OID 36318)
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
-- TOC entry 2306 (class 0 OID 0)
-- Dependencies: 1448
-- Name: lieferarten_liefer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lieferarten_liefer_id_seq', 1, false);


--
-- TOC entry 1451 (class 1259 OID 36333)
-- Dependencies: 1915 1916 1917 1918 1919 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.naviglobal OWNER TO postgres;

--
-- TOC entry 1450 (class 1259 OID 36331)
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
-- TOC entry 2307 (class 0 OID 0)
-- Dependencies: 1450
-- Name: naviglobal_global_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('naviglobal_global_id_seq', 1, false);


--
-- TOC entry 1453 (class 1259 OID 36347)
-- Dependencies: 1920 1921 1922 1923 1924 1925 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.navimain OWNER TO postgres;

--
-- TOC entry 1452 (class 1259 OID 36345)
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
-- TOC entry 2308 (class 0 OID 0)
-- Dependencies: 1452
-- Name: navimain_main_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('navimain_main_id_seq', 1, false);


--
-- TOC entry 1455 (class 1259 OID 36362)
-- Dependencies: 1926 1927 1928 1929 1930 1931 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.navisub OWNER TO postgres;

--
-- TOC entry 1454 (class 1259 OID 36360)
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
-- TOC entry 2309 (class 0 OID 0)
-- Dependencies: 1454
-- Name: navisub_sub_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('navisub_sub_id_seq', 1, false);


--
-- TOC entry 1456 (class 1259 OID 36375)
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
-- TOC entry 2310 (class 0 OID 0)
-- Dependencies: 1456
-- Name: organisation_organisation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organisation_organisation_id_seq', 1, false);


SET default_with_oids = false;

--
-- TOC entry 1457 (class 1259 OID 36377)
-- Dependencies: 1932 1933 5
-- Name: organisation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organisation (
    deleted boolean,
    insertedby bigint,
    name character varying(255) NOT NULL,
    organisation_id integer DEFAULT nextval('organisation_organisation_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatedby bigint,
    updatetime timestamp without time zone
);


ALTER TABLE public.organisation OWNER TO postgres;

--
-- TOC entry 1458 (class 1259 OID 36383)
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
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 1458
-- Name: organisation_users_organisation_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organisation_users_organisation_users_id_seq', 1, false);


--
-- TOC entry 1459 (class 1259 OID 36385)
-- Dependencies: 1934 1935 1936 5
-- Name: organisation_users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organisation_users (
    deleted boolean DEFAULT false,
    organisation_id integer NOT NULL,
    organisation_users_id integer DEFAULT nextval('organisation_users_organisation_users_id_seq'::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.organisation_users OWNER TO postgres;

SET default_with_oids = true;

--
-- TOC entry 1461 (class 1259 OID 36394)
-- Dependencies: 1937 1938 1939 5
-- Name: phones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE phones (
    "comment" text NOT NULL,
    phone_id bigint DEFAULT nextval(('phones_PHONE_ID_seq'::text)::regclass) NOT NULL,
    phonevalue character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.phones OWNER TO postgres;

--
-- TOC entry 1460 (class 1259 OID 36392)
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
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 1460
-- Name: phones_phone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('phones_phone_id_seq', 1, false);


--
-- TOC entry 1463 (class 1259 OID 36406)
-- Dependencies: 1940 1941 1942 1943 5
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
-- TOC entry 1462 (class 1259 OID 36404)
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
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 1462
-- Name: sessiondata_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sessiondata_id_seq', 1, false);


--
-- TOC entry 1465 (class 1259 OID 36416)
-- Dependencies: 1944 1945 1946 5
-- Name: states; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE states (
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    state_id bigint DEFAULT nextval(('states_STATE_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.states OWNER TO postgres;

--
-- TOC entry 1464 (class 1259 OID 36414)
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
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 1464
-- Name: states_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('states_state_id_seq', 1, false);


--
-- TOC entry 1467 (class 1259 OID 36425)
-- Dependencies: 1947 1948 1949 5
-- Name: suppliergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE suppliergroups (
    description text NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    suppliergroup_id bigint DEFAULT nextval(('suppliergroups_SUPPLIERGROUP_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.suppliergroups OWNER TO postgres;

--
-- TOC entry 1466 (class 1259 OID 36423)
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
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 1466
-- Name: suppliergroups_suppliergroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('suppliergroups_suppliergroup_id_seq', 1, false);


--
-- TOC entry 1469 (class 1259 OID 36437)
-- Dependencies: 1950 1951 1952 5
-- Name: suppliers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE suppliers (
    adresses_id integer NOT NULL,
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    supplier_id bigint DEFAULT nextval(('suppliers_SUPPLIER_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.suppliers OWNER TO postgres;

--
-- TOC entry 1468 (class 1259 OID 36435)
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
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 1468
-- Name: suppliers_supplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('suppliers_supplier_id_seq', 1, false);


--
-- TOC entry 1471 (class 1259 OID 36446)
-- Dependencies: 1953 1954 1955 5
-- Name: suppliers_suppliergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE suppliers_suppliergroups (
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    supplier_id bigint NOT NULL,
    supplier_suppliergroup_id bigint DEFAULT nextval(('suppliers_suppliergroups_SUPPLIER_SUPPLIERGROUP_ID_seq'::text)::regclass) NOT NULL,
    suppliergroup_id bigint NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.suppliers_suppliergroups OWNER TO postgres;

--
-- TOC entry 1470 (class 1259 OID 36444)
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
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 1470
-- Name: suppliers_suppliergroups_supplier_suppliergroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('suppliers_suppliergroups_supplier_suppliergroup_id_seq', 1, false);


--
-- TOC entry 1473 (class 1259 OID 36455)
-- Dependencies: 1956 1957 1958 1959 1960 1961 1962 1963 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.termine OWNER TO postgres;

--
-- TOC entry 1475 (class 1259 OID 36472)
-- Dependencies: 1964 1965 1966 5
-- Name: termine_participant; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE termine_participant (
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    termin_id bigint NOT NULL,
    termine_participant_id bigint DEFAULT nextval(('termine_participant_TERMINE_PARTICIPANT_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint NOT NULL,
    deleted boolean DEFAULT false
);


ALTER TABLE public.termine_participant OWNER TO postgres;

--
-- TOC entry 1474 (class 1259 OID 36470)
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
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 1474
-- Name: termine_participant_termine_participant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_participant_termine_participant_id_seq', 1, false);


--
-- TOC entry 1472 (class 1259 OID 36453)
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
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 1472
-- Name: termine_termin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_termin_id_seq', 1, false);


--
-- TOC entry 1477 (class 1259 OID 36481)
-- Dependencies: 1967 1968 1969 1970 1971 1972 1973 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.termine_todo_user OWNER TO postgres;

--
-- TOC entry 1476 (class 1259 OID 36479)
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
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 1476
-- Name: termine_todo_user_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_todo_user_uid_seq', 1, false);


--
-- TOC entry 1479 (class 1259 OID 36497)
-- Dependencies: 1974 1975 1976 1977 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.termine_todolist OWNER TO postgres;

--
-- TOC entry 1478 (class 1259 OID 36495)
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
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 1478
-- Name: termine_todolist_todo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_todolist_todo_id_seq', 1, false);


--
-- TOC entry 1481 (class 1259 OID 36510)
-- Dependencies: 1978 1979 1980 1981 1982 1983 1984 1985 1986 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.termine_user OWNER TO postgres;

--
-- TOC entry 1480 (class 1259 OID 36508)
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
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 1480
-- Name: termine_user_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('termine_user_uid_seq', 1, false);


--
-- TOC entry 1482 (class 1259 OID 36526)
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
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 1482
-- Name: terminegroups_terminegroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('terminegroups_terminegroup_id_seq', 1, false);


--
-- TOC entry 1483 (class 1259 OID 36528)
-- Dependencies: 1987 1988 1989 1990 1991 1992 1993 1994 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.terminegroups OWNER TO postgres;

--
-- TOC entry 1485 (class 1259 OID 36545)
-- Dependencies: 1995 1996 1997 1998 5
-- Name: terminestatus; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE terminestatus (
    "comment" text,
    description text,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    status_id bigint DEFAULT nextval(('terminestatus_STATUS_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    deleted boolean DEFAULT false
);


ALTER TABLE public.terminestatus OWNER TO postgres;

--
-- TOC entry 1484 (class 1259 OID 36543)
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
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 1484
-- Name: terminestatus_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('terminestatus_status_id_seq', 1, false);


--
-- TOC entry 1487 (class 1259 OID 36558)
-- Dependencies: 1999 2000 2001 5
-- Name: titles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE titles (
    name character varying(255) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    title_id bigint DEFAULT nextval(('titles_TITLE_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.titles OWNER TO postgres;

--
-- TOC entry 1486 (class 1259 OID 36556)
-- Dependencies: 5
-- Name: titles_title_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE titles_title_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.titles_title_id_seq OWNER TO postgres;

--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 1486
-- Name: titles_title_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('titles_title_id_seq', 1, false);


--
-- TOC entry 1489 (class 1259 OID 36567)
-- Dependencies: 2002 2003 2004 5
-- Name: transstatus; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE transstatus (
    "comment" text NOT NULL,
    description text NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    status_id bigint DEFAULT nextval(('transstatus_STATUS_ID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.transstatus OWNER TO postgres;

--
-- TOC entry 1488 (class 1259 OID 36565)
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
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 1488
-- Name: transstatus_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('transstatus_status_id_seq', 1, false);


--
-- TOC entry 1491 (class 1259 OID 36579)
-- Dependencies: 2005 2006 2007 2008 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.userdata OWNER TO postgres;

--
-- TOC entry 1490 (class 1259 OID 36577)
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
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 1490
-- Name: userdata_data_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userdata_data_id_seq', 1, false);


--
-- TOC entry 1493 (class 1259 OID 36592)
-- Dependencies: 2009 2010 2011 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.usergroups OWNER TO postgres;

--
-- TOC entry 1492 (class 1259 OID 36590)
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
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 1492
-- Name: usergroups_usergroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usergroups_usergroup_id_seq', 1, false);


--
-- TOC entry 1495 (class 1259 OID 36604)
-- Dependencies: 2012 2013 2014 2015 5
-- Name: userlevel; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userlevel (
    description text,
    level_id bigint DEFAULT nextval(('userlevel_LEVEL_ID_seq'::text)::regclass) NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    statuscode integer DEFAULT 0 NOT NULL,
    updatetime timestamp without time zone,
    deleted boolean DEFAULT false
);


ALTER TABLE public.userlevel OWNER TO postgres;

--
-- TOC entry 1494 (class 1259 OID 36602)
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
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 1494
-- Name: userlevel_level_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userlevel_level_id_seq', 1, false);


--
-- TOC entry 1497 (class 1259 OID 36617)
-- Dependencies: 2016 2017 2018 2019 2020 2021 2022 2023 5
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    adresses_id bigint,
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
    language_id integer NOT NULL,
    pictureuri character varying(255),
    deleted boolean DEFAULT false
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 1496 (class 1259 OID 36615)
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
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 1496
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_user_id_seq', 1, false);


--
-- TOC entry 1499 (class 1259 OID 36634)
-- Dependencies: 2024 2025 2026 2027 2028 5
-- Name: users_usergroups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users_usergroups (
    "comment" text NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    users_usergroup_id bigint DEFAULT nextval(('users_usergroups_UID_seq'::text)::regclass) NOT NULL,
    updatetime timestamp without time zone,
    user_id bigint DEFAULT 0 NOT NULL,
    usergroup_id bigint DEFAULT 0 NOT NULL,
    deleted boolean DEFAULT false
);


ALTER TABLE public.users_usergroups OWNER TO postgres;

--
-- TOC entry 1498 (class 1259 OID 36632)
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
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 1498
-- Name: users_usergroups_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_usergroups_uid_seq', 1, false);


--
-- TOC entry 1501 (class 1259 OID 36648)
-- Dependencies: 2029 2030 2031 2032 2033 2034 2035 2036 2037 5
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
    deleted boolean DEFAULT false
);


ALTER TABLE public.userwaren OWNER TO postgres;

--
-- TOC entry 1500 (class 1259 OID 36646)
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
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 1500
-- Name: userwaren_userwaren_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userwaren_userwaren_id_seq', 1, false);


--
-- TOC entry 1503 (class 1259 OID 36666)
-- Dependencies: 2038 2039 2040 2041 5
-- Name: zahlungsarten; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE zahlungsarten (
    beschreibung text NOT NULL,
    "comment" text NOT NULL,
    freigeschalten integer DEFAULT 0 NOT NULL,
    starttime timestamp without time zone DEFAULT now() NOT NULL,
    updatetime timestamp without time zone,
    zahlungs_id bigint DEFAULT nextval(('zahlungsarten_ZAHLUNGS_ID_seq'::text)::regclass) NOT NULL,
    deleted boolean DEFAULT false
);


ALTER TABLE public.zahlungsarten OWNER TO postgres;

--
-- TOC entry 1502 (class 1259 OID 36664)
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
-- TOC entry 2333 (class 0 OID 0)
-- Dependencies: 1502
-- Name: zahlungsarten_zahlungs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('zahlungsarten_zahlungs_id_seq', 1, false);


--
-- TOC entry 2226 (class 0 OID 36064)
-- Dependencies: 1400
-- Data for Name: adresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) VALUES ('additionalname', 1, 'comment', 'fax', '2007-03-24 13:05:15.945', 1, 'street', 'town', NULL, 'zip', NULL);
INSERT INTO adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) VALUES ('92', 2, '', 'fax number', '2007-03-27 16:43:30.356', 1, 'Bleichstraße', 'Pforzheim', NULL, '75173', NULL);
INSERT INTO adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) VALUES ('92', 3, '', 'fax number', '2007-03-27 16:48:31.309', 1, 'Bleichstraße', 'Pforzheim', NULL, '75173', NULL);
INSERT INTO adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) VALUES ('92', 4, '', 'fax number', '2007-03-27 16:59:45.594', 1, 'Bleichstraße', 'Pforzheim', NULL, '75173', NULL);
INSERT INTO adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) VALUES ('No 14', 5, '', 'faxno', '2007-03-27 18:09:45.157', 1, 'street', 'town', NULL, 'zip', NULL);
INSERT INTO adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) VALUES ('No 14', 6, '', 'faxno', '2007-03-27 18:10:17.78', 1, 'street', 'town', NULL, 'zip', NULL);
INSERT INTO adresses (additionalname, adresses_id, "comment", fax, starttime, state_id, street, town, updatetime, zip, deleted) VALUES ('No 14', 7, '', 'faxno', '2007-03-27 18:11:53.732', 1, 'street', 'town', NULL, 'zip', NULL);


--
-- TOC entry 2227 (class 0 OID 36076)
-- Dependencies: 1402
-- Data for Name: adresses_emails; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) VALUES (1, 1, 1, '2007-03-27 15:32:34.339', NULL, NULL);
INSERT INTO adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) VALUES (2, 2, 2, '2007-03-27 16:43:31.418', NULL, NULL);
INSERT INTO adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) VALUES (3, 3, 3, '2007-03-27 16:48:31.541', NULL, NULL);
INSERT INTO adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) VALUES (4, 4, 4, '2007-03-27 16:59:45.723', NULL, NULL);
INSERT INTO adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) VALUES (5, 5, 5, '2007-03-27 18:09:45.222', NULL, NULL);
INSERT INTO adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) VALUES (6, 6, 6, '2007-03-27 18:10:17.839', NULL, NULL);
INSERT INTO adresses_emails (adresses_emails_id, adresses_id, mail_id, starttime, updatetime, deleted) VALUES (7, 7, 7, '2007-03-27 18:11:53.801', NULL, NULL);


--
-- TOC entry 2228 (class 0 OID 36085)
-- Dependencies: 1404
-- Data for Name: adresses_phones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2229 (class 0 OID 36094)
-- Dependencies: 1406
-- Data for Name: articlegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2230 (class 0 OID 36104)
-- Dependencies: 1408
-- Data for Name: articles; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2231 (class 0 OID 36117)
-- Dependencies: 1410
-- Data for Name: articles_articlegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2232 (class 0 OID 36127)
-- Dependencies: 1412
-- Data for Name: articles_lieferarten; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2233 (class 0 OID 36136)
-- Dependencies: 1414
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO configuration ("comment", conf_key, conf_value, configuration_id, starttime, updatetime, user_id, deleted) VALUES ('', 'allow_frontend_register', '1', 1, '2007-03-19 11:38:13.851', '2007-03-19 11:38:13.851', 1, NULL);
INSERT INTO configuration ("comment", conf_key, conf_value, configuration_id, starttime, updatetime, user_id, deleted) VALUES ('', 'default_group_id', '1', 2, '2007-03-19 11:38:13.861', '2007-03-19 11:38:13.862', 1, NULL);


--
-- TOC entry 2234 (class 0 OID 36150)
-- Dependencies: 1416
-- Data for Name: contactfreigabe; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2235 (class 0 OID 36160)
-- Dependencies: 1417
-- Data for Name: contactgroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2236 (class 0 OID 36172)
-- Dependencies: 1419
-- Data for Name: contactgroups_contacts; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2237 (class 0 OID 36181)
-- Dependencies: 1421
-- Data for Name: contactgroups_conuser; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2238 (class 0 OID 36190)
-- Dependencies: 1423
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2239 (class 0 OID 36204)
-- Dependencies: 1425
-- Data for Name: conuser; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2240 (class 0 OID 36213)
-- Dependencies: 1427
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO emails ("comment", email, mail_id, startdate, updatedate, deleted) VALUES ('', 'email', 6, '2007-03-27 18:10:17.835', NULL, false);
INSERT INTO emails ("comment", email, mail_id, startdate, updatedate, deleted) VALUES ('', 'email', 7, '2007-03-27 18:11:53.796', NULL, false);
INSERT INTO emails ("comment", email, mail_id, startdate, updatedate, deleted) VALUES ('', 'email', 5, '2007-03-27 18:09:45.218', NULL, false);
INSERT INTO emails ("comment", email, mail_id, startdate, updatedate, deleted) VALUES ('', 'seba.wagner@gmail.com', 4, '2007-03-27 16:59:45.718', NULL, false);
INSERT INTO emails ("comment", email, mail_id, startdate, updatedate, deleted) VALUES ('', 'seba.wagner@gmail.com', 3, '2007-03-27 16:48:31.535', NULL, false);
INSERT INTO emails ("comment", email, mail_id, startdate, updatedate, deleted) VALUES ('', 'seba.wagner@gmail.com', 2, '2007-03-27 16:43:31.303', NULL, false);
INSERT INTO emails ("comment", email, mail_id, startdate, updatedate, deleted) VALUES ('', 'seba.wagner@gmail.com', 1, '2007-03-27 15:32:30.723', NULL, false);


--
-- TOC entry 2241 (class 0 OID 36225)
-- Dependencies: 1429
-- Data for Name: employeegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2242 (class 0 OID 36237)
-- Dependencies: 1431
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2243 (class 0 OID 36248)
-- Dependencies: 1433
-- Data for Name: employees_articlegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2244 (class 0 OID 36257)
-- Dependencies: 1435
-- Data for Name: employees_articles; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2245 (class 0 OID 36266)
-- Dependencies: 1437
-- Data for Name: employees_employeegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2246 (class 0 OID 36275)
-- Dependencies: 1439
-- Data for Name: employees_suppliergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2247 (class 0 OID 36284)
-- Dependencies: 1441
-- Data for Name: employees_suppliers; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2248 (class 0 OID 36293)
-- Dependencies: 1443
-- Data for Name: fieldlanguage; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fieldlanguage (deleted, language_id, name, starttime, updatetime) VALUES (NULL, 1, 'deutsch', '2007-03-19 11:38:13.461', NULL);
INSERT INTO fieldlanguage (deleted, language_id, name, starttime, updatetime) VALUES (NULL, 2, 'english', '2007-03-19 11:38:13.533', NULL);
INSERT INTO fieldlanguage (deleted, language_id, name, starttime, updatetime) VALUES (NULL, 3, 'french', '2007-03-19 11:38:13.538', NULL);
INSERT INTO fieldlanguage (deleted, language_id, name, starttime, updatetime) VALUES (NULL, 4, 'spanish', '2007-03-19 11:38:13.54', NULL);


--
-- TOC entry 2249 (class 0 OID 36302)
-- Dependencies: 1445
-- Data for Name: fieldlanguagesvalues; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 1, 1, 1, '2007-03-19 11:38:13.555', NULL, 'Konferenz');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 2, 1, 2, '2007-03-19 11:38:13.563', NULL, 'Confernce');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 3, 1, 3, '2007-03-19 11:38:13.576', NULL, 'Conférence');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 4, 1, 4, '2007-03-19 11:38:13.582', NULL, 'Conferencia');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 5, 2, 1, '2007-03-19 11:38:13.587', NULL, 'Meeting');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 6, 2, 2, '2007-03-19 11:38:13.589', NULL, 'Meeting');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 7, 2, 3, '2007-03-19 11:38:13.594', NULL, 'Meeting');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 8, 2, 4, '2007-03-19 11:38:13.597', NULL, 'Encuentro');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 9, 3, 1, '2007-03-19 11:38:13.608', NULL, 'Auditorium');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 10, 3, 2, '2007-03-19 11:38:13.611', NULL, 'Auditorium');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 11, 3, 3, '2007-03-19 11:38:13.615', NULL, 'Auditorium');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 12, 3, 4, '2007-03-19 11:38:13.618', NULL, 'Auditorio');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 13, 4, 1, '2007-03-19 11:38:13.632', NULL, 'Einstellungen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 14, 4, 2, '2007-03-19 11:38:13.635', NULL, 'Settings');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 15, 4, 3, '2007-03-19 11:38:13.639', NULL, 'Paramètres');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 16, 4, 4, '2007-03-19 11:38:13.645', NULL, 'Calibración');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 17, 5, 1, '2007-03-19 11:38:13.653', NULL, 'Benutzer');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 18, 5, 2, '2007-03-19 11:38:13.656', NULL, 'User');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 19, 5, 3, '2007-03-19 11:38:13.658', NULL, 'Client');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 20, 5, 4, '2007-03-19 11:38:13.662', NULL, 'Usuario');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 21, 6, 1, '2007-03-19 11:38:13.666', NULL, 'Benutzer');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 22, 6, 2, '2007-03-19 11:38:13.668', NULL, 'User');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 23, 6, 3, '2007-03-19 11:38:13.671', NULL, 'Client');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 24, 6, 4, '2007-03-19 11:38:13.673', NULL, 'Usuario');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 25, 7, 1, '2007-03-19 11:38:26.894', NULL, 'Stop');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 26, 7, 2, '2007-03-19 11:38:26.901', NULL, 'Stop');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 27, 7, 3, '2007-03-19 11:38:26.904', NULL, 'Stop');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 28, 7, 4, '2007-03-19 11:38:26.907', NULL, 'Stop');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 29, 8, 1, '2007-03-19 11:38:26.919', NULL, 'Record');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 30, 8, 2, '2007-03-19 11:38:26.927', NULL, 'Record');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 31, 8, 3, '2007-03-19 11:38:26.932', NULL, 'Record');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 32, 8, 4, '2007-03-19 11:38:26.934', NULL, 'Record');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 33, 9, 1, '2007-03-19 11:38:26.945', NULL, 'Keine Datei vorhanden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 34, 9, 2, '2007-03-19 11:38:26.948', NULL, 'Keine Datei vorhanden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 35, 9, 3, '2007-03-19 11:38:26.955', NULL, 'Keine Datei vorhanden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 36, 9, 4, '2007-03-19 11:38:26.958', NULL, 'Keine Datei vorhanden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 37, 10, 1, '2007-03-19 11:38:26.963', NULL, 'Aufnahme nur für Lehrer verfügbar');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 38, 10, 2, '2007-03-19 11:38:26.965', NULL, 'Aufnahme nur für Lehrer verfügbar');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 39, 10, 3, '2007-03-19 11:38:26.969', NULL, 'Aufnahme nur für Lehrer verfügbar');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 40, 10, 4, '2007-03-19 11:38:26.971', NULL, 'Aufnahme nur für Lehrer verfügbar');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 41, 11, 1, '2007-03-19 11:38:26.977', NULL, 'verbunden Benutzer:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 42, 11, 2, '2007-03-19 11:38:26.979', NULL, 'verbunden Benutzer:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 43, 11, 3, '2007-03-19 11:38:26.983', NULL, 'verbunden Benutzer:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 44, 11, 4, '2007-03-19 11:38:26.987', NULL, 'verbunden Benutzer:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 45, 12, 1, '2007-03-19 11:38:26.997', NULL, 'Konferenz starten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 46, 12, 2, '2007-03-19 11:38:27', NULL, 'Konferenz starten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 47, 12, 3, '2007-03-19 11:38:27.003', NULL, 'Konferenz starten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 48, 12, 4, '2007-03-19 11:38:27.005', NULL, 'Konferenz starten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 49, 13, 1, '2007-03-19 11:38:27.01', NULL, 'Mein Name');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 50, 13, 2, '2007-03-19 11:38:27.013', NULL, 'Mein Name');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 51, 13, 3, '2007-03-19 11:38:27.015', NULL, 'Mein Name');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 52, 13, 4, '2007-03-19 11:38:27.018', NULL, 'Mein Name');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 53, 14, 1, '2007-03-19 11:38:27.024', NULL, 'VideoConference');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 54, 14, 2, '2007-03-19 11:38:27.028', NULL, 'VideoConference');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 55, 14, 3, '2007-03-19 11:38:27.031', NULL, 'VideoConference');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 56, 14, 4, '2007-03-19 11:38:27.033', NULL, 'VideoConference');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 57, 15, 1, '2007-03-19 11:38:27.037', NULL, 'Präsentation importieren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 58, 15, 2, '2007-03-19 11:38:27.039', NULL, 'Präsentation importieren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 59, 15, 3, '2007-03-19 11:38:27.042', NULL, 'Präsentation importieren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 60, 15, 4, '2007-03-19 11:38:27.044', NULL, 'Präsentation importieren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 61, 16, 1, '2007-03-19 11:38:27.048', NULL, 'Liste neu laden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 62, 16, 2, '2007-03-19 11:38:27.05', NULL, 'Liste neu laden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 63, 16, 3, '2007-03-19 11:38:27.052', NULL, 'Liste neu laden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 64, 16, 4, '2007-03-19 11:38:27.055', NULL, 'Liste neu laden');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 65, 17, 1, '2007-03-19 11:38:27.061', NULL, 'Zum Hauptverzeichnis');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 66, 17, 2, '2007-03-19 11:38:27.063', NULL, 'Zum Hauptverzeichnis');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 67, 17, 3, '2007-03-19 11:38:27.066', NULL, 'Zum Hauptverzeichnis');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 68, 17, 4, '2007-03-19 11:38:27.069', NULL, 'Zum Hauptverzeichnis');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 69, 18, 1, '2007-03-19 11:38:27.075', NULL, 'neue Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 70, 18, 2, '2007-03-19 11:38:27.079', NULL, 'neue Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 71, 18, 3, '2007-03-19 11:38:27.081', NULL, 'neue Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 72, 18, 4, '2007-03-19 11:38:27.084', NULL, 'neue Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 73, 19, 1, '2007-03-19 11:38:27.094', NULL, 'Eine neue Umfrage für die Konferenz.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 74, 19, 2, '2007-03-19 11:38:27.097', NULL, 'Eine neue Umfrage für die Konferenz.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 75, 19, 3, '2007-03-19 11:38:27.099', NULL, 'Eine neue Umfrage für die Konferenz.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 76, 19, 4, '2007-03-19 11:38:27.101', NULL, 'Eine neue Umfrage für die Konferenz.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 77, 20, 1, '2007-03-19 11:38:27.11', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 78, 20, 2, '2007-03-19 11:38:27.113', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 79, 20, 3, '2007-03-19 11:38:27.116', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 80, 20, 4, '2007-03-19 11:38:27.12', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 81, 21, 1, '2007-03-19 11:38:27.127', NULL, 'Umfragenart:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 82, 21, 2, '2007-03-19 11:38:27.13', NULL, 'Umfragenart:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 83, 21, 3, '2007-03-19 11:38:27.133', NULL, 'Umfragenart:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 84, 21, 4, '2007-03-19 11:38:27.136', NULL, 'Umfragenart:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 85, 22, 1, '2007-03-19 11:38:27.142', NULL, 'anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 86, 22, 2, '2007-03-19 11:38:27.145', NULL, 'anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 87, 22, 3, '2007-03-19 11:38:27.147', NULL, 'anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 88, 22, 4, '2007-03-19 11:38:27.15', NULL, 'anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 89, 23, 1, '2007-03-19 11:38:27.155', NULL, 'Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 90, 23, 2, '2007-03-19 11:38:27.157', NULL, 'Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 91, 23, 3, '2007-03-19 11:38:27.16', NULL, 'Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 92, 23, 4, '2007-03-19 11:38:27.162', NULL, 'Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 93, 24, 1, '2007-03-19 11:38:27.181', NULL, 'Umfrage anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 94, 24, 2, '2007-03-19 11:38:27.183', NULL, 'Umfrage anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 95, 24, 3, '2007-03-19 11:38:27.185', NULL, 'Umfrage anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 96, 24, 4, '2007-03-19 11:38:27.19', NULL, 'Umfrage anlegen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 97, 25, 1, '2007-03-19 11:38:27.198', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 98, 25, 2, '2007-03-19 11:38:27.20', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 99, 25, 3, '2007-03-19 11:38:27.202', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 100, 25, 4, '2007-03-19 11:38:27.205', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 101, 26, 1, '2007-03-19 11:38:27.21', NULL, 'Ja/Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 102, 26, 2, '2007-03-19 11:38:27.212', NULL, 'Ja/Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 103, 26, 3, '2007-03-19 11:38:27.214', NULL, 'Ja/Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 104, 26, 4, '2007-03-19 11:38:27.217', NULL, 'Ja/Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 105, 27, 1, '2007-03-19 11:38:27.222', NULL, 'Numerisch 1-10');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 106, 27, 2, '2007-03-19 11:38:27.225', NULL, 'Numerisch 1-10');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 107, 27, 3, '2007-03-19 11:38:27.228', NULL, 'Numerisch 1-10');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 108, 27, 4, '2007-03-19 11:38:27.232', NULL, 'Numerisch 1-10');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 109, 28, 1, '2007-03-19 11:38:27.238', NULL, 'Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 110, 28, 2, '2007-03-19 11:38:27.241', NULL, 'Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 111, 28, 3, '2007-03-19 11:38:27.243', NULL, 'Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 112, 28, 4, '2007-03-19 11:38:27.246', NULL, 'Umfrage');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 116, 29, 4, '2007-03-19 11:38:27.262', NULL, 'Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 117, 30, 1, '2007-03-19 11:38:27.267', NULL, 'Ihr Stimme wurde abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 118, 30, 2, '2007-03-19 11:38:27.271', NULL, 'Ihr Stimme wurde abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 119, 30, 3, '2007-03-19 11:38:27.273', NULL, 'Ihr Stimme wurde abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 120, 30, 4, '2007-03-19 11:38:27.275', NULL, 'Ihr Stimme wurde abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 128, 32, 4, '2007-03-19 11:38:27.296', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 130, 33, 2, '2007-03-19 11:38:27.306', NULL, 'Ihre Antwort:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 132, 33, 4, '2007-03-19 11:38:27.313', NULL, 'Ihre Antwort:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 133, 34, 1, '2007-03-19 11:38:27.318', NULL, 'Ja');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 135, 34, 3, '2007-03-19 11:38:27.324', NULL, 'Ja');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 138, 35, 2, '2007-03-19 11:38:27.334', NULL, 'Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 145, 37, 1, '2007-03-19 11:38:27.354', NULL, 'Umfrageergebnisse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 146, 37, 2, '2007-03-19 11:38:27.357', NULL, 'Umfrageergebnisse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 147, 37, 3, '2007-03-19 11:38:27.359', NULL, 'Umfrageergebnisse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 150, 38, 2, '2007-03-19 11:38:27.371', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 152, 38, 4, '2007-03-19 11:38:27.377', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 153, 39, 1, '2007-03-19 11:38:27.382', NULL, 'Antworten:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 155, 39, 3, '2007-03-19 11:38:27.392', NULL, 'Antworten:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 157, 40, 1, '2007-03-19 11:38:27.404', NULL, 'Ergebnis:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 158, 40, 2, '2007-03-19 11:38:27.407', NULL, 'Ergebnis:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 159, 40, 3, '2007-03-19 11:38:27.409', NULL, 'Ergebnis:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 160, 40, 4, '2007-03-19 11:38:27.411', NULL, 'Ergebnis:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 162, 41, 2, '2007-03-19 11:38:27.418', NULL, 'Es gibt zur Zeit keine Umfage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 173, 44, 1, '2007-03-19 11:38:27.451', NULL, 'Conference (max 50 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 174, 44, 2, '2007-03-19 11:38:27.454', NULL, 'Conference (max 50 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 175, 44, 3, '2007-03-19 11:38:27.456', NULL, 'Conference (max 50 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 176, 44, 4, '2007-03-19 11:38:27.458', NULL, 'Conference (max 50 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 177, 45, 1, '2007-03-19 11:38:27.462', NULL, 'Modus');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 178, 45, 2, '2007-03-19 11:38:27.463', NULL, 'Modus');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 182, 46, 2, '2007-03-19 11:38:27.479', NULL, 'verbleibende Plätze');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 184, 46, 4, '2007-03-19 11:38:27.484', NULL, 'verbleibende Plätze');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 185, 47, 1, '2007-03-19 11:38:27.489', NULL, 'Bereits vergeben');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 187, 47, 3, '2007-03-19 11:38:27.494', NULL, 'Bereits vergeben');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 190, 48, 2, '2007-03-19 11:38:27.505', NULL, 'Eintreten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 193, 49, 1, '2007-03-19 11:38:27.516', NULL, 'Der Moderator/Lehrer dieses Raums hat den Raum verlassen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 194, 49, 2, '2007-03-19 11:38:27.518', NULL, 'Der Moderator/Lehrer dieses Raums hat den Raum verlassen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 195, 49, 3, '2007-03-19 11:38:27.52', NULL, 'Der Moderator/Lehrer dieses Raums hat den Raum verlassen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 196, 49, 4, '2007-03-19 11:38:27.524', NULL, 'Der Moderator/Lehrer dieses Raums hat den Raum verlassen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 204, 51, 4, '2007-03-19 11:38:27.556', NULL, 'Geräteauswahl');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 205, 52, 1, '2007-03-19 11:38:27.561', NULL, 'Kamera wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 207, 52, 3, '2007-03-19 11:38:27.571', NULL, 'Kamera wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 210, 53, 2, '2007-03-19 11:38:27.58', NULL, 'Mikrophon wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 211, 53, 3, '2007-03-19 11:38:27.583', NULL, 'Mikrophon wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 212, 53, 4, '2007-03-19 11:38:27.584', NULL, 'Mikrophon wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 214, 54, 2, '2007-03-19 11:38:27.59', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 215, 54, 3, '2007-03-19 11:38:27.592', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 216, 54, 4, '2007-03-19 11:38:27.594', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 217, 55, 1, '2007-03-19 11:38:27.598', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 218, 55, 2, '2007-03-19 11:38:27.601', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 219, 55, 3, '2007-03-19 11:38:27.603', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 220, 55, 4, '2007-03-19 11:38:27.605', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 221, 56, 1, '2007-03-19 11:38:27.611', NULL, 'Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 223, 56, 3, '2007-03-19 11:38:27.618', NULL, 'Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 226, 57, 2, '2007-03-19 11:38:27.63', NULL, 'Einstellungen ändern.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 228, 57, 4, '2007-03-19 11:38:27.634', NULL, 'Einstellungen ändern.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 229, 58, 1, '2007-03-19 11:38:27.639', NULL, 'Kurs:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 238, 60, 2, '2007-03-19 11:38:27.67', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 239, 60, 3, '2007-03-19 11:38:27.673', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 240, 60, 4, '2007-03-19 11:38:27.675', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 241, 61, 1, '2007-03-19 11:38:27.68', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 242, 61, 2, '2007-03-19 11:38:27.682', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 243, 61, 3, '2007-03-19 11:38:27.684', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 245, 62, 1, '2007-03-19 11:38:27.69', NULL, 'Zeichenbrett leeren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 246, 62, 2, '2007-03-19 11:38:27.692', NULL, 'Zeichenbrett leeren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 247, 62, 3, '2007-03-19 11:38:27.694', NULL, 'Zeichenbrett leeren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 248, 62, 4, '2007-03-19 11:38:27.696', NULL, 'Zeichenbrett leeren');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 249, 63, 1, '2007-03-19 11:38:27.699', NULL, 'Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 250, 63, 2, '2007-03-19 11:38:27.701', NULL, 'Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 251, 63, 3, '2007-03-19 11:38:27.703', NULL, 'Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 252, 63, 4, '2007-03-19 11:38:27.705', NULL, 'Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 253, 64, 1, '2007-03-19 11:38:27.709', NULL, 'Nicht nochmal fragen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 254, 64, 2, '2007-03-19 11:38:27.711', NULL, 'Nicht nochmal fragen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 255, 64, 3, '2007-03-19 11:38:27.713', NULL, 'Nicht nochmal fragen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 256, 64, 4, '2007-03-19 11:38:27.714', NULL, 'Nicht nochmal fragen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 257, 65, 1, '2007-03-19 11:38:27.72', NULL, 'nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 259, 65, 3, '2007-03-19 11:38:27.727', NULL, 'nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 262, 66, 2, '2007-03-19 11:38:27.738', NULL, 'Einstellungen bearbeiten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 113, 29, 1, '2007-03-19 11:38:27.253', NULL, 'Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 114, 29, 2, '2007-03-19 11:38:27.258', NULL, 'Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 115, 29, 3, '2007-03-19 11:38:27.26', NULL, 'Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 121, 31, 1, '2007-03-19 11:38:27.279', NULL, 'Sie haben für diese Umfrage bereits ihr Votum abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 122, 31, 2, '2007-03-19 11:38:27.282', NULL, 'Sie haben für diese Umfrage bereits ihr Votum abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 123, 31, 3, '2007-03-19 11:38:27.284', NULL, 'Sie haben für diese Umfrage bereits ihr Votum abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 124, 31, 4, '2007-03-19 11:38:27.286', NULL, 'Sie haben für diese Umfrage bereits ihr Votum abgegeben.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 125, 32, 1, '2007-03-19 11:38:27.29', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 126, 32, 2, '2007-03-19 11:38:27.292', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 127, 32, 3, '2007-03-19 11:38:27.294', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 129, 33, 1, '2007-03-19 11:38:27.302', NULL, 'Ihre Antwort:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 131, 33, 3, '2007-03-19 11:38:27.309', NULL, 'Ihre Antwort:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 134, 34, 2, '2007-03-19 11:38:27.321', NULL, 'Ja');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 136, 34, 4, '2007-03-19 11:38:27.328', NULL, 'Ja');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 137, 35, 1, '2007-03-19 11:38:27.332', NULL, 'Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 139, 35, 3, '2007-03-19 11:38:27.337', NULL, 'Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 140, 35, 4, '2007-03-19 11:38:27.34', NULL, 'Nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 141, 36, 1, '2007-03-19 11:38:27.344', NULL, 'will wissen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 142, 36, 2, '2007-03-19 11:38:27.346', NULL, 'will wissen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 143, 36, 3, '2007-03-19 11:38:27.348', NULL, 'will wissen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 144, 36, 4, '2007-03-19 11:38:27.35', NULL, 'will wissen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 148, 37, 4, '2007-03-19 11:38:27.361', NULL, 'Umfrageergebnisse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 149, 38, 1, '2007-03-19 11:38:27.367', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 151, 38, 3, '2007-03-19 11:38:27.375', NULL, 'Frage:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 154, 39, 2, '2007-03-19 11:38:27.387', NULL, 'Antworten:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 156, 39, 4, '2007-03-19 11:38:27.398', NULL, 'Antworten:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 161, 41, 1, '2007-03-19 11:38:27.415', NULL, 'Es gibt zur Zeit keine Umfage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 163, 41, 3, '2007-03-19 11:38:27.423', NULL, 'Es gibt zur Zeit keine Umfage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 164, 41, 4, '2007-03-19 11:38:27.426', NULL, 'Es gibt zur Zeit keine Umfage.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 165, 42, 1, '2007-03-19 11:38:27.431', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 166, 42, 2, '2007-03-19 11:38:27.433', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 167, 42, 3, '2007-03-19 11:38:27.435', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 168, 42, 4, '2007-03-19 11:38:27.437', NULL, 'abstimmen!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 169, 43, 1, '2007-03-19 11:38:27.44', NULL, 'Meeting (max 4 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 170, 43, 2, '2007-03-19 11:38:27.442', NULL, 'Meeting (max 4 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 171, 43, 3, '2007-03-19 11:38:27.444', NULL, 'Meeting (max 4 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 172, 43, 4, '2007-03-19 11:38:27.446', NULL, 'Meeting (max 4 Plätze)');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 179, 45, 3, '2007-03-19 11:38:27.465', NULL, 'Modus');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 180, 45, 4, '2007-03-19 11:38:27.469', NULL, 'Modus');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 181, 46, 1, '2007-03-19 11:38:27.476', NULL, 'verbleibende Plätze');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 183, 46, 3, '2007-03-19 11:38:27.481', NULL, 'verbleibende Plätze');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 186, 47, 2, '2007-03-19 11:38:27.492', NULL, 'Bereits vergeben');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 188, 47, 4, '2007-03-19 11:38:27.496', NULL, 'Bereits vergeben');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 189, 48, 1, '2007-03-19 11:38:27.501', NULL, 'Eintreten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 191, 48, 3, '2007-03-19 11:38:27.507', NULL, 'Eintreten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 192, 48, 4, '2007-03-19 11:38:27.51', NULL, 'Eintreten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 197, 50, 1, '2007-03-19 11:38:27.53', NULL, 'Systemnachricht');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 198, 50, 2, '2007-03-19 11:38:27.534', NULL, 'Systemnachricht');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 199, 50, 3, '2007-03-19 11:38:27.536', NULL, 'Systemnachricht');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 200, 50, 4, '2007-03-19 11:38:27.538', NULL, 'Systemnachricht');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 201, 51, 1, '2007-03-19 11:38:27.541', NULL, 'Geräteauswahl');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 202, 51, 2, '2007-03-19 11:38:27.551', NULL, 'Geräteauswahl');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 203, 51, 3, '2007-03-19 11:38:27.553', NULL, 'Geräteauswahl');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 206, 52, 2, '2007-03-19 11:38:27.563', NULL, 'Kamera wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 208, 52, 4, '2007-03-19 11:38:27.573', NULL, 'Kamera wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 209, 53, 1, '2007-03-19 11:38:27.578', NULL, 'Mikrophon wählen:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 213, 54, 1, '2007-03-19 11:38:27.588', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 222, 56, 2, '2007-03-19 11:38:27.613', NULL, 'Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 224, 56, 4, '2007-03-19 11:38:27.62', NULL, 'Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 225, 57, 1, '2007-03-19 11:38:27.625', NULL, 'Einstellungen ändern.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 227, 57, 3, '2007-03-19 11:38:27.632', NULL, 'Einstellungen ändern.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 230, 58, 2, '2007-03-19 11:38:27.641', NULL, 'Kurs:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 231, 58, 3, '2007-03-19 11:38:27.644', NULL, 'Kurs:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 232, 58, 4, '2007-03-19 11:38:27.648', NULL, 'Kurs:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 233, 59, 1, '2007-03-19 11:38:27.654', NULL, 'Kurssprache:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 234, 59, 2, '2007-03-19 11:38:27.656', NULL, 'Kurssprache:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 235, 59, 3, '2007-03-19 11:38:27.658', NULL, 'Kurssprache:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 236, 59, 4, '2007-03-19 11:38:27.66', NULL, 'Kurssprache:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 237, 60, 1, '2007-03-19 11:38:27.666', NULL, 'ok');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 244, 61, 4, '2007-03-19 11:38:27.686', NULL, 'abbrechen');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 258, 65, 2, '2007-03-19 11:38:27.724', NULL, 'nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 260, 65, 4, '2007-03-19 11:38:27.729', NULL, 'nein');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 261, 66, 1, '2007-03-19 11:38:27.734', NULL, 'Einstellungen bearbeiten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 263, 66, 3, '2007-03-19 11:38:27.74', NULL, 'Einstellungen bearbeiten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 264, 66, 4, '2007-03-19 11:38:27.745', NULL, 'Einstellungen bearbeiten');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 265, 67, 1, '2007-03-19 11:38:27.751', NULL, 'Bestätigung anfordern bevor das Zeichenbrett geleert wird.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 266, 67, 2, '2007-03-19 11:38:27.753', NULL, 'Bestätigung anfordern bevor das Zeichenbrett geleert wird.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 267, 67, 3, '2007-03-19 11:38:27.756', NULL, 'Bestätigung anfordern bevor das Zeichenbrett geleert wird.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 268, 67, 4, '2007-03-19 11:38:27.758', NULL, 'Bestätigung anfordern bevor das Zeichenbrett geleert wird.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 269, 68, 1, '2007-03-21 06:42:05.528', NULL, 'User Info');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 270, 68, 2, '2007-03-21 06:42:05.586', NULL, 'User Info');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 271, 68, 3, '2007-03-21 06:42:05.596', NULL, 'User Info');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 272, 68, 4, '2007-03-21 06:42:05.599', NULL, 'User Info');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 273, 69, 1, '2007-03-21 06:42:05.614', NULL, 'Clear DrawArea');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 274, 69, 2, '2007-03-21 06:42:05.617', NULL, 'Clear DrawArea');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 275, 69, 3, '2007-03-21 06:42:05.622', NULL, 'Clear DrawArea');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 276, 69, 4, '2007-03-21 06:42:05.624', NULL, 'Clear DrawArea');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 277, 70, 1, '2007-03-21 06:42:05.637', NULL, 'Undo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 278, 70, 2, '2007-03-21 06:42:05.64', NULL, 'Undo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 279, 70, 3, '2007-03-21 06:42:05.643', NULL, 'Undo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 280, 70, 4, '2007-03-21 06:42:05.646', NULL, 'Undo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 281, 71, 1, '2007-03-21 06:42:05.651', NULL, 'Redo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 282, 71, 2, '2007-03-21 06:42:05.653', NULL, 'Redo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 283, 71, 3, '2007-03-21 06:42:05.656', NULL, 'Redo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 284, 71, 4, '2007-03-21 06:42:05.66', NULL, 'Redo');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 285, 72, 1, '2007-03-21 06:42:05.678', NULL, 'Select an Object');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 286, 72, 2, '2007-03-21 06:42:05.681', NULL, 'Select an Object');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 287, 72, 3, '2007-03-21 06:42:05.685', NULL, 'Select an Object');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 288, 72, 4, '2007-03-21 06:42:05.687', NULL, 'Select an Object');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 289, 73, 1, '2007-03-21 06:42:05.692', NULL, 'Text');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 290, 73, 2, '2007-03-21 06:42:05.695', NULL, 'Text');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 291, 73, 3, '2007-03-21 06:42:05.697', NULL, 'Text');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 292, 73, 4, '2007-03-21 06:42:05.70', NULL, 'Text');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 293, 74, 1, '2007-03-21 06:42:05.705', NULL, 'Paint');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 294, 74, 2, '2007-03-21 06:42:05.707', NULL, 'Paint');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 295, 74, 3, '2007-03-21 06:42:05.711', NULL, 'Paint');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 296, 74, 4, '2007-03-21 06:42:05.714', NULL, 'Paint');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 297, 75, 1, '2007-03-21 06:42:05.721', NULL, 'Draw line');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 298, 75, 2, '2007-03-21 06:42:05.724', NULL, 'Draw line');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 300, 75, 4, '2007-03-21 06:42:05.731', NULL, 'Draw line');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 301, 76, 1, '2007-03-21 06:42:05.738', NULL, 'Draw underline');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 303, 76, 3, '2007-03-21 06:42:05.743', NULL, 'Draw underline');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 305, 77, 1, '2007-03-21 06:42:05.756', NULL, 'Rectangle');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 306, 77, 2, '2007-03-21 06:42:05.758', NULL, 'Rectangle');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 307, 77, 3, '2007-03-21 06:42:05.76', NULL, 'Rectangle');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 313, 79, 1, '2007-03-21 06:42:05.776', NULL, 'Arrow');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 315, 79, 3, '2007-03-21 06:42:05.783', NULL, 'Arrow');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 319, 80, 3, '2007-03-21 06:42:05.80', NULL, 'delete chosen Item');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 325, 82, 1, '2007-03-21 06:42:05.832', NULL, 'apply');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 333, 84, 1, '2007-03-21 06:42:05.861', NULL, 'Become moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 334, 84, 2, '2007-03-21 06:42:05.864', NULL, 'Become moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 335, 84, 3, '2007-03-21 06:42:05.867', NULL, 'Become moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 336, 84, 4, '2007-03-21 06:42:05.869', NULL, 'Become moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 337, 85, 1, '2007-03-21 06:42:05.89', NULL, 'close');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 340, 85, 4, '2007-03-21 06:42:05.898', NULL, 'close');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 341, 86, 1, '2007-03-21 06:42:05.905', NULL, 'italic');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 343, 86, 3, '2007-03-21 06:42:05.91', NULL, 'italic');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 348, 87, 4, '2007-03-21 06:42:05.93', NULL, 'bold');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 356, 89, 4, '2007-03-21 06:42:05.952', NULL, 'A User wants to apply for moderation:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 358, 90, 2, '2007-03-21 06:42:05.965', NULL, 'accept');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 360, 90, 4, '2007-03-21 06:42:05.972', NULL, 'accept');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 361, 91, 1, '2007-03-21 06:42:05.977', NULL, 'reject');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 363, 91, 3, '2007-03-21 06:42:05.985', NULL, 'reject');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 366, 92, 2, '2007-03-21 06:42:05.999', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 367, 92, 3, '2007-03-21 06:42:06.001', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 368, 92, 4, '2007-03-21 06:42:06.003', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 369, 93, 1, '2007-03-21 06:42:06.007', NULL, 'Sending request to following Users');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 370, 93, 2, '2007-03-21 06:42:06.009', NULL, 'Sending request to following Users');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 372, 93, 4, '2007-03-21 06:42:06.012', NULL, 'Sending request to following Users');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 373, 94, 1, '2007-03-21 06:42:06.018', NULL, 'Accepted');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 380, 95, 4, '2007-03-21 06:42:06.038', NULL, 'Rejected');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 381, 96, 1, '2007-03-21 06:42:06.045', NULL, 'Change Moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 383, 96, 3, '2007-03-21 06:42:06.05', NULL, 'Change Moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 386, 97, 2, '2007-03-21 06:42:06.061', NULL, 'You are not moderating this course!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 388, 97, 4, '2007-03-21 06:42:06.066', NULL, 'You are not moderating this course!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 393, 99, 1, '2007-03-21 06:42:06.086', NULL, 'This Room is full. Sorry please try again later.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 394, 99, 2, '2007-03-21 06:42:06.088', NULL, 'This Room is full. Sorry please try again later.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 395, 99, 3, '2007-03-21 06:42:06.091', NULL, 'This Room is full. Sorry please try again later.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 396, 99, 4, '2007-03-21 06:42:06.093', NULL, 'This Room is full. Sorry please try again later.');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 397, 100, 1, '2007-03-21 06:42:06.098', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 399, 100, 3, '2007-03-21 06:42:06.107', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 402, 101, 2, '2007-03-21 06:42:06.125', NULL, 'close');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 404, 101, 4, '2007-03-21 06:42:06.131', NULL, 'close');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 299, 75, 3, '2007-03-21 06:42:05.728', NULL, 'Draw line');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 302, 76, 2, '2007-03-21 06:42:05.741', NULL, 'Draw underline');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 304, 76, 4, '2007-03-21 06:42:05.746', NULL, 'Draw underline');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 308, 77, 4, '2007-03-21 06:42:05.762', NULL, 'Rectangle');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 309, 78, 1, '2007-03-21 06:42:05.766', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 310, 78, 2, '2007-03-21 06:42:05.769', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 311, 78, 3, '2007-03-21 06:42:05.77', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 312, 78, 4, '2007-03-21 06:42:05.772', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 314, 79, 2, '2007-03-21 06:42:05.78', NULL, 'Arrow');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 316, 79, 4, '2007-03-21 06:42:05.785', NULL, 'Arrow');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 317, 80, 1, '2007-03-21 06:42:05.792', NULL, 'delete chosen Item');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 318, 80, 2, '2007-03-21 06:42:05.797', NULL, 'delete chosen Item');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 320, 80, 4, '2007-03-21 06:42:05.803', NULL, 'delete chosen Item');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 321, 81, 1, '2007-03-21 06:42:05.813', NULL, 'Apply for moderation');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 322, 81, 2, '2007-03-21 06:42:05.816', NULL, 'Apply for moderation');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 323, 81, 3, '2007-03-21 06:42:05.822', NULL, 'Apply for moderation');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 324, 81, 4, '2007-03-21 06:42:05.825', NULL, 'Apply for moderation');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 326, 82, 2, '2007-03-21 06:42:05.839', NULL, 'apply');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 327, 82, 3, '2007-03-21 06:42:05.841', NULL, 'apply');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 328, 82, 4, '2007-03-21 06:42:05.843', NULL, 'apply');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 329, 83, 1, '2007-03-21 06:42:05.848', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 330, 83, 2, '2007-03-21 06:42:05.851', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 331, 83, 3, '2007-03-21 06:42:05.853', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 332, 83, 4, '2007-03-21 06:42:05.856', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 338, 85, 2, '2007-03-21 06:42:05.892', NULL, 'close');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 339, 85, 3, '2007-03-21 06:42:05.896', NULL, 'close');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 342, 86, 2, '2007-03-21 06:42:05.907', NULL, 'italic');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 344, 86, 4, '2007-03-21 06:42:05.918', NULL, 'italic');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 345, 87, 1, '2007-03-21 06:42:05.923', NULL, 'bold');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 346, 87, 2, '2007-03-21 06:42:05.925', NULL, 'bold');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 347, 87, 3, '2007-03-21 06:42:05.927', NULL, 'bold');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 349, 88, 1, '2007-03-21 06:42:05.935', NULL, 'WAITING');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 350, 88, 2, '2007-03-21 06:42:05.937', NULL, 'WAITING');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 351, 88, 3, '2007-03-21 06:42:05.939', NULL, 'WAITING');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 352, 88, 4, '2007-03-21 06:42:05.941', NULL, 'WAITING');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 353, 89, 1, '2007-03-21 06:42:05.946', NULL, 'A User wants to apply for moderation:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 354, 89, 2, '2007-03-21 06:42:05.948', NULL, 'A User wants to apply for moderation:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 355, 89, 3, '2007-03-21 06:42:05.95', NULL, 'A User wants to apply for moderation:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 357, 90, 1, '2007-03-21 06:42:05.963', NULL, 'accept');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 359, 90, 3, '2007-03-21 06:42:05.968', NULL, 'accept');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 362, 91, 2, '2007-03-21 06:42:05.982', NULL, 'reject');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 364, 91, 4, '2007-03-21 06:42:05.989', NULL, 'reject');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 365, 92, 1, '2007-03-21 06:42:05.994', NULL, 'cancel');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 371, 93, 3, '2007-03-21 06:42:06.01', NULL, 'Sending request to following Users');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 374, 94, 2, '2007-03-21 06:42:06.02', NULL, 'Accepted');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 375, 94, 3, '2007-03-21 06:42:06.024', NULL, 'Accepted');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 376, 94, 4, '2007-03-21 06:42:06.027', NULL, 'Accepted');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 377, 95, 1, '2007-03-21 06:42:06.032', NULL, 'Rejected');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 378, 95, 2, '2007-03-21 06:42:06.034', NULL, 'Rejected');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 379, 95, 3, '2007-03-21 06:42:06.036', NULL, 'Rejected');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 382, 96, 2, '2007-03-21 06:42:06.048', NULL, 'Change Moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 384, 96, 4, '2007-03-21 06:42:06.052', NULL, 'Change Moderator');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 385, 97, 1, '2007-03-21 06:42:06.059', NULL, 'You are not moderating this course!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 387, 97, 3, '2007-03-21 06:42:06.064', NULL, 'You are not moderating this course!');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 389, 98, 1, '2007-03-21 06:42:06.071', NULL, 'Moderator:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 390, 98, 2, '2007-03-21 06:42:06.073', NULL, 'Moderator:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 391, 98, 3, '2007-03-21 06:42:06.075', NULL, 'Moderator:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 392, 98, 4, '2007-03-21 06:42:06.077', NULL, 'Moderator:');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 398, 100, 2, '2007-03-21 06:42:06.101', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 400, 100, 4, '2007-03-21 06:42:06.111', NULL, 'Ellipse');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 401, 101, 1, '2007-03-21 06:42:06.118', NULL, 'close');
INSERT INTO fieldlanguagesvalues (deleted, fieldlanguagesvalues_id, fieldvalues_id, language_id, starttime, updatetime, value) VALUES (NULL, 403, 101, 3, '2007-03-21 06:42:06.128', NULL, 'close');


--
-- TOC entry 2250 (class 0 OID 36311)
-- Dependencies: 1447
-- Data for Name: fieldvalues; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 1, 'conference', '2007-03-19 11:38:13.545', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 2, 'meeting', '2007-03-19 11:38:13.584', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 3, 'classroom', '2007-03-19 11:38:13.605', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 4, 'settings', '2007-03-19 11:38:13.62', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 5, 'benutzer', '2007-03-19 11:38:13.65', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 6, 'admin', '2007-03-19 11:38:13.664', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 7, 'stop', '2007-03-19 11:38:26.819', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 8, 'record', '2007-03-19 11:38:26.917', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 9, 'nofile', '2007-03-19 11:38:26.942', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 10, 'recordbyteacher', '2007-03-19 11:38:26.961', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 11, 'connectedusers', '2007-03-19 11:38:26.975', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 12, 'startconf', '2007-03-19 11:38:26.994', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 13, 'myname', '2007-03-19 11:38:27.008', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 14, 'videoconference', '2007-03-19 11:38:27.02', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 15, 'import', '2007-03-19 11:38:27.035', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 16, 'refreshfiles', '2007-03-19 11:38:27.046', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 17, 'tomainfile', '2007-03-19 11:38:27.057', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 18, 'newpoll', '2007-03-19 11:38:27.072', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 19, 'newpollheader', '2007-03-19 11:38:27.09', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 20, 'question', '2007-03-19 11:38:27.103', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 21, 'polltype', '2007-03-19 11:38:27.124', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 22, 'create', '2007-03-19 11:38:27.139', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 23, 'infomessage', '2007-03-19 11:38:27.153', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 24, 'creatpoll', '2007-03-19 11:38:27.179', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 25, 'cancel', '2007-03-19 11:38:27.195', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 26, 'yesno', '2007-03-19 11:38:27.208', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 27, 'numeric', '2007-03-19 11:38:27.219', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 28, 'poll', '2007-03-19 11:38:27.235', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 29, 'moderation', '2007-03-19 11:38:27.25', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 30, 'vote', '2007-03-19 11:38:27.265', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 31, 'alreadyvoted', '2007-03-19 11:38:27.278', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 32, 'voting', '2007-03-19 11:38:27.288', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 33, 'answer', '2007-03-19 11:38:27.299', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 34, 'yes', '2007-03-19 11:38:27.316', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 35, 'no', '2007-03-19 11:38:27.33', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 36, 'questionwant', '2007-03-19 11:38:27.342', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 37, 'pollresults', '2007-03-19 11:38:27.352', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 38, 'question', '2007-03-19 11:38:27.363', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 39, 'results', '2007-03-19 11:38:27.379', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 40, 'answers', '2007-03-19 11:38:27.401', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 41, 'nopoll', '2007-03-19 11:38:27.413', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 42, 'votings', '2007-03-19 11:38:27.429', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 43, 'meeting', '2007-03-19 11:38:27.439', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 44, 'conference', '2007-03-19 11:38:27.449', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 45, 'type', '2007-03-19 11:38:27.46', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 46, 'remainingseats', '2007-03-19 11:38:27.472', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 47, 'alreadychosen', '2007-03-19 11:38:27.487', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 48, 'enter', '2007-03-19 11:38:27.499', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 49, 'modleave', '2007-03-19 11:38:27.514', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 50, 'systemmessage', '2007-03-19 11:38:27.528', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 51, 'chossedevice', '2007-03-19 11:38:27.54', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 52, 'choosecam', '2007-03-19 11:38:27.559', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 53, 'choosemic', '2007-03-19 11:38:27.576', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 54, 'ok', '2007-03-19 11:38:27.586', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 55, 'cancel2', '2007-03-19 11:38:27.596', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 56, 'reconeectneeded', '2007-03-19 11:38:27.608', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 57, 'editsetup', '2007-03-19 11:38:27.623', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 58, 'course', '2007-03-19 11:38:27.637', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 59, 'language', '2007-03-19 11:38:27.65', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 60, 'ok2', '2007-03-19 11:38:27.662', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 61, 'cancel3', '2007-03-19 11:38:27.678', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 62, 'clearwhiteboard', '2007-03-19 11:38:27.688', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 63, 'clearwhiteboardquestion', '2007-03-19 11:38:27.698', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 64, 'dontaskagain', '2007-03-19 11:38:27.707', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 65, 'no', '2007-03-19 11:38:27.716', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 66, 'editsetup2', '2007-03-19 11:38:27.732', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 67, 'needconfirmationwhiteboard', '2007-03-19 11:38:27.749', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 68, 'course', '2007-03-21 06:42:05.384', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 69, 'course', '2007-03-21 06:42:05.611', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 70, 'course', '2007-03-21 06:42:05.633', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 71, 'course', '2007-03-21 06:42:05.649', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 72, 'course', '2007-03-21 06:42:05.675', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 73, 'course', '2007-03-21 06:42:05.69', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 74, 'course', '2007-03-21 06:42:05.703', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 75, 'course', '2007-03-21 06:42:05.716', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 76, 'course', '2007-03-21 06:42:05.734', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 77, 'course', '2007-03-21 06:42:05.748', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 78, 'course', '2007-03-21 06:42:05.765', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 79, 'course', '2007-03-21 06:42:05.774', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 80, 'course', '2007-03-21 06:42:05.788', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 81, 'course', '2007-03-21 06:42:05.806', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 82, 'course', '2007-03-21 06:42:05.827', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 83, 'course', '2007-03-21 06:42:05.846', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 84, 'course', '2007-03-21 06:42:05.859', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 85, 'course', '2007-03-21 06:42:05.888', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 86, 'course', '2007-03-21 06:42:05.901', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 87, 'course', '2007-03-21 06:42:05.921', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 88, 'course', '2007-03-21 06:42:05.932', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 89, 'course', '2007-03-21 06:42:05.943', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 90, 'course', '2007-03-21 06:42:05.961', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 91, 'course', '2007-03-21 06:42:05.974', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 92, 'course', '2007-03-21 06:42:05.991', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 93, 'course', '2007-03-21 06:42:06.005', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 94, 'course', '2007-03-21 06:42:06.015', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 95, 'course', '2007-03-21 06:42:06.029', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 96, 'course', '2007-03-21 06:42:06.041', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 97, 'course', '2007-03-21 06:42:06.055', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 98, 'course', '2007-03-21 06:42:06.069', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 99, 'course', '2007-03-21 06:42:06.082', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 100, 'course', '2007-03-21 06:42:06.095', NULL);
INSERT INTO fieldvalues (deleted, fieldvalues_id, name, starttime, updatetime) VALUES (NULL, 101, 'course', '2007-03-21 06:42:06.113', NULL);


--
-- TOC entry 2251 (class 0 OID 36320)
-- Dependencies: 1449
-- Data for Name: lieferarten; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2252 (class 0 OID 36333)
-- Dependencies: 1451
-- Data for Name: naviglobal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO naviglobal (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, name, starttime, fieldvalues_id, deleted) VALUES (1, 'conf', '', 1, '', false, true, NULL, 1, 'conference', '2007-03-19 11:38:13.70', 1, NULL);
INSERT INTO naviglobal (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, name, starttime, fieldvalues_id, deleted) VALUES (2, 'settings', '', 2, '', false, true, NULL, 1, 'setings', '2007-03-19 11:38:13.726', 4, NULL);
INSERT INTO naviglobal (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, name, starttime, fieldvalues_id, deleted) VALUES (3, 'admin', '', 3, '', false, true, NULL, 1, 'admin', '2007-03-19 11:38:13.74', 6, NULL);


--
-- TOC entry 2253 (class 0 OID 36347)
-- Dependencies: 1453
-- Data for Name: navimain; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO navimain (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, main_id, name, starttime, fieldvalues_id, deleted) VALUES (1, 'conf1', '', 1, '', true, false, NULL, 1, 1, 'meeting', '2007-03-19 11:38:13.711', 2, NULL);
INSERT INTO navimain (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, main_id, name, starttime, fieldvalues_id, deleted) VALUES (2, 'conf2', '', 1, '', true, false, NULL, 1, 2, 'classroom', '2007-03-19 11:38:13.72', 3, NULL);
INSERT INTO navimain (naviorder, "action", "comment", global_id, icon, isleaf, isopen, updatetime, level_id, main_id, name, starttime, fieldvalues_id, deleted) VALUES (1, 'userself', '', 2, '', true, false, NULL, 1, 3, 'userself', '2007-03-19 11:38:13.736', 5, NULL);


--
-- TOC entry 2254 (class 0 OID 36362)
-- Dependencies: 1455
-- Data for Name: navisub; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2255 (class 0 OID 36377)
-- Dependencies: 1457
-- Data for Name: organisation; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2256 (class 0 OID 36385)
-- Dependencies: 1459
-- Data for Name: organisation_users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2257 (class 0 OID 36394)
-- Dependencies: 1461
-- Data for Name: phones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2258 (class 0 OID 36406)
-- Dependencies: 1463
-- Data for Name: sessiondata; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (1, '2007-03-19 11:39:04.753', '604d72687dcead3eaaa13d2f035b8ca2', '2007-03-19 11:38:54.389', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (2, '2007-03-19 11:45:05.884', '3c517fc4308586e9f4c5cdb84e2212f0', '2007-03-19 11:45:01.665', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (3, '2007-03-19 11:45:57.779', 'f3fdaf7e22860c00eda054257e11bf6a', '2007-03-19 11:45:54.568', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (4, '2007-03-19 11:46:27.837', 'a0634e8242f114617f3e15e9100b2a13', '2007-03-19 11:46:24.751', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (5, '2007-03-19 11:47:24.189', '1e026b21ce2ec10b95ea24faefffa2c5', '2007-03-19 11:47:20.348', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (6, '2007-03-19 11:48:18.399', 'ba2add6f17affa7e2f09316685bc64e1', '2007-03-19 11:48:18.399', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (7, '2007-03-20 15:23:11.232', 'e508bab46edca7a2ceb1a8886b7859f6', '2007-03-20 15:23:11.232', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (8, '2007-03-20 15:58:43.60', '638ce68a51d842602ba11eb4d240cc23', '2007-03-20 15:58:43.60', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (9, '2007-03-21 05:08:12.768', 'ea31dc0ba1bf686de82234533aef15f2', '2007-03-21 05:08:02.983', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (10, '2007-03-21 05:18:58.607', 'bd79ed06e251a1776193e9f9ece39682', '2007-03-21 05:18:54.708', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (11, '2007-03-21 05:19:30.683', '5408d40aa4abcc27f72d6890fd07526b', '2007-03-21 05:19:27.356', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (12, '2007-03-21 05:22:39.925', '5f58383e3fd4b0bd2646c0bf4d8947b2', '2007-03-21 05:22:39.925', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (13, '2007-03-21 05:23:13.378', '44eaf2c3612e26bf45ecd7836a8ca981', '2007-03-21 05:23:09.778', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (14, '2007-03-21 05:24:43.358', 'a8d3aa3a67c9e35870d23c9198111685', '2007-03-21 05:24:40.315', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (15, '2007-03-21 05:26:39.911', '6cf5c89537650d0b9b7075e1fd986681', '2007-03-21 05:26:37.314', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (16, '2007-03-21 05:28:40.842', 'a8536deecc3ffc2341b7c37371310e23', '2007-03-21 05:28:37.78', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (17, '2007-03-21 05:30:27.828', 'd3c7a4fcdfe86c20b37e6e7e42de0a67', '2007-03-21 05:30:25.165', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (18, '2007-03-21 05:31:10.867', 'd21abf97a18bec72b4a4af7d8796a2d1', '2007-03-21 05:31:08.324', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (19, '2007-03-21 05:32:01.518', 'cd5ad670186bcceffc17bc3ad21871a0', '2007-03-21 05:31:58.811', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (20, '2007-03-21 05:32:35.766', '0a9b378b272bedf0c378a9eef57d0a12', '2007-03-21 05:32:33.002', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (21, '2007-03-21 05:33:45.009', 'cba1f6e70451f10496cc6a5fc8738d56', '2007-03-21 05:33:26.496', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (22, '2007-03-21 05:34:32.742', '995c5a0172b3b4d39c2f92478caf6937', '2007-03-21 05:34:29.019', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (23, '2007-03-21 05:55:55.896', '11cc6dfd62e09ee93bd6b292343e42c9', '2007-03-21 05:55:53.236', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (24, '2007-03-21 05:56:23.419', 'f11e7786954a8dcf2d61a4b0436250f8', '2007-03-21 05:56:20.915', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (25, '2007-03-21 05:58:14.002', '3b747ee63237b99c4c107218eb0764f9', '2007-03-21 05:58:11.309', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (26, '2007-03-21 05:59:20.669', '9af9f2968834639760e9c36864ac87e5', '2007-03-21 05:59:17.938', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (27, '2007-03-21 05:59:47.692', '67d5079b368d35636def239d37f605b5', '2007-03-21 05:59:47.692', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (28, '2007-03-21 06:00:38.132', '31539c79b80f7975caebb399fcfaee49', '2007-03-21 06:00:35.536', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (29, '2007-03-21 06:01:18.114', 'a20b027dcf71606e3e940018ebf5740b', '2007-03-21 06:01:14.973', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (30, '2007-03-21 06:02:19.097', '6eef12781bb0c4b170b06e3c2f0577f7', '2007-03-21 06:02:16.335', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (31, '2007-03-21 06:03:11.376', 'c07b0bcae0cf3731a944fd176a915cba', '2007-03-21 06:03:06.034', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (38, '2007-03-21 06:44:40.02', 'd7d3fbb13e8d3b68ac39ecd164b413db', '2007-03-21 06:30:50.908', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (42, '2007-03-21 06:50:23.077', '047c4b3e4a2e4430a28e2d47c69a67ed', '2007-03-21 06:50:20.136', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (43, '2007-03-21 06:52:27.084', '2d559afdd3fc5a16c662f6bb3d8de9c9', '2007-03-21 06:52:27.084', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (44, '2007-03-21 06:53:18.985', 'a221f95213bbc68f3f48080baa59ccd5', '2007-03-21 06:53:05.961', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (53, '2007-03-21 07:20:11.179', 'a60fbaf53960d5e86b6a91148e756db7', '2007-03-21 07:20:11.179', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (32, '2007-03-21 06:08:02.865', '54263cf71721dfe9d91d3f49034111c7', '2007-03-21 06:08:00.385', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (33, '2007-03-21 06:20:53.619', '53b7db27971a6ef16af8f6ec3dd8fc1a', '2007-03-21 06:20:51.201', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (34, '2007-03-21 06:22:14.942', 'a8595e2d52ffd08ba7f0cd782f4cb806', '2007-03-21 06:22:09.889', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (35, '2007-03-21 06:25:10.865', '26459618255beb7d5533755f6692db3e', '2007-03-21 06:25:07.623', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (36, '2007-03-21 06:26:50.65', 'e3c4813e3a570eae3acd854e159a6ce1', '2007-03-21 06:26:47.995', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (37, '2007-03-21 06:28:49.112', '4fa785f97e9b0a2a7304cd0cb6e93158', '2007-03-21 06:28:41.839', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (39, '2007-03-21 06:46:21.588', '5207ee246f835d8b7f80f1e27625eff4', '2007-03-21 06:46:18.483', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (40, '2007-03-21 06:47:47.702', 'cabe6857412bd100ed8ebff4e9966fe1', '2007-03-21 06:47:44.661', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (41, '2007-03-21 06:49:02.048', '806f26734f777e8d30b91f7527238c4b', '2007-03-21 06:48:50.388', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (45, '2007-03-21 06:54:01.829', '8970beadb0ed8cd0e91f2324b5882443', '2007-03-21 06:54:01.829', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (46, '2007-03-21 06:54:55.414', '099e846d54cf09ddb5f8686b38af9779', '2007-03-21 06:54:51.604', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (47, '2007-03-21 06:58:46.429', '9dade24615f402ab626cbdaa8ec35048', '2007-03-21 06:58:43.439', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (48, '2007-03-21 07:01:03.98', '592af6fa7b1b230609beac08d7adfa13', '2007-03-21 07:01:03.98', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (49, '2007-03-21 07:01:43.002', 'aeb00b48dbe3833144c865362a4a30ec', '2007-03-21 07:01:39.685', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (50, '2007-03-21 07:03:18.619', 'bd14407150af4a7ec144aec2ab327ab3', '2007-03-21 07:03:09.073', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (51, '2007-03-21 07:10:32.167', '00f461a27b9fcb4de7743fe60895381d', '2007-03-21 07:05:03.131', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (52, '2007-03-21 07:19:53.877', '3cb3f259ef97540b1fdd8be7599d0062', '2007-03-21 07:11:24.572', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (54, '2007-03-21 09:34:38.278', 'd230e110acdf31afa884c2d201dc8d33', '2007-03-21 09:34:38.278', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (55, '2007-03-21 09:36:36.528', '25ca6e88fcc73326c09c55db258621cc', '2007-03-21 09:35:10.792', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (56, '2007-03-21 09:46:43.412', 'f8db8250f0b8e88f975a36dcc3a53f62', '2007-03-21 09:46:33.793', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (57, '2007-03-21 09:47:41.723', 'c2f99df67d0b8fe47a087adb7de81ff1', '2007-03-21 09:47:37.341', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (58, '2007-03-21 09:51:08.145', '29411596c23a6aa76f62c36590e05f69', '2007-03-21 09:50:56.733', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (59, '2007-03-21 09:52:19.36', '9b14077b95d6089b03b4d2ca232518e3', '2007-03-21 09:52:19.36', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (60, '2007-03-21 09:52:32.253', '2e46673fa9b895e81118d5fb6cc4294a', '2007-03-21 09:52:28.02', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (61, '2007-03-21 09:53:37.494', '31cc74430b838bb456c6c2aa888566e6', '2007-03-21 09:53:37.494', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (62, '2007-03-21 09:57:31.749', '205bd415f6ac1794276db3efa1646be4', '2007-03-21 09:57:21.408', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (63, '2007-03-21 09:57:57.797', '7a2b48d8f93c02597576de5ecfab5896', '2007-03-21 09:57:53.698', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (64, '2007-03-21 09:58:35.937', '069be48adcf72ec802cfaf7a5e50cb42', '2007-03-21 09:58:35.937', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (66, '2007-03-21 10:00:05.863', '606533ac67d543313641ebc290a2356e', '2007-03-21 10:00:02.349', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (65, '2007-03-21 10:00:25.458', '903a5cb583cc7d0bc451a8bee916443c', '2007-03-21 09:59:52.132', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (67, '2007-03-21 10:01:00.511', 'fe736f9d197487de3bde3d6adf916705', '2007-03-21 10:00:56.86', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (68, '2007-03-21 10:01:24.003', 'd77fd71b17d71e029fcad5d735c4e757', '2007-03-21 10:01:24.003', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (69, '2007-03-21 10:04:14.825', '4002a1595312af4203b3b3060b1597df', '2007-03-21 10:01:32.158', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (70, '2007-03-21 10:05:19.623', '9f28515f9f984d1a3c43e06423c691a3', '2007-03-21 10:05:19.623', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (71, '2007-03-21 10:17:25.193', 'd573cb12421286c41637403850b6ce9e', '2007-03-21 10:17:25.193', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (72, '2007-03-21 10:19:01.316', '82ee5aa28590f975eaecb4e759335b60', '2007-03-21 10:18:57.561', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (73, '2007-03-21 10:19:54.834', '4b6b1b2dd5bc7e614d6ecf6ef6704836', '2007-03-21 10:19:43.926', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (74, '2007-03-21 10:20:27.756', '70bd5e5473bfe7f3a9e3b9b42bb5a462', '2007-03-21 10:20:23.996', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (75, '2007-03-21 10:21:16.949', '80b7f85c534329b582145c7d161b9b51', '2007-03-21 10:21:16.949', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (76, '2007-03-21 10:24:12.63', 'fcc5e60cf26001f1064b4ca24604895b', '2007-03-21 10:24:05.19', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (77, '2007-03-21 10:24:53.143', '87ea1a50445ea8d59765ec58795f4064', '2007-03-21 10:24:49.772', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (78, '2007-03-21 10:26:31.246', '09503a747ef347249831e6fb758f3607', '2007-03-21 10:26:22.93', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (79, '2007-03-21 10:27:37.695', '04bbb79e254715d5158e2846ce273c73', '2007-03-21 10:27:35.506', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (80, '2007-03-21 10:31:49.579', 'fb0dd04c0e05034050e7c5c2fdd0eecb', '2007-03-21 10:31:42.189', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (81, '2007-03-21 10:32:23.269', '7db6942abed5198ed351f5ac7e4455a6', '2007-03-21 10:32:20.817', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (82, '2007-03-21 10:32:49.233', '5c562b26c4028cb861ff4ec1f0192f85', '2007-03-21 10:32:49.233', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (83, '2007-03-21 10:32:52.858', '93aad1fb9f5fc8de5f4e27ece0c0df2d', '2007-03-21 10:32:52.858', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (84, '2007-03-21 10:40:02.589', '2d40dbfd8be9a499b39f20dd707ab59d', '2007-03-21 10:39:51.245', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (85, '2007-03-21 10:41:58.42', '671e6adf1455a3b3e046aebafed4f9a1', '2007-03-21 10:41:50.458', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (86, '2007-03-21 10:43:46.184', '5a95d1f08504f3886e755e2a9bee03b4', '2007-03-21 10:43:37.159', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (88, '2007-03-21 11:09:57.547', '6a71c870230c5580d310fdbf38fade59', '2007-03-21 10:59:55.808', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (87, '2007-03-21 11:10:15.309', '3c2f8c7b540f20b43bb12d60459454b2', '2007-03-21 10:44:59.312', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (89, '2007-03-21 11:10:46.544', 'd81c58ddda9c2813792afb36be5d48c5', '2007-03-21 11:10:46.544', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (90, '2007-03-21 11:17:34.484', '3e4d9c9b45d22b8b7afb1e4f97492ce6', '2007-03-21 11:17:23.383', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (91, '2007-03-21 11:17:56.407', 'f1ce325d87aa7d794ebd3adce1c407c3', '2007-03-21 11:17:54.061', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (92, '2007-03-21 11:18:19.70', '66465228ed01e6e2c77e90b1a40d1a9b', '2007-03-21 11:18:19.70', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (93, '2007-03-21 11:20:32.059', '2270358e7d6abcfb09cb3c7b7394cfd5', '2007-03-21 11:20:24.625', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (94, '2007-03-21 11:20:47.59', '693b6b5f81dcc6576606c31099a3b29e', '2007-03-21 11:20:47.59', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (95, '2007-03-21 11:37:02.977', '3d2ba6183a92cd07af01c37187eaddab', '2007-03-21 11:31:34.776', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (96, '2007-03-21 11:37:27.048', '1350cf652fa2cacf566e11dae30a629a', '2007-03-21 11:37:27.048', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (97, '2007-03-21 12:09:15.115', 'f4aeb2e5d16544b8a1a5c2a709466719', '2007-03-21 11:48:47.651', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (98, '2007-03-21 12:09:30.946', '07b3babeee7bce6bc6e256504d7e5135', '2007-03-21 12:09:30.946', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (99, '2007-03-21 12:12:57.437', '1a3ec74308433ab58dc2b9471394356a', '2007-03-21 12:12:49.24', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (100, '2007-03-21 12:13:25.822', 'f5a46c9b7a310518d298f870cfa9d2a9', '2007-03-21 12:13:25.822', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (101, '2007-03-21 12:16:02.124', 'a01995f2f1b6bfe326c923a7b2b6a51a', '2007-03-21 12:15:54.49', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (102, '2007-03-21 12:16:21.889', '76662e31325471ac2806f2870c788b15', '2007-03-21 12:16:21.889', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (103, '2007-03-21 12:18:21.235', 'e5897702297469f040860edc76de2c17', '2007-03-21 12:18:13.366', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (104, '2007-03-21 12:18:37.646', 'bb635c39ef5288b0a8a60f855a7fffa1', '2007-03-21 12:18:37.646', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (105, '2007-03-21 12:21:15.619', 'e20ab90a951c4930b9722403a1b21217', '2007-03-21 12:21:07.745', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (106, '2007-03-21 12:21:52.132', 'c0c02d74ee05476e86457ca8ecd75d5f', '2007-03-21 12:21:37.578', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (107, '2007-03-21 12:22:11.806', '96a64567409e50d3477c93255ea0cfd3', '2007-03-21 12:22:08.446', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (108, '2007-03-21 12:22:33.482', '5a7f446740ee4fc82d29ba6bce5d2309', '2007-03-21 12:22:33.482', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (109, '2007-03-21 12:47:59.095', '53c86ed1b6c3e1b81a79901f000d09c3', '2007-03-21 12:47:46.848', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (110, '2007-03-21 12:48:26.881', '85858fefa664485b019ac0d5a7d4671e', '2007-03-21 12:48:24.485', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (111, '2007-03-21 12:49:07.383', '624288e3bad4995e36b375cb7cb408ec', '2007-03-21 12:49:03.405', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (112, '2007-03-21 12:49:45.186', '3dd8ccea432219102f864f4f6b3e3766', '2007-03-21 12:49:45.186', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (113, '2007-03-21 23:25:03.235', 'fb7c06a5c5bf8ca9ad627f85c76a067b', '2007-03-21 23:25:03.235', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (114, '2007-03-21 23:25:17.327', '31e508c8058b6b8672c215dc3ef9c7a3', '2007-03-21 23:25:13.963', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (115, '2007-03-21 23:25:42.85', '10c68f2a3c09c492c2ce192b4346a3aa', '2007-03-21 23:25:40.674', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (116, '2007-03-21 23:26:16.171', 'fa5795aa4d6e179630ae9ac85aa49999', '2007-03-21 23:26:11.765', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (117, '2007-03-21 23:26:33.398', '1396b8f3bd9e2251ba5fdf9432f388e7', '2007-03-21 23:26:31.402', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (118, '2007-03-21 23:29:26.247', '5e1432d136a1be5e4cf6b7a8a25a63ea', '2007-03-21 23:29:15.418', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (119, '2007-03-21 23:29:42.452', '06da47af63ca5acaddf3d10fdcc3fcf7', '2007-03-21 23:29:23.638', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (120, '2007-03-21 23:30:46.376', '7083d63ca4e7893b47dfe81ba3fa20ca', '2007-03-21 23:30:46.376', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (121, '2007-03-21 23:30:59.289', 'fae0181562f0eb7f74b2bc32d84c348d', '2007-03-21 23:30:55.426', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (122, '2007-03-21 23:32:09.108', '21b81f686843131d717b3812167a6c62', '2007-03-21 23:32:09.108', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (123, '2007-03-21 23:32:33.47', '50c4d27de946e59ff15fa923c54b8e7a', '2007-03-21 23:32:33.47', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (124, '2007-03-22 09:57:13.69', 'da4433b9dfef047c39732dce0051528e', '2007-03-22 09:57:06.053', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (125, '2007-03-22 10:05:24.427', 'a76e5798f9e0934f98ca6486db950329', '2007-03-22 10:05:14.269', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (126, '2007-03-22 10:06:05.54', '16f7a5b134179e453190c11ecf8cda86', '2007-03-22 10:06:05.54', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (127, '2007-03-22 10:09:08.694', '93f2ff2e0aa9cc129e0c91b3a775a42f', '2007-03-22 10:09:02.256', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (128, '2007-03-22 10:25:52.96', 'a6100fb03860d02820805f1439375748', '2007-03-22 10:25:46.55', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (129, '2007-03-22 10:29:27.782', '5c79fe8747914f1a4b3c880a3cf75fb1', '2007-03-22 10:29:27.782', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (130, '2007-03-23 15:28:34.617', '2fb778b35de640796e9cf38740bab169', '2007-03-23 15:28:23.007', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (131, '2007-03-23 15:29:08.114', '21fd4e0811f5c5243c7efb6d8fbb84a8', '2007-03-23 15:29:05.405', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (132, '2007-03-23 15:33:45.053', '319701825c6b8376de54e3a0317c40db', '2007-03-23 15:33:45.053', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (133, '2007-03-27 18:08:01.099', 'fe47ad88f076ab2c2e6febf8bcf5e615', '2007-03-27 18:08:00.362', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (134, '2007-03-27 18:09:45.047', '3913b6332c3e9f55e1bd7b911d65ede5', '2007-03-27 18:09:44.675', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (135, '2007-03-27 18:09:55.719', '030e68abcca22a015ca1678b2e041882', '2007-03-27 18:09:55.373', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (136, '2007-03-27 18:10:17.612', '15ee5aff7c9b604550b62ce833883f2a', '2007-03-27 18:10:17.273', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (137, '2007-03-27 18:11:40.764', 'a9b8a1c0e01b7617ff576c5833f342b6', '2007-03-27 18:11:40.416', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (138, '2007-03-27 18:11:53.335', 'a24986745b9920ab9053a30eb32d29da', '2007-03-27 18:11:52.976', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (139, '2007-03-27 18:12:55.189', '14997f96e1d0ce50264ce948545083a0', '2007-03-27 18:12:54.841', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (140, '2007-03-27 18:13:26.169', '3c82b3c2de2b4d90a32cb1afffa3498e', '2007-03-27 18:13:25.823', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (141, '2007-03-27 18:14:52.323', 'a6c31ac471e81e50cde1ceee093a4950', '2007-03-27 18:14:51.977', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (142, '2007-03-27 18:18:56.581', 'af853c9b67e9c4ada085d132fe9af630', '2007-03-27 18:18:56.236', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (143, '2007-03-27 18:20:16.597', 'e0b083b5462051f05b0d9868e6b9f4ab', '2007-03-27 18:20:16.252', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (144, '2007-03-27 18:20:49.536', '87e042e54a8756addc63701e513dafc4', '2007-03-27 18:20:49.173', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (145, '2007-03-27 18:21:43.30', '35e8aaca2d27a00e92bb0eaa547fbac3', '2007-03-27 18:21:42.942', 1);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (146, '2007-03-28 14:39:30.916', 'ecda91dc5b78cc7ebd3fceaba8691f5a', '2007-03-28 14:39:30.916', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (147, '2007-03-28 14:58:01.721', '24ac86ffa66f6218a07b89ab7566be80', '2007-03-28 14:58:01.721', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (148, '2007-03-28 14:59:50.874', '3115b018aaadb68bd355531d373735c8', '2007-03-28 14:59:50.874', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (149, '2007-03-28 14:59:57.529', 'ac21fd5621fd65f1a3d4dd53f016458c', '2007-03-28 14:59:57.529', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (150, '2007-03-28 15:07:10.60', '59ca05f4b9e844ee25b667f4b483a5a7', '2007-03-28 15:07:10.60', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (151, '2007-03-28 15:08:22.429', 'a4ef709d73406b73ab9e4947980d7a3a', '2007-03-28 15:08:22.429', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (152, '2007-03-28 15:08:57.159', '09a2cc43388e3ea32f937aace1298ab5', '2007-03-28 15:08:57.159', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (153, '2007-03-28 15:41:30.729', '2a1c0cdb8693f331cd01ca4df80a61d1', '2007-03-28 15:41:30.729', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (154, '2007-03-28 15:43:38.934', '4931572fd2ba59bcb060bfbd17ed734a', '2007-03-28 15:43:38.934', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (155, '2007-03-28 15:50:12.49', '7db8671cfbbb229c52942f91cbf868c5', '2007-03-28 15:50:12.49', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (156, '2007-03-28 15:50:47.15', '815d3f1521bfe67f995619f3daf64ce0', '2007-03-28 15:50:47.15', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (157, '2007-03-28 15:56:46.437', 'a9b71c3fa568f06360e50552d0743f9a', '2007-03-28 15:56:46.437', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (158, '2007-03-28 15:57:30.042', '3a486937725c85c9c273d768e103757a', '2007-03-28 15:57:30.042', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (159, '2007-03-28 15:58:31.651', '4090baebc87df83ee65b75b1f23f91d0', '2007-03-28 15:58:31.651', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (160, '2007-03-28 16:05:15.517', '31cfc85fa9598713e33e23f9f26bc7dc', '2007-03-28 16:05:15.518', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (161, '2007-03-28 16:09:14.734', '333580f08da4d2719d561c2446d329ea', '2007-03-28 16:09:14.734', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (162, '2007-03-28 16:14:06.808', 'fa1af2d4fd081336a240a9ea5fe63d31', '2007-03-28 16:14:06.808', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (163, '2007-03-28 16:15:50.10', '6ea6ddee9601e8f9758450c67358f0dc', '2007-03-28 16:15:50.10', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (164, '2007-03-28 16:18:42.826', 'c7fa7bba909ad3c9858fead271b125a1', '2007-03-28 16:18:42.826', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (165, '2007-03-28 16:20:12.49', 'f0840566bef28eabce126b42d1aed9ca', '2007-03-28 16:20:12.49', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (166, '2007-03-28 16:20:41.128', 'c298afce4ae42dd60c802c5f87b58f68', '2007-03-28 16:20:41.128', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (167, '2007-03-28 16:21:21.649', '506d78591c319ff01578da56a9df1e7c', '2007-03-28 16:21:21.649', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (168, '2007-03-28 16:21:56.483', '0840de4791f99500405d200aecc29bd4', '2007-03-28 16:21:56.483', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (169, '2007-03-28 16:26:11.372', 'dab97472133a92a9da5ccc79be812aa2', '2007-03-28 16:26:11.372', NULL);
INSERT INTO sessiondata (id, refresh_time, session_id, starttermin_time, user_id) VALUES (170, '2007-03-28 17:19:04.626', 'ede6e3e6e4f7b31cb6130fef148f73fb', '2007-03-28 17:19:04.626', NULL);


--
-- TOC entry 2259 (class 0 OID 36416)
-- Dependencies: 1465
-- Data for Name: states; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO states (name, starttime, state_id, updatetime, deleted) VALUES ('Deutschland', '2007-03-24 13:05:15.574', 1, NULL, false);


--
-- TOC entry 2260 (class 0 OID 36425)
-- Dependencies: 1467
-- Data for Name: suppliergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2261 (class 0 OID 36437)
-- Dependencies: 1469
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2262 (class 0 OID 36446)
-- Dependencies: 1471
-- Data for Name: suppliers_suppliergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2263 (class 0 OID 36455)
-- Dependencies: 1473
-- Data for Name: termine; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2264 (class 0 OID 36472)
-- Dependencies: 1475
-- Data for Name: termine_participant; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2265 (class 0 OID 36481)
-- Dependencies: 1477
-- Data for Name: termine_todo_user; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2266 (class 0 OID 36497)
-- Dependencies: 1479
-- Data for Name: termine_todolist; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2267 (class 0 OID 36510)
-- Dependencies: 1481
-- Data for Name: termine_user; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2268 (class 0 OID 36528)
-- Dependencies: 1483
-- Data for Name: terminegroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2269 (class 0 OID 36545)
-- Dependencies: 1485
-- Data for Name: terminestatus; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2270 (class 0 OID 36558)
-- Dependencies: 1487
-- Data for Name: titles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO titles (name, starttime, title_id, updatetime, deleted) VALUES ('Herr', '2007-03-19 11:38:13.745', 1, NULL, NULL);
INSERT INTO titles (name, starttime, title_id, updatetime, deleted) VALUES ('Frau', '2007-03-19 11:38:13.749', 2, NULL, NULL);


--
-- TOC entry 2271 (class 0 OID 36567)
-- Dependencies: 1489
-- Data for Name: transstatus; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2272 (class 0 OID 36579)
-- Dependencies: 1491
-- Data for Name: userdata; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2273 (class 0 OID 36592)
-- Dependencies: 1493
-- Data for Name: usergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2274 (class 0 OID 36604)
-- Dependencies: 1495
-- Data for Name: userlevel; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO userlevel (description, level_id, starttime, statuscode, updatetime, deleted) VALUES ('User', 1, '2007-03-19 11:38:13.685', 1, NULL, NULL);
INSERT INTO userlevel (description, level_id, starttime, statuscode, updatetime, deleted) VALUES ('Moderator', 2, '2007-03-19 11:38:13.69', 2, NULL, NULL);
INSERT INTO userlevel (description, level_id, starttime, statuscode, updatetime, deleted) VALUES ('Admin', 3, '2007-03-19 11:38:13.694', 3, NULL, NULL);


--
-- TOC entry 2275 (class 0 OID 36617)
-- Dependencies: 1497
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) VALUES (NULL, '2007-03-27 16:43:30.908', 1, 'Sebastian', '2007-03-27 16:43:30.979', 'Wagner', 0, 1, 'swagner2', '519edbe68ddca5be0c26ed1d4807bdd2', '2007-03-27 16:43:31.008', 1, 1, 2, NULL, NULL, '2007-03-27 16:43:30.993', '2007-03-27 16:43:30.993', 1, NULL, NULL);
INSERT INTO users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) VALUES (NULL, '2007-03-27 16:48:31.379', 1, 'Sebastian', '2007-03-27 16:48:31.421', 'Wagner', 0, 1, 'swagner3', '519edbe68ddca5be0c26ed1d4807bdd2', '2007-03-27 16:48:31.424', 1, 1, 3, NULL, NULL, '2007-03-27 16:48:31.422', '2007-03-27 16:48:31.422', 1, NULL, NULL);
INSERT INTO users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) VALUES (NULL, '2007-03-27 16:59:45.659', 1, 'Sebastian', '2007-03-27 16:59:45.706', 'Wagner', 0, 3, 'swagner4', '519edbe68ddca5be0c26ed1d4807bdd2', '2007-03-27 16:59:45.708', 1, 1, 4, NULL, NULL, '2007-03-27 16:59:45.707', '2007-03-27 16:59:45.707', 1, NULL, NULL);
INSERT INTO users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) VALUES (NULL, '2007-03-27 18:09:45.174', 0, 'firstname', '2007-03-27 18:09:45.211', 'lastname', 0, 1, 'username', '63e780c3f321d13109c71bf81805476e', '2007-03-27 18:09:45.211', 1, 1, 5, NULL, NULL, '2007-03-27 18:09:45.211', '2007-03-27 18:09:45.211', 1, NULL, NULL);
INSERT INTO users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) VALUES (NULL, '2007-03-27 18:10:17.795', 0, 'firstname', '2007-03-27 18:10:17.828', 'lastname', 0, 1, 'username2', '63e780c3f321d13109c71bf81805476e', '2007-03-27 18:10:17.829', 1, 1, 6, NULL, NULL, '2007-03-27 18:10:17.828', '2007-03-27 18:10:17.828', 1, NULL, NULL);
INSERT INTO users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) VALUES (NULL, '2007-03-27 18:11:53.749', 0, 'firstname', '2007-03-27 18:11:53.786', 'lastname', 0, 1, 'username3', '63e780c3f321d13109c71bf81805476e', '2007-03-27 18:11:53.787', 1, 1, 7, NULL, NULL, '2007-03-27 18:11:53.787', '2007-03-27 18:11:53.787', 1, NULL, NULL);
INSERT INTO users (adresses_id, age, availible, firstname, lastlogin, lastname, lasttrans, level_id, "login", "password", regdate, status, title_id, user_id, delivery_adress_id, domicile_adress_id, starttime, updatetime, language_id, pictureuri, deleted) VALUES (NULL, '2007-03-19 11:38:13.834', 1, 'Sebastian', '2007-03-19 11:38:13.834', 'Wagner', 0, 1, 'swagner', '519edbe68ddca5be0c26ed1d4807bdd2', '2007-03-19 11:38:13.835', 1, 1, 1, NULL, NULL, '2007-03-19 11:38:13.834', '2007-03-19 11:38:13.834', 1, NULL, NULL);


--
-- TOC entry 2276 (class 0 OID 36634)
-- Dependencies: 1499
-- Data for Name: users_usergroups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2277 (class 0 OID 36648)
-- Dependencies: 1501
-- Data for Name: userwaren; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2278 (class 0 OID 36666)
-- Dependencies: 1503
-- Data for Name: zahlungsarten; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2043 (class 2606 OID 36073)
-- Dependencies: 1400 1400
-- Name: pkadresses; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adresses
    ADD CONSTRAINT pkadresses PRIMARY KEY (adresses_id);


--
-- TOC entry 2045 (class 2606 OID 36082)
-- Dependencies: 1402 1402
-- Name: pkadresses_emails; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adresses_emails
    ADD CONSTRAINT pkadresses_emails PRIMARY KEY (adresses_emails_id);


--
-- TOC entry 2047 (class 2606 OID 36091)
-- Dependencies: 1404 1404
-- Name: pkadresses_phones; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adresses_phones
    ADD CONSTRAINT pkadresses_phones PRIMARY KEY (adresses_phone_id);


--
-- TOC entry 2050 (class 2606 OID 36100)
-- Dependencies: 1406 1406
-- Name: pkarticlegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articlegroups
    ADD CONSTRAINT pkarticlegroups PRIMARY KEY (articlegroup_id);


--
-- TOC entry 2053 (class 2606 OID 36113)
-- Dependencies: 1408 1408
-- Name: pkarticles; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT pkarticles PRIMARY KEY (article_id);


--
-- TOC entry 2056 (class 2606 OID 36123)
-- Dependencies: 1410 1410
-- Name: pkarticles_articlegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articles_articlegroups
    ADD CONSTRAINT pkarticles_articlegroups PRIMARY KEY (articles_articlesgroups_id);


--
-- TOC entry 2058 (class 2606 OID 36133)
-- Dependencies: 1412 1412
-- Name: pkarticles_lieferarten; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articles_lieferarten
    ADD CONSTRAINT pkarticles_lieferarten PRIMARY KEY (articles_lieferarten_id);


--
-- TOC entry 2061 (class 2606 OID 36146)
-- Dependencies: 1414 1414
-- Name: pkconfiguration; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY configuration
    ADD CONSTRAINT pkconfiguration PRIMARY KEY (configuration_id);


--
-- TOC entry 2063 (class 2606 OID 36159)
-- Dependencies: 1416 1416
-- Name: pkcontactfreigabe; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactfreigabe
    ADD CONSTRAINT pkcontactfreigabe PRIMARY KEY (freigabe_id);


--
-- TOC entry 2065 (class 2606 OID 36169)
-- Dependencies: 1417 1417
-- Name: pkcontactgroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactgroups
    ADD CONSTRAINT pkcontactgroups PRIMARY KEY (contactgroup_id);


--
-- TOC entry 2067 (class 2606 OID 36178)
-- Dependencies: 1419 1419
-- Name: pkcontactgroups_contacts; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactgroups_contacts
    ADD CONSTRAINT pkcontactgroups_contacts PRIMARY KEY (contactgroups_contact_id);


--
-- TOC entry 2069 (class 2606 OID 36187)
-- Dependencies: 1421 1421
-- Name: pkcontactgroups_conuser; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactgroups_conuser
    ADD CONSTRAINT pkcontactgroups_conuser PRIMARY KEY (contactgroup_user_id);


--
-- TOC entry 2071 (class 2606 OID 36201)
-- Dependencies: 1423 1423
-- Name: pkcontacts; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT pkcontacts PRIMARY KEY (contact_id);


--
-- TOC entry 2073 (class 2606 OID 36210)
-- Dependencies: 1425 1425
-- Name: pkconuser; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY conuser
    ADD CONSTRAINT pkconuser PRIMARY KEY (conuser_id);


--
-- TOC entry 2075 (class 2606 OID 36222)
-- Dependencies: 1427 1427
-- Name: pkemails; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY emails
    ADD CONSTRAINT pkemails PRIMARY KEY (mail_id);


--
-- TOC entry 2077 (class 2606 OID 36234)
-- Dependencies: 1429 1429
-- Name: pkemployeegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employeegroups
    ADD CONSTRAINT pkemployeegroups PRIMARY KEY (employeegroup_id);


--
-- TOC entry 2079 (class 2606 OID 36245)
-- Dependencies: 1431 1431
-- Name: pkemployees; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees
    ADD CONSTRAINT pkemployees PRIMARY KEY (employee_id);


--
-- TOC entry 2081 (class 2606 OID 36254)
-- Dependencies: 1433 1433
-- Name: pkemployees_articlegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_articlegroups
    ADD CONSTRAINT pkemployees_articlegroups PRIMARY KEY (employees_articlegroups_id);


--
-- TOC entry 2083 (class 2606 OID 36263)
-- Dependencies: 1435 1435
-- Name: pkemployees_articles; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_articles
    ADD CONSTRAINT pkemployees_articles PRIMARY KEY (employees_articles_id);


--
-- TOC entry 2085 (class 2606 OID 36272)
-- Dependencies: 1437 1437
-- Name: pkemployees_employeegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_employeegroups
    ADD CONSTRAINT pkemployees_employeegroups PRIMARY KEY (employee_employeegroup_id);


--
-- TOC entry 2087 (class 2606 OID 36281)
-- Dependencies: 1439 1439
-- Name: pkemployees_suppliergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_suppliergroups
    ADD CONSTRAINT pkemployees_suppliergroups PRIMARY KEY (employees_suppliergroups_id);


--
-- TOC entry 2089 (class 2606 OID 36290)
-- Dependencies: 1441 1441
-- Name: pkemployees_suppliers; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employees_suppliers
    ADD CONSTRAINT pkemployees_suppliers PRIMARY KEY (employees_suplier_id);


--
-- TOC entry 2091 (class 2606 OID 36299)
-- Dependencies: 1443 1443
-- Name: pkfieldlanguage; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fieldlanguage
    ADD CONSTRAINT pkfieldlanguage PRIMARY KEY (language_id);


--
-- TOC entry 2093 (class 2606 OID 36308)
-- Dependencies: 1445 1445
-- Name: pkfieldlanguagesvalues; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fieldlanguagesvalues
    ADD CONSTRAINT pkfieldlanguagesvalues PRIMARY KEY (fieldlanguagesvalues_id);


--
-- TOC entry 2095 (class 2606 OID 36317)
-- Dependencies: 1447 1447
-- Name: pkfieldvalues; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fieldvalues
    ADD CONSTRAINT pkfieldvalues PRIMARY KEY (fieldvalues_id);


--
-- TOC entry 2097 (class 2606 OID 36330)
-- Dependencies: 1449 1449
-- Name: pklieferarten; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lieferarten
    ADD CONSTRAINT pklieferarten PRIMARY KEY (liefer_id);


--
-- TOC entry 2099 (class 2606 OID 36344)
-- Dependencies: 1451 1451
-- Name: pknaviglobal; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY naviglobal
    ADD CONSTRAINT pknaviglobal PRIMARY KEY (global_id);


--
-- TOC entry 2101 (class 2606 OID 36359)
-- Dependencies: 1453 1453
-- Name: pknavimain; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT pknavimain PRIMARY KEY (main_id);


--
-- TOC entry 2103 (class 2606 OID 36374)
-- Dependencies: 1455 1455
-- Name: pknavisub; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT pknavisub PRIMARY KEY (sub_id);


--
-- TOC entry 2105 (class 2606 OID 36382)
-- Dependencies: 1457 1457
-- Name: pkorganisation; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisation
    ADD CONSTRAINT pkorganisation PRIMARY KEY (organisation_id);


--
-- TOC entry 2107 (class 2606 OID 36391)
-- Dependencies: 1459 1459
-- Name: pkorganisation_users; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisation_users
    ADD CONSTRAINT pkorganisation_users PRIMARY KEY (organisation_users_id);


--
-- TOC entry 2109 (class 2606 OID 36403)
-- Dependencies: 1461 1461
-- Name: pkphones; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY phones
    ADD CONSTRAINT pkphones PRIMARY KEY (phone_id);


--
-- TOC entry 2111 (class 2606 OID 36413)
-- Dependencies: 1463 1463
-- Name: pksessiondata; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sessiondata
    ADD CONSTRAINT pksessiondata PRIMARY KEY (id);


--
-- TOC entry 2113 (class 2606 OID 36422)
-- Dependencies: 1465 1465
-- Name: pkstates; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY states
    ADD CONSTRAINT pkstates PRIMARY KEY (state_id);


--
-- TOC entry 2115 (class 2606 OID 36434)
-- Dependencies: 1467 1467
-- Name: pksuppliergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY suppliergroups
    ADD CONSTRAINT pksuppliergroups PRIMARY KEY (suppliergroup_id);


--
-- TOC entry 2117 (class 2606 OID 36443)
-- Dependencies: 1469 1469
-- Name: pksuppliers; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pksuppliers PRIMARY KEY (supplier_id);


--
-- TOC entry 2119 (class 2606 OID 36452)
-- Dependencies: 1471 1471
-- Name: pksuppliers_suppliergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY suppliers_suppliergroups
    ADD CONSTRAINT pksuppliers_suppliergroups PRIMARY KEY (supplier_suppliergroup_id);


--
-- TOC entry 2121 (class 2606 OID 36469)
-- Dependencies: 1473 1473
-- Name: pktermine; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine
    ADD CONSTRAINT pktermine PRIMARY KEY (termin_id);


--
-- TOC entry 2123 (class 2606 OID 36478)
-- Dependencies: 1475 1475
-- Name: pktermine_participant; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_participant
    ADD CONSTRAINT pktermine_participant PRIMARY KEY (termine_participant_id);


--
-- TOC entry 2125 (class 2606 OID 36494)
-- Dependencies: 1477 1477
-- Name: pktermine_todo_user; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT pktermine_todo_user PRIMARY KEY (termine_todo_user_id);


--
-- TOC entry 2127 (class 2606 OID 36507)
-- Dependencies: 1479 1479
-- Name: pktermine_todolist; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_todolist
    ADD CONSTRAINT pktermine_todolist PRIMARY KEY (termine_todolist_id);


--
-- TOC entry 2129 (class 2606 OID 36525)
-- Dependencies: 1481 1481
-- Name: pktermine_user; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termine_user
    ADD CONSTRAINT pktermine_user PRIMARY KEY (termine_user_id);


--
-- TOC entry 2131 (class 2606 OID 36542)
-- Dependencies: 1483 1483
-- Name: pkterminegroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY terminegroups
    ADD CONSTRAINT pkterminegroups PRIMARY KEY (terminegroup_id);


--
-- TOC entry 2133 (class 2606 OID 36555)
-- Dependencies: 1485 1485
-- Name: pkterminestatus; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY terminestatus
    ADD CONSTRAINT pkterminestatus PRIMARY KEY (status_id);


--
-- TOC entry 2135 (class 2606 OID 36564)
-- Dependencies: 1487 1487
-- Name: pktitles; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY titles
    ADD CONSTRAINT pktitles PRIMARY KEY (title_id);


--
-- TOC entry 2137 (class 2606 OID 36576)
-- Dependencies: 1489 1489
-- Name: pktransstatus; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transstatus
    ADD CONSTRAINT pktransstatus PRIMARY KEY (status_id);


--
-- TOC entry 2139 (class 2606 OID 36589)
-- Dependencies: 1491 1491
-- Name: pkuserdata; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userdata
    ADD CONSTRAINT pkuserdata PRIMARY KEY (data_id);


--
-- TOC entry 2141 (class 2606 OID 36601)
-- Dependencies: 1493 1493
-- Name: pkusergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usergroups
    ADD CONSTRAINT pkusergroups PRIMARY KEY (usergroup_id);


--
-- TOC entry 2143 (class 2606 OID 36614)
-- Dependencies: 1495 1495
-- Name: pkuserlevel; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userlevel
    ADD CONSTRAINT pkuserlevel PRIMARY KEY (level_id);


--
-- TOC entry 2145 (class 2606 OID 36631)
-- Dependencies: 1497 1497
-- Name: pkusers; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pkusers PRIMARY KEY (user_id);


--
-- TOC entry 2147 (class 2606 OID 36645)
-- Dependencies: 1499 1499
-- Name: pkusers_usergroups; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users_usergroups
    ADD CONSTRAINT pkusers_usergroups PRIMARY KEY (users_usergroup_id);


--
-- TOC entry 2149 (class 2606 OID 36663)
-- Dependencies: 1501 1501
-- Name: pkuserwaren; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT pkuserwaren PRIMARY KEY (userwaren_id);


--
-- TOC entry 2151 (class 2606 OID 36676)
-- Dependencies: 1503 1503
-- Name: pkzahlungsarten; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY zahlungsarten
    ADD CONSTRAINT pkzahlungsarten PRIMARY KEY (zahlungs_id);


--
-- TOC entry 2048 (class 1259 OID 36101)
-- Dependencies: 1406
-- Name: articlegroups_articlegroup_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX articlegroups_articlegroup_id_idx ON articlegroups USING btree (articlegroup_id);


--
-- TOC entry 2051 (class 1259 OID 36114)
-- Dependencies: 1408
-- Name: articles_article_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX articles_article_id_idx ON articles USING btree (article_id);


--
-- TOC entry 2054 (class 1259 OID 36124)
-- Dependencies: 1410
-- Name: articles_articlegroups_articles_articlesgroups_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX articles_articlegroups_articles_articlesgroups_id_idx ON articles_articlegroups USING btree (articles_articlesgroups_id);


--
-- TOC entry 2059 (class 1259 OID 36147)
-- Dependencies: 1414
-- Name: configuration_configuration_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX configuration_configuration_id_idx ON configuration USING btree (configuration_id);


--
-- TOC entry 2154 (class 2606 OID 36682)
-- Dependencies: 2042 1402 1400
-- Name: fk_adresses_emails_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_emails
    ADD CONSTRAINT fk_adresses_emails_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2153 (class 2606 OID 36687)
-- Dependencies: 2074 1427 1402
-- Name: fk_adresses_emails_emails; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_emails
    ADD CONSTRAINT fk_adresses_emails_emails FOREIGN KEY (mail_id) REFERENCES emails(mail_id);


--
-- TOC entry 2156 (class 2606 OID 36692)
-- Dependencies: 2042 1400 1404
-- Name: fk_adresses_phones_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_phones
    ADD CONSTRAINT fk_adresses_phones_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2155 (class 2606 OID 36697)
-- Dependencies: 1404 2108 1461
-- Name: fk_adresses_phones_phones; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses_phones
    ADD CONSTRAINT fk_adresses_phones_phones FOREIGN KEY (phone_id) REFERENCES phones(phone_id);


--
-- TOC entry 2152 (class 2606 OID 36677)
-- Dependencies: 2112 1465 1400
-- Name: fk_adresses_states; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adresses
    ADD CONSTRAINT fk_adresses_states FOREIGN KEY (state_id) REFERENCES states(state_id);


--
-- TOC entry 2159 (class 2606 OID 36707)
-- Dependencies: 1406 1410 2049
-- Name: fk_articles_articlegroups_articlegroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_articlegroups
    ADD CONSTRAINT fk_articles_articlegroups_articlegroups FOREIGN KEY (articlegroup_id) REFERENCES articlegroups(articlegroup_id);


--
-- TOC entry 2158 (class 2606 OID 36712)
-- Dependencies: 2052 1410 1408
-- Name: fk_articles_articlegroups_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_articlegroups
    ADD CONSTRAINT fk_articles_articlegroups_articles FOREIGN KEY (article_id) REFERENCES articles(article_id);


--
-- TOC entry 2161 (class 2606 OID 36717)
-- Dependencies: 2052 1412 1408
-- Name: fk_articles_lieferarten_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_lieferarten
    ADD CONSTRAINT fk_articles_lieferarten_articles FOREIGN KEY (articles_id) REFERENCES articles(article_id);


--
-- TOC entry 2160 (class 2606 OID 36722)
-- Dependencies: 2096 1412 1449
-- Name: fk_articles_lieferarten_lieferarten; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles_lieferarten
    ADD CONSTRAINT fk_articles_lieferarten_lieferarten FOREIGN KEY (liefer_id) REFERENCES lieferarten(liefer_id);


--
-- TOC entry 2157 (class 2606 OID 36702)
-- Dependencies: 1469 1408 2116
-- Name: fk_articles_suppliers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT fk_articles_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id);


--
-- TOC entry 2162 (class 2606 OID 36727)
-- Dependencies: 1414 1497 2144
-- Name: fk_configuration_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuration
    ADD CONSTRAINT fk_configuration_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2163 (class 2606 OID 36732)
-- Dependencies: 2062 1416 1417
-- Name: fk_contactgroups_contactfreigabe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups
    ADD CONSTRAINT fk_contactgroups_contactfreigabe FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe(freigabe_id);


--
-- TOC entry 2165 (class 2606 OID 36737)
-- Dependencies: 1417 1419 2064
-- Name: fk_contactgroups_contacts_contactgroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_contacts
    ADD CONSTRAINT fk_contactgroups_contacts_contactgroups FOREIGN KEY (contactgroup_id) REFERENCES contactgroups(contactgroup_id);


--
-- TOC entry 2164 (class 2606 OID 36742)
-- Dependencies: 2070 1419 1423
-- Name: fk_contactgroups_contacts_contacts; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_contacts
    ADD CONSTRAINT fk_contactgroups_contacts_contacts FOREIGN KEY (contact_id) REFERENCES contacts(contact_id);


--
-- TOC entry 2167 (class 2606 OID 36747)
-- Dependencies: 2064 1417 1421
-- Name: fk_contactgroups_conuser_contactgroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_conuser
    ADD CONSTRAINT fk_contactgroups_conuser_contactgroups FOREIGN KEY (contactgroup_id) REFERENCES contactgroups(contactgroup_id);


--
-- TOC entry 2166 (class 2606 OID 36752)
-- Dependencies: 1421 1425 2072
-- Name: fk_contactgroups_conuser_conuser; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contactgroups_conuser
    ADD CONSTRAINT fk_contactgroups_conuser_conuser FOREIGN KEY (conuser_id) REFERENCES conuser(conuser_id);


--
-- TOC entry 2171 (class 2606 OID 36757)
-- Dependencies: 1423 1400 2042
-- Name: fk_contacts_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2170 (class 2606 OID 36762)
-- Dependencies: 1423 1416 2062
-- Name: fk_contacts_contactfreigabe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_contactfreigabe FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe(freigabe_id);


--
-- TOC entry 2169 (class 2606 OID 36767)
-- Dependencies: 2134 1487 1423
-- Name: fk_contacts_titles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_titles FOREIGN KEY (titel_id) REFERENCES titles(title_id);


--
-- TOC entry 2168 (class 2606 OID 36772)
-- Dependencies: 2144 1423 1497
-- Name: fk_contacts_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT fk_contacts_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2173 (class 2606 OID 36777)
-- Dependencies: 1416 1425 2062
-- Name: fk_conuser_contactfreigabe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conuser
    ADD CONSTRAINT fk_conuser_contactfreigabe FOREIGN KEY (freigabe_id) REFERENCES contactfreigabe(freigabe_id);


--
-- TOC entry 2172 (class 2606 OID 36782)
-- Dependencies: 1425 2144 1497
-- Name: fk_conuser_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conuser
    ADD CONSTRAINT fk_conuser_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2176 (class 2606 OID 36792)
-- Dependencies: 1406 2049 1433
-- Name: fk_employees_articlegroups_articlegroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articlegroups
    ADD CONSTRAINT fk_employees_articlegroups_articlegroups FOREIGN KEY (articlegroup_id) REFERENCES articlegroups(articlegroup_id);


--
-- TOC entry 2175 (class 2606 OID 36797)
-- Dependencies: 2078 1433 1431
-- Name: fk_employees_articlegroups_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articlegroups
    ADD CONSTRAINT fk_employees_articlegroups_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2178 (class 2606 OID 36802)
-- Dependencies: 2052 1435 1408
-- Name: fk_employees_articles_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articles
    ADD CONSTRAINT fk_employees_articles_articles FOREIGN KEY (article_id) REFERENCES articles(article_id);


--
-- TOC entry 2177 (class 2606 OID 36807)
-- Dependencies: 2078 1435 1431
-- Name: fk_employees_articles_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_articles
    ADD CONSTRAINT fk_employees_articles_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2180 (class 2606 OID 36812)
-- Dependencies: 1437 2076 1429
-- Name: fk_employees_employeegroups_employeegroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_employeegroups
    ADD CONSTRAINT fk_employees_employeegroups_employeegroups FOREIGN KEY (employeegroup_id) REFERENCES employeegroups(employeegroup_id);


--
-- TOC entry 2179 (class 2606 OID 36817)
-- Dependencies: 1431 1437 2078
-- Name: fk_employees_employeegroups_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_employeegroups
    ADD CONSTRAINT fk_employees_employeegroups_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2182 (class 2606 OID 36822)
-- Dependencies: 1439 1431 2078
-- Name: fk_employees_suppliergroups_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliergroups
    ADD CONSTRAINT fk_employees_suppliergroups_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2181 (class 2606 OID 36827)
-- Dependencies: 2114 1467 1439
-- Name: fk_employees_suppliergroups_suppliergroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliergroups
    ADD CONSTRAINT fk_employees_suppliergroups_suppliergroups FOREIGN KEY (employees_suppliergroups_id) REFERENCES suppliergroups(suppliergroup_id);


--
-- TOC entry 2184 (class 2606 OID 36832)
-- Dependencies: 1441 2078 1431
-- Name: fk_employees_suppliers_employees; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliers
    ADD CONSTRAINT fk_employees_suppliers_employees FOREIGN KEY (employee_id) REFERENCES employees(employee_id);


--
-- TOC entry 2183 (class 2606 OID 36837)
-- Dependencies: 1441 1469 2116
-- Name: fk_employees_suppliers_suppliers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees_suppliers
    ADD CONSTRAINT fk_employees_suppliers_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id);


--
-- TOC entry 2174 (class 2606 OID 36787)
-- Dependencies: 2144 1431 1497
-- Name: fk_employees_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employees
    ADD CONSTRAINT fk_employees_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2186 (class 2606 OID 36842)
-- Dependencies: 2090 1443 1445
-- Name: fk_fieldvalues_fieldlanguagesvalues_fieldlanguage; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fieldlanguagesvalues
    ADD CONSTRAINT fk_fieldvalues_fieldlanguagesvalues_fieldlanguage FOREIGN KEY (language_id) REFERENCES fieldlanguage(language_id);


--
-- TOC entry 2185 (class 2606 OID 36847)
-- Dependencies: 1445 2094 1447
-- Name: fk_fieldvalues_fieldlanguagesvalues_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fieldlanguagesvalues
    ADD CONSTRAINT fk_fieldvalues_fieldlanguagesvalues_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2188 (class 2606 OID 36852)
-- Dependencies: 1447 2094 1451
-- Name: fk_naviglobal_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY naviglobal
    ADD CONSTRAINT fk_naviglobal_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2187 (class 2606 OID 36857)
-- Dependencies: 1495 2142 1451
-- Name: fk_naviglobal_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY naviglobal
    ADD CONSTRAINT fk_naviglobal_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2191 (class 2606 OID 36862)
-- Dependencies: 1453 2094 1447
-- Name: fk_navimain_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT fk_navimain_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2190 (class 2606 OID 36867)
-- Dependencies: 1451 2098 1453
-- Name: fk_navimain_naviglobal; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT fk_navimain_naviglobal FOREIGN KEY (global_id) REFERENCES naviglobal(global_id);


--
-- TOC entry 2189 (class 2606 OID 36872)
-- Dependencies: 1453 2142 1495
-- Name: fk_navimain_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navimain
    ADD CONSTRAINT fk_navimain_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2194 (class 2606 OID 36877)
-- Dependencies: 1447 2094 1455
-- Name: fk_navisub_fieldvalues; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT fk_navisub_fieldvalues FOREIGN KEY (fieldvalues_id) REFERENCES fieldvalues(fieldvalues_id);


--
-- TOC entry 2193 (class 2606 OID 36882)
-- Dependencies: 1455 2100 1453
-- Name: fk_navisub_navimain; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT fk_navisub_navimain FOREIGN KEY (main_id) REFERENCES navimain(main_id);


--
-- TOC entry 2192 (class 2606 OID 36887)
-- Dependencies: 1495 2142 1455
-- Name: fk_navisub_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY navisub
    ADD CONSTRAINT fk_navisub_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2196 (class 2606 OID 36892)
-- Dependencies: 1457 1459 2104
-- Name: fk_organisation_users_organisation; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisation_users
    ADD CONSTRAINT fk_organisation_users_organisation FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id);


--
-- TOC entry 2195 (class 2606 OID 36897)
-- Dependencies: 1497 2144 1459
-- Name: fk_organisation_users_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisation_users
    ADD CONSTRAINT fk_organisation_users_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2197 (class 2606 OID 36902)
-- Dependencies: 2042 1400 1469
-- Name: fk_suppliers_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT fk_suppliers_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2199 (class 2606 OID 36907)
-- Dependencies: 1471 2114 1467
-- Name: fk_suppliers_suppliergroups_suppliergroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY suppliers_suppliergroups
    ADD CONSTRAINT fk_suppliers_suppliergroups_suppliergroups FOREIGN KEY (suppliergroup_id) REFERENCES suppliergroups(suppliergroup_id);


--
-- TOC entry 2198 (class 2606 OID 36912)
-- Dependencies: 1471 1469 2116
-- Name: fk_suppliers_suppliergroups_suppliers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY suppliers_suppliergroups
    ADD CONSTRAINT fk_suppliers_suppliergroups_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id);


--
-- TOC entry 2203 (class 2606 OID 36927)
-- Dependencies: 1475 2120 1473
-- Name: fk_termine_participant_termine; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_participant
    ADD CONSTRAINT fk_termine_participant_termine FOREIGN KEY (termin_id) REFERENCES termine(termin_id);


--
-- TOC entry 2202 (class 2606 OID 36932)
-- Dependencies: 1497 1475 2144
-- Name: fk_termine_participant_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_participant
    ADD CONSTRAINT fk_termine_participant_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2201 (class 2606 OID 36917)
-- Dependencies: 1485 2132 1473
-- Name: fk_termine_terminestatus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine
    ADD CONSTRAINT fk_termine_terminestatus FOREIGN KEY (status_id) REFERENCES terminestatus(status_id);


--
-- TOC entry 2206 (class 2606 OID 36937)
-- Dependencies: 1497 2144 1477
-- Name: fk_termine_todo_user_owner_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT fk_termine_todo_user_owner_users FOREIGN KEY (owner_id) REFERENCES users(user_id);


--
-- TOC entry 2205 (class 2606 OID 36942)
-- Dependencies: 2126 1479 1477
-- Name: fk_termine_todo_user_termine_todolist; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT fk_termine_todo_user_termine_todolist FOREIGN KEY (todo_id) REFERENCES termine_todolist(termine_todolist_id);


--
-- TOC entry 2204 (class 2606 OID 36947)
-- Dependencies: 1477 2144 1497
-- Name: fk_termine_todo_user_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_todo_user
    ADD CONSTRAINT fk_termine_todo_user_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2208 (class 2606 OID 36952)
-- Dependencies: 1473 2120 1481
-- Name: fk_termine_user_termine; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_user
    ADD CONSTRAINT fk_termine_user_termine FOREIGN KEY (termin_id) REFERENCES termine(termin_id);


--
-- TOC entry 2207 (class 2606 OID 36957)
-- Dependencies: 1481 2144 1497
-- Name: fk_termine_user_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine_user
    ADD CONSTRAINT fk_termine_user_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2200 (class 2606 OID 36922)
-- Dependencies: 2144 1497 1473
-- Name: fk_termine_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termine
    ADD CONSTRAINT fk_termine_users FOREIGN KEY (owner_id) REFERENCES users(user_id);


--
-- TOC entry 2210 (class 2606 OID 36962)
-- Dependencies: 1483 1473 2120
-- Name: fk_terminegroups_termine; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY terminegroups
    ADD CONSTRAINT fk_terminegroups_termine FOREIGN KEY (termin_id) REFERENCES termine(termin_id);


--
-- TOC entry 2209 (class 2606 OID 36967)
-- Dependencies: 1483 2132 1485
-- Name: fk_terminegroups_terminestatus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY terminegroups
    ADD CONSTRAINT fk_terminegroups_terminestatus FOREIGN KEY (status_id) REFERENCES terminestatus(status_id);


--
-- TOC entry 2211 (class 2606 OID 36972)
-- Dependencies: 1497 2144 1491
-- Name: fk_userdata_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userdata
    ADD CONSTRAINT fk_userdata_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2212 (class 2606 OID 36977)
-- Dependencies: 2142 1493 1495
-- Name: fk_usergroups_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usergroups
    ADD CONSTRAINT fk_usergroups_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2220 (class 2606 OID 37012)
-- Dependencies: 1499 1497 2144
-- Name: fk_usergroups_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_usergroups
    ADD CONSTRAINT fk_usergroups_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2218 (class 2606 OID 36982)
-- Dependencies: 1400 1497 2042
-- Name: fk_users_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_adresses FOREIGN KEY (adresses_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2217 (class 2606 OID 36987)
-- Dependencies: 1400 2042 1497
-- Name: fk_users_delivery_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_delivery_adresses FOREIGN KEY (delivery_adress_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2216 (class 2606 OID 36992)
-- Dependencies: 1400 2042 1497
-- Name: fk_users_domicile_adresses; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_domicile_adresses FOREIGN KEY (domicile_adress_id) REFERENCES adresses(adresses_id);


--
-- TOC entry 2215 (class 2606 OID 36997)
-- Dependencies: 1497 2090 1443
-- Name: fk_users_fieldlanguage; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_fieldlanguage FOREIGN KEY (language_id) REFERENCES fieldlanguage(language_id);


--
-- TOC entry 2214 (class 2606 OID 37002)
-- Dependencies: 1487 2134 1497
-- Name: fk_users_titles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_titles FOREIGN KEY (title_id) REFERENCES titles(title_id);


--
-- TOC entry 2219 (class 2606 OID 37017)
-- Dependencies: 1499 2140 1493
-- Name: fk_users_usergroups_usergroups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_usergroups
    ADD CONSTRAINT fk_users_usergroups_usergroups FOREIGN KEY (usergroup_id) REFERENCES usergroups(usergroup_id);


--
-- TOC entry 2213 (class 2606 OID 37007)
-- Dependencies: 1497 2142 1495
-- Name: fk_users_userlevel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_userlevel FOREIGN KEY (level_id) REFERENCES userlevel(level_id);


--
-- TOC entry 2225 (class 2606 OID 37022)
-- Dependencies: 1408 2052 1501
-- Name: fk_userwaren_articles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_articles FOREIGN KEY (article_id) REFERENCES articles(article_id);


--
-- TOC entry 2224 (class 2606 OID 37027)
-- Dependencies: 2096 1501 1449
-- Name: fk_userwaren_lieferarten; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_lieferarten FOREIGN KEY (liefer_id) REFERENCES lieferarten(liefer_id);


--
-- TOC entry 2223 (class 2606 OID 37032)
-- Dependencies: 2136 1501 1489
-- Name: fk_userwaren_transstatus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_transstatus FOREIGN KEY (status_id) REFERENCES transstatus(status_id);


--
-- TOC entry 2222 (class 2606 OID 37037)
-- Dependencies: 2144 1497 1501
-- Name: fk_userwaren_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_users FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2221 (class 2606 OID 37042)
-- Dependencies: 1501 1503 2150
-- Name: fk_userwaren_zahlungsarten; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userwaren
    ADD CONSTRAINT fk_userwaren_zahlungsarten FOREIGN KEY (zahlungs_id) REFERENCES zahlungsarten(zahlungs_id);


-- Completed on 2007-03-28 17:31:44

--
-- PostgreSQL database dump complete
--

