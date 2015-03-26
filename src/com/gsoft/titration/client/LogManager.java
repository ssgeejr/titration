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

import java.util.logging.*;
import java.io.*;

public class LogManager {
  private final String NEW_LINE = System.getProperties().getProperty("line.separator");
  private static LogManager singleton;
  private Logger logger = null;
  private LogManager(){
    try {
      File file = new File("./log");
      if(!file.exists()){
        file.mkdir();
      }
      Handler fh = new FileHandler("./log/titration.client.log");
      logger = Logger.getLogger("titration");
      logger.addHandler(fh);
    }catch (Exception ex) {
      ex.printStackTrace();
    }
}

  public static LogManager getInstance(){
    if(singleton == null)
      singleton = new LogManager();
    return singleton;
  }


  public void log(Throwable tex){
    log("no user comment provided",tex);
  }

  public void log(String comment, Throwable tex){
    try {
      logger.log(Level.INFO, comment, tex);
    }catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void log(String comment){
    try {
      logger.log(Level.INFO, comment);
    }catch (Exception ex) {
      ex.printStackTrace();
    }
  }


}