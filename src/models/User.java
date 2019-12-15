package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "sex", nullable = false)
    private Integer sex;    //0･･･Male 1･･･Female 2･･･Others

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "passwoed", nullable = false)
    private String password;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


    //getter setter
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public Integer getSex(){
        return sex;
    }

    public void setSex(Integer sex){
        this.sex = sex;
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

    public String getUser_name(){
        return user_name;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getDelete_flag(){
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag){
        this.delete_flag = delete_flag;
    }
}
