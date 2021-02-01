package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropontoLanceSteps {

    private Lance lance;
    private Leilao leilao;
    private ArrayList<Lance> lista;

    @Before
    public void setup(){
        this.lista = new ArrayList<Lance>();
        this.leilao = new Leilao("Tablet IPAD");
    }

    @Dado("um lance válido")
    public void um_lance_válido() {
        Usuario usuario = new Usuario("Fernanda.");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @Quando("propõe ao leilão")
    public void propõe_ao_leilão() {
        leilao.propoe(lance);
    }

    @Entao("o lance é aceito")
    public void o_lance_é_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }

    /* vários */

    @Dado("um lance de {double} reais da usuária {string}")
    public void um_lance_de_reais_da_usuária(Double valor, String nomeUsuario) {
        Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
        this.lista.add(lance);
    }

    @Quando("propõe vários lances ao leilão")
    public void propõe_vários_lances_ao_leilão() {
        this.lista.forEach(lance -> leilao.propoe(lance));
    }
    @Entao("os lances são aceitos")
    public void os_lances_são_aceitos() {
        assertEquals(this.lista.size(), leilao.getLances().size());
        assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
        assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
    }

    /* inválido */

    @Dado("um lance inválido de {double} reais")
    public void um_lance_inválido_de_reais(Double valor) {
        this.lance = new Lance(new BigDecimal(valor));
    }

    @Entao("o lance não é aceito")
    public void o_lance_não_é_aceito() {
        assertEquals(0, leilao.getLances().size());
    }

    /* inválido dois seguidos */

    @Entao("o segundo lance não é aceito")
    public void o_segundo_lance_não_é_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
    }

    @Dado("dois lances")
    public void dois_lances(DataTable dataTable) {
        List<Map<String, String>> valores = dataTable.asMaps();

        for (Map<String, String> mapa : valores) {
            String valor = mapa.get("valor");
            String nomeUsuario = mapa.get("nomeUsuario");

            Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
            lista.add(lance);
        }
    }




}
