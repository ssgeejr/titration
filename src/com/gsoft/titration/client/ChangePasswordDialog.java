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

public class ChangePasswordDialog extends JDialog implements ActionListener {
  private JPanel panel1 = new JPanel();
  private JPanel panel2 = new JPanel();
  private JPanel insetsPanel1 = new JPanel();
  private JButton jbCancel = new JButton();
  private ImageIcon image1 = new ImageIcon();
  private BorderLayout borderLayout1 = new BorderLayout();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private BorderLayout borderLayout3 = new BorderLayout();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JPanel insetsPanel2 = new JPanel();
  private JLabel imageLabel = new JLabel();
  private JPanel jpComment = new JPanel();
  private BorderLayout borderLayout4 = new BorderLayout();
  private FlowLayout flowLayout2 = new FlowLayout();
  private JPanel jPanel2 = new JPanel();
  private GridLayout gridLayout1 = new GridLayout();
  private JLabel jLabel1 = new JLabel();
  private GridLayout gridLayout2 = new GridLayout();
  private JPanel insetsPanel3 = new JPanel();
  private JLabel jLabel3 = new JLabel();
  private JLabel label1 = new JLabel();
  private JLabel label3 = new JLabel();
  private JPasswordField jtPWOld = new JPasswordField();
  private JPasswordField jtPWNew = new JPasswordField();
  private JPasswordField jtPWConf = new JPasswordField();
  private JButton jbChange = new JButton();
  private boolean changePW = false;

  public ChangePasswordDialog(Frame parent) {
    super(parent);
    try {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
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
    this.setSize(new Dimension(381, 163));
    image1 = new ImageIcon(com.gsoft.titration.client.TitrationUI.class.getResource("changePW.gif"));
    setTitle("Comment");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    jbCancel.setText("Cancel");
    jbCancel.addActionListener(this);
    jPanel1.setLayout(borderLayout3);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    insetsPanel2.setLayout(flowLayout1);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    imageLabel.setIcon(image1);
    imageLabel.setMaximumSize(new Dimension(50,51));
    jpComment.setLayout(borderLayout4);
    jpComment.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
    insetsPanel1.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    jPanel2.setLayout(gridLayout1);
    gridLayout1.setColumns(3);
    gridLayout1.setRows(1);
    gridLayout2.setColumns(1);
    gridLayout2.setRows(6);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    insetsPanel3.setLayout(gridLayout2);
    jLabel3.setText("Confirm Password");
    label1.setText("Old Password");
    label3.setText("New Password");
    jLabel1.setText("");
    jtPWOld.setText("");
    jtPWNew.setText("");
    jtPWConf.setText("");
    jbChange.setText("Change Password");
    jbChange.addActionListener(this);
    insetsPanel1.add(jbChange, null);
    panel2.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(insetsPanel2,  BorderLayout.WEST);
    insetsPanel2.add(imageLabel, null);
    jPanel1.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(insetsPanel3, null);
    insetsPanel3.add(label1, null);
    insetsPanel3.add(jtPWOld, null);
    insetsPanel3.add(label3, null);
    insetsPanel3.add(jtPWNew, null);
    insetsPanel3.add(jLabel3, null);
    jPanel2.add(jLabel1, null);
    panel1.add(jpComment, BorderLayout.CENTER);
    getContentPane().add(panel1, null);
    insetsPanel1.add(jbCancel, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2,  BorderLayout.NORTH);
    insetsPanel3.add(jtPWConf, null);
    setResizable(true);}

  /**
   * Close the dialog on a button event.
   *
   * @param actionEvent ActionEvent
   */
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getSource() == jbCancel) {
      dispose();
    }else if (actionEvent.getSource() == jbChange) {
      changePW = true;
      dispose();
    }
  }
  public boolean changePassword(){return changePW;}
  public String[] getPasswords(){
    String pwds[] = new String[3];
    pwds[0] = new String(jtPWOld.getPassword());
    pwds[1] = new String(jtPWNew.getPassword());
    pwds[2] = new String(jtPWConf.getPassword());
    return pwds;
  }
}
