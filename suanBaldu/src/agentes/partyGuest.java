package agentes;

import behaviours.SaludoGuest;
import behaviours.comer;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class partyGuest extends Agent{
	int Comida=100;
	int Bebida=100;
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		Behaviour saludar=new SaludoGuest(this, 2000);
		addBehaviour(saludar);
		Behaviour com=new comer(Comida,Bebida); 
		addBehaviour(com);
	}
	@Override
	protected void takeDown() {
		 System.out.println(getLocalName()+": adios boss");
		 DFAgentDescription template = new DFAgentDescription();
		 ServiceDescription sd1 = new ServiceDescription();
         sd1.setName("Host");
         template.addServices(sd1);
         try{
        	 DFAgentDescription[] result = DFService.search(this, template);
        	 AID host = result[0].getName();
        	 ACLMessage despedida = new ACLMessage(ACLMessage.INFORM);
             despedida.setContent("me voy");
             despedida.addReceiver(host);
             send(despedida);
             blockingReceive(MessageTemplate.MatchContent("Adios amigote"));
             DFService.deregister(this);
         }
         catch(FIPAException e){
        	 e.printStackTrace();
         }
		 System.out.println(getLocalName()+ ": Se fue");
	}
}
