package org.openjfx;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FXMLController {
    @FXML
    private Tab clientTab, boatTab, houseHoldTab, homeTab, travelingTab, skadeTab;

    @FXML
    private TableView KunderTable, SkadeMldTable, BoatTable;

    @FXML
    private TableColumn<mdClients, String> clientDateCreated, fornavn, etternavn, adress, forsikringsNR, skademeldinger,
                                            insurances, unpaid;

    @FXML
    ProgressIndicator progressBar;

    @FXML
    private TextField testFelt;

    @FXML
    private AnchorPane mainFrame;


    @FXML
    private TableColumn<mdSkademelding, String> smDato, skadeNr, skadeType, skadeBeskrivelse, vitneKontaktInfo,
                                                takseringsBeløp, erstatningsBeløp;

    @FXML
    private TableColumn<BoatInsurance, String> boatDate, boatOwner, boatInsurancePrice, boatInsuranceAmount,
                                                boatInsuranceConditions, typeModel, regNr, length, yearModel, motorType,
                                                motorStrength;
    @FXML
    private TableView<HouseholdInsurance> Householdtable;

    @FXML
    private TableColumn<HouseholdInsurance, String> houseAdress, houseInsurancePrice, houseDate, houseInsuranceAmount,
                                                    houseInsuranceConditions, houseConstructionYear, houseResidentalType,
                                                    houseMaterials, houseStandard, houseSqMeters,
                                                    houseBuildingInsuranceAmount, HouseHousingInsuranceAmount;

    FileHandler reader;
    ObservableList data;
    @FXML
    private void loadClients (ActionEvent event) throws IOException {
        loadFile(KunderTable);
    }

    @FXML
    private void loadBoat(ActionEvent event) throws IOException {
        loadFile(BoatTable);


        testFelt.setVisible(true);
        testFelt.setDisable(false);
    }

    @FXML
    private void loadSkademld(ActionEvent event) throws IOException {
        // TODO få dette til å funke
        loadFile(SkadeMldTable);
    }

    private void loadFile(TableView tableView) throws IOException {
        File file = new FileChooser().showOpenDialog(mainFrame.getScene().getWindow());
        //CheckFileType checkFileType = new CheckFileType(file);
        readDataThread readDataThread = new readDataThread();
        readDataThread.setFile(file);
        if (file.getName().contains(".csv")){//TODO Bruk annen måte, fordi filen kan hete kim.csv.exe, da skal det ikke funke
            new Thread (() -> {
                reader = new mCSVReader();
                try {
                    reader.addFromFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.setData(reader.getData());
                tableView.setItems(data);
                tableView.setEditable(true);
            }).start();
        }else if (file.getName().contains(".jobj")){
            //TODO Lage jobj reader/writer
            reader = new mJOBJReader();
        }else {
            //TODO Throw invalid FileType exception
        }

    }
    private void assignKunderColumns() {
        clientDateCreated.setCellValueFactory(
                new PropertyValueFactory<>("dateCreated")
        );
        fornavn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );

        etternavn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );

        adress.setCellValueFactory(
                new PropertyValueFactory<>("adress")
        );

        forsikringsNR.setCellValueFactory(
                new PropertyValueFactory<>("forsikringsNR")
        );

        skademeldinger.setCellValueFactory(
                new PropertyValueFactory<>("skademeldinger")
        );

        insurances.setCellValueFactory(
                new PropertyValueFactory<>("forsikringer")
        );

        unpaid.setCellValueFactory(
                new PropertyValueFactory<>("ubetalt")
        );
    }

    private void assignBoatInsuranceColumns() {
        boatOwner.setCellValueFactory(
                new PropertyValueFactory<>("Owner")
        );

        boatInsurancePrice.setCellValueFactory(
                new PropertyValueFactory<>("insurancePrice")
        );

        boatDate.setCellValueFactory(
                new PropertyValueFactory<>("dateCreated")
        );

        boatInsuranceAmount.setCellValueFactory(
                new PropertyValueFactory<>("insuranceAmount")
        );

        boatInsuranceConditions.setCellValueFactory(
                new PropertyValueFactory<>("insuranceConditions")
        );

        regNr.setCellValueFactory(
                new PropertyValueFactory<>("RegNr")
        );

        typeModel.setCellValueFactory(
                new PropertyValueFactory<>("TypeModel")
        );

        length.setCellValueFactory(
                new PropertyValueFactory<>("length")
        );

        yearModel.setCellValueFactory(
                new PropertyValueFactory<>("year")
        );

        motorType.setCellValueFactory(
                new PropertyValueFactory<>("motorType")
        );

        motorStrength.setCellValueFactory(
                new PropertyValueFactory<>("motorStrength")
        );
    }

    private void assignHouseholdColumns() {
        houseAdress.setCellValueFactory(
                new PropertyValueFactory<>("adress")
        );

        houseInsurancePrice.setCellValueFactory(
                new PropertyValueFactory<>("insurancePrice")
        );

        houseDate.setCellValueFactory(
                new PropertyValueFactory<>("dateCreated")
        );

        houseInsuranceAmount.setCellValueFactory(
                new PropertyValueFactory<>("insuranceAmount")
        );

        houseInsuranceConditions.setCellValueFactory(
                new PropertyValueFactory<>("insuranceConditions")
        );

        houseResidentalType.setCellValueFactory(
                new PropertyValueFactory<>("residentalType")
        );


    }

    private void assignSkademldColumns() {
        smDato.setCellValueFactory(
                new PropertyValueFactory<>("SMDato")
        );

        skadeNr.setCellValueFactory(
                new PropertyValueFactory<>("SkadeNR")
        );

        skadeType.setCellValueFactory(
                new PropertyValueFactory<>("SkadeType")
        );

        skadeBeskrivelse.setCellValueFactory(
                new PropertyValueFactory<>("SkadeBeskrivelse")
        );

        vitneKontaktInfo.setCellValueFactory(
                new PropertyValueFactory<>("VitneKontaktInfo")
        );

        takseringsBeløp.setCellValueFactory(
                new PropertyValueFactory<>("TakseringsBeloep")
        );

        erstatningsBeløp.setCellValueFactory(
                new PropertyValueFactory<>("ErstatningsBeloep")
        );

    }

    private void assignAllColumns(){
        assignKunderColumns();
        assignBoatInsuranceColumns();
        //assignHouseholdColumns();
        assignSkademldColumns();
    }

    @FXML
    private void saveFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(".csv", "*.csv");
        FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter(".jobj","*.jobj");
        fileChooser.getExtensionFilters().addAll(extensionFilter, extensionFilter1);
        File destination = fileChooser.showSaveDialog(mainFrame.getScene().getWindow());
        if (destination.getName().endsWith(".csv")){
            mCSVWriter mCSVWriter = new mCSVWriter();
            mCSVWriter.saveFile(destination, KunderTable.getItems());
            System.out.println("CSV");
        }else if (destination.getName().endsWith(".jobj")){
            System.out.println("JOBJ");
        }
    }

    @FXML
    private void deleteButton(ActionEvent event){
        TableView tableView = null;

        if(clientTab.isSelected()) {
            tableView = KunderTable;
        }
        else if(boatTab.isSelected()) {
            tableView = BoatTable;
        }
        else if(skadeTab.isSelected()) {
            tableView = SkadeMldTable;
        }

        System.out.println(tableView.getId());
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());

    }

    @FXML
    private void testButton(ActionEvent event){
        //TODO Popup med verdier, fyll dem, trykk på knappen, verdiene sendes inn i metoden her, valideres, også lages hele objektet.
        mdClients clients = new mdClients();
        clients.setDateCreated("1");
        clients.setFirstName("2");
        clients.setLastName("3");
        clients.setAdress("4");
        clients.setForsikringsNR("5");
        clients.setForsikringer("6");
        clients.setSkademeldinger("7");
        clients.setUbetalt("8");
        data.add(clients);
    }

    @FXML
    private void onEdit(TableColumn.CellEditEvent editEvent){
        mdClients mdClients = getKunderTable().getSelectionModel().getSelectedItem();
        //mdClients.setFirstName(editEvent.getNewValue());
        String Column = editEvent.getTableColumn().getText();
        String Value = editEvent.getNewValue().toString();
        switch (Column){
            case "Opprettet":
                mdClients.setDateCreated(Value);
                break;
            case "Fornavn":
                mdClients.setFirstName(Value);
                break;
            case "Etternavn":
                mdClients.setLastName(Value);
                break;
        }
    }

    public void initialize() {
        // TODO
        assignAllColumns();
        setEditableColumns();
    }

    private void setEditableColumns(){
        clientDateCreated.setCellFactory(TextFieldTableCell.forTableColumn());
        fornavn.setCellFactory(TextFieldTableCell.forTableColumn());
        etternavn.setCellFactory(TextFieldTableCell.forTableColumn());
        forsikringsNR.setCellFactory(TextFieldTableCell.forTableColumn());
        adress.setCellFactory(TextFieldTableCell.forTableColumn());
        skademeldinger.setCellFactory(TextFieldTableCell.forTableColumn());
        insurances.setCellFactory(TextFieldTableCell.forTableColumn());
        unpaid.setCellFactory(TextFieldTableCell.forTableColumn());


        smDato.setCellFactory(TextFieldTableCell.forTableColumn());
        skadeNr.setCellFactory(TextFieldTableCell.forTableColumn());
        skadeType.setCellFactory(TextFieldTableCell.forTableColumn());
        skadeBeskrivelse.setCellFactory(TextFieldTableCell.forTableColumn());
        vitneKontaktInfo.setCellFactory(TextFieldTableCell.forTableColumn());
        takseringsBeløp.setCellFactory(TextFieldTableCell.forTableColumn());
        erstatningsBeløp.setCellFactory(TextFieldTableCell.forTableColumn());

        //TODO Fyll ut denne lille satanen
    }


    //Setter and getter

    public TableView<mdClients> getKunderTable() {
        return KunderTable;
    }

    public void setKunderTable(TableView<mdClients> kunderTable) {
        KunderTable = kunderTable;
    }

    public TableColumn<mdClients, String> getFornavn() {
        return fornavn;
    }

    public void setFornavn(TableColumn<mdClients, String> fornavn) {
        this.fornavn = fornavn;
    }

    public TableColumn<mdClients, String> getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(TableColumn<mdClients, String> etternavn) {
        this.etternavn = etternavn;
    }

    public TextField getTestFelt() {
        return testFelt;
    }

    public void setTestFelt(TextField testFelt) {
        this.testFelt = testFelt;
    }

    public TableColumn<mdClients, String> getForsikringsNR() {
        return forsikringsNR;
    }

    public void setForsikringsNR(TableColumn<mdClients, String> forsikringsNR) {
        this.forsikringsNR = forsikringsNR;
    }

    public ObservableList getData() {
        return data;
    }

    public void setData(ObservableList data) {
        this.data = data;
    }
}
