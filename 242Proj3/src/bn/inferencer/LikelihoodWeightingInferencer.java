package bn.inferencer;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bn.base.StringValue;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.core.Value;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;
import bn.util.ArrayMap;



public class LikelihoodWeightingInferencer {

	static BayesianNetwork bn=null;
	boolean next=false;
	
	//basic implementation of LikelihoodWeightingInferencer with sub-methods from AIMA
	public static Distribution LikeWeigh(RandomVariable X, Assignment e, BayesianNetwork bn, int n) {
		Distribution count=new bn.base.Distribution(X);
		for(Value t: X.getDomain()) {
			count.put(t, 0.0);
		}
		Assignment x = null;
		for(int i=0; i<n;i++) {
			ArrayMap<Assignment, Double> xw= WeightedSample(bn,e);
			for(Assignment k:xw.keySet()) {
				if(k.containsKey(X)) {
					double weight=count.get(k.get(X))+xw.get(k);
					count.put(k.get(X), weight);
				}
			}	
		}
		count.normalize();
		return count;
	}
	
	public static ArrayMap<Assignment, Double> WeightedSample(BayesianNetwork bn, Assignment e) {
		ArrayMap<Assignment, Double> wx= new ArrayMap<Assignment, Double>();
		double w=1;
		
		Assignment event= new bn.base.Assignment();
		event.putAll(e);
		for(RandomVariable x: bn.getVariablesSortedTopologically()) {
			if(e.containsKey(x)) {
				w=w*bn.getProbability(x, event);
			}else {
				event.putAll(prior(bn,event));
					}
				}
		wx.put(event, w);
		return wx;
		
			}
	
	public static Assignment prior(BayesianNetwork bn, Assignment e) {
		Random rand= new Random();
		Assignment r = new bn.base.Assignment();
		r.putAll(e);
		List<RandomVariable> variables= bn.getVariablesSortedTopologically();
		variables.removeAll(r.keySet());
		for(RandomVariable x: variables) {
			Distribution count= new bn.base.Distribution(x);
			for(Value E: x.getDomain()) {
				r.put(x,E);
				count.put(E, bn.getProbability(x, r));
				}
				boolean check= false;
				for(Value v: x.getDomain()) {
					if(check==true) {
						r.put(x, v);
						break;
						}
					r.put(x, v);
					double probx= bn.getProbability(x, r);
					double rand_sample= rand.nextDouble();
					if(rand_sample<probx) {
						r.put(x, v);
						break;
					}else {
						check=true;
							}
						}
					}
			return r;	
		}
	
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		if(args[1].contains(".xml")) {
			bn=new XMLBIFParser().readNetworkFromFile("./bn/examples/"+args[1]);
		}else if(args[1].contains(".bif")) {
			bn= new BIFParser(new FileInputStream("./bn/examples/"+args[1])).parseNetwork();
		}
		
		int n=Integer.parseInt(args[0]);
		RandomVariable X= bn.getVariableByName(args[2]);
		Assignment e = new bn.base.Assignment();
		for(int i=3; i<args.length;i+=2) {
			e.put(bn.getVariableByName(args[i]), new StringValue(args[i+1]));
			Distribution rejectSamp=LikeWeigh(X,e,bn,n);
			System.out.println(rejectSamp);
		}
	}	

	
	}


