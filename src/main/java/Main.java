import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("General Software").getOrCreate();

        String path = "C:/Users/migue/Documents/generalsw/data/bank OB.csv";
        Dataset<Row> logData = spark.read().option("header", true).csv(path);
        logData.show();
    }
}