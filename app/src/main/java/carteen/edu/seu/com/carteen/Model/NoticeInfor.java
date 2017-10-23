package carteen.edu.seu.com.carteen.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mind on 2017/10/23.
 */
public class NoticeInfor implements Serializable{
    String time;
    List<notice> information=new ArrayList<>();

    public List<notice> getInformation() {
        return information;
    }

    public void setInformation(List<notice> information) {
        this.information = information;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class notice{
        String title;
        String content;
        String informant;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getInformant() {
            return informant;
        }

        public void setInformant(String informant) {
            this.informant = informant;
        }
    }
}
