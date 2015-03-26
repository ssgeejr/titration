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
import javax.swing.border.*;

public class MessageDialog extends JDialog implements ActionListener {
  public static final int ERROR_DIALOG = 0;
  public static final int MESSAGE_DIALOG = 1;
  private JPanel panel1 = new JPanel();
  private JPanel panel2 = new JPanel();
  private JPanel insetsPanel1 = new JPanel();
  private JPanel insetsPanel2 = new JPanel();
  private JPanel insetsPanel3 = new JPanel();
  private JButton button1 = new JButton();
  private JLabel imageLabel = new JLabel();
  private JLabel label2 = new JLabel();
  private ImageIcon image1 = new ImageIcon();
  private BorderLayout borderLayout1 = new BorderLayout();
  private BorderLayout borderLayout2 = new BorderLayout();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JLabel errorLbl = new JLabel();
  private String message = "";
  private GridLayout gridLayout1 = new GridLayout();
  private int MSG_TYPE = 0;
  public MessageDialog(Frame parent,String msg, int type) {
    super(parent);
    MSG_TYPE = type;
    message = msg;
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    if(MSG_TYPE == ERROR_DIALOG){
      image1 = new ImageIcon(com.gsoft.titration.client.TitrationUI.class.getResource("error.gif"));
      this.setTitle("An Error Has Occurred");
      label2.setText("An Error Has Occurred");
    }else{
      image1 = new ImageIcon(com.gsoft.titration.client.TitrationUI.class.getResource("info.gif"));
      this.setTitle("Message Dialog");
      label2.setText("");

    }
    imageLabel.setIcon(image1);
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    insetsPanel1.setLayout(flowLayout1);
    insetsPanel2.setLayout(flowLayout1);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    insetsPanel2.setMinimumSize(new Dimension(85, 105));
    insetsPanel2.setPreferredSize(new Dimension(85, 105));
    insetsPanel2.setRequestFocusEnabled(true);
    label2.setForeground(Color.red);
    label2.setHorizontalAlignment(SwingConstants.CENTER);
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
    insetsPanel3.setDebugGraphicsOptions(0);
    insetsPanel3.setToolTipText("");
    button1.setText("Ok");
    button1.addActionListener(this);
    errorLbl.setText(message);
    gridLayout1.setHgap(0);
    gridLayout1.setRows(5);
    panel2.add(insetsPanel2, BorderLayout.WEST);
    insetsPanel2.add(imageLabel, null);
    this.getContentPane().add(panel1, null);
    panel2.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel3.add(label2, null);
    insetsPanel3.add(errorLbl, null);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.NORTH);
    setResizable(true);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }
  //Close the dialog
  void cancel() {
    dispose();
  }
  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}
