package school.connection;

import java.io.*;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream("teste.json");
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        JsonMapper pixMapper = new JsonMapper();
        List<MainJson> listaPix;

        try {
            listaPix = pixMapper.mapearProcesso(inputStream);

            CsvWriter csvWriter = new CsvWriter();

            ByteArrayOutputStream csvOutputStream = csvWriter.writeCsv(listaPix);



            try (OutputStream arquivo = new FileOutputStream("saida.csv")) {

                InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

                arquivo.write(csvInputStream.readAllBytes());

                arquivo.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("Erro ao mapear o arquivo");
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arqui  vo json");
            }
        }
    }
}
