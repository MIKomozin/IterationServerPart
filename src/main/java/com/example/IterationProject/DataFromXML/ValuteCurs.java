package com.example.IterationProject.DataFromXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuteCurs {

    @XmlElement(name = "Valute")
    private List<DataValute> valute;
    @XmlElement(name = "Date")
    private Date date;

    public List<DataValute> getValute() {
        return valute;
    }

    public void setValute(List<DataValute> valute) {
        this.valute = valute;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
