import androidx.room.Dao;
import androidx.room.Insert;

@Dao

// For Database Methods

public interface MyDao {
    @Insert
    public void addUser(User user);
}
