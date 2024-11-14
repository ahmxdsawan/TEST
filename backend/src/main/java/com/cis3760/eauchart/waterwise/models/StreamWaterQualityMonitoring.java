package com.cis3760.eauchart.waterwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="stream_water_quality_monitoring")
public class StreamWaterQualityMonitoring {
    // Attributes of stream_water_quality table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="last_updated")
    private LocalDateTime lastUpdated;
    @Column(name="station")
    private String station;
    @Column(name="stream_name")
    private String streamName;

    // Constructors
    public StreamWaterQualityMonitoring() {
    }

    public StreamWaterQualityMonitoring(Long id, LocalDateTime lastUpdated, String station, String streamName) {
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.station = station;
        this.streamName = streamName;
    }

    // Getters + Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }
}
