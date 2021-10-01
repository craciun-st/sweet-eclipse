package com.codecool.sweeteclipse.init;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class ServerInit implements CommandLineRunner {
    @Value("${stripe.apiKey}")
    String stripeSecret;

    @Override
    public void run(String... args) throws Exception {
        Stripe.apiKey = stripeSecret;
    }
}
