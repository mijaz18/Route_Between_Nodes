package bn.inferencer;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bn.core.Distribution;
import bn.base.StringValue;
import bn.core.Value;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.RandomVariable;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;

public class RejectionSamplingInferencer {
	
	static BayesianNetwork bn=null;

	//basic implementation of rejection sampling with sub-methods from AIMA
	public static Distribution rejectionSampling(RandomVariable X,Assignment e, BayesianNetwork bn, int n) {
		Distribution count=new bn.base.Distribution(X);
		for(int i=0; i<n;i++) {
			Assignment prior= priorSample(bn);
			if(Consistent(prior,e)) {
				Value N=prior.get(X);
				if(count.keySet().contains(N)) {
					count.put(N, count.get(N)+1);
				}else {
					count.put(N, 1.0);
				}
			}	
		}
		count.normalize();
		return count;
	}
	
	 public static boolean Consistent(Assignment x, Assignment e) {
	        for (RandomVariable a : e.keySet()) {
	            if (!e.get(a).equals(x.get(a))) {
	            	return false;
	            }
	        }
	        return true;
	    }
	 
	 public static Assignment priorSample(BayesianNetwork bn) {
			Assignment event=new bn.base.Assignment();
			
			for(RandomVariable x: bn.getVariablesSortedTopologically()) { 
				boolean check=true;
				for(Value a: x.getDomain()){
					event.put(x, a);
					double d= bn.getProbability(x, event);
					Random r = new Random();
					double randomValue = 0 + (1 - 0) * r.nextDouble();
					if(randomValue>=d) {
						check=false;
						continue;
					}else {
						break;
					}
				}
			}
				
			return event;
		}
	 
	 public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
			if(args[1].contains(".xml")) {
				bn=new XMLBIFParser().readNetworkFromFile("./bn/examples/"+args[1]);
			}else if(args[1].contains(".bif")) {
				bn= new BIFParser(new FileInputStream("./bn/examples/"+args[1])).parseNetwork();
			}
			
			int n=Integer.parseInt(args[0]);
			
			RandomVariable X= bn.getVariableByName(args[2]);
			Assignment as = new bn.base.Assignment();
			for(int i=3; i<args.length;i+=2) {
				as.put(bn.getVariableByName(args[i]), new StringValue(args[i+1]));
				Distribution rejectSamp=rejectionSampling(X,as,bn,n);
				System.out.println(rejectSamp);
			}
		}
}
