package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Parts
 * <p>
 * Created by Yuraga on 14/10/2018.
 */
@Data
@NoArgsConstructor
public class Parts {
    private long id;
    private String producer;
    private String category;
    private String img;
    private String name;
    private String chatacteristics;
    private double price;

    public Parts(String producer, String category, String img, String name, String chatacteristics, double price) {
        this.producer = producer;
        this.category = category;
        this.img = img;
        this.name = name;
        this.chatacteristics = chatacteristics;
        this.price = price;
    }

    public Parts(long id) {
        this.id = id;
    }
}
