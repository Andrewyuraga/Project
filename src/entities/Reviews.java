package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Reviews
 * <p>
 * Created by Yuraga 26/11/2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reviews {
    private long id;
    private long userId;
    private String reviews;

    public Reviews(long userId, String reviews) {
        this.userId = userId;
        this.reviews = reviews;
    }
}
