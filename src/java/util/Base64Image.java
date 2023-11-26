/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import model.Item;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Lashan
 */
public class Base64Image {
    
    public static String convertItem(Item item)throws IOException{        
        String path = item.getImgurl().replace("\\", "/");
        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return "data:image/jpeg;base64," + encodedString;    
    }    
}
