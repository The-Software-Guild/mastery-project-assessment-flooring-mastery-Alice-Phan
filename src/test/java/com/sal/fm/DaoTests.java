package com.sal.fm;

import com.sal.fm.dao.FlooringMasteryDao;
import com.sal.fm.dao.FlooringMasteryDaoImpl;
import com.sal.fm.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoTests {

    public static FlooringMasteryDao fmDao;
    private static Order order1, order2;
    private LocalDate date = LocalDate.now();

    public DaoTests()
    {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception
    {
        fmDao = new FlooringMasteryDaoImpl();

        order1 = new Order("45", "Doge", "WA", "9.25", "Laminate", "328", "1.75", "2.1", "574", "688.8", "116.809",
                "1379.609");
        order2 = new Order("46", "Doge", "WA", "9.25", "Laminate", "328", "1.75", "2.1", "574", "688.8",
                "116.809",
                "1379.609");
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception
    {
        fmDao.addNewOrder(date, order1.getCustomerName(), order1.getState(), order1.getProductType(), order1.getArea().toString());
        fmDao.addNewOrder(date, order2.getCustomerName(), order2.getState(), order2.getProductType(), order2.getArea().toString());
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception
    {
        fmDao.removeOrder(date, order1.getOrderNumber());
        fmDao.removeOrder(date, order2.getOrderNumber());
    }

    @org.junit.jupiter.api.Test
    public void getOrderDao() throws Exception
    {
        assertEquals(fmDao.getOrder(date, 45), order1);
    }

    @org.junit.jupiter.api.Test
    public void listAllOrdersDao() throws Exception
    {
        List<Order> testList = fmDao.listAllOrders(date);

        assertEquals(testList.size(), 2);
    }

    @org.junit.jupiter.api.Test
    public void editOrderDao() throws Exception {
        {
            String customerName = order1.getCustomerName(), state = "TX", productType = "Tile", area = "550";

            fmDao.editOrder(date, order1.getOrderNumber(), customerName, state, productType, area);

            assertEquals(order1.getCustomerName(), customerName);
            assertEquals(order1.getState(), state);
            assertEquals(order1.getProductType(), productType);
            assertEquals(order1.getArea(), new BigDecimal(area));
            // test calculations
        }

        {
            String customerName = "Ojama Yellow", state = order2.getState(), productType = order2.getProductType(),
                    area = order2.getArea().toString();

            fmDao.editOrder(date, order2.getOrderNumber(), customerName, state, productType, area);

            assertEquals(order2.getCustomerName(), customerName);
            assertEquals(order2.getState(), state);
            assertEquals(order2.getProductType(), productType);
            assertEquals(order2.getArea(), new BigDecimal(area));
            // test calculations
        }
    }
}
