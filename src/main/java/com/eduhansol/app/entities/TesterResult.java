package com.eduhansol.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="testerResults")
public class TesterResult{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @ManyToOne(cascade = {CascadeType.ALL})
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn
    private Tester tester;

    @ManyToOne
    @JoinColumn
    private NormPersonality normPersonality;

    private double PersonalityScore;
    private double PersonalityTScore;
    private double dev;
    private int mark1Count;
    private int extremeMark1Count;
    private double consistencyScore;
    private double consistencyTScore;

    private double subFactorN1S;
    private double subFactorN2S;
    private double subFactorN3S;
    private double subFactorN4S;
    private double subFactorN5S;
    private double subFactorN6S;
    private double subFactorN7S;
    private double subFactorN8S;
    private double subFactorN9S;
    private double subFactorN10S;
    private double subFactorN11S;
    private double subFactorN12S;
    private double subFactorN13S;
    private double subFactorN14S;
    private double subFactorN15S;
    private double subFactorN16S;
    private double subFactorN17S;
    private double subFactorN18S;
    private double subFactorN19S;
    private double subFactorN20S;
    private double subFactorN21S;
    private double subFactorN22S;
    private double subFactorN23S;
    private double subFactorN24S;
    private double subFactorN25S;
    private double subFactorN26S;
    private double subFactorN27S;
    private double subFactorN28S;

    private double subFactorI1S;
    private double subFactorI2S;
    private double subFactorI3S;
    private double subFactorI4S;
    private double subFactorI5S;
    private double subFactorI6S;
    private double subFactorI7S;
    private double subFactorI8S;
    private double subFactorI9S;
    private double subFactorI10S;
    private double subFactorI11S;
    private double subFactorI12S;
    private double subFactorI13S;
    private double subFactorI14S;
    private double subFactorI15S;
    private double subFactorI16S;
    private double subFactorI17S;
    private double subFactorI18S;
    private double subFactorI19S;
    private double subFactorI20S;
    private double subFactorI21S;
    private double subFactorI22S;
    private double subFactorI23S;
    private double subFactorI24S;
    private double subFactorI25S;
    private double subFactorI26S;
    private double subFactorI27S;
    private double subFactorI28S;

    private double subFactorN1T;
    private double subFactorN2T;
    private double subFactorN3T;
    private double subFactorN4T;
    private double subFactorN5T;
    private double subFactorN6T;
    private double subFactorN7T;
    private double subFactorN8T;
    private double subFactorN9T;
    private double subFactorN10T;
    private double subFactorN11T;
    private double subFactorN12T;
    private double subFactorN13T;
    private double subFactorN14T;
    private double subFactorN15T;
    private double subFactorN16T;
    private double subFactorN17T;
    private double subFactorN18T;
    private double subFactorN19T;
    private double subFactorN20T;
    private double subFactorN21T;
    private double subFactorN22T;
    private double subFactorN23T;
    private double subFactorN24T;
    private double subFactorN25T;
    private double subFactorN26T;
    private double subFactorN27T;
    private double subFactorN28T;

    private double subFactorI1T;
    private double subFactorI2T;
    private double subFactorI3T;
    private double subFactorI4T;
    private double subFactorI5T;
    private double subFactorI6T;
    private double subFactorI7T;
    private double subFactorI8T;
    private double subFactorI9T;
    private double subFactorI10T;
    private double subFactorI11T;
    private double subFactorI12T;
    private double subFactorI13T;
    private double subFactorI14T;
    private double subFactorI15T;
    private double subFactorI16T;
    private double subFactorI17T;
    private double subFactorI18T;
    private double subFactorI19T;
    private double subFactorI20T;
    private double subFactorI21T;
    private double subFactorI22T;
    private double subFactorI23T;
    private double subFactorI24T;
    private double subFactorI25T;
    private double subFactorI26T;
    private double subFactorI27T;
    private double subFactorI28T;

    private double subFactor1S;
    private double subFactor2S;
    private double subFactor3S;
    private double subFactor4S;
    private double subFactor5S;
    private double subFactor6S;
    private double subFactor7S;
    private double subFactor8S;
    private double subFactor9S;
    private double subFactor10S;
    private double subFactor11S;
    private double subFactor12S;
    private double subFactor13S;
    private double subFactor14S;
    private double subFactor15S;
    private double subFactor16S;
    private double subFactor17S;
    private double subFactor18S;
    private double subFactor19S;
    private double subFactor20S;
    private double subFactor21S;
    private double subFactor22S;
    private double subFactor23S;
    private double subFactor24S;
    private double subFactor25S;
    private double subFactor26S;
    private double subFactor27S;
    private double subFactor28S;

    private double subFactor1T;
    private double subFactor2T;
    private double subFactor3T;
    private double subFactor4T;
    private double subFactor5T;
    private double subFactor6T;
    private double subFactor7T;
    private double subFactor8T;
    private double subFactor9T;
    private double subFactor10T;
    private double subFactor11T;
    private double subFactor12T;
    private double subFactor13T;
    private double subFactor14T;
    private double subFactor15T;
    private double subFactor16T;
    private double subFactor17T;
    private double subFactor18T;
    private double subFactor19T;
    private double subFactor20T;
    private double subFactor21T;
    private double subFactor22T;
    private double subFactor23T;
    private double subFactor24T;
    private double subFactor25T;
    private double subFactor26T;
    private double subFactor27T;
    private double subFactor28T;

