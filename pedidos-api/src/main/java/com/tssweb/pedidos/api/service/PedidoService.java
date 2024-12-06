package com.tssweb.pedidos.api.service;

import com.tssweb.pedidos.api.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchageName;

    private final RabbitTemplate rabbitTemplate;

    public PedidoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        logger.info("PedidoService created");
    }

    public Pedido enfileirarPedido(Pedido pedido) {
        logger.info("Enfileirando pedido: {}", pedido.toString());
        rabbitTemplate.convertAndSend(exchageName, "", pedido);
        return pedido;
    }
}
