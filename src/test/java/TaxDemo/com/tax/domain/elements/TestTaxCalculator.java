package TaxDemo.com.tax.domain.elements;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tax.utilities.Utility;


@SuppressWarnings("unused")
public class TestTaxCalculator {
		   
	private static Utility utility;
	private Province province;
	private Employee employee;
		
	@Before
	public void setup() {
			
		utility = new Utility();
		province = new Province("alberta");
		employee = new Employee("Opeyemi Adesina", 22, 55000.00, province);
	}
		
	@Test
	public void testProvince() {
		
		//tests whether the name is set correctly
		Assert.assertEquals("alberta", province.getName());
		province.setName("manitoba");
		Assert.assertEquals("manitoba", province.getName());
	}
	
	@Test
	public void testEI() {
		
		EmploymentInsurance ei = new EmploymentInsurance(employee); //856.36
		assertEquals(856.36, ei.getAmount(), 0);
	}
	
	@Test
	public void testProvincialTax() {
		
		ProvincialTax provincialTax = new ProvincialTax(employee); //5500 CAD
		assertEquals(5500, employee.totalDeductions(), 0);
	}
	
	@Test
	public void testFederalTax() {
		
		FederalTax federalTax = new FederalTax(employee); //8605.57
		assertEquals(8605.57, employee.totalDeductions(), 0);
	}
	
	@Test
	public void testCPP() {
		
		CanadianPensionPlan cpp = new CanadianPensionPlan(employee); //2887.50
		assertEquals(2887.50, employee.totalDeductions(), 0);
	}
	
	
	@Test
	public void testEmployee() {
		
		EmploymentInsurance ei = new EmploymentInsurance(employee);
		ProvincialTax provincialTax = new ProvincialTax(employee);
		FederalTax federalTax = new FederalTax(employee);
		CanadianPensionPlan cpp = new CanadianPensionPlan(employee);

		assertEquals(4, employee.getDeductions().size());
		assertEquals(17849.43, employee.totalDeductions(), 0);

		// Donovan's Tests B)
		Province emptyProvince = null;
		assertThrows(RuntimeException.class, () -> new Employee("Jim", 12, 13000.00, province));
		assertThrows(RuntimeException.class, () -> new Employee("Bob", 19, -10.00, province));
		assertThrows(RuntimeException.class, () -> new Employee("Bob", 19, 50000.00, emptyProvince));

		assertEquals(37150.57, employee.netIncome(), 0);

		assertEquals(0, employee.minimumNumberOfDeductions());

		assertEquals(ei, employee.getDeduction(0));

		employee.addOrMoveDeductionAt(ei, 3);
		assertEquals(3, employee.indexOfDeduction(ei));
		assertFalse(employee.addDeductionAt(ei, 4));

		assertEquals("[name:Opeyemi Adesina,age:22.0,grossIncome:55000.0]\n" +
				"  worksIn = [name:alberta]", employee.toString());

	}
		
	@After
	public void teardown() {
		
		this.employee.delete();
		this.province.delete();
	}
}
