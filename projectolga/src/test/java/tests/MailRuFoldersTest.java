package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/MailRuFolders.feature")
public class MailRuFoldersTest extends AbstractTestNGCucumberTests {}
