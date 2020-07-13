package edu.miu.simpleshop.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_TABLE")
public class Order  {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @OneToOne
   private BillingInfo billingInfo;

   private LocalDate date;

   @OneToOne
   private Address shippingAddress;

   @NotEmpty
   @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
   private List<@NotNull OrderLine> orderLines = new ArrayList<>();

   public Order(){}


   public Long getId() { return this.id; }

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