    private double factor1S;
    private double factor2S;
    private double factor3S;
    private double factor4S;
    private double factor5S;
    private double factor6S;

    private double factor1T;
    private double factor2T;
    private double factor3T;
    private double factor4T;
    private double factor5T;
    private double factor6T;

    public Tester getTester(){
        return this.tester;
    }

    public void setTester(Tester tester){
        this.tester = tester;
    }

    public NormPersonality getNormPersonality(){
        return this.normPersonality;
    }

    public void setNormPersonality(NormPersonality normPersonality){
        this.normPersonality = normPersonality;
    }
    
    public double getPersonalityScore() {
        return this.PersonalityScore;
    }

    public void setPersonalityScore(double PersonalityScore) {
        this.PersonalityScore = PersonalityScore;
    }

    public double getPersonalityTScore() {
        return this.PersonalityTScore;
    }

    public void setPersonalityTScore(double PersonalityTScore) {
        this.PersonalityTScore = PersonalityTScore;
    }

    public double getDev() {
        return this.dev;
    }

    public void setDev(double dev) {
        this.dev = dev;
    }

    public int getMark1Count() {
        return this.mark1Count;
    }

    public void setMark1Count(int mark1Count) {
        this.mark1Count = mark1Count;
    }

    public int getExtremeMark1Count() {
        return this.extremeMark1Count;
    }

    public void setExtremeMark1Count(int extremeMark1Count) {
        this.extremeMark1Count = extremeMark1Count;
    }

    public double getConsistencyScore() {
        return this.consistencyScore;
    }

    public void setConsistencyScore(double consistencyScore) {
        this.consistencyScore = consistencyScore;
    }

    public double getConsistencyTScore() {
        return this.consistencyTScore;
    }

    public void setConsistencyTScore(double consistencyTScore) {
        this.consistencyTScore = consistencyTScore;
    }

    public double getSubFactorN1S() {
        return this.subFactorN1S;
    }

    public void setSubFactorN1S(double subFactorN1S) {
        this.subFactorN1S = subFactorN1S;
    }

    public double getSubFactorN2S() {
        return this.subFactorN2S;
    }

    public void setSubFactorN2S(double subFactorN2S) {
        this.subFactorN2S = subFactorN2S;
    }

    public double getSubFactorN3S() {
        return this.subFactorN3S;
    }

    public void setSubFactorN3S(double subFactorN3S) {
        this.subFactorN3S = subFactorN3S;
    }

    public double getSubFactorN4S() {
        return this.subFactorN4S;
    }

    public void setSubFactorN4S(double subFactorN4S) {
        this.subFactorN4S = subFactorN4S;
    }

    public double getSubFactorN5S() {
        return this.subFactorN5S;
    }

    public void setSubFactorN5S(double subFactorN5S) {
        this.subFactorN5S = subFactorN5S;
    }

    public double getSubFactorN6S() {
        return this.subFactorN6S;
    }

    public void setSubFactorN6S(double subFactorN6S) {
        this.subFactorN6S = subFactorN6S;
    }

    public double getSubFactorN7S() {
        return this.subFactorN7S;
    }

    public void setSubFactorN7S(double subFactorN7S) {
        this.subFactorN7S = subFactorN7S;
    }

    public double getSubFactorN8S() {
        return this.subFactorN8S;
    }

    public void setSubFactorN8S(double subFactorN8S) {
        this.subFactorN8S = subFactorN8S;
    }

    public double getSubFactorN9S() {
        return this.subFactorN9S;
    }

    public void setSubFactorN9S(double subFactorN9S) {
        this.subFactorN9S = subFactorN9S;
    }

    public double getSubFactorN10S() {
        return this.subFactorN10S;
    }

    public void setSubFactorN10S(double subFactorN10S) {
        this.subFactorN10S = subFactorN10S;
    }

    public double getSubFactorN11S() {
        return this.subFactorN11S;
    }

    public void setSubFactorN11S(double subFactorN11S) {
        this.subFactorN11S = subFactorN11S;
    }

    public double getSubFactorN12S() {
        return this.subFactorN12S;
    }

    public void setSubFactorN12S(double subFactorN12S) {
        this.subFactorN12S = subFactorN12S;
    }

    public double getSubFactorN13S() {
        return this.subFactorN13S;
    }

    public void setSubFactorN13S(double subFactorN13S) {
        this.subFactorN13S = subFactorN13S;
    }

    public double getSubFactorN14S() {
        return this.subFactorN14S;
    }

    public void setSubFactorN14S(double subFactorN14S) {
        this.subFactorN14S = subFactorN14S;
    }

    public double getSubFactorN15S() {
        return this.subFactorN15S;
    }

    public void setSubFactorN15S(double subFactorN15S) {
        this.subFactorN15S = subFactorN15S;
    }

    public double getSubFactorN16S() {
        return this.subFactorN16S;
    }

