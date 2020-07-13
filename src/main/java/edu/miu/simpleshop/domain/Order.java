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

   private Double totalCost;

   @OneToOne
   private Address shippingAddress;

   @NotEmpty
   @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
   private List<@NotNull OrderLine> orderLines = new ArrayList<>();

   public Order(){}

   public Order(List<OrderLine> orderLines, BillingInfo billingInfo, Address shippingAddress) {
      this.orderLines = orderLines;
      Double cost = 0D;
      for(OrderLine orderLine : orderLines) {
         orderLine.setOrder(this);
         cost += (orderLine.getProduct().getPrice() * orderLine.getQuantity());
      }
      this.totalCost = cost;
      this.billingInfo = billingInfo;
      this.shippingAddress = shippingAddress;
   }

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

   public Double getTotalCost() {
      return totalCost;
   }

   public void setTotalCost(Double totalCost) {
      this.totalCost = totalCost;
   }
}