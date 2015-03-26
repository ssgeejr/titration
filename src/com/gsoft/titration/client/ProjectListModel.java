package com.gsoft.titration.client;
/*************************************************************************
   Copyright (C) 2003  Steve Gee
   stevesgee@cox.net
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

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import com.gsoft.titration.*;

class ProjectListModel extends AbstractTableModel {
  private boolean failedSet = false;
  private final String[] columnNames = {"*", "Status","% Comp", "Name"};
  private Vector data;
  public ProjectListModel() {
    this.data = new Vector();
  }

  public int getRowCount() {
    return data.size();
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public String getColumnName(int column) {
    return columnNames[column];
  }

  public void addRow(ProjectItem a) {
    data.add(a);
    this.fireTableRowsInserted(data.indexOf(a), data.indexOf(a));
  }

  public ProjectItem getProjectItem(int zrow) {
    return (ProjectItem)data.elementAt(zrow);
  }

  public Iterator getProjectItems(int[] selectedRows) {
    ArrayList list = new ArrayList();
    for(int selrow = 0;selrow < selectedRows.length;selrow++){
      list.add((ProjectItem) data.elementAt(selectedRows[selrow]));
    }
    return list.iterator();
  }

  public void removeRow(int x) {
    data.remove(x);
    this.fireTableRowsDeleted(0, x);
  }

  public Object[] moveUp(int row){
    Object list[] = new Object[2];
    Vector vec = fetchVector();
    int to = row - 1;
    ProjectItem toMove = (ProjectItem)vec.elementAt(row);
    ProjectItem tmpObj = (ProjectItem)vec.elementAt(to);
    int toMovePriority = toMove.getPriority();
    toMove.setPriority(tmpObj.getPriority());
    tmpObj.setPriority(toMovePriority);
    vec.setElementAt(toMove,to);
    vec.setElementAt(tmpObj,row);
    list[0] = toMove;
    list[1] = tmpObj;
//    this.removeAll();
//    Iterator iter = vec.iterator();
//    while(iter.hasNext()){
//      this.addRow((ProjectItem)iter.next());
//    }//end while
    return list;
  }
//  public Object[] moveUp(int row){
//    Object list[] = new Object[2];
//    Vector vec = fetchVector();
//    int to = row - 1;
//    ProjectItem toMove = (ProjectItem)vec.elementAt(row);
//    ProjectItem tmpObj = (ProjectItem)vec.elementAt(to);
//    int toMovePriority = toMove.getPriority();
//    toMove.setPriority(tmpObj.getPriority());
//    tmpObj.setPriority(toMovePriority);
//    vec.setElementAt(toMove,to);
//    vec.setElementAt(tmpObj,row);
//    list[0] = toMove;
//    list[1] = tmpObj;
//    this.removeAll();
//    Iterator iter = vec.iterator();
//    while(iter.hasNext()){
//      this.addRow((ProjectItem)iter.next());
//    }//end while
//    return list;
//  }

  public Object[] moveDown(int row){
    Object list[] = new Object[2];
    Vector vec = fetchVector();
    int to = row + 1;
    ProjectItem toMove = (ProjectItem)vec.elementAt(row);
    ProjectItem tmpObj = (ProjectItem)vec.elementAt(to);
    int toMovePriority = toMove.getPriority();
    toMove.setPriority(tmpObj.getPriority());
    tmpObj.setPriority(toMovePriority);
    vec.setElementAt(toMove,to);
    vec.setElementAt(tmpObj,row);
    list[0] = toMove;
    list[1] = tmpObj;
//    this.removeAll();
//    Iterator iter = vec.iterator();
//    while(iter.hasNext()) {
//      this.addRow((ProjectItem)iter.next());
//    } //end while
    return list;
  }

  public int getSize(){
    return data.size();
  }

  public Vector fetchVector(){
    return new Vector(data);
  }

  public boolean isHidden(int row){
    boolean hidden = false;
    if(((ProjectItem) data.get(row)).getIsHidden() == 1)
      return true;
    return hidden;
  }

  public Object getValueAt(int row, int column) {
    ProjectItem a = (ProjectItem) data.get(row);
    switch (column) {
      case 0:
        return a.getPriority() + "";
      case 1:
        return a.getStatusLabel();
      case 2:
        return a.getPctCompleted() + "%";
      case 3:
        return a.getName();
      default:
        return "";
    }
  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  public Class getColumnClass(int columnIndex) {
    return String.class;
  }

  public void removeAll() {
    int rowcount = this.getRowCount();
    data.removeAllElements();
    this.fireTableRowsDeleted(0, rowcount);

  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    ProjectItem a = (ProjectItem) data.get(rowIndex);
    switch (columnIndex) {
      case 0:
        a.setPriority( Integer.parseInt((String) aValue));
        break;
      case 1:
        a.setStatusLable( (String) aValue);
        break;
      case 2:
        a.setPctCompleted( (String) aValue);
        break;
      case 3:
        a.setName( (String) aValue);
        break;
      default:
        break;
    }

    this.fireTableCellUpdated(rowIndex, columnIndex);
  }
}