    public void setSubFactorN16S(double subFactorN16S) {
        this.subFactorN16S = subFactorN16S;
    }

    public double getSubFactorN17S() {
        return this.subFactorN17S;
    }

    public void setSubFactorN17S(double subFactorN17S) {
        this.subFactorN17S = subFactorN17S;
    }

    public double getSubFactorN18S() {
        return this.subFactorN18S;
    }

    public void setSubFactorN18S(double subFactorN18S) {
        this.subFactorN18S = subFactorN18S;
    }

    public double getSubFactorN19S() {
        return this.subFactorN19S;
    }

    public void setSubFactorN19S(double subFactorN19S) {
        this.subFactorN19S = subFactorN19S;
    }

    public double getSubFactorN20S() {
        return this.subFactorN20S;
    }

    public void setSubFactorN20S(double subFactorN20S) {
        this.subFactorN20S = subFactorN20S;
    }

    public double getSubFactorN21S() {
        return this.subFactorN21S;
    }

    public void setSubFactorN21S(double subFactorN21S) {
        this.subFactorN21S = subFactorN21S;
    }

    public double getSubFactorN22S() {
        return this.subFactorN22S;
    }

    public void setSubFactorN22S(double subFactorN22S) {
        this.subFactorN22S = subFactorN22S;
    }

    public double getSubFactorN23S() {
        return this.subFactorN23S;
    }

    public void setSubFactorN23S(double subFactorN23S) {
        this.subFactorN23S = subFactorN23S;
    }

    public double getSubFactorN24S() {
        return this.subFactorN24S;
    }

    public void setSubFactorN24S(double subFactorN24S) {
        this.subFactorN24S = subFactorN24S;
    }

    public double getSubFactorN25S() {
        return this.subFactorN25S;
    }

    public void setSubFactorN25S(double subFactorN25S) {
        this.subFactorN25S = subFactorN25S;
    }

    public double getSubFactorN26S() {
        return this.subFactorN26S;
    }

    public void setSubFactorN26S(double subFactorN26S) {
        this.subFactorN26S = subFactorN26S;
    }

    public double getSubFactorN27S() {
        return this.subFactorN27S;
    }

    public void setSubFactorN27S(double subFactorN27S) {
        this.subFactorN27S = subFactorN27S;
    }

    public double getSubFactorN28S() {
        return this.subFactorN28S;
    }

    public void setSubFactorN28S(double subFactorN28S) {
        this.subFactorN28S = subFactorN28S;
    }

    public double getSubFactorI1S() {
        return this.subFactorI1S;
    }

    public void setSubFactorI1S(double subFactorI1S) {
        this.subFactorI1S = subFactorI1S;
    }

    public double getSubFactorI2S() {
        return this.subFactorI2S;
    }

    public void setSubFactorI2S(double subFactorI2S) {
        this.subFactorI2S = subFactorI2S;
    }

    public double getSubFactorI3S() {
        return this.subFactorI3S;
    }

    public void setSubFactorI3S(double subFactorI3S) {
        this.subFactorI3S = subFactorI3S;
    }

    public double getSubFactorI4S() {
        return this.subFactorI4S;
    }

    public void setSubFactorI4S(double subFactorI4S) {
        this.subFactorI4S = subFactorI4S;
    }

    public double getSubFactorI5S() {
        return this.subFactorI5S;
    }

    public void setSubFactorI5S(double subFactorI5S) {
        this.subFactorI5S = subFactorI5S;
    }

    public double getSubFactorI6S() {
        return this.subFactorI6S;
    }

    public void setSubFactorI6S(double subFactorI6S) {
        this.subFactorI6S = subFactorI6S;
    }

    public double getSubFactorI7S() {
        return this.subFactorI7S;
    }

    public void setSubFactorI7S(double subFactorI7S) {
        this.subFactorI7S = subFactorI7S;
    }

    public double getSubFactorI8S() {
        return this.subFactorI8S;
    }

    public void setSubFactorI8S(double subFactorI8S) {
        this.subFactorI8S = subFactorI8S;
    }

    public double getSubFactorI9S() {
        return this.subFactorI9S;
    }

    public void setSubFactorI9S(double subFactorI9S) {
        this.subFactorI9S = subFactorI9S;
    }

    public double getSubFactorI10S() {
        return this.subFactorI10S;
    }

    public void setSubFactorI10S(double subFactorI10S) {
        this.subFactorI10S = subFactorI10S;
    }

    public double getSubFactorI11S() {
        return this.subFactorI11S;
    }

    public void setSubFactorI11S(double subFactorI11S) {
        this.subFactorI11S = subFactorI11S;
    }

    public double getSubFactorI12S() {
        return this.subFactorI12S;
    }

    public void setSubFactorI12S(double subFactorI12S) {
        this.subFactorI12S = subFactorI12S;
    }

    public double getSubFactorI13S() {
        return this.subFactorI13S;
    }

    public void setSubFactorI13S(double subFactorI13S) {
        this.subFactorI13S = subFactorI13S;
    }

    public double getSubFactorI14S() {
        return this.subFactorI14S;
    }

