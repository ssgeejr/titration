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

import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;


public class Titration {
  private String UI = "javax.swing.plaf.metal.MetalLookAndFeel";
  private int selid = 0;
  private LogManager lman = LogManager.getInstance();
  public Titration(int id){
    try {
      lman = LogManager.getInstance();
      new ClassLoadManager();
      new InitDataBase();
      lman.log("########## DATABASE SUCCESFULLY INITIALIZED ##############");
    }catch (Exception ex) {
      lman.log("########## DATABASE FAILED TO INITIALIZE ##############");
      lman.log(ex);
    }
  }

  public Titration() {
//--Check to see if we can write to ./log
    new ClassLoadManager();
    Properties prop = new Properties();
    try {

      prop.load(new FileInputStream("./conf/client.prop"));
      UI = prop.getProperty("interface");
    } catch (Exception ex) {
      prop.setProperty("interface","System");
    }finally{
      try {
        prop.store(new FileOutputStream("./conf/client.prop"),"Titration UI Properties");
      }catch(Exception ex){}
    }

    try {
      String lookAndFeelClass = UIManager.getSystemLookAndFeelClassName();
      if(UI.equals("Metal")){
        lookAndFeelClass = "javax.swing.plaf.metal.MetalLookAndFeel";
      }else if(UI.equals("System")){
        lookAndFeelClass = UIManager.getSystemLookAndFeelClassName();
        selid = 1;
      }else if(UI.equals("GTK")){
        lookAndFeelClass = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
        selid = 2;
      }else if(UI.equals("Cross Platform")){
        lookAndFeelClass = UIManager.getCrossPlatformLookAndFeelClassName();
        selid = 3;
      }else if(UI.equals("Motif")){
        lookAndFeelClass = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        selid = 4;
      }
      UIManager.setLookAndFeel(lookAndFeelClass);

    }catch(Exception e) {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception ex) {}
//      e.printStackTrace();
    }
// Center the window
    TitrationUI frame = new TitrationUI();
    frame.setProperties(prop);
    frame.validate();
//------------Center the window---------------
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation( (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setLookAndFeelID(selid);
    frame.setVisible(true);
    frame.login();
  }

  /**
   * Application entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    if (args.length > 0 && args[0].equalsIgnoreCase("-initdb")) {
      new Titration(0);
    } else {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          } catch (Exception exception) {
            exception.printStackTrace();
          }
          new Titration();
        }
      });
    }
  }
}
