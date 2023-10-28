package model;
// Generated Oct 28, 2023 11:27:24 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Item generated by hbm2java
 */
@Entity
@Table(name="item"
    ,catalog="j2eeb1"
)
public class Item  implements java.io.Serializable {


     private String id;
     private String name;
     private String imgurl;
     private Boolean isactive;

    public Item() {
    }

	
    public Item(String id) {
        this.id = id;
    }
    public Item(String id, String name, String imgurl, Boolean isactive) {
       this.id = id;
       this.name = name;
       this.imgurl = imgurl;
       this.isactive = isactive;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=16)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="imgurl")
    public String getImgurl() {
        return this.imgurl;
    }
    
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    
    @Column(name="isactive")
    public Boolean getIsactive() {
        return this.isactive;
    }
    
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }




}


