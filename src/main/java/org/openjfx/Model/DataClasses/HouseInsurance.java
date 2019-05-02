package org.openjfx.Model.DataClasses;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HouseInsurance extends Insurances implements Serializable {

    private transient SimpleStringProperty
            adress,
            constructionYear,
            residentialType,
            materials,
            standard,
            SqMeters,
            buildingInsuranceAmount,
            contentInsuranceAmount;


    public HouseInsurance() {
        InitSuperHouseInsurace();
    }

    private void InitSuperHouseInsurace(){
        this.adress = new SimpleStringProperty();
        this.constructionYear = new SimpleStringProperty();
        this.residentialType = new SimpleStringProperty();
        this.materials = new SimpleStringProperty();
        this.standard = new SimpleStringProperty();
        this.SqMeters = new SimpleStringProperty();
        this.buildingInsuranceAmount = new SimpleStringProperty();
        this.contentInsuranceAmount = new SimpleStringProperty();
    }

    private void writeObject(ObjectOutputStream s) throws IOException{

    }
    private void readObject(ObjectInputStream s) throws IOException{
        InitSuperHouseInsurace();
    }



    public String getAdress() {
        return adress.get();
    }

    public SimpleStringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public String getConstructionYear() {
        return constructionYear.get();
    }

    public SimpleStringProperty constructionYearProperty() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear.set(constructionYear);
    }

    public String getResidentialType() {
        return residentialType.get();
    }

    public SimpleStringProperty residentialTypeProperty() {
        return residentialType;
    }

    public void setResidentialType (String residentialType) {
        this.residentialType.set(residentialType);
    }

    public String getMaterials() {
        return materials.get();
    }

    public SimpleStringProperty materialsProperty() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials.set(materials);
    }

    public String getStandard() {
        return standard.get();
    }

    public SimpleStringProperty standardProperty() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard.set(standard);
    }

    public String getSqMeters() {
        return SqMeters.get();
    }

    public SimpleStringProperty sqMetersProperty() {
        return SqMeters;
    }

    public void setSqMeters(String sqMeters) {
        this.SqMeters.set(sqMeters);
    }

    public String getBuildingInsuranceAmount() {
        return buildingInsuranceAmount.get();
    }

    public SimpleStringProperty buildingInsuranceAmountProperty() {
        return buildingInsuranceAmount;
    }

    public void setBuildingInsuranceAmount(String buildingInsuranceAmount) {
        this.buildingInsuranceAmount.set(buildingInsuranceAmount);
    }

    public String getContentInsuranceAmount() {
        return contentInsuranceAmount.get();
    }

    public SimpleStringProperty housingInsuranceAmountProperty() {
        return contentInsuranceAmount;
    }

    public void setContentInsuranceAmount(String housingInsuranceAmount) {
        this.contentInsuranceAmount.set(housingInsuranceAmount);
    }
}