package ETL;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main implements RequestHandler<S3Event, String> {
    // Criação do cliente S3 para acessar os buckets
    private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

    // Bucket de destino para o CSV gerado
    private static final String DESTINATION_BUCKET = "latency-slayer-bucket-s3-trusted";

    @Override
    public String handleRequest(S3Event s3Event, Context context) {

        String sourceBucket = s3Event.getRecords().get(0).getS3().getBucket().getName();
        String sourceKey = s3Event.getRecords().get(0).getS3().getObject().getKey();

        try {
            // Leitura do arquivo JSON do bucket de origem
            InputStream s3InputStream = s3Client.getObject(sourceBucket, sourceKey).getObjectContent();

            // Conversão do JSON para uma lista de objetos Componentes usando o Mapper
            CsvReader reader = new CsvReader();

            List<List<String>> dadosCSV = reader.lerArquivoCSVComParser(s3InputStream);

            formmatData(dadosCSV);

            List<String> cabecalho = dadosCSV.getFirst();
            List<List<String>> componenteDados = dadosCSV.subList(1,dadosCSV.size());

            CsvWriter csvWriter = new CsvWriter();
            ByteArrayOutputStream csvOutputStream = csvWriter.writeCsv(cabecalho, componenteDados);

            // Converte o ByteArrayOutputStream para InputStream para enviar ao bucket de destino
            InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

            // Envio do CSV para o bucket de destino
            s3Client.putObject(DESTINATION_BUCKET, sourceKey, csvInputStream, null);

            return "Sucesso no processamento";
        } catch (Exception e) {
            // Tratamento de erros e log do contexto em caso de exceção
            context.getLogger().log("Erro: " + e.getMessage());
            return "Erro no processamento";
        }
    }


    public static void formmatData(List<List<String>> matriz) {
        List<String> header = matriz.getFirst();
        List<List<String>> data = matriz.subList(1, matriz.size());

        for (List<String> dataLine : data) {
            for (int j = 0; j < dataLine.size(); j++) {
                List<String> headerSplit = new java.util.ArrayList<>(Arrays.stream(header.get(j).split("_")).toList());

                headerSplit.replaceAll(s -> s.replace("\"", ""));

                if (headerSplit.size() == 3 && headerSplit.get(2).equals("%") || headerSplit.size() == 3 && headerSplit.get(2).equals("GB")) {
                    dataLine.set(j, String.format(Locale.US, "%.2f", Double.valueOf(dataLine.get(j))));
                } else

                if(headerSplit.get(0).equals("download") || headerSplit.get(0).equals("upload")) {
                    dataLine.set(j, String.format(Locale.US, "%.2f", Double.valueOf(dataLine.get(j))));
                }
            }
        }
    }
}
