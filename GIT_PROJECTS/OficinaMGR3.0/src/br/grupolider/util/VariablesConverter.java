package br.grupolider.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigues rafael
 */
public class VariablesConverter {

    public String formatDoubleToString(double d) {

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        String numero = null;

        try {
            if (d != 0) {
                numero = nf.format(d);
            } else {
                numero = "0";
            }
        } catch (Exception ex) {
            System.err.println("Erro ao formatar numero: " + ex);
        }
        return numero;
    }

    public String formatData(String str) throws ParseException {
        try {
            if (!str.equalsIgnoreCase("")) {
                StringBuilder stringBuilder = new StringBuilder(str);
                stringBuilder.insert(str.length() - 2, '-');
                stringBuilder.insert(str.length() - 4, '-');

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                java.util.Date date = format.parse(stringBuilder.toString());

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String result = df.format(date);
                str = result;
            }
        } catch (ParseException ex) {
            Logger.getLogger(VariablesConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
    
}
