package carteen.edu.seu.com.carteen.Model;

import java.io.Serializable;

/**
 * Created by Mind on 2017/10/11.
 */
public class Comment implements Serializable{
    private User user;
    private String content;

    public Comment(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Comment() {
        this.user = new User();
        this.content="这个我好像没看过";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
