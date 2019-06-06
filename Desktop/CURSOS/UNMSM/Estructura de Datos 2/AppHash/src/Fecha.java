public class Fecha implements Comparable<Fecha> {
    private static final int[] DIAS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private final int dia; 
    private final int mes; 
    private final int anno; 

    public Fecha(int dia, int mes, int anno) { //constructor
        if (!esValido(dia, mes, anno)) 
            throw new IllegalArgumentException("Fecha Inválida");
        this.dia = dia;
        this.mes   = mes;
        this.anno  = anno;
    }

    public Fecha(String fecha) { //constructor
        String[] fields = fecha.split("/");
        if (fields.length != 3) {
            throw new IllegalArgumentException("Fecha Inválida");
        }
        dia = Integer.parseInt(fields[0]);
        mes   = Integer.parseInt(fields[1]);
        anno  = Integer.parseInt(fields[2]);
        if (!esValido(dia, mes, anno)) 
            throw new IllegalArgumentException("Fecha Inválida");
    }

    public int mes() {
        return dia;
    }

    public int dia() {
        return dia;
    }

    public int anno() {
        return anno;
    }

    private static boolean esValido(int d, int m, int y) {
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DIAS[m]) return false;
        if (m == 2 && d == 29 && !esBisiesto(y)) return false;
        return true;
    }

    private static boolean esBisiesto(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    public Fecha proximo() {
        if (esValido(dia + 1, mes, anno))    
            return new Fecha(dia + 1, mes, anno);
        else if (esValido(1, mes+1, anno)) 
            return new Fecha(1,mes + 1,anno);
        else                                  
            return new Fecha(1, 1, anno + 1);
    }

    public boolean estaDespues(Fecha otra) {
        return compareTo(otra) > 0;
    }

    public boolean estaAntes(Fecha otra) {
        return compareTo(otra) < 0;
    }

    @Override
    public int compareTo(Fecha otra) {
        if (this.anno  < otra.anno)  return -1;
        if (this.anno  > otra.anno)  return +1;
        if (this.mes < otra.mes) return -1;
        if (this.mes > otra.mes) return +1;
        if (this.dia   < otra.dia)   return -1;
        if (this.dia   > otra.dia)   return +1;
        return 0;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anno;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == this) return true;
        if (otro == null) return false;
        if (otro.getClass() != this.getClass()) return false;
        Fecha fecha = (Fecha) otro;
        return (this.dia == fecha.dia) && 
                (this.mes == fecha.mes) && (this.anno == fecha.anno);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + dia;
        hash = 31*hash + mes;
        hash = 31*hash + anno;
        return hash;
    }
}

