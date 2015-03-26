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

public class CommentItem {
  private String comment;
  private String commentBy;
  private int commentid;
  private String date;

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getCommentid() {
    return this.commentid;
  }

  public void setCommentid(int commentid) {
    this.commentid = commentid;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCommentBy() {
    return this.commentBy;
  }

  public void setCommentBy(String commentBy) {
    this.commentBy = commentBy;
  }

}