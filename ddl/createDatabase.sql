
CREATE TABLE DATASOURCES (
       PROJECTID            INTEGER NOT NULL,
       DATASOURCEID         INTEGER NOT NULL
);


ALTER TABLE DATASOURCES
       ADD  ( PRIMARY KEY (PROJECTID, DATASOURCEID) ) ;


CREATE TABLE DATASTORES (
       DATASOURCEID         INTEGER NOT NULL,
       DATABASENAME         VARCHAR2(32) NOT NULL,
       SERVERNAME           VARCHAR2(32) NOT NULL,
       SERVERDNSIP          VARCHAR2(15) NOT NULL
);


ALTER TABLE DATASTORES
       ADD  ( PRIMARY KEY (DATASOURCEID) ) ;

/* Empty table will cause syntax error. */
/*
CREATE TABLE E_5 (
);*/
CREATE TABLE NEXTNUM (
       TABLENAME            VARCHAR2(24) NOT NULL,
       NEXTNUM              INTEGER NULL
);


ALTER TABLE NEXTNUM
       ADD  ( PRIMARY KEY (TABLENAME) ) ;


CREATE TABLE PROJECTLOC (
       PROJECTID            INTEGER NULL,
       PROJECTLOCID         INTEGER NOT NULL,
       CVSNAME              VARCHAR2(32) NOT NULL,
       DEVSERVER            VARCHAR2(32) NOT NULL,
       DEVDNSIP             VARCHAR2(15) NOT NULL,
       QASERVER             VARCHAR2(20) NULL,
       QADNSIP              VARCHAR2(20) NULL,
       PRODDNSIP            VARCHAR2(15) NULL,
       PRODSERVER           VARCHAR2(32) NULL
);


ALTER TABLE PROJECTLOC
       ADD  ( PRIMARY KEY (PROJECTLOCID) ) ;


CREATE TABLE PROJECTNOTES (
       NOTEID               INTEGER NOT NULL,
       COMMENT              VARCHAR2(255) NULL,
       COMMENTDATE          DATE NULL,
       PROJECTID            INTEGER NULL,
       USERID               INTEGER NULL
);


ALTER TABLE PROJECTNOTES
       ADD  ( PRIMARY KEY (NOTEID) ) ;


CREATE TABLE PROJECTS (
       PROJECTID            INTEGER NOT NULL,
       PROJECTNAME          VARCHAR2(32) NULL,
       DESCRIPTION          VARCHAR2(255) NULL,
       REQUESTOR            VARCHAR2(32) NULL,
       REQUESTDATE          DATE NULL,
       STARTDATE            DATE NULL,
       COMPLETIONDATE       DATE NULL,
       STATUSID             INTEGER NULL,
       HOURS                INTEGER NULL,
       PRIORITY             INTEGER NULL
);


ALTER TABLE PROJECTS
       ADD  ( PRIMARY KEY (PROJECTID) ) ;


CREATE TABLE PROJECTSTATUS (
       STATUSID             INTEGER NOT NULL,
       STATUS               VARCHAR2(15) NULL
);


ALTER TABLE PROJECTSTATUS
       ADD  ( PRIMARY KEY (STATUSID) ) ;


CREATE TABLE PROJECTTECH (
       PROJECTID            INTEGER NOT NULL,
       TECHNOLOGYID         INTEGER NOT NULL
);


ALTER TABLE PROJECTTECH
       ADD  ( PRIMARY KEY (PROJECTID, TECHNOLOGYID) ) ;


CREATE TABLE TECHNOLOGIES (
       TECHNOLOGYID         INTEGER NOT NULL,
       TECHNAME             VARCHAR2(32) NULL,
       DESCRIPTION          VARCHAR2(128) NULL,
       VERSION              VARCHAR2(20) NULL
);


ALTER TABLE TECHNOLOGIES
       ADD  ( PRIMARY KEY (TECHNOLOGYID) ) ;


CREATE TABLE USERPROJECTS (
       PROJECTORDER         INTEGER DEFAULT 0 NULL,
       PROJECTID            INTEGER NULL,
       USERID               INTEGER NULL,
       COMPLETEDPCT         INTEGER NULL,
       PROJECTHOURS         INTEGER DEFAULT 0 NOT NULL
);


CREATE TABLE USERS (
       USERID               INTEGER NOT NULL,
       USERNAME             VARCHAR2(12) NULL,
       PASSWORD             VARCHAR2(12) NULL,
       ACTUALNAME           VARCHAR2(32) NULL,
       PREFEREDNAME         VARCHAR2(32) NULL
);


ALTER TABLE USERS
       ADD  ( PRIMARY KEY (USERID) ) ;


ALTER TABLE DATASOURCES
       ADD  ( FOREIGN KEY (DATASOURCEID)
                             REFERENCES DATASTORES ) ;


ALTER TABLE DATASOURCES
       ADD  ( FOREIGN KEY (PROJECTID)
                             REFERENCES PROJECTS ) ;


ALTER TABLE PROJECTLOC
       ADD  ( FOREIGN KEY (PROJECTID)
                             REFERENCES PROJECTS ) ;


ALTER TABLE PROJECTNOTES
       ADD  ( FOREIGN KEY (USERID)
                             REFERENCES USERS ) ;


ALTER TABLE PROJECTNOTES
       ADD  ( FOREIGN KEY (PROJECTID)
                             REFERENCES PROJECTS ) ;


ALTER TABLE PROJECTS
       ADD  ( FOREIGN KEY (STATUSID)
                             REFERENCES PROJECTSTATUS ) ;


ALTER TABLE PROJECTTECH
       ADD  ( FOREIGN KEY (TECHNOLOGYID)
                             REFERENCES TECHNOLOGIES ) ;


ALTER TABLE PROJECTTECH
       ADD  ( FOREIGN KEY (PROJECTID)
                             REFERENCES PROJECTS ) ;


ALTER TABLE USERPROJECTS
       ADD  ( FOREIGN KEY (USERID)
                             REFERENCES USERS ) ;


ALTER TABLE USERPROJECTS
       ADD  ( FOREIGN KEY (PROJECTID)
                             REFERENCES PROJECTS ) ;


