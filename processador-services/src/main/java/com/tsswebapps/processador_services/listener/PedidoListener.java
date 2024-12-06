package com.tsswebapps.processador_services.listener;

import com.tsswebapps.processador_services.entity.Pedido;
import com.tsswebapps.processador_services.entity.enums.Status;
import com.tsswebapps.processador_services.service.PedidoService;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoListener.class);

    private final PedidoService pedidoService;

    public PedidoListener(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-processamento")
    public void processarPedido(Pedido pedido) {
        logger.info("Processando pedido: {}", pedido.toString());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error("Erro ao processar pedido: {}", e.getMessage());
        }
        pedido.processar();
        pedidoService.save(pedido);
        logger.info("Pedido processado: {}", pedido);
    }

}