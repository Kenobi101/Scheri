package FinalCode.au.Scheri;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE userId = :userId AND password= :password")
    List<UserEntity> getusersbyuseridandpassword(String userId, String password);
}
