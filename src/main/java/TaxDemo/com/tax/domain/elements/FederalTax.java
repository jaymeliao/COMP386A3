package TaxDemo.com.tax.domain.elements;

//line 207 "../../../../../../../ump/tmpjntyadj0/model.ump"
//line 339 "../../../../../../../ump/tmpjntyadj0/model.ump"
public class FederalTax extends Tax
{

//------------------------
// MEMBER VARIABLES
//------------------------

//------------------------
// CONSTRUCTOR
//------------------------

public FederalTax(Employee aEmployee)
{
 super(aEmployee);
 // line 214 "../../../../../../../ump/tmpjntyadj0/model.ump"
 setAmount(computeTax( grossIncome() ));
 // END OF UMPLE AFTER INJECTION
}

//------------------------
// INTERFACE
//------------------------

public void delete()
{
 super.delete();
}


	/**
	* 
	* operation computes provincial taxes for the the province of alberta
	* @param grossIncome employee's gross income prior to any tax deduction
	* @return federalTax deductible federal tax
	*/
	// line 225 "../../../../../../../ump/tmpjntyadj0/model.ump"
	private double computeTax(double grossIncome){
	 //setting up tax percentages and their respective lower bounds
	 double [] taxPercentages = {0.15, 0.205, 0.26, 0.29};
		double [] lowerBounds = {48535.0, 97069.0, 150473.0, 214368.0};
	 
	 //federalTaxBracket = "15.0% [$0 .. $48,535.01)"
	 if( grossIncome >= 0.0 && grossIncome < 48535.01) { 
	   return 0.15 * grossIncome;
	 }
	 
	 //federalTaxBracket = "20.5% [$48,535.01 .. $97,069.01)"
	 else if( grossIncome >= 48535.01 && grossIncome < 97069.01) {
	   return ( grossIncome - 48535.01 ) * .205 + computeTaxCategoryPay( taxPercentages, lowerBounds, 0 );
	 }
	 
	 //federalTaxBracket = "26.0% [$97,069.01 .. $150,473.01)
	 else if( grossIncome >= 97069.01 && grossIncome < 150473.01) {
	   return ( grossIncome - 97069.01 ) * .26 + computeTaxCategoryPay( taxPercentages, lowerBounds, 1 );
	 }
	 
	  //federalTaxBracket = "29.0% [$150,473.01 .. $214,368.01)"
	 else if( grossIncome > 150473.01 && grossIncome < 214368.01 ) {
	    return ( grossIncome - 150473.01 ) * .29 + computeTaxCategoryPay( taxPercentages, lowerBounds, 2 );
	 }
	 
	  //federalTaxBracket = "33.0% [$214,368.01 .. )"
	 else {
	    return ( grossIncome - 214368.01 ) * .33 + computeTaxCategoryPay( taxPercentages, lowerBounds, 3 );
	 }
	}
}
