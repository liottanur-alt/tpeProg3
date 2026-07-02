package tpeProg3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargadorDatos {

    public static List<Camion> cargarCamiones(String archivo) throws IOException {
        List<Camion> camiones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea; 
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                int id = Integer.parseInt(campos[0].trim());
                String patente = campos[1].trim();
                boolean refrigerado = campos[2].trim().equals("1");
                int capacidad = Integer.parseInt(campos[3].trim());
                camiones.add(new Camion(id, patente, refrigerado, capacidad));
            }
        }
        return camiones;
    }

    public static List<Paquete> cargarPaquetes(String archivo) throws IOException {
        List<Paquete> paquetes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                int id = Integer.parseInt(campos[0].trim());
                String codigo = campos[1].trim();
                int peso = Integer.parseInt(campos[2].trim());
                boolean alimentos = campos[3].trim().equals("1");
                int urgencia = Integer.parseInt(campos[4].trim());
                paquetes.add(new Paquete(id, codigo, peso, alimentos, urgencia));
            }
        }
        return paquetes;
    }
}