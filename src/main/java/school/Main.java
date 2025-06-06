package school;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

public class Main implements RequestHandler<S3Event, String> {

    @Override
    public String handleRequest(S3Event s3Event, Context context) {

        String sourceKey = s3Event.getRecords().getFirst().getS3().getObject().getKey();

        try {
            if (sourceKey.contains("component-data/")){
                school.component.Main main = new school.component.Main();
                main.handleRequest(s3Event, context);
            }

            if (sourceKey.contains("pix-data/")){
                school.pix.Main mainPix = new school.pix.Main();
                mainPix.handleRequest(s3Event, context);
            }

            if (sourceKey.contains("process-data/")){
                school.process.Main mainProcess = new school.process.Main();
                mainProcess.handleRequest(s3Event, context);
            }

            if (sourceKey.contains("connections-data/")) {
                school.connection.Main mainProcess = new school.connection.Main();
                mainProcess.handleRequest(s3Event, context);
            }

            return "Sucesso no processamento";
        } catch (Exception e) {
            // Tratamento de erros e log do contexto em caso de exceção
            context.getLogger().log("Erro: " + e.getMessage());
            return "Erro no processamento";
        }
    }
}
