package common;

import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(publish = true, features = {
//		 "src/test/resources/feature",
//		"src/test/resources/feature/API_RestAssured.feature",
		"src/test/resources/feature/ManagementYoEdu.feature",
}
		, glue = { "common", "stepsDefinition" } // define steps dc khai báoo
		, tags = " @hocvien "
		// , dryRun = true
		, plugin = { "pretty",
				"summary",
				"junit:target/cucumber.xml",
				"html:target/cucumber/index.html",
				"json:target/cucumber.json",
		}, monochrome = true)

@Test
public class RunParallel extends AbstractTestNGCucumberTests {

}

