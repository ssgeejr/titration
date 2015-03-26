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

import javax.swing.JFrame;
import com.gsoft.titration.sql.*;
import java.sql.*;
import java.util.*;

public class TitrationDataManager extends JFrame {
  protected LogManager lman = LogManager.getInstance();
  protected TagReader tReader = null;
  private EncryptionUtility encUtil = new EncryptionUtility();
  private ConnectionManager conMgr = null;
  private Connection conn = null;
  private Hashtable connList = new Hashtable();
  private Hashtable stmtSQL = new Hashtable();
  private PreparedStatement pstate = null;
  private String sqlReference[] = {"shiftPriorityDown","shiftPriorityUp"};


  public TitrationDataManager() {
    try {
      tReader = new TagReader("./conf/config.xml");
      conMgr = ConnectionManager.getInstance();
      initStatements();
    } catch (Exception ex) {
      lman.log(ex);
    }
  }

  private void initStatements() throws Exception {
    validateConnection();
    Hashtable sqlHash = tReader.getTags("sql");
    Enumeration loop = sqlHash.keys();
    while (loop.hasMoreElements()) {
      String key = (String) loop.nextElement();
      createPreparedStatement(key, (String) sqlHash.get(key));
    }
  }

  private void validateConnection() throws Exception {
    if (conn == null || conn.isClosed()) {
      conn = conMgr.getConnection(tReader.getTagValue("database", "driver"),
                                  tReader.getTagValue("database", "url"),
                                  tReader.getTagValue("database", "username"),
                                  tReader.getTagValue("database", "password"));
      refreshStatements();
    }
  }

  public void createPreparedStatement(String name, String sql) throws Exception {
    validateConnection();
    stmtSQL.put(name, sql);
    connList.put(name, conn.prepareStatement(sql));
  }

  public PreparedStatement fetchStoredProcedure(String name) throws Exception {
    validateConnection();
    return (PreparedStatement) connList.get(name);
  }

  private void refreshStatements() throws Exception {
    if (stmtSQL.size() > 0) {
      System.err.println("--WARNING Connection was reset....attempting to recover -all- stored procedures--");
      Enumeration enm = stmtSQL.keys();
      while (enm.hasMoreElements()) {
        String key = (String) enm.nextElement();
        connList.put(key, conn.prepareStatement( (String) stmtSQL.get(key)));
      }
    } //end if > 0
  }

  public int getNextNum(String table) throws Exception {
    int nextnum = -1;
    pstate = fetchStoredProcedure("getNextNum");
    pstate.setString(1, table);
    ResultSet rset = pstate.executeQuery();
    rset.next();
    nextnum = rset.getInt("NEXTNUM");
    rset.close();
    pstate = fetchStoredProcedure("updateNextNum");
    pstate.setString(1, table);
    pstate.executeUpdate();
    return nextnum;
  }

  public ArrayList getProjectList(int userid, int filter, int showHidden) throws Exception {
    ArrayList list = new ArrayList();
    pstate = fetchStoredProcedure("userProjectsFiltered");
    pstate.setInt(1, userid);
    pstate.setInt(2, filter);
    ResultSet rset = pstate.executeQuery();
    ProjectItem pi = null;
    while (rset.next()) {
      pi = new ProjectItem();
      pi.setProjectID(rset.getInt("pid"));
      pi.setName(rset.getString("pname"));
      pi.setDescription(rset.getString("pdesc"));
      pi.setReqestor(rset.getString("preq"));
      pi.setReqDate(rset.getString("preqdate"));
      pi.setStartDate(rset.getString("pstart"));
      pi.setCloseDate(rset.getString("pcomplete"));
      pi.setStatus(rset.getInt("pstatus"));
      pi.setPriority(rset.getInt("ppriority"));
      pi.setHours(rset.getInt("phours"));
      list.add(pi);
    }
    return list;
  }

