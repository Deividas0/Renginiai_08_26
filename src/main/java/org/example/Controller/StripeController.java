package org.example.Controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.example.Service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody Map<String, Object> data) {
        try {
            Long amount = Long.parseLong(data.get("amount").toString());
            String currency = data.get("currency").toString();
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount, currency);

            Map<String, String> responseData = new HashMap<>();
            responseData.put("clientSecret", paymentIntent.getClientSecret());

            return ResponseEntity.ok(responseData);
        } catch (StripeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody Map<String, Object> data) throws StripeException {
        Stripe.apiKey = "sk_test_51Pvg0g02npHYE7SXy4g8bIcDS6kZ7WINRkWDtBGD6IvoC8Nv4IDQmyltDrXJteznG9YNVKC879Ecu0IaAA5LarPT005YEWpDBH"; // Use your Stripe Secret Key

        String eventName = data.get("eventName").toString();
        Long eventPrice = Long.parseLong(data.get("eventPrice").toString()); // Stripe expects amount in cents
        String imageUrl = data.get("imageUrl").toString();  // Event image URL from Imgur

        // Creating the Stripe session
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)  // Use enum for card payment
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount(eventPrice)  // Stripe expects the amount in cents
                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName(eventName)  // Name of the product (event)
                                                .addImage(imageUrl)  // Add the image URL
//                                                .setDescription("labas")
                                                .build())
                                        .build())
                                .setQuantity(1L)  // Quantity for the item (event)
                                .build())
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5500/success.html")
                .setCancelUrl("http://localhost:5500/cancel.html")
                .build();

        Session session = Session.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("checkoutUrl", session.getUrl());

        return response;  // Return Stripe Checkout URL to the frontend
    }
}