package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tagTable")
@NamedQueries({
    @NamedQuery(
            name = "getTagMaps",//該当レコードのタグマップを取得する
            query = "SELECT tm FROM TagMap AS tm WHERE tm.record_id = :record_id"
            )
})

@Entity

public class TagMap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "record_id", nullable = false)
    private Integer record_id;

    @JoinColumn(name = "tag_id", nullable = false)
    private Integer tag_id;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;


    //ゲッターセッター
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getRecord_id(){
        return record_id;
    }

    public void setRecord_id(Integer record_id){
        this.record_id = record_id;
    }

    public Integer getTag_id(){
        return tag_id;
    }

    public void setTag_id(Integer tag_id){
        this.tag_id = tag_id;
    }

    public Timestamp getCreated_at(){
        return created_at;
    }

    public void setCreated_at(Timestamp created_at){
        this.created_at = created_at;
    }
}
