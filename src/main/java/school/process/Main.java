package school.process;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class Main implements RequestHandler<S3Event, String> {
        // Criação do cliente S3 para acessar os buckets
        private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

        // Bucket de destino para o CSV gerado
        private static final String DESTINATION_BUCKET = "latency-trusted";

        @Override
        public String handleRequest (S3Event s3Event, Context context){

            // Extraímos o nome do bucket de origem e a chave do arquivo JSON
            String sourceBucket = s3Event.getRecords().getFirst().getS3().getBucket().getName();
            String sourceKey = s3Event.getRecords().getFirst().getS3().getObject().getKey();

            try {
                // Leitura do arquivo JSON do bucket de origem

                context.getLogger().log("Pegando arquivo " + sourceKey);
                InputStream s3InputStream = s3Client.getObject(sourceBucket, sourceKey).getObjectContent();
                context.getLogger().log("Pegou o arquivo");

                ProcessMapper mapper = new ProcessMapper();
                List<Json> p = mapper.mapearProcesso(s3InputStream);

                CsvWriter csvWriter = new CsvWriter();
                ByteArrayOutputStream csvOutputStream = csvWriter.writeCsv(p);

                InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

                s3Client.putObject(DESTINATION_BUCKET, sourceKey.replace(".json", ".csv"), csvInputStream, null);

                return "Sucesso no processamento";
            } catch (Exception e) {
                // Tratamento de erros e log do contexto em caso de exceção
                context.getLogger().log("Erro: " + e.getMessage());
                return "Erro no processamento";
            }
        }
}
