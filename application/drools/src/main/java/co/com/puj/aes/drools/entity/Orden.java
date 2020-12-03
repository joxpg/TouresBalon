package co.com.puj.aes.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    private String category;
    private long amount;
    private  boolean check;

}
