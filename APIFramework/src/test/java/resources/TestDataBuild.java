package resources;

import java.util.ArrayList;

import pojo.GetMaps;
import pojo.Location;

public class TestDataBuild {
	
	public GetMaps addPlacePayload(String name, String language, String address) {
		
		GetMaps g= new GetMaps();// Create object to access GetMaps class
		 g.setAddress(address);
		 g.setAccuracy(50);
		 g.setLanguage(language);
		 g.setName(name);
		 g.setPhone_number("(+91) 983 893 3937");
		 g.setWebsite("http://google.com");
		 
		 Location l=new Location();//Create object to access Location class
		 l.setLat(-38.383494);
		 l.setLng(33.427362);
		 g.setLocation(l);

		 ArrayList<String> myList = new ArrayList<String>();// Create arraylist for passing values to the array
		 myList.add("shoe park");
		 myList.add("shop");
		 g.setTypes(myList);
		 
		 return g;
		
		
		  
	}

}
