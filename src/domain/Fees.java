package domain;

import java.math.BigDecimal;

public enum Fees {
	
	MISC(new BigDecimal("2000.00")), UNDERGRADUATE(new BigDecimal("2000.00")), GRADUATE(new BigDecimal("4000.00"));
	
	private BigDecimal cost;
	
	private Fees(BigDecimal bigDecimal){
		cost = bigDecimal;
	}
	
	public BigDecimal getCost(){
		return cost;
	}
	
	public static BigDecimal getCost(EducationLevel level){
		return Fees.valueOf(Fees.class,level.toString()).getCost();
	}

}
