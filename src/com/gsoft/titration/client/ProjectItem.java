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

public class ProjectItem {
  private String name = "Available Projects";
  private String description = "n/a";
  private int projectID = -1;
  private int priority = 1;
  private int status = 0;
  private String reqestor = "n/a";
  private String reqDate;
  private String startDate;
  private String closeDate;
  private int hours;
  private int pctCompleted = 0;
  private int isHidden = 0;
  private String[] statusLabel = {"Active","Closed","On-Going","Paused","Closed"};

  public ProjectItem(){}
  public ProjectItem(String pn, int pid){
    this.name = pn;
    this.projectID = pid;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = (name == null)?"":name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = (description == null)?"":description;
  }

  public int getProjectID() {
    return this.projectID;
  }

  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  public int getPriority() {
    return this.priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getStatusLabel(){
    return statusLabel[status];
  }

  public void setStatusLable(String nlabel){
   if(nlabel.equals("Active")){
     status = 0;
   }else if(nlabel.equals("Closed")){
     status = 1;
   }else if(nlabel.equals("On-Going")){
     status = 2;
   }else{
     status = 1;
   }
  }

  public String getReqestor() {
    return this.reqestor;
  }

  public void setReqestor(String reqestor) {
    this.reqestor = (reqestor == null)?"":reqestor;
  }

  public String getReqDate() {
    return this.reqDate;
  }

  public void setReqDate(String reqDate) {
    this.reqDate = (reqDate == null)?"":reqDate;
  }

  public String getStartDate() {
    return this.startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = (startDate == null)?"":startDate;
  }

  public String getCloseDate() {
    return this.closeDate;
  }

  public void setCloseDate(String closeDate) {

    this.closeDate = (closeDate == null)?"":closeDate;
  }

  public int getHours() {
    return this.hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public String toString(){ return name;}

  public int getPctCompleted() {
    return this.pctCompleted;
  }

  public void setPctCompleted(int pctCompleted) {
    this.pctCompleted = pctCompleted;
  }

  public void setPctCompleted(String pctcmpt){
    int indx = pctcmpt.indexOf("%");
    if(indx > -1){
      this.pctCompleted = new Integer(pctcmpt.substring(0,indx)).intValue();
    }
  }

  public int getIsHidden() {
    return this.isHidden;
  }

  public void setIsHidden(int isHidden) {
    this.isHidden = isHidden;
  }


}