  public Iterator getSortedProjectList(int userid, int hideHidden) throws Exception {
    ArrayList list = new ArrayList();
    pstate = fetchStoredProcedure("sortedUserProjects");
    pstate.setInt(1, userid);
    pstate.setInt(2, hideHidden);
    ResultSet rset = pstate.executeQuery();
    ProjectItem pi = null;
    while (rset.next()) {
      pi = new ProjectItem();
      pi.setProjectID(rset.getInt("pid"));
      pi.setName(rset.getString("pname"));
      pi.setDescription(rset.getString("pdesc"));
      pi.setReqestor(rset.getString("preq"));
      pi.setReqDate(rset.getString("preqdate"));
      pi.setStartDate(rset.getString("pstart"));
      pi.setCloseDate(rset.getString("pcomplete"));
      pi.setStatus(rset.getInt("pstatus"));
      pi.setPriority(rset.getInt("ppriority"));
      pi.setHours(rset.getInt("phours"));
      pi.setPctCompleted(rset.getInt("completedpct"));
      list.add(pi);
    }
    return list.iterator();
  }


  public ArrayList getProjectList(int userid, int hideHidden) throws Exception {
    ArrayList list = new ArrayList();
    pstate = fetchStoredProcedure("userProjects");
    pstate.setInt(1, userid);
    pstate.setInt(2, hideHidden);
    ResultSet rset = pstate.executeQuery();
    ProjectItem pi = null;
    while (rset.next()) {
      pi = new ProjectItem();
      pi.setProjectID(rset.getInt("pid"));
      pi.setName(rset.getString("pname"));
      pi.setDescription(rset.getString("pdesc"));
      pi.setReqestor(rset.getString("preq"));
      pi.setReqDate(rset.getString("preqdate"));
      pi.setStartDate(rset.getString("pstart"));
      pi.setCloseDate(rset.getString("pcomplete"));
      pi.setStatus(rset.getInt("pstatus"));
      pi.setPriority(rset.getInt("ppriority"));
      pi.setHours(rset.getInt("phours"));
      pi.setPctCompleted(rset.getInt("completedpct"));
      pi.setIsHidden(rset.getInt("ishidden"));
      list.add(pi);
    }
    return list;
  }

  public void hideShowSelectedRows(int userid, int hidden, Iterator itr) throws Exception{
    pstate = fetchStoredProcedure("hideSelectedRow");
    while(itr.hasNext()){
      pstate.setInt(1,hidden);
      pstate.setInt(2,userid);
      pstate.setInt(3,((ProjectItem)itr.next()).getProjectID());
      pstate.executeUpdate();
    }
  }

  public LoginItem doLogin(String uname, String pword) throws Exception {
    LoginItem li = new LoginItem();
    pstate = fetchStoredProcedure("login");
    pstate.setString(1, uname);
    pstate.setString(2, encUtil.encrypt(pword));
    ResultSet rset = pstate.executeQuery();
    if (rset.next()) {
      li.setUserID(rset.getInt("USERID"));
      li.setuserName(rset.getString("ACTUALNAME"));
    }
    return li;
  }

  public void shiftRowsDown(int userid, int moveFrom, int moveTo) throws Exception {
//Not sure if this is even possible, but we'll put it in here as safety measure
    if(moveTo == moveFrom) return;
    int sqlType = (moveTo > moveFrom)?0:1;

    try{
      int tmpProjectId = Integer.parseInt("99" + userid + "" + moveFrom + "" + moveTo) * -1;
      pstate = fetchStoredProcedure("setNewPriority");
      pstate.setInt(1,tmpProjectId);
      pstate.setInt(2,moveFrom);
      pstate.setInt(3,userid);
      pstate.executeUpdate();
//----------------------------------------------------------
      pstate = fetchStoredProcedure(sqlReference[sqlType]);
      pstate.setInt(1,userid);
      pstate.setInt(2,moveFrom);
      pstate.setInt(3,moveTo);
      pstate.executeUpdate();
//----------------------------------------------------------
      pstate = fetchStoredProcedure("setNewPriority");
      pstate.setInt(1,moveTo);
      pstate.setInt(2,tmpProjectId);
      pstate.setInt(3,userid);
      pstate.executeUpdate();
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }


  public void updatePriority(int userid, Object[] items) throws Exception {
    pstate = fetchStoredProcedure("alterProjectPriority");
    ProjectItem pitem = (ProjectItem) items[0];
    pstate.setInt(1, pitem.getPriority());
    pstate.setInt(2, pitem.getProjectID());
    pstate.setInt(3, userid);
    pstate.executeUpdate();
    pitem = (ProjectItem) items[1];
    pstate.setInt(1, pitem.getPriority());
    pstate.setInt(2, pitem.getProjectID());
    pstate.setInt(3, userid);
    pstate.executeUpdate();
  }

  public boolean projectNameOnfile(String pname) throws Exception {
    boolean onfile = false;
    pstate = fetchStoredProcedure("validateProjectName");
    pstate.setString(1, pname);
    ResultSet rset = pstate.executeQuery();
    if (rset.next()) {
      onfile = true;
    }
    rset.close();

    return onfile;
  }

  public Iterator fetchStatusMap() throws Exception {
    ArrayList list = new ArrayList();
    pstate = fetchStoredProcedure("fetchStatusMap");
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      list.add(rset.getString("STATUS"));
    }
    rset.close();
    return list.iterator();

  }

