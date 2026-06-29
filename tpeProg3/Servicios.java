package tpeProg3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servicios {
    private List<Paquete> paquetes;
    private List<Camion> camiones;

    private Map<String, Paquete> indicePorCodigo;

    private List<Paquete> conAlimentos;
    private List<Paquete> sinAlimentos;

    private List<Paquete> ordenadosPorUrgencia;
    /*
        La complejidad temporal del constructor : O(K + N log N) 
        Teniendo en cuenta que K es la cantidad de camiones y N es la cantidad de paquetes.
    */
    public Servicios(String pathCamiones, String pathPaquetes){

        try {
            this.camiones = CargadorDatos.cargarCamiones(pathCamiones);
            this.paquetes = CargadorDatos.cargarPaquetes(pathPaquetes);
        } catch (IOException e) {
            System.err.println("Error al cargar los archivos: " + e.getMessage());
            this.camiones = new ArrayList<>();
            this.paquetes = new ArrayList<>();
        }

        indicePorCodigo = new HashMap<>();
        conAlimentos = new ArrayList<>();
        sinAlimentos = new ArrayList<>();
        
        for (Paquete p : paquetes) {
            indicePorCodigo.put(p.getCodigo(), p);
            if (p.getContieneAlimentos()) {
                conAlimentos.add(p);
            } else {
                sinAlimentos.add(p);
            }
        }
/* Priorizamos la complejidad temporal de los servicios sobre el almacenamiento, utilizando estructuras auxiliares  */

        ordenadosPorUrgencia = new ArrayList<>(paquetes);
        ordenadosPorUrgencia.sort(new ComparadorPorUrgencia());
}


/*
* Complejidad temporal del servicio 1 : O(1) 
*/
public Paquete servicio1(String codigoPaquete) { 
    return indicePorCodigo.get(codigoPaquete);
}
/*
* Complejidad temporal del servicio 2 : O(1)
*/
public List<Paquete> servicio2(boolean contieneAlimentos) {
    if(contieneAlimentos){
        return this.conAlimentos;
    } else {
        return this.sinAlimentos;
    }
}
/*
* Complejidad temporal del servicio 3 : O(log N + N) teniendo en cuenta que N son la cantidad de paquetes
*/
public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();

        int inicio = busquedaBinariaInferior(urgenciaMinima);
        for (int i = inicio; i < ordenadosPorUrgencia.size(); i++) {
            Paquete p = ordenadosPorUrgencia.get(i);
            if (p.getUrgencia() > urgenciaMaxima) break;
            if (p.getUrgencia() >= urgenciaMinima) resultado.add(p);
        }
        return resultado;
    }

    /*
* Complejidad temporal busqueda binaria : O(log N) teniendo en cuenta que N son la cantidad de paquetes
*/
   private int busquedaBinariaInferior(int valor) {
        int lo = 0, hi = ordenadosPorUrgencia.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (ordenadosPorUrgencia.get(mid).getUrgencia() < valor) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public List<Camion> getCamiones(){
        return camiones;
    }
    public List<Paquete> getTodosPaquetes(){
        return paquetes;
    }
}
