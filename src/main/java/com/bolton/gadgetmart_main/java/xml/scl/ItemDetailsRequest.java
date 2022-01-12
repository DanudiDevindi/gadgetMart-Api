package com.bolton.gadgetmart_main.java.xml.scl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "price",
    "brand",
    "category",
    "shop"
})
@XmlRootElement(name = "ItemDetailsRequest")
public class ItemDetailsRequest {

}
