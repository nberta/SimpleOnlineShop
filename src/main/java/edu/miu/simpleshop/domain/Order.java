package edu.miu.simpleshop.domain;


import java.time.LocalDate;
import java.util.List;

public class Order  {
   private Long id;
   private Category category;
   private BillingInfo billingInfo;
   private LocalDate date;
   private Address shippingAddress;
   private List<OrderLine> orderLines;

   public Order(){}

   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public BillingInfo getBillingInfo() {
      return billingInfo;
   }

   public void setBillingInfo(BillingInfo billingInfo) {
      this.billingInfo = billingInfo;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public Address getShippingAddress() {
      return shippingAddress;
   }

   public void setShippingAddress(Address shippingAddress) {
      this.shippingAddress = shippingAddress;
   }

   public List<OrderLine> getOrderLines() {
      return orderLines;
   }

   public void setOrderLines(List<OrderLine> orderLines) {
      this.orderLines = orderLines;
   }
}