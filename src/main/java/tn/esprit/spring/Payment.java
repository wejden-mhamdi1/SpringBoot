package tn.esprit.spring;

public class Payment {
	public enum Currency{
        usd, eur;
    }

    private String description;
    private float amount;
    private Currency currency;
	public String getDescription() {
		return description;
	}
	public float getAmount() {
		return amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAmount(float f) {
		this.amount = f;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}



