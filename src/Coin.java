public class Coin {

	private String name;
	private String abbreviation;
	private String symbol;
	private double exchange;

	public Coin(String name, String abbreviation, String symbol, double exchange) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.symbol = symbol;
		this.exchange = exchange;
	}

	public Coin() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getExchange() {
		return exchange;
	}

	public void setExchange(double exchange) {
		this.exchange = exchange;
	}
	
	@Override
	public String toString() {
		return abbreviation;
	}

}
