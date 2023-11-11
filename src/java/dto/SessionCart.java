/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lashan
 */
public class SessionCart {

    private Date date;
    private Double total;
    private Boolean isActive;
    private List<SessionCartItem> itemList;

    public List<SessionCartItem> getItemList() {
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        return itemList;
    }

    public void setItemList(List<SessionCartItem> itemList) {
        this.itemList = itemList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
