package my.company.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ru.yandex.qatools.allure.annotations.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import com.csvreader.CsvReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = Parameterized.class)
@Title("������������ �������")
public class DivCalcTest {
	private final CalcParams params;

	public static class CalcParams {
		public int firstParameter;
		public int secondParameter;
		public char operation;
		public int expectedResult;
	}

	public DivCalcTest(CalcParams parameters) {
		this.params = parameters;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() throws Exception  {
		Collection<Object[]> para = new ArrayList<Object[]>();
		String sPath = "src/test/resources/sample.csv";

		CsvReader csv = new CsvReader(sPath,';');
		while (csv.readRecord()) {
			CalcParams testContainer = new CalcParams();
			testContainer.firstParameter = Integer.parseInt(csv.get(0));
			testContainer.secondParameter = Integer.parseInt(csv.get(1));
			testContainer.operation = csv.get(2).charAt(0);
			testContainer.expectedResult = Integer.parseInt(csv.get(3));
			if (testContainer.operation=='/') {
				para.add(new Object[] { testContainer });
			}
		}
		csv.close();
		return para;
	}
		
	@Test()
	@Title("�������� ���������� �������") 
	public void myTest() throws Exception {
		Calculator calc = new Calculator(params.firstParameter,params.secondParameter);
		int res = calc.getAddResult();
		saveTextLog("���������","("+params.firstParameter+"/"+params.secondParameter+") == " + params.expectedResult);
	       	assertThat("���������("+params.firstParameter+"/"+params.secondParameter+") �� ����� " + params.expectedResult, (params.firstParameter/params.secondParameter)==params.expectedResult );
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String attachName, String message) {
		return message;
	}	
}
