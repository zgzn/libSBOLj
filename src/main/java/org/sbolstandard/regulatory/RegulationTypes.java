package org.sbolstandard.regulatory;

public class RegulationTypes {

	public static final RegulationType getInducibleRegulation() {
		RegulationType ind = RegulatoryFactory.createRegulationType();
		ind.setName("INDUCTION");
		ind.setDescription("This is an inducible regulatory interaction!");
		ind.setDisplayId("induces");
		return ind;
	}
	
	public static final RegulationType getRepressingRegulation() {
		RegulationType ind = RegulatoryFactory.createRegulationType();
		ind.setName("REPRESSION");
		ind.setDescription("This is a repressible regulatory interaction!");
		ind.setDisplayId("represses");
		return ind;
	}
}