  public void saveNewProject(ProjectItem pi, int userID) throws Exception {
    int nextnum = getNextNum("PROJECTS");
    PreparedStatement newPrj = fetchStoredProcedure("saveNewProject");
    newPrj.setInt(1, nextnum);
    newPrj.setString(2, pi.getName());
    newPrj.setString(3, pi.getDescription());
    newPrj.setString(4, pi.getReqestor());
    newPrj.setString(5, formatDate(pi.getReqDate()));
    newPrj.setString(6, formatDate(pi.getStartDate()));
    newPrj.setString(7, formatDate(pi.getCloseDate()));
    newPrj.setInt(8, pi.getStatus());
//    newPrj.setInt(9, pi.getHours());
//    newPrj.setInt(10,pi.getPriority());
    newPrj.executeUpdate();
//    newPrj.close();                       ----------------XXX-----------------
    newPrj = fetchStoredProcedure("assignNewPrjToUser");
    newPrj.setInt(1, nextnum);
    newPrj.setInt(2, userID);
    newPrj.setInt(3, pi.getPriority());
    newPrj.setInt(4,pi.getPctCompleted());
    newPrj.setInt(5,pi.getHours());
    newPrj.executeUpdate();
    if (userID != 0) {
      newPrj = fetchStoredProcedure("assignNewPrjToAdmin");
      newPrj.setInt(1, nextnum);
      newPrj.executeUpdate();
    }
    refreshProjectPriorityTable(userID);
  }

  public void updateExistingProject(ProjectItem pi, int userid) throws Exception {
    pstate = fetchStoredProcedure("updateExistingPrj");
//System.out.println("getStartDate [" + formatDate(pi.getStartDate()) + "]");
//System.out.println("getImpDate [" + formatDate(pi.getImpDate()) + "]");
//System.out.println("getStatus [" + pi.getStatus() + "]");
//System.out.println("getHours [" + pi.getHours() + "]");
//System.out.println("getProjectID [" + pi.getProjectID() + "]");
    pstate.setString(1, formatDate(pi.getStartDate()));
    pstate.setString(2, formatDate(pi.getCloseDate()));
    pstate.setInt(3, pi.getStatus());
    pstate.setInt(4, pi.getProjectID());
    pstate.executeUpdate();
    pstate = fetchStoredProcedure("updateProjectPctComplete");
    pstate.setInt(1,pi.getPctCompleted());
    pstate.setInt(2,pi.getHours());
    pstate.setInt(3, pi.getProjectID());
    pstate.setInt(4, userid);
    pstate.executeUpdate();
  }

  public void addNotes(String date, String comment, int userid, int projectid) throws Exception {
//addNotes = createPreparedStatement("addNotes","insert"
//into PROJECTNOTES (NOTEID,PROJECTID,USERID,COMMENT,COMMENTDATE)"
    PreparedStatement newNote = fetchStoredProcedure("addNotes");
    newNote.setInt(1, getNextNum("PROJECTNOTES"));
    newNote.setInt(2, projectid);
    newNote.setInt(3, userid);
    newNote.setString(4, comment);
    newNote.setString(5, formatDate(date));
    newNote.executeUpdate();
  }

