package TDB;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;

public class TDB_test {
    public static void main(String args[]){
        String directory = "MyDatabase/Dataset1";
        Dataset dataset = TDBFactory.createDataset(directory);

        dataset.begin(ReadWrite.READ);
        Model model = dataset.getDefaultModel();
        dataset.end();

        dataset.begin(ReadWrite.WRITE);
        model = dataset.getDefaultModel();
        dataset.end();
    }
}
