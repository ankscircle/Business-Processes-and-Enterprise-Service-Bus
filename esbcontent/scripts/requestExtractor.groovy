import org.jboss.soa.esb.message.*
import groovy.util.*

//Put the message in a local variable 
Message myMessage = message;

println "*********** Begin Service ***********"

def incomingXML = myMessage.getBody().get()

println "Extracting Variables"
def conciergeRequest = new XmlParser().parseText(incomingXML)
def bookTickets = conciergeRequest.bookTickets.text()
def returnTickets = conciergeRequest.returnTickets.text()
def movieName = conciergeRequest.movieName.text()
def ticketSucceed = conciergeRequest.ticketSucceed.text()
def reserveDinner = conciergeRequest.reserveDinner.text()
def cancelDinner = conciergeRequest.cancelDinner.text()
def dinnerSucceed = conciergeRequest.dinnerSucceed.text()
def customerName = conciergeRequest.customerName.text()
def makePayment = conciergeRequest.makePayment.text()
def paySucceed = conciergeRequest.paySucceed.text()
def paySucceeded="false"

println "Returning Variables to Requester"
myMessage.getBody().add("bookTickets", bookTickets)
myMessage.getBody().add("returnTickets", returnTickets)
myMessage.getBody().add("movieName", movieName)
myMessage.getBody().add("ticketSucceed", ticketSucceed)
myMessage.getBody().add("reserveDinner", reserveDinner)
myMessage.getBody().add("cancelDinner", cancelDinner)
myMessage.getBody().add("dinnerSucceed", dinnerSucceed)
myMessage.getBody().add("customerName", customerName)
myMessage.getBody().add("makePayment",makePayment)
myMessage.getBody().add("paySucceed", paySucceed)
myMessage.getBody().add("paySucceeded", paySucceeded)




println "************ End Service ************"