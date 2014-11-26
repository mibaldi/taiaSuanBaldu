package behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class FinFiestaHost extends TickerBehaviour{

	public FinFiestaHost(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onTick() {
		DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd1 = new ServiceDescription();
        sd1.setType("attendant");
        template.addServices(sd1);
        SearchConstraints ALL = new SearchConstraints();
        ALL.setMaxResults(new Long(-1));
        try {
        	DFAgentDescription[] result = DFService.search(myAgent, template, ALL);
        	if(result.length<=1)
        	{
        		System.out.println(myAgent.getLocalName()+ ": Se acabo la fiesta Suan ya no puede mas");
        		myAgent.doDelete();
        	}
        }
        catch(FIPAException e){
        	e.printStackTrace();
        }
		
	}

}
