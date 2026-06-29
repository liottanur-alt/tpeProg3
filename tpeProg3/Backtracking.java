package tpeProg3;

import java.util.List;

public class Backtracking {

    private List<Camion> camiones;
    private List<Paquete> paquetes;
    private int[] pesoActualPorCamion;
    private int estadosGenerados;
    private int mejorPesoNoAsignadoGlobal;

    /*
     * Estrategia de resolución (Backtracking):
     * Exploramos el espacio de soluciones evaluando asignar cada paquete a los camiones 
     * disponibles (verificando capacidad y refrigeración) o dejándolo sin asignar.
     * * Poda: Mantenemos un registro del 'peso no asignado acumulado' en la rama recursiva 
     * actual. Si en algún punto este peso acumulado iguala o supera al 'mejorPesoNoAsignadoGlobal'
     * encontrado hasta el momento, cortamos la exploración de esa rama.
     */
    public Solucion backtracking(List<Camion> camiones, List<Paquete> paquetes) {
        this.camiones = camiones;
        this.paquetes = paquetes;
        this.pesoActualPorCamion = new int[camiones.size()];
        this.estadosGenerados = 0;
        this.mejorPesoNoAsignadoGlobal = Integer.MAX_VALUE; 

        Solucion actual = new Solucion(camiones);
        
        Solucion peorSolucion = new Solucion(camiones);
        for (Paquete p : paquetes) {
            peorSolucion.agregarNoAsignado(p); // arrancan todos sin asignar
        }
        Solucion[] bestRef = new Solucion[] { peorSolucion };


        resolver(0, actual, 0, bestRef);

        bestRef[0].setEstadosGenerados(estadosGenerados);
        return bestRef[0];
    }

    private void resolver(int indicePaquete, Solucion actual, int pesoNoAsignadoAcumulado, Solucion[] bestRef) {
        estadosGenerados++;

        // PODA (De optimidad): Si el peso no asignado actual ya es mayor o igual al de la mejor solución, podamos.
        if (pesoNoAsignadoAcumulado >= mejorPesoNoAsignadoGlobal) {
            return;
        }

        if (indicePaquete == paquetes.size()) {
            bestRef[0] = new Solucion(actual);
            mejorPesoNoAsignadoGlobal = pesoNoAsignadoAcumulado;
            return;
        }

        Paquete p = paquetes.get(indicePaquete);

        // Intentamos asignar el paquete a cada camión disponible
        for (int i = 0; i < camiones.size(); i++) {
            Camion camion = camiones.get(i);

            if (pesoActualPorCamion[i] + p.getPeso() > camion.getCapacidadMaxima()) {
                continue;
            }

            if (p.getContieneAlimentos() && !camion.getRefrigeracion()) {
                continue;
            }

            
            actual.asignarPaquete(camion.getId(), p);
            pesoActualPorCamion[i] += p.getPeso();

            resolver(indicePaquete + 1, actual, pesoNoAsignadoAcumulado, bestRef);

            actual.desasignarPaquete(camion.getId(), p);
            pesoActualPorCamion[i] -= p.getPeso(); 
        }

        actual.agregarNoAsignado(p);
        
        resolver(indicePaquete + 1, actual, pesoNoAsignadoAcumulado + p.getPeso(), bestRef);
        
        actual.quitarNoAsignado(p);
    }
}