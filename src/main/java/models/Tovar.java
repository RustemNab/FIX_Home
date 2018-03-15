package models;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Рустем on 14.03.2018.
 */
@Data
@Builder
public class Tovar {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int id;
    private String name;
    private int price;

    public Tovar(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
