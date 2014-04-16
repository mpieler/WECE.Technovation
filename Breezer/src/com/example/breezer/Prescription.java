package com.example.breezer;

public class Prescription {
	//public String prescription;
	//public static long id;
	public String prescriptionName;
	public String prescriptionSize;
	public String prescriptionColor;
	public String prescriptionFrequency;
	public String prescriptionAmount;
	public String prescriptionStartdate;
	public String prescriptionRemaining;
	
	public Prescription(){
		
	}
	
	public Prescription(String pname, String size, String color, String frequ, String amt, String start, String remaining){
		
	}
	
	//get/setters

	public String getPrescriptionName() {
		return prescriptionName;
	}
	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}
	public String getPrescriptionSize() {
		return prescriptionSize;
	}
	public void setPrescriptionSize(String prescriptionSize) {
		this.prescriptionSize = prescriptionSize;
	}
	public String getPrescriptionColor() {
		return prescriptionColor;
	}
	public void setPrescriptionColor(String prescriptionColor) {
		this.prescriptionColor = prescriptionColor;
	}
	public String getPrescriptionFrequency() {
		return prescriptionFrequency;
	}
	public void setPrescriptionFrequency(String prescriptionFrequency) {
		this.prescriptionFrequency = prescriptionFrequency;
	}
	public String getPrescriptionAmount() {
		return prescriptionAmount;
	}
	public void setPrescriptionAmount(String prescriptionAmount) {
		this.prescriptionAmount = prescriptionAmount;
	}
	public String getPrescriptionStartdate() {
		return prescriptionStartdate;
	}
	public void setPrescriptionStartdate(String prescriptionStartdate) {
		this.prescriptionStartdate = prescriptionStartdate;
	}
	public String getPrescriptionRemaining() {
		return prescriptionRemaining;
	}
	public void setPrescriptionRemaining(String prescriptionRemaining) {
		this.prescriptionRemaining = prescriptionRemaining;
	}
	
	@Override
	public String toString(){
		return prescriptionName;
	}
	
}
