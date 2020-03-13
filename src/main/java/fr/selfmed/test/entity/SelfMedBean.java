package fr.selfmed.test.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfMedBean {
    long id;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate date;
    String amount;
    String label;
    String status;

    @Override
    public String toString() {
        return "transaction:" + "<br/>"+
                "id:    " + id + "<br/>"+
                "date:  " + date + "<br/>"+
                "amount:    " + amount + "<br/>"+
                "label:    " + label + "<br/>"+
                "status:    " + status + "<br/>";
    }
}
