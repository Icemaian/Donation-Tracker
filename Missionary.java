/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Isaac_Craig
 */
public class Missionary {
    
        // @XmlElement specifies XML element name for each object in the List
    @XmlElement(name = "missionary")
    private final Person persons = new Person(); // holds Missionary and donator list tiems

    public Person getPerson() {
        return persons;
    }
}
