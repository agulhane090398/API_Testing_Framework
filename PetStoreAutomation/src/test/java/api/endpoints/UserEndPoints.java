package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payloads.User;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 * Created to perform CRUD operation - create/update/retrieve/delete
 */

public class UserEndPoints {
	
	public static Response createUser(User payload) {
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return res;
	}
	
	public static Response readUser(String uname) {
		Response res = given()
			.pathParam("username", uname)
		.when()
			.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUser(String uname, User payload) {
		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.pathParam("username", uname)
		.when()
			.put(Routes.update_url);
		
		return res;
	}
	
	public static Response deleteUser(String uname) {
		Response res = given()
			.pathParam("username", uname)
		.when()
			.delete(Routes.delete_url);
		
		return res;
	}

}
