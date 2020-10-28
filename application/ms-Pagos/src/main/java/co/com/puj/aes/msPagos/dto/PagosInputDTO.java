package co.com.puj.aes.msPagos.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.util.Collection;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagosInputDTO {

    private Short idEps;
    @Size(max = 100)
    private String nombreEps;
    @Size(max = 100)
    private String direccionEps;
    @Size(max = 75)
    private Boolean estadoEps;
    @Size(max = 100)
    private String direccion;
    @Size(max = 15)
    private String telefonoEps;

}
