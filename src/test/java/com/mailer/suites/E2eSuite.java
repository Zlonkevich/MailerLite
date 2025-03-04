package com.mailer.suites;

import com.mailer.common.TestMailerApplication;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = TestMailerApplication.class)
@Suite
@SuiteDisplayName("E2E UI test suite")
@SelectPackages({"com.mailer.ui"})
public class E2eSuite {
}
