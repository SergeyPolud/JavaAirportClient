package users;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class ClientUser extends User implements Serializable
{
    @Serial
    private static final long serialVersionUID = 420L;
    public int userNumber;
    public ClientUser(String username, String password)
    {
        super.username = username;
        super.password = password;
        userNumber = new Random().nextInt(99999);
    }
}
