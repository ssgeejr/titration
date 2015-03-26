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

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TableRenderer extends JLabel implements TableCellRenderer {

  public TableRenderer() {
    super();
    setOpaque(true);
  }

  // Here, we can just add some crazy crap to customize how to render each one.
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {

    this.setForeground(Color.black);
    setText( (String) value);
      try{
        if(((ProjectListModel)(table.getModel())).isHidden(row)){
          if(isSelected){
            setBackground(Color.darkGray);
            setForeground(Color.white);
          }else{
            setBackground(Color.darkGray);
            setForeground(Color.lightGray);
          }
          return this;
        }//end rowIsHidden
      }catch(Exception ex){}

    if(row % 2 == 1) {
      setBackground(Color.lightGray);
      setForeground(Color.black);
      if(isSelected) {
        setForeground(Color.white);
        setBackground(Color.black);
      }
    } else {
      setBackground(Color.yellow);
      setForeground(Color.black);
      if(isSelected) {
        setForeground(Color.white);
        setBackground(Color.black);
      }
    }

    return this;
  }
}
