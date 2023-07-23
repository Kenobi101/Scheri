package FinalCode.au.Scheri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "userId")
    String userId;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "name")
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("setId : " + id.toString());
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        System.out.println("set userId : " + userId.toString());
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("setPassword : " + password);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName : " + name);
        this.name = name;
    }
}
