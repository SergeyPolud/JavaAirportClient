package users;

import java.io.Serial;
import java.io.Serializable;

public class AdminUser extends User implements Serializable
{
    @Serial
    private static final long serialVersionUID = 999L;
    public AdminUser(String password)
    {
        super.username = username;
        super.password = password;
    }

}
