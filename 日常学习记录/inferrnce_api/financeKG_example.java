package inferrnce_api;

import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.InfGraph;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

public class financeKG_example {
    public static void main(String[] args) throws FileNotFoundException {
        //声明NS
        String finance = "http://finance#";

        //创建模型
        Model myModel = ModelFactory.createDefaultModel();
        Resource 孙宏斌 = myModel.createResource(finance+ "孙宏斌");
        Resource 融创中国 = myModel.createResource(finance+ "融创中国");
        Resource 乐视网 = myModel.createResource(finance+ "乐视网");
        Resource 贾跃亭 = myModel.createResource(finance+ "贾跃亭");
        Resource 地产公司 = myModel.createResource(finance+ "地产公司");
        Resource 公司 = myModel.createResource(finance+ "公司");
        Resource 法人实体 = myModel.createResource(finance+ "法人实体");
        Resource 人 = myModel.createResource(finance+ "人");
        Resource 地产事业 = myModel.createResource(finance+ "地产事业");
        Resource 王健林 = myModel.createResource(finance+ "王健林");
        Resource 万达集团 = myModel.createResource(finance+ "万达集团");
        Property 执掌 = myModel.createProperty(finance+ "执掌");
        Property 主要收入 = myModel.createProperty(finance+ "主要收入");
        Property 主要资产 = myModel.createProperty(finance+ "主要资产");
        Property 股东 = myModel.createProperty(finance+ "股东");
        Property 关联交易 = myModel.createProperty(finance+ "关联交易");
        Property 收购 = myModel.createProperty(finance+ "收购");

        //加入三元组
        myModel.add(孙宏斌,执掌,融创中国);
        myModel.add(贾跃亭,执掌,乐视网);
        myModel.add(王健林,执掌,万达集团);
        myModel.add(乐视网, RDF.type,公司);
        myModel.add(万达集团, RDF.type,公司);
        myModel.add(融创中国, RDF.type,地产公司);
        myModel.add(地产公司, RDFS.subClassOf,公司);
        myModel.add(公司, RDFS.subClassOf,法人实体);
        myModel.add(孙宏斌, RDF.type,人);
        myModel.add(贾跃亭, RDF.type,人);
        myModel.add(王健林, RDF.type,人);
        myModel.add(万达集团,主要资产,地产事业);
        myModel.add(融创中国,主要收入,地产事业);
        myModel.add(孙宏斌,股东,乐视网);
        myModel.add(孙宏斌,收购,万达集团);

        PrintUtil.registerPrefix("", finance);

        //输出模型
        StmtIterator i = myModel.listStatements(null,null,(RDFNode)null);
        while (i.hasNext()){
            System.out.println("-" + PrintUtil.print(i.nextStatement()));
        }

        /*创建推理规则
        1.执掌一家公司就一定是这家公司的股东
        2.收购一家公司就一定是这家公司的股东
        3.某人同时是A与B公司股东时，A与B一定有关联交易
         */

        //theinstance()接口用来直接转到已知的模型类以定位模型实例。
        GenericRuleReasoner reasoner = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance().create(null);

        reasoner.setRules(Rule.parseRules(
                "[ruleHoldShare: (?p :执掌 ?c) -> (?p :股东 ?c)]\n"
                + "[ruleConnTrans: (?p :收购 ?c) -> (?p :股东 ?c)]\n"
                + "[ruleConnTrans: (?p :股东 ?c) (?p :股东 ?c2) -> (?c :关联交易 ?c2)]\n"
                + "-> tableAll()."));



        reasoner.setMode(GenericRuleReasoner.HYBRID);

        InfGraph infgraph = reasoner.bind(myModel.getGraph());
        infgraph.setDerivationLogging(true);


        //判断数据属性是否冲突
        ValidityReport validity = infgraph.validate();
        if (validity.isValid()) {
            System.out.println("\n无冲突");
        } else {
            System.out.println("\n存在冲突");
            for (Iterator j = validity.getReports(); j.hasNext(); ) {
                ValidityReport.Report report = (ValidityReport.Report)j.next();
                System.out.println(" - " + report);
            }
        }
        System.out.println("推理后...\n");

        Iterator<Triple> tripleIterator = infgraph.find(null, null, null);
        while (tripleIterator.hasNext()) {
            System.out.println(" - " + PrintUtil.print(tripleIterator.next()));
        }
        FileOutputStream out = new FileOutputStream("C:\\Users\\57818\\Desktop\\FinanceKG.owl");
        myModel.write(out);

    }
}
