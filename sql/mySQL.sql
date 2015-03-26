mysql> create database cerberus;
Query OK, 1 row affected (0.06 sec)

mysql> use cerberus
Database changed
mysql> grant all privileges on cerberus.* to cerberus@localhost identified by 'micromgr';
Query OK, 0 rows affected (0.24 sec)

mysql> grant all privileges on cerberus.* to cerberus@ren identified by 'micromgr';
Query OK, 0 rows affected (0.00 sec)

mysql> grant all privileges on cerberus.* to cerberus@tao identified by 'micromgr';
Query OK, 0 rows affected (0.00 sec)

mysql> grant all privileges on cerberus.* to cerberus@sgee identified by 'micromgr';
Query OK, 0 rows affected (0.00 sec)