package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
//import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import com.mysql.jdbc.Statement;

import DAO.Student;

import java.awt.Cursor;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseMotionAdapter;

public class signInStd extends JFrame {

	private static final long serialVersionUID = -1746464630668715641L;
	private JTextField studentID, adminUsername;
	private JPasswordField passwordField, adminUserpass;
	private JPasswordField studentPassword;
	private CardLayout cardlayout = new CardLayout();
	private JPanel cards = new JPanel(cardlayout), contentPane, adminSignPane, stdSignPane, dashboard_std, dashboard_admin;
	private JPanel edPageI, edPageII, edPageIII, edPageIV;
	private static final String reg = " has sent an admission request.";
	private JTextField stdFirstName;
	private JTextField stdLastName;
	private JTextField stdMidName;
	private JTextField stdExName;
	private JTextField stdOtherSpec;
	private JTextField stdBirthDay;
	private JTextField stdAddress;
	private JTextField fatherName;
	private JTextField motherName;
	private JTextField guardianContact;
	private JTextField guardianAdd;
	private JTextField previousSch;
	private JTextField prevSchAdd;
	private JTextField dateAttend;
	private JTextField courseNamess;
	private File transcript, transcriptSave;
	private String[] names; String accepted = "No", currID = "", accID = "", selected_request = "";
	private Connection connector;
	ArrayList<Student> gather;
	
