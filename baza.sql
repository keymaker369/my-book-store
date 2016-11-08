--
-- Create Schema Script 
--   Database Version   : 10.2.0.1.0 
--   TOAD Version       : 9.7.2.5 
--   DB Connect String  : XE 
--   Schema             : PROJEKTOVANJESOFTVERA 
--   Script Created by  : PROJEKTOVANJESOFTVERA 
--   Script Created at  : 6/28/2010 2:19:41 AM 
--   Physical Location  :  
--   Notes              :  
--

-- Object Counts: 
--   Indexes: 5         Columns: 7          
--   Sequences: 1 
--   Tables: 5          Columns: 24         Constraints: 9      

-- create user mybookstore identified by mybookstore;

-- GRANT CONNECT,RESOURCE,DBA TO mybookstore;

-- GRANT CREATE SESSION TO mybookstore;

-- GRANT ANY PRIVILEGE TO mybookstore;

-- GRANT UNLIMITED TABLESPACE TO mybookstore;

-- GRANT SELECT,UPDATE,INSERT ON <TABLE NAME> TO <USER NAME>;


CREATE SEQUENCE SIFRAPROIZVODA_SEKVENCA
  START WITH 45
  MAXVALUE 9999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;

CREATE SEQUENCE SIFRANARUDZBENICE_SEKVENCA
  START WITH 45
  MAXVALUE 9999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;

CREATE SEQUENCE SIFRARACUNA_SEKVENCA
  START WITH 45
  MAXVALUE 9999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;
    
CREATE TABLE PROIZVOD
(
  SIFRAPROIZVODA  NUMBER                        NOT NULL,
  NAZIV           VARCHAR2(20 BYTE),
  DOBAVLJAC       VARCHAR2(20 BYTE),
  OPIS            VARCHAR2(50 BYTE),
  KOLICINA        NUMBER,
  CENA            NUMBER
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE RACUN
(
  SIFRA           NUMBER,
  DATUM           VARCHAR2(20 BYTE),
  UKUPNAVREDNOST  NUMBER,
  OBRADJEN        VARCHAR2(20 BYTE),
  NAZIVPARTNERA   VARCHAR2(20 BYTE)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE STAVKARACUNA
(
  SIFRA           NUMBER                        NOT NULL,
  REDNIBROJ       NUMBER                        NOT NULL,
  SIFRAPROIZVODA  NUMBER,
  KOLICINA        NUMBER
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE NARUDZBENICA
(
  SIFRANARUDZBENICE     NUMBER,
  SIFRANARUDZBENICEKUP  VARCHAR2(20 BYTE),
  DATUM                 VARCHAR2(20 BYTE),
  UKUPNAVREDNOST        NUMBER,
  KUPAC                 VARCHAR2(20 BYTE)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE STAVKANARUDZBENICE
(
  SIFRANARUDZBENICE  NUMBER,
  REDNIBROJ          NUMBER,
  SIFRAPROIZVODA     NUMBER,
  KOLICINA           NUMBER
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX STAVKARACUNA_PK ON STAVKARACUNA
(SIFRA, REDNIBROJ)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX RACUN_PK ON RACUN
(SIFRA)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX STAVKANAR_PK ON STAVKANARUDZBENICE
(SIFRANARUDZBENICE, REDNIBROJ)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX SIFRANARUDZBENICE ON NARUDZBENICA
(SIFRANARUDZBENICE)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX SIFRAPROIZVODA ON PROIZVOD
(SIFRAPROIZVODA)
LOGGING
NOPARALLEL;


ALTER TABLE PROIZVOD ADD (
  CONSTRAINT SIFRAPROIZVODA
 PRIMARY KEY
 (SIFRAPROIZVODA));

ALTER TABLE RACUN ADD (
  CONSTRAINT RACUN_PK
 PRIMARY KEY
 (SIFRA));

ALTER TABLE STAVKARACUNA ADD (
  CONSTRAINT STAVKARACUNA_PK
 PRIMARY KEY
 (SIFRA, REDNIBROJ));

ALTER TABLE NARUDZBENICA ADD (
  CONSTRAINT SIFRANARUDZBENICE
 PRIMARY KEY
 (SIFRANARUDZBENICE));

ALTER TABLE STAVKANARUDZBENICE ADD (
  CONSTRAINT STAVKANAR_PK
 PRIMARY KEY
 (SIFRANARUDZBENICE, REDNIBROJ));

ALTER TABLE STAVKARACUNA ADD (
  CONSTRAINT STAVKARACUNA_FK 
 FOREIGN KEY (SIFRA) 
 REFERENCES RACUN (SIFRA),
  CONSTRAINT STAVKARACUNA_FK2 
 FOREIGN KEY (SIFRAPROIZVODA) 
 REFERENCES PROIZVOD (SIFRAPROIZVODA));

ALTER TABLE STAVKANARUDZBENICE ADD (
  CONSTRAINT STAVKANAR_FK 
 FOREIGN KEY (SIFRANARUDZBENICE) 
 REFERENCES NARUDZBENICA (SIFRANARUDZBENICE),
  CONSTRAINT STAVKANAR_FK2 
 FOREIGN KEY (SIFRAPROIZVODA) 
 REFERENCES PROIZVOD (SIFRAPROIZVODA));

