package cn.iclass.wechat.models;

import cn.iclass.iclassboot.models.BaseModel;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
public class MiniContractModel extends BaseModel {

    public String A;
    public String B;
    public boolean signedA;
    public boolean signedB;
    public boolean finishedA;
    public boolean finishedB;
    public byte[] title;
    public byte[] content;
    public byte[] sound;
    public byte[] picture;

    public long dbindex;
}
