package com.qa.test.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.custom_listener.TestExecution.DisableListener;
import com.qa.resources.TestData;

import io.restassured.response.Response;

@DisableListener
@Listeners(com.qa.custom_listener.TestExecution.class)
public class createUsers {

	@BeforeMethod
	public void setUp() {

		RequestURL.BaseURL();

	}

	@Test
	public void postUser() throws NullPointerException {

		Response postres = given().body(TestData.Payload()).when().post(RequestURL.ServiceURL()).then().extract()
				.response();

		String postResString = postres.asString();

		System.out.println("Status code is - " + postres.getStatusCode());
        System.out.println("Time taken is - " + postres.getTime());
        System.out.println("The headers are - " + postres.getHeaders());
        System.out.println("The resposne body for create user");
        System.out.println(postResString);
        System.out.println("--------------------------------");

	}

}
