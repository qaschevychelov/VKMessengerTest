package data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    public String phone;
    public String pass;

    public User(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
    }
}
