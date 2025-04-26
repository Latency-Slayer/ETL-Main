package school.sptech;

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
    public ByteArrayOutputStream writeCsv(List<Pix> pix) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ANO_MES", "PAG_PFPJ", "REC_PFPJ", "PAG_REGIAO", "REC_REGIAO", "PAG_IDADE", "REC_IDADE", "FINALIDADE", "VALOR", "QUANTIDADE")
                .withDelimiter(';')
                .withQuote('"')
                .withQuoteMode(QuoteMode.NON_NUMERIC)
        );

        // Processar e escrever cada objeto no CSV
        for (Pix p : pix) {
            if (p.getFINALIDADE().equals("Nao disponivel")){
                continue;
            }
            csvPrinter.printRecord(
                    p.anoMes(),
                    Pix.formatarTipoPessoa(p.getPAG_PFPJ()),
                    Pix.formatarTipoPessoa(p.getREC_PFPJ()),
                    p.getPAG_REGIAO(),
                    p.getREC_REGIAO(),
                    p.getPAG_IDADE(),
                    p.getREC_IDADE(),
                    p.getFINALIDADE(),
                    p.getVALOR(),
                    p.getQUANTIDADE()
            );
        }

        // Fechar o CSV para garantir que todos os dados sejam escritos
        csvPrinter.flush();
        writer.close();

        // Retornar o ByteArrayOutputStream que contém o CSV gerado
        return outputStream;
    }
}
