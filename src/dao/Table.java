/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author smart
 */
public class Table {

    private String id_alias;
    private String name_alias;
    private String deviceNumber_alias;
    private String damageDescription_alias;
    private String receiveDate_alias;
    private String phoneNumber_alias;
    private String whatsappNumber_alias;

    public String getId_alias() {
        return id_alias;
    }

    public void setId_alias(String id) {
        this.id_alias = id;
    }

    public String getName_alias() {
        return name_alias;
    }

    public void setName_alias(String name_alias) {
        this.name_alias = name_alias;
    }

    public String getDeviceNumber_alias() {
        return deviceNumber_alias;
    }

    public void setDeviceNumber_alias(String deviceNumber_alias) {
        this.deviceNumber_alias = deviceNumber_alias;
    }

    public String getDamageDescription_alias() {
        return damageDescription_alias;
    }

    public void setDamageDescription_alias(String damageDescription_alias) {
        this.damageDescription_alias = damageDescription_alias;
    }

    public String getReceiveDate_alias() {
        return receiveDate_alias;
    }

    public void setReceiveDate_alias(String receiveDate_alias) {
        this.receiveDate_alias = receiveDate_alias;
    }

    public String getPhoneNumber_alias() {
        return phoneNumber_alias;
    }

    public void setPhoneNumber_alias(String phoneNumber_alias) {
        this.phoneNumber_alias = phoneNumber_alias;
    }

    public String getWhatsappNumber_alias() {
        return whatsappNumber_alias;
    }

    public void setWhatsappNumber_alias(String whatsappNumber_alias) {
        this.whatsappNumber_alias = whatsappNumber_alias;
    }

}