    public void setSubFactorI14S(double subFactorI14S) {
        this.subFactorI14S = subFactorI14S;
    }

    public double getSubFactorI15S() {
        return this.subFactorI15S;
    }

    public void setSubFactorI15S(double subFactorI15S) {
        this.subFactorI15S = subFactorI15S;
    }

    public double getSubFactorI16S() {
        return this.subFactorI16S;
    }

    public void setSubFactorI16S(double subFactorI16S) {
        this.subFactorI16S = subFactorI16S;
    }

    public double getSubFactorI17S() {
        return this.subFactorI17S;
    }

    public void setSubFactorI17S(double subFactorI17S) {
        this.subFactorI17S = subFactorI17S;
    }

    public double getSubFactorI18S() {
        return this.subFactorI18S;
    }

    public void setSubFactorI18S(double subFactorI18S) {
        this.subFactorI18S = subFactorI18S;
    }

    public double getSubFactorI19S() {
        return this.subFactorI19S;
    }

    public void setSubFactorI19S(double subFactorI19S) {
        this.subFactorI19S = subFactorI19S;
    }

    public double getSubFactorI20S() {
        return this.subFactorI20S;
    }

    public void setSubFactorI20S(double subFactorI20S) {
        this.subFactorI20S = subFactorI20S;
    }

    public double getSubFactorI21S() {
        return this.subFactorI21S;
    }

    public void setSubFactorI21S(double subFactorI21S) {
        this.subFactorI21S = subFactorI21S;
    }

    public double getSubFactorI22S() {
        return this.subFactorI22S;
    }

    public void setSubFactorI22S(double subFactorI22S) {
        this.subFactorI22S = subFactorI22S;
    }

    public double getSubFactorI23S() {
        return this.subFactorI23S;
    }

    public void setSubFactorI23S(double subFactorI23S) {
        this.subFactorI23S = subFactorI23S;
    }

    public double getSubFactorI24S() {
        return this.subFactorI24S;
    }

    public void setSubFactorI24S(double subFactorI24S) {
        this.subFactorI24S = subFactorI24S;
    }

    public double getSubFactorI25S() {
        return this.subFactorI25S;
    }

    public void setSubFactorI25S(double subFactorI25S) {
        this.subFactorI25S = subFactorI25S;
    }

    public double getSubFactorI26S() {
        return this.subFactorI26S;
    }

    public void setSubFactorI26S(double subFactorI26S) {
        this.subFactorI26S = subFactorI26S;
    }

    public double getSubFactorI27S() {
        return this.subFactorI27S;
    }

    public void setSubFactorI27S(double subFactorI27S) {
        this.subFactorI27S = subFactorI27S;
    }

    public double getSubFactorI28S() {
        return this.subFactorI28S;
    }

    public void setSubFactorI28S(double subFactorI28S) {
        this.subFactorI28S = subFactorI28S;
    }

    public double getSubFactorN1T() {
        return this.subFactorN1T;
    }

    public void setSubFactorN1T(double subFactorN1T) {
        this.subFactorN1T = subFactorN1T;
    }

    public double getSubFactorN2T() {
        return this.subFactorN2T;
    }

    public void setSubFactorN2T(double subFactorN2T) {
        this.subFactorN2T = subFactorN2T;
    }

    public double getSubFactorN3T() {
        return this.subFactorN3T;
    }

    public void setSubFactorN3T(double subFactorN3T) {
        this.subFactorN3T = subFactorN3T;
    }

    public double getSubFactorN4T() {
        return this.subFactorN4T;
    }

    public void setSubFactorN4T(double subFactorN4T) {
        this.subFactorN4T = subFactorN4T;
    }

    public double getSubFactorN5T() {
        return this.subFactorN5T;
    }

    public void setSubFactorN5T(double subFactorN5T) {
        this.subFactorN5T = subFactorN5T;
    }

    public double getSubFactorN6T() {
        return this.subFactorN6T;
    }

    public void setSubFactorN6T(double subFactorN6T) {
        this.subFactorN6T = subFactorN6T;
    }

    public double getSubFactorN7T() {
        return this.subFactorN7T;
    }

    public void setSubFactorN7T(double subFactorN7T) {
        this.subFactorN7T = subFactorN7T;
    }

    public double getSubFactorN8T() {
        return this.subFactorN8T;
    }

    public void setSubFactorN8T(double subFactorN8T) {
        this.subFactorN8T = subFactorN8T;
    }

    public double getSubFactorN9T() {
        return this.subFactorN9T;
    }

    public void setSubFactorN9T(double subFactorN9T) {
        this.subFactorN9T = subFactorN9T;
    }

    public double getSubFactorN10T() {
        return this.subFactorN10T;
    }

    public void setSubFactorN10T(double subFactorN10T) {
        this.subFactorN10T = subFactorN10T;
    }

    public double getSubFactorN11T() {
        return this.subFactorN11T;
    }

    public void setSubFactorN11T(double subFactorN11T) {
        this.subFactorN11T = subFactorN11T;
    }

    public double getSubFactorN12T() {
        return this.subFactorN12T;
    }

    public void setSubFactorN12T(double subFactorN12T) {
        this.subFactorN12T = subFactorN12T;
    }

