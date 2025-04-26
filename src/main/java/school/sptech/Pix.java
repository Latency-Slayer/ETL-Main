package school.sptech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pix {

    @JsonProperty("AnoMes")
    private Integer anoMes;

    @JsonProperty("PAG_PFPJ")
    private String PAG_PFPJ;

    @JsonProperty("REC_PFPJ")
    private String REC_PFPJ;

    @JsonProperty("PAG_REGIAO")
    private String PAG_REGIAO;

    @JsonProperty("REC_REGIAO")
    private String REC_REGIAO;

    @JsonProperty("PAG_IDADE")
    private String PAG_IDADE;

    @JsonProperty("REC_IDADE")
    private String REC_IDADE;

    @JsonProperty("FINALIDADE")
    private String FINALIDADE;

    @JsonProperty("VALOR")
    private Double VALOR;

    @JsonProperty("QUANTIDADE")
    private Integer QUANTIDADE;

    public Integer getAno() {
        return anoMes;
    }

    public String anoMes(){
        String str = String.format("%06d", anoMes);
        return str.substring(0, 4) + "/" + str.substring(4);
    }
    public static String formatarTipoPessoa(String tipo) {
        if ("PF".equalsIgnoreCase(tipo)) {
            return "Fisica";
        } else if ("PJ".equalsIgnoreCase(tipo)) {
            return "Juridica";
        }
        return tipo;
    }


    public String getPAG_PFPJ() {
        return PAG_PFPJ;
    }

    public String getREC_PFPJ() {
        return REC_PFPJ;
    }

    public String getPAG_REGIAO() {
        return PAG_REGIAO;
    }

    public String getREC_REGIAO() {
        return REC_REGIAO;
    }

    public String getPAG_IDADE() {
        return PAG_IDADE;
    }

    public String getREC_IDADE() {
        return REC_IDADE;
    }

    public String getFINALIDADE() {
        return FINALIDADE;
    }

    public Double getVALOR() {
        return VALOR;
    }

    public Integer getQUANTIDADE() {
        return QUANTIDADE;
    }

    @Override
    public String toString() {
        return "Pix{" +
                "ano=" + anoMes +
                ", PAG_PFPJ='" + PAG_PFPJ + '\'' +
                ", REC_PFPJ='" + REC_PFPJ + '\'' +
                ", PAG_REGIAO='" + PAG_REGIAO + '\'' +
                ", REC_REGIAO='" + REC_REGIAO + '\'' +
                ", PAG_IDADE='" + PAG_IDADE + '\'' +
                ", REC_IDADE='" + REC_IDADE + '\'' +
                ", FINALIDADE='" + FINALIDADE + '\'' +
                ", VALOR=" + VALOR +
                ", QUANTIDADE=" + QUANTIDADE +
                '}';
    }
}
