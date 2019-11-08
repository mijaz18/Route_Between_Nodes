package bn.inferencer;

import bn.base.StringValue;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.core.Value;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;



public class EnumerationInferencer {
	
	static BayesianNetwork bn=null;
	
	//basic implementation of enumeration_ask and eumerate_all from AIMA
	public static Distribution enumeration_ask(RandomVariable x,Assignment e,BayesianNetwork bn) {
		Distribution Q= new bn.base.Distribution(x);
		for(Value a: x.getDomain()) {
			Assignment as=e.copy();
			as.put(x, a);
			Q.set(a, enumerate_all(bn.getVariablesSortedTopologically(),as));
		}
		Q.normalize();
		return Q;
	}
	
	public static double enumerate_all(List<RandomVariable> vars, Assignment e) {
		if(vars.size()==0) {
			return 1.0;
		}
			RandomVariable Y= (RandomVariable) vars.toArray()[0];
	        	if(e.containsKey(Y)) {
	        		return bn.getProbability(Y, e)* enumerate_all(vars.subList(1, vars.size()),e);
	        	}
	        double sum=0;
	        for(Value y: Y.getDomain()) {
	        	Assignment as= e.copy();
	        	as.put(Y, y);
	        	sum=sum+bn.getProbability(Y, as)*enumerate_all(vars.subList(1, vars.size()),as);
	        }
	        return sum;
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		if(args[0].contains(".xml")) {
			bn=new XMLBIFParser().readNetworkFromFile("./bn/examples/"+args[0]);
		}else if(args[0].contains(".bif")) {
			bn= new BIFParser(new FileInputStream("./bn/examples/"+args[0])).parseNetwork();
		}
		
		RandomVariable query= bn.getVariableByName(args[1]);
		Assignment as = new bn.base.Assignment();
		
		for(int i=2; i<args.length;i+=2) {
			as.put(bn.getVariableByName(args[i]), new StringValue(args[i+1]));
			Distribution d=enumeration_ask(query,as,bn);
			System.out.println(d);
		}
	}

}
