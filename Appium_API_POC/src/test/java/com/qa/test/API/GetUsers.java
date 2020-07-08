package com.qa.test.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.custom_listener.TestExecution.DisableListener;

import io.restassured.response.Response;

@DisableListener
@Listeners(com.qa.custom_listener.TestExecution.class)
public class GetUsers {

	Response getRes;
	
	@BeforeMethod
	public void setUp() {
		RequestURL.BaseURL();

	}

	@Test
	public void getAllUsers() throws Exception {
		// Ability to get users list with the GET method
		getRes = given().when().get(RequestURL.GetUsersServiceURL()).then().assertThat().statusCode(200).extract()
				.response();

		String reponseBody = getRes.asString();

		System.out.println("Status code is : " + getRes.getStatusCode());
        System.out.println("The times taken is  : " + getRes.getTime());
        System.out.println("The headers are : ");
        System.out.println(getRes.getHeaders());

 

        System.out.println("The response body displaying the list of users");
        System.out.println(reponseBody);
        System.out.println("--------------------------------");
	}

}