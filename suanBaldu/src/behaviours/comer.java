package behaviours;

import agentes.partyGuest;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.JADEAgentManagement.CreateAgent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class comer extends CyclicBehaviour{
	int Comida=0;
	int Bebida=0;
	public comer(int comida,int bebida){
		Comida=comida;
		Bebida=bebida;
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		MessageTemplate mt=MessageTemplate.or(MessageTemplate.MatchContent("comida"), MessageTemplate.MatchContent("bebida"));
        ACLMessage msg = myAgent.receive(mt);
        if(msg!=null){
        	if(msg.getContent().compareTo("comida")==0){
        		if(Comida>=5){
        			ACLMessage reply = msg.createReply();
                    reply.setContent("Si");
                    System.out.println(myAgent.getLocalName()+":Si, gracias tengo hambre todavia"); 
                    myAgent.send(reply);
                    Comida=Comida-5;
        		}
        		else{
        			ACLMessage reply = msg.createReply();
                    reply.setContent("No");
                    System.out.println(myAgent.getLocalName()+":No gracias"); 
                    myAgent.send(reply);
        		} 
        	}
        	else if(msg.getContent().compareTo("bebida")==0){
        		if(Bebida>=5){
        			ACLMessage reply = msg.createReply();
                    reply.setContent("Si");
                    System.out.println(myAgent.getLocalName()+":Si, gracias tengo sed todavia"); 
                    myAgent.send(reply);
                    Bebida=Bebida-5;
        		}
        		else{
        			ACLMessage reply = msg.createReply();
                    reply.setContent("No");
                    System.out.println(myAgent.getLocalName()+":No gracias"); 
                    myAgent.send(reply);
        		} 
        	}
        }
        if (Comida==0 && Bebida==0){
        	myAgent.doDelete();
        }
	}

}
