package api.endpoints;

/*
 * From Swagger we will get all url
 * https://petstore.swagger.io/#/user/getUserByName
 * base url : https://petstore.swagger.io/v2
 * end points : /#/user/getUserByName
 */

public class Routes {
	public static String base_url  = "https://petstore.swagger.io/v2";

//	User
	public static String post_url  = base_url + "/user";
	public static String get_url  = base_url + "/user/{username}";
	public static String update_url  = base_url + "/user/{username}";
	public static String delete_url  = base_url + "/user/{username}";
	
//	Store
//	correct below urls
	public static String store_post_url  = base_url + "/store";
	public static String store_get_url  = base_url + "/store/{username}";
	public static String store_update_url  = base_url + "/store/{username}";
	public static String store_delete_url  = base_url + "/store/{username}";
	
//	Pet
//	Here pet URL
	
}