    public double getSubFactorN13T() {
        return this.subFactorN13T;
    }

    public void setSubFactorN13T(double subFactorN13T) {
        this.subFactorN13T = subFactorN13T;
    }

    public double getSubFactorN14T() {
        return this.subFactorN14T;
    }

    public void setSubFactorN14T(double subFactorN14T) {
        this.subFactorN14T = subFactorN14T;
    }

    public double getSubFactorN15T() {
        return this.subFactorN15T;
    }

    public void setSubFactorN15T(double subFactorN15T) {
        this.subFactorN15T = subFactorN15T;
    }

    public double getSubFactorN16T() {
        return this.subFactorN16T;
    }

    public void setSubFactorN16T(double subFactorN16T) {
        this.subFactorN16T = subFactorN16T;
    }

    public double getSubFactorN17T() {
        return this.subFactorN17T;
    }

    public void setSubFactorN17T(double subFactorN17T) {
        this.subFactorN17T = subFactorN17T;
    }

    public double getSubFactorN18T() {
        return this.subFactorN18T;
    }

    public void setSubFactorN18T(double subFactorN18T) {
        this.subFactorN18T = subFactorN18T;
    }

    public double getSubFactorN19T() {
        return this.subFactorN19T;
    }

    public void setSubFactorN19T(double subFactorN19T) {
        this.subFactorN19T = subFactorN19T;
    }

    public double getSubFactorN20T() {
        return this.subFactorN20T;
    }

    public void setSubFactorN20T(double subFactorN20T) {
        this.subFactorN20T = subFactorN20T;
    }

    public double getSubFactorN21T() {
        return this.subFactorN21T;
    }

    public void setSubFactorN21T(double subFactorN21T) {
        this.subFactorN21T = subFactorN21T;
    }

    public double getSubFactorN22T() {
        return this.subFactorN22T;
    }

    public void setSubFactorN22T(double subFactorN22T) {
        this.subFactorN22T = subFactorN22T;
    }

    public double getSubFactorN23T() {
        return this.subFactorN23T;
    }

    public void setSubFactorN23T(double subFactorN23T) {
        this.subFactorN23T = subFactorN23T;
    }

    public double getSubFactorN24T() {
        return this.subFactorN24T;
    }

    public void setSubFactorN24T(double subFactorN24T) {
        this.subFactorN24T = subFactorN24T;
    }

    public double getSubFactorN25T() {
        return this.subFactorN25T;
    }

    public void setSubFactorN25T(double subFactorN25T) {
        this.subFactorN25T = subFactorN25T;
    }

    public double getSubFactorN26T() {
        return this.subFactorN26T;
    }

    public void setSubFactorN26T(double subFactorN26T) {
        this.subFactorN26T = subFactorN26T;
    }

    public double getSubFactorN27T() {
        return this.subFactorN27T;
    }

    public void setSubFactorN27T(double subFactorN27T) {
        this.subFactorN27T = subFactorN27T;
    }

    public double getSubFactorN28T() {
        return this.subFactorN28T;
    }

    public void setSubFactorN28T(double subFactorN28T) {
        this.subFactorN28T = subFactorN28T;
    }

    public double getSubFactorI1T() {
        return this.subFactorI1T;
    }

    public void setSubFactorI1T(double subFactorI1T) {
        this.subFactorI1T = subFactorI1T;
    }

    public double getSubFactorI2T() {
        return this.subFactorI2T;
    }

    public void setSubFactorI2T(double subFactorI2T) {
        this.subFactorI2T = subFactorI2T;
    }

    public double getSubFactorI3T() {
        return this.subFactorI3T;
    }

    public void setSubFactorI3T(double subFactorI3T) {
        this.subFactorI3T = subFactorI3T;
    }

    public double getSubFactorI4T() {
        return this.subFactorI4T;
    }

    public void setSubFactorI4T(double subFactorI4T) {
        this.subFactorI4T = subFactorI4T;
    }

    public double getSubFactorI5T() {
        return this.subFactorI5T;
    }

    public void setSubFactorI5T(double subFactorI5T) {
        this.subFactorI5T = subFactorI5T;
    }

    public double getSubFactorI6T() {
        return this.subFactorI6T;
    }

    public void setSubFactorI6T(double subFactorI6T) {
        this.subFactorI6T = subFactorI6T;
    }

    public double getSubFactorI7T() {
        return this.subFactorI7T;
    }

    public void setSubFactorI7T(double subFactorI7T) {
        this.subFactorI7T = subFactorI7T;
    }

    public double getSubFactorI8T() {
        return this.subFactorI8T;
    }

    public void setSubFactorI8T(double subFactorI8T) {
        this.subFactorI8T = subFactorI8T;
    }

    public double getSubFactorI9T() {
        return this.subFactorI9T;
    }

    public void setSubFactorI9T(double subFactorI9T) {
        this.subFactorI9T = subFactorI9T;
    }

    public double getSubFactorI10T() {
        return this.subFactorI10T;
    }

    public void setSubFactorI10T(double subFactorI10T) {
        this.subFactorI10T = subFactorI10T;
    }

