package models;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Рустем on 14.03.2018.
 */
@Data
@Builder
public class Tovar {
    private int id;
    private String name;
    private int price;
}
