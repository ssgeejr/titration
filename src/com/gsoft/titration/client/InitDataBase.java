package com.gsoft.titration.client;
/*************************************************************************
     Copyright (C) 2005  Steve Gee
     ioexcept@gmail.com
     This program is free software; you can redistribute it and/or
     modify it under the terms of the GNU General Public License
     as published by the Free Software Foundation; either version 2
     of the License, or (at your option) any later version.
     This program is distributed in the hope that it will be useful,
     but WITHOUT ANY WARRANTY; without even the implied warranty of
     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     GNU General Public License for more details.
     You should have received a copy of the GNU General Public License
     along with this program; if not, write to the Free Software
     Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*************************************************************************/

import java.util.*;
import java.sql.*;
import com.gsoft.titration.sql.*;
import java.io.*;

public class InitDataBase {
  private TagReader tReader = null;
  private ConnectionManager conMgr;
  private Connection conn = null;
  private Statement stmt = null;
  public InitDataBase() throws Exception {
    try {
      tReader = new TagReader("./conf/config.xml");
      conMgr = ConnectionManager.getInstance();
      conn = conMgr.getConnection(tReader.getTagValue("database", "driver"),
                                  tReader.getTagValue("database", "url"),
                                  tReader.getTagValue("database", "username"),
                                  tReader.getTagValue("database", "password"));
      stmt = conn.createStatement();
      initDB();
    } finally {
      conMgr.closeConnection();
    }
  }

  private void initDB() throws Exception {
    BufferedReader reader = null;
    try{
      reader = new BufferedReader(new FileReader("./conf/initdb.sql"));
      StringBuffer sql = new StringBuffer();
      String input = null;
      while( (input = reader.readLine()) != null){
        if(input.startsWith("--") || input.trim().length() == 0)
           continue;

        if(input.indexOf(";") > -1){
          sql.append(input);
          System.out.println("Executing: " + sql.toString());
          stmt.execute(sql.toString());
          sql.setLength(0);
        }else{
          sql.append(input);
        }
      }
    }finally{
      try{ reader.close();}catch(Exception ex){}
    }
  }

}