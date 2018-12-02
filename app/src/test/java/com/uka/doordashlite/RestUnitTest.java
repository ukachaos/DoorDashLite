package com.uka.doordashlite;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import DataObjects.Restaurant;
import Services.RestaurantService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RestUnitTest {

    @Test
    public void testRestSingleCall() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mockWebServer.enqueue(new MockResponse().setBody("{\"phone_number\":\"+16509889323\",\"yelp_review_count\":1048,\"is_consumer_subscription_eligible\":true,\"offers_delivery\":true,\"max_order_size\":null,\"delivery_fee\":100,\"max_composite_score\":10,\"id\":30,\"average_rating\":4.6,\"tags\":[\"Thai\"],\"delivery_radius\":7257,\"inflation_rate\":0.0,\"menus\":[{\"status\":\"Pre-order for 5:45PM\",\"menu_version\":1,\"subtitle\":\"Dinner\",\"name\":\"Amarin Thai Cuisine (Mountain View) (Dinner) (DD4B)\",\"open_hours\":[[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":22,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":22,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}]],\"is_business_enabled\":true,\"is_catering\":false,\"id\":301091,\"status_type\":\"pre-order\"},{\"status\":\"Pre-order for 5:45PM\",\"menu_version\":1,\"subtitle\":\"Dinner\",\"name\":\"Amarin Thai Cuisine (Mountain View) (Dinner)\",\"open_hours\":[[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":22,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":22,\"minute\":0}],[{\"hour\":17,\"minute\":0},{\"hour\":21,\"minute\":0}]],\"is_business_enabled\":null,\"is_catering\":false,\"id\":107032,\"status_type\":\"pre-order\"},{\"status\":\"Available now\",\"menu_version\":1,\"subtitle\":\"Lunch\",\"name\":\"Amarin Thai Cuisine (Mountain View) (Lunch)\",\"open_hours\":[[{\"hour\":11,\"minute\":0},{\"hour\":14,\"minute\":0}],[{\"hour\":11,\"minute\":0},{\"hour\":14,\"minute\":0}],[{\"hour\":11,\"minute\":0},{\"hour\":14,\"minute\":0}],[{\"hour\":11,\"minute\":0},{\"hour\":14,\"minute\":0}],[{\"hour\":11,\"minute\":0},{\"hour\":14,\"minute\":0}],[{\"hour\":12,\"minute\":0},{\"hour\":14,\"minute\":30}],[{\"hour\":12,\"minute\":0},{\"hour\":14,\"minute\":30}]],\"is_business_enabled\":null,\"is_catering\":false,\"id\":107070,\"status_type\":\"open\"}],\"show_store_menu_header_photo\":false,\"composite_score\":9,\"offers_pickup\":null,\"number_of_ratings\":3930,\"status_type\":\"open\",\"is_only_catering\":false,\"status\":\"29 - 39 mins\",\"delivery_fee_details\":{\"final_fee\":{\"display_string\":\"$1.00\",\"unit_amount\":100},\"discount\":{\"description\":\"\",\"source_type\":\"first_delivery\",\"text\":\"Try $$1.00 Delivery\",\"discount_type\":\"try_reduced_fee\",\"amount\":{\"display_string\":\"$1.99\",\"unit_amount\":199},\"min_subtotal\":{\"display_string\":\"$0.00\",\"unit_amount\":0}},\"surge_fee\":{\"display_string\":\"$0.00\",\"unit_amount\":0},\"original_fee\":{\"display_string\":\"$2.99\",\"unit_amount\":299}},\"object_type\":\"restaurant.restaurant\",\"description\":\"Thai Cuisine\",\"business\":{\"business_vertical\":null,\"id\":32,\"name\":\"Amarin Thai Cuisine\"},\"yelp_biz_id\":\"amarin-thai-cuisine-mountain-view\",\"asap_time\":34,\"yelp_rating\":3.0,\"extra_sos_delivery_fee\":0,\"business_id\":32,\"special_instructions_max_length\":null,\"cover_img_url\":\"https://cdn.doordash.com/media/restaurant/cover/Amarin-Thai-Cuisine.png\",\"address\":{\"city\":\"Mountain View\",\"subpremise\":\"\",\"id\":368,\"printable_address\":\"176 Castro Street, Mountain View, CA 94041, USA\",\"state\":\"CA\",\"street\":\"176 Castro Street\",\"country\":\"United States\",\"lat\":37.39422,\"lng\":-122.0788802,\"shortname\":\"176 Castro Street\",\"zip_code\":\"94041\"},\"price_range\":2,\"slug\":\"amarin-thai-cuisine-mountain-view\",\"show_suggested_items\":true,\"name\":\"Amarin Thai Cuisine (Mountain View)\",\"is_newly_added\":false,\"is_good_for_group_orders\":true,\"service_rate\":11.0,\"merchant_promotions\":[{\"minimum_subtotal_monetary_fields\":{\"currency\":\"USD\",\"display_string\":\"$0.00\",\"unit_amount\":null,\"decimal_places\":2},\"delivery_fee\":100,\"delivery_fee_monetary_fields\":{\"currency\":\"USD\",\"display_string\":\"$1.00\",\"unit_amount\":100,\"decimal_places\":2},\"minimum_subtotal\":null,\"new_store_customers_only\":true,\"id\":0}],\"header_image_url\":null}"));

        RestaurantService service = retrofit.create(RestaurantService.class);

        Call<Restaurant> restaurantCall = service.getRestaurantByID("30");

        assertTrue(restaurantCall.execute() != null);

        mockWebServer.shutdown();
    }

    @Test
    public void testRestCall() throws Exception {
//        getClass().getClassLoader().getResource("sample.json");
        InputStream jsonStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        byte[] jsonBytes = IOUtils.toByteArray(jsonStream);

        MockWebServer mockWebServer = new MockWebServer();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mockWebServer.enqueue(new MockResponse().setBody(new String(jsonBytes)));

        RestaurantService service = retrofit.create(RestaurantService.class);

        Call<List<Restaurant>> restaurantCall = service.getRestaurants("37.422740", " -122.139956", 0, 10);

        Response<List<Restaurant>> listResponse = restaurantCall.execute();

        assertTrue(listResponse != null);

        assertEquals(listResponse.body().size(), 10);

        mockWebServer.shutdown();
    }
}