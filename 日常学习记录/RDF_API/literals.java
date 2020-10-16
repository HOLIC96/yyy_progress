import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import java.io.PrintWriter;
public class literals {
    public static void main (String args[]) {
        // 创建模型
        Model model = ModelFactory.createDefaultModel();

        // 创建资源
        Resource r = model.createResource();

        // 为资源增加属性
        r.addProperty(RDFS.label, model.createLiteral("chat", "en"))
                .addProperty(RDFS.label, model.createLiteral("chat", "fr"))
                .addProperty(RDFS.label, model.createLiteral("<em>chat</em>", true));

        // 输出
        model.write(new PrintWriter(System.out));
        System.out.println();

        // 创建模型
        model = ModelFactory.createDefaultModel();

        // 创建资源
        r = model.createResource();

        // 增添属性
        r.addProperty(RDFS.label, "11")
                .addLiteral(RDFS.label, 11);

        // 以N-TRIPLE形式输出
        model.write( System.out, "N-TRIPLE");
    }
}
