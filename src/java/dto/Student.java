/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Lashan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {
    private String id;
    private String name;
    private String age;
}
