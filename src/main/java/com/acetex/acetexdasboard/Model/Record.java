package com.acetex.acetexdasboard.Model;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer Leads;
    private Integer Meetups;
    private Integer Contracts;
    private Date recordTimestamp;
}
