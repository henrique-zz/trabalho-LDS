package org;

import org.lojadediscos.util.I18n;
import org.lojadediscos.view.TelaCadastroDisco;
import java.util.Locale;

/**
 * Classe que iremos dar o "run", para executar tudo.
 */


public class Main {
    public static void main(String[] args) {
        // lógica para definir o idioma
        String idioma = "pt";
        String pais = "BR";

        if (args.length == 2) {
            idioma = args[0];
            pais = args[1];
        } else {
            // Usa o idioma do sistema operacional como padrão
            idioma = System.getProperty("user.language", "pt");
            pais = System.getProperty("user.country", "BR");
        }

        // define o Locale na classe I18n antes de criar qualquer tela
        I18n.setLocale(new Locale(idioma, pais));

        new TelaCadastroDisco();
    }
}
