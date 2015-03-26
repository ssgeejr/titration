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

class CommentModel extends AbstractTableModel {
  private boolean failedSet = false;
  private final String[] columnNames = {"Date", "User", "Comment"};
  private Vector data;
  public CommentModel() {
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

  public void addRow(CommentItem a) {
    data.add(a);
    this.fireTableRowsInserted(data.indexOf(a), data.indexOf(a));
  }

  public CommentItem getSelectedComment(int x) {
    return (CommentItem) data.elementAt(x);
  }

  public void removeRow(int x) {
    data.remove(x);
    this.fireTableRowsDeleted(0, x);
  }

  public Object getValueAt(int row, int column) {
    CommentItem a = (CommentItem) data.get(row);
    switch (column) {
      case 0:
        return a.getDate();
      case 1:
        return a.getCommentBy();
      case 2:
        return a.getComment();
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
    CommentItem a = (CommentItem) data.get(rowIndex);
    switch (columnIndex) {
      case 0:
        a.setDate( (String) aValue);
        break;
      case 1:
        a.setCommentBy( (String) aValue);
        break;
      case 2:
        a.setComment( (String) aValue);
        break;
      default:
        break;
    }

    this.fireTableCellUpdated(rowIndex, columnIndex);
  }
}
