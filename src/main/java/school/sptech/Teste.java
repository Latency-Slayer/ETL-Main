package school.sptech;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream("arquivo.json");
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        PixMapper pixMapper = new PixMapper();
        List<Pix> listaPix;

        try {
            listaPix = pixMapper.mapearPix(inputStream);

            gravaArquivoCSV(listaPix, "livros");

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
    public static void gravaArquivoCSV(List<Pix> listaPix, String nomeArq) {
        OutputStreamWriter saida = null; //escrever o arquivo
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            saida = new OutputStreamWriter(new FileOutputStream(nomeArq), StandardCharsets.UTF_8);
        } catch (IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        //Escrever e fechar o arquivo
        try {
            saida.append("anoMes;pag_pfpj;rec_pfpj;pag_regiao;rec_regiao;pag_idade;rec_idade;finalidade;valor;quantidade;\n");
            for (Pix p : listaPix){
                saida.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%.2f;%d;\n",p.anoMes(),Pix.formatarTipoPessoa(p.getPAG_PFPJ()),Pix.formatarTipoPessoa(p.getREC_PFPJ()),p.getPAG_REGIAO(), p.getREC_REGIAO(),p.getPAG_IDADE(),p.getREC_IDADE(),p.getFINALIDADE(),p.getVALOR(),p.getQUANTIDADE()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar um arquivo");
            e.printStackTrace();
            deuRuim = true;
        } finally {
            try {
                saida.close();
            } catch (IOException e){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim){
                System.exit(1);
            }
        }
    }
}
