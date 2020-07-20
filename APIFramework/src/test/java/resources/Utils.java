package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
		public static RequestSpecification specreq; //Declare the variable as static as by default java will make the variable as null as value is replaced and executes this everytime
	
	public RequestSpecification requestSpecification() throws IOException {
		
		
		
		if(specreq == null)                    //Will write the values in files for first time since specreq is null and subsequent responses will be written in same file without overiding
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		specreq =new RequestSpecBuilder().setBaseUri(getValue("baseUrl")).addQueryParam("key","qaclick123") //call baseUrl instead of key
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
			
			return specreq;
		}
		 return specreq;
	}
	
	public static String getValue(String key) throws IOException { //Declare as static so that can access request specification method
		
		Properties prop = new Properties(); //After creating a file with .properties extension used to scan the file
		FileInputStream fis = new FileInputStream("C:\\Automation Softwares\\WorkSpace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		
		prop.load(fis);
		return prop.getProperty(key); //Use key and not baseUrl since we should not hardcode it
		
		
	}

	public String getJsonPath(Response response, String key)
	{
		String res = response.asString();
		JsonPath js= new JsonPath(res);
		return js.get(key).toString();
	}
}
