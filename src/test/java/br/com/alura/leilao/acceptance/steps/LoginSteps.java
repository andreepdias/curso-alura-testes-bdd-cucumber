package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private Browser browser;
    private LoginPage loginPage;
    private LeiloesPage leiloesPage;

    /* usuário válido */

    @Dado("o usuário válido")
    public void o_usuário_válido() {
        browser = new Browser();
        browser.seed();
        loginPage = browser.getLoginPage();
    }

    @Quando("realiza login")
    public void realiza_login() {
        leiloesPage = this.loginPage.realizaLoginComo("fulano", "pass");
    }

    @Então("é redirecionado para a página de leilões")
    public void é_redirecionado_para_a_página_de_leilões() {
        assertTrue(this.leiloesPage.estaNaPaginaDeLeiloes());
        browser.clean();
    }

    /* usuário inválido */

    @Dado("o usuário inválido")
    public void o_usuário_inválido() {
        browser = new Browser();
        browser.seed();
        loginPage = browser.getLoginPage();
    }

    @Quando("tenta se logar")
    public void tenta_se_logar() {
        this.loginPage.realizaLoginComo("fulano", "incorrect_pass");
    }

    @Então("continua na página de login")
    public void continua_na_página_de_login() {
        assertTrue(this.loginPage.estaNaPaginaDeLoginComErro());
        browser.clean();
    }



}
