// 002
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class jena_train {
    public static void main(String[] args){
        //定义
        String personURI ="http://JohnSimth";
        String givenname = "John";
        String familyname = "Simth";
        String fullname = givenname + " " + familyname;
        //创建模型
        Model model = ModelFactory.createDefaultModel();

        //为模型添加实体资源及属性
        Resource johnsimth = model.createResource(personURI)
                .addProperty(VCARD.FN,fullname)
                .addProperty(VCARD.N,
                        model.createResource()
                            .addProperty(VCARD.Given,givenname)
                            .addProperty(VCARD.Family,familyname));

        //model.write(System.out);

        //遍历statements
        StmtIterator iter = model.listStatements();

        //hasnext判断是否还有数据，通过getsubject,getpredicate,getobject获取三元组信息
        while(iter.hasNext()){
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");

        }
        //写入rdf
        model.write(System.out);


    }
}
