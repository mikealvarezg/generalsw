import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("General Software").getOrCreate();

        String path = "data/bank OB.csv";
        Dataset<Row> clientsDF = spark.read().option("header", true).csv(path);

        //¿Cuál es el rango de edad que contrata más préstamos?
        Dataset<Row> clientsWithLoanDF = clientsDF.filter(clientsDF.col("loan").equalTo("yes"));
        Row question1 = clientsWithLoanDF.groupBy("age").count().orderBy(col("count").desc()).first();
        System.out.println("El rango de edad que contrata más préstamos es " + question1.get(0) + " con un total de " + question1.get(1));

        //¿Cuál es el rango edad y estado civil que tiene más dinero en las cuentas?
        Row question2 = clientsDF.groupBy("age", "marital").agg(sum("balance").as("balanceSum")).orderBy(col("balanceSum").desc()).first();
        System.out.println("El rango de edad " + question2.get(0) + " con estado civil " + question2.get(1) + " es el que tiene mas dinero con un total de " + question2.get(2));

        //¿Cuál es la forma más común de contactar a los clientes, entre 25-35 años?
        Dataset<Row> clients2535 = clientsDF.filter(col("age").geq(25).and(col("age").leq(35))).orderBy(col("age").desc());
        Row question3 = clients2535.groupBy("contact").count().orderBy(col("count").desc()).first();
        System.out.println("La forma mas común de contactar a los clientes entre 25-35 años es mediante " + question3.get(0) + " con un total de " + question3.get(1) + " veces");

        //¿Cuál es el balance medio, máximo y mínimo por cada tipo de campaña, teniendo en cuenta su estado civil y profesión?
        Dataset<Row> a = clientsDF.groupBy("campaign", "marital", "job").count();
        a.show();
        clientsDF.groupBy("campaign", "marital", "job").agg(sum("balance"), count("balance"), avg("balance"), max("balance"), min("balance")).show();

        //¿Cuál es el tipo de trabajo más común, entre los casados (marital=married), que tienen casa propia (housing=yes), y que tienen en la cuenta más de 1.200€ y qué son de la campaña 3?
        Dataset<Row> clientsFiltered = clientsDF.filter(
                col("marital").equalTo("married").and(
                col("housing").equalTo("yes").and(
                col("balance").geq(1200)).and(
                col("campaign").equalTo(3))));
        Row question5 = clientsFiltered.groupBy("job").count().orderBy(col("count").desc()).first();
        System.out.println("El tipo de trabajo mas común según los filtros establecidos es " + question5.get(0) + " con un total de " + question5.get(1) + " veces");
    }
}