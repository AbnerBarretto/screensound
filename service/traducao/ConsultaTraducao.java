package br.com.alura.screensound.service.traducao;

import br.com.alura.screensound.service.ConsumoApi;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaTraducao {

    public static String traduzirTexto(String textoOriginal) {
        try {
            String textoCodificado = URLEncoder.encode(textoOriginal, StandardCharsets.UTF_8);
            String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);

            String urlTraducao = "https://api.mymemory.translated.net/get?q=" + textoCodificado + "&langpair=" + langpair;

            String respostaJson = ConsumoApi.buscarUrl(urlTraducao);

            ObjectMapper mapper = new ObjectMapper();
            DadosTraducao dados = mapper.readValue(respostaJson, DadosTraducao.class);

            return dados.dadosResposta().textoTraduzido();

        } catch (Exception e) {
            throw new RuntimeException("Erro na tradução", e);
        }
    }
}
