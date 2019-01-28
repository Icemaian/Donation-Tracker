/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXB;

/**
 *
 * @author Isaac_Craig
 */
public class MissionaryDonationModel {

    static String location;
    Persons MissionaryDonation;
    Missionary missionary;
    
    public void setLocation(Stage stage) {
        FileChooser picker = new FileChooser();
        picker.setTitle("Choose file");
        File file = picker.showOpenDialog(stage);
        location = file.toString();
    }

    public String getLocation() {
        return location;
    }

    public MissionaryDonationModel() {
        MissionaryDonation = new Persons();
        missionary = new Missionary();
    }
    
    public void updateList() {
        try (BufferedWriter output
                = Files.newBufferedWriter(Paths.get(location))) {
            // Write the Shopping List to XML file
            JAXB.marshal(MissionaryDonation, output);
        } catch (IOException ioException) {
            System.err.println("Error opening file.");
        }
    }
    
    public void removeDonation(Person Person) {
        Iterator<Person> iterator = MissionaryDonation.getList().iterator();
        boolean found = false;
        while (iterator.hasNext())
        {
            if (iterator.next().getAddress().toLowerCase().equals(Person.getAddress().toLowerCase()))
            {
                iterator.remove();
                found = true;
                break;
            }
        }  
        if (found)
            updateList();
    }
    
    public List<Person> getItems()
    {
        return MissionaryDonation.getList();
    }
    
    public Person getMission()
    {
        return missionary.getPerson();
    }
    
     public void loadMissionary() {
        // test if file is present
        if (Files.exists(Paths.get(getLocation()))) {
            // try to open file for deserialization
            try (BufferedReader input
                    = Files.newBufferedReader(Paths.get(getLocation()))) {
                // unmarshal the file's contents
                missionary = JAXB.unmarshal(input, Missionary.class);
            } catch (IOException ioException) {
                System.err.println("Error opening file.");
            }
        }
    }
     
    public void loadList() {
        // test if file is present
        if (Files.exists(Paths.get(getLocation()))) {
            // try to open file for deserialization
            try (BufferedReader input
                    = Files.newBufferedReader(Paths.get(getLocation()))) {
                // unmarshal the file's contents
                MissionaryDonation = JAXB.unmarshal(input, Persons.class);
            } catch (IOException ioException) {
                System.err.println("Error opening file.");
            }
        }
    }
    
    public void addPerson(Person Person) {
        if (Person.getAddress().length() > 0)
        {
            Iterator<Person> iterator = MissionaryDonation.getList().iterator();
            boolean found = false;
            while (iterator.hasNext())
            {
                Person currentAddress = iterator.next();
                if (currentAddress.getAddress().toLowerCase().equals(Person.getAddress().toLowerCase()))
                {   // found matching Address in the list, update
                    currentAddress.setFirst(Person.getFirst());
                    currentAddress.setLast(Person.getLast());
                    currentAddress.setTelephone(Person.getTelephone());
                    currentAddress.setAddress(Person.getAddress());
                    currentAddress.setState(Person.getState());
                    currentAddress.setZip(Person.getZip());
                    found = true;   
                    break;
                }
            }  
            if (!found) // if existing Address not modified, add it
                MissionaryDonation.getList().add(Person);
            updateList();   // persist new list to file system
        }
    }
    
    public List<Person> findFirstLast(Person i)
    {
        List<Person> addresses = MissionaryDonation.getList();
        List<Person> matched = new ArrayList<>();
        if (i.getLast().length() > 0)
        {   
            for (Person Person : addresses)
            {
                if ((Person.getLast().toLowerCase().contains(i.getLast().toLowerCase()))
                        || (Person.getFirst().toLowerCase().contains(i.getLast().toLowerCase())))
                {
                    matched.add(Person);
                }
            }
        }
        return matched;    
    }
}
