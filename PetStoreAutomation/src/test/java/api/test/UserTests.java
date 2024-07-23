package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	
	/*
	 * Need to create 4 test
	 */
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPasword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
//		logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		logger.info("*****************Creating user*****************");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****************User created*****************");
		System.out.println("Passed test 1");

	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("*****************Reading user*****************");

		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Passed test  2");
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		logger.info("*****************Updating user*****************");

//		update data
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response res = UserEndPoints.updateUser(this.userPayload.getUsername() ,userPayload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
//		checking data after update
		Response ress = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(ress.getStatusCode(), 200);
		System.out.println("Passed test  3");

	}

	@Test(priority=4)
	public void testDeleteUser() {
		logger.info("*****************Deleting user*****************");

		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Passed test  4");
	}
}
