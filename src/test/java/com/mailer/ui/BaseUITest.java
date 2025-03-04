package com.mailer.ui;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class BaseUITest {
    @Autowired
    protected Page page;

    @BeforeEach
    public void setUp() {
        page.navigate("about:blank");
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }
}