    public double getSubFactorI11T() {
        return this.subFactorI11T;
    }

    public void setSubFactorI11T(double subFactorI11T) {
        this.subFactorI11T = subFactorI11T;
    }

    public double getSubFactorI12T() {
        return this.subFactorI12T;
    }

    public void setSubFactorI12T(double subFactorI12T) {
        this.subFactorI12T = subFactorI12T;
    }

    public double getSubFactorI13T() {
        return this.subFactorI13T;
    }

    public void setSubFactorI13T(double subFactorI13T) {
        this.subFactorI13T = subFactorI13T;
    }

    public double getSubFactorI14T() {
        return this.subFactorI14T;
    }

    public void setSubFactorI14T(double subFactorI14T) {
        this.subFactorI14T = subFactorI14T;
    }

    public double getSubFactorI15T() {
        return this.subFactorI15T;
    }

    public void setSubFactorI15T(double subFactorI15T) {
        this.subFactorI15T = subFactorI15T;
    }

    public double getSubFactorI16T() {
        return this.subFactorI16T;
    }

    public void setSubFactorI16T(double subFactorI16T) {
        this.subFactorI16T = subFactorI16T;
    }

    public double getSubFactorI17T() {
        return this.subFactorI17T;
    }

    public void setSubFactorI17T(double subFactorI17T) {
        this.subFactorI17T = subFactorI17T;
    }

    public double getSubFactorI18T() {
        return this.subFactorI18T;
    }

    public void setSubFactorI18T(double subFactorI18T) {
        this.subFactorI18T = subFactorI18T;
    }

    public double getSubFactorI19T() {
        return this.subFactorI19T;
    }

    public void setSubFactorI19T(double subFactorI19T) {
        this.subFactorI19T = subFactorI19T;
    }

    public double getSubFactorI20T() {
        return this.subFactorI20T;
    }

    public void setSubFactorI20T(double subFactorI20T) {
        this.subFactorI20T = subFactorI20T;
    }

    public double getSubFactorI21T() {
        return this.subFactorI21T;
    }

    public void setSubFactorI21T(double subFactorI21T) {
        this.subFactorI21T = subFactorI21T;
    }

    public double getSubFactorI22T() {
        return this.subFactorI22T;
    }

    public void setSubFactorI22T(double subFactorI22T) {
        this.subFactorI22T = subFactorI22T;
    }

    public double getSubFactorI23T() {
        return this.subFactorI23T;
    }

    public void setSubFactorI23T(double subFactorI23T) {
        this.subFactorI23T = subFactorI23T;
    }

    public double getSubFactorI24T() {
        return this.subFactorI24T;
    }

    public void setSubFactorI24T(double subFactorI24T) {
        this.subFactorI24T = subFactorI24T;
    }

    public double getSubFactorI25T() {
        return this.subFactorI25T;
    }

    public void setSubFactorI25T(double subFactorI25T) {
        this.subFactorI25T = subFactorI25T;
    }

    public double getSubFactorI26T() {
        return this.subFactorI26T;
    }

    public void setSubFactorI26T(double subFactorI26T) {
        this.subFactorI26T = subFactorI26T;
    }

    public double getSubFactorI27T() {
        return this.subFactorI27T;
    }

    public void setSubFactorI27T(double subFactorI27T) {
        this.subFactorI27T = subFactorI27T;
    }

    public double getSubFactorI28T() {
        return this.subFactorI28T;
    }

    public void setSubFactorI28T(double subFactorI28T) {
        this.subFactorI28T = subFactorI28T;
    }

    public double getSubFactor1S() {
        return this.subFactor1S;
    }

    public void setSubFactor1S(double subFactor1S) {
        this.subFactor1S = subFactor1S;
    }

    public double getSubFactor2S() {
        return this.subFactor2S;
    }

    public void setSubFactor2S(double subFactor2S) {
        this.subFactor2S = subFactor2S;
    }

    public double getSubFactor3S() {
        return this.subFactor3S;
    }

    public void setSubFactor3S(double subFactor3S) {
        this.subFactor3S = subFactor3S;
    }

    public double getSubFactor4S() {
        return this.subFactor4S;
    }

    public void setSubFactor4S(double subFactor4S) {
        this.subFactor4S = subFactor4S;
    }

    public double getSubFactor5S() {
        return this.subFactor5S;
    }

    public void setSubFactor5S(double subFactor5S) {
        this.subFactor5S = subFactor5S;
    }

    public double getSubFactor6S() {
        return this.subFactor6S;
    }

    public void setSubFactor6S(double subFactor6S) {
        this.subFactor6S = subFactor6S;
    }

    public double getSubFactor7S() {
        return this.subFactor7S;
    }

    public void setSubFactor7S(double subFactor7S) {
        this.subFactor7S = subFactor7S;
    }

    public double getSubFactor8S() {
        return this.subFactor8S;
    }

    public void setSubFactor8S(double subFactor8S) {
        this.subFactor8S = subFactor8S;
    }

    public double getSubFactor9S() {
        return this.subFactor9S;
    }

    public void setSubFactor9S(double subFactor9S) {
        this.subFactor9S = subFactor9S;
    }

