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
import java.io.FileOutputStream;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.beans.*;
import javax.swing.tree.*;
import java.text.*;

public class TitrationUI extends TitrationDataManager
    implements CellEditorListener{
  private boolean IS_NEW = false;
  private boolean IS_ADMIN = false;
  private LoginItem loginItem = new LoginItem();
  private ProjectItem openItem = new ProjectItem();
  private ProjectItem emptyProject = new ProjectItem();
  private ArrayList projectList = new ArrayList();
//--------------***--------------
  NumberFormat pctFormat = NumberFormat.getPercentInstance(Locale.US);
  private StringComboBoxModel statusModel = new StringComboBoxModel();
  private StringComboBoxModel filterModel = new StringComboBoxModel();
  private StringComboBoxModel projectComboList = new StringComboBoxModel();
  private StringComboBoxModel userComboList = new StringComboBoxModel();
  private StringListModel adminAvailable = new StringListModel();
  private StringListModel adminAssigned = new StringListModel();
  private ProjectListModel plModel = new ProjectListModel();
  private CommentModel cmntModel = new CommentModel();
  private Properties prop;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jmFile = new JMenu();
  private JMenuItem jMenuFileExit = new JMenuItem();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuHelpAbout = new JMenuItem();
  private JMenu jmLANDF = new JMenu();
  private JRadioButtonMenuItem jrbMetal = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jrbSystem = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jrbGTK = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jrbCrossPlatform = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jrbMotif = new JRadioButtonMenuItem();
  private JPanel mainPanel = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JTabbedPane masterTabPane = new JTabbedPane();
  private JPanel northPanel = new JPanel();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JLabel jlSouth = new JLabel();
  private JPanel mainTabPanel = new JPanel();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JPanel selectProjectPanel = new JPanel();
  private JLabel jLabel1 = new JLabel();
  private JLabel jlUser = new JLabel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private BorderLayout borderLayout3 = new BorderLayout();
  private JTable jlProjectList = new JTable();
  private BorderLayout borderLayout4 = new BorderLayout();
  private JPanel projectPanelCenter = new JPanel();
  private JComboBox jcProjectList = new JComboBox();
  private JLabel jlProjList = new JLabel();
  private JButton jbLoadProject = new JButton();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel10 = new JLabel();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JLabel jLabel11 = new JLabel();
  private JTextArea jtDesc = new JTextArea();
  private JButton jbNewProject = new JButton();
  private JComboBox jcStatus = new JComboBox();
  private JButton jbAddNewComment = new JButton();
  private JButton jbSaveProject = new JButton();
  private JTextField jtReqestor = new JTextField();
  private JLabel jlPriority = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private JTextField jtTotalHours = new JTextField();
  private DateButton jbReqDate = new DateButton();
  private DateButton jbStartDate = new DateButton();
  private DateButton jbCloseDate = new DateButton();
  private JTable jtComments = new JTable();
  private JLabel jLabel13 = new JLabel();
  private JLabel jlProjectID = new JLabel();
  private JMenuItem jmRefreshProj = new JMenuItem();
  private JTextField jtProjectName = new JTextField();
  private JPanel jpMainNorth = new JPanel();
  private FlowLayout mainPriorityFlow = new FlowLayout();
  private JButton jbShowAll = new JButton();
  private JButton jbHide = new JButton();
  private JPanel adminPanel = new JPanel();
  private JLabel jLabel14 = new JLabel();
  private JComboBox jcAdminUserList = new JComboBox();
  private JButton jbAdminLoad = new JButton();
  private JButton jbAdminAddPrj = new JButton();
  private JButton jbAdminRefresh = new JButton();
  private JScrollPane jScrollPane4 = new JScrollPane();
  private JLabel jLabel15 = new JLabel();
  private JLabel jLabel16 = new JLabel();
  private JScrollPane jScrollPane5 = new JScrollPane();
  private JLabel jLabel17 = new JLabel();
  private JLabel jLabel18 = new JLabel();
  private JLabel jLabel19 = new JLabel();
  private JTextField jtUName = new JTextField();
  private JTextField jtDName = new JTextField();
  private JButton jbCreateUser = new JButton();
  private JPasswordField jpPWord = new JPasswordField();
  private JButton jbAdminSaveNewUser = new JButton();
  private JButton jbAdminRemovePrj = new JButton();
  private JButton jbAdminRestPWord = new JButton();
  private JPanel userBkg = new JPanel();
  private JList jlAdminAvailable = new JList();
  private JList jlAdminAssigned = new JList();
  private JMenuItem jmChangePassword = new JMenuItem();
  private JMenu jmOptions = new JMenu();
  private JComboBox jcFilter = new JComboBox();
  private JButton jbFilter = new JButton();
  private JButton jbAssume = new JButton();
  private JButton jbBecomeAdmin = new JButton();
  private JMenuItem jmiRunReport = new JMenuItem();
  private JLabel jLabel20 = new JLabel();
  private JSlider pctComplete = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
  private JLabel jlPctCmplt = new JLabel();
  private JButton jbPctPlus = new JButton();
  private JButton jbCmpMinus = new JButton();
  private JPanel projectPanel = new JPanel();
  private JComboBox jcProjectList2 = new JComboBox();
  private JLabel jlProjList2 = new JLabel();
  private JButton jbLoadProject2 = new JButton();
  private JScrollPane jspProjectTree = new JScrollPane();
  private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Project Listings");
  private JTree projectTree = new JTree(rootNode);

  private DefaultMutableTreeNode projectBranch = null;
  private DefaultMutableTreeNode userBranch = null;
  private DefaultMutableTreeNode projectChild = null;
  private JButton jbLoadAllProject = new JButton();
  private JComboBox jcUserList = new JComboBox();
  private JLabel jLabel2 = new JLabel();
  private JButton jbLoadAllUsers = new JButton();
  private JButton jbLoadUser = new JButton();
  private JMenuItem jmiexportOverview = new JMenuItem();
  private JMenuItem jmiPrintOverview = new JMenuItem();
  private JMenuItem jmiSaveOverviewText = new JMenuItem();
  private JMenu jmOverview = new JMenu();
  private JMenuItem jmiExportXML = new JMenuItem();
  private JMenuItem jmExportHtml = new JMenuItem();

  private boolean mouseDrag = false;
  private int initialSelectedRow = 0;
  private JButton jbClearHidden = new JButton();
  private JButton jbRefreshProject = new JButton();

  public TitrationUI() {
    try {
      pctFormat.setMaximumFractionDigits(2);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      jbInit();
      initModels();
      debugComment();
    } catch (Exception exception) {
      lman.log(exception);
    }
  }

  public void setProperties(Properties _prop) {
    prop = _prop;
  }

  private void jbInit() throws Exception {
    this.setResizable(false);
    setSize(new Dimension(536, 511));
    setTitle("Titration [Project Tracking]");
    this.getContentPane().setLayout(borderLayout2);
    jmFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new TitrationUI_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new TitrationUI_jMenuHelpAbout_ActionAdapter(this));
    jmLANDF.setText("Look and Feel");
    jrbMetal.setText("Metal");
    jrbMetal.addActionListener(new TitrationUI_SwapLookAndFeel_actionAdapter(this));
    jrbSystem.setText("System");
    jrbSystem.addActionListener(new TitrationUI_SwapLookAndFeel_actionAdapter(this));
    jrbGTK.setText("GTK");
    jrbGTK.addActionListener(new TitrationUI_SwapLookAndFeel_actionAdapter(this));
    jrbCrossPlatform.setText("Cross Platform");
    jrbCrossPlatform.addActionListener(new TitrationUI_SwapLookAndFeel_actionAdapter(this));
    jrbMotif.setText("Motif");
    jrbMotif.addActionListener(new TitrationUI_SwapLookAndFeel_actionAdapter(this));
    mainPanel.setLayout(borderLayout1);
    northPanel.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    flowLayout1.setHgap(2);
    flowLayout1.setVgap(2);
    jlSouth.setText("Project Count: ");
    jLabel1.setText("Welcome,  ");
    jlUser.setText("--USER-NOT-LOGGED-IN--");
    mainTabPanel.setLayout(borderLayout3);
    selectProjectPanel.setLayout(borderLayout4);
    projectPanelCenter.setLayout(null);
    jcProjectList.setModel(projectComboList);
    jcProjectList.setBounds(new Rectangle(102, 4, 266, 21));
    jcProjectList.addItemListener(new TitrationUI_jcProjectList_itemAdapter(this));
    jlProjList.setText("Project Listings");
    jlProjList.setBounds(new Rectangle(7, 4, 93, 21));
    jbLoadProject.setBounds(new Rectangle(378, 4, 67, 24));
    jbLoadProject.setText("Load");
    jbLoadProject.addActionListener(new TitrationUI_jbLoadProject_actionAdapter(this));
    jLabel3.setText("Project Name");
    jLabel3.setBounds(new Rectangle(5, 64, 87, 21));
    jLabel4.setText("Description");
    jLabel4.setBounds(new Rectangle(5, 183, 93, 21));
    jLabel5.setText("Requestor");
    jLabel5.setBounds(new Rectangle(5, 93, 87, 21));
    jLabel6.setText("Request Date");
    jLabel6.setBounds(new Rectangle(288, 64, 81, 21));
    jLabel7.setText("Start Date");
    jLabel7.setBounds(new Rectangle(5, 121, 87, 21));
    jLabel8.setText("Status");
    jLabel8.setBounds(new Rectangle(288, 93, 83, 21));
    jLabel9.setText("Total Hours");
    jLabel9.setBounds(new Rectangle(288, 121, 83, 21));
    jLabel10.setText("Close Date");
    jLabel10.setBounds(new Rectangle(5, 149, 87, 21));
    jScrollPane2.setBounds(new Rectangle(5, 210, 513, 54));
    jScrollPane3.setBounds(new Rectangle(5, 299, 513, 88));
    jLabel11.setText("User Comments");
    jLabel11.setBounds(new Rectangle(5, 274, 93, 21));
    jtDesc.setText("");
    jbNewProject.setBounds(new Rectangle(379, 32, 139, 24));
    jbNewProject.setText("Add New");
    jbNewProject.addActionListener(new TitrationUI_jbNewProject_actionAdapter(this));
    jcStatus.setEnabled(false);
    jcStatus.setEditable(false);
    jcStatus.setModel(statusModel);
    jcStatus.setBounds(new Rectangle(375, 93, 139, 23));
    jbAddNewComment.setBounds(new Rectangle(120, 272, 160, 24));
    jbAddNewComment.setEnabled(false);
    jbAddNewComment.setText("Add New Comment");
    jbAddNewComment.addActionListener(new TitrationUI_jbAddNewComment_actionAdapter(this));
    jbSaveProject.setBounds(new Rectangle(451, 4, 67, 24));
    jbSaveProject.setEnabled(false);
    jbSaveProject.setText("Save");
    jbSaveProject.addActionListener(new TitrationUI_jbSaveProject_actionAdapter(this));
    jtReqestor.setBounds(new Rectangle(94, 93, 174, 20));
    jtProjectName.setBounds(new Rectangle(94, 64, 174, 20));
    jlPriority.setText("1");
    jlPriority.setBounds(new Rectangle(94, 36, 31, 20));
    jLabel12.setText("Priority");
    jLabel12.setBounds(new Rectangle(5, 36, 87, 21));
    jbReqDate.setBounds(new Rectangle(376, 64, 134, 24));
    jbReqDate.setEnabled(false);
    jbReqDate.setText("n/a");
    jtTotalHours.setText("0");
    jtTotalHours.setBounds(new Rectangle(375, 121, 46, 20));
    jtTotalHours.addFocusListener(new TitrationUI_jtTotalHours_focusAdapter(this));
    jbStartDate.setBounds(new Rectangle(94, 121, 134, 24));
    jbStartDate.setEnabled(false);
    jbStartDate.setText("n/a");
    jbCloseDate.setBounds(new Rectangle(94, 149, 134, 24));
    jbCloseDate.setEnabled(false);
    jbCloseDate.setText("n/a");
    jtComments.addMouseListener(new TitrationUI_jtComments_mouseAdapter(this));
    jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel13.setText("Project ID: ");
    jLabel13.setBounds(new Rectangle(159, 36, 82, 21));
    jlProjectID.setBounds(new Rectangle(249, 36, 82, 21));
    jmRefreshProj.setActionCommand("Refresh Project List");
    jmRefreshProj.setText("Refresh Project List");
    jmRefreshProj.addActionListener(new TitrationUI_jmRefreshProj_actionAdapter(this));
    jtProjectName.setBounds(new Rectangle(94, 64, 174, 20));
    jtProjectName.setBounds(new Rectangle(94, 64, 174, 20));
    jpMainNorth.setLayout(mainPriorityFlow);
    mainPriorityFlow.setAlignment(FlowLayout.RIGHT);
    jbShowAll.setText("Show All");
    jbShowAll.addActionListener(new TitrationUI_jbShowAll_actionAdapter(this));
    jbHide.setText("Hide");
    jbHide.addActionListener(new TitrationUI_jbHide_actionAdapter(this));
    adminPanel.setLayout(null);
    jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel14.setText("Existing Users");
    jLabel14.setBounds(new Rectangle(8, 7, 113, 23));
    jcAdminUserList.setModel(userComboList);
    jcAdminUserList.setBounds(new Rectangle(126, 7, 179, 23));
    jcAdminUserList.addItemListener(new TitrationUI_jcAdminUserList_itemAdapter(this));
    jbAdminLoad.setBounds(new Rectangle(331, 7, 75, 24));
    jbAdminLoad.setText("Load");
    jbAdminLoad.addActionListener(new TitrationUI_jbAdminLoad_actionAdapter(this));
    jbAdminAddPrj.setBounds(new Rectangle(217, 163, 88, 24));
    jbAdminAddPrj.setEnabled(false);
    jbAdminAddPrj.setText("Add");
    jbAdminAddPrj.addActionListener(new TitrationUI_jbAdminAddPrj_actionAdapter(this));
    jbAdminRefresh.setBounds(new Rectangle(411, 7, 106, 24));
    jbAdminRefresh.setText("Refresh");
    jbAdminRefresh.addActionListener(new TitrationUI_jbAdminRefresh_actionAdapter(this));
    jScrollPane4.setBounds(new Rectangle(312, 163, 205, 166));
    jLabel15.setText("Assigned Projects");
    jLabel15.setBounds(new Rectangle(312, 138, 124, 22));
    jLabel16.setText("Available Projects");
    jLabel16.setBounds(new Rectangle(6, 138, 124, 22));
    jScrollPane5.setBounds(new Rectangle(6, 163, 205, 166));
    jLabel17.setForeground(Color.lightGray);
    jLabel17.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel17.setText("Username");
    jLabel17.setBounds(new Rectangle(57, 45, 64, 20));
    jLabel18.setForeground(Color.lightGray);
    jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel18.setText("Password");
    jLabel18.setBounds(new Rectangle(330, 45, 68, 20));
    jLabel19.setForeground(Color.lightGray);
    jLabel19.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel19.setText("Display Name");
    jLabel19.setBounds(new Rectangle(10, 74, 111, 20));
    jtUName.setEnabled(true);
    jtUName.setEditable(false);
    jtUName.setText("");
    jtUName.setBounds(new Rectangle(135, 45, 103, 20));
    jtDName.setEnabled(true);
    jtDName.setEditable(false);
    jtDName.setText("");
    jtDName.setBounds(new Rectangle(135, 74, 103, 20));
    jbCreateUser.setBounds(new Rectangle(252, 74, 109, 24));
    jbCreateUser.setText("Create User");
    jbCreateUser.addActionListener(new TitrationUI_jbCreateUser_actionAdapter(this));
    jpPWord.setText("");
    jpPWord.setBounds(new Rectangle(411, 45, 93, 20));
    userBkg.setBackground(Color.gray);
    userBkg.setBounds(new Rectangle(6, 40, 511, 66));
    jbAdminSaveNewUser.setBounds(new Rectangle(367, 74, 137, 24));
    jbAdminSaveNewUser.setEnabled(false);
    jbAdminSaveNewUser.setText("Save New User");
    jbAdminRemovePrj.setBounds(new Rectangle(217, 190, 88, 24));
    jbAdminRemovePrj.setEnabled(false);
    jbAdminRemovePrj.setText("Remove");
    jbAdminRemovePrj.addActionListener(new TitrationUI_jbAdminRemovePrj_actionAdapter(this));
    jbAdminRestPWord.setBounds(new Rectangle(380, 111, 137, 24));
    jbAdminRestPWord.setEnabled(false);
    jbAdminRestPWord.setText("Reset Password");
    jbAdminRestPWord.addActionListener(new TitrationUI_jbAdminRestPWord_actionAdapter(this));
    jlAdminAvailable.setModel(adminAvailable);
    jlAdminAvailable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jlAdminAssigned.setModel(adminAssigned);
    jlAdminAssigned.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jmChangePassword.setText("Change Password");
    jmChangePassword.addActionListener(new TitrationUI_jmChangePassword_actionAdapter(this));
    jmOptions.setText("Options");
    jbFilter.setText("Filter");
    jbFilter.addActionListener(new TitrationUI_jbFilter_actionAdapter(this));
    jcFilter.setModel(filterModel);
    jbAssume.setBounds(new Rectangle(177, 111, 200, 24));
    jbAssume.setEnabled(false);
    jbAssume.setText("Assume User Identity");
    jbAssume.addActionListener(new TitrationUI_jbAssume_actionAdapter(this));
    jbBecomeAdmin.setBounds(new Rectangle(6, 111, 166, 24));
    jbBecomeAdmin.setEnabled(false);
    jbBecomeAdmin.setText("Assume Administrator");
    jbBecomeAdmin.addActionListener(new TitrationUI_jbBecomeAdmin_actionAdapter(this));
    jmiRunReport.setText("Run Report");
    jmiRunReport.addActionListener(new TitrationUI_jmiRunReport_actionAdapter(this));
    jLabel20.setHorizontalAlignment(SwingConstants.TRAILING);
    jLabel20.setText("Completed");
    jLabel20.setBounds(new Rectangle(286, 149, 75, 21));
    pctComplete.setBounds(new Rectangle(375, 147, 141, 57));
    pctComplete.setMajorTickSpacing(20);
    pctComplete.setMinorTickSpacing(5);
    pctComplete.setSnapToTicks(true);
    pctComplete.addChangeListener(new TitrationUI_pctComplete_changeAdapter(this));
    pctComplete.setPaintTicks(true);
    pctComplete.setPaintLabels(true);

    jlPctCmplt.setFont(new java.awt.Font("Dialog", 1, 11));
    jlPctCmplt.setForeground(Color.blue);
    jlPctCmplt.setHorizontalAlignment(SwingConstants.TRAILING);
    jlPctCmplt.setText("0 %");
    jlPctCmplt.setBounds(new Rectangle(242, 149, 48, 21));
    jbPctPlus.setBounds(new Rectangle(317, 171, 44, 24));
    jbPctPlus.setText("+");
    jbPctPlus.addActionListener(new TitrationUI_jbPctPlus_actionAdapter(this));
    jbCmpMinus.setBounds(new Rectangle(269, 171, 44, 24));
    jbCmpMinus.setText("-");
    jbCmpMinus.addActionListener(new TitrationUI_jbCmpMinus_actionAdapter(this));
    projectPanel.setLayout(null);
    jcProjectList2.setModel(projectComboList);
    jcProjectList2.setBounds(new Rectangle(102, 4, 254, 21));
    jcProjectList2.addItemListener(new TitrationUI_jcProjectList2_itemAdapter(this));
    jlProjList2.setBounds(new Rectangle(7, 4, 93, 21));
    jlProjList2.setText("Project Listings");
    jbLoadProject2.setBounds(new Rectangle(362, 4, 63, 24));
    jbLoadProject2.setText("Load");
    jbLoadProject2.addActionListener(new TitrationUI_jbLoadProject2_actionAdapter(this));
    jspProjectTree.setBounds(new Rectangle(7, 69, 515, 313));
    jbLoadAllProject.setBounds(new Rectangle(428, 4, 94, 24));
    jbLoadAllProject.setText("Load All");
    jbLoadAllProject.addActionListener(new TitrationUI_jbLoadAllProject_actionAdapter(this));
    jcUserList.setModel(userComboList);
    jcUserList.setBounds(new Rectangle(102, 36, 254, 21));
    jcUserList.addItemListener(new TitrationUI_jcUserList_itemAdapter(this));
    jLabel2.setText("User Listings");
    jLabel2.setBounds(new Rectangle(7, 36, 93, 21));
    jbLoadAllUsers.setBounds(new Rectangle(428, 36, 94, 24));
    jbLoadAllUsers.setText("Load All");
    jbLoadAllUsers.addActionListener(new TitrationUI_jbLoadAllUsers_actionAdapter(this));
    jbLoadUser.setBounds(new Rectangle(362, 36, 63, 24));
    jbLoadUser.setText("Load");
    jbLoadUser.addActionListener(new TitrationUI_jbLoadUser_actionAdapter(this));
    projectTree.setFont(new java.awt.Font("Monospaced", 0, 11));
    jmiexportOverview.setText("Export Overview");
    jmiexportOverview.addActionListener(new TitrationUI_jmiExportOverview_actionAdapter(this));
    jmiPrintOverview.setText("Print Overview");
    jmiPrintOverview.addActionListener(new TitrationUI_jmiPrintOverview_actionAdapter(this));
    jmiSaveOverviewText.setText("Save Overview as Text");
    jmiSaveOverviewText.addActionListener(new TitrationUI_jmiSaveOverviewText_actionAdapter(this));
    jmOverview.setText("Overview");
    jmiExportXML.setText("Export To XML");
    jmiExportXML.addActionListener(new TitrationUI_jmiExportXML_actionAdapter(this));
    jmExportHtml.setToolTipText("");
    jmExportHtml.setText("Export to HTML");
    jmExportHtml.addActionListener(new TitrationUI_jmExportHtml_actionAdapter(this));
    jlProjectList.addMouseListener(new TitrationUI_jlProjectList_mouseAdapter(this));
    jlProjectList.addMouseMotionListener(new TitrationUI_jlProjectList_mouseMotionAdapter(this));
    jbClearHidden.setText("Clear Hidden");
    jbClearHidden.addActionListener(new TitrationUI_jbClearHidden_actionAdapter(this));
    jbRefreshProject.setText("Refresh");
    jbRefreshProject.addActionListener(new TitrationUI_jbRefreshProject_actionAdapter(this));
    jmOptions.add(jmChangePassword);
    jmFile.add(jmiRunReport);
    jmOverview.add(jmiPrintOverview);
    jmOverview.add(jmiexportOverview);
    jmOverview.add(jmiSaveOverviewText);
    jmOverview.add(jmiExportXML);
    jmOverview.add(jmExportHtml);
    jmFile.add(jmRefreshProj);
    jmFile.addSeparator();
    jmFile.add(jMenuFileExit);
//-------- ADD MENUS TO MENU-BAR
    jMenuBar1.add(jmFile);
    jMenuBar1.add(jmOverview);
    jMenuBar1.add(jmOptions);
    jMenuBar1.add(jmLANDF);
    jMenuBar1.add(jMenuHelp);
//------------------------------
    jMenuHelp.add(jMenuHelpAbout);
    setJMenuBar(jMenuBar1);
    jmLANDF.add(jrbMetal);
    jmLANDF.add(jrbSystem);
    jmLANDF.add(jrbGTK);
    jmLANDF.add(jrbCrossPlatform);
    jmLANDF.add(jrbMotif);
    this.getContentPane().add(mainPanel, BorderLayout.CENTER);
    mainPanel.add(masterTabPane,  BorderLayout.CENTER);
    masterTabPane.add(mainTabPanel,   "Projects");
    mainTabPanel.add(jScrollPane1,  BorderLayout.CENTER);
    mainTabPanel.add(jpMainNorth, BorderLayout.NORTH);
    jScrollPane1.getViewport().add(jlProjectList, null);
    mainPanel.add(northPanel, BorderLayout.NORTH);
    mainPanel.add(jlSouth,  BorderLayout.SOUTH);
    masterTabPane.add(selectProjectPanel,   "Manage Projects");
    selectProjectPanel.add(projectPanelCenter,  BorderLayout.CENTER);
    northPanel.add(jLabel1, null);
    northPanel.add(jlUser, null);
    projectPanelCenter.add(jcProjectList, null);
    projectPanelCenter.add(jlProjList, null);
    projectPanelCenter.add(jbSaveProject, null);
    projectPanelCenter.add(jbNewProject, null);
    projectPanelCenter.add(jbLoadProject, null);
    projectPanelCenter.add(jbAddNewComment, null);
    projectPanelCenter.add(jScrollPane2, null);
    projectPanelCenter.add(jScrollPane3, null);
    jScrollPane3.getViewport().add(jtComments, null);
    projectPanelCenter.add(jLabel4, null);
    projectPanelCenter.add(jLabel11, null);
    projectPanelCenter.add(jLabel9, null);
    projectPanelCenter.add(jLabel8, null);
    projectPanelCenter.add(jcStatus, null);
    projectPanelCenter.add(jLabel6, null);
    projectPanelCenter.add(jlPriority, null);
    projectPanelCenter.add(jLabel12, null);
    projectPanelCenter.add(jLabel3, null);
    projectPanelCenter.add(jtReqestor, null);
    projectPanelCenter.add(jLabel5, null);
    projectPanelCenter.add(jLabel7, null);
    projectPanelCenter.add(jLabel10, null);
    projectPanelCenter.add(jbReqDate, null);
    jScrollPane2.getViewport().add(jtDesc, null);
    projectPanelCenter.add(jtTotalHours, null);
    projectPanelCenter.add(jbStartDate, null);
    projectPanelCenter.add(jbCloseDate, null);
    projectPanelCenter.add(jLabel13, null);
    projectPanelCenter.add(jlProjectID, null);
    projectPanelCenter.add(jtProjectName, null);
    projectPanelCenter.add(jLabel20, null);
    projectPanelCenter.add(pctComplete, null);
    projectPanelCenter.add(jbPctPlus, null);
    projectPanelCenter.add(jlPctCmplt, null);
    projectPanelCenter.add(jbCmpMinus, null);
    masterTabPane.add(projectPanel,    "Project Overview");
    projectPanel.add(jcProjectList2, null);
//  //Comment/Uncomment this line to add the Admin panel for coding and testing....
//    masterTabPane.add(adminPanel,  "Administration");
    jpMainNorth.add(jcFilter, null);
    jpMainNorth.add(jbFilter, null);
    jpMainNorth.add(jbShowAll, null);
    jpMainNorth.add(jbHide, null);
    jpMainNorth.add(jbClearHidden, null);
    jpMainNorth.add(jbRefreshProject, null);
    adminPanel.add(jLabel14, null);
    adminPanel.add(jbAdminRefresh, null);
    adminPanel.add(jcAdminUserList, null);
    adminPanel.add(jLabel17, null);
    adminPanel.add(jLabel19, null);
    adminPanel.add(jtDName, null);
    adminPanel.add(jpPWord, null);
    adminPanel.add(jLabel18, null);
    adminPanel.add(jtUName, null);
    adminPanel.add(jbAdminAddPrj, null);
    adminPanel.add(jScrollPane5, null);
    jScrollPane5.getViewport().add(jlAdminAvailable, null);
    adminPanel.add(jScrollPane4, null);
    adminPanel.add(jLabel16, null);
    adminPanel.add(jbAdminLoad, null);
    adminPanel.add(jLabel15, null);
    adminPanel.add(jbAdminRemovePrj, null);
    adminPanel.add(jbAdminRestPWord, null);
    adminPanel.add(jbAdminSaveNewUser, null);
    adminPanel.add(jbCreateUser, null);
    adminPanel.add(userBkg, null);
    adminPanel.add(jbAssume, null);
    adminPanel.add(jbBecomeAdmin, null);
    jScrollPane4.getViewport().add(jlAdminAssigned, null);
    projectPanel.add(jlProjList2, null);
    projectPanel.add(jspProjectTree, null);
    projectPanel.add(jbLoadAllProject, null);
    projectPanel.add(jbLoadProject2, null);
    projectPanel.add(jcUserList, null);
    projectPanel.add(jLabel2, null);
    projectPanel.add(jbLoadAllUsers, null);
    projectPanel.add(jbLoadUser, null);
    projectTree.setShowsRootHandles(true);
    jspProjectTree.getViewport().add(projectTree, null);
//------
    jlProjectList.setModel(plModel);
//    jlProjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jlProjectList.setDefaultEditor(String.class, new TableEditor());
    jlProjectList.getDefaultEditor(String.class).addCellEditorListener(this);
    jlProjectList.setDefaultRenderer(String.class, new TableRenderer());
    initProjectListColumnSizes(jlProjectList);
//------
    jtComments.setModel(cmntModel);
    jtComments.setDefaultEditor(String.class, new TableEditor());
    jtComments.getDefaultEditor(String.class).addCellEditorListener(this);
    jtComments.setDefaultRenderer(String.class, new TableRenderer());
    initCommentColumnSizes(jtComments);
    jbAdminSaveNewUser.addActionListener(new TitrationUI_jbAdminSaveNewUser_actionAdapter(this));


    projectBranch = new DefaultMutableTreeNode("Project1");
    projectChild = new DefaultMutableTreeNode("Assigned To: ??????");
    projectBranch.add(projectChild);
    projectChild = new DefaultMutableTreeNode("Prject Started: ??????");
    projectBranch.add(projectChild);
    projectChild = new DefaultMutableTreeNode("Completion %: ??????");
    projectBranch.add(projectChild);
    rootNode.add(projectBranch);

    projectBranch = new DefaultMutableTreeNode("Project2");
    projectChild = new DefaultMutableTreeNode("Assigned To: ??????");
    projectBranch.add(projectChild);
    projectChild = new DefaultMutableTreeNode("Prject Started: ??????");
    projectBranch.add(projectChild);
    projectChild = new DefaultMutableTreeNode("Completion %: ??????");
    projectBranch.add(projectChild);

    rootNode.add(projectBranch);

  }

  private void debugComment(){
    CommentItem ci = new CommentItem();
    ci.setComment("TESTCOMMENT");
    ci.setCommentBy("USER1");
    ci.setCommentid(99);
    ci.setDate("-DATE-");
    cmntModel.addRow(ci);
  }


  /**
   * File | Exit action performed.
   *
   * @param actionEvent ActionEvent
   */
  void jMenuFileExit_actionPerformed(ActionEvent actionEvent) {
    System.exit(0);
  }

  /**
   * Help | About action performed.
   *
   * @param actionEvent ActionEvent
   */
  void jMenuHelpAbout_actionPerformed(ActionEvent actionEvent) {
    AboutDialog dlg = new AboutDialog(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
  }

  private void initModels() throws Exception{
    Iterator itr = fetchStatusMap();
    filterModel.addElement("No Filter");
    while(itr.hasNext()){
      String item = (String)itr.next();
      statusModel.addElement(item);
      filterModel.addElement(item);
    }
    jcStatus.setSelectedIndex(0);
    jcFilter.setSelectedIndex(0);
    refreshUserList();
  }

  private void initProjectListColumnSizes(JTable table) {
    TableColumn column = table.getColumnModel().getColumn(0);
    column.setPreferredWidth(50);
    column.setMaxWidth(50);
    column.setMinWidth(50);
    column = table.getColumnModel().getColumn(1);
    column.setPreferredWidth(100);
    column.setMaxWidth(100);
    column.setMinWidth(100);
    column = table.getColumnModel().getColumn(2);
    column.setMaxWidth(65);
    column.setMinWidth(65);
    column = table.getColumnModel().getColumn(3);
    column.setPreferredWidth(250);
  }

  private void initCommentColumnSizes(JTable table) {
    TableColumn column = table.getColumnModel().getColumn(0);
    column.setPreferredWidth(100);
    column.setMaxWidth(100);
    column.setMinWidth(100);
    column = table.getColumnModel().getColumn(1);
    column.setPreferredWidth(100);
    column.setMaxWidth(100);
    column.setMinWidth(100);
    column = table.getColumnModel().getColumn(2);
    column.setPreferredWidth(250);
  }

//########## LOOK AND FEEL ###########
  void swapLookAndFeel(String lookandfeel) {
    jrbMetal.setSelected(false);
    jrbSystem.setSelected(false);
    jrbGTK.setSelected(false);
    jrbCrossPlatform.setSelected(false);
    jrbMotif.setSelected(false);

    String lookAndFeelClass = UIManager.getSystemLookAndFeelClassName();
    if (lookandfeel == "Metal") {
      lookAndFeelClass = "javax.swing.plaf.metal.MetalLookAndFeel";
      jrbMetal.setSelected(true);
      prop.setProperty("interface", "Metal");
    } else if (lookandfeel == "System") {
      lookAndFeelClass = UIManager.getSystemLookAndFeelClassName();
      jrbSystem.setSelected(true);
      prop.setProperty("interface", "System");
    } else if (lookandfeel == "GTK") {
      lookAndFeelClass = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
      jrbGTK.setSelected(true);
      prop.setProperty("interface", "GTK");
    } else if (lookandfeel == "Cross Platform") {
      lookAndFeelClass = UIManager.getCrossPlatformLookAndFeelClassName();
      jrbCrossPlatform.setSelected(true);
      prop.setProperty("interface", "Cross Platform");
    } else if (lookandfeel == "Motif") {
      lookAndFeelClass = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
      jrbMotif.setSelected(true);
      prop.setProperty("interface", "Motif");
    }
    try {
      prop.store(new FileOutputStream("./conf/client.prop"), "Titration Look & Feel Properties");
    } catch (Exception ex) {}

    try {
      UIManager.setLookAndFeel(lookAndFeelClass);
      SwingUtilities.updateComponentTreeUI(this);
    } catch (Exception e) {
      e.printStackTrace(); }
  }

  public void setLookAndFeelID(int id) {
    jrbMetal.setSelected(false);
    if (id == 0)
      jrbMetal.setSelected(true);
    else if (id == 1)
      jrbSystem.setSelected(true);
    else if (id == 2)
      jrbGTK.setSelected(true);
    else if (id == 3)
      jrbCrossPlatform.setSelected(true);
    else if (id == 4)
      jrbMotif.setSelected(true);
  }

  void showCommentDialog(MouseEvent e){
    if(e.getClickCount() == 2){
      CommentDialog dlg = new CommentDialog(this,cmntModel.getSelectedComment(jtComments.getSelectedRow()));
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation( (frmSize.width - 450) / 2 + loc.x, (frmSize.height - 200) / 2 + loc.y);
      dlg.setModal(true);
      dlg.show();
    }
  }

  private void openDialog(String msg, int type) {
    MessageDialog dlg = new MessageDialog(this, msg,type);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

//************************************************//
//----------------- END OF CLASS -----------------//
//************************************************//
  public void editingStopped(ChangeEvent e) {}

  public void editingCanceled(ChangeEvent e) {}

  public boolean validateEdit(Object o, int row, int col) {
    return true; }


  boolean checkHourValue(FocusEvent e) {
    boolean failed = false;
    try {
      double thours = Double.parseDouble(jtTotalHours.getText());
      projectComboList.size();
      if(thours < 0){
        throw new Exception("TOTAL_HOUR VALUE FAILED");
      }
    }catch (Exception ex) {
      openDialog("Total Hours must be a valid number >= 0",MessageDialog.ERROR_DIALOG);
      jtTotalHours.setText(openItem.getHours() + "");
      this.requestFocus();
      failed = true;
    }
    return failed;
  }



  protected void login() {
//#---REMOVE ALL THIS COMMENTED SHIT FOR PRODUCTION---#

///*
    LoginDialog dlg = new LoginDialog(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
    try {
      loginItem = doLogin(dlg.getUser(), dlg.getPasswd());
//      loginItem.setuserName("IOExcept");
//      loginItem.setUserID(1);
//*/
      if(loginItem.getUserID() < 0) {
        login();
      }else{
        jlUser.setText(loginItem.getUserName());
        refreshProjectLists(false,0);
        if(loginItem.getUserID() == 0
           || loginItem.getUserID() == 1){
          IS_ADMIN = true;
          masterTabPane.add(adminPanel,  "Administration");
        }
        loadUserProjects(null,true);
      }
    } catch(Exception ex) {
      lman.log(ex);
    }
  }



  void showAll(ActionEvent e) {
    refreshProjectLists(false,1);
  }

  void showHideSelected(int showHide) {
    if(jlProjectList.getSelectedRows().length > 0){
      try {
        hideShowSelectedRows(loginItem.getUserID(), showHide, plModel.getProjectItems(jlProjectList.getSelectedRows()));
      } catch (Exception ex) {
        openDialog("There was an exception hiding/showing your projects", MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
      refreshProjectLists(false, 0);
    }
  }

  protected void refreshProjectLists(boolean filter, int hHidden){
    jlSouth.setText("Project Count: ");
    try {
      cleanProject();
      if(filter){
        int selFilter = jcFilter.getSelectedIndex() - 1;
        if(selFilter > -1){
          projectList = getProjectList(loginItem.getUserID(), (selFilter),hHidden);
        }else{
          projectList = getProjectList(loginItem.getUserID(),hHidden);
        }
      }else{
        projectList = getProjectList(loginItem.getUserID(),hHidden);
      }

      plModel.removeAll();
      projectComboList.removeAllElements();
      projectComboList.setSelectedItem("");
      Iterator itr = projectList.iterator();
      ProjectItem projItem = null;
      int projCounter = 0;
      while(itr.hasNext()){
        projItem = (ProjectItem)itr.next();
        plModel.addRow(projItem);
        projCounter++;
      }
      jlSouth.setText("Project Count: " + projCounter);
      itr = getSortedProjectList(loginItem.getUserID(),hHidden);
      while(itr.hasNext()){
        projectComboList.addElement((ProjectItem)itr.next());
      }

    }catch (Exception ex) {
      openDialog("There was an exception requesting the project list",MessageDialog.ERROR_DIALOG);
      lman.log(ex);
    }
  }

  private void enableButtons(boolean value){
    jbSaveProject.setEnabled(value);
    jbReqDate.setEnabled(value);
    jbStartDate.setEnabled(value);
    jbCloseDate.setEnabled(value);
    jbAddNewComment.setEnabled(value);
    jcStatus.setEnabled(value);
    pctComplete.setEnabled(value);
    jbCmpMinus.setEnabled(value);
    jbPctPlus.setEnabled(value);

  }
  private void cleanProject(){
    enableButtons(false);
    jbReqDate.setText("n/a");
    jbStartDate.setText("n/a");
    jbCloseDate.setText("n/a");
    jlPriority.setText("");
    jtProjectName.setText("");
    jtReqestor.setText("");
    jcStatus.setSelectedIndex(0);
    jtTotalHours.setText("0");
    jtDesc.setText("");
    jlProjectID.setText("");
    cmntModel.removeAll();
    jtProjectName.setEditable(true);
    jtReqestor.setEditable(true);
    jtDesc.setEditable(true);
    jlPctCmplt.setText("0%");
    pctComplete.setValue(0);
    cmntModel.removeAll();
//--------- PROJECT_TREE OBJECT MANAGEMENT
      this.invalidate();
      jspProjectTree.getViewport().remove(projectTree);
      jspProjectTree.getViewport().remove(projectTree);
      rootNode = new DefaultMutableTreeNode("Project Listings");
      projectTree = new JTree(rootNode);
      jspProjectTree.getViewport().add(projectTree, null);
      projectTree.setShowsRootHandles(true);
      projectTree.setFont(new java.awt.Font("Monospaced", 0, 11));
      this.validate();
      repaint();
  }

  void loadProjectOverview(ActionEvent e) {
    enableButtons(false);
    cleanProject();
    if (jcProjectList2.getSelectedIndex() > -1) {
      try{
        double value = 0;
        ProjectItem ovitem = (ProjectItem) jcProjectList2.getSelectedItem();
        projectBranch = new DefaultMutableTreeNode(ovitem.getName());
        projectBranch.add(new DefaultMutableTreeNode("Project ID        [" + ovitem.getProjectID() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Status            [" + ovitem.getStatusLabel() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Assign-Date       [" + ovitem.getReqDate() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Start-Date        [" + ovitem.getStartDate() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Close-Date        [" + ovitem.getCloseDate() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Requested By      [" + ovitem.getReqestor() + "]"));
        Iterator pdevpitr = getProjectDevs(ovitem.getProjectID()).iterator();
        ProjectDetailItem pdi = null;
        int counter = 0;
        int totalHours = 0;
        int totalComplete = 0;
        ArrayList container = new ArrayList();
        while (pdevpitr.hasNext()) {
          pdi = (ProjectDetailItem) pdevpitr.next();
          userBranch = new DefaultMutableTreeNode(pdi.getUserName());
          userBranch.add(new DefaultMutableTreeNode("Project Order [" + pdi.getProjectOrder() + "]"));
          userBranch.add(new DefaultMutableTreeNode("% Completed   [" + pdi.getPctComplete() + "%]"));
          userBranch.add(new DefaultMutableTreeNode("Project Hours [" + pdi.getProjectHours() + "]"));
          totalHours+=pdi.getProjectHours();
          totalComplete+=pdi.getPctComplete();
          container.add(userBranch);
          counter++;
        } //end while
        if(counter > 0){
          value = new Double(totalComplete).doubleValue() / (new Double(counter).doubleValue() * 100d);
        }else
          value = 0;
        projectBranch.add(new DefaultMutableTreeNode("Total Hours       [" + totalHours + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Total % Completed [" + pctFormat.format(value) + "]"));
        projectBranch.add(new DefaultMutableTreeNode("X===== Developers Assigned to this project =====X"));
        Iterator contItr = container.iterator();
        while(contItr.hasNext()){
          projectBranch.add( (DefaultMutableTreeNode)contItr.next());
        }
        rootNode.add(projectBranch);
      }catch(Exception ex){
        openDialog("There was an exception requesting the project detail",MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
    }
    repaint();
  }

  void loadUserProjects(ActionEvent e, boolean isOnLoad) {
    int selectedUserIndex = jcUserList.getSelectedIndex();
    UserItem userProject = null;
    try {
      if (isOnLoad) {
        userProject = fetchUserItem(loginItem.getUserID());
      } else {
        userProject = (UserItem) jcUserList.getSelectedItem();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

   if (userProject != null) {
     enableButtons(false);
     cleanProject();

      this.invalidate();
      userBranch = new DefaultMutableTreeNode(userProject.getUname());
      try{
        Iterator itr = getUserProjectDetail(userProject.getUserid()).iterator();
        ProjectDetailItem pdi = null;
        while(itr.hasNext()){
          pdi = (ProjectDetailItem)itr.next();
          projectBranch = new DefaultMutableTreeNode(pdi.getProjectName());
          projectBranch.add(new DefaultMutableTreeNode("Project ID          [" + pdi.getProjectID() + "]"));
          projectBranch.add(new DefaultMutableTreeNode("Project Order       [" + pdi.getProjectOrder() + "]"));
          projectBranch.add(new DefaultMutableTreeNode("Project Hours       [" + pdi.getProjectHours() + "]"));
          projectBranch.add(new DefaultMutableTreeNode("Project % Completed [" + pdi.getPctComplete() + "%]"));
          projectBranch.add(new DefaultMutableTreeNode("Project Status      [" + pdi.getProjectStatus() + "]"));
          userBranch.add(projectBranch);
        }
      }catch(Exception ex){
        openDialog("There was an exception requesting the users project list",MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
      rootNode.add(userBranch);
      projectTree.setShowsRootHandles(true);
      projectTree.repaint();
      this.validate();
      repaint();
    }
  }

  void loadAllProjects(ActionEvent e) {
    this.invalidate();
    enableButtons(false);
    cleanProject();
    try {jcProjectList2.setSelectedIndex(-1);}catch (Exception ex) {}
    try {jcUserList.setSelectedIndex(-1);}catch (Exception ex) {}
    try {
      double value = 0;
      Iterator projMasterList = getProjectMasterList().iterator();
      ProjectItem piProject = null;
      ProjectDetailItem pdi = null;
      Iterator pdevpitr = null;
      int counter = 0;
      int totalHours = 0;
      int totalComplete = 0;
      ArrayList container = new ArrayList();
      while(projMasterList.hasNext()){
        piProject = (ProjectItem)projMasterList.next();
        projectBranch = new DefaultMutableTreeNode(piProject.getName());
        projectBranch.add(new DefaultMutableTreeNode("Project ID        [" + piProject.getProjectID() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Status            [" + piProject.getStatusLabel() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Assign-Date       [" + piProject.getReqDate() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Start-Date        [" + piProject.getStartDate() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Close-Date        [" + piProject.getCloseDate() + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Requested By      [" + piProject.getReqestor() + "]"));
        pdevpitr = getProjectDevs(piProject.getProjectID()).iterator();

        counter = 0;
        totalHours = 0;
        totalComplete = 0;
        container.clear();
        while (pdevpitr.hasNext()) {
          pdi = (ProjectDetailItem) pdevpitr.next();
          userBranch = new DefaultMutableTreeNode(pdi.getUserName());
          userBranch.add(new DefaultMutableTreeNode("Project Order [" + pdi.getProjectOrder() + "]"));
          userBranch.add(new DefaultMutableTreeNode("% Completed   [" + pdi.getPctComplete() + "%]"));
          userBranch.add(new DefaultMutableTreeNode("Project Hours [" + pdi.getProjectHours() + "]"));
          totalHours += pdi.getProjectHours();
          totalComplete += pdi.getPctComplete();
          container.add(userBranch);
          counter++;
        } //end while
        if(counter > 0){
          value = new Double(totalComplete).doubleValue() / (new Double(counter).doubleValue() * 100d);
        }else
          value = 0;
        projectBranch.add(new DefaultMutableTreeNode("Total Hours       [" + totalHours + "]"));
        projectBranch.add(new DefaultMutableTreeNode("Total % Completed [" + pctFormat.format(value) + "]"));
        projectBranch.add(new DefaultMutableTreeNode("X===== Developers Assigned to this project =====X"));
        Iterator contItr = container.iterator();
        while (contItr.hasNext()) {
          projectBranch.add( (DefaultMutableTreeNode) contItr.next());
        }//end while
        rootNode.add(projectBranch);
      }//end-while
    } catch (Exception ex) {
      openDialog("There was an exception requesting the project detail", MessageDialog.ERROR_DIALOG);
      lman.log(ex);
    }
    projectTree.setShowsRootHandles(true);
    this.validate();
    repaint();
  }

  void loadAllUserProjects(ActionEvent e) {
    this.invalidate();
    enableButtons(false);
    cleanProject();
    projectTree.setFont(new java.awt.Font("Monospaced", 0, 11));
    try{
      Iterator masterItr = getUserMasterList().iterator();
      Iterator itr = null;
      ProjectDetailItem pdi = null;
      ProjectDetailItem mpdi = null;
      while(masterItr.hasNext()){
        mpdi =(ProjectDetailItem) masterItr.next();
        itr = getUserProjectDetail(mpdi.getUserID()).iterator();
        userBranch = new DefaultMutableTreeNode(mpdi.getUserName());
        while (itr.hasNext()) {
          pdi = (ProjectDetailItem) itr.next();
          projectBranch = new DefaultMutableTreeNode(pdi.getProjectName());
          projectBranch.add(new DefaultMutableTreeNode("Project ID          [" + pdi.getProjectID() + "]"));
          projectBranch.add(new DefaultMutableTreeNode("Project Order       [" + pdi.getProjectOrder() + "]"));
          projectBranch.add(new DefaultMutableTreeNode("Project Hours       [" + pdi.getProjectHours() + "]"));
          projectBranch.add(new DefaultMutableTreeNode("Project % Completed [" + pdi.getPctComplete() + "%]"));
          projectBranch.add(new DefaultMutableTreeNode("Project Status      [" + pdi.getProjectStatus() + "]"));
          userBranch.add(projectBranch);
        }
        rootNode.add(userBranch);
      }
    }catch(Exception ex){
      openDialog("There was an exception requesting the users project list",MessageDialog.ERROR_DIALOG);
      lman.log(ex);
    }

    projectTree.setShowsRootHandles(true);
    this.validate();
    repaint();
  }

  void clearProjectOverView(ItemEvent e) {
    enableButtons(false);
    cleanProject();
  }

  void clearProjectData(ItemEvent e) {
    if (jcProjectList.getSelectedIndex() > -1) {
      enableButtons(false);
      cleanProject();
    }
  }

  void loadExistingProject(ActionEvent e) {
    if(projectComboList.getSelectedItem() != null &&
          jcProjectList.getSelectedIndex() > -1){
//    if( ((ProjectItem)projectComboList.getSelectedItem()).getProjectID() > -1){
      IS_NEW = false;
      cleanProject();
      enableButtons(true);
      openItem = (ProjectItem)projectComboList.getSelectedItem();
      jlPriority.setText(openItem.getPriority() + "");
      jtDesc.setText(openItem.getDescription());
      jtDesc.setEditable(false);
      jtProjectName.setText(openItem.getName());
      jtProjectName.setEditable(false);
      jtReqestor.setText(openItem.getReqestor());
      jtReqestor.setEditable(false);
      jbReqDate.setText(openItem.getReqDate()==""?"n/a":openItem.getReqDate());
      jbReqDate.setEnabled(false);
      jcStatus.setSelectedIndex(openItem.getStatus());
      jlProjectID.setText(openItem.getProjectID()+ "");
      jbStartDate .setText(openItem.getStartDate()==""?"n/a":openItem.getStartDate());
      jbCloseDate.setText(openItem.getCloseDate()==""?"n/a":openItem.getCloseDate());
      refreshComments(openItem.getProjectID());
      jtTotalHours.setText(openItem.getHours() + "");
      jlPctCmplt.setText(openItem.getPctCompleted() + "%");
      pctComplete.setValue(openItem.getPctCompleted());
    }//end if
  }

  void addNewProject(ActionEvent e) {
    IS_NEW = true;
    cleanProject();
    enableButtons(true);
    jbAddNewComment.setEnabled(false);
    Calendar cal = Calendar.getInstance(Locale.US);
    jbReqDate.setText((cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.YEAR));
    jbReqDate.setEnabled(true);
    jlPriority.setText(projectComboList.size() + 1 + "");
    projectComboList.setSelectedItem("");
  }

  void saveOpenProject(ActionEvent e) {
    boolean didFail = false;
    String projectName = jtProjectName.getText().trim();

    if(checkHourValue(null)){
      didFail = true;
    }
    try{
      if(IS_NEW){
        if(projectName.length() < 3 || projectName.length() > 32){
          openDialog("Project Names must be -greater- than 3 and -less- than 32 characters",MessageDialog.ERROR_DIALOG);
          didFail = true;
        }
        if(jtReqestor.getText().trim().length() < 3 || jtReqestor.getText().trim().length() > 32){
          openDialog("Project Requestor must be -greater- than 3 and -less- than 32 characters",MessageDialog.ERROR_DIALOG);
          didFail = true;
        }
        if(jtDesc.getText().trim().length() > 255){
          openDialog("Project Description must be -less- than 255 characters",MessageDialog.ERROR_DIALOG);
          didFail = true;
        }
        if (projectNameOnfile(projectName)) {
          openDialog("Project Name [" + projectName + "] is already on file",MessageDialog.ERROR_DIALOG);
          didFail = true;
        }
        if(!didFail){
          openItem = new ProjectItem();
          openItem.setPriority(Integer.parseInt(jlPriority.getText()));
          openItem.setProjectID(-1);
          openItem.setName(projectName);
          openItem.setReqestor(jtReqestor.getText().trim());
          openItem.setStartDate(jbStartDate.getText());
          openItem.setCloseDate(jbCloseDate.getText());
          openItem.setReqDate(jbReqDate.getText());
          openItem.setStatus(jcStatus.getSelectedIndex());
          openItem.setHours(Integer.parseInt(jtTotalHours.getText()));
          openItem.setDescription(jtDesc.getText().trim());
          openItem.setPctCompleted(pctComplete.getValue());
          saveNewProject(openItem,loginItem.getUserID());
          refreshProjectLists(false,0);
        }
      }else{
        openItem.setStartDate(jbStartDate.getText());
        openItem.setCloseDate(jbCloseDate.getText());
        openItem.setStatus(jcStatus.getSelectedIndex());
        openItem.setHours(Integer.parseInt(jtTotalHours.getText()));
        openItem.setPctCompleted(pctComplete.getValue());
        updateExistingProject(openItem,loginItem.getUserID());
        refreshProjectLists(false,0);
      }//end if -IS_NEW-
    }catch(Exception ex){
      openDialog("An excpetion has occurred validating the save values",MessageDialog.ERROR_DIALOG);
    lman.log(ex);
    }
  }



  public void refreshComments(int projectID){
    try {
      cmntModel.removeAll();
      Iterator iter = fetchNotes(projectID).iterator();
      CommentItem ci = null;
      while(iter.hasNext()){
        cmntModel.addRow((CommentItem)iter.next());

      }
    }catch (Exception ex) {
      openDialog("Error refreshing comment list",MessageDialog.ERROR_DIALOG);
      lman.log(ex);
    }
  }

  void addNewCommentDialog(){
    AddCommentDialog dlg = new AddCommentDialog(this,loginItem.getUserName());
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - 450) / 2 + loc.x, (frmSize.height - 200) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
    if(dlg.doSave()){
      try {
        addNotes(dlg.getDate(),dlg.getComment(),loginItem.getUserID(),openItem.getProjectID());
        refreshComments(openItem.getProjectID());
      }catch (Exception ex) {
        openDialog("There was an error saving your comment",MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
    }
  }


//  void movePriorityUp(ActionEvent e) {
//    if(jlProjectList.getSelectedRow() > 0){
//      try {
//        int setRow = jlProjectList.getSelectedRow();
//        updatePriority(loginItem.getUserID(),plModel.moveUp(jlProjectList.getSelectedRow()));
//        refreshProjectLists(false,0);
//      }catch (Exception ex) {
//        openDialog("There has been an error altering the priority of the selected project",MessageDialog.ERROR_DIALOG);
//        lman.log(ex);
//      }
//    }
//  }

//  void movePriorityDown(ActionEvent e) {
//    if(jlProjectList.getSelectedRow() > -1 &&
//       jlProjectList.getSelectedRow() < (plModel.getSize() - 1)){
//      try {
//        updatePriority(loginItem.getUserID(),plModel.moveDown(jlProjectList.getSelectedRow()));
//        refreshProjectLists(false,0);
//      }catch (Exception ex) {
//        openDialog("There has been an error altering the priority of the selected project",MessageDialog.ERROR_DIALOG);
//        lman.log(ex);
//      }
//    }
//  }

//**************** USER CONTROL **********************
  private void refreshUserList(){
    try {
      userComboList.removeAllElements();
      Iterator itr = fetchUserList().iterator();
      while(itr.hasNext()){
        userComboList.addElement((UserItem)itr.next());
      }
    }catch (Exception ex) {
      openDialog("Failed to initialize User List",MessageDialog.ERROR_DIALOG);
      lman.log(ex);
    }
  }


  void createNewUser(ActionEvent e) {
    jcAdminUserList.setSelectedIndex(-1);
    adminAvailable.removeAllElements();
    adminAssigned.removeAllElements();
    jpPWord.setText("");
    jtDName.setText("");
    jtUName.setText("");
    jtDName.setEditable(true);
    jtUName.setEditable(true);
    jbAdminSaveNewUser.setEnabled(true);
    jbAdminRestPWord.setEnabled(false);
    jbAdminAddPrj.setEnabled(false);
    jbAssume.setEnabled(false);
    jbAdminRemovePrj.setEnabled(false);
  }

  void loadUserData(ActionEvent e) {
    if(jcAdminUserList.getSelectedIndex() > -1){
      try{
        jtDName.setEditable(false);
        jtUName.setEditable(false);
        jbAdminAddPrj.setEnabled(true);
        jbAssume.setEnabled(true);
        jbAdminRemovePrj.setEnabled(true);
        jbAdminRestPWord.setEnabled(true);
        jpPWord.setEditable(true);
        jbAdminSaveNewUser.setEnabled(false);
        UserItem ui = (UserItem) jcAdminUserList.getSelectedItem();
        adminAssigned.removeAllElements();
        Object container[] = fetchProjectLists(ui.getUserid());

        Iterator itr = ((ArrayList)container[0]).iterator();
        while (itr.hasNext()) {
          adminAssigned.addElement((ProjectItem)itr.next());
        }
        adminAvailable.removeAllElements();
        itr = ((ArrayList)container[1]).iterator();
        while (itr.hasNext()) {
         adminAvailable.addElement((ProjectItem)itr.next());
       }
     }catch(Exception ex){
        openDialog("There was an error loading user data",MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
    }
  }

  void refreshUserList(ActionEvent e) {
    jcAdminUserList.setSelectedIndex(-1);
    userComboList.removeAllElements();
    adminAvailable.removeAllElements();
    adminAssigned.removeAllElements();
    jtDName.setEditable(false);
    jtUName.setEditable(false);
    jpPWord.setEditable(false);
    jbAdminSaveNewUser.setEnabled(false);
    jbAdminAddPrj.setEnabled(false);
    jbAssume.setEnabled(false);
    jbAdminRemovePrj.setEnabled(false);
    jbAdminRestPWord.setEnabled(false);
    jpPWord.setText("");
    jtDName.setText("");
    jtUName.setText("");
    adminAvailable.removeAllElements();
    adminAssigned.removeAllElements();
    refreshUserList();
  }

  void saveNewUser(ActionEvent e) {
    boolean error = false;
    String uname = jtUName.getText();
    String dname = jtDName.getText();
    String pword = new String(jpPWord.getPassword());
    if(uname.length() < 6 && uname.length() > 12){
      openDialog("User name -must be- between 6 and 12 characters",MessageDialog.ERROR_DIALOG);
      error = true;
    }
    if(dname.length() < 6 || dname.length() > 32){
      openDialog("Display Name -must be- between 6 and 32 characters",MessageDialog.ERROR_DIALOG);
      error = true;
    }

    if(pword.length() < 6 || pword.length() > 12){
      openDialog("Password -must be- between 6 and 12 characters",MessageDialog.ERROR_DIALOG);
      error = true;
    }
    if(!error){
      try {
        if(addNewUser(uname,pword,dname)){
          openDialog("User [" + uname + "] already exist", MessageDialog.ERROR_DIALOG);
        }else{
          openDialog("New User [" + uname + "] added", MessageDialog.MESSAGE_DIALOG);
          refreshUserList(null);
        }
      }catch (Exception ex) {
        openDialog("Failed to add new user", MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
    }
  }

  void resetUserPassword(ActionEvent e) {
    if(jcAdminUserList.getSelectedIndex() > -1){
      try {
        UserItem ui = (UserItem) jcAdminUserList.getSelectedItem();
        resetPassword(ui.getUserid(), "pa$$word");
        openDialog("Password reset to pa$$word", MessageDialog.MESSAGE_DIALOG);
      } catch (Exception ex) {
        openDialog("Failed to reset password", MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
    }
  }

  void removeProjectFromUser(ActionEvent e) {
    try {
      if (jcAdminUserList.getSelectedIndex() > -1) {
        if(jlAdminAssigned.getSelectedIndex() > -1){
          ProjectItem pi = (ProjectItem) adminAssigned.get(jlAdminAssigned.getSelectedIndex());
          adminAvailable.addElement(pi);
          adminAssigned.remove(jlAdminAssigned.getSelectedIndex());
          UserItem ui = (UserItem) jcAdminUserList.getSelectedItem();
          super.updateUserProjects(ui.getUserid(), pi.getProjectID(), true);
          refreshProjectLists(false,0);
        }
      }
    } catch (Exception ex) {
      openDialog("There was an error updating this event",MessageDialog.ERROR_DIALOG);
      lman.log(ex);
    }

  }

  void addProjectFromUser(ActionEvent e) {
    try {
      if (jcAdminUserList.getSelectedIndex() > -1) {
        if (jlAdminAvailable.getSelectedIndex() > -1) {
          ProjectItem pi = (ProjectItem) adminAvailable.get(jlAdminAvailable.getSelectedIndex());
          adminAssigned.addElement(pi);
          adminAvailable.remove(jlAdminAvailable.getSelectedIndex());
          UserItem ui = (UserItem) jcAdminUserList.getSelectedItem();
          super.updateUserProjects(ui.getUserid(), pi.getProjectID(), false);
          refreshProjectLists(false,0);
        }
      }
    } catch (Exception ex) {
      openDialog("There was an error updating this event",MessageDialog.ERROR_DIALOG);
      lman.log(ex);
    }
  }

  void userListChanged(ItemEvent e) {
    if (jcAdminUserList.getSelectedIndex() > -1) {
      adminAvailable.removeAllElements();
      adminAssigned.removeAllElements();
      jpPWord.setText("");
      jtDName.setText("");
      jtUName.setText("");
      jtDName.setEditable(false);
      jtUName.setEditable(false);
      jbAdminSaveNewUser.setEnabled(false);
      jbAdminRestPWord.setEnabled(false);
      jbAdminAddPrj.setEnabled(false);
      jbAssume.setEnabled(false);
      jbAdminRemovePrj.setEnabled(false);
    }
  }

  void showChangePWDialog(ActionEvent e) {
    ChangePasswordDialog dlg = new ChangePasswordDialog(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
    if(dlg.changePassword()){
      String pwds[] = dlg.getPasswords();
      if(pwds[1].trim().length() < 6 || pwds[1].trim().length() > 32){
        openDialog("Password -must be- between 6 and 12 characters",MessageDialog.ERROR_DIALOG);
      }else if(!pwds[1].trim().equals(pwds[2].trim())){
        openDialog("New and Confirmed passwords do not match ",MessageDialog.ERROR_DIALOG);
      }else{
        try {
          if(validateUpdateUserPassword(loginItem.getUserID(),pwds[0],pwds[1]) == 1){
            openDialog("Your password has been updated",MessageDialog.MESSAGE_DIALOG);
          }else{
            openDialog("Your existing password was not correct, please contact administration",MessageDialog.ERROR_DIALOG);
          }
        }catch (Exception ex) {
          openDialog("There was an error updating your password",MessageDialog.ERROR_DIALOG);
          lman.log(ex);
        }
      }
    }
  }

  void applyFilter(ActionEvent e) {
    refreshProjectLists(true,0);
  }

  void assumeUserID(ActionEvent e) {
    if(jcAdminUserList.getSelectedIndex() > -1){
      try {
        UserItem ui = (UserItem) jcAdminUserList.getSelectedItem();
        loginItem.setUserID(ui.getUserid());
        loginItem.setuserName(ui.getActualName());
        refreshProjectLists(false,0);
        jlUser.setText(loginItem.getUserName());
        openDialog("You have now assumed the identity of [" + loginItem.getUserName() + "]", MessageDialog.MESSAGE_DIALOG);
        jbBecomeAdmin.setEnabled(true);
        jbCreateUser.setEnabled(false);
        jbAdminLoad.setEnabled(false);
        jbAdminRefresh.setEnabled(false);
        jcAdminUserList.setEnabled(false);
        jbCreateUser.setEnabled(false);
        jbAdminSaveNewUser.setEnabled(false);
        jbAdminRestPWord.setEnabled(false);
        jbAdminAddPrj.setEnabled(false);
        jbAdminRemovePrj.setEnabled(false);
        jbAssume.setEnabled(false);
        jmChangePassword.setEnabled(false);
      } catch (Exception ex) {
        openDialog("Failed to assume User Itendity", MessageDialog.ERROR_DIALOG);
        lman.log(ex);
      }
    }
  }

  void resumeAdmin(ActionEvent e) {
    loginItem.setUserID(0);
    loginItem.setuserName("Administrator");
    jlUser.setText(loginItem.getUserName());
//---------- RESET THE ADMIN BUTTONS ----------
    jbBecomeAdmin.setEnabled(false);
    jbCreateUser.setEnabled(true);
    jbAdminLoad.setEnabled(true);
    jbAdminRefresh.setEnabled(true);
    jcAdminUserList.setEnabled(true);
    jmChangePassword.setEnabled(true);
//---------- CLEAN THE FIELDS AND TALBES ----------
    adminAvailable.removeAllElements();
    adminAssigned.removeAllElements();
    jpPWord.setText("");
    jtDName.setText("");
    jtUName.setText("");
    jtDName.setEditable(false);
    jtUName.setEditable(false);
    jbAdminSaveNewUser.setEnabled(false);
    jbAdminRestPWord.setEnabled(false);
    jbAdminAddPrj.setEnabled(false);
    jbAssume.setEnabled(false);
    jbAdminRemovePrj.setEnabled(false);
    refreshProjectLists(false,0);
    openDialog("You have been restored to the administrative user", MessageDialog.MESSAGE_DIALOG);
  }

  void runReport(ActionEvent e) {
    ReportDialog dlg = new ReportDialog(this,plModel.fetchVector());
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( ((frmSize.width - 500) / 2) + loc.x, (frmSize.height - 600) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  void sliderChanged(ChangeEvent e) {
     JSlider source = (JSlider)e.getSource();
     if (!source.getValueIsAdjusting()) {
         jlPctCmplt.setText(source.getValue() +"%");
     }
 }

  void addFivePct(ActionEvent e) {
    int val = pctComplete.getValue();
    if(val < 100){
      jlPctCmplt.setText((val + 5) + "%");
      pctComplete.setValue(val + 5);
    }
  }

  void subFivePct(ActionEvent e) {
    int val = pctComplete.getValue();
    if(val > 0){
      jlPctCmplt.setText((val - 5) + "%");
      pctComplete.setValue(val - 5);
    }
  }

  void handleOverview(int type) {
    //0 == Export
    //1 == Save
    //2 == Print

    boolean execute = false;
    FileDialog dia = null;
    if(rootNode.getChildCount() > 0){
      String path = "";
      if(type == 0){
        dia = new FileDialog(this, "Export CSV File to ...", FileDialog.SAVE);
        dia.setVisible(true);
        if (dia.getFile() != null) {
          path = dia.getDirectory() + dia.getFile().trim();
          if (!path.endsWith(".csv"))
            path += ".csv";
        }else{
          return;
        }//end filename
      }else if (type == 1) {
        dia = new FileDialog(this, "Save File to ...", FileDialog.SAVE);
        dia.setVisible(true);
        if (dia.getFile() != null) {
          path = dia.getDirectory() + dia.getFile().trim();
        }else{
          return;
        }//end if-filename
      }else if (type == 3) {
        dia = new FileDialog(this, "Save XML File to ...", FileDialog.SAVE);
        dia.setVisible(true);
        if (dia.getFile() != null) {
          path = dia.getDirectory() + dia.getFile().trim();
          if (!path.endsWith(".xml"))
            path += ".xml";
        }else{
          return;
        }//end if-filename
      }else if (type == 4) {
        dia = new FileDialog(this, "Save HTML File to ...", FileDialog.SAVE);
        dia.setVisible(true);
        if (dia.getFile() != null) {
          path = dia.getDirectory() + dia.getFile().trim();
          if (!path.endsWith(".htm"))
            path += ".htm";
        }else{
          return;
        }//end if-filename
      }

      try {
        new TreeNodeHandler().processNodeRequest(type,rootNode,path,this);
      }catch (Exception ex) {
        openDialog("The Was an error handeling this request", MessageDialog.ERROR_DIALOG);
        ex.printStackTrace();
      }

    }else{
      openDialog("The Overview Root Node contains no Reportable Elements", MessageDialog.ERROR_DIALOG);
    }

  }

  void plMouseUp(MouseEvent e) {
    if(mouseDrag){
      try{
          shiftRowsDown(loginItem.getUserID(),initialSelectedRow,plModel.getProjectItem(jlProjectList.getSelectedRow()).getPriority());
          refreshProjectLists(false,0);
      }catch(Exception ex){
        ex.printStackTrace();
      }
      mouseDrag = false;
    }
  }

  void plMouseDrag(MouseEvent e) {

    if(!mouseDrag){
      initialSelectedRow = plModel.getProjectItem(jlProjectList.getSelectedRow()).getPriority();
      mouseDrag = true;
    }
  }

}




/*
         public void stateChanged(ChangeEvent e) {
          JSlider source = (JSlider)e.getSource();
          if (!source.getValueIsAdjusting()) {
              int fps = (int)source.getValue();
              if (fps == 0) {
                  if (!frozen) stopAnimation();
              } else {
                  delay = 1000 / fps;
                  timer.setDelay(delay);
                  timer.setInitialDelay(delay * 10);
                  if (frozen) startAnimation();
              }
          }
      }
*/


//*************************************************************************************/


class TitrationUI_SwapLookAndFeel_actionAdapter
    implements ActionListener {
  private TitrationUI adaptee;
  TitrationUI_SwapLookAndFeel_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee; }

  public void actionPerformed(ActionEvent e) {
    adaptee.swapLookAndFeel(e.getActionCommand());
  }
}

class TitrationUI_jMenuFileExit_ActionAdapter
    implements ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jMenuFileExit_ActionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee; }

  public void actionPerformed(ActionEvent actionEvent) {
    adaptee.jMenuFileExit_actionPerformed(actionEvent); }}

class TitrationUI_jMenuHelpAbout_ActionAdapter
    implements ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jMenuHelpAbout_ActionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee; }

  public void actionPerformed(ActionEvent actionEvent) {
    adaptee.jMenuHelpAbout_actionPerformed(actionEvent); }}

class TitrationUI_jtComments_mouseAdapter extends java.awt.event.MouseAdapter {
  private TitrationUI adaptee;

  TitrationUI_jtComments_mouseAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.showCommentDialog(e);
  }
}

class TitrationUI_jbAddNewComment_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAddNewComment_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.addNewCommentDialog();
  }
}

class TitrationUI_jtTotalHours_focusAdapter extends java.awt.event.FocusAdapter {
  private TitrationUI adaptee;

  TitrationUI_jtTotalHours_focusAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.checkHourValue(e);
  }
}

class TitrationUI_jbNewProject_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbNewProject_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.addNewProject(e);
  }
}

class TitrationUI_jbLoadProject_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbLoadProject_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.loadExistingProject(e);
  }
}

class TitrationUI_jbSaveProject_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbSaveProject_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveOpenProject(e);
  }
}

class TitrationUI_jmRefreshProj_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmRefreshProj_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.refreshProjectLists(false,1);
  }
}

class TitrationUI_jbCreateUser_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbCreateUser_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.createNewUser(e);
  }
}

class TitrationUI_jbAdminLoad_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAdminLoad_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.loadUserData(e);
  }
}

class TitrationUI_jbAdminRefresh_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAdminRefresh_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.refreshUserList(e);
  }
}

class TitrationUI_jbAdminSaveNewUser_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAdminSaveNewUser_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveNewUser(e);
  }
}

class TitrationUI_jbAdminRestPWord_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAdminRestPWord_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.resetUserPassword(e);
  }
}

class TitrationUI_jbAdminRemovePrj_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAdminRemovePrj_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.removeProjectFromUser(e);
  }
}

class TitrationUI_jbAdminAddPrj_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAdminAddPrj_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.addProjectFromUser(e);
  }
}

