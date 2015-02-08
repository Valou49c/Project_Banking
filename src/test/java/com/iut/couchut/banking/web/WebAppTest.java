package com.iut.couchut.banking.web;

import com.iut.couchut.banking.BankingCouchutApplication;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.iut.couchut.banking.config.Profiles.DEV;


/**
 * Created by Valentin on 08/02/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BankingCouchutApplication.class)
@WebIntegrationTest
@ActiveProfiles(DEV)
@Ignore
public class WebAppTest {

    @Value("${local.http.baseuri}")
    private String baseURI;

    @Value("${local.http.port}")
    private int localPort;

    /**
     * Do not override this method in your test,
     * otherwise you'll get a Connection refused
     */
    @Before
    public void initialSetup() {
        RestAssured.baseURI = baseURI;
        RestAssured.port = localPort;
    }


}
