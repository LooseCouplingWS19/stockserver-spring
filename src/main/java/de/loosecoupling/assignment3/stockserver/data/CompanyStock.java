package de.loosecoupling.assignment3.stockserver.data;

public class CompanyStock {

	private String companyName;
	private int companyValue;
	
	public CompanyStock(String companyName, int companyValue) {
		this.companyName = companyName;
		this.companyValue = companyValue;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getCompanyValue() {
		return companyValue;
	}
	public void setCompanyValue(int companyValue) {
		this.companyValue = companyValue;
	}
	
	
}
