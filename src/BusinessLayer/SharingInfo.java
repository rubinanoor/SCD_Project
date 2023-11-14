package BusinessLayer;
import java.util.Date;
public class SharingInfo {
    private User user;
    private Date sharedDate;
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }

    public Date getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(Date sharedDate) {
        this.sharedDate = sharedDate;
    }
}
