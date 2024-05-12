package com.shop.store.model;

import static com.shop.store.utilities.AccountSessionManager.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.shop.store.utilities.AppUtilities;

public class Bill implements Parcelable {
    public static final String BILL_UNPAID = "Unpaid";
    public static final String BILL_PAID = "Paid";
    private Integer id;
    private Integer userId;
    private Integer cartId;
    private String phone;
    private String address;
    private String date;
    private String status;
    private Float discount;
    private Float price;
    private Cart cart;

    public Bill(Integer id, Integer userId, Integer cartId, String phone, String address, String date, Float discount, Float price, String status) {
        this.setId(id);
        this.setUserId(userId);
        this.setCartId(cartId);
        this.setPhone(phone);
        this.setAddress(address);
        this.setDate(date);
        this.setDiscount(discount);
        this.setPrice(price);
        this.setStatus(status);
    }

    public Bill(String date,Integer userId, Integer cartId, String phone, String address, Float discount, Float price) {
        this(-1, userId, cartId, phone, address, date, discount, price, BILL_UNPAID);
    }

    public Bill(Integer userId, Integer cartId, String address, Float discount, Float price) {
        this(-1, userId, cartId, user.getPhone(), address, AppUtilities.getDateTimeNow(), discount, price, BILL_UNPAID);
    }

    public Bill(String date, Integer userId, Integer cartId, String address, Float discount, Float price, String status) {
        this(-1, userId, cartId, user.getPhone(), address, date, discount, price, status);
    }

    public Bill(Integer userId, Integer cartId, Float discount, Float price) {
        this(-1, userId, cartId, user.getPhone(), user.getAddress(), AppUtilities.getDateTimeNow(), discount, price, BILL_UNPAID);
    }

    protected Bill(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        if (in.readByte() == 0) {
            cartId = null;
        } else {
            cartId = in.readInt();
        }
        phone = in.readString();
        address = in.readString();
        date = in.readString();
        status = in.readString();
        if (in.readByte() == 0) {
            discount = null;
        } else {
            discount = in.readFloat();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readFloat();
        }
    }

    public static final Creator<Bill> CREATOR = new Creator<Bill>() {
        @Override
        public Bill createFromParcel(Parcel in) {
            return new Bill(in);
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", userId=" + userId +
                ", cartId=" + cartId +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                ", cart=" + cart +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        if (cartId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(cartId);
        }
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(date);
        dest.writeString(status);
        if (discount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(discount);
        }
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(price);
        }
    }
}
