package tpeProg3;

public class Camion {
        private int id;
        private String patente;
        private boolean refrigerado;
        private int capacidadMaxima;

        public Camion(int id, String patente, boolean refrigerado, int capacidadMaxima) {
            this.id = id;
            this.patente = patente;
            this.refrigerado = refrigerado;
            this.capacidadMaxima = capacidadMaxima;
        }

        public int getCapacidadMaxima() {
            return capacidadMaxima;
        }
        public int getId() {
            return id;
        }
        public String getPatente() {
            return patente;
        }

        public boolean getRefrigeracion(){
            return refrigerado;
        }

        @Override
        public String toString() {
            return "Camion{id=" + id + ", patente=" + patente + ", refrigerado=" + refrigerado + ", capacidad=" + capacidadMaxima + "}";
        }
    }