    public double getSubFactor10S() {
        return this.subFactor10S;
    }

    public void setSubFactor10S(double subFactor10S) {
        this.subFactor10S = subFactor10S;
    }

    public double getSubFactor11S() {
        return this.subFactor11S;
    }

    public void setSubFactor11S(double subFactor11S) {
        this.subFactor11S = subFactor11S;
    }

    public double getSubFactor12S() {
        return this.subFactor12S;
    }

    public void setSubFactor12S(double subFactor12S) {
        this.subFactor12S = subFactor12S;
    }

    public double getSubFactor13S() {
        return this.subFactor13S;
    }

    public void setSubFactor13S(double subFactor13S) {
        this.subFactor13S = subFactor13S;
    }

    public double getSubFactor14S() {
        return this.subFactor14S;
    }

    public void setSubFactor14S(double subFactor14S) {
        this.subFactor14S = subFactor14S;
    }

    public double getSubFactor15S() {
        return this.subFactor15S;
    }

    public void setSubFactor15S(double subFactor15S) {
        this.subFactor15S = subFactor15S;
    }

    public double getSubFactor16S() {
        return this.subFactor16S;
    }

    public void setSubFactor16S(double subFactor16S) {
        this.subFactor16S = subFactor16S;
    }

    public double getSubFactor17S() {
        return this.subFactor17S;
    }

    public void setSubFactor17S(double subFactor17S) {
        this.subFactor17S = subFactor17S;
    }

    public double getSubFactor18S() {
        return this.subFactor18S;
    }

    public void setSubFactor18S(double subFactor18S) {
        this.subFactor18S = subFactor18S;
    }

    public double getSubFactor19S() {
        return this.subFactor19S;
    }

    public void setSubFactor19S(double subFactor19S) {
        this.subFactor19S = subFactor19S;
    }

    public double getSubFactor20S() {
        return this.subFactor20S;
    }

    public void setSubFactor20S(double subFactor20S) {
        this.subFactor20S = subFactor20S;
    }

    public double getSubFactor21S() {
        return this.subFactor21S;
    }

    public void setSubFactor21S(double subFactor21S) {
        this.subFactor21S = subFactor21S;
    }

    public double getSubFactor22S() {
        return this.subFactor22S;
    }

    public void setSubFactor22S(double subFactor22S) {
        this.subFactor22S = subFactor22S;
    }

    public double getSubFactor23S() {
        return this.subFactor23S;
    }

    public void setSubFactor23S(double subFactor23S) {
        this.subFactor23S = subFactor23S;
    }

    public double getSubFactor24S() {
        return this.subFactor24S;
    }

    public void setSubFactor24S(double subFactor24S) {
        this.subFactor24S = subFactor24S;
    }

    public double getSubFactor25S() {
        return this.subFactor25S;
    }

    public void setSubFactor25S(double subFactor25S) {
        this.subFactor25S = subFactor25S;
    }

    public double getSubFactor26S() {
        return this.subFactor26S;
    }

    public void setSubFactor26S(double subFactor26S) {
        this.subFactor26S = subFactor26S;
    }

    public double getSubFactor27S() {
        return this.subFactor27S;
    }

    public void setSubFactor27S(double subFactor27S) {
        this.subFactor27S = subFactor27S;
    }

    public double getSubFactor28S() {
        return this.subFactor28S;
    }

    public void setSubFactor28S(double subFactor28S) {
        this.subFactor28S = subFactor28S;
    }

    public double getSubFactor1T() {
        return this.subFactor1T;
    }

    public void setSubFactor1T(double subFactor1T) {
        this.subFactor1T = subFactor1T;
    }

    public double getSubFactor2T() {
        return this.subFactor2T;
    }

    public void setSubFactor2T(double subFactor2T) {
        this.subFactor2T = subFactor2T;
    }

    public double getSubFactor3T() {
        return this.subFactor3T;
    }

    public void setSubFactor3T(double subFactor3T) {
        this.subFactor3T = subFactor3T;
    }

    public double getSubFactor4T() {
        return this.subFactor4T;
    }

    public void setSubFactor4T(double subFactor4T) {
        this.subFactor4T = subFactor4T;
    }

    public double getSubFactor5T() {
        return this.subFactor5T;
    }

    public void setSubFactor5T(double subFactor5T) {
        this.subFactor5T = subFactor5T;
    }

    public double getSubFactor6T() {
        return this.subFactor6T;
    }

    public void setSubFactor6T(double subFactor6T) {
        this.subFactor6T = subFactor6T;
    }

    public double getSubFactor7T() {
        return this.subFactor7T;
    }

    public void setSubFactor7T(double subFactor7T) {
        this.subFactor7T = subFactor7T;
    }

    public double getSubFactor8T() {
        return this.subFactor8T;
    }

    public void setSubFactor8T(double subFactor8T) {
        this.subFactor8T = subFactor8T;
    }

    public double getSubFactor9T() {
        return this.subFactor9T;
    }

    public void setSubFactor9T(double subFactor9T) {
        this.subFactor9T = subFactor9T;
    }

    public double getSubFactor10T() {
        return this.subFactor10T;
    }

