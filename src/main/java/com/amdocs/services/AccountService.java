package com.amdocs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amdocs.model.Account;
import com.amdocs.model.User;

public interface AccountService {

	public Account createAccount(Account account);

	public Account updateAccount(Account account, long accountId);

	public Account getAccount(long accountId);

	public List<Account> getAllAccount();

	public void deleteAccount(long accountId);

	public void deleteAllAccount();

}
