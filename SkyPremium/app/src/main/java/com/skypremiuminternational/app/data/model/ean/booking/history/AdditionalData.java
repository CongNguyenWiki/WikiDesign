package com.skypremiuminternational.app.data.model.ean.booking.history;

import java.util.List;

public class AdditionalData {

  public final String affiliate_reference_id;
  public final List<Payment> payments;
  public final List<Room> rooms;

  public AdditionalData(
      String affiliate_reference_id, List<Payment> payments,
      List<Room> rooms) {
    this.affiliate_reference_id = affiliate_reference_id;
    this.payments = payments;
    this.rooms = rooms;
  }

  public static class Payment {
    public final String type;
    public final String card_brand;
    public final String number;
//    public final String security_code;
//    public final String expiration_month;
//    public final String expiration_year;
    public final BillingContact billing_contact;

    public Payment(String type,String card_brand, String number, BillingContact billing_contact) {
      this.type = type;
      this.card_brand = card_brand;
      this.number = number;
//      this.security_code = security_code;
//      this.expiration_month = expiration_month;
//      this.expiration_year = expiration_year;
      this.billing_contact = billing_contact;
    }
  }

  public static class BillingContact {
    public final String given_name;
    public final String family_name;
    public final Address address;

    public BillingContact(String given_name, String family_name, Address address) {
      this.given_name = given_name;
      this.family_name = family_name;
      this.address = address;
    }
  }

  public static class Address {
    public final String city;
    public final String country_code;
    public final String line_1;
    public final String line_2;

    public Address(String city, String country_code, String line_1, String line_2) {
      this.city = city;
      this.country_code = country_code;
      this.line_1 = line_1;
      this.line_2 = line_2;

    }
  }

  public static class Room {
    public final String family_name;
    public final String given_name;


    public Room(String family_name, String given_name) {


      this.family_name = family_name;
      this.given_name = given_name;
    }
  }
}
