package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Ord
 * <p>
 * Created by Yuraga on 14/10/2018.
 */
@Data
@NoArgsConstructor
public class Ord {
    private long id;
    private long userId;
    private long partsId;
    private int quantity;
    private double total;

    //Используем этот конструктор для сохранения деталей
    public Ord(long userId, long partsId, int quantity, double total) {
        this.userId = userId;
        this.partsId = partsId;
        this.quantity = quantity;
        this.total = total;
    }
}
