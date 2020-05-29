package com.tax.utilities;

import java.util.*;
import TaxDemo.com.tax.domain.elements.*;


@SuppressWarnings("unused")
public class CanadianTax {

	private static Utility utility = new Utility();
	
	
	
	public static void main( String [] args ) {
		
		Province province = new Province("alberta");
		Employee employee = new Employee("Opeyemi Adesina", 22, 55000.00, province);
		
		//defining deductions
		EmploymentInsurance ei = new EmploymentInsurance(employee);
		ProvincialTax provincialTax = new ProvincialTax(employee);
		FederalTax federalTax = new FederalTax(employee);
		CanadianPensionPlan cpp = new CanadianPensionPlan(employee);
		
		
		
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		List<Double> netIncomes = utility.computeNetIncomes( employees );
		System.out.println("Employees' gross incomes CAD($): "+ netIncomes);		
	}
}