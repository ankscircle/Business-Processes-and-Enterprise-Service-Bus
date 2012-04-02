package edu.ncsu.soc.esb;



import org.apache.log4j.Logger;
import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class PaymentService extends AbstractActionLifecycle{
	protected ConfigTree config;
	private Logger logger = Logger.getLogger(PaymentService.class);
	
    public PaymentService(ConfigTree config) { this.config = config; } 
	
	public Message process(Message message) throws Exception {
		Integer makePayment = Integer.valueOf((String)message.getBody().get("makePayment"));
		Boolean paySucceed = Boolean.parseBoolean(message.getBody().get("paySucceed").toString());
				
       /* printPayload("bookTickets", message.getBody().get("bookTickets"));
        printPayload("returnTickets", message.getBody().get("returnTickets"));
        printPayload("movieName", message.getBody().get("movieName"));
        printPayload("reserveDinner", message.getBody().get("reserveDinner"));
        printPayload("cancelDinner", message.getBody().get("cancelDinner"));
        printPayload("customerName", message.getBody().get("customerName"));*/
		/*Boolean ticketsBooked, ticketsRefunded ;
		ticketsBooked = Boolean.FALSE;
		ticketsRefunded = Boolean.FALSE;*/
		
		
		try{
			
			
			if(makePayment>0 && paySucceed==true)
			{
				
				message.getBody().add("paySucceeded","true");
			}
			if(makePayment>0 && paySucceed==false)
			{
						
				message.getBody().add("paySucceeded","false");
				
			}
			}
		catch (Exception e){
			logger.info("payment service error: "+e.getMessage());
		}
       // message.getBody().add("ticketsBooked",ticketsBooked.toString());
        //message.getBody().add("ticketsRefunded",ticketsRefunded.toString());
        //logger.info(ticketsBooked.toString() + " movie service returns");
		return message;         	
	

}
}