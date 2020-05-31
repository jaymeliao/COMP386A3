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

	private Province bc;
	private Employee bcEmployee;
		
	@Before
	public void setup() {
			
		utility = new Utility();
		province = new Province("alberta");
		employee = new Employee("Opeyemi Adesina", 22, 55000.00, province);

		// For BC tests
		bc = new Province("British Columbia");
		bcEmployee = new Employee("Tom Riddle", 40, 30000.00, bc);
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

		// Donovan Ollenberger-Kutzer
		// Testing BC
		// These should fail as the methods for BC havent been configured yet

		ProvincialTax bcTax = new ProvincialTax(bcEmployee);
		double taxAmount;

		assertEquals(1518.00, bcEmployee.totalDeductions(), 0);

		taxAmount = bcTax.computeTaxes(50000.00, "british columbia");
		assertEquals(2748.46, taxAmount, 0.01);

		taxAmount = bcTax.computeTaxes(90000.00, "british columbia");
		assertEquals(6011.83, taxAmount, 0.01);

		taxAmount = bcTax.computeTaxes(100000.00, "british columbia");
		assertEquals(7136.79, taxAmount, 0.01);

		taxAmount = bcTax.computeTaxes(150000.00, "british columbia");
		assertEquals(14092.90, taxAmount, 0.01);

		taxAmount = bcTax.computeTaxes(200000.00, "british columbia");
		assertEquals(22330.19, taxAmount, 0.01);

		taxAmount = bcTax.computeTaxes(300000.00, "british columbia");
		assertEquals(42090.19, taxAmount, 0.01);
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

		// Donovan Ollenberger-Kutzer's Tests)
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
