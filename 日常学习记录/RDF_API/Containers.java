

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Containers extends Object {

    static final String inputFileName = "vc-db-1.rdf";

    public static void main (String args[]) {
        // 创建模型
        Model model = ModelFactory.createDefaultModel();

        // 输入
        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }

        // 读取
        model.read(new InputStreamReader(in), "");

        // 创建一个无序集合
        Bag smiths = model.createBag();

        //选择VCARD.FN中末尾是"Simth"的数据
        StmtIterator iter = model.listStatements(
                new
                        SimpleSelector(null, VCARD.FN, (RDFNode) null) {
                            @Override
                            public boolean selects(Statement s) {
                                return s.getString().endsWith("Smith");
                            }
                        });
        // 将选择出的资源加入集合中
        while (iter.hasNext()) {
            smiths.add( iter.nextStatement().getSubject());
        }

        // 输出
        model.write(new PrintWriter(System.out));
        System.out.println();

        NodeIterator iter2 = smiths.iterator();
        if (iter2.hasNext()) {
            System.out.println("The bag contains:");
            while (iter2.hasNext()) {
                System.out.println("  " +
                        ((Resource) iter2.next())
                                .getRequiredProperty(VCARD.FN)
                                .getString());
            }
        } else {
            System.out.println("The bag is empty");
        }
    }
}

