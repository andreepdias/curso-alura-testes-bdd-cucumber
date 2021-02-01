package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeilaoSteps {

    private LoginPage loginPage;
    private LeiloesPage leiloesPage;
    private NovoLeilaoPage novoLeilaoPage;
    private Browser browser;

    @Dado("um usuário logado")
    public void um_usuário_logado() {

        this.browser = new Browser();
        this.browser.seed();
        this.loginPage = this.browser.getLoginPage();
        this.leiloesPage = this.loginPage.realizaLoginComoFulano();

    }

    @Quando("acessa a página de novo leilão")
    public void acessa_a_página_de_novo_leilão() {
        this.novoLeilaoPage = this.leiloesPage.visitaPaginaParaCriarUmNovoLeilao();
    }

    @Quando("preenche o forumulário com dados válidos")
    public void preenche_o_forumulário_com_dados_válidos() {
        this.leiloesPage = novoLeilaoPage.preencheForm("PC", "15000", "01/11/2020");
    }

    @Então("volta para a página de leilões")
    public void volta_para_a_página_de_leilões() {
        assertTrue(this.leiloesPage.estaNaPaginaDeLeiloes());
    }

    @Então("o novo leilão aparece na tela")
    public void o_novo_leilão_aparece_na_tela() {
        assertTrue(this.leiloesPage.existe("PC", "15000", "01/11/2020", "fulano"));
        this.browser.clean();
    }
}
