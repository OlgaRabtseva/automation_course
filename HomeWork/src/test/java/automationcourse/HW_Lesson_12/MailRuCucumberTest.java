package automationcourse.HW_Lesson_12;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/MailRu12.feature")
public class MailRuCucumberTest extends AbstractTestNGCucumberTests {}
