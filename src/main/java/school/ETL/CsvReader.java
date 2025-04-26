package ETL;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public List<List<String>> lerArquivoCSVComParser(InputStream inputStream) throws IOException {
        List<List<String>> linhas = new ArrayList<>();

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withTrim()
                        .withIgnoreSurroundingSpaces()
                )
        ) {
            for (CSVRecord record : parser) {
                List<String> linha = new ArrayList<>();
                record.forEach(linha::add);
                linhas.add(linha);
            }
        }

        return linhas;
    }
}
