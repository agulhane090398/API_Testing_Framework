package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 * Created to perform CRUD operation - create/update/retrieve/delete
 */

public class UserEndPoints2FromPropertisFile {
	
	static ResourceBundle getUrl(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");//Will load properties file
		return routes;
	}
	
	public static Response createUser(User payload) {
		String post_url = getUrl().getString("post_url");
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return res;
	}
	
	public static Response readUser(String uname) {
		String get_url = getUrl().getString("get_url");

		Response res = given()
			.pathParam("username", uname)
		.when()
			.get(get_url);
		
		return res;
	}
	
	public static Response updateUser(String uname, User payload) {
		String update_url = getUrl().getString("update_url");
		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.pathParam("username", uname)
		.when()
			.put(update_url);
		
		return res;
	}
	
	public static Response deleteUser(String uname) {
		String delete_url = getUrl().getString("delete_url");
		Response res = given()
			.pathParam("username", uname)
		.when()
			.delete(delete_url);
		
		return res;
	}

}
