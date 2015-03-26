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

public class CommentDialog extends JDialog implements ActionListener {
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
  private JLabel jlPostedBy = new JLabel();
  private JPanel insetsPanel3 = new JPanel();
  private JLabel jlPostDate = new JLabel();
  private JLabel label1 = new JLabel();
  private JLabel label3 = new JLabel();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JPanel insetsPanel2 = new JPanel();
  private JLabel imageLabel = new JLabel();
  private JPanel jpComment = new JPanel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private BorderLayout borderLayout4 = new BorderLayout();
  private FlowLayout flowLayout2 = new FlowLayout();
  private JLabel jLabel2 = new JLabel();
  private JTextArea jtComment = new JTextArea();

  public CommentDialog(Frame parent, CommentItem ci) {
    super(parent);
    try {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
      jlPostedBy.setText(ci.getCommentBy());
      jlPostDate.setText(ci.getDate());
      jtComment.setText(ci.getComment());
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
    this.setSize(new Dimension(450, 200));
    image1 = new ImageIcon(com.gsoft.titration.client.TitrationUI.class.getResource("comment.gif"));
    setTitle("Comment");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    button1.setText("Close");
    button1.addActionListener(this);
    jPanel1.setLayout(borderLayout3);
    gridLayout1.setRows(4);
    gridLayout1.setColumns(1);
    jlPostedBy.setForeground(Color.blue);
    jlPostedBy.setText("<posted by>");
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    jlPostDate.setForeground(Color.blue);
    jlPostDate.setText("<post date>");
    label1.setText("Posted By:");
    label3.setText("Post Date");
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    insetsPanel2.setLayout(flowLayout1);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    imageLabel.setIcon(image1);
    imageLabel.setMaximumSize(new Dimension(50,51));
    jpComment.setLayout(borderLayout4);
    jpComment.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
    insetsPanel1.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    jLabel2.setText("Comment");
    jtComment.setEditable(false);
    jtComment.setText("");
    panel2.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel3.add(label1, null);
    insetsPanel3.add(jlPostedBy, null);
    insetsPanel3.add(label3, null);
    insetsPanel3.add(jlPostDate, null);
    jPanel1.add(insetsPanel2,  BorderLayout.WEST);
    insetsPanel2.add(imageLabel, null);
    panel1.add(jpComment, BorderLayout.CENTER);
    jpComment.add(jScrollPane1,  BorderLayout.CENTER);
    getContentPane().add(panel1, null);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2,  BorderLayout.NORTH);
    jpComment.add(jLabel2, BorderLayout.NORTH);
    jScrollPane1.getViewport().add(jtComment, null);
    setResizable(true);}

  /**
   * Close the dialog on a button event.
   *
   * @param actionEvent ActionEvent
   */
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getSource() == button1) {
      dispose();
    }
  }}
