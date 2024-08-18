package com.hutech.demo.service;

import com.hutech.demo.model.CartItem;
import com.hutech.demo.model.Order;
import com.hutech.demo.model.OrderDetail;
import com.hutech.demo.repository.OrderDetailRepository;
import com.hutech.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//@Transactional
//public class OrderService {
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private OrderDetailRepository orderDetailRepository;
//    @Autowired
//    private CartService cartService;  // Assuming you have a CartService
//
//    @Transactional
//    public Order createOrder(String customerName, List<CartItem> cartItems) {
//        Order order = new Order();
//        order.setCustomerName(customerName);
//        order = orderRepository.save(order);
//
//        for (CartItem item : cartItems) {
//            OrderDetail detail = new OrderDetail();
//            detail.setOrder(order);
//            detail.setProduct(item.getProduct());
//            detail.setQuantity(item.getQuantity());
//            orderDetailRepository.save(detail);
//        }
//
//        // Optionally clear the cart after order placement
//        cartService.clearCart();
//
//        return order;
//    }
//}

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService;

    public Order createOrder(String customerName, String shippingAddress, String phoneNumber, String email, String notes, String paymentMethod, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setShippingAddress(shippingAddress);
        order.setPhoneNumber(phoneNumber);
        order.setEmail(email);
        order.setNotes(notes);
        order.setPaymentMethod(paymentMethod);
        order = orderRepository.save(order);

        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }

        // Optionally clear the cart after order placement
        cartService.clearCart();

        return order;
    }
}
