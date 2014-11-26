package behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SaludoGuest extends WakerBehaviour{
	AID[] totales; 
	public SaludoGuest(Agent a, long timeout) {
		super(a, timeout);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onWake() {
		// TODO Auto-generated method stub
		DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd1 = new ServiceDescription();
        sd1.setType("attendant");
        template.addServices(sd1);
        /*SearchConstraints ALL = new SearchConstraints();
        ALL.setMaxResults(new Long(-1));*/
		try {
			DFAgentDescription[] result = DFService.search(myAgent, template);
			totales=new AID[result.length];
			for (int i=0;i<result.length;i++){
				totales[i]=result[i].getName();
				ACLMessage saludo = new ACLMessage(ACLMessage.INFORM);
	            saludo.setContent("he llegado");
	            saludo.addReceiver(totales[i]);
	            System.out.println(myAgent.getLocalName()+": saludando a "+totales[i].getLocalName());
	            myAgent.send(saludo);
	            myAgent.blockingReceive(MessageTemplate.MatchContent("Hola amigote"));
	            System.out.println(myAgent.getLocalName()+": se ha saludado a "+totales[i].getLocalName()); 
			}
			
		} catch (FIPAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		 DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(myAgent.getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("attendant");
        sd.setName("Guest");
        dfd.addServices(sd); 
        try {
			DFService.register(myAgent, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
