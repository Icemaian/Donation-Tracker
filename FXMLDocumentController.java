package javafxapplication5;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//!! Implement Initializable
public class FXMLDocumentController implements Initializable {

    Stage stage;
    MissionaryDonationModel myList = new MissionaryDonationModel();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    Person myMission = new Person();
    
    // mission field************************************************************
    @FXML
    private TextField missionAddress;
    @FXML
    private TextField missionState;
    @FXML
    private TextField missionFirst;
    @FXML
    private TextField missionZip;
    @FXML
    private TextField missionLast;
    @FXML
    private TextField missionPhone;
    @FXML
    private TextField missionGoal;
    @FXML
    private TextField missionTotal;
    //**************************************************************************

    // Donater fields***********************************************************
    @FXML
    private TextField donateLast;
    @FXML
    private TextField donatePhone;
    @FXML
    private TextField donateAddress;
    @FXML
    private TextField donateState;
    @FXML
    private TextField donateZip;
    @FXML
    private TextField donateAmount;
    @FXML
    private DatePicker donateDate;
    @FXML
    private TextField donateFirst;
    //**************************************************************************

    // Table Elements***********************************************************
    @FXML
    private TableView<Person> donaterTable;
    @FXML
    private TableColumn<?, ?> firstColumn;
    @FXML
    private TableColumn<?, ?> lastColumn;
    @FXML
    private TableColumn<?, ?> phoneColumn;
    @FXML
    private TableColumn<?, ?> addressColumn;
    @FXML
    private TableColumn<?, ?> stateColumn;
    @FXML
    private TableColumn<?, ?> zipColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> amountColumn;
    //**************************************************************************

    // Button elements**********************************************************
    @FXML
    private Button openButton;
    @FXML
    private Button findButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;
    //**************************************************************************

    // Open Button**************************************************************
    @FXML
    void handleButtonOpen(ActionEvent event) {
        // Donators List
        myList.setLocation(stage);
        myList.loadList();

        // Missionary List
        myList.loadMissionary();
        myMission = myList.getMission();
        setMissionary();
        updateDisplay();
    }
    //**************************************************************************

    // Update Button************************************************************
    @FXML
    void handleButtonUpdate(ActionEvent event) {
        Person i = new Person(donateFirst.getText(), donateLast.getText(),
                donatePhone.getText(), donateAddress.getText(), donateState.getText(),
                donateZip.getText(), donateDate.getValue().format(formatter),
                Double.parseDouble(donateAmount.getText()));
        myList.addPerson(i);
        updateDisplay();
        clearFields();
        donateAddress.requestFocus();
        updateDisplay();
    }
    //**************************************************************************

    // Clear Button*************************************************************
    @FXML
    void handleButtonClear(ActionEvent event) {
        clearFields();
        updateDisplay();
    }
    //**************************************************************************

    // Delete Button************************************************************
    @FXML
    void handleButtonDelete(ActionEvent event) {
        Person i = new Person(donateFirst.getText(), donateLast.getText(),
                donatePhone.getText(), donateAddress.getText(), donateState.getText(),
                donateZip.getText(), donateDate.getValue().format(formatter),
                Double.parseDouble(donateAmount.getText()));
        myList.removeDonation(i);
        updateDisplay();
        clearFields();
    }
    //**************************************************************************

    // Table Click**************************************************************
    @FXML
    void handleTableClick(MouseEvent event) {
        Person i = donaterTable.getSelectionModel().getSelectedItem();
        if (i != null) {
            donateAddress.setText(i.getAddress());
            donateFirst.setText(String.valueOf(i.getFirst()));
            donateLast.setText(String.valueOf(i.getLast()));
            donatePhone.setText(String.valueOf(i.getTelephone()));
            donateState.setText(String.valueOf(i.getState()));
            donateZip.setText(String.valueOf(i.getZip()));
            donatePhone.setText(String.valueOf(i.getTelephone()));
            donateAmount.setText(String.valueOf(i.getAmount()));
            donateDate.setValue(LocalDate.parse(i.getDate(), formatter));
        }
    }
    //**************************************************************************

    // Find button**************************************************************
    @FXML
    void handleButtonFind(ActionEvent event) {
        Person i = new Person(donateFirst.getText(), donateLast.getText());
        List<Person> l = myList.findFirstLast(i);
        donaterTable.getItems().setAll(l);
    }
    //**************************************************************************

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Donator Table********************************************************
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("First"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("Last"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("State"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("Zip"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        //**********************************************************************

        updateDisplay();
    }

    private void updateDisplay() {
        donaterTable.getItems().setAll(myList.getItems());  
        double Total = 0;
        for (Person item : donaterTable.getItems()) {
            Total += item.getAmount();
        }
        missionTotal.setText(String.valueOf(Total));
    }

    private void clearFields() {
        donateFirst.setText("");
        donateLast.setText("");
        donatePhone.setText("");
        donateAddress.setText("");
        donateState.setText("");
        donateZip.setText("");
        donateDate.setValue(LocalDate.now());
        donateAmount.setText("");
    }

    private void setMissionary() {
        missionFirst.setText(myMission.getFirst());
        missionLast.setText(myMission.getLast());
        missionAddress.setText(myMission.getAddress());
        missionPhone.setText(myMission.getTelephone());
        missionZip.setText(myMission.getZip());
        missionState.setText(myMission.getState());
        missionGoal.setText(String.valueOf(myMission.getGoal()));
    }
}
