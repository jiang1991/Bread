package space.xxv.bread;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangjiang on 2017/1/18.
 */

@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class Note{
    @Id
    private Long id;

    @NotNull
    private String text;
    private String comment;
    private java.util.Date date;
@Generated(hash = 1028896452)
public Note(Long id, @NotNull String text, String comment,
        java.util.Date date) {
    this.id = id;
    this.text = text;
    this.comment = comment;
    this.date = date;
}
@Generated(hash = 1272611929)
public Note() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getText() {
    return this.text;
}
public void setText(String text) {
    this.text = text;
}
public String getComment() {
    return this.comment;
}
public void setComment(String comment) {
    this.comment = comment;
}
public java.util.Date getDate() {
    return this.date;
}
public void setDate(java.util.Date date) {
    this.date = date;
}
}
