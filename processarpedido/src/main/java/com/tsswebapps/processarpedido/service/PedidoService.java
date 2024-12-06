package com.tsswebapps.processarpedido.service;

import com.tsswebapps.processarpedido.entity.ItemPedido;
import com.tsswebapps.processarpedido.entity.Pedido;
import com.tsswebapps.processarpedido.repository.PedidoRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoService itemPedidoService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoService itemPedidoService, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoService = itemPedidoService;
        this.produtoService = produtoService;
    }

    public void save(Pedido pedido) {
        produtoService.save(pedido.getItens());
        List<ItemPedido> itemPedidos = itemPedidoService.save(pedido.getItens());
        pedidoRepository.save(pedido);
        itemPedidoService.updateItemIdPedido(itemPedidos, pedido);
        log.info("Pedido salvo com sucesso");
    }
}
