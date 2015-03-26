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

public class ProjectDetailItem {
  private int projectID = 0;
  private int userID = 0;
  private int projectOrder = 0;
  private int pctComplete = 0;
  private int projectHours = 0;
  private String userName  = "";
  private String projectName = "";
  private String projectStatus = "";
  private String actualName = "";

  public int getProjectID() {
    return this.projectID;
  }

  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  public int getProjectOrder() {
    return this.projectOrder;
  }

  public void setProjectOrder(int projectOrder) {
    this.projectOrder = projectOrder;
  }

  public int getPctComplete() {
    return this.pctComplete;
  }

  public void setPctComplete(int pctComplete) {
    this.pctComplete = pctComplete;
  }

  public int getProjectHours() {
    return this.projectHours;
  }

  public void setProjectHours(int projectHours) {
    this.projectHours = projectHours;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getProjectName() {
    return this.projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectStatus() {
    return this.projectStatus;
  }

  public void setProjectStatus(String projectStatus) {
    this.projectStatus = projectStatus;
  }

  public String getActualName() {
    return this.actualName;
  }

  public void setActualName(String actualName) {
    this.actualName = actualName;
  }

  public int getUserID() {
    return this.userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

}