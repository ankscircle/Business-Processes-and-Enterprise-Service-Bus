package edu.ncsu.soc.esb;

import java.util.UUID;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import org.apache.log4j.Logger;

public class MovieTicketsService extends AbstractActionLifecycle {
	
	protected ConfigTree config;
	private Logger logger = Logger.getLogger(MovieTicketsService.class);
	
    public MovieTicketsService(ConfigTree config) { this.config = config; } 
	
	public Message process(Message message) throws Exception {
		Integer bookTickets = Integer.valueOf((String)message.getBody().get("bookTickets"));
		Boolean ticketSucceed = Boolean.parseBoolean(message.getBody().get("ticketSucceed").toString());
		String movieName= message.getBody().get("movieName").toString();
		Boolean returnTickets = Boolean.parseBoolean(message.getBody().get("returnTickets").toString());
		String customerName= message.getBody().get("customerName").toString();
		
       	
		
		try{
			
			
			if(bookTickets>0 && ticketSucceed==true&& returnTickets!=true)
			{UUID rNumber = UUID.randomUUID();
			message.getBody().add("ticketBookingRef",rNumber.toString());
			
				message.getBody().add("ticketsBooked","true");
				message.getBody().add("ticketsRefunded","false");
			}
			if(bookTickets>0 && ticketSucceed==false&& returnTickets!=true)
			{
				logger.info(" movie service returns");
				message.getBody().add("ticketBookingRef","");
				message.getBody().add("ticketsBooked","false");
				message.getBody().add("ticketsRefunded","false");

			}
			if(returnTickets==true)
			{
				if(message.getBody().get("ticketBookingRef")==null)
				{
					UUID rNumber = UUID.randomUUID();
					message.getBody().add("ticketBookingRef",rNumber.toString());
				}
				
				message.getBody().add("ticketsBooked","false");
				message.getBody().add("ticketsRefunded","true");
			}
			}
		catch (Exception e){
			logger.info("movie service error: "+e.getMessage());
		}
       // message.getBody().add("ticketsBooked",ticketsBooked.toString());
        //message.getBody().add("ticketsRefunded",ticketsRefunded.toString());
        //logger.info(ticketsBooked.toString() + " movie service returns");
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