	public signInStd() {
		initGUI();	
	}
	
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initGUI() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 512);
		
		
		//Initializing all relevant JPanels
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		adminSignPane = new JPanel();
		adminSignPane.setBackground(SystemColor.window);
		adminSignPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		stdSignPane = new JPanel();
		stdSignPane.setBackground(SystemColor.window);
		stdSignPane.setBorder(new LineBorder(new Color(0,0,0),1,true));
		dashboard_std = new JPanel();
		dashboard_std.setBackground(SystemColor.window);
		dashboard_std.setBorder(new LineBorder(new Color(0,0,0),1,true));
		dashboard_admin = new JPanel();
		dashboard_admin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		dashboard_admin.setBackground(SystemColor.window);
		dashboard_admin.setBorder(new LineBorder(new Color(0,0,0),1,true));
		edPageI = new JPanel();
		edPageI.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		edPageI.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		edPageI.setBackground(SystemColor.window);
		edPageI.setBorder(UIManager.getBorder("OptionPane.border"));
		edPageII = new JPanel();
		edPageII.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		edPageII.setBackground(SystemColor.window);
		edPageII.setBorder(new LineBorder(new Color(0,0,0),1,true));
		edPageIII = new JPanel();
		edPageIII.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		edPageIII.setBackground(SystemColor.window);
		edPageIII.setBorder(new LineBorder(new Color(0,0,0),1,true));
		edPageIV = new JPanel();
		edPageIV.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		edPageIV.setBackground(SystemColor.window);
		edPageIV.setBorder(new LineBorder(new Color(0,0,0),1,true));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		adminSignPane.setLayout(null);
		stdSignPane.setLayout(null);
		dashboard_std.setLayout(null);
		dashboard_admin.setLayout(null);
		edPageI.setLayout(null);
		edPageII.setLayout(null);
		edPageIII.setLayout(null);
		edPageIV.setLayout(null);
		
		setContentPane(contentPane);
		cards.add(stdSignPane, "StudentSign");
		cards.add(adminSignPane, "AdminSign");
		cards.add(dashboard_std, "Student");
		cards.add(dashboard_admin, "Admin");
		cards.add(edPageI, "EditI");
		cards.add(edPageII, "EditII");
		cards.add(edPageIII, "EditIII");
		cards.add(edPageIV, "EditIV");
		
		JComboBox<Object> enrolmentType = new JComboBox<Object>();
		enrolmentType.setFont(new Font("Montserrat", Font.PLAIN, 11));
		enrolmentType.setModel(new DefaultComboBoxModel(new String[] {"Select enrolment type here", "Full-time", "Fee-paying", "Distance", "Foreign"}));
		enrolmentType.setBounds(10, 214, 320, 39);
		edPageIV.add(enrolmentType);
		
		JComboBox<Object> yearLevel = new JComboBox<Object>();
		yearLevel.setFont(new Font("Montserrat", Font.PLAIN, 11));
		yearLevel.setModel(new DefaultComboBoxModel(new String[] {"Select level from drop-down", "Level 100", "Level 200", "Level 300", "Level 400"}));
		yearLevel.setBounds(372, 111, 320, 39);
		edPageIV.add(yearLevel);
		
		JLabel saveReturn = new JLabel("");
		saveReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cName, yLevel, enrol, insertIV;
				cName = courseNamess.getText();
				yLevel = yearLevel.getSelectedItem().toString();
				enrol = enrolmentType.getSelectedItem().toString();
				
				insertIV = "UPDATE `student` SET `course` = ?, `yearlevel` = ? ,  `enrolment` = ? WHERE `student`.`ID` = ?";
				
				Student insertStdIV = new Student();
				insertStdIV.admission(cName, yLevel, enrol);
				
				Connection saveI = connect();
				try {
					PreparedStatement pstmt = saveI.prepareStatement(insertIV, Statement.RETURN_GENERATED_KEYS);
					
					pstmt.setString(1, insertStdIV.getCourse());
					pstmt.setString(2, insertStdIV.getYear());
					pstmt.setString(3, insertStdIV.getEnrolment());
					pstmt.setString(4, currID);
					
					int rowAffected = pstmt.executeUpdate();
					
					if(rowAffected == 1) {
						JOptionPane.showMessageDialog(null, "Saved to DB successfully");
						cardlayout.show(cards, "Student");
					}
					else 
						JOptionPane.showMessageDialog(null, "Smth went wrong!");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		saveReturn.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Submit-2.png")));
		saveReturn.setBounds(548, 214, 144, 39);
		edPageIV.add(saveReturn);
		
		JLabel lblNewLabel_15_1_2 = new JLabel("Previous");
		lblNewLabel_15_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.previous(cards);
			}
		});
		lblNewLabel_15_1_2.setIcon(new ImageIcon(signInStd.class.getResource("/Images/Line 14.png")));
		lblNewLabel_15_1_2.setFont(new Font("Montserrat Light", Font.PLAIN, 11));
		lblNewLabel_15_1_2.setBounds(10, 11, 115, 14);
		edPageIV.add(lblNewLabel_15_1_2);
		
		JLabel lblNewLabel_21_1_2 = new JLabel("Edit - Admission information");
		lblNewLabel_21_1_2.setFont(new Font("Lora", Font.BOLD, 18));
		lblNewLabel_21_1_2.setBounds(10, 45, 265, 14);
		edPageIV.add(lblNewLabel_21_1_2);
		
		JLabel lblNewLabel_22_3_3 = new JLabel("Course name");
		lblNewLabel_22_3_3.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_3_3.setBounds(10, 85, 94, 14);
		edPageIV.add(lblNewLabel_22_3_3);
		
		courseNamess = new JTextField();
		courseNamess.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		courseNamess.setColumns(10);
		courseNamess.setBounds(10, 111, 320, 39);
		edPageIV.add(courseNamess);
		
		JLabel lblNewLabel_22_1_2_3 = new JLabel("Year level");
		lblNewLabel_22_1_2_3.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_2_3.setBounds(372, 85, 94, 14);
		edPageIV.add(lblNewLabel_22_1_2_3);
		
		JLabel lblNewLabel_22_3_1_2 = new JLabel("Enrolment type");
		lblNewLabel_22_3_1_2.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_3_1_2.setBounds(10, 188, 127, 14);
		edPageIV.add(lblNewLabel_22_3_1_2);
		
		JLabel saveSub = new JLabel("");
		saveSub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(accepted.equals("No")) {
					String cName, yLevel, enrol, insertIV;
					cName = courseNamess.getText();
					yLevel = yearLevel.getSelectedItem().toString();
					enrol = enrolmentType.getSelectedItem().toString();
					
					insertIV = "UPDATE `student` SET `course` = ?, `yearlevel` = ? ,  `enrolment` = ?, `admissionRequest` = ? WHERE `student`.`ID` = ?";
					
					Student insertStdIV = new Student();
					insertStdIV.admissionI(cName, yLevel, enrol);
					
					Connection saveI = connect();
					try {
						PreparedStatement pstmt = saveI.prepareStatement(insertIV, Statement.RETURN_GENERATED_KEYS);
						
						pstmt.setString(1, insertStdIV.getCourse());
						pstmt.setString(2, insertStdIV.getYear());
						pstmt.setString(3, insertStdIV.getEnrolment());
						pstmt.setString(4, "Yes");
						pstmt.setString(5, currID);
						
						int rowAffected = pstmt.executeUpdate();
						
						if(rowAffected == 1) {
							JOptionPane.showMessageDialog(null, "Saved to DB successfully and submitted request!");
							cardlayout.show(cards, "Student");
						}
						else 
							JOptionPane.showMessageDialog(null, "Smth went wrong!");
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		saveSub.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Submit-1.png")));
		saveSub.setBounds(372, 214, 144, 39);
		edPageIV.add(saveSub);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Dashboard_std.png")));
		lblNewLabel_5.setBounds(0, 0, 712, 471);
		edPageIV.add(lblNewLabel_5);
		
		JLabel saveNextIII = new JLabel("");
		saveNextIII.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String prev, prevAdd, dateComp, insertIII;
				prev = previousSch.getText();
				prevAdd = prevSchAdd.getText();
				dateComp = dateAttend.getText();
				
				insertIII = "UPDATE `student` SET `previousSchool` = ?, `schoolAddress` = ? ,  `yearCompleted` = ?, `transcript` = ? WHERE `student`.`ID` = ?";
				
				Student insertStdIII = new Student();
				insertStdIII.school(prev, prevAdd, dateComp, transcript);
				
				//System.out.println(transcript.getAbsolutePath());
				
				Connection saveI = connect();
				try {
					PreparedStatement pstmt = saveI.prepareStatement(insertIII, Statement.RETURN_GENERATED_KEYS);
					
					pstmt.setString(1, insertStdIII.getPreviousSchool());
					pstmt.setString(2, insertStdIII.getSchoolAddress());
					pstmt.setDate(3, insertStdIII.getYearCompleted());
					pstmt.setBinaryStream(4, insertStdIII.getTranscript());
					pstmt.setString(1, currID);
					
					int rowAffected = pstmt.executeUpdate();
					
					if(rowAffected == 1) {
						System.out.println("Saved to DB successfully!");
						cardlayout.show(cards, "EditIV");
					}
					else 
						System.out.println("Smth went wrong!");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch(FileNotFoundException f) {
					System.out.println(f.getMessage());
					f.printStackTrace();
				}
			}
		});
		saveNextIII.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Save_next.png")));
		saveNextIII.setBounds(293, 290, 158, 39);
		edPageIII.add(saveNextIII);
		
		JLabel lblNewLabel_15_1_1 = new JLabel("Previous");
		lblNewLabel_15_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.previous(cards);
			}
		});
		lblNewLabel_15_1_1.setIcon(new ImageIcon(signInStd.class.getResource("/Images/Line 14.png")));
		lblNewLabel_15_1_1.setFont(new Font("Montserrat Light", Font.PLAIN, 11));
		lblNewLabel_15_1_1.setBounds(10, 11, 115, 14);
		edPageIII.add(lblNewLabel_15_1_1);
		
		JLabel lblNewLabel_21_1_1 = new JLabel("Edit - School information");
		lblNewLabel_21_1_1.setFont(new Font("Lora", Font.BOLD, 18));
		lblNewLabel_21_1_1.setBounds(10, 45, 265, 14);
		edPageIII.add(lblNewLabel_21_1_1);
		
		JLabel lblNewLabel_22_3_2 = new JLabel("Previous school");
		lblNewLabel_22_3_2.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_3_2.setBounds(10, 85, 103, 14);
		edPageIII.add(lblNewLabel_22_3_2);
		
		previousSch = new JTextField();
		previousSch.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		previousSch.setColumns(10);
		previousSch.setBounds(10, 111, 320, 39);
		edPageIII.add(previousSch);
		
		JLabel lblNewLabel_22_1_2_2 = new JLabel("School's address");
		lblNewLabel_22_1_2_2.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_2_2.setBounds(372, 85, 127, 14);
		edPageIII.add(lblNewLabel_22_1_2_2);
		
		prevSchAdd = new JTextField();
		prevSchAdd.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		prevSchAdd.setColumns(10);
		prevSchAdd.setBounds(372, 111, 320, 39);
		edPageIII.add(prevSchAdd);
		
		JLabel lblNewLabel_22_3_1_1 = new JLabel("Transcript");
		lblNewLabel_22_3_1_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_3_1_1.setBounds(372, 188, 127, 14);
		edPageIII.add(lblNewLabel_22_3_1_1);
		
		JLabel lblNewLabel_22_1_2_1_1 = new JLabel("Last date attended YYYY-MM-DD");
		lblNewLabel_22_1_2_1_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_2_1_1.setBounds(10, 188, 209, 14);
		edPageIII.add(lblNewLabel_22_1_2_1_1);
		
		dateAttend = new JTextField();
		dateAttend.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		dateAttend.setColumns(10);
		dateAttend.setBounds(10, 214, 320, 39);
		edPageIII.add(dateAttend);
		
		JButton uploadBtn = new JButton("Upload file . . .");
		uploadBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				int output_res = fileChooser.showOpenDialog(null);
				
				if(output_res == JFileChooser.APPROVE_OPTION)
					transcript = new File(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		
		
		uploadBtn.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 13));
		uploadBtn.setBounds(372, 213, 127, 40);
		edPageIII.add(uploadBtn);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Dashboard_std.png")));
		lblNewLabel_5_1.setBounds(0, 0, 712, 471);
		edPageIII.add(lblNewLabel_5_1);
		
		JLabel saveNextII = new JLabel("");
		saveNextII.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String father, mother, contact, gAdd, insertII;
				father = fatherName.getText();
				mother = motherName.getText();
				contact = guardianContact.getText();
				gAdd = guardianAdd.getText();
				
				
				
				insertII = "UPDATE `student` SET `fatherName` = ?, `MotherName` = ? ,  `guardianContact` = ?, `guardianAddress` = ? WHERE `student`.`ID` = ?";
				
				Student insertStdI = new Student();
				insertStdI.guardians(father, mother, contact, gAdd);
				
				Connection saveI = connect();
				try {
					PreparedStatement pstmt = saveI.prepareStatement(insertII, Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, insertStdI.getFatherName());
					pstmt.setString(2, insertStdI.getMotherName());
					pstmt.setString(3, insertStdI.getGuardianContact());
					pstmt.setString(4, insertStdI.getGuardianAddress());
					pstmt.setString(5, currID);
					
					int rowAffected = pstmt.executeUpdate();
					
					if(rowAffected == 1) {
						System.out.println("Saved to DB successfully!");
						cardlayout.show(cards, "EditIII");
					}
					else 
						System.out.println("Smth went wrong!");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		saveNextII.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Save_next.png")));
		saveNextII.setBounds(293, 290, 127, 39);
		edPageII.add(saveNextII);
		
		JLabel lblNewLabel_15_1 = new JLabel("Previous");
		lblNewLabel_15_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.previous(cards);
			}
		});
		lblNewLabel_15_1.setIcon(new ImageIcon(signInStd.class.getResource("/Images/Line 14.png")));
		lblNewLabel_15_1.setFont(new Font("Montserrat Light", Font.PLAIN, 11));
		lblNewLabel_15_1.setBounds(10, 11, 115, 14);
		edPageII.add(lblNewLabel_15_1);
		
		JLabel lblNewLabel_21_1 = new JLabel("Edit - Guardian information");
		lblNewLabel_21_1.setFont(new Font("Lora", Font.BOLD, 18));
		lblNewLabel_21_1.setBounds(10, 45, 265, 14);
		edPageII.add(lblNewLabel_21_1);
		
		JLabel lblNewLabel_22_3 = new JLabel("Father's name");
		lblNewLabel_22_3.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_3.setBounds(10, 85, 94, 14);
		edPageII.add(lblNewLabel_22_3);
		
		fatherName = new JTextField();
		fatherName.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		fatherName.setColumns(10);
		fatherName.setBounds(10, 111, 320, 39);
		edPageII.add(fatherName);
		
		JLabel lblNewLabel_22_1_2 = new JLabel("Mother's name");
		lblNewLabel_22_1_2.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_2.setBounds(372, 85, 94, 14);
		edPageII.add(lblNewLabel_22_1_2);
		
		motherName = new JTextField();
		motherName.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		motherName.setColumns(10);
		motherName.setBounds(372, 111, 320, 39);
		edPageII.add(motherName);
		
		JLabel lblNewLabel_22_3_1 = new JLabel("Guardian's contact");
		lblNewLabel_22_3_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_3_1.setBounds(10, 188, 127, 14);
		edPageII.add(lblNewLabel_22_3_1);
		
		guardianContact = new JTextField();
		guardianContact.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		guardianContact.setColumns(10);
		guardianContact.setBounds(10, 214, 320, 39);
		edPageII.add(guardianContact);
		
		JLabel lblNewLabel_22_1_2_1 = new JLabel("Guardian's address");
		lblNewLabel_22_1_2_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_2_1.setBounds(372, 188, 127, 14);
		edPageII.add(lblNewLabel_22_1_2_1);
		
		guardianAdd = new JTextField();
		guardianAdd.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		guardianAdd.setColumns(10);
		guardianAdd.setBounds(372, 214, 320, 39);
		edPageII.add(guardianAdd);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Dashboard_std.png")));
		lblNewLabel_12.setBounds(0, 0, 702, 471);
		edPageII.add(lblNewLabel_12);
		
		JLabel backp1 = new JLabel("Back to home");
		backp1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.show(cards, "Student");
			}
		});
		backp1.setIcon(new ImageIcon(signInStd.class.getResource("/Images/Line 14.png")));
		backp1.setFont(new Font("Montserrat Light", Font.PLAIN, 11));
		backp1.setBounds(10, 11, 115, 14);
		edPageI.add(backp1);
		
		JLabel lblNewLabel_21 = new JLabel("Edit - Personal Information");
		lblNewLabel_21.setFont(new Font("Lora", Font.BOLD, 18));
		lblNewLabel_21.setBounds(10, 45, 265, 14);
		edPageI.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("First name");
		lblNewLabel_22.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22.setBounds(10, 80, 94, 14);
		edPageI.add(lblNewLabel_22);
		
		stdFirstName = new JTextField();
		stdFirstName.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		stdFirstName.setBounds(10, 106, 320, 39);
		edPageI.add(stdFirstName);
		stdFirstName.setColumns(10);
		
		stdLastName = new JTextField();
		stdLastName.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		stdLastName.setColumns(10);
		stdLastName.setBounds(372, 106, 320, 39);
		edPageI.add(stdLastName);
		
		JLabel lblNewLabel_22_1 = new JLabel("Last name");
		lblNewLabel_22_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1.setBounds(372, 80, 94, 14);
		edPageI.add(lblNewLabel_22_1);
		
		JLabel lblNewLabel_22_2 = new JLabel("Middle name");
		lblNewLabel_22_2.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_2.setBounds(10, 171, 94, 14);
		edPageI.add(lblNewLabel_22_2);
		
		stdMidName = new JTextField();
		stdMidName.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		stdMidName.setColumns(10);
		stdMidName.setBounds(10, 197, 320, 39);
		edPageI.add(stdMidName);
		
		stdExName = new JTextField();
		stdExName.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		stdExName.setColumns(10);
		stdExName.setBounds(372, 197, 320, 39);
		edPageI.add(stdExName);
		
		JLabel lblNewLabel_22_1_1 = new JLabel("Extension name");
		lblNewLabel_22_1_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_1.setBounds(372, 171, 115, 14);
		edPageI.add(lblNewLabel_22_1_1);
		
		JLabel lblNewLabel_22_2_1 = new JLabel("Gender");
		lblNewLabel_22_2_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_2_1.setBounds(10, 265, 94, 14);
		edPageI.add(lblNewLabel_22_2_1);
		
		JRadioButton stdMale = new JRadioButton("Male");
		stdMale.setFont(new Font("Montserrat", Font.PLAIN, 12));
		stdMale.setBounds(10, 287, 80, 23);
		edPageI.add(stdMale);
		
		JRadioButton stdFemale = new JRadioButton("Female");
		stdFemale.setFont(new Font("Montserrat", Font.PLAIN, 12));
		stdFemale.setBounds(104, 288, 80, 23);
		edPageI.add(stdFemale);
		
		JRadioButton stdOther = new JRadioButton("Other");
		stdOther.setFont(new Font("Montserrat", Font.PLAIN, 12));
		stdOther.setBounds(197, 287, 80, 23);
		edPageI.add(stdOther);
		
		ButtonGroup genders = new ButtonGroup();
		genders.add(stdMale);
		genders.add(stdFemale);
		genders.add(stdOther);
		
		stdOtherSpec = new JTextField();
		stdOtherSpec.setText("Please specify if other . . . ");
		stdOtherSpec.setEnabled(false);
		stdOtherSpec.setFont(new Font("Montserrat Medium", Font.ITALIC, 14));
		stdOtherSpec.setColumns(10);
		stdOtherSpec.setBounds(11, 319, 320, 39);
		edPageI.add(stdOtherSpec);
		
		JLabel lblNewLabel_22_1_1_1 = new JLabel("Birthday YYYY-MM-DD");
		lblNewLabel_22_1_1_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_1_1.setBounds(372, 293, 158, 14);
		edPageI.add(lblNewLabel_22_1_1_1);
		
		stdBirthDay = new JTextField();
		stdBirthDay.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		stdBirthDay.setColumns(10);
		stdBirthDay.setBounds(372, 319, 320, 39);
		edPageI.add(stdBirthDay);
		
		JLabel lblNewLabel_22_1_1_2 = new JLabel("Address");
		lblNewLabel_22_1_1_2.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_22_1_1_2.setBounds(10, 384, 115, 14);
		edPageI.add(lblNewLabel_22_1_1_2);
		
		stdAddress = new JTextField();
		stdAddress.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		stdAddress.setColumns(10);
		stdAddress.setBounds(10, 410, 320, 39);
		edPageI.add(stdAddress);
		
		JLabel saveNext1 = new JLabel("");
		saveNext1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String first, last, mid, ext, gender = "NA", birth, addr, insertI;
				first = stdFirstName.getText();
				last = stdLastName.getText();
				mid = stdMidName.getText();
				ext = stdExName.getText();
				
				if(stdMale.isSelected())
					gender = "Male";
				else if(stdFemale.isSelected())
					gender = "Female";
				else if(stdOther.isSelected()) {
					stdOtherSpec.setEnabled(true);
					gender = stdOtherSpec.getText();
				}
				
				birth = stdBirthDay.getText();
				addr = stdAddress.getText();
				
				insertI = "UPDATE `student` SET `lName` = ?, `fName` = ? ,  `mName` = ?, `eName` = ?, `gender` = ?, `addressID` = ?, `birth` = ? WHERE `student`.`ID` = ?";
				
				Student insertStd = new Student(first, last, mid, ext, gender, addr, birth);
				
				Connection saveI = connect();
				try {
					PreparedStatement pstmt = saveI.prepareStatement(insertI, Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, insertStd.getlName());
					pstmt.setString(2, insertStd.getfName());
					pstmt.setString(3, insertStd.getmName());
					pstmt.setString(4, insertStd.geteName());
					pstmt.setString(5, insertStd.getGender());
					pstmt.setString(6, insertStd.getAddress());
					pstmt.setDate(7, insertStd.getBirth());
					pstmt.setString(8, currID);
					
					int rowAffected = pstmt.executeUpdate();
					
					if(rowAffected == 1) {
						System.out.println("Saved to DB successfully!");
						cardlayout.show(cards, "EditII");
					}
					else 
						System.out.println("Smth went wrong!");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		saveNext1.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Save_next.png")));
		saveNext1.setBounds(372, 410, 158, 39);
		edPageI.add(saveNext1);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Dashboard_std.png")));
		lblNewLabel_10.setBounds(0, 0, 702, 471);
		edPageI.add(lblNewLabel_10);
		//cards.add(dashboard_admin);
		
		JLabel rejectReq = new JLabel("");
		rejectReq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(names.length > 0) {
					try {
						
						JOptionPane checker = new JOptionPane();
						
						int reject = checker.showConfirmDialog(null, "Reject request: Are you sure?");
						
						System.out.println(accID);
						
						if(reject == JOptionPane.YES_OPTION) {
							
							Connection newcon = connect();
							PreparedStatement p = newcon.prepareStatement("UPDATE `student` SET admissionRequest = 'No' WHERE ID = ?;");
							p.setString(1, accID);
							p.executeUpdate();
							JOptionPane.showMessageDialog(null, "Done");
							contentPane.revalidate();
							contentPane.repaint();
						}
						
					}catch(Exception e22) {
						
						e22.printStackTrace();
					}
				}
			}
		});
		rejectReq.setBorder(null);
		rejectReq.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Reject_btn.png")));
		rejectReq.setBounds(560, 401, 130, 46);
		dashboard_admin.add(rejectReq);
		
		JLabel acceptReq = new JLabel("");
		acceptReq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					if(names.length > 0) {
						String hall = JOptionPane.showInputDialog("Enter hall:");
						
						if(hall == null) 
							hall = "Pending";
						
						System.out.println(hall);
						System.out.println(accID);
						
						try {
							Connection connn = connect();
							PreparedStatement ptsmt = connn.prepareStatement("UPDATE `student` SET `admissionRequest` = ?, `acceptedRequest` = ? ,`hall` = ? WHERE `student`.`ID` = ?;", Statement.RETURN_GENERATED_KEYS);
							ptsmt.setString(1, "No");
							ptsmt.setString(2, "Yes");
							ptsmt.setString(3, hall);
							ptsmt.setString(4, accID);
							
							int rowsAffected = ptsmt.executeUpdate();
							
							if(rowsAffected == 1) {
								JOptionPane.showMessageDialog(null, "Success!");
//								contentPane.remove();
//								contentPane.revalidate();
//								contentPane.repaint();
							}
							
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
		});
		acceptReq.setBorder(null);
		acceptReq.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Accept_btn.png")));
		acceptReq.setBounds(414, 401, 130, 46);
		dashboard_admin.add(acceptReq);
		
		JLabel downloadTranscript = new JLabel("");
		downloadTranscript.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(names.length > 0) {
					JFileChooser fileSave = new JFileChooser();
					int response = fileSave.showSaveDialog(null);
					
					System.out.println(accID);
					
					if(response == JFileChooser.APPROVE_OPTION)
						transcriptSave = new File(fileSave.getSelectedFile().getAbsolutePath());
					
					Connection download = connect();
					String downloadQuery = "SELECT `transcript` FROM `student` WHERE `ID` = ?;";
					try {
						
						PreparedStatement ptsmt = download.prepareStatement(downloadQuery);
						ptsmt.setString(1, accID);
						ResultSet rs = ptsmt.executeQuery();
						
						FileOutputStream fo = new FileOutputStream(transcriptSave);
						
						System.out.println("Writing to file " + transcriptSave.getAbsolutePath());
						
						while(rs.next()) {
							InputStream input = rs.getBinaryStream("transcript");
							
							byte[] buffer = new byte[1024];
						    while (input.read(buffer) > 0) {
						        fo.write(buffer);
						    }
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
						
					} catch(FileNotFoundException f) {
						
						f.printStackTrace();
						
					} catch (IOException e1) {
					
						e1.printStackTrace();
					}
					
				}	
			}
		});
		downloadTranscript.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Dwnld_btn.png")));
		downloadTranscript.setBounds(456, 343, 205, 46);
		dashboard_admin.add(downloadTranscript);
		
		JLabel enrolType = new JLabel("");
		enrolType.setForeground(Color.WHITE);
		enrolType.setFont(new Font("Montserrat Light", Font.PLAIN, 12));
		enrolType.setBounds(571, 317, 121, 14);
		dashboard_admin.add(enrolType);
		
		JLabel lblNewLabel_16_2_1_1 = new JLabel("Enrolment type:");
		lblNewLabel_16_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_16_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_16_2_1_1.setFont(new Font("Montserrat SemiBold", Font.BOLD, 12));
		lblNewLabel_16_2_1_1.setBounds(425, 318, 140, 14);
		dashboard_admin.add(lblNewLabel_16_2_1_1);
		
		JLabel courseName = new JLabel("");
		courseName.setForeground(Color.WHITE);
		courseName.setFont(new Font("Montserrat Light", Font.PLAIN, 12));
		courseName.setBounds(572, 291, 120, 14);
		dashboard_admin.add(courseName);
		
		JLabel lblNewLabel_16_2_1 = new JLabel("Course of pursuit:");
		lblNewLabel_16_2_1.setForeground(Color.WHITE);
		lblNewLabel_16_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_16_2_1.setFont(new Font("Montserrat SemiBold", Font.BOLD, 12));
		lblNewLabel_16_2_1.setBounds(426, 292, 140, 14);
		dashboard_admin.add(lblNewLabel_16_2_1);
		
		JLabel doC = new JLabel("");
		doC.setForeground(Color.WHITE);
		doC.setFont(new Font("Montserrat Light", Font.PLAIN, 12));
		doC.setBounds(570, 266, 122, 14);
		dashboard_admin.add(doC);
		
		JLabel lblNewLabel_16_2 = new JLabel("Year of completion:");
		lblNewLabel_16_2.setForeground(Color.WHITE);
		lblNewLabel_16_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_16_2.setFont(new Font("Montserrat SemiBold", Font.BOLD, 12));
		lblNewLabel_16_2.setBounds(424, 267, 140, 14);
		dashboard_admin.add(lblNewLabel_16_2);
		
		JLabel schName = new JLabel("");
		schName.setForeground(Color.WHITE);
		schName.setFont(new Font("Montserrat Light", Font.PLAIN, 12));
		schName.setBounds(568, 242, 124, 14);
		dashboard_admin.add(schName);
		
		JLabel lblNewLabel_16 = new JLabel("School of completion:");
		lblNewLabel_16.setForeground(Color.WHITE);
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_16.setFont(new Font("Montserrat SemiBold", Font.BOLD, 12));
		lblNewLabel_16.setBounds(424, 242, 140, 14);
		dashboard_admin.add(lblNewLabel_16);
		
		JLabel studentName = new JLabel("");
		studentName.setHorizontalAlignment(SwingConstants.CENTER);
		studentName.setFont(new Font("Lora", Font.BOLD, 20));
		studentName.setBounds(437, 204, 240, 27);
		dashboard_admin.add(studentName);
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setBorder(null);
		lblNewLabel_14.setForeground(Color.BLACK);
		lblNewLabel_14.setOpaque(true);
		lblNewLabel_14.setIcon(new ImageIcon(signInStd.class.getResource("/Images/Login_Form.png")));
		lblNewLabel_14.setBounds(485, 62, 140, 140);
		dashboard_admin.add(lblNewLabel_14);
		
		JLabel signoutAdmin = new JLabel("");
		signoutAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.show(cards, "AdminSign");
			}
		});
		signoutAdmin.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Group 1.png")));
		signoutAdmin.setBounds(604, 11, 88, 32);
		dashboard_admin.add(signoutAdmin);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Rectangle 11.png")));
		lblNewLabel_9.setBounds(402, 0, 300, 471);
		dashboard_admin.add(lblNewLabel_9);
		
		JLabel lblNewLabel_20 = new JLabel("Admission");
		lblNewLabel_20.setFont(new Font("Lora", Font.BOLD, 23));
		lblNewLabel_20.setBounds(10, 11, 130, 46);
		dashboard_admin.add(lblNewLabel_20);
		
		JLabel lblNewLabel_20_1 = new JLabel("Requests");
		lblNewLabel_20_1.setFont(new Font("Lora", Font.BOLD, 23));
		lblNewLabel_20_1.setBounds(10, 37, 130, 46);
		dashboard_admin.add(lblNewLabel_20_1);
		
		gather = readAllRequests();
		
		names = new String[gather.size()];
		
		for(int i = 0; i < names.length; i++)
			names[i] = "  " + gather.get(i).getfName();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 382, 362);
		dashboard_admin.add(scrollPane);
		
		JList requestList = new JList(names);
		
		if(names.length <= 0) {
			acceptReq.setEnabled(false);
			rejectReq.setEnabled(false);
			downloadTranscript.setEnabled(false);
		}
		else {
			acceptReq.setEnabled(true);
			rejectReq.setEnabled(true);
			downloadTranscript.setEnabled(true);
		}
		
		requestList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				selected_request = requestList.getSelectedValue().toString();
				
				//System.out.println(selected_request);
				
				for(int i = 0; i < gather.size(); i++) {
					if(gather.get(i).getfName().equals(selected_request.trim())) {
						studentName.setText(gather.get(i).getfName() + " " +  gather.get(i).getlName());
						schName.setText(gather.get(i).getPreviousSchool());
						doC.setText(gather.get(i).getYearCompleted().toString());
						courseName.setText(gather.get(i).getCourse());
						enrolType.setText(gather.get(i).getEnrolment());
						accID = gather.get(i).getID();
					}
				}
			}
		});
		
		scrollPane.setViewportView(requestList);
		requestList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		requestList.setBackground(SystemColor.control);
		requestList.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		requestList.setFixedCellHeight(80);
		requestList.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		requestList.setVisibleRowCount(4);
		
		JLabel lblNewLabel_6 = new JLabel("Welcome,");
		lblNewLabel_6.setFont(new Font("Lora", Font.BOLD, 25));
		lblNewLabel_6.setBounds(20, 11, 125, 26);
		dashboard_std.add(lblNewLabel_6);
		
		JLabel std_signOut = new JLabel("");
		std_signOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.show(cards, "StudentSign");
			}
		});
		std_signOut.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Group 1.png")));
		std_signOut.setBounds(603, 11, 89, 26);
		dashboard_std.add(std_signOut);
		
		JLabel userName = new JLabel("");
		userName.setFont(new Font("Lora", Font.BOLD, 25));
		userName.setBounds(143, 11, 207, 26);
		dashboard_std.add(userName);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(signInStd.class.getResource("/Images/Login_Form.png")));
		lblNewLabel_7.setBounds(281, 110, 130, 130);
		dashboard_std.add(lblNewLabel_7);
		
		JLabel usernameDisp = new JLabel("");
		usernameDisp.setHorizontalAlignment(SwingConstants.CENTER);
		usernameDisp.setFont(new Font("Lora", Font.BOLD, 16));
		usernameDisp.setBounds(182, 251, 314, 26);
		dashboard_std.add(usernameDisp);
		
		JLabel lblNewLabel_8 = new JLabel("Admission status:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setFont(new Font("Montserrat Medium", Font.BOLD, 13));
		lblNewLabel_8.setBounds(205, 283, 133, 14);
		dashboard_std.add(lblNewLabel_8);
		
		JLabel admissionStatus = new JLabel("Admitted/Rejected/Pending");
		admissionStatus.setHorizontalAlignment(SwingConstants.LEFT);
		admissionStatus.setFont(new Font("Montserrat Light", Font.PLAIN, 13));
		admissionStatus.setBounds(351, 283, 188, 14);
		dashboard_std.add(admissionStatus);
		
		JLabel hOr = new JLabel("Name/Rejected");
		hOr.setHorizontalAlignment(SwingConstants.LEFT);
		hOr.setFont(new Font("Montserrat Light", Font.PLAIN, 13));
		hOr.setBounds(351, 307, 188, 14);
		dashboard_std.add(hOr);
		
		JLabel lblNewLabel_8_1 = new JLabel(" Hall of residence:");
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8_1.setFont(new Font("Montserrat Medium", Font.BOLD, 13));
		lblNewLabel_8_1.setBounds(215, 307, 125, 14);
		dashboard_std.add(lblNewLabel_8_1);
		
		JLabel subReq = new JLabel("");
		subReq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(accepted.equals("No")) {
					String insertIV = "UPDATE `student` SET `admissionRequest` = ? WHERE `student`.`ID` = ?";
					
					Connection saveI = connect();
					try {
						PreparedStatement pstmt = saveI.prepareStatement(insertIV, Statement.RETURN_GENERATED_KEYS);
						pstmt.setString(1, "Yes");
						pstmt.setString(2, currID);
						
						int rowAffected = pstmt.executeUpdate();
						
						if(rowAffected == 1) {
							JOptionPane.showMessageDialog(null, "Submitted request!");
							//cardlayout.show(cards, "Student");
						}
						else 
							JOptionPane.showMessageDialog(null, "Smth went wrong!");
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		subReq.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Submit.png")));
		subReq.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 11));
		subReq.setBounds(205, 352, 133, 38);
		dashboard_std.add(subReq);
		
		JLabel editInfoBtn = new JLabel("");
		editInfoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.show(cards, "EditI");
			}
		});
		editInfoBtn.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Edit.png")));
		editInfoBtn.setBounds(363, 352, 133, 38);
		dashboard_std.add(editInfoBtn);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Dashboard_std.png")));
		lblNewLabel_4.setBounds(0, 0, 702, 472);
		dashboard_std.add(lblNewLabel_4);
		
		JLabel std2adm = new JLabel("Not a student, sign in as admin");
		std2adm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 cardlayout.next(cards);
			}
		});
		
		std2adm.setForeground(SystemColor.textHighlight);
		std2adm.setFont(new Font("Montserrat", Font.PLAIN, 9));
		std2adm.setBounds(365, 355, 148, 14);
		stdSignPane.add(std2adm);
		
		studentPassword = new JPasswordField();
		studentPassword.setBorder(UIManager.getBorder("OptionPane.border"));
		studentPassword.setBounds(365, 248, 230, 38);
		stdSignPane.add(studentPassword);
		adminSignPane.setLayout(null);
		
		JLabel lblNewLabel_52 = new JLabel("Not an admin, sign in as student");
		lblNewLabel_52.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.previous(cards);
			}
		});
		lblNewLabel_52.setBounds(365, 355, 148, 14);
		lblNewLabel_52.setForeground(SystemColor.textHighlight);
		lblNewLabel_52.setFont(new Font("Montserrat", Font.PLAIN, 9));
		adminSignPane.add(lblNewLabel_52);
		
		JLabel signInbtn = new JLabel("");
		signInbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					currID = studentID.getText().trim();
					String pass = studentPassword.getText();
					System.out.println(currID + " " + pass);
					
					connector = connect();
					
					PreparedStatement stmt = connector.prepareStatement("SELECT * FROM student WHERE ID = ? AND stdpassword = ?;");
					stmt.setString(1, currID);
					stmt.setString(2, pass);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						System.out.println(rs.getString("addressID"));
						userName.setText(rs.getString("fName"));
						usernameDisp.setText(rs.getString("fName") + " " + rs.getString("lName"));
						if(rs.getString("acceptedRequest") == null) {
							admissionStatus.setText("Pending...");
							hOr.setText("Pending...");
						}
						else if(rs.getString("acceptedRequest").equals("No")) {
							admissionStatus.setText("Rejected");
							hOr.setText("Rejected");
						}
						else if(rs.getString("acceptedRequest").equals("Yes")) {
							accepted = "Yes";
							admissionStatus.setText("Accepted");
							hOr.setText(rs.getString("hall"));
							subReq.setEnabled(false);
						}
						
						fillInBlanks();
						cardlayout.show(cards, "Student");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
			});
		signInbtn.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/SignIn_Btn.png")));
		signInbtn.setBounds(426, 297, 110, 44);
		stdSignPane.add(signInbtn);
		
		JLabel signInAdmin = new JLabel("");
		signInAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean isvalidated = false;
				try {
					String Ausername = adminUsername.getText(), Apassword = adminUserpass.getText();
					final String query = "SELECT * FROM admin WHERE username = ? AND admpassword = ?;";
					Connection adminConn = connect();
					PreparedStatement ptmst = adminConn.prepareStatement(query);
					ptmst.setString(1, Ausername);
					ptmst.setString(2, Apassword);
					ResultSet rs = ptmst.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getString("username"));
						isvalidated = true;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(isvalidated) {
					cardlayout.show(cards, "Admin");
					return;
				}
				else {
					System.out.println("Invalid username or password.");
				}
			}
		});
		signInAdmin.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/SignIn_Btn.png")));
		signInAdmin.setBounds(426, 297, 110, 44);
		adminSignPane.add(signInAdmin);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(UIManager.getBorder("OptionPane.border"));
		passwordField.setBounds(365, 248, 230, 38);
		stdSignPane.add(passwordField);
		
		adminUserpass = new JPasswordField();
		adminUserpass.setBounds(365, 248, 230, 38);
		adminUserpass.setBorder(UIManager.getBorder("OptionPane.border"));
		adminSignPane.add(adminUserpass);
		
		JLabel lblNewLabel_3_1 = new JLabel("Your password");
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(365, 220, 98, 15);
		stdSignPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Your password");
		lblNewLabel_3_2.setBounds(365, 220, 98, 15);
		lblNewLabel_3_2.setForeground(Color.BLACK);
		lblNewLabel_3_2.setFont(new Font("Montserrat", Font.PLAIN, 12));
		adminSignPane.add(lblNewLabel_3_2);
		
		studentID = new JTextField();
		studentID.setBorder(UIManager.getBorder("OptionPane.border"));
		studentID.setForeground(Color.DARK_GRAY);
		studentID.setBackground(SystemColor.window);
		studentID.setFont(new Font("Montserrat", Font.PLAIN, 11));
		studentID.setBounds(365, 148, 230, 38);
		stdSignPane.add(studentID);
		adminUsername = new JTextField();
		adminUsername.setBorder(UIManager.getBorder("OptionPane.border"));
		adminUsername.setForeground(Color.DARK_GRAY);
		adminUsername.setBackground(SystemColor.window);
		adminUsername.setFont(new Font("Montserrat", Font.PLAIN, 11));
		adminUsername.setBounds(365, 148, 230, 38);
		adminSignPane.add(adminUsername);
		adminUsername.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Your ID");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(365, 122, 81, 15);
		stdSignPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_3 = new JLabel("Your name");
		lblNewLabel_3_3.setForeground(Color.BLACK);
		lblNewLabel_3_3.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel_3_3.setBounds(365, 122, 81, 15);
		adminSignPane.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome Student, Sign in to continue");
		lblNewLabel_2.setFont(new Font("Lora", Font.BOLD, 16));
		lblNewLabel_2.setBounds(330, 58, 295, 21);
		stdSignPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Welcome Administrator, Sign in to continue");
		lblNewLabel_2_2.setFont(new Font("Lora", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(310, 58, 353, 21);
		adminSignPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Student_SignIn.png")));
		lblNewLabel.setBounds(0, 0, 257, 473);
		stdSignPane.add(lblNewLabel);
		
		JLabel lblNewLabelI = new JLabel("New label");
		lblNewLabelI.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/SignIn_Admin_Img.png")));
		lblNewLabelI.setBounds(0, 0, 257, 473);
		adminSignPane.add(lblNewLabelI);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Login_Form.png")));
		lblNewLabel_1.setBounds(340, 96, 283, 292);
		stdSignPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setBorder(null);
		lblNewLabel_11.setIcon(new ImageIcon(signInStd.class.getResource("/ImagesFinal/Login_Form.png")));
		lblNewLabel_11.setBounds(340, 90, 285, 292);
		adminSignPane.add(lblNewLabel_11);
		
		contentPane.add(cards);
		cardlayout.show(cards, getName());
	}

	
	public ArrayList<Student> readAllRequests(){
		
		ArrayList<Student> requestedUsers = new ArrayList<Student>();
		int counter = 0;
		
		String fetch = "SELECT ID, fName, lName, previousSchool, course, enrolment, yearCompleted FROM student WHERE admissionRequest = 'Yes' AND acceptedRequest = 'No'";
		
		try {
			Connection req = connect();
			java.sql.Statement readall = req.createStatement();
			
			ResultSet rs = readall.executeQuery(fetch);
			
			while(rs.next()) {
				Student st = new Student();
				st.setID(rs.getString("ID"));
				st.setfName(rs.getString("fName"));
				st.setlName(rs.getString("lName"));
				st.setPreviousSchool(rs.getString("previousSchool"));
				st.setCourse(rs.getString("course"));
				st.setEnrolment(rs.getString("enrolment"));
				st.setYearCompleted(rs.getDate("yearCompleted"));
				
				requestedUsers.add(counter, st);
				counter++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return requestedUsers;
	}
	
	public Connection connect() {

		
		
		Connection conn = null;
		try {
			
		    // Database parameters
		    String url       = "jdbc:mysql://localhost:3306/admission";
		    String user      = "root";
		    String password  = "";
			
		    // create a connection to the database
		    conn = DriverManager.getConnection(url, user, password);
		    System.out.println("Connection success!");
		} catch(SQLException e) {
		   System.out.println("Something's wrong!\n" + e.getMessage());
		}
		return conn;
	}

	public void fillInBlanks() {
		
		try {
			
			String queryStmt = "SELECT * FROM `student` WHERE `ID` = ?";
			Connection blanks = connect();
			PreparedStatement getBlanks = blanks.prepareStatement(queryStmt);
			getBlanks.setString(1, currID);
			
			ResultSet rs = getBlanks.executeQuery();
			
			while(rs.next()) {
				stdFirstName.setText(rs.getString("fName"));
				stdLastName.setText(rs.getString("lName"));
				stdMidName.setText(rs.getString("mName"));
				stdExName.setText(rs.getString("eName"));
				stdBirthDay.setText(rs.getString("birth"));
				stdAddress.setText(rs.getString("addressID"));
				fatherName.setText(rs.getString("fatherName"));
				motherName.setText(rs.getString("motherName"));
				guardianContact.setText(rs.getString("guardianContact"));
				guardianAdd.setText(rs.getString("guardianAddress"));
				previousSch.setText(rs.getString("previousSchool"));
				prevSchAdd.setText(rs.getString("schoolAddress"));
				dateAttend.setText(rs.getString("yearCompleted"));
				courseNamess.setText(rs.getString("course"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}