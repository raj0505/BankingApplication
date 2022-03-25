package com.amdocs.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.model.Account;
import com.amdocs.model.User;
import com.amdocs.services.AccountService;
import com.amdocs.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@ApiOperation(value = "Creates a new account", notes = "creates a new new with auto generated accountId", response = Account.class

	)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Service Not Available"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 201, message = "Created") })
	@PostMapping("/createaccount")
	public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
		Account createdAccount = this.accountService.createAccount(account);
		logger.info("a new Account  is created with Account id :" + createdAccount.getAccountId());
		return new ResponseEntity<Account>(createdAccount, HttpStatus.CREATED);

	}

	@ApiOperation(value = "gets a account from accountId", notes = "please provide Accountid to get account details", response = Account.class

	)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Service Not Available"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content") })

	@GetMapping("/getaccount/{accountId}")
	public ResponseEntity<Account> getAccount(@Valid @PathVariable("accountId") long accountId) {
		Account accountRetrieved = this.accountService.getAccount(accountId);
		logger.info("Account with account id" + accountId + "is retrieved");
		return new ResponseEntity<Account>(accountRetrieved, HttpStatus.OK);

	}

	@ApiOperation(value = "gets all account ", notes = "it fetches all account details", response = Account.class

	)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Service Not Available"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content") })
	@GetMapping("/getallaccount")
	public ResponseEntity<List<Account>> getAllAccount() {
		List<Account> allAccount = this.accountService.getAllAccount();
		logger.info("All Accou Details are fetched");

		return new ResponseEntity<List<Account>>(allAccount, HttpStatus.OK);

	}

	@ApiOperation(value = "deletes a account   ", notes = "it deletes  account details from accountId", response = Account.class

	)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Service Not Available"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping("/deleteaccount/{accountId}")
	public ResponseEntity deleteAccount(@Valid @PathVariable("accountId") long accountId) {
		this.accountService.deleteAccount(accountId);
		logger.info("An Account with AccountId" + accountId + "is deleted from db");
		return new ResponseEntity(HttpStatus.ACCEPTED);

	}

	@ApiOperation(value = "deletes all Account   ", notes = "it deletes all  account details", response = Account.class

	)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Service Not Available"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping("/deleteallaccount")
	public ResponseEntity deleteAllAccount() {
		this.accountService.deleteAllAccount();
		logger.info("All Accounts are deleted from databases");
		return new ResponseEntity(HttpStatus.ACCEPTED);

	}

	@ApiOperation(value = "updates a account   ", notes = "it updates   account details with accountId", response = Account.class

	)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Service Not Available"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content") })
	@PutMapping("/updateaccount/{accountId}")
	public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account,
			@PathVariable("accountId") long accountId) {

		Account updatedAccount = this.accountService.updateAccount(account, accountId);
		logger.info("Accunt is updated with accountid" + accountId);
		return new ResponseEntity<Account>(updatedAccount, HttpStatus.ACCEPTED);

	}

}
