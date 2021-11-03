package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;

public class Student {
	
	String ID, password, lName, fName, mName, eName, gender, address; //Personal info
	String fatherName, motherName, guardianContact, guardianAddress; //Guardian info
	String previousSchool, schoolAddress; //Alma-matter info
	String course, year, enrolment, hall; //Course-info
	boolean submitRequest = false;
	Date birth, yearCompleted;
	private File transcript;
	
	public Student() {
		
	}
	
	public Student(String lName, String fName, String mName, String eName, String gender, String address, String birth) {
		this.lName = lName;
		this.fName = fName;
		this.mName = mName;
		this.eName = eName;
		this.gender = gender;
		this.address = address;
		this.birth = java.sql.Date.valueOf(birth);
		//this.hall = "Pending";
		//this.IsAccepted = false;
	}
	
	public void guardians(String fatherName, String motherName, String guardianContact, String guardianAddress) {
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.guardianContact = guardianContact;
		this.guardianAddress = guardianAddress;
	}
	
	public void school(String previousSchool, String schoolAddress, String yearCompleted,  File trans) {
		this.previousSchool = previousSchool;
		this.schoolAddress = schoolAddress;
		this.yearCompleted = java.sql.Date.valueOf(yearCompleted);
		this.transcript = trans;
	}
	
	public void admission(String course, String year, String enrolment) {
		this.course = course;
		this.year = year;
		this.enrolment = enrolment;
	}
	
	public void admissionI(String course, String year, String enrolment) {
		this.course = course;
		this.year = year;
		this.enrolment = enrolment;
		submitRequest = true;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getGuardianContact() {
		return guardianContact;
	}
	public void setGuardianContact(String fatherContact) {
		this.guardianContact = fatherContact;
	}
	public String getGuardianAddress() {
		return guardianAddress;
	}
	public void setGuardianAddress(String motherContact) {
		this.guardianAddress = motherContact;
	}
	public String getPreviousSchool() {
		return previousSchool;
	}
	public void setPreviousSchool(String previousSchool) {
		this.previousSchool = previousSchool;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getEnrolment() {
		return enrolment;
	}
	public void setEnrolment(String enrolment) {
		this.enrolment = enrolment;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public boolean submitRequest() {
		return submitRequest;
	}
	public void setSubmitRequest(boolean submitReq) {
		submitRequest = submitReq;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getYearCompleted() {
		return yearCompleted;
	}
	public void setYearCompleted(Date yearCompleted) {
		this.yearCompleted = yearCompleted;
	}

	public FileInputStream getTranscript() throws FileNotFoundException {
		return new FileInputStream(transcript);
	}

	public void setTranscript(File transcript) {
		this.transcript = transcript;
	}
	
}