class TitrationUI_jcAdminUserList_itemAdapter implements java.awt.event.ItemListener {
  private TitrationUI adaptee;

  TitrationUI_jcAdminUserList_itemAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.userListChanged(e);
  }
}

class TitrationUI_jmChangePassword_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmChangePassword_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.showChangePWDialog(e);
  }
}

class TitrationUI_jbFilter_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbFilter_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.applyFilter(e);
  }
}

class TitrationUI_jbAssume_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbAssume_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.assumeUserID(e);
  }
}

class TitrationUI_jbBecomeAdmin_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbBecomeAdmin_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.resumeAdmin(e);
  }
}

class TitrationUI_jmiRunReport_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmiRunReport_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.runReport(e);
  }
}

class TitrationUI_pctComplete_changeAdapter implements javax.swing.event.ChangeListener {
  private TitrationUI adaptee;

  TitrationUI_pctComplete_changeAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void stateChanged(ChangeEvent e) {
    adaptee.sliderChanged(e);
  }
}

class TitrationUI_jbPctPlus_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbPctPlus_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.addFivePct(e);
  }
}

class TitrationUI_jbCmpMinus_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbCmpMinus_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.subFivePct(e);
  }
}

class TitrationUI_jcProjectList_itemAdapter implements java.awt.event.ItemListener {
  private TitrationUI adaptee;

