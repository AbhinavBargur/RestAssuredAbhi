package resources;

//enum is a special class in java which has collection of methods or constants

public enum APIResources {
	

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;

	APIResources(String resource) {
		this.resource= resource; // Loads /maps/api............. and assigned to global variable
		
	}
			
	public String getResource() {
		
		return resource; // this returns whatever is loaded in the reource
		
	}
}
