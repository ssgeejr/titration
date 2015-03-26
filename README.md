# Titration 

http://titration.sourceforge.net

Titration is a simple, multi-user, multi-project, project tracking software. Simplicistic in design, easy to use, setup and maintain. Uses any backend database that supports a JDBC driver or can run standalone. Java based and platform independant

Allow me to state that if you have installed or compiled version 1.3.2
you are going to be in for a big surprise.  I left a line of 
debugging code uncommented and all users log in as 'ioexcept'.
Although this seldom happens, I'm only human and make sill-big mistakes
like everyone else.

Steve

If you are using version 1.3.x you will need to run this command on your
database: 
ALTER TABLE USERPROJECTS ADD (HIDDEN   INTEGER  DEFAULT 0);

If you are going with a new intall, the database will be built with this
feature already enabled.

New Feature
***BUG FIX***
**User items (rows) can now be hidden for ease of use for users with a large
number of projects that are completed, paused or on-hold.


---------------- DEFAULT NEW INSTALL INFORMATION ----------------------
New Install:
This program is developed under the rule of -keep it simple-
All of the SQL statements are external of the application
so that different TO_DATE/DATETIME/DATE_FORMAT commands can
be massaged and used on any database.  It would be nice if 
we could get a standard, accepted, DATE manipulation command.

PRIOR to using. A database will need to be established. Once
that is done, open ./conf/config.xml and put in the goodies
for the <database> xml section.
Here are a few examples for different databases:
MySql:
          <driver>com.mysql.jdbc.Driver</driver>
          <url>jdbc:mysql://DBServer/titrationDB</url>
Oracle:
          <driver>oracle.jdbc.OracleDriver</driver>
          <url>jdbc:oracle:thin:@DBServer:1521:titrationDB</url>
HSQLDB:
          <driver>org.hsql.jdbcDriver</driver>
          <url>jdbc:HypersonicSQL:hsql://DBServer</url>
SQLServer:
          <driver>com.microsoft.jdbc.sqlserver.SQLServerDriver</driver>
          <url>jdbc:microsoft:sqlserver://DBServer:1433;DatabaseName=titrationDB</url>
          
There are two ways for Titration to find the appropriate library (jar file).
	1) Put it in the classpath prior to running
	2) Simply drop it in the $TITRATION_HOME/lib folder. The application
	   has a built in classloader that will find any .jar or .zip files
	
******************** IMPORTANT ********************
Once you have this done run the command: java -jar titration.jar -initdb
This will build your database.  If you're going to be using any high-
performance database you can go into ./conf/initdb.sql and change all
of your VARCHAR to VARCHAR2.  
***************************************************

**Please take note MYSQL is CASE SENSITIVE so if you change some of the tables
make sure you update them in ./conf/config.xml. 

Once you have this done you can simply double click the jar or run java -jar titration.jar

Building the project:
I have written the code so it's compatible with 1.4.2 or greater JDK (it -might- run on 
any platform).  The JBuilder project is included in the source release as well as a
standard ant script.  If your running windows make sure you use ant -f windows.build.xml
for build.xml is a sysV (Linux) directory structure release.
The ant file is designed to base all paths of the ROOT.DIR property. Simply change that
and you will be good to go.
