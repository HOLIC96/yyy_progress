import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

import java.io.*;

public class adddelete {
    public static void main(String[] args){
        String personURI = "http://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;
        Model model = ModelFactory.createDefaultModel();

        Resource johnSmith = model.createResource(personURI);
        johnSmith.addProperty(VCARD.FN, fullName);
        johnSmith.addProperty(VCARD.N,
                model.createResource()
                        .addProperty(VCARD.Given, givenName)
                        .addProperty(VCARD.Family, familyName));

        System.out.println("原始内容：");
        model.write(System.out);
        // 删除 Statement
        model.remove(model.listStatements(null, VCARD.N, (RDFNode)null));
        model.removeAll(null, VCARD.Given, (RDFNode)null);
        model.removeAll(null, VCARD.Family, (RDFNode)null);

        System.out.println("\n删除后的内容：");
        model.write(System.out);

        //增加 Statement
        model.add(johnSmith, VCARD.N, model.createResource()
                .addProperty(VCARD.Given, givenName)
                .addProperty(VCARD.Family, familyName));
        System.out.println("\n重新增加后的内容：");
        model.write(System.out);
    }

}
