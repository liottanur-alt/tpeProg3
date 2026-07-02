package tpeProg3;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String pathCamiones = "Camiones.csv"; 
        String pathPaquetes = "Paquetes.csv";


        System.out.println("PRUEBA REENTREGA TP - PROGRAMACION 3");
        System.out.println("------------------------------\n");

        // --- SERVICIOS ---
        System.out.println("--- PRIMERA PARTE: SERVICIOS ---");
        Servicios servicios = new Servicios(pathCamiones, pathPaquetes);

        // Prueba Servicio 1
        System.out.println("> Servicio 1 (Buscar paquete por codigo):");
        System.out.println("  " + servicios.servicio1("PKG001"));

        // Prueba Servicio 2
        System.out.println("> Servicio 2 (Paquetes con alimentos):");
        System.out.println("  Cantidad encontrada: " + servicios.servicio2(true).size());

        // Prueba Servicio 3
        System.out.println("> Servicio 3 (Urgencia entre minimo y maximo):");
        System.out.println("  Cantidad encontrada: " + servicios.servicio3(50, 100).size());


        // --- ALGORITMOS --- (utilizamos el caso de Test 1)
        System.out.println("\n=================================================");
        System.out.println("--- SEGUNDA PARTE: Algoritmos ---");
        
        List<Camion> camiones = servicios.getCamiones();
        List<Paquete> paquetes = servicios.getTodosPaquetes();

        Greedy greedyAlg = new Greedy();
        Solucion solGreedy = greedyAlg.greedy(camiones, paquetes);
        System.out.println("-------------------------");
        System.out.println(" - Backtracking");

        Backtracking backAlg = new Backtracking();
        Solucion solBack = backAlg.backtracking(camiones, paquetes);
        
        imprimirSolucion(solBack);
        System.out.println("Métrica para analizar el costo (estados generados): " + solBack.getEstadosGenerados());
        System.out.println("-------------------------");
        System.out.println("- Greedy");
        imprimirSolucion(solGreedy);
        System.out.println("Métrica para analizar el costo (candidatos considerados): " + solGreedy.getCandidatosConsiderados());
    }

    private static void imprimirSolucion(Solucion solucion) {
        System.out.println("Solución obtenida:");
        for (Map.Entry<Integer, List<Paquete>> entry : solucion.getAsignaciones().entrySet()) {
            System.out.print("  Camión " + entry.getKey() + ": ");
            if (entry.getValue().isEmpty()) {
                System.out.println("Sin paquetes asignados.");
            } else {
                for (Paquete p : entry.getValue()) {
                    System.out.print("[" + p.getCodigo() + "] ");
                }
                System.out.println();
            }
        }
        System.out.println("Peso no asignado: " + solucion.getPesoNoAsignado() + " kg.");
    }
}
