package my.domain.name.FBLogin1;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
	    features = {"feature"},
	    glue = {"steps"},
	    monochrome = true
	
)


public class TestRunner {
 
}




