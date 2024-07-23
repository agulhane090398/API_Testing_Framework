package api.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String uName, String fName, String lName, String uMail, String pwd, String phn) {
		User userPayLoad = new User();
		
		userPayLoad.setId(Integer.parseInt(userId));
		userPayLoad.setUsername(uName);
		userPayLoad.setFirstName(fName);
		userPayLoad.setLastName(lName);
		userPayLoad.setEmail(uMail);
		userPayLoad.setPasword(pwd); 
		userPayLoad.setPhone(phn);
		
		Response res = UserEndPoints.createUser(userPayLoad);
//		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Data created for " + uName);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String uName) {
		Response res = UserEndPoints.deleteUser(uName);
		
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Data deleted for " + uName);
	}
	
}
