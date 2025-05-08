package conversor;

public class DatosConversor {
    String dolar;
    String pesoARG;
    String pesoCOP;
    String realBR;
    String valorConvertir = "";


    public String getValorConvertir() {
        return valorConvertir;
    }

    public DatosConversor (ConvertorAPI convertor) {
    this.dolar = convertor.conversion_rates().USD();
    this.pesoARG = convertor.conversion_rates().ARS();
    this.pesoCOP = convertor.conversion_rates().COP();
    this.realBR = convertor.conversion_rates().BRL();
}


    public double convertorMoneda(String opcion, int moneadaConvertir) {
        if (opcion.equals("1")){
            valorConvertir = "Pesos Argentinos";
            return moneadaConvertir * Double.parseDouble(pesoARG);
        } else if (opcion.equals("2")) {
            valorConvertir = "USD";
            return moneadaConvertir * Double.parseDouble(dolar);
        } else if (opcion.equals("3")) {
            valorConvertir = "Reales Brasileños";
            return moneadaConvertir * Double.parseDouble(realBR);
        } else if (opcion.equals("4")) {
            valorConvertir = "USD";
            return moneadaConvertir * Double.parseDouble(dolar);
        } else if (opcion.equals("5")) {
            valorConvertir = "Pesos Colombianos";
            return moneadaConvertir * Double.parseDouble(pesoCOP);
        } else {
            valorConvertir = "USD";
            return moneadaConvertir * Double.parseDouble(dolar);
        }
    }

    @Override
    public String toString() {
        return "DatosConversor{" +
                "dolar=" + dolar +
                ", pesoARG=" + pesoARG +
                ", pesoCOP=" + pesoCOP +
                ", realBR=" + realBR;
    }
}
