package com.example.IterationProject.DataFromXML;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuteCurs {

    @XmlElement(name = "Valute")
    private List<DataValute> valute;
    @XmlAttribute(name = "Date")
    private String date;

    public List<DataValute> getValute() {
        return valute;
    }

    public void setValute(List<DataValute> valute) {
        this.valute = valute;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
