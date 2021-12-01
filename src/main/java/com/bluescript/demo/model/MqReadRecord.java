package com.bluescript.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@RedisHash("MqControl")
@Entity
@Table(name = "MqControl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MqReadRecord {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "MQRECORD")
    private String mqRecord;

}
