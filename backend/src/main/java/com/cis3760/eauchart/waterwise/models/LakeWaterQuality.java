package com.cis3760.eauchart.waterwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="inland_lake_drinking_water_quality")
public class LakeWaterQuality {
    // Attributes of inland_lake_drinking_water_quality table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="last_updated")
    public LocalDateTime lastUpdated;
    @Column(name="lake_name")
    private String lakeName;
    @Column(name="station_code")
    private String stationCode;
    @Column(name="station_details")
    private String stationDetails;
    @Column(name="sample_date")
    private String sampleDate;
    @Column(name="gran_alkalinity")
    private BigDecimal granAlkalinity;
    @Column(name="calcium")
    private BigDecimal calcium;
    @Column(name="chloride")
    private BigDecimal chloride;
    @Column(name="specific_conductance")
    private BigDecimal specificConductance;
    @Column(name="dic")
    private BigDecimal dic;
    @Column(name="doc")
    private BigDecimal doc;
    @Column(name="iron")
    private BigDecimal iron;
    @Column(name="potassium")
    private BigDecimal potassium;
    @Column(name="magnesium")
    private BigDecimal magnesium;
    @Column(name="sodium")
    private BigDecimal sodium;
    @Column(name="ammonium_ammonia")
    private BigDecimal ammoniumAmmonia;
    @Column(name="nitrate_nitrite")
    private BigDecimal nitrateNitrite;
    @Column(name="tkn")
    private BigDecimal tkn;
    @Column(name="tn")
    private BigDecimal tn;
    @Column(name="ph")
    private BigDecimal ph;
    @Column(name="total_phosphorus")
    private BigDecimal totalPhosphorus;
    @Column(name="silicate")
    private BigDecimal silicate;
    @Column(name="sulfate")
    private BigDecimal sulfate;

    // Constructors
    public LakeWaterQuality() {
    }

    public LakeWaterQuality(Long id, LocalDateTime lastUpdated, String lakeName, String stationCode, String stationDetails, String sampleDate, BigDecimal granAlkalinity, BigDecimal calcium, BigDecimal chloride, BigDecimal specificConductance, BigDecimal dic, BigDecimal doc, BigDecimal iron, BigDecimal potassium, BigDecimal magnesium, BigDecimal sodium, BigDecimal ammoniumAmmonia, BigDecimal nitrateNitrite, BigDecimal tkn, BigDecimal tn, BigDecimal ph, BigDecimal totalPhosphorus, BigDecimal silicate, BigDecimal sulfate) {
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.lakeName = lakeName;
        this.stationCode = stationCode;
        this.stationDetails = stationDetails;
        this.sampleDate = sampleDate;
        this.granAlkalinity = granAlkalinity;
        this.calcium = calcium;
        this.chloride = chloride;
        this.specificConductance = specificConductance;
        this.dic = dic;
        this.doc = doc;
        this.iron = iron;
        this.potassium = potassium;
        this.magnesium = magnesium;
        this.sodium = sodium;
        this.ammoniumAmmonia = ammoniumAmmonia;
        this.nitrateNitrite = nitrateNitrite;
        this.tkn = tkn;
        this.tn = tn;
        this.ph = ph;
        this.totalPhosphorus = totalPhosphorus;
        this.silicate = silicate;
        this.sulfate = sulfate;
    }

    // Getters + Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLast_updated() {
        return lastUpdated;
    }

    public void setLast_updated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLakeName() {
        return lakeName;
    }

    public void setLakeName(String lake_name) {
        this.lakeName = lake_name;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationDetails() {
        return stationDetails;
    }

    public void setStationDetails(String stationDetails) {
        this.stationDetails = stationDetails;
    }

    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public BigDecimal getGranAlkalinity() {
        return granAlkalinity;
    }

    public void setGranAlkalinity(BigDecimal granAlkalinity) {
        this.granAlkalinity = granAlkalinity;
    }

    public BigDecimal getCalcium() {
        return calcium;
    }

    public void setCalcium(BigDecimal calcium) {
        this.calcium = calcium;
    }

    public BigDecimal getChloride() {
        return chloride;
    }

    public void setChloride(BigDecimal chloride) {
        this.chloride = chloride;
    }

    public BigDecimal getSpecificConductance() {
        return specificConductance;
    }

    public void setSpecificConductance(BigDecimal specificConductance) {
        this.specificConductance = specificConductance;
    }

    public BigDecimal getDic() {
        return dic;
    }

    public void setDic(BigDecimal dic) {
        this.dic = dic;
    }

    public BigDecimal getDoc() {
        return doc;
    }

    public void setDoc(BigDecimal doc) {
        this.doc = doc;
    }

    public BigDecimal getIron() {
        return iron;
    }

    public void setIron(BigDecimal iron) {
        this.iron = iron;
    }

    public BigDecimal getPotassium() {
        return potassium;
    }

    public void setPotassium(BigDecimal potassium) {
        this.potassium = potassium;
    }

    public BigDecimal getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(BigDecimal magnesium) {
        this.magnesium = magnesium;
    }

    public BigDecimal getSodium() {
        return sodium;
    }

    public void setSodium(BigDecimal sodium) {
        this.sodium = sodium;
    }

    public BigDecimal getAmmoniumAmmonia() {
        return ammoniumAmmonia;
    }

    public void setAmmoniumAmmonia(BigDecimal ammoniumAmmonia) {
        this.ammoniumAmmonia = ammoniumAmmonia;
    }

    public BigDecimal getNitrateNitrite() {
        return nitrateNitrite;
    }

    public void setNitrateNitrite(BigDecimal nitrateNitrite) {
        this.nitrateNitrite = nitrateNitrite;
    }

    public BigDecimal getTkn() {
        return tkn;
    }

    public void setTkn(BigDecimal tkn) {
        this.tkn = tkn;
    }

    public BigDecimal getTn() {
        return tn;
    }

    public void setTn(BigDecimal tn) {
        this.tn = tn;
    }

    public BigDecimal getPh() {
        return ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public BigDecimal getTotalPhosphorus() {
        return totalPhosphorus;
    }

    public void setTotalPhosphorus(BigDecimal totalPhosphorus) {
        this.totalPhosphorus = totalPhosphorus;
    }

    public BigDecimal getSilicate() {
        return silicate;
    }

    public void setSilicate(BigDecimal silicate) {
        this.silicate = silicate;
    }

    public BigDecimal getSulfate() {
        return sulfate;
    }

    public void setSulfate(BigDecimal sulfate) {
        this.sulfate = sulfate;
    }
}
