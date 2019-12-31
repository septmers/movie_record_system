package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "record")

@NamedQueries({
    @NamedQuery(
            name = "getAllRecords",           //全ての映画記録を取得する
            query = "SELECT r FROM Record AS r ORDER BY r.created_at DESC"
            ),
    @NamedQuery(
            name = "getAllMyRecords",         //ログインユーザの全ての映画記録を取得する
            query = "SELECT r FROM Record AS r WHERE r.user_id = :user ORDER BY r.created_at DESC"
            ),
    @NamedQuery(
            name = "getTaggedRecords",        //該当のタグが付けられた記録一覧を取得する
            query = "SELECT r FROM Record AS r WHERE r.id IN (SELECT tm.record_id FROM TagMap AS tm WHERE tm.tag_id = :tag_id) ORDER BY r.created_at DESC"
            ),
    @NamedQuery(
            name = "getTaggedRecordsCount",        //該当のタグが付けられた記録一覧を取得する
            query = "SELECT COUNT(r) FROM Record AS r WHERE r.id IN (SELECT tm.record_id FROM TagMap AS tm WHERE tm.tag_id = :tag_id)"
            ),
    @NamedQuery(
            name = "getMyRecords",
            query = "SELECT r FROM Record AS r WHERE r.user_id = :user_id ORDER BY r.created_at DESC"
            )
})

@Entity


public class Record {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    private Integer genre;
    //「1」･･･Anime
    //「2」･･･LoveRomance
    //「3」･･･Horror
    //「4」･･･SF
    //「5」･･･Comedy
    //「6」･･･Action
    //「7」･･･Documentary
    //「8」･･･HumanDrama
    //「9」･･･Mystery
    //「10」･･･Others

    @Column(name = "value", nullable = false)
    private Integer value;
    //「1」･･･★☆☆☆☆
    //「2」･･･★★☆☆☆
    //「3」･･･★★★☆☆
    //「4」･･･★★★★☆
    //「5」･･･★★★★★


    @Column(name = "impression", nullable = false)
    private String impression;

    @Column(name = "mylife", nullable = false)
    private String mylife;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;
    //「0」･･･未削除
    //「1」･･･削除済み

    @JoinColumn(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "user_age", nullable = false)
    private Integer user_age;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;



    //ゲッターセッター
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Integer getGenre(){
        return genre;
    }

    public void setGenre(Integer genre){
        this.genre = genre;
    }

    public Integer getValue(){
        return value;
    }

    public void setValue(Integer value){
        this.value = value;
    }

    public String getImpression(){
        return impression;
    }

    public void setImpression(String impression){
        this.impression = impression;
    }

    public String getMylife(){
        return mylife;
    }

    public void setMylife(String mylife){
        this.mylife = mylife;
    }

    public Timestamp getCreated_at(){
        return created_at;
    }

    public void setCreated_at(Timestamp created_at){
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at(){
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at){
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag(){
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag){
        this.delete_flag = delete_flag;
    }

    public Integer getUser_id(){
        return user_id;
    }

    public void setUser_id(Integer user_id){
        this.user_id = user_id;
    }

    public Integer getUser_age(){
        return user_age;
    }

    public void setUser_age(Integer user_age){
        this.user_age = user_age;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}
