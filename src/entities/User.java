package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Class User
 * <p>
 * Created by Yuraga on 14/10/2018.
 */
@Data
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private Date birthday;
    private String status;
    private String rights;

    //Используем для сохранения
    public User(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    //Используем для вывода списка
    public User(String name, String email, String password, Date birthday, String status, String rights, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.status = status;
        this.rights = rights;
    }
}
