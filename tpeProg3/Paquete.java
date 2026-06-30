package tpeProg3;

public class Paquete {
        private int id;
        private String codigo;
        private int peso;
        private boolean contieneAlimentos;
        private int urgencia;

        public Paquete(int id, String codigo, int peso, boolean contieneAlimentos, int urgencia) {
            this.id = id;
            this.codigo = codigo;
            this.peso = peso;
            this.contieneAlimentos = contieneAlimentos;
            this.urgencia = urgencia;
        }

        @Override
        public String toString() {
            return "Paquete{id=" + id + ", codigo=" + codigo + ", peso=" + peso + ", alimentos=" + contieneAlimentos + ", urgencia=" + urgencia + "}";
        }
        
        public int getUrgencia(){
            return this.urgencia;
        }

        public int getPeso(){
            return this.peso;
        }
        public String getCodigo() {
            return codigo;
        }
        public int getId() {
            return id;
        }
        public boolean getContieneAlimentos(){
            return contieneAlimentos;
        }
}
