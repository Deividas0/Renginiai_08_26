//package org.example;
//
//import java.nio.file.Paths;
//
//import static org.springframework.http.RequestEntity.post;
//import static spark.Spark.post;
//import static spark.Spark.port;
//import static spark.Spark.staticFiles;
//
//import com.stripe.Stripe;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Server {
//
//
//    public static void main(String[] args) {
//        port(5500);
//
//        // This is your test secret API key.
//        Stripe.apiKey = "sk_test_51Pvg0g02npHYE7SXy4g8bIcDS6kZ7WINRkWDtBGD6IvoC8Nv4IDQmyltDrXJteznG9YNVKC879Ecu0IaAA5LarPT005YEWpDBH";
//
//        staticFiles.externalLocation(
//                Paths.get("public").toAbsolutePath().toString());
//
//        post("/create-checkout-session", (request, response) -> {
//            String YOUR_DOMAIN = "http://127.0.0.1:5500";
//            SessionCreateParams params =
//                    SessionCreateParams.builder()
//                            .setMode(SessionCreateParams.Mode.PAYMENT)
//                            .setSuccessUrl("http://127.0.0.1:5500" + "/success.html")
//                            .setCancelUrl("http://127.0.0.1:5500" + "/cancel.html")
//                            .addLineItem(
//                                    SessionCreateParams.LineItem.builder()
//                                            .setQuantity(1L)
//                                            // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
//                                            .setPrice("price_1PviHC02npHYE7SXS9vXOgfE")
//                                            .build())
//                            .build();
//            Session session = Session.create(params);
//
//            response.redirect(session.getUrl(), 303);
//            return "";
//        });
//    }
//}