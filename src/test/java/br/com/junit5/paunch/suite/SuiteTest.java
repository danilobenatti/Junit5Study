package br.com.junit5.paunch.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName(value = "Suite Test")
//@SelectClasses(value = { UserTest.class, UserServiceTest.class })
@SelectPackages(
	value = { "br.com.junit5.paunch.domain", "br.com.junit5.paunch.service" })
public class SuiteTest {
	
}
