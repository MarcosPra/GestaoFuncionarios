package util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GeralUtil {
    public static String formatarDataBR(LocalDate data) {
        if (data == null) return null;
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatoBR);
    }

    public static String formatarValorMonetario(Double valor) {
        if(valor == null) return null;
        DecimalFormat formatoMonetario = new DecimalFormat("0.00");
        return "R$ " + formatoMonetario.format(valor);
    }
}
