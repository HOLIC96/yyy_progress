package RDF_API;// 001
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
public class create_model {
    static String personURI = "http://somewhere/JohnSminth";
    static String fullName = "John Simth";

    public static void main(String arg[]){
        //创建模型
        Model model = ModelFactory.createDefaultModel();

        Resource johnsimth = model.createResource(personURI);

        johnsimth.addProperty(VCARD.FN,fullName);
        model.write(System.out);
    }
}