    public void setSubFactor10T(double subFactor10T) {
        this.subFactor10T = subFactor10T;
    }

    public double getSubFactor11T() {
        return this.subFactor11T;
    }

    public void setSubFactor11T(double subFactor11T) {
        this.subFactor11T = subFactor11T;
    }

    public double getSubFactor12T() {
        return this.subFactor12T;
    }

    public void setSubFactor12T(double subFactor12T) {
        this.subFactor12T = subFactor12T;
    }

    public double getSubFactor13T() {
        return this.subFactor13T;
    }

    public void setSubFactor13T(double subFactor13T) {
        this.subFactor13T = subFactor13T;
    }

    public double getSubFactor14T() {
        return this.subFactor14T;
    }

    public void setSubFactor14T(double subFactor14T) {
        this.subFactor14T = subFactor14T;
    }

    public double getSubFactor15T() {
        return this.subFactor15T;
    }

    public void setSubFactor15T(double subFactor15T) {
        this.subFactor15T = subFactor15T;
    }

    public double getSubFactor16T() {
        return this.subFactor16T;
    }

    public void setSubFactor16T(double subFactor16T) {
        this.subFactor16T = subFactor16T;
    }

    public double getSubFactor17T() {
        return this.subFactor17T;
    }

    public void setSubFactor17T(double subFactor17T) {
        this.subFactor17T = subFactor17T;
    }

    public double getSubFactor18T() {
        return this.subFactor18T;
    }

    public void setSubFactor18T(double subFactor18T) {
        this.subFactor18T = subFactor18T;
    }

    public double getSubFactor19T() {
        return this.subFactor19T;
    }

    public void setSubFactor19T(double subFactor19T) {
        this.subFactor19T = subFactor19T;
    }

    public double getSubFactor20T() {
        return this.subFactor20T;
    }

    public void setSubFactor20T(double subFactor20T) {
        this.subFactor20T = subFactor20T;
    }

    public double getSubFactor21T() {
        return this.subFactor21T;
    }

    public void setSubFactor21T(double subFactor21T) {
        this.subFactor21T = subFactor21T;
    }

    public double getSubFactor22T() {
        return this.subFactor22T;
    }

    public void setSubFactor22T(double subFactor22T) {
        this.subFactor22T = subFactor22T;
    }

    public double getSubFactor23T() {
        return this.subFactor23T;
    }

    public void setSubFactor23T(double subFactor23T) {
        this.subFactor23T = subFactor23T;
    }

    public double getSubFactor24T() {
        return this.subFactor24T;
    }

    public void setSubFactor24T(double subFactor24T) {
        this.subFactor24T = subFactor24T;
    }

    public double getSubFactor25T() {
        return this.subFactor25T;
    }

    public void setSubFactor25T(double subFactor25T) {
        this.subFactor25T = subFactor25T;
    }

    public double getSubFactor26T() {
        return this.subFactor26T;
    }

    public void setSubFactor26T(double subFactor26T) {
        this.subFactor26T = subFactor26T;
    }

    public double getSubFactor27T() {
        return this.subFactor27T;
    }

    public void setSubFactor27T(double subFactor27T) {
        this.subFactor27T = subFactor27T;
    }

    public double getSubFactor28T() {
        return this.subFactor28T;
    }

    public void setSubFactor28T(double subFactor28T) {
        this.subFactor28T = subFactor28T;
    }

    public double getFactor1S() {
        return this.factor1S;
    }

    public void setFactor1S(double factor1S) {
        this.factor1S = factor1S;
    }

    public double getFactor2S() {
        return this.factor2S;
    }

    public void setFactor2S(double factor2S) {
        this.factor2S = factor2S;
    }

    public double getFactor3S() {
        return this.factor3S;
    }

    public void setFactor3S(double factor3S) {
        this.factor3S = factor3S;
    }

    public double getFactor4S() {
        return this.factor4S;
    }

    public void setFactor4S(double factor4S) {
        this.factor4S = factor4S;
    }

    public double getFactor5S() {
        return this.factor5S;
    }

    public void setFactor5S(double factor5S) {
        this.factor5S = factor5S;
    }

    public double getFactor6S() {
        return this.factor6S;
    }

    public void setFactor6S(double factor6S) {
        this.factor6S = factor6S;
    }

    public double getFactor1T() {
        return this.factor1T;
    }

    public void setFactor1T(double factor1T) {
        this.factor1T = factor1T;
    }

    public double getFactor2T() {
        return this.factor2T;
    }

    public void setFactor2T(double factor2T) {
        this.factor2T = factor2T;
    }

    public double getFactor3T() {
        return this.factor3T;
    }

    public void setFactor3T(double factor3T) {
        this.factor3T = factor3T;
    }

    public double getFactor4T() {
        return this.factor4T;
    }

    public void setFactor4T(double factor4T) {
        this.factor4T = factor4T;
    }

    public double getFactor5T() {
        return this.factor5T;
    }

    public void setFactor5T(double factor5T) {
        this.factor5T = factor5T;
    }

    public double getFactor6T() {
        return this.factor6T;
    }

    public void setFactor6T(double factor6T) {
        this.factor6T = factor6T;
    }

}