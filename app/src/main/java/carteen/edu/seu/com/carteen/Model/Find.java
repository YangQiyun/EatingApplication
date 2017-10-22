package carteen.edu.seu.com.carteen.Model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/8.
 */
public class Find implements MultiItemEntity,Serializable{
    String Title;
    String author;
    int imge;
    String content;
    boolean isSpecial;

    public Find(){
        Title="这些小技巧：让你在饮食方面不断的健康调和轻松变强";
        author="小军";
        imge=R.drawable.test1;
        content="正文";
        isSpecial=false;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImge() {
        return imge;
    }

    public void setImge(int imge) {
        this.imge = imge;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public int getItemType() {
        return isSpecial?1:0;
    }
}
