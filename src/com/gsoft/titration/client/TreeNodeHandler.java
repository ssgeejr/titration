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
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.beans.*;
import javax.swing.tree.*;
import java.text.*;
import java.io.*;

public class TreeNodeHandler {
  private String NEW_LINE = System.getProperties().getProperty("line.separator");

  public void processNodeRequest(int type, TreeNode rootNode, String path, Frame parent) throws Exception {
//0 == Export
//1 == Save
//2 == Print
//3 == XML
//4 == HTML
    String SPACER = "\t";
    if (type == 0)
      SPACER = " ,";
    else if (type == 2)
      SPACER = "   ";
    else if (type == 3) {
      exportToXML(rootNode, path);
      return;
    } else if (type == 4) {
      exportToHTML(rootNode, path);
      return;
    }

    StringBuffer report = new StringBuffer();
    Enumeration enm = rootNode.children();
    Enumeration projectEnm = null;
    Enumeration userEnm = null;
    Enumeration detailEnm = null;
    Enumeration itemEnm = null;

    TreeNode projectList = null;
    TreeNode userList = null;
    TreeNode detailList = null;
    TreeNode itemList = null;

    while (enm.hasMoreElements()) {
      projectList = (TreeNode) enm.nextElement();
      report.append(projectList.toString()).append(NEW_LINE);
      projectEnm = projectList.children();
      while (projectEnm.hasMoreElements()) {
        userList = (TreeNode) projectEnm.nextElement();
        report.append(SPACER + userList.toString()).append(NEW_LINE);
        detailEnm = userList.children();
        while (detailEnm.hasMoreElements()) {
          detailList = (TreeNode) detailEnm.nextElement();
          report.append(SPACER + SPACER + detailList.toString()).append(NEW_LINE);
          itemEnm = detailList.children();
          while (itemEnm.hasMoreElements()) {
            itemList = (TreeNode) itemEnm.nextElement();
            report.append(SPACER + SPACER + SPACER + detailList.toString()).append(NEW_LINE);
          } //end while itemEnm
        } //end while detailEnm
      } //end while projectEnm
    } //end while enm

    if (type == 2) {
      new PrintManager(parent).printFile(report.toString());
    } else {
      DataOutputStream dos = null;
      try {
        dos = new DataOutputStream(new FileOutputStream(path));
        dos.writeBytes(report.toString());
      } finally {
        try {
          dos.close(); } catch (Exception ex) {}
      }
    }
  } //end method

  private void exportToHTML(TreeNode rootNode, String path) throws Exception {
    String SPACER = ". . . ";
    String BR_NEW_LINE = "<br>" + System.getProperties().getProperty("line.separator");
    String bgcolor[] = {"#FFFFCC","#FFFFFF"};
    int rowColor = 0;
    StringBuffer report = new StringBuffer();
    Enumeration enm = rootNode.children();
    Enumeration projectEnm = null;
    Enumeration userEnm = null;
    Enumeration detailEnm = null;
    Enumeration itemEnm = null;

    TreeNode projectList = null;
    TreeNode userList = null;
    TreeNode detailList = null;
    TreeNode itemList = null;
    report.append("<html><head><title>Project Overview ["
                  + new java.util.Date().toString()
                  + "</title></head><body>");
    while (enm.hasMoreElements()) {
      projectList = (TreeNode) enm.nextElement();
      report.append("<div style=\"background-color: " + bgcolor[rowColor] + "\">");
      rowColor = (rowColor == 0)?1:0;
      report.append("<b>" + projectList.toString() + "</b>").append(BR_NEW_LINE);
      projectEnm = projectList.children();
      while (projectEnm.hasMoreElements()) {
        userList = (TreeNode) projectEnm.nextElement();
        if(userList.getChildCount() > 0){
          report.append(SPACER + "<b>" + userList.toString() + "</b>").append(BR_NEW_LINE);
        }else{
          report.append(SPACER + "<i>" + userList.toString() + "</i>").append(BR_NEW_LINE);
        }
        detailEnm = userList.children();
        while (detailEnm.hasMoreElements()) {
          detailList = (TreeNode) detailEnm.nextElement();
          if(detailList.getChildCount() > 0){
            report.append(SPACER + SPACER + "<b>" + detailList.toString() + "</b>").append(BR_NEW_LINE);
          }else{
            report.append(SPACER + SPACER + "<i>" + detailList.toString() + "</i>").append(BR_NEW_LINE);
          }
          itemEnm = detailList.children();
          while (itemEnm.hasMoreElements()) {
            itemList = (TreeNode) itemEnm.nextElement();
            report.append(SPACER + SPACER + SPACER + "<i>" + detailList.toString() + "</i>").append(BR_NEW_LINE);
          } //end while itemEnm
        } //end while detailEnm
      } //end while projectEnm
      report.append("</div>").append(NEW_LINE);
//      report.append("<hr width=\"50%\" align=\"left\">").append(BR_NEW_LINE);
    } //end while enm
    report.append("</body></html>");

    DataOutputStream dos = null;
    try {
      dos = new DataOutputStream(new FileOutputStream(path));
      dos.writeBytes(report.toString());
    } finally {try {dos.close(); } catch (Exception ex) {}}
  }

