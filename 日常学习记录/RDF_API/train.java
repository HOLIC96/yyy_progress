package RDF_API;

import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class train {
    public static void main(String[] args){
        //声明URI
        String familyURI = "http://family/";
        String relationshipURI = "http://relationship";

        //创建模型
        Model model = ModelFactory.createDefaultModel();

        //创建资源
        Resource adam = model.createResource(familyURI+"adam");
        Resource beth = model.createResource(familyURI+"beth");

        //创建属性
        Property parentof = model.createProperty(relationshipURI, "parentof");

        //添加属性
        adam.addProperty(parentof,beth);

        //创建声明
        Statement stmt = model.createStatement(adam, parentof ,beth);

        //添加声明
        model.add(stmt);


    }
}
