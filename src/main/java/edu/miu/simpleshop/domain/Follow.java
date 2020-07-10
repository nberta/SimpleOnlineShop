package edu.miu.simpleshop.domain;

public class Follow {
    private Long id;
    private Buyer buyer;
    private Seller seller;

    public Follow(){}

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
