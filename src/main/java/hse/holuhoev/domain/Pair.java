package hse.holuhoev.domain;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "city")
    private CityType cityType;

    @Column(name = "pair")
    private Integer pair;

    @Column(name = "begin")
    private LocalTime begin;

    @Column(name = "end")
    private LocalTime end;

    @Column(name = "week_day")
    private DayType dayType;

    public Pair(CityType cityType, Integer pair, LocalTime begin, LocalTime end) {
        this(cityType, pair, begin, end, DayType.WORKING_DAY);
    }

    public Pair(CityType cityType, Integer pair, LocalTime begin, LocalTime end, DayType dayType) {
        this.cityType = cityType;
        this.pair = pair;
        this.begin = begin;
        this.end = end;
        this.dayType = dayType;
    }

    public Pair() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CityType getCityType() {
        return cityType;
    }

    public void setCityType(CityType cityType) {
        this.cityType = cityType;
    }

    public Integer getPair() {
        return pair;
    }

    public void setPair(Integer pair) {
        this.pair = pair;
    }

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public DayType getDayType() {
        return dayType;
    }

    public void setDayType(DayType dayType) {
        this.dayType = dayType;
    }
}
