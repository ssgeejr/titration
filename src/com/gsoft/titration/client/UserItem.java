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

public class UserItem {
  private String uname;
  private String pword;
  private String actualName;
  private int userid;

  public UserItem(){}
  public UserItem(String un,String pw,String dn,int uid){
    uname = un;
    pword = pw;
    actualName = dn;
    userid = uid;
  }

  public String getUname() {
    return this.uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String getPword() {
    return this.pword;
  }

  public void setPword(String pword) {
    this.pword = pword;
  }

  public String getActualName() {
    return this.actualName;
  }

  public void setDname(String actualName) {
    this.actualName = actualName;
  }

  public int getUserid() {
    return this.userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }
  public String toString(){ return actualName;}
}