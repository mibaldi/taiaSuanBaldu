package agentes;

import java.util.Random;

import behaviours.OfrecerComida;
import behaviours.SaludoHost;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class camarero extends Agent{
AID[] lista;
Random ran = new Random();
DFAgentDescription[] result;
int numRandom;
	protected void setup(){
		final DFAgentDescription template = new DFAgentDescription();
        ServiceDescription ca = new ServiceDescription();
		ca.setType("attendant");
		template.addServices(ca);
		
		//comportamiento para volver actualizar la lista de invitados
		addBehaviour(new CyclicBehaviour(this) {

			@Override
			public void action() {
				// TODO Auto-generated method stub
				try {
					result = DFService.search(myAgent, template);
				   	lista = new AID[result.length];
					//System.out.println("taamaño lista: " + lista.length);
					if(lista.length>0){
						numRandom = ran.nextInt(lista.length);
					}
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
			  	
		 		
		
		//comportamiento ofrecer comida
		addBehaviour(new CyclicBehaviour(this) {
			@Override
			public void action() {
				// TODO Auto-generated method stub
				
				if (lista.length>0){				
					lista[numRandom]=result[numRandom].getName();
					System.out.println("numRandom: " + numRandom);
					System.out.println(myAgent.getLocalName() + ": se ofrece comida a: " + lista[numRandom].getLocalName());
				
					ACLMessage ofrecer = new ACLMessage(ACLMessage.INFORM);
					ofrecer.setContent("comida");
					ofrecer.addReceiver(lista[numRandom]);
					myAgent.send(ofrecer);
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				}    
			}			
		});
		
		//comportamiento ofrecer bebida
		addBehaviour(new CyclicBehaviour(this) {
			@Override
			public void action() {
				// TODO Auto-generated method stub

				if (lista.length>0){					
					lista[numRandom]=result[numRandom].getName();
					System.out.println(myAgent.getLocalName() + ": se ofrece bebida a: " + lista[numRandom].getLocalName());

					
					ACLMessage ofrecer = new ACLMessage(ACLMessage.INFORM);
					ofrecer.setContent("bebida");
					ofrecer.addReceiver(lista[numRandom]);
					myAgent.send(ofrecer);

					//System.out.println("numRandom: " + numRandom);
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   
				}	
			}			
		});		
	}

	
}