  public ArrayList fetchNotes(int projectid) throws Exception {
    ArrayList list = new ArrayList();
    CommentItem ci = null;
    pstate = fetchStoredProcedure("fetchNotes");
    pstate.setInt(1, projectid);
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      ci = new CommentItem();
      ci.setCommentBy(rset.getString("UNAME"));
      ci.setComment(rset.getString("CMNT"));
      ci.setDate(rset.getString("CDATE"));
      list.add(ci);
    }
    return list;
  }

  public UserItem fetchUserItem(int userid) throws Exception {
    ArrayList list = new ArrayList();
    pstate = fetchStoredProcedure("fetchUserItem");
    pstate.setInt(1,userid);
    ResultSet rset = pstate.executeQuery();
    UserItem uitem = null;
    if (rset.next()) {
      uitem = new UserItem(rset.getString("USERNAME"), rset.getString("PASSWORD"), rset.getString("ACTUALNAME"), rset.getInt("USERID"));
    }
    return uitem;
  }


  public ArrayList fetchUserList() throws Exception {
    ArrayList list = new ArrayList();
    pstate = fetchStoredProcedure("fetchUserList");
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      list.add(new UserItem(rset.getString("USERNAME"), rset.getString("PASSWORD"), rset.getString("ACTUALNAME"), rset.getInt("USERID")));
    }
    return list;
  }

  public boolean addNewUser(String un, String pw, String dn) throws Exception {
    boolean isOnFile = true;
    pstate = fetchStoredProcedure("checkNewUser");
    pstate.setString(1, un);
    ResultSet rset = pstate.executeQuery();
    if (!rset.next()) {
      isOnFile = false;
      int userID = getNextNum("USERS");
      pstate = fetchStoredProcedure("saveNewUser");
      pstate.setInt(1, userID);
      pstate.setString(2, un);
      pstate.setString(3, encUtil.encrypt(pw));
      pstate.setString(4, dn);
      pstate.executeUpdate();
    }
    return isOnFile;
  }

  public void resetPassword(int userid, String pword) throws Exception {
    pstate = fetchStoredProcedure("resetPassword");
    pstate.setString(1, encUtil.encrypt(pword));
    pstate.setInt(2, userid);
    pstate.executeUpdate();
  }

  public Object[] fetchProjectLists(int userid) throws Exception {
    Object obj[] = new Object[2];
    Hashtable list = new Hashtable();
    ArrayList asgn = new ArrayList();
    ArrayList avl = new ArrayList();
    pstate = fetchStoredProcedure("fetchAssingedProjects");
    pstate.setInt(1, userid);
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      list.put(rset.getString("pid"), "");
      asgn.add(new ProjectItem(rset.getString("pname"), rset.getInt("pid")));
    }
    rset.close();
    pstate = fetchStoredProcedure("fetchAllProjects");
    rset = pstate.executeQuery();
    while (rset.next()) {
      if (!list.containsKey(rset.getString("pid"))) {
        avl.add(new ProjectItem(rset.getString("pname"), rset.getInt("pid")));
      }
    }
    obj[0] = asgn;
    obj[1] = avl;
    return obj;
  }

  public void updateUserProjects(int userid, int projectid, boolean remove) throws Exception {
    if (remove) {
      pstate = fetchStoredProcedure("cleanUserProjects");
      pstate.setInt(1, userid);
      pstate.setInt(2, projectid);
      pstate.executeUpdate();
    } else {
      pstate = fetchStoredProcedure("getUserProjectCount");
      pstate.setInt(1, userid);
      ResultSet rset = pstate.executeQuery();
      rset.next();
      int nextpriority = rset.getInt("newPriority");
      rset.close();
      pstate = fetchStoredProcedure("saveUserProjects");
      pstate.setInt(1, userid);
      pstate.setInt(2, projectid);
      pstate.setInt(3, nextpriority);
      pstate.executeUpdate();
    }
    refreshProjectPriorityTable(userid);
  }

