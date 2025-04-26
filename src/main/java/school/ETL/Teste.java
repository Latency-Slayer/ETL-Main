package ETL;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static ETL.Main.formmatData;

public class Teste {
    public static void main(String[] args) throws IOException {
        InputStream fileReader = new FileInputStream("data_2025-04-24.csv");

        CsvReader csvReader = new CsvReader();

        List<List<String>> matriz =  csvReader.lerArquivoCSVComParser(fileReader);

        formmatData(matriz);

        List<String> header = matriz.getFirst();
        List<List<String>> componenteDados = matriz.subList(1,matriz.size());

        CsvWriter csvWriter = new CsvWriter();

        ByteArrayOutputStream csv = csvWriter.writeCsv(header, componenteDados);

        try (OutputStream arquivo = new FileOutputStream("saida.csv")) {

            InputStream csvInputStream = new ByteArrayInputStream(csv.toByteArray());

            arquivo.write(csvInputStream.readAllBytes());

            arquivo.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
