package com.LoginTest.server;

import com.LoginTest.server.controller.ReturnUserController;
import com.LoginTest.server.controller.ValidateAuthController;
import com.LoginTest.server.controller.VerifyExistenceController;
import com.LoginTest.server.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ServerTests {

	@Autowired
	private ReturnUserController returnUserController;

	@Autowired
	private ValidateAuthController validateAuthController;

	@Autowired
	private VerifyExistenceController verifyExistenceController;

	/*
       This test verifies if the api link localhost/{email} returns a Htpp status code as "ACCEPTED" if an user tries to register with an email
       that doesn't exist and "IM_USED" if the email already exists in the database.
    */
	@Test
	void verifyExistenceTest() {
		 HttpStatus httpStatus=verifyExistenceController.checkUser("m@m");
		 HttpStatus httpStatus1=verifyExistenceController.checkUser("m@mmm");

		 assertEquals(httpStatus,HttpStatus.IM_USED);
		 assertEquals(httpStatus1,HttpStatus.ACCEPTED);
	}
	/*
		This test verifies if the api link localhost/get/{email} return the wanted user as a user form, it only checks the password and the email.
	 */
	@Test
	void returnUserTest() {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		User u=new User();
		u.setEmail("m@m");
		u.setPassword("pass");

		User uTest=returnUserController.returnUser("m@m");
		assertEquals(uTest.getEmail(),u.getEmail());
		assertTrue(bCryptPasswordEncoder.matches(u.getPassword(),uTest.getPassword()));
	}

	/*
		This test verifies if the api link localhost/checkuser/{email} return an "ACCEPTED" Http status code that will be used for
		 validating the authentication of the client
	 */
	@Test
	void validateAuthTest() {
		User u2=new User();
		u2.setEmail("m@m");
		u2.setPassword("pass");
		ResponseEntity<HttpStatus> uTest=validateAuthController.validateAuth("m@m",u2);
		assertEquals(uTest.getStatusCode(),HttpStatus.ACCEPTED);
	}

}
