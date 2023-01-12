/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pwss.uass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwss.uass.model.entity.Databarang;
import pwss.uass.model.jpa.DatabarangJpaController;

/**
 *
 * @author DELL
 */
@RestController
@CrossOrigin
@RequestMapping("/uas")
public class barangController {
    
     //declare entity class
    Databarang dt = new Databarang();
    //declare jpa class
    DatabarangJpaController ctrl = new DatabarangJpaController();
    
    //get mapping all data
    @GetMapping
    public List<Databarang> getData(){
        List<Databarang> data = new ArrayList<>();
    try{ //try 
        data = ctrl.findDatabarangEntities(); //get all data from database
    } catch (Exception e){ //catch
    }
    return data; //return object data
    }
    
    //get mapping using id
    @GetMapping("/{id}")
    public List<Databarang> getDatabarangEntities(@PathVariable("id")int id){
        List<Databarang> dataa = new ArrayList<Databarang>(); //create new object
        try{ //try
        dt = ctrl.findDatabarang(id);//get data by id
        dataa.add(dt);
        }catch (Exception e){} //catch
        return dataa; //return object dataa
    }
    
    //post mapping
    @PostMapping
    public String insertData(HttpEntity<String> requestdata) { //accept data from user
        String message = "Success insert new data"; //pesan ketika berhasil
        try{ //try
        String json_receive = requestdata.getBody();
         
        ObjectMapper map = new ObjectMapper(); //create object baru untuk ubah data
         
        dt = map.readValue(json_receive, Databarang.class);
        
        ctrl.create(dt); //insert data
        
        }catch(Exception e){ //catch
            message = "Failed to insert new data"; //pesan ketika gagal
        }
         return message; //return object mesage
    }
    
    //put mapping
    @PutMapping
    public String updateData(HttpEntity<String> requestdata) { //accept data from user
        String message = "Success edit the data"; //pesan ketika berhasil
        try{ //try
        String json_receive = requestdata.getBody();
         
        ObjectMapper map = new ObjectMapper(); //create object baru untuk ubah data
         
        dt = map.readValue(json_receive, Databarang.class);
        
        ctrl.edit(dt); //edit data
        
        }catch(Exception e){ //catch
            message = "Failed to edit the data"; //pesan ketika gagal
        }
         return message; //return object mesage
    }
    
    //delete mapping
    @DeleteMapping
    public String deleteData(HttpEntity<String> requestdata) { //accept data from user
        String message = "Success delete the data"; //pesan ketika berhasil
        try{ //try
        String json_receive = requestdata.getBody();
         
        ObjectMapper map = new ObjectMapper(); //create object baru untuk ubah data
         
        dt = map.readValue(json_receive, Databarang.class);
        
        ctrl.destroy(dt.getId()); //delete data
        
        }catch(Exception e){ //catch
            message = "Failed to delete the data"; //pesan ketika gagal
        }
         return message; //return object "mesage"
    }
}
