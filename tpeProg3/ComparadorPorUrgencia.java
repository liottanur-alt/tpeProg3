package tpeProg3;

import java.util.Comparator;

public class ComparadorPorUrgencia implements Comparator<Paquete>{

@Override
    public int compare(Paquete p1, Paquete p2){
        return Integer.compare(p1.getUrgencia(), p2.getUrgencia());
    }
    
}
