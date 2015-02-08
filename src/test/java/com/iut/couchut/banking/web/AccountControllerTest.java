package com.iut.couchut.banking.web;

import com.iut.couchut.banking.domain.Account;
import com.iut.couchut.banking.domain.AccountOperations;
import com.iut.couchut.banking.domain.AccountType;
import com.iut.couchut.banking.repository.AccountRepository;
import com.iut.couchut.banking.web.dto.AccountDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.iut.couchut.banking.domain.Account.newAccount;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.*;
import static com.iut.couchut.banking.web.exception.ErrorCode.NO_ENTITY_FOUND;
import static com.iut.couchut.banking.web.exception.ErrorCode.WRONG_ENTITY_INFORMATION;





/**
 * Created by Valentin on 08/02/2015.
 */

//@SqlGroup({
//        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:import-dev.sql"),
//        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete-dev.sql")
//})
public class AccountControllerTest extends WebAppTest{

    @Autowired
    private AccountRepository accountRepository;

    private int firstAccountId = 1;
    private String firstAccountName = "First Todo Unchecked";
    private long firstAccountBalance = 100;
    private AccountType firstAccounttype = AccountType.PEL;

    @Test
    public void should_Get_AllAccount_WithTwoTestAccountInResult() {
        given()
                .log().all()
                .when()
                .get("/account")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("[0].id", is(firstAccountId))
                .body("[0].name", is(firstAccountName))
                .body("[0].balance",is(firstAccountBalance))
                .body("[0].type", is(firstAccounttype));

    }

    @Test
    public void should_Get_OneTodoById_WithOneTestTodoInResult() {
        given()
                .log().all()
                .when()
                .get("/account/{id}", firstAccountId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("account.id", is(firstAccountId))
                .body("account.name", is(firstAccountName))
                .body("account.balance",is(firstAccountBalance))
                .body("account.type", is(firstAccounttype));
    }

    @Test
    public void shouldNot_Get_OneTodoById_WhenNotFound() {
        final int unknownId = 100;
        given()
                .log().all()
                .when()
                .get("/account/{id}", unknownId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value())
                .body("url", is("/account/" + unknownId))
                .body("errorCode", is(NO_ENTITY_FOUND.getCode()))
                .body("reasonCause", is(NO_ENTITY_FOUND.getDescription()));
    }

    @Test
    public void should_Create_OneTodo_Nominal() {
        final String accountName = "NewDesc";
        final String accountBalance= "100";
        final AccountType accountType = AccountType.PEL;

        Account accountToCreate = newAccount().withAll(accountName,accountBalance).build();
        AccountDTO accountDTO = new AccountDTO(accountToCreate);

        given()
                .header("Content-Type", "application/json")
                .body(accountDTO)
                .log().all()
                .when()
                .post("/account")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                .body("account.id", notNullValue())
                .body("account.name", is(accountName))
                .body("account.balance", is(accountBalance));

        // And then assert what has been done in db
        Account createdAccount = accountRepository.findByName(accountName);

        assertThat(createdAccount, notNullValue());
        assertThat(createdAccount.getId(), notNullValue());
        assertThat(createdAccount.getName(), is(accountName));
    }

    @Test
    public void shouldNot_Create_Todo_WhenNoDescription() {
        AccountDTO accountDTO = new AccountDTO(newAccount().build());

        given()
                .header("Content-Type", "application/json")
                .body(accountDTO)
                .log().all()
                .when()
                .post("/todo")
                .then()
                .statusCode(BAD_REQUEST.value())
                .log().all()
                .body("url", is("/todo"))
                .body("errorCode", is(WRONG_ENTITY_INFORMATION.getCode()))
                .body("reasonCause", is(WRONG_ENTITY_INFORMATION.getDescription()));
    }

    @Test
    public void should_Update_Todo_Nominal() {
        final String updateName = "NewDesc";
        final String updateBalance= "100";
        final AccountType updateType = AccountType.PEL;

        AccountDTO accountDTO = new AccountDTO(newAccount().withAll(updateName, updateBalance).build());

        given()
                .header("Content-Type", "application/json")
                .body(accountDTO)
                .log().all()
                .when()
                .put("/account/{id}", firstAccountId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("account.id", is(1))
                .body("account.name", is(updateName))
                .body("account.balance", is(updateBalance))
                .body("account.type", is(updateType));

        Account updatedTodo = accountRepository.findByName(updateName);

        assertThat(updatedTodo, notNullValue());
        assertThat(updatedTodo.getId(), is(Long.valueOf(firstAccountId)));
        assertThat(updatedTodo.getName(), is(updateName));
    }

    @Test
    public void shouldNot_Update_Todo_WhenTodoNotFound() {
        Long unknownTodoId = 100L;
        final String updateName = "NewDesc";
        final String updateBalance= "100";
        final AccountType updateType = AccountType.PEL;

        AccountDTO accountDTO = new AccountDTO(newAccount().withAll(updateName,updateBalance).build());

        given()
                .header("Content-Type", "application/json")
                .body(accountDTO)
                .log().all()
                .when()
                .put("/account/{id}", unknownTodoId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value())
                .body("url", is("/account/100"))
                .body("errorCode", is(NO_ENTITY_FOUND.getCode()))
                .body("reasonCause", is(NO_ENTITY_FOUND.getDescription()));
    }

    @Test
    public void should_Delete_Todo_Nominal() {
        long initialTotalEntries = accountRepository.count();

        // Start with api test
        given()
                .log().all()
                .when()
                .delete("/account/{id}", firstAccountId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());

        // Recheck todos db count and verify if it has well changed by -1
        long finalTotalEntries = accountRepository.count();
        assertThat(finalTotalEntries, not(initialTotalEntries));
        assertThat(finalTotalEntries, is(initialTotalEntries - 1));
    }

    @Test
    public void shouldNot_Delete_Todo_WhenNotFound() {
        long initialTotalEntries = accountRepository.count();

        given()
                .log().all()
                .when()
                .delete("/account/{id}", 100)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value())
                .body("url", is("/account/100"))
                .body("errorCode", is(NO_ENTITY_FOUND.getCode()))
                .body("reasonCause", is(NO_ENTITY_FOUND.getDescription()));

        long finalTotalEntries = accountRepository.count();
        assertThat(finalTotalEntries, is(initialTotalEntries));
    }



}
