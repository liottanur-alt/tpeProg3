package tpeProg3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solucion {
    private Map<Integer, List<Paquete>> asignaciones;
    private List<Paquete> noAsignados;
    private int estadosGenerados;
    private int candidatosConsiderados; 

    public Solucion(List<Camion> camiones) {
        this.asignaciones = new HashMap<>();
        for (Camion c : camiones) {
            asignaciones.put(c.getId(), new ArrayList<>());
        }
        this.noAsignados = new ArrayList<>();
        this.estadosGenerados = 0;
        this.candidatosConsiderados = 0;
    }

    public Solucion(Solucion otra) {
        this.asignaciones = new HashMap<>();
        for (Map.Entry<Integer, List<Paquete>> entry : otra.asignaciones.entrySet()) {
            this.asignaciones.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        this.noAsignados = new ArrayList<>(otra.noAsignados);
        this.estadosGenerados = otra.estadosGenerados;
        this.candidatosConsiderados = otra.candidatosConsiderados;
    }
    public void asignarPaquete(int idCamion, Paquete p){
        asignaciones.get(idCamion).add(p);
    }

    public void desasignarPaquete(int idCamion, Paquete p){
        asignaciones.get(idCamion).remove(p);
    }

    public void agregarNoAsignado(Paquete p){
        noAsignados.add(p);
    }

    public void quitarNoAsignado(Paquete p){
        noAsignados.remove(p);
    }

    public int getPesoNoAsignado(){
        int total = 0;
        for (Paquete p : noAsignados){
            total += p.getPeso();
        }
        return total;
    }

    public void setEstadosGenerados(int n){
        this.estadosGenerados = n;
    }

    public void setCandidatosConsiderados(int n){
        this.candidatosConsiderados = n;
    }
    
    public int getEstadosGenerados() {
            return estadosGenerados;
        }

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }

    public Map<Integer, List<Paquete>> getAsignaciones() {
        return asignaciones;
    }
}
