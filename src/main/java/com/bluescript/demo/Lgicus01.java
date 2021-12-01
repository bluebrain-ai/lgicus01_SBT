package com.bluescript.demo;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import com.bluescript.demo.repository.MqControlRepository;
import com.bluescript.demo.repository.TestControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import io.swagger.annotations.ApiResponses;
import com.bluescript.demo.model.WsHeader;
import com.bluescript.demo.model.ErrorMsg;
import com.bluescript.demo.model.MqReadRecord;
import com.bluescript.demo.model.EmVariable;
import com.bluescript.demo.model.Dfhcommarea;
import com.bluescript.demo.model.CaCustomerRequest;
import com.bluescript.demo.model.CaCustsecrRequest;
import com.bluescript.demo.model.CaPolicyRequest;
import com.bluescript.demo.model.CaPolicyCommon;
import com.bluescript.demo.model.CaEndowment;
import com.bluescript.demo.model.CaHouse;
import com.bluescript.demo.model.CaMotor;
import com.bluescript.demo.model.CaCommercial;
//import com.bluescript.demo.mapper.ConvStrToObj;
import com.bluescript.demo.model.CaClaim;

@Getter
@Setter
@RequiredArgsConstructor
@Log4j2
@Service

@RestController
@RequestMapping("/")
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server/Application is down. Please contact support team.") })

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Lgicus01 {

    @Autowired
    private WsHeader wsHeader;
    @Autowired
    private ErrorMsg errorMsg;
    @Autowired
    private EmVariable emVariable;
    @Autowired
    private Dfhcommarea dfhcommarea;
    @Autowired
    private CaCustomerRequest caCustomerRequest;
    @Autowired
    private CaCustsecrRequest caCustsecrRequest;
    @Autowired
    private CaPolicyRequest caPolicyRequest;
    @Autowired
    private CaPolicyCommon caPolicyCommon;
    @Autowired
    private CaEndowment caEndowment;
    @Autowired
    private CaHouse caHouse;
    @Autowired
    private CaMotor caMotor;
    @Autowired
    private CaCommercial caCommercial;
    @Autowired
    private CaClaim caClaim;

    private int mqControlItemKeyId = 5;

    @Autowired
    private MqControlRepository mqControl;

    private int testControlItemKeyId = 01;

    private String wsTime;
    private String wsDate;
    private String caData;
    private int wsCaHeadertrailerLen = 0;
    private int wsRequiredCaLen = 0;
    private int wsResp;
    private int mqHit;

    private MqReadRecord mqReadRecord;
    private int wsCustomerLen = 0;
    private int eibcalen = 0;
    private String TestReadRecord;

    private int TestControlItemKeyId;

    // @Value({})

    // @Value(value = "#{\"${api.lgicdb01}\".trim()})")
    @Value("${api.lgicdb01.uri}")
    private String lgicdb01_URI;
    @Value("${api.aaaaaaaa.uri}")
    private String aaaaaaaa_URI;
    @Value("${api.lgstsq.uri}")
    private String lgstsq_URI;
    private WebClient webClientBuilder;
    private String wsAbstime;
    @Value("${api.lgstsq.host}")
    private String LQSTSQ_HOST;
    @Value("${api.lgicdb01.host}")
    private String LGICDB01_HOST;

    // @CrossOrigin(origins = "http://localhost:4200")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/lgicus01")
    public ResponseEntity<Dfhcommarea> mainline(@RequestBody Dfhcommarea payload) {
        log.debug("Methodmainlinestarted..");
        /*
         * Manually removed handled in beans and client side if (eibcalen == 0) {
         * errorMsg.setEmVariable(" NO COMMAREA RECEIVED"); writeErrorMessage(); log.error("Error code :", "LGCA");
         * caCustomerRequest = convStrToObj.caRequestSpecificTocaCustomerRequest_1(caRequestSpecific,
         * caCustomerRequest); }
         */
        // copy request payload to dfhcommarea autowired
        BeanUtils.copyProperties(payload, dfhcommarea);
        System.out.println("dfhcommarea.getCaCustomerNum():" + dfhcommarea.getCaCustomerNum());
        dfhcommarea.setCaReturnCode(00);
        caCustomerRequest.setCaNumPolicies(00);
        wsRequiredCaLen = wsCustomerLen;
        wsRequiredCaLen = wsCaHeadertrailerLen + wsRequiredCaLen;
        /*
         * manuallly removed not requried if (eibcalen < wsRequiredCaLen) { dfhcommarea.setCaReturnCode(98); // return
         * 
         * }
         */
        // emVariable.setEmCusnum(String.valueOf(dfhcommarea.getCaCustomerNum()));
        getCustomerInfo();

        log.debug("Method mainline completed..");
        log.info("dfhcommarea.getCaCustomerNum():" + dfhcommarea.getCaCustomerRequest().getCaFirstName());
        return new ResponseEntity<>(dfhcommarea, HttpStatus.OK);

    }

    public void getCustomerInfo() {
        WebClient webClientBuilder = WebClient.create(LGICDB01_HOST);
        log.debug("MethodgetCustomerInfostarted..");
        mqHit = 0;

        // // Testing code for redis
        MqReadRecord m = new MqReadRecord();
        m.setId(03);
        m.setMqRecord("MQHIT= LGICUS01 Record from Redis");
        mqControl.save(m);

        // String mqReadRecord = mqControl.findById(01).orElseThrow().getMqRecord();
        testControlItemKeyId = 01;
        log.info("mqReadRecord:" + mqReadRecord);
        if (mqReadRecord != null) {

            do {
                try {
                    // mqReadRecord = mqControl.findById(Integer.valueOf(testControlItemKeyId)).get();
                    mqReadRecord = mqControl.findById(03).get();
                    log.info("MqReadRecord:" + mqReadRecord.getMqRecord());
                } catch (Exception e) {
                    log.error(e);
                    wsResp = 1;

                }

                if (mqReadRecord != null && mqReadRecord.getMqRecord().substring(0, 6) == "MQHIT=") {
                    mqHit = 1;
                }
                testControlItemKeyId = testControlItemKeyId + 1;
            } while (wsResp > 0);
        }
        if (mqHit == 0) {
            log.warn("lgicdb01Resp: Triggered");
            System.out.println("dfhcommarea.getCaCustomerNum():" + dfhcommarea.getCaCustomerNum());
            try {
                Mono<Dfhcommarea> lgicdb01Resp = webClientBuilder.post().uri(lgicdb01_URI)
                        .body(Mono.just(dfhcommarea), Dfhcommarea.class).retrieve().bodyToMono(Dfhcommarea.class);// .timeout(Duration.ofMillis(10_000));

                dfhcommarea = lgicdb01Resp.block();
                System.out.println("dfhcommarea.getCaCustomerNum():" + dfhcommarea.getCaCustomerNum());
            } catch (Exception e) {
                log.error(e);
            }

        } else {
            try {
                Mono<Dfhcommarea> aaaaaaaaResp = webClientBuilder.post().uri(aaaaaaaa_URI)
                        .body(Mono.just(dfhcommarea), Dfhcommarea.class).retrieve().bodyToMono(Dfhcommarea.class);// .timeout(Duration.ofMillis(10_000));
                dfhcommarea = aaaaaaaaResp.block();
            } catch (Exception e) {
                log.error(e);
            }

        }

        log.debug("Method getCustomerInfo completed..");
    }

    public void writeErrorMessage() {
        log.debug("MethodwriteErrorMessagestarted..");
        WebClient webClientBuilder = WebClient.create(LQSTSQ_HOST);
        wsAbstime = LocalTime.now().toString();
        wsAbstime = LocalTime.now().toString();
        wsDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMDDYYYY"));
        wsTime = LocalTime.now().toString();
        errorMsg.setEmDate(wsDate.substring(0, 8));
        errorMsg.setEmTime(wsTime.substring(0, 6));
        try {
            Mono<ErrorMsg> lgstsqResp = webClientBuilder.post().uri(lgstsq_URI)
                    .body(Mono.just(errorMsg), ErrorMsg.class).retrieve().bodyToMono(ErrorMsg.class);// .timeout(Duration.ofMillis(10_000));
            errorMsg = lgstsqResp.block();
        } catch (Exception e) {
            log.error(e);
        }
        if (eibcalen > 0) {
            if (eibcalen < 91) {
                caData = (dfhcommarea.getCaRequestId().substring(0, 3));
                try {
                    Mono<ErrorMsg> lgstsqResp = webClientBuilder.post().uri(lgstsq_URI)
                            .body(Mono.just(errorMsg), ErrorMsg.class).retrieve().bodyToMono(ErrorMsg.class);// .timeout(Duration.ofMillis(10_000));
                    errorMsg = lgstsqResp.block();
                } catch (Exception e) {
                    log.error(e);
                }

            } else {
                caData = (dfhcommarea.getCaRequestId() + dfhcommarea.getCaReturnCode() + dfhcommarea.getCaCustomerNum()
                        + dfhcommarea.getCaRequestSpecific().substring(0, 72));
                try {
                    Mono<ErrorMsg> lgstsqResp = webClientBuilder.post().uri(lgstsq_URI)
                            .body(Mono.just(errorMsg), ErrorMsg.class).retrieve().bodyToMono(ErrorMsg.class);// .timeout(Duration.ofMillis(10_000));
                    errorMsg = lgstsqResp.block();
                } catch (Exception e) {
                    log.error(e);
                }

            }

        }

        log.debug("Method writeErrorMessage completed..");
    }

    /* End of program */
}