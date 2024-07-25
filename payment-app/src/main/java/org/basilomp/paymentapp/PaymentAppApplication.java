package org.basilomp.paymentapp;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class PaymentAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(PaymentAppApplication.class, args);
    }

}
