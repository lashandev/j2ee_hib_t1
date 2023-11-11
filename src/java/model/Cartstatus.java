package model;
// Generated Nov 11, 2023 12:17:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cartstatus generated by hbm2java
 */
@Entity
@Table(name="cartstatus"
    ,catalog="j2eeb1"
)
public class Cartstatus  implements java.io.Serializable {


     private int id;
     private String description;
     private Set<Cart> carts = new HashSet<Cart>(0);

    public Cartstatus() {
    }

	
    public Cartstatus(int id) {
        this.id = id;
    }
    public Cartstatus(int id, String description, Set<Cart> carts) {
       this.id = id;
       this.description = description;
       this.carts = carts;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="description", length=20)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cartstatus")
    public Set<Cart> getCarts() {
        return this.carts;
    }
    
    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }




}


