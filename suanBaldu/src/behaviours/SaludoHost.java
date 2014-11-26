package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SaludoHost extends CyclicBehaviour{

	@Override
	public void action() {
        // TODO Auto-generated method stub
        //MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
		MessageTemplate mt=MessageTemplate.or(MessageTemplate.MatchContent("he llegado"), MessageTemplate.MatchContent("me voy"));
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null){
        	if (msg.getContent().compareTo("he llegado")==0){
        		 ACLMessage reply = msg.createReply();
                 reply.setContent("Hola amigote");
                 System.out.println(myAgent.getLocalName()+":hombre cuanto tiempo "+ msg.getSender().getLocalName()); 
                 myAgent.send(reply);
        	}
        	else if (msg.getContent().compareTo("me voy")==0){
        		 ACLMessage reply = msg.createReply();
                 reply.setContent("Adios amigote");
                 myAgent.send(reply);
        	}
           
        }else{
            block();         
        }

    }

}
