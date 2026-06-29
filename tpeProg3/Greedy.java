package tpeProg3;

import java.util.ArrayList;
import java.util.List;

public class Greedy {

    /*
    Primero ordenamos los paquetes por peso, y la estategia greedy en este caso es tomar un paquete
    (desde el de mayor peso al menor)y buscar entre los camiones que lo pueden transportar, el camion
    mas proximo a llenarse (con menor capacidad restante), si lo encuentra lo asigna y si no hay un 
    camion disponible que cumpla marca el paquete como no asignado.
    Elegimos este criterio porque al colocar primero los paquetes mas pesados disminuye el riesgo de 
    que no entren al final cuando los camiones ya esten casi llenos.
    */
    public Solucion greedy(List<Camion> camiones, List<Paquete> paquetes) {
        Solucion solucion = new Solucion(camiones);
        int candidatosConsiderados = 0;

        List<Paquete> ordenados = new ArrayList<>(paquetes);
        ordenados.sort(new ComparadorPorPeso());

        int[] capacidadRestante = new int[camiones.size()];
        for (int i = 0; i < camiones.size(); i++) {
            capacidadRestante[i] = camiones.get(i).getCapacidadMaxima();
        }

        for (Paquete p : ordenados) {
            int mejorIndice = -1;
            int menorCapacidadRestante = Integer.MAX_VALUE;

            for (int i = 0; i < camiones.size(); i++) {
                candidatosConsiderados++;
                Camion camion = camiones.get(i);

                if (p.getPeso() > capacidadRestante[i]) continue;

                if (p.getContieneAlimentos() && !camion.getRefrigeracion()) continue;

                if (capacidadRestante[i] < menorCapacidadRestante) {
                    menorCapacidadRestante = capacidadRestante[i];
                    mejorIndice = i;
                }
            }

            if (mejorIndice >= 0) {
                Camion elegido = camiones.get(mejorIndice);
                solucion.asignarPaquete(elegido.getId(), p);
                capacidadRestante[mejorIndice] -= p.getPeso();
            } else {
                solucion.agregarNoAsignado(p);
            }
        }

        solucion.setCandidatosConsiderados(candidatosConsiderados);
        return solucion;
    }
}
