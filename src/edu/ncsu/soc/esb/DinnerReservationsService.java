package edu.ncsu.soc.esb;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import java.util.UUID;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import org.apache.log4j.Logger;

public class DinnerReservationsService extends AbstractActionLifecycle {
	
	protected ConfigTree config;
	private Logger logger = Logger.getLogger(DinnerReservationsService.class);
	
    public DinnerReservationsService(ConfigTree config) { this.config = config; } 
	
	public Message process(Message message) throws Exception {
		Integer reserveDinner=Integer.valueOf((String) message.getBody().get("reserveDinner"));
		Boolean dinnerSucceed =Boolean.parseBoolean(message.getBody().get("dinnerSucceed").toString());
		Boolean cancelDinner=Boolean.parseBoolean(message.getBody().get("cancelDinner").toString());
		String customerName=message.getBody().get("customerName").toString();
       		
		try{
					
			
		if (reserveDinner>0 &&  dinnerSucceed== true&& cancelDinner !=true){
			UUID rNumber = UUID.randomUUID();
			message.getBody().add("dinnerReservationRef",rNumber.toString());
			
			message.getBody().add("dinnerCancelled","false");
		message.getBody().add("dinnerReserved","true");
		}
		if((reserveDinner>0) && (dinnerSucceed==false) && (cancelDinner!=true)){
	
	message.getBody().add("dinnerReservationRef","");
			message.getBody().add("dinnerCancelled","false");
			 message.getBody().add("dinnerReserved","false");
			
		}
	if(cancelDinner == true){
		if(message.getBody().get("dinnerReservationRef")==null)
		{
		UUID rNumber = UUID.randomUUID();
		message.getBody().add("dinnerReservationRef",rNumber.toString());
		}
		
		 message.getBody().add("dinnerReserved","false");
			message.getBody().add("dinnerCancelled","true");
			
		}
		
		}
		catch (Exception e){
			logger.info("dinner service error: "+e.getMessage());
		}
        
		return message;         	
	}
	
	/*private void printPayload(String displayName, Object payload) {
		
        if (payload instanceof String) {
			logger.info(displayName + ": " + payload);
		} else {
			logger.info(displayName + " is NULL");
		}		
	}*/
	
}