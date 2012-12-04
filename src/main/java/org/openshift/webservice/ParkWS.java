package org.openshift.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.openshift.data.DBConnection;
import org.openshift.data.Park;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


@RequestScoped
@Path("/parks")
public class ParkWS {
	
	@Inject 
	private DBConnection dbConnection;
	
	@GET()
	@Produces("application/json")
	public List getAllParks(){
		ArrayList<Park> allParksList = new ArrayList<Park>();
		DB db = dbConnection.getDB();
		DBCollection parkListCollection = db.getCollection("parkpoints");
		DBCursor cursor = parkListCollection.find();
		try {
			while(cursor.hasNext()) {
				allParksList.add((Park)cursor.next());
            }
        } finally {
            cursor.close();
        }

		return allParksList;
	}
	
	
/****** Just for testing purposes ***********/	
	@GET()
	@Path("/test")
	@Produces("text/plain")
	public String sayHello() {
		System.out.println("Where is this getting written");
	    return "Hello World In Both Places";
	}

}