//######## RESET ALL THE PRIORITIES TO 1,2,3...##########
  public void refreshProjectPriorityTable(int userid) throws Exception {
    PreparedStatement prepstmt = fetchStoredProcedure("alterProjectPriority");
    pstate = fetchStoredProcedure("userProjects");
    pstate.setInt(1, userid);
    ResultSet rset = pstate.executeQuery();
    int priority = 1;
    while (rset.next()) {
      prepstmt.setInt(1, priority);
      prepstmt.setInt(2, rset.getInt("pid"));
      prepstmt.setInt(3, userid);
      prepstmt.executeUpdate();
      priority++;
    }
  }

  public int validateUpdateUserPassword(int userid, String old, String newpw) throws Exception {
    pstate = fetchStoredProcedure("resetValidPassword");
    pstate.setString(1, encUtil.encrypt(newpw));
    pstate.setInt(2, userid);
    pstate.setString(3, encUtil.encrypt(old));
    return pstate.executeUpdate();
  }

  public ArrayList getUserProjectDetail(int userid) throws Exception {
    ArrayList list = new ArrayList();
    ProjectDetailItem pdi = null;
    pstate = fetchStoredProcedure("userProjectsDetail");
    pstate.setInt(1, userid);
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      pdi = new ProjectDetailItem();
      pdi.setProjectID(rset.getInt("PID"));
      pdi.setProjectOrder(rset.getInt("PORDER"));
      pdi.setPctComplete(rset.getInt("PCMPT"));
      pdi.setProjectHours(rset.getInt("PCHOURS"));
      pdi.setUserName(rset.getString("UNAME"));
      pdi.setProjectName(rset.getString("PNAME"));
      pdi.setProjectStatus(rset.getString("PSTAT"));
      list.add(pdi);
    }
    return list;
  }

  public ArrayList getUserMasterList() throws Exception {
    ArrayList list = new ArrayList();
    ProjectDetailItem pdi = null;
    pstate = fetchStoredProcedure("userMasterList");
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      pdi = new ProjectDetailItem();
      pdi.setUserID(rset.getInt("USERID"));
      pdi.setUserName(rset.getString("ACTUALNAME"));
      list.add(pdi);
    }
    return list;
  }

  public ArrayList getProjectDevs(int projectID) throws Exception{
    ArrayList list = new ArrayList();
    ProjectDetailItem pdi = null;
    pstate = fetchStoredProcedure("projectDevs");
    pstate.setInt(1,projectID);
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      pdi = new ProjectDetailItem();
      pdi.setUserName(rset.getString("ANAME"));
      pdi.setProjectOrder(rset.getInt("PORDER"));
      pdi.setPctComplete(rset.getInt("CMPPCT"));
      pdi.setProjectHours(rset.getInt("CMPHOURS"));
      list.add(pdi);
    }
    return list;
  }


  public ArrayList getProjectMasterList() throws Exception{
    ArrayList list = new ArrayList();
    ProjectItem pdi = null;
    pstate = fetchStoredProcedure("masterProjectList");
    ResultSet rset = pstate.executeQuery();
    while (rset.next()) {
      pdi = new ProjectItem();
      pdi.setName(rset.getString("PNAME"));
      pdi.setProjectID(rset.getInt("PID"));
      pdi.setReqestor(rset.getString("PREQ"));
      pdi.setReqDate(rset.getString("REQD"));
      pdi.setStartDate(rset.getString("SDATE"));
      pdi.setCloseDate(rset.getString("CDATE"));
      pdi.setStatus(rset.getInt("PSTAT"));
      list.add(pdi);
    }
    return list;
  }



//---------**********************-------------
  private String formatDate(String date) {
    if (date == null || date.equals("n/a"))
      return null;
    StringTokenizer stok = new StringTokenizer(date, "-");
    String m = stok.nextToken();
    String d = stok.nextToken();
    String y = stok.nextToken();
    return y + "-" + m + "-" + d;
  }
}
