package com.amdocs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.amdocs.model.Account;
import com.amdocs.model.User;
import com.amdocs.repository.AccountRepository;
import com.amdocs.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class AccountTest {
	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	//@Rollback(false)
	
	public void testCreateAccount()
	{
		 Account account=new Account();
		 account.setBranchName("PUNE");
		 account.setAccountType("Savings");
		 account.setAccountBalance(123.25);
		 
		Account createdAccount = accountRepository.save(account);
		
	assertNotNull(createdAccount);
	
	}
	
	@Test
	@Order(2)
	public void testGetAccountExist() {
		long accountId=40;
		Account RetrieveAccount = accountRepository.findByAccountId(accountId);
		assertThat(RetrieveAccount.getAccountId()).isEqualTo(accountId);
		
		
		
	}
	@Test
	@Order(3)
	public void testGetAccountNotExist() {
		long accountId=1;
		Account RetrievedAccount = accountRepository.findByAccountId(accountId);
		
	
		assertNull(RetrievedAccount);
		
		
		
	}
	
	@Test
//	@Rollback(false)
	@Order(4)
	public void testUpdateUser() {
		long accountId=40;
		Account account=new Account();
		account.setAccountBalance(123.52);
		account.setAccountType("Savings");
		account.setBranchName("manglore");
		accountRepository.save(account);
		
		Account updatedAccount = accountRepository.findById(accountId).get();
		
		assertThat(updatedAccount.getAccountId()).isEqualTo(accountId);
	}
	
	@Test
	@Order(5)
	public void testGetAllAccount() {
		List<Account> accounts=(List<Account> )accountRepository.findAll();
		assertThat(accounts).size().isGreaterThan(0);
	}
	
	@Test
	@Order(6)
	public void testDeleteAccount() {
		long accountId=42;
		
		boolean isAccountPresentBeforeDelete  =accountRepository.findById(accountId).isPresent();
		
		 accountRepository.deleteById(accountId);
		 boolean isAccountPresentAfterDelete  =accountRepository.findById(accountId).isPresent();
			assertTrue(isAccountPresentBeforeDelete);
			assertFalse(isAccountPresentAfterDelete);
			
		
	}
}


