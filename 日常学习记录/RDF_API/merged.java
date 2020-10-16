import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.*;

public class merged {
    static final String inputFileName1 = "vc-db-3.rdf";
    static final String inputFileName2 = "vc-db-4.rdf";

    public static void main(String args[]){
        //创建模型
        Model model1 = ModelFactory.createDefaultModel();
        Model model2 = ModelFactory.createDefaultModel();

        //读取
        InputStream in1 = FileManager.get().open(inputFileName1);
        if (in1 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName1 + " not found");
        }
        InputStream in2 = FileManager.get().open(inputFileName2);
        if (in2 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName2 + " not found");
        }

        model1.read( in1, "" );
        model2.read( in2, "" );

        // 合并模型
        Model model = model1.union(model2);
        Model model3 = model1.intersection(model2);
        Model model4 = model1.difference(model2);

        // 输出
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println();
        model3.write(System.out);
        System.out.println();
        model4.write(System.out);
    }
}