  private void exportToXML(TreeNode rootNode, String path) throws Exception {
    String SPACER = "\t";
    StringBuffer report = new StringBuffer();
    Enumeration enm = rootNode.children();
    Enumeration projectEnm = null;
    Enumeration userEnm = null;
    Enumeration detailEnm = null;
    Enumeration itemEnm = null;

    TreeNode projectList = null;
    TreeNode userList = null;
    TreeNode detailList = null;
    TreeNode itemList = null;

    report.append("<?xml version='1.0' encoding='utf-8'?>").append(NEW_LINE);
    report.append("<PROJECT_OVERVIEW>").append(NEW_LINE);
    while (enm.hasMoreElements()) {
      projectList = (TreeNode) enm.nextElement();
      String thisNode = projectList.toString().trim().replaceAll(" ", "_");
      report.append("<_" + thisNode + "_>").append(NEW_LINE);
      projectEnm = projectList.children();
      while (projectEnm.hasMoreElements()) {
        userList = (TreeNode) projectEnm.nextElement();
        detailEnm = userList.children();
        if (userList.getChildCount() > 0)
          report.append("<_" + userList.toString().trim().replaceAll(" ", "_") + "_>").append(NEW_LINE);
        else
          report.append("<value>" + userList.toString() + "</value>").append(NEW_LINE);
        while (detailEnm.hasMoreElements()) {
          detailList = (TreeNode) detailEnm.nextElement();
          if (detailList.getChildCount() > 0)
            report.append("<_" + detailList.toString().trim().replaceAll(" ", "_") + "_>").append(NEW_LINE);
          else
            report.append("<value>" + detailList.toString() + "</value>").append(NEW_LINE);
          itemEnm = detailList.children();
          while (itemEnm.hasMoreElements()) {
            itemList = (TreeNode) itemEnm.nextElement();
            report.append(SPACER + "<value>" + itemList.toString() + "</value>").append(NEW_LINE); } //end while itemEnm
        } //end while detailEnm
        if (userList.getChildCount() > 0)
          report.append(SPACER + "</_" + userList.toString().trim().replaceAll(" ", "_") + "_>").append(NEW_LINE);
      } //end while projectEnm
      report.append("</_" + thisNode + "_>").append(NEW_LINE);
    } //end while enm

    report.append("</PROJECT_OVERVIEW>").append(NEW_LINE);
    DataOutputStream dos = null;
    try {
      dos = new DataOutputStream(new FileOutputStream(path));
      dos.writeBytes(report.toString());
    } finally {
      try {
        dos.close(); } catch (Exception ex) {}
    }
  }
}
