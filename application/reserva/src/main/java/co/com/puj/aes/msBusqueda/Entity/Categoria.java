package co.com.puj.aes.msBusqueda.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Categoria  implements Serializable {
    private String categorianame;
    private String categoriacode;



}
