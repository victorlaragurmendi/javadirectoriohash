import java.util.HashSet;

public final class NumeroTelefono {
    private final int area;   
    private final int inter; 
    private final int ext; 

    public NumeroTelefono(int area, int inter, int ext) {
        this.area = area;
        this.inter = inter;
        this.ext  = ext;
    }
        
    public NumeroTelefono(String fecha) {
        String[] fields = fecha.split("-");
        if (fields.length != 3) {
            throw new IllegalArgumentException("Numero de telefono invalido");
        }
        area = Integer.parseInt(fields[0]);
        inter   = Integer.parseInt(fields[1]);
        ext  = Integer.parseInt(fields[2]); 
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == this) return true;
        if (otro == null) return false;
        if (otro.getClass() != this.getClass()) return false;
        NumeroTelefono este = (NumeroTelefono) otro;
        return (this.area == este.area) && (this.inter == este.inter) && (this.ext == este.ext);
    }

    @Override
    public String toString() {
        return String.format("(%03d) %03d-%04d", area, inter, ext);
    }

    @Override
    public int hashCode() {
        return 31 * (area + 31 * inter) + ext;
    }

}
