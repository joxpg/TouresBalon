package co.com.puj.aes.msPagos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class UpdateReserva implements Serializable {
        private String idBooking;
        private boolean active;

    }

