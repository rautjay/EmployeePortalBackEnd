package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Bills {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int billsId;

	private float amount;

	@Lob
	private byte[] attachement;

	private String filename;

	private String fileUri;

	public Bills() {
		super();
	}

	public int getBillsId() {
		return billsId;
	}

	public void setBillsId(int billsId) {
		this.billsId = billsId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public byte[] getAttachement() {
		return attachement;
	}

	public void setAttachement(byte[] attachement) {
		this.attachement = attachement;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public Bills(float amount, byte[] attachement, String filename) {
		super();
		this.amount = amount;
		this.attachement = attachement;
		this.filename = filename;
	}



}
