package school.process;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvWriter {
    public ByteArrayOutputStream writeCsv(List<Json> registro) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("name", "pid", "cpu_percent", "memory_percent", "memory_gb", "datetime")
                .withDelimiter(';')
                .withQuote('"')
                .withQuoteMode(QuoteMode.NON_NUMERIC)
        );

        // Processar e escrever cada objeto no CSV
        for (Json r : registro) {
            for(Process p : r.getProcessesData()) {
                csvPrinter.printRecord(
                        p.getName(),
                        p.getPid(),
                        p.getCpuPercent(),
                        p.getMemoryPercent(),
                        p.getMemoryGb(),
                        r.getDatetime()
                );
            }
        }

        // Fechar o CSV para garantir que todos os dados sejam escritos
        csvPrinter.flush();
        writer.close();

        // Retornar o ByteArrayOutputStream que contém o CSV gerado
        return outputStream;
    }
}
