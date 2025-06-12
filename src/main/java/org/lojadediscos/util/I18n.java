package org.lojadediscos.util;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe central para gerenciar a internacionalização (i18n).
 * Carrega o arquivo de idioma apropriado (ResourceBundle) e fornece
 * métodos para obter textos (Strings) e formatos de moeda.
 */
public class I18n {

    private static ResourceBundle bundle;
    private static Locale locale;

    /**
     * Inicializa o gerenciador com um Locale específico.
     * Deve ser chamado no início da aplicação.
     * @param loc O Locale a ser usado (ex: pt_BR, en_US).
     */

    public static void setLocale(Locale loc) {
        locale = loc;
        bundle = ResourceBundle.getBundle("Messages", locale);
    }

    /**
     * Retorna a string traduzida para a chave fornecida.
     * @param key A chave da string no arquivo .properties.
     * @return A string traduzida.
     */

    public static String getString(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            // Retorna a própria chave se não encontrar a tradução, para facilitar a depuração.
            return key;
        }
    }

    /**
     * Formata um valor numérico como moeda, de acordo com o Locale atual.
     * Ex: R$ 10,00 para pt_BR, $10.00 para en_US.
     * @param amount O valor a ser formatado.
     * @return A string formatada como moeda.
     */
    public static String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount);
    }
}