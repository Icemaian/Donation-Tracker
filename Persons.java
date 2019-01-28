/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Isaac_Craig
 */
public class Persons {
    
        // @XmlElement specifies XML element name for each object in the List
    @XmlElement(name = "person")
    private final List<Person> persons = new ArrayList<>(); // holds Missionary and donator list tiems

    public List<Person> getList() {
        return persons;
    }
}
