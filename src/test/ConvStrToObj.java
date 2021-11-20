package com.bluescript.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.InheritInverseConfiguration;
import com.bluescript.demo.model.CaCustomerRequest;

@Mapper
public interface ConvStrToObj {
    @Mapping(target = "caFirstName", expression = "java(dfhcommarea.getCaRequestSpecific().substring(0,10))")
    @Mapping(target = "caLastName", expression = "java(dfhcommarea.getCaRequestSpecific().substring(10,30))")
    @Mapping(target = "caDob", expression = "java(dfhcommarea.getCaRequestSpecific().substring(30,40))")
    @Mapping(target = "caHouseName", expression = "java(dfhcommarea.getCaRequestSpecific().substring(40,60))")
    @Mapping(target = "caHouseNum", expression = "java(dfhcommarea.getCaRequestSpecific().substring(60,64))")
    @Mapping(target = "caPostcode", expression = "java(dfhcommarea.getCaRequestSpecific().substring(64,72))")
    @Mapping(target = "caNumPolicies", expression = "java(dfhcommarea.getCaRequestSpecific().substring(72,75))")
    @Mapping(target = "caPhoneMobile", expression = "java(dfhcommarea.getCaRequestSpecific().substring(75,95))")
    @Mapping(target = "caPhoneHome", expression = "java(dfhcommarea.getCaRequestSpecific().substring(95,115))")
    @Mapping(target = "caEmailAddress", expression = "java(dfhcommarea.getCaRequestSpecific().substring(115,215))")
    @Mapping(target = "caPolicyData", expression = "java(dfhcommarea.getCaRequestSpecific().substring(215,32482))")
    CaCustomerRequest caRequestSpecificTocaCustomerRequest_1(String caRequestSpecific,
            @MappingTarget CaCustomerRequest caCustomerRequest);

}