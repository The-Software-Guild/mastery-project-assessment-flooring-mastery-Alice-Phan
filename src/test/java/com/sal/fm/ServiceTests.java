package com.sal.fm;

import com.sal.fm.dao.FlooringMasteryException;
import com.sal.fm.model.Order;
import com.sal.fm.service.FMService;
import com.sal.fm.service.FMServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTests {

    public static FMService service;
    private static Order order1, order2;
    private LocalDate date = LocalDate.now();

    public ServiceTests()
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        service = ctx.getBean("service", FMServiceImpl.class);

        // imagine closing ctx??? couldn't be me
    }

    @BeforeAll
    public static void setUpClass() throws FlooringMasteryException
    {
        order1 = new Order("45", "Doge", "WA", "9.25", "Laminate", "328", "1.75", "2.1", "574", "688.8", "116.809",
                "1379.609");
        order2 = new Order("46", "Doge", "WA", "9.25", "Laminate", "328", "1.75", "2.1", "574", "688.8",
                "116.809",
                "1379.609");
    }

    @BeforeEach
    public void setUp() throws FlooringMasteryException
    {
        service.addNewOrder(date, order1.getCustomerName(), order1.getState(), order1.getProductType(), order1.getArea().toString());
        service.addNewOrder(date, order2.getCustomerName(), order2.getState(), order2.getProductType(), order2.getArea().toString());
    }

    @AfterEach
    public void tearDown() throws FlooringMasteryException
    {
        service.removeOrder(date, order1.getOrderNumber());
        service.removeOrder(date, order2.getOrderNumber());
    }

    @org.junit.jupiter.api.Test
    public void getOrderService() throws FlooringMasteryException
    {
        assertEquals(service.getOrder(date, 45), order1);
    }

    @org.junit.jupiter.api.Test
    public void listAllOrdersService() throws Exception
    {
        List<Order> testList = service.listAllOrders(date);

        assertEquals(testList.size(), 2);
    }

    @org.junit.jupiter.api.Test
    public void editOrderService() throws Exception
    {
        {
            String customerName = order1.getCustomerName(), state = "TX", productType = "Tile", area = "550";

            service.editOrder(date, order1.getOrderNumber(), customerName, state, productType, area);

            assertEquals(order1.getCustomerName(), customerName);
            assertEquals(order1.getState(), state);
            assertEquals(order1.getProductType(), productType);
            assertEquals(order1.getArea(), new BigDecimal(area));
            // test calculations
        }

        {
            String customerName = "Ojama Yellow", state = order2.getState(), productType = order2.getProductType(),
                    area = order2.getArea().toString();

            service.editOrder(date, order2.getOrderNumber(), customerName, state, productType, area);

            assertEquals(order2.getCustomerName(), customerName);
            assertEquals(order2.getState(), state);
            assertEquals(order2.getProductType(), productType);
            assertEquals(order2.getArea(), new BigDecimal(area));
            // test calculations
        }
    }
}
