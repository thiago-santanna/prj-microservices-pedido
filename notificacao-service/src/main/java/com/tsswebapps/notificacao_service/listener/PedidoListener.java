package com.tsswebapps.notificacao_service.listener;

import com.tsswebapps.notificacao_service.entity.Pedido;
import com.tsswebapps.notificacao_service.service.EmailService;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoListener.class);

    private final EmailService emailService;

    public PedidoListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-notificacao")
    public void enviarNotificacao(Pedido pedido) {

//        if (pedido.getValorTotal() > 1000) {
//            logger.info("Pedido com valor acima de 1000, enviando notificação para o pedido: {}", pedido);
//            throw new RuntimeException("Valor do pedido acima de 1000");
//        }

        emailService.enviarEmail(pedido);
        logger.info("Enviando notificação para o pedido: {}", pedido);
    }
}
