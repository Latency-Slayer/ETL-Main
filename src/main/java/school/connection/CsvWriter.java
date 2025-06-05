package school.connection;

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
    public ByteArrayOutputStream writeCsv(List<MainJson> registro) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ip_player", "longitude", "latitude", "country", "continent_code", "date_time")
                .withDelimiter(';')
                .withQuote('"')
                .withQuoteMode(QuoteMode.NON_NUMERIC)
        );


        for (MainJson r : registro) {
            for(PlayerData p : r.getConnectionData().getPlayersData()) {
                csvPrinter.printRecord(
                        p.getIp(),
                        p.getGeoData().getLon(),
                        p.getGeoData().getLat(),
                        p.getGeoData().getCountry(),
                        p.getGeoData().getContinentCode(),
                        r.getDateTime()
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
