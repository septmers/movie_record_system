package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tag")
@NamedQueries({
    @NamedQuery(
            name = "getRegisteredTag_id",
            query = "SELECT t.id FROM Tag AS t WHERE t.tag = :tag"
            ),
    @NamedQuery(
            name = "getTags",
            query = "SELECT t FROM Tag AS t WHERE t.id IN(SELECT tm.tag_id FROM TagMap AS tm WHERE tm.record_id = :record_id)"
            )
})


@Entity

public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    //ゲッターセッター
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTag(){
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public Timestamp getCreated_at(){
        return created_at;
    }

    public void setCreated_at(Timestamp created_at){
        this.created_at = created_at;
    }
}
