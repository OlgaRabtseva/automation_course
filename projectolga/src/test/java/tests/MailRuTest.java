package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/MailRu12.feature")
public class MailRuTest extends AbstractTestNGCucumberTests {}
