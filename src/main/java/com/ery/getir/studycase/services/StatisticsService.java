package com.ery.getir.studycase.services;

import com.ery.getir.studycase.collections.Order;
import com.ery.getir.studycase.collections.OrderItem;
import com.ery.getir.studycase.dtos.MontlyStaticticDto;
import com.ery.getir.studycase.repositories.OrderRepository;
import com.ery.getir.studycase.responses.MontlyStaticticReponse;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sem", "Oct", "Nov", "Dec"};
    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public StatisticsService(OrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    public MontlyStaticticReponse getCustomerMonthlyStatisticByCustomerId(String customerId) {
        MontlyStaticticReponse montlyStaticticReponse = new MontlyStaticticReponse();

        List<Order> orderList = orderRepository.findAllByCustomer(customerService.findById(customerId).get());

        if (CollectionUtils.isEmpty(orderList)) {
            return montlyStaticticReponse;
        }


        Map<Integer, List<Order>> result = orderList.stream()
                .collect(Collectors.groupingBy(ordr -> ordr.getCreatedAt().getMonth()));

        List<MontlyStaticticDto> collect = result.entrySet().stream()
                .map(entry -> createNewMontlyStaticticDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        montlyStaticticReponse.setMontlyStaticticDtoList(collect);

        return montlyStaticticReponse;

    }

    private MontlyStaticticDto createNewMontlyStaticticDto(Integer month, List<Order> orders) {
        MontlyStaticticDto montlyStaticticDto = new MontlyStaticticDto();
        BigDecimal totalPurchasedAmount = BigDecimal.ZERO;
        int bookCount = 0;


        for (Order order : orders) {
            List<OrderItem> orderItemList = order.getOrderItemList();
            bookCount += orderItemList.size();
            for (OrderItem orderItem : orderItemList) {
                totalPurchasedAmount = totalPurchasedAmount.add(orderItem.getPrice());
            }
        }
        montlyStaticticDto.setTotalOrderCount(orders.size());
        montlyStaticticDto.setMonth(MONTH[month]);
        montlyStaticticDto.setTotalBookCount(bookCount);
        montlyStaticticDto.setTotalPurchasedAmount(totalPurchasedAmount);

        return montlyStaticticDto;
    }

}
