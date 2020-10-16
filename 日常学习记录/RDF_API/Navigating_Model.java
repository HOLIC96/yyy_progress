package RDF_API;//导航模型
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.*;


public class Navigating_Model extends Object {

    static final String inputFileName = "vc-db-1.rdf";
    static final String johnSmithURI = "http://somewhere/JohnSmith";

    public static void main (String args[]) {
        // 创建模型
        Model model = ModelFactory.createDefaultModel();

        // 读取
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }

        // 读取rdf
        model.read(new InputStreamReader(in), "");

        // 检索资源
        Resource vcard = model.getResource(johnSmithURI);

        // 检索属性
        Resource name = vcard.getProperty(VCARD.N).getResource();
        // 检索属性名
        String fullName = vcard.getRequiredProperty(VCARD.FN)
                .getString();
        // 添加绰号
        vcard.addProperty(VCARD.NICKNAME, "Smithy")
                .addProperty(VCARD.NICKNAME, "Adman");

        // 输出
        System.out.println("The nicknames of \"" + fullName + "\" are:");

        StmtIterator iter = vcard.listProperties(VCARD.NICKNAME);
        while (iter.hasNext()) {
            System.out.println("    " + iter.nextStatement().getObject()
                    .toString());
        }
    }
}