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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class ReportDialog extends JDialog implements ActionListener {
  private LogManager lman = LogManager.getInstance();
  private final String FILE_SEP = System.getProperties().getProperty("file.separator");
  private final String NEW_LINE = System.getProperties().getProperty("line.separator");
  private final String TAB = "     ";
  private int margin = 72;

  private JPanel panel1 = new JPanel();
  private JPanel panel2 = new JPanel();
  private JPanel insetsPanel1 = new JPanel();
  private JButton button1 = new JButton();
  private ImageIcon image1 = new ImageIcon();
  private BorderLayout borderLayout1 = new BorderLayout();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private BorderLayout borderLayout3 = new BorderLayout();
  private GridLayout gridLayout1 = new GridLayout();
  private JPanel insetsPanel3 = new JPanel();
  private JLabel jlablelx = new JLabel();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JPanel insetsPanel2 = new JPanel();
  private JLabel imageLabel = new JLabel();
  private JPanel jpComment = new JPanel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private BorderLayout borderLayout4 = new BorderLayout();
  private FlowLayout flowLayout2 = new FlowLayout();
  private JLabel jLabel2 = new JLabel();
  private JTextArea jtReport = new JTextArea();
  private JButton jbSave = new JButton();
  private JButton jbPrint = new JButton();
  private Frame superParent;
  private Vector dataVec;
  public ReportDialog(Frame parent, Vector _vec) {
    super(parent);
    try {
      superParent = parent;
      dataVec = _vec;
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
      ProjectItem pi = null;
      jtReport.append("Project\tPriority\t"
                      + "Request Date\t"
                      + "Start Date\t"
                      + "Imp. Date\t"
                      + "Status\t"
                      + "Description\n");

      for(Iterator itr = _vec.iterator();itr.hasNext();){
        pi = (ProjectItem)itr.next();
        jtReport.append(pi.getName() + "\t"
                        + pi.getPriority() + "\t"
                        + pi.getReqDate() + "\t"
                        + pi.getStartDate() + "\t"
                        + pi.getCloseDate() + "\t"
                        + pi.getStatusLabel() + "\t"
                        + pi.getDescription().replaceAll("\n"," ") + "\n");
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Component initialization.
   *
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {
    this.setSize(new Dimension(500, 600));
    image1 = new ImageIcon(com.gsoft.titration.client.TitrationUI.class.getResource("report.gif"));
    this.setModal(true);
    setTitle("Project Report");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    button1.setText("Close");
    button1.addActionListener(this);
    jPanel1.setLayout(borderLayout3);
    gridLayout1.setRows(4);
    gridLayout1.setColumns(1);
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    jlablelx.setFont(new java.awt.Font("Dialog", 0, 13));
    jlablelx.setForeground(Color.blue);
    jlablelx.setText("Project Report");
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    insetsPanel2.setLayout(flowLayout1);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    imageLabel.setIcon(image1);
    imageLabel.setMaximumSize(new Dimension(50,51));
    jpComment.setLayout(borderLayout4);
    jpComment.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
    insetsPanel1.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    jLabel2.setText("Report");
    jtReport.setEditable(false);
    jtReport.setText("");
    jbSave.setText("Export To CSV File");
    jbSave.addActionListener(this);
    jbPrint.setText("Print");
    jbPrint.addActionListener(this);
    insetsPanel1.add(jbPrint, null);
    insetsPanel1.add(jbSave, null);
    panel2.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel3.add(jlablelx, null);
    jPanel1.add(insetsPanel2,  BorderLayout.WEST);
    insetsPanel2.add(imageLabel, null);
    panel1.add(jpComment, BorderLayout.CENTER);
    jpComment.add(jScrollPane1,  BorderLayout.CENTER);
    getContentPane().add(panel1, null);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2,  BorderLayout.NORTH);
    jpComment.add(jLabel2, BorderLayout.NORTH);
    jScrollPane1.getViewport().add(jtReport, null);
    setResizable(true);}

  /**
   * Close the dialog on a button event.
   *
   * @param actionEvent ActionEvent
   */
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getSource() == button1) {
      dispose();
    }else if (actionEvent.getSource() == jbSave) {
      saveFile();
    }else if (actionEvent.getSource() == jbPrint) {
      printFile();
    }
  }

  private void saveFile(){
    FileDialog dia = new FileDialog(superParent,"Save Export File to ...",FileDialog.SAVE);
    dia.setVisible(true);

    if(dia.getFile() != null){
      String filename = dia.getFile().trim();
      if(!filename.endsWith(".csv"))
        filename += ".csv";
      DataOutputStream dos = null;
      try {
        dos = new DataOutputStream(new FileOutputStream(dia.getDirectory() + filename));
        dos.writeBytes("Project,"
                       +"Priority,"
                       + "Request Date,"
                       + "Start Date,"
                       + "Imp. Date,"
                       + "Status,"
                       + "Description" + NEW_LINE);
        ProjectItem pi = null;
        for(Iterator itr = dataVec.iterator();itr.hasNext();){
          pi = (ProjectItem)itr.next();
          dos.writeBytes(pi.getName() + ","
                         + pi.getPriority() + ","
                         + pi.getReqDate() + ","
                         + pi.getStartDate() + ","
                         + pi.getCloseDate() + ","
                         + pi.getStatusLabel() + ","
                         + pi.getDescription().replaceAll("\n"," ") + NEW_LINE);
        }//end for
      }catch (Exception ex) {
        showMessage("Failed to save the file");
        lman.log("Failed to save the file",ex);
      }finally{
        try{ dos.close();}catch(Exception ex){}
      }//end tcf
    }
  }

  public void showMessage(String msg){
    MessageDialog dlg = new MessageDialog(superParent, msg, MessageDialog.ERROR_DIALOG);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  public void printFile(){
    try{
      StringBuffer report = new StringBuffer();
      ProjectItem pi = null;

      for (Iterator itr = dataVec.iterator(); itr.hasNext(); ) {
        pi = (ProjectItem) itr.next();
        report.append("Project" + TAB  +  "       [" + pi.getName() + "]" + NEW_LINE
                      + TAB  + "Priority      [" + pi.getPriority() +  "]" + NEW_LINE
                      + TAB  + "Request Date  [" + pi.getReqDate() +  "]" + NEW_LINE
                      + TAB  + "Start Date    [" + pi.getStartDate() +  "]" + NEW_LINE
                      + TAB  + "Imp. Date     [" + pi.getCloseDate() +  "]" + NEW_LINE
                      + TAB  + "Status        [" + pi.getStatusLabel() +  "]" + NEW_LINE
                      + TAB  + "Description   [" + pi.getDescription().replaceAll("\n"," ") +  "]" + NEW_LINE
                      +"________________________________________________________________________" + NEW_LINE);
      }

      PrintJob pjob = superParent.getToolkit().getPrintJob(superParent, "Print Report", new Properties());
      if (pjob != null) {
        Graphics pg = pjob.getGraphics();
        if (pg != null) {
          printLongString(pjob, pg, report.toString());
          pg.dispose();
        }
        pjob.end();
      }
    }catch(Exception ex){
      showMessage("Failed to print file");
      lman.log("Failed to print file",ex);
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
//    System.out.println ("" + linesForThisPage + " lines printed for page " + pageNum);
//    System.out.println ("pages printed: " + pageNum);
//    System.out.println ("total lines printed: " + linesForThisJob);
  }


}
