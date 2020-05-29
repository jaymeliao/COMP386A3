package TaxDemo.com.tax.domain.elements;

//line 256 "../../../../../../../ump/tmpjntyadj0/model.ump"
//line 344 "../../../../../../../ump/tmpjntyadj0/model.ump"
public class CanadianPensionPlan extends Deduction
{

//------------------------
// MEMBER VARIABLES
//------------------------

//------------------------
// CONSTRUCTOR
//------------------------

public CanadianPensionPlan(Employee aEmployee)
{
 super(aEmployee);
 // line 261 "../../../../../../../ump/tmpjntyadj0/model.ump"
 setAmount(computeCPP( grossIncome() ));
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
* Beginning of Step 4 - Computing employee's CPP
* operation computes pension deductions
* @param grossIncome employee's gross income prior to any tax deduction
* @return cpp deductible federal tax
*/
// line 272 "../../../../../../../ump/tmpjntyadj0/model.ump"
private double computeCPP(double grossIncome){
 double employeeCPP = .0525 * grossIncome;
 if( employeeCPP > 2898 ) {
   employeeCPP = 2898;
 }
 
 return employeeCPP;
}

}
