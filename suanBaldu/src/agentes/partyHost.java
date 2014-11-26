package agentes;
 
import behaviours.FinFiestaHost;
import behaviours.SaludoHost;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
 
public class partyHost extends Agent{
     
	int Comida=100;
	int Bebida=100;
    @Override
    protected void setup() {
          
         DFAgentDescription dfd = new DFAgentDescription();
         dfd.setName(getAID());
         ServiceDescription sd = new ServiceDescription();
         sd.setType("attendant");
         sd.setName("host");
         
         dfd.addServices(sd);    
         try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         System.out.println(getLocalName()+": que empiece la fiesta");
        
         
        //el siguiente comportamiento se encargará de mirar si algún invitado a llegado a la fiesta
        //y si ese es el caso, se le saludará
        Behaviour saludo= new SaludoHost();
        addBehaviour(saludo);
        Behaviour fin= new FinFiestaHost(this,10000);
        addBehaviour(fin);
}
}