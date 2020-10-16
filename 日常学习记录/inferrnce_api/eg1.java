package inferrnce_api;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.PrintUtil;

import java.util.Iterator;

public class eg1 {
    public static void main(String args[]){
        //声明NS
        String finance = "http://finance#";

        //创建模型
        Model myModel = ModelFactory.createDefaultModel();
//        Resource r = myModel.createResource(finance+ "r");
//        Resource p = myModel.createResource(finance+ "p");
//        Resource q = myModel.createResource(finance+ "q");
        Resource A = myModel.createResource(finance+ "A");
        Resource B = myModel.createResource(finance+ "B");
        Resource C = myModel.createResource(finance+ "C");
        Property p = myModel.createProperty(finance + "p");
        Property q = myModel.createProperty(finance + "q");
//        Property concatFirst = myModel.createProperty(finance+ "concatFirst");
//        Property concatSecond = myModel.createProperty(finance+ "concatFirst");

        //创建三元组
//        myModel.add(r,concatFirst,p);
//        myModel.add(r,concatSecond,q);
        myModel.add(A,p,B);
        myModel.add(B,q,C);

        //注释前缀
        PrintUtil.registerPrefix("",finance);

        //输出
        StmtIterator i = myModel.listStatements();
        while (i.hasNext()){
            System.out.println(PrintUtil.print(i.nextStatement()));
        }

        String rules = "[r1: (?c concatFirst ?p), (?c concatSecond ?q) ->" +
        "[r1b: (?x ?c ?y) <- (?x ?p ?z) (?z ?q ?y)]]";

        Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
        InfModel inf = ModelFactory.createInfModel(reasoner,myModel);
        System.out.println("A**=>");
        Iterator list = inf.listStatements(A,null,(RDFNode)null);
        while(list.hasNext()){
            System.out.println(list.next());
        }
    }
}
