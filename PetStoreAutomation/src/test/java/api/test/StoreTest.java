package api.test;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payloads.Store;
import api.utilities.Status;
import io.restassured.response.Response;

public class StoreTest {

	Faker faker;
	Store payload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		payload = new Store();
		
//		payload.setId(faker.idNumber().hashCode());
//		payload.setId(10000);
		payload.setPetId(faker.idNumber().hashCode());
		payload.setQuantity(faker.number().hashCode());
//		payload.setShipDate(faker.date().toString());
		payload.setShipDate("2024-07-27T15:25:16.304Z");
		
		payload.setStatus(Status.getRandomStatus().toString());
		payload.setComplete(faker.bool().bool());
		
		System.out.println(payload.getId());
		System.out.println(payload.getPetId());
		System.out.println(payload.getQuantity());
		System.out.println(payload.getShipDate());
		System.out.println(payload.getStatus());
		System.out.println(payload.getComplete());
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostStore() {
		logger.info("*****************Creating store*****************");
		
		Response res = StoreEndPoints.createStore(payload);
		res.then().log().all();
		String responseString = res.asString();

        // Create a JSONObject from the response String
        JSONObject objJsonObject1 = new JSONObject(responseString);

        // Print the "id" field
        try {
            long id = objJsonObject1.getLong("id");
            System.out.println("ID: " + id);
            payload.setId(id);
        } catch (JSONException e) {
            System.out.println("JSONObject['id'] not found.");
        }
				
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****************Store created*****************");
		
	}
	
	@Test(priority = 2)
	public void testGetStore() {
		logger.info("*****************Reading store*****************");
		
		Response res = StoreEndPoints.readStore(this.payload.getId());
		res.then().log().all();
		
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****************Store*****************");

	}
	
	@Test(priority = 3)
	public void testDeleteStore() {
		logger.info("*****************Deleting store*****************");

		Response res = StoreEndPoints.deleteStore(this.payload.getId());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*****************Deleted*****************");

	}
}