  TitrationUI_jcProjectList_itemAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.clearProjectData(e);
  }
}

class TitrationUI_jbLoadProject2_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbLoadProject2_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.loadProjectOverview(e);
  }
}

class TitrationUI_jbLoadAllProject_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbLoadAllProject_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.loadAllProjects(e);
  }
}

class TitrationUI_jbLoadUser_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbLoadUser_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.loadUserProjects(e,false);
  }
}

class TitrationUI_jbLoadAllUsers_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbLoadAllUsers_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.loadAllUserProjects(e);
  }
}

class TitrationUI_jcProjectList2_itemAdapter implements java.awt.event.ItemListener {
  private TitrationUI adaptee;

  TitrationUI_jcProjectList2_itemAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.clearProjectOverView(e);
  }
}

class TitrationUI_jcUserList_itemAdapter implements java.awt.event.ItemListener {
  private TitrationUI adaptee;

  TitrationUI_jcUserList_itemAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.clearProjectOverView(e);
  }
}

class TitrationUI_jmiExportOverview_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmiExportOverview_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.handleOverview(0);
  }
}

class TitrationUI_jmiSaveOverviewText_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmiSaveOverviewText_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.handleOverview(1);
  }
}

class TitrationUI_jmiPrintOverview_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmiPrintOverview_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.handleOverview(2);
  }
}

class TitrationUI_jmiExportXML_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmiExportXML_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.handleOverview(3);
  }
}

class TitrationUI_jmExportHtml_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jmExportHtml_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.handleOverview(4);
  }
}

class TitrationUI_jlProjectList_mouseAdapter extends java.awt.event.MouseAdapter {
  private TitrationUI adaptee;

  TitrationUI_jlProjectList_mouseAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseReleased(MouseEvent e) {
    adaptee.plMouseUp(e);
  }
}

class TitrationUI_jlProjectList_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  private TitrationUI adaptee;

  TitrationUI_jlProjectList_mouseMotionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseDragged(MouseEvent e) {
    adaptee.plMouseDrag(e);
  }
}

class TitrationUI_jbShowAll_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbShowAll_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.showAll(e);
  }
}

class TitrationUI_jbHide_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbHide_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.showHideSelected(1);
  }
}

class TitrationUI_jbClearHidden_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbClearHidden_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.showHideSelected(0);
  }
}

class TitrationUI_jbRefreshProject_actionAdapter implements java.awt.event.ActionListener {
  private TitrationUI adaptee;

  TitrationUI_jbRefreshProject_actionAdapter(TitrationUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.refreshProjectLists(false,0);
  }
}
