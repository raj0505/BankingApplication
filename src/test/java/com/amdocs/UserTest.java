package com.amdocs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.amdocs.model.User;

import com.amdocs.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class UserTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	//@Rollback(false)
	@Order(1)
	public void testCreateUser()
	{
		 User user=new User();
		 user.setName("amana");
		// user.setDateOfBirth("01/05/1997");
		 user.setEmailId("r15.polytechnic@gmail.com");
		 user.setMobileNumber("9056486858");
		 user.setGender('M');
		 user.setSecondaryMobile("9431837772");
		
		 
		 User createdUser = userRepository.save(user);
	
	assertNotNull(createdUser);
	
	}
	
	@Test
	@Order(2)
	public void testGetUserExist() {
		long userId=38;
		User Retrieveduser = userRepository.findByUserId(userId);
		assertThat(Retrieveduser.getUserId()).isEqualTo(userId);
		
		
		
	}
	@Test
	@Order(3)
	public void testGetUserNotExist() {
		long userId=1;
		User Retrieveduser = userRepository.findByUserId(userId);
	
		assertNull(Retrieveduser);
		
		
		
	}
	
	@Test
//	@Rollback(false)
	@Order(4)
	public void testUpdateUser() {
		String name="testadmin";
		User user=new User();
		user.setName(name);
		user.setUserId(38);
		user.setEmailId("test@gmail.com");
	//	user.setDateOfBirth("11111111");
		user.setMobileNumber("1234567890");
		
		userRepository.save(user);
		
		User updatedUser = userRepository.findByName(name);
		
		assertThat(updatedUser.getName()).isEqualTo(name);
	}
	
	@Test
	@Order(5)
	public void testGetAllUser() {
		List<User> users=(List<User> )userRepository.findAll();
		assertThat(users).size().isGreaterThan(0);
	}
	
	@Test
	@Order(6)
	public void testDeleteUser() {
		long userId=38;
		
		boolean isUserPresentBeforeDelete  =userRepository.findById(userId).isPresent();
		
		 userRepository.deleteById(userId);
		 boolean isUserPresentAfterDelete  =userRepository.findById(userId).isPresent();
			assertTrue(isUserPresentBeforeDelete);
			assertFalse(isUserPresentAfterDelete);
			
		
	}
	
	
}


