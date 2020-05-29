package TaxDemo.com.tax.domain.elements;

//line 281 "../../../../../../../ump/tmpjntyadj0/model.ump"
//line 349 "../../../../../../../ump/tmpjntyadj0/model.ump"
public class EmploymentInsurance extends Deduction
{

//------------------------
// MEMBER VARIABLES
//------------------------

//------------------------
// CONSTRUCTOR
//------------------------

public EmploymentInsurance(Employee aEmployee)
{
 super(aEmployee);
 // line 287 "../../../../../../../ump/tmpjntyadj0/model.ump"
 setAmount(computeEI( grossIncome() ));
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
* Beginning of Step 5 - Computing employee's EI
* operation computes employee's insurance
* @param grossIncome employee's gross income prior to any tax deduction
* @return ei deductible employment income
*/
// line 298 "../../../../../../../ump/tmpjntyadj0/model.ump"
private double computeEI(double grossIncome){
 double employeeEI = .0158 * grossIncome;
 if( employeeEI > 856.36 ) {
   employeeEI = 856.36;
 }

 return employeeEI;
}

}