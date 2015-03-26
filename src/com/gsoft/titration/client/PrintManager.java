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
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PrintManager {
  private Frame superParent = null;
  public PrintManager(Frame _frame){superParent = _frame;}
  private final String TAB = "     ";
  private int margin = 72;

  public void printFile(String report) throws Exception{
    PrintJob pjob = superParent.getToolkit().getPrintJob(superParent, "Print Report", new Properties());
    if (pjob != null) {
      Graphics pg = pjob.getGraphics();
      if (pg != null) {
        printLongString(pjob, pg, report);
        pg.dispose();
      }
      pjob.end();
    }
  }

  private void printLongString (PrintJob pjob, Graphics pg, String s) {
    int pageNum = 1;
    int linesForThisPage = 0;
    int linesForThisJob = 0;
    // Note: String is immutable so won't change while printing.
    if (!(pg instanceof PrintGraphics)) {
      throw new IllegalArgumentException ("Graphics context not PrintGraphics");
    }
    StringReader sr = new StringReader (s);
    LineNumberReader lnr = new LineNumberReader (sr);
    String nextLine;
    int pageHeight = pjob.getPageDimension().height - margin;
    Font helv = new Font("Monospaced", Font.PLAIN, 10);
    //have to set the font to get any output
    pg.setFont (helv);
    FontMetrics fm = pg.getFontMetrics(helv);
    int fontHeight = fm.getHeight();
    int fontDescent = fm.getDescent();
    int curHeight = margin;
    try {
      do {
        nextLine = lnr.readLine();
        if (nextLine != null) {
          if ((curHeight + fontHeight) > pageHeight) {
            // New Page
            System.out.println ("" + linesForThisPage + " lines printed for page " + pageNum);
            if (linesForThisPage == 0) {
               System.out.println ("Font is too big for pages of this size; aborting...");
               break;
            }
            pageNum++;
            linesForThisPage = 0;
            pg.dispose();
            pg = pjob.getGraphics();
            if (pg != null) {
              pg.setFont (helv);
            }
            curHeight = 0;
          }
          curHeight += fontHeight;
          if (pg != null) {
            pg.drawString (nextLine, margin, curHeight - fontDescent);
            linesForThisPage++;

            linesForThisJob++;
          } else {
            System.out.println ("pg null");
          }
        }
      } while (nextLine != null);
    } catch (EOFException eof) {
      // Fine, ignore
    } catch (Throwable t) { // Anything else
      t.printStackTrace();
    }
  }


}