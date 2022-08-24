package com.highradius;

public class Payment 
{
	private int sl_no;
	private String business_code;
	private int customer_number;
	private String clear_date;
	private int buisness_year;
	private String doc_id;
	private String posting_date;
	private String document_create_date;
	private String due_in_date;
	private String invoice_currency;
	private String document_type;
	private int posting_id;
	private double total_open_amount;
	private String baseline_create_date;
	private String customer_payment_terms;
	private String invoice_id;
	
	
	
	public Payment(int sl, String bussiness_code, int customer_number, String clear_date, int buisness_year, String doc_id,
			String posting_date, String document_create_date, String due_in_date, String invoice_currency,
			String document_type, int posting_id, double total_open_amount, String baseline_create_date,
			String customer_payment_terms, String invoice_id) {
		super();
		this.sl_no = sl;
		this.business_code = bussiness_code;
		this.customer_number = customer_number;
		this.clear_date = clear_date;
		this.buisness_year = buisness_year;
		this.doc_id = doc_id;
		this.posting_date = posting_date;
		this.document_create_date = document_create_date;
		this.due_in_date = due_in_date;
		this.invoice_currency = invoice_currency;
		this.document_type = document_type;
		this.posting_id = posting_id;
		this.total_open_amount = total_open_amount;
		this.baseline_create_date = baseline_create_date;
		this.customer_payment_terms = customer_payment_terms;
		this.invoice_id = invoice_id;
	}
	public int getSl_no() {
		return sl_no;
	}
	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}
	public String getBussiness_code() {
		return business_code;
	}
	public void setBussiness_code(String bussiness_code) {
		this.business_code = bussiness_code;
	}
	public int getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(int customer_number) {
		this.customer_number = customer_number;
	}
	public String getClear_date() {
		return clear_date;
	}
	public void setClear_date(String clear_date) {
		this.clear_date = clear_date;
	}
	public int getBuisness_year() {
		return buisness_year;
	}
	public void setBuisness_year(int buisness_year) {
		this.buisness_year = buisness_year;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	public String getDocument_create_date() {
		return document_create_date;
	}
	public void setDocument_create_date(String document_create_date) {
		this.document_create_date = document_create_date;
	}
	public String getDue_in_date() {
		return due_in_date;
	}
	public void setDue_in_date(String due_in_date) {
		this.due_in_date = due_in_date;
	}
	public String getInvoice_currency() {
		return invoice_currency;
	}
	public void setInvoice_currency(String invoice_currency) {
		this.invoice_currency = invoice_currency;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public int getPosting_id() {
		return posting_id;
	}
	public void setPosting_id(int posting_id) {
		this.posting_id = posting_id;
	}
	public double getTotal_open_amount() {
		return total_open_amount;
	}
	public void setTotal_open_amount(double total_open_amount) {
		this.total_open_amount = total_open_amount;
	}
	public String getBaseline_create_datel() {
		return baseline_create_date;
	}
	public void setBaseline_create_datel(String baseline_create_date) {
		this.baseline_create_date = baseline_create_date;
	}
	public String getCustomer_payment_terms() {
		return customer_payment_terms;
	}
	public void setCustomer_payment_terms(String customer_payment_terms) {
		this.customer_payment_terms = customer_payment_terms;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	} 
	
	
	
}
