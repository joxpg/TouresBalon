package co.com.puj.aes.proveedor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    /**
     * @author Johan Céspedes at PUJ
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Calificacion  {
        private Short idCalificacion;
        private String idProveedor;
        private String calificacion;
        private boolean estadoCalificacion;
        public Calificacion (Short idCalificacion) {this.idCalificacion = idCalificacion;}

        public Calificacion(String proveedor) {
        }
    }


