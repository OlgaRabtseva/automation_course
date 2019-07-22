package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/Login.feature")
public class LoginTest extends AbstractTestNGCucumberTests {}
