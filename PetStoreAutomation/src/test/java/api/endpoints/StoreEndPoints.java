package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.Store;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {
	
	static ResourceBundle getUrl() {
		return ResourceBundle.getBundle("routes");
	}
	
	public static Response createStore(Store payload) {
		String postUrl = getUrl().getString("store_post_url");
		
		Response res = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(payload).
			when().
			post(postUrl);
		return res;
	}
	
	public static Response readStore(long orderId) {
		String url = getUrl().getString("store_get_url");
		
		Response res = given().
			pathParam("orderId", orderId).
		when().get(url);
		
		return res;
	}
	
	public static Response deleteStore(long orderId) {
		String url = getUrl().getString("store_delete_url");
		
		Response res = given().pathParam("orderId", orderId).
		when().delete(url);
		
		return res;
	}


}
