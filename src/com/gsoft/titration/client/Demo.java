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
import java.beans.*;
import java.util.*;
import javax.swing.*;

public class Demo extends JFrame implements PropertyChangeListener
{
    private DateButton startDateButton;
    private DateButton endDateButton;
    private Date startDate;
    private Date endDate;

    private Demo() {
	super( "DateChooser demo" );
	addWindowListener
	    (
	     new WindowAdapter() {
		     public void windowClosing( WindowEvent e ) {
			 System.exit( 0 );
		     }
		 }
	     );

	startDate = new Date();
	endDate = new Date();

	startDateButton = new DateButton();
	startDateButton.addPropertyChangeListener( "date", this );
	endDateButton = new DateButton();
	endDateButton.addPropertyChangeListener( "date", this );

	getContentPane().setLayout( new GridLayout(2,2) );
	getContentPane().add( new JLabel("Start date") );
	getContentPane().add( startDateButton );
	getContentPane().add( new JLabel("End date") );
	getContentPane().add( endDateButton );
	pack();
	setResizable( false );
    }

    public void propertyChange( PropertyChangeEvent e ) {
	DateButton db = (DateButton)e.getSource();
	if ( db == startDateButton )
	    System.out.print( "Start date changed: " );
	else
	    System.out.print( "End date changed: " );
	System.out.println( db.getDate() );
    }

    public static void main( String[] args ) {
	(new Demo()).show();
    }
}

