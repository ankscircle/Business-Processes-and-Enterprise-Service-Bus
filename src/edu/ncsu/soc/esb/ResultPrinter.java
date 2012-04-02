package edu.ncsu.soc.esb;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import org.apache.log4j.Logger;

public class ResultPrinter extends AbstractActionLifecycle {
	
	protected ConfigTree config;
	private Logger logger = Logger.getLogger(ResultPrinter.class);
	
    public ResultPrinter(ConfigTree config) { this.config = config; } 
	
	public Message process(Message message) throws Exception {
	/*try{
      printPayload("bookTickets", message.getBody().get("bookTickets"));
        printPayload("returnTickets", message.getBody().get("returnTickets").toString());
        printPayload("movieName", message.getBody().get("movieName"));
        printPayload("reserveDinner", message.getBody().get("reserveDinner"));
        printPayload("cancelDinner", message.getBody().get("cancelDinner").toString());
        printPayload("customerName", message.getBody().get("customerName"));
        printPayload("paySucceeded", message.getBody().get("paySucceeded"));
        printPayload("ticketsBooked", message.getBody().get("ticketsBooked").toString());
        printPayload("dinnerReserved", message.getBody().get("dinnerReserved").toString());
        printPayload("reserveDinner", message.getBody().get("reserveDinner").toString());
        printPayload("ticketsRefunded", message.getBody().get("ticketsRefunded").toString());
        printPayload("dinnerCancelled", message.getBody().get("dinnerCancelled").toString());
        printPayload("dinnerReservationRef", message.getBody().get("dinnerReservationRef"));
      
		}catch(Exception e)
		{
			logger.info(""+ e);
		}*/
		try{
        Boolean dinnerReserved = Boolean.parseBoolean(message.getBody().get("dinnerReserved").toString());
        Boolean dinnerSucceed = Boolean.parseBoolean(message.getBody().get("dinnerSucceed").toString());
        Boolean ticketsBooked = Boolean.parseBoolean(message.getBody().get("ticketsBooked").toString());
        Boolean paySucceeded = Boolean.parseBoolean(message.getBody().get("paySucceeded").toString());
        
        String customerName=message.getBody().get("customerName").toString();
        Integer bookTickets = Integer.valueOf((String)message.getBody().get("bookTickets"));
        Integer reserveDinner=Integer.valueOf((String) message.getBody().get("reserveDinner"));
        Boolean dinnerCancelled=Boolean.parseBoolean(message.getBody().get("dinnerCancelled").toString());
        String movieName= message.getBody().get("movieName").toString();
        Boolean ticketsRefunded=Boolean.parseBoolean(message.getBody().get("ticketsRefunded").toString());
        Integer makePayment = Integer.valueOf((String)message.getBody().get("makePayment"));
        try{
        	
        	
        if(paySucceeded==true)
        {
        	logger.info("DINNER RESERVATION SUCCEEDED FOR " + reserveDinner+" ;CUSTOMER:"+customerName+"; RESERVATION REF " +message.getBody().get("dinnerReservationRef"));
        }
        if(dinnerReserved==false && dinnerCancelled==false)
        {   
        	logger.info("DINNER RESERVATION FAILED FOR " +reserveDinner+"; RESERVATION REF null");
        }
        if(dinnerCancelled==true)
        {
        	logger.info("DINNER RESERVATION CANCELLED FOR "+ message.getBody().get("reserveDinner").toString()+"; RESERVATION REF "+message.getBody().get("dinnerReservationRef"));
        }
        	if(paySucceeded==true)
        	{
        		logger.info(movieName+" TICKETS SUCCESSFULLY BOOKED FOR "+bookTickets+";CUSTOMER: "+customerName+"; BOOKING REF "+message.getBody().get("ticketBookingRef"));
        	}
        	if(ticketsBooked==false &&ticketsRefunded==false)
        	{
        		logger.info(movieName+" TICKET BOOKING FAILED FOR "+bookTickets+"; BOOKING REF null");
        	}
        	if(ticketsRefunded==true)
        	{
        		logger.info(movieName+" TICKET BOOKING CANCELLED FOR "+bookTickets+"; BOOKING REF "+message.getBody().get("ticketBookingRef"));
        	}
        if(paySucceeded==true)
        {
        	logger.info("PAYMENT PROCESSED FOR AMOUNT "+makePayment);
        }
        if(paySucceeded==false || ticketsRefunded==true || dinnerCancelled==true)
        {
        	if((ticketsRefunded==true && dinnerCancelled==true))
            {
            	logger.info("PAYMENT FAILED FOR AMOUNT "+makePayment); 	
            }
        	else
        	{
        	logger.info("PAYMENT CANCELLED FOR AMOUNT "+makePayment);
        	}
        }
        
        }catch(Exception e1)
        {
        	logger.info("Result Printer error1: "+e1.getMessage());
        }
		}catch(Exception e){
			logger.info("Result Printer error2: "+e.getMessage());
		}
		return message;         	
	}
	
	private void printPayload(String displayName, Object payload) {
		
        if (payload instanceof String) {
			logger.info(displayName + ": " + payload);
		} else {
			logger.info(displayName + " is NULL");
		}		
	}
}	
