package com.gsoft.titration.client;
/*************************************************************************
 Copyright (C) 2002  Steve Gee
 stevegee@gravitysoft.com
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

import java.io.Serializable;
import javax.swing.ComboBoxModel;

/**
 Similar to the <code>StringListModel</code> with the addition of a "selected item" property.
 @see #getSelectedItem
 @see #setSelectedItem
 */
public class StringComboBoxModel extends StringListModel
    implements Serializable,
    ComboBoxModel {
  /** The currently selected item.
   */
  protected Object selectedItem;

  // Added for javadoc...
  /** Constructs a default <code>StringComboBoxModel</code>.
   */
  public StringComboBoxModel() {
  }

  //
  // ComboBoxModel methods
  //

  /** Sets the currently selected item.
           @param item the item that is currently selected
           @see #getSelectedItem
   */
  public void setSelectedItem(Object item) {
    selectedItem = item;
    fireContentsChanged(this, -1, -1);
  }

  /** Gets the currently selected item.
           @return the item that is currently selected
           @see #setSelectedItem
   */
  public Object getSelectedItem() {
    return selectedItem;
  }
}