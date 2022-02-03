package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		glue = {"stepDefinitions"},
		plugin= {"pretty",
		"json:target/jsonReports/cucumber-report.json",
				"junit:target/jsonReports/cucumber-report.xml"
		}
		)
		
//,tags= {"@DeletePlace"}

public class TestRunner